package ikariera.company

import cz.ikariera.admin.CreditsReqests
import cz.ikariera.company.Company
import cz.ikariera.company.Purchase
import cz.ikariera.company.Services
import cz.ikariera.company.ServicesExpire
import cz.ikariera.security.User

class CompanyAccountServicesController {


    def springSecurityService
    def internalEmailService


    def index() {
        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "login")
            return
        }

        Company companyInstance = user?.company

        if (!companyInstance) {
            redirect(controller: "companyAccount", action: "index")
            return
        }



        render(view: "index", model: [companyInstance: companyInstance])

    }


    def activation() {

        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "login")
            return
        }

        Company companyInstance = user?.company

        String uniqueName = params.id as String

        def expirationTime = getExpirationTime(uniqueName, companyInstance?.servicesExpire)

        def servicesInstance = Services.findByUniqueName(uniqueName)

        if (!servicesInstance) {
            render(view: "/notFound")
            return
        }

        render(view: "activation", model: [expirationTime: expirationTime, servicesInstance: servicesInstance, companyInstance: companyInstance])

    }


    def save() {

        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "login")
            return
        }
        String uniqueName = params.uniqueName

        Company companyInstance = user?.company

        def servicesInstance = Services.findByUniqueName(uniqueName)
        def expirationTime = getExpirationTime(uniqueName, companyInstance?.servicesExpire)

        if (!servicesInstance) {
            render(view: "/notFound")
            return
        }


        if (companyInstance.credits < servicesInstance.creditPrice) {

            flash.error = message(code: "company.service.activation.notEnoughCredits.error")
            redirect(action: "activation", id: uniqueName, model: [expirationTime: expirationTime, servicesInstance: servicesInstance])
            return
        }



        Date dateExpire = new Date() + servicesInstance.defaultExpirationTime

        if (!companyInstance.servicesExpire) {
            companyInstance.servicesExpire = new ServicesExpire()

        }

        switch (uniqueName) {
            case "cv-service":
                companyInstance?.servicesExpire?.cv = dateExpire
                break
            case "articles-service":
                companyInstance?.servicesExpire?.article = dateExpire
                break
            case "mail-service":
                companyInstance?.servicesExpire?.mail = dateExpire
                break
            case "top-service":
                companyInstance?.servicesExpire?.topOffer = dateExpire
                break
            case "hero-service":
                companyInstance?.servicesExpire?.hero = dateExpire
                break
            case "message-service":
                companyInstance?.servicesExpire?.messages = dateExpire
                break
            case "advanced-profile-service":
                companyInstance?.servicesExpire?.topProfile = dateExpire
                break
            case "remote-post":
                companyInstance?.servicesExpire?.remote = dateExpire
                break
            case "jobofferapi":
                companyInstance?.servicesExpire?.apiActiveTill = dateExpire
                break

            case "share-service":
                companyInstance?.servicesExpire?.shareOffer = dateExpire
                break

            case "rest-service":
                companyInstance?.servicesExpire?.rest = dateExpire
                break
        }


        Purchase purchase = new Purchase(

                company: companyInstance,

                serviceName: "purchaseServices.serviceBought.label",
                user: user.firstName + " " + user.lastName + " (" + user.username + ")",
                isAdmin: false,
                datePurchased: new Date(),
                serviceNameParams: servicesInstance.name,
                credits: -servicesInstance.creditPrice,
                details: "purchaseServices.serviceBought.text",
                comment: "",
                detailsParams: ""

        )


        companyInstance.credits -= servicesInstance.creditPrice


        if (!companyInstance.addToPurchases(purchase).save(flush: true, failOnError: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "activation", id: uniqueName, model: [expirationTime: expirationTime, servicesInstance: servicesInstance])
            return
        }



        flash.message = message(code: "purchaseServices.serviceBought.text")
        redirect(action: "activation", id: uniqueName, model: [expirationTime: expirationTime, servicesInstance: servicesInstance])

    }

    def askForCredits() {
        def user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "login")
            return
        }

        render view: 'requestCredits'
    }

    def requireCredits() {
        def user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: 'login')
            return
        }
        sendNotificationEmail(user)

        flash.message = message(code: 'company.requestCredits.success')
        redirect(controller: "companyAccountCreditsHistory")
    }

    private void sendNotificationEmail(User user) {
        try {
            def creditRequest = new CreditsReqests(company: user.company)
            creditRequest.save(flush: true)
            internalEmailService.sendSimpleEmailMessage(

                    grailsApplication.config.internalEmailService.infoEmails,
                    grailsApplication.config.internalEmailService.infoEmailsSubjectPrefix + "" + (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.infoEmailsReply,
                    grailsApplication.config.internalEmailService.infoEmailsSubjectPrefix + "" + message(code: 'company.creditsRequire.label'),


                    "Company " + user.company.companyName + " requires credits, please contact " + user.username,


            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e

        }
    }

    private Date getExpirationTime(String uniqueName, ServicesExpire servicesExpire) {

        def expirationTime = null

        switch (uniqueName) {

            case "cv-service":
                expirationTime = servicesExpire?.cv
                break
            case "articles-service":
                expirationTime = servicesExpire?.article
                break
            case "mail-service":
                expirationTime = servicesExpire?.mail
                break
            case "top-service":
                expirationTime = servicesExpire?.topOffer
                break
            case "hero-service":
                expirationTime = servicesExpire?.hero
                break
            case "message-service":
                expirationTime = servicesExpire?.messages
                break
            case "advanced-profile-service":
                expirationTime = servicesExpire?.topProfile
                break
            case "remote-post":
                expirationTime = servicesExpire?.remote
                break
            case "jobofferapi":
                expirationTime = servicesExpire?.apiActiveTill
                break

            case "share-service":
                expirationTime = servicesExpire?.shareOffer
                break

            case "rest-service":
                expirationTime = servicesExpire?.rest
                break

        }
        return expirationTime
    }


}
