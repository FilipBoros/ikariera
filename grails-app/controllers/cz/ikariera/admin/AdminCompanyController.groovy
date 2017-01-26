package cz.ikariera.admin

import cz.ikariera.admin.CreditsReqests
import cz.ikariera.admin.RemoteServer
import cz.ikariera.company.*
import cz.ikariera.security.User
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.time.TimeCategory

class AdminCompanyController {

    def springSecurityService
    //def mailService
    def internalEmailService

    def adminService

    def index(CompanyAdminFilterCommand filterCommand) {

        def user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "logout")
            return
        }

        filterCommand.max = Math.min(params.int('max') ? params.int('max') : 20, 100)
        filterCommand.offset = params.int('offset') ? params.int('offset') : 0
        filterCommand.order = params.order ? params.order : "desc"
        filterCommand.sort = params.sort ? params.sort : "dateCreated"

        filterCommand.mainJobCategories = params.list('mainJobCategories')

        def list = params?.list('mainJobCategories').collect { if (it != "") it as Long }

        params?.mainJobCategories = list

        def companyInstanceList = Company.companiesAdminFilter(filterCommand.filterParams)

        session["filterParams"] = filterCommand

        render view: 'index',
                model: [
                        companyInstanceList : companyInstanceList,
                        companyInstanceTotal: companyInstanceList?.totalCount
                ],
                params: params
    }


    def disableCompany() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([companyId: false, data: "A"]) as JSON
        }

        def companyId = params.id

        if (!companyId) {

            render([companyId: false, data: "A"]) as JSON
            return
        }

        def company = Company.get(companyId)
        company.active = false
        company.save(flush: true)

        def result = [companyId: companyId, data: "N"]
        def converter = result as JSON;
        render converter.toString();

    }


    def denyStartup() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
        }
        def companyId = params.id
        if (!companyId) {

            render([text: "companyId: false"]) as JSON
            return
        }

        def company = Company.get(companyId)

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1); //Year, month and day of month
        company.startup = cal.getTime()

        if (!company.validate()) {

            render([text: "companyId: false"]) as JSON
            return

        }
        company.save(flush: true)

        def result = [companyId: companyId, data: "N"]
        def converter = result as JSON;
        render converter.toString();
    }


    def setStartup() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
        }
        def companyId = params.id
        if (!companyId) {

            render([text: "companyId: false"]) as JSON
            return
        }

        def company = Company.get(companyId)

        use(TimeCategory) {
            company.startup = new Date() + 730.days
        }

        if (!company.validate()) {

            render([text: "companyId: false"]) as JSON
            return

        }
        company.save(flush: true)

        def result = [companyId: companyId, data: "A"]
        def converter = result as JSON;
        render converter.toString();
    }
    /**
     * will enable company by ajax
     */

    def enableCompany() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
        }
        def companyId = params.id
        if (!companyId) {

            render([text: "companyId: false"]) as JSON
            return
        }

        def company = Company.get(companyId)

        company.active = true

        if (!company.validate()) {

            render([text: "companyId: false"]) as JSON
            return

        }
        def recipient = CompanyAccount.find("from CompanyAccount as c where c.user!=null and c.user.company=:company order by c.user.lastLoginDate desc", [company: company])

        if (company.save(flush: true)) {

            String emailAddress = recipient?.user?.username?.toString()?.toLowerCase()

            if (emailAddress ==~ /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/) {
/*
                try {

                    mailService.sendMail {

                        // Mail parameters
                        from "\"" + message(code: 'mail.from.label.registration') + " - iKariera.cz\" <registration@ikariera.cz>"
                        to emailAddress
                        subject "[ikariera] " + message(code: "admin.enabled.company.text")
                        html g.render(template: "/email/enableCompanyEmail", model: [company: company])



                    }
                } catch (Exception e) {
                    log.error "Error: ${e.message}", e
                }
*/

                try {
                    internalEmailService.sendSimpleEmailMessage(
                            emailAddress,


                            message(code: 'mail.company.enabled') + "" + (grailsApplication.config.internalEmailService.name),
                            grailsApplication.config.internalEmailService.replyAddress,
                            grailsApplication.config.internalEmailService.subjectPrefix + "" + params.subject,


                            g.render(template: "/email/enableCompanyEmail", model: [company: company])
                    )
                }
                catch (Exception e) {
                    log.error "Error: ${e.message}", e
                }
            }

            def result = [companyId: companyId, data: "A"]
            def converter = result as JSON;
            render converter.toString(); // Return JSON for JAVA

        } else {

            render([text: "companyId: false"]) as JSON /*TODO This could be changed, because no one knows what happened*/

        }

    }

    /**
     * Mark personal agency
     */

    def markPersonalCompany() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
        }


        def companyId = params.id

        if (!companyId) {

            render([text: "companyId: false"]) as JSON
        }


        def company = Company.get(companyId)

        company.personalAgency = true;

        company.save(flush: true)

        def result = [companyId: companyId, data: "P"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will un mark personal agency
     */

    def unMarkPersonalCompany() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            //redirect(controller: "logout")
            render([text: "companyId: false"]) as JSON
        }


        def companyId = params.id

        if (!companyId) {

            render([text: "companyId: false"]) as JSON

        }


        def company = Company.get(companyId)

        company.personalAgency = false;

        company.save(flush: true)

        def result = [companyId: companyId, data: "-"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will ban company
     */

    def banCompany() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
        }
        def companyId = params.id
        if (!companyId) {
            render([text: "companyId: false"]) as JSON
        }
        def companyInstance = Company.get(companyId)
        companyInstance.banned = true;

        /*companyInstance.companyAccount.user.eachWithIndex { it, i ->
            it.accountLocked = true;

        }*/

        companyInstance.jobOffer.eachWithIndex { it, i ->
            it.willExpire = new Date();
            //it.topPosExpiration = new Date();
        }

        companyInstance.save(flush: true)

        def result = [companyId: companyId, data: "Ban"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will unBan company
     */

    def unBanCompany() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
        }
        def companyId = params.id
        if (!companyId) {
            render([text: "companyId: false"]) as JSON
        }
        def companyInstance = Company.get(companyId)
        companyInstance.banned = false;
