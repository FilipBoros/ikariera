package cz.ikariera.company

import cz.ikariera.admin.AdminEmails
import cz.ikariera.company.*
import cz.ikariera.security.User
import cz.ikariera.service.CompanyServicesService
import cz.ikariera.student.LanguageType
import cz.ikariera.student.StudentAccount
import cz.ikariera.student.University

class CompanyAccountEmailSearchController {

    def springSecurityService

    def internalEmailService

    def index(Long emailId, EmailFilterCommand filterCommand) {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def companyEmailsInstance = CompanyEmails.get(emailId)


        if (!companyEmailsInstance) {
            redirect(controller: "companyAccountEmail")
            return
        }

        def company = user.company
        String uniqueName = "mail-service"
        if (!CompanyServicesService.isActivated(uniqueName, company)) {

            redirect(controller: "companyAccountServices", action: "activation", id: uniqueName)
            return
        }

        filterCommand.jobCategories = params.list('jobCategories')
        filterCommand.educations = params.list('educations')
        filterCommand.languages = params.list('languages')

        filterCommand.allStudents = params.allStudents ? Integer.parseInt(params.allStudents) : 0

        def studentInstanceList = StudentAccount.studentFilter(filterCommand.filterParams)
        def totalStudents = studentInstanceList.totalCount

        def fixPrice = Services.findByUniqueName('send-mail-fix')?.creditPrice
        def varPrice = Services.findByUniqueName('send-mail-var')?.creditPrice

        BigDecimal creditPrice = totalStudents * varPrice + fixPrice

        if (params.send) {

            params.send = null

            if (user.company.credits < creditPrice) {

                flash.error = message(code: 'company.email.notEnoughCredits.error')

                render view: 'index',
                        model: [
                                companyEmailsInstance: companyEmailsInstance,
                                studentInstanceTotal : totalStudents,
                                filterParams         : filterCommand.filterParams,
                                creditPrice          : creditPrice,
                                companyCredits       : company.credits,
                                companyInstance      : user.company
                        ],
                        params: params
                return
            }
            if (totalStudents == 0) {

                flash.error = message(code: 'company.email.noStudentSelected.error')
                render view: 'index',
                        model: [
                                companyEmailsInstance: companyEmailsInstance,
                                studentInstanceTotal : totalStudents,
                                filterParams         : filterCommand.filterParams,
                                creditPrice          : creditPrice,
                                companyCredits       : company.credits,
                                companyInstance      : user.company
                        ],
                        params: params
                return
            }

            companyEmailsInstance.recipients = ""
            studentInstanceList.eachWithIndex { StudentAccount student, int i ->

                companyEmailsInstance.recipients += student?.user?.username + ', '
            }

            companyEmailsInstance.numberRecipients = totalStudents
            if (!send(company, user, companyEmailsInstance, creditPrice)) {
                params.send = null
                //println("what")

                flash.error = message(code: 'system.unspecifiedError.error')
                render view: 'index',
                        model: [
                                companyEmailsInstance: companyEmailsInstance,
                                studentInstanceTotal : totalStudents,
                                filterParams         : filterCommand.filterParams,
                                creditPrice          : creditPrice,
                                companyCredits       : company.credits,
                                companyInstance      : user.company
                        ],
                        params: params
                return

            }
            flash.message =  message(code: 'email.send.in.fewHours')

            redirect(controller: 'companyAccountEmail')
            return

        }

        params.send = null
        render view: 'index',
                model: [
                        companyEmailsInstance: companyEmailsInstance,
                        studentInstanceTotal : totalStudents,
                        filterParams         : filterCommand.filterParams,
                        creditPrice          : creditPrice,
                        companyCredits       : company.credits,
                        companyInstance      : user.company
                ],
                params: params
    }

    private boolean send(Company companyInstance, User user, CompanyEmails companyEmailsInstance, BigDecimal creditPrice) {

        Purchase purchase = new Purchase(

                company: companyInstance,
                serviceName: "purchaseServices.serviceBought.label",
                user: user.firstName + " " + user.lastName + " (" + user.username + ")",
                isAdmin: false,
                datePurchased: new Date(),
                serviceNameParams: 'send-mail',
                credits: creditPrice,
                details: companyEmailsInstance.subject,
                comment: "",
                detailsParams: ""

        )

        companyInstance.credits -= creditPrice
        companyEmailsInstance.dateSent = new Date()
        companyEmailsInstance.creditCost = creditPrice

        if (!companyInstance.addToPurchases(purchase).save(flush: true, failOnError: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            return false
        }

        def adminEmailsInstance = new AdminEmails(
                attachment: companyEmailsInstance.attachment,
                subject: companyEmailsInstance.subject,
                message: companyEmailsInstance.message,
                numberRecipients: companyEmailsInstance.numberRecipients,
                recipients: companyEmailsInstance.recipients,
                companyEmail: companyEmailsInstance,
                originalName: companyEmailsInstance.originalName,
        )

        if (!adminEmailsInstance.save(failOnError: true, flush: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            return false
        }

        sendNotificationEmail(companyInstance, adminEmailsInstance.id)

        return true
    }

    private void sendNotificationEmail(Company companyInstance, Long emailId) {
        try {
            internalEmailService.sendInfoMail(
                    grailsApplication.config.internalEmailService.infoEmailsSubjectPrefix + "" + "Mass Emailing",
                    grailsApplication.config.internalEmailService.infoEmails,
                    "Company " + companyInstance.companyName + " wants to send public email, see http://ikariera.cz/admin-email/display-email/" + emailId,
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            //println("Email error.")
        }
    }
}

/**
 * Command class for binding filter data from controller
 */
class EmailFilterCommand {
    List<JobCategory> jobCategories = []
    List<University> educations = []
    List<LanguageType> languages = []

    String sort
    String order

    Integer max
    Integer offset

    Boolean studentOrganisation

    Integer allStudents

    // setter for JobCategories (workaround for dynamicBinding)
    public setJobCategories(List<String> jobCategories) {
        List<JobCategory> newJobCategories = []
        jobCategories.each {
            def jobCategory = JobCategory.get(it)
            if (jobCategory)
                newJobCategories.add(jobCategory)
        }
        this.jobCategories = newJobCategories
    }

    // setter for JobCategories (workaround for dynamicBinding)
    public setEducations(List<String> educations) {
        List<University> newUniversities = []
        educations.each {
            def university = University.get(it)
            if (university)
                newUniversities.add(university)
        }
        this.educations = newUniversities
    }

    // setter for JobCategories (workaround for dynamicBinding)
    public setLanguages(List<String> languages) {
        List<LanguageType> newLanguages = []
        languages.each {
            def language = LanguageType.get(it)
            if (language)
                newLanguages.add(language)
        }
        this.languages = newLanguages
    }

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << [
                "jobCategories"      : jobCategories,
                "educations"         : educations,
                "languages"          : languages,
                "studentOrganisation": studentOrganisation,
                "allStudents"        : allStudents,
                "max"                : max,
                "offset"             : offset,
                "sort"               : sort,
                "order"              : order
        ]
        return filterParams
    }
}