/*
        companyInstance.companyAccountUsers.user.eachWithIndex { it, i ->
            it.accountLocked = false;

        }
*/
        companyInstance.save(flush: true)

        def result = [companyId: companyId, data: "-"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will delete company
     */

    def delete() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
        }

        def companyId = params.id
        if (!companyId) {
            render([text: "companyId: false"]) as JSON
        }
        def companyInstance = Company.get(companyId)

        Boolean result = adminService.removeAdmins(companyInstance)

        //loop the users if admin is there.. remove him
        /*User.withTransaction { session->
            //def tx = session.beginTransaction()
            companyInstance.users.each {
                def userToRemove = UserRole.findByUser(it)
                if(userToRemove){
                    if(userToRemove.role.id == 3 || userToRemove.role.id == 4){
                        it.company = null
                        it.save(flush: true)
                        //companyInstance.removeFromUsers(it)
                    }
                }
            }

            //Update status to processing
            //tx.commit()
        }
        companyInstance.save(flush: true)*/

        companyInstance = Company.get(companyId)

        /*Untested piece of code, it should disable the admin ability to delete itself.*/
        def companyUsers = User.findAllByCompany(companyInstance)
        for (User companyUser : companyUsers) {
            if (companyUser.authorities.any{it.authority == 'ROLE_ADMIN'}){
                flash.error = "Admin is among the users of this company, so you cannot delete it"
            }
        }


        //loop for CreditsReqests relationship
        companyInstance.each {
            CreditsReqests.where {
                company == companyInstance
            }.deleteAll()
        }
/*
        companyInstance.users.each {
            UserRole.removeAll(it)
        }
*/

        //delete job offers
        /*companyInstance.jobOffer.each {
            ReqLangCombination.deleteAll(it)
        }
        //delete job offers
        companyInstance.users.each {
            Dialog.deleteAll(it)
        }*/

        //delete users
        /*companyInstance.users.each {
            it.delete()
        }*/


        try {
            companyInstance.delete(failOnError: true, flush: true)
            flash.message = "Company has been deleted."
        } catch (Exception e) {
            flash.error = "Error occur when trying to delete this company."

        }

        CompanyAdminFilterCommand f = new CompanyAdminFilterCommand()
        bindData(f, session["filterParams"])
        def lst = f.mainJobCategories.collect { it.id }
        def filter = f.getFilterParams()
        filter.mainJobCategories = lst

        redirect(controller: "adminCompany", params: filter)
    }


    def companyProfileUnrestrictedEdit() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def companyInstance = Company.get(params.id)

        if (!companyInstance) {
            flash.error = message(code: "system.companyDontExist.warning")
            redirect(action: "list")
        }

        render(view: "/adminCompany/editProfile", model: [companyInstance: companyInstance])
    }


    def companyProfileUnrestrictedSaveChanges() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }


        def companyInstance = Company.get(params.id)

        companyInstance.properties = params

        if (params.startupBox) {//the end date of startup
            use(TimeCategory) {
                companyInstance.startup = new Date() + 730.days
            }

        } else {
            Calendar cal = Calendar.getInstance();
            cal.set(2000, Calendar.JANUARY, 1); //Year, month and day of month
            companyInstance.startup = cal.getTime()
        }

        if (!companyInstance) {
            flash.error = message(code: "system.companyDontExist.warning")
            redirect(action: "list")
        }

        if (!companyInstance.validate(["companyName", "companyID", "companyWeb", "companyStreet",
                                       "companyZip", "companyCity", "companyCountry", "seoName"])) {
            render(view: "/adminCompany/editProfile", model: [companyInstance: companyInstance])
            return
        }

        if (!companyInstance?.companyWeb?.startsWith("http://")) {
            companyInstance?.companyWeb = "http://" + companyInstance.companyWeb
        }

        if (!companyInstance.save()) {
            flash.error = message(code: "system.unspecifiedError.error")
            render(view: "/adminCompany/editProfile", model: [companyInstance: companyInstance])
            return
        }

        redirect(action: "index")
    }


    def logAsCompany() {
        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def selectedCompany = Company.get(params.id)


        user.company = selectedCompany

        user.save(flush: true)



        redirect(controller: "companyAccount", action: "index")
    }

    private def publishRESTJobOffer(JobOffer job, RemoteServer server, Company company) {

        RestBuilder rest = new RestBuilder()

        def testpath = "http://localhost:8080/RESTl/listner/index"
        // send email
        def data = [:]
        data.put('joboffer', job)
        data.put('company', company)
        def resp = rest.post(testpath) {
            json data
        }
    }

    def sendJobOffersTocountries(JobOffer offer) {
        def list = params.selectedCountries
        def servicesInstance = Services.findByUniqueName("remote-post")

        User user = springSecurityService.getCurrentUser() as User

        Company companyInstance = user.company

        list.each {

            if (companyInstance.credits < servicesInstance.creditPrice) {
                flash.error = message(code: 'company.notEnoughCredits')
                redirect(action: "index", controller: "adminJobOffer")

                return
            }
            RemoteServer remoteServer = RemoteServer.get(Long.parseLong(it))
            try {
                publishRESTJobOffer(offer, remoteServer, companyInstance)
            }
            catch (Exception e) {
                render(view: 'error', model: [exception: e])
                return
            }
            Purchase purchase = new Purchase(

                    company: companyInstance,
                    serviceName: "purchaseServices.serviceBought.label",
                    user: user.firstName + " " + user.lastName + " (" + user.username + ")",
                    isAdmin: false,
                    datePurchased: new Date(),
                    serviceNameParams: servicesInstance.name,
                    credits: -servicesInstance.creditPrice,
                    details: offer?.positionName + " published to " + remoteServer.country.name,
                    comment: "",
                    detailsParams: ""
            )

            //companyInstance.credits -= servicesInstance.creditPrice

            if (!companyInstance.addToPurchases(purchase).save(flush: true, failOnError: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                redirect(action: "index")
                return
            }
        }
        flash.message = message(code: 'company.jobOffer.not.sent')
        redirect(action: "index", controller: "adminJobOffer")
    }


}

/* createAlias('mainJobCategories', '_mainJobCategories', CriteriaSpecification.LEFT_JOIN)

            and {
                mainJobCategories?.each {
                    like('_mainJobCategories.id', it.id)
                }
            }


            eq('active', true)
            eq('banned', false)

            if (params.companyName)
                ilike("companyName", '%' + params.companyName + '%')

            if (params.companyID)
                ilike("companyID", '%' + params.companyID + '%')

*/

/**
 * Command class for binding filter data from controller
 */
class CompanyAdminFilterCommand {

    List<JobCategory> mainJobCategories = []

    Boolean personalAgency

    String companyName
    String companyID

    Integer max
    Integer offset

    String sort
    String order

    // setter for JobCategories (workaround for dynamicBinding)
    public setMainJobCategories(List<String> mainJobCategories) {
        List<JobCategory> newJobCategories = []
        mainJobCategories.each {
            def jobCategory = JobCategory.get(it)
            if (jobCategory)
                newJobCategories.add(jobCategory)
        }
        this.mainJobCategories = newJobCategories
    }

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << [
                "mainJobCategories": mainJobCategories,
                "companyName"      : companyName,
                "companyID"        : companyID,
                "max"              : max,
                "personalAgency"   : personalAgency,

                "offset"           : offset,
                "sort"             : sort,
                "order"            : order
        ]

        return filterParams
    }

}