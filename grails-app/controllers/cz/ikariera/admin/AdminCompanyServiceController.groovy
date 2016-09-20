package cz.ikariera.admin

import cz.ikariera.company.Company
import cz.ikariera.company.Services
import cz.ikariera.company.ServicesExpire
import cz.ikariera.security.User

class AdminCompanyServiceController {

    def springSecurityService


    def index() {
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }
        def companyInstance = Company.get(params.id)
        if (!companyInstance) {
            redirect(controller: "adminCompany", action: "index")
            return
        }
        render(view: "/adminCompanyService/index", model: [companyInstance: companyInstance])

    }


    def activateCompanyService(Company companyInstance) {
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }


        String serviceUniqueName = params.serviceUniqueName



        Services serviceInstance = Services.findByUniqueName(serviceUniqueName)


        if (!companyInstance || !serviceInstance) {
            redirect(controller: "index")
            flash.error = message(code: 'system.unspecifiedError.error')

            return
        }

        Date dateExpire = new Date() + serviceInstance.defaultExpirationTime

        if (!companyInstance.servicesExpire) {
            companyInstance.servicesExpire = new ServicesExpire()
        }

        switch (serviceUniqueName) {
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

        if (!companyInstance.save(flush: true, failOnError: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(controller: "adminCompanyService", id: companyInstance.id)
            return
        }

        flash.message = message(code: 'services.transactionActivation.ok', args: [message(code: serviceInstance.name)])

        redirect(controller: "adminCompanyService", id: companyInstance.id)
    }


    def expireCompanyService(Company companyInstance) {
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        String serviceUniqueName = params.serviceUniqueName

        Date dateExpire = new Date()

        if (!companyInstance.servicesExpire) {
            companyInstance.servicesExpire = new ServicesExpire()
        }

        switch (serviceUniqueName) {
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

        if (!companyInstance.save(flush: true, failOnError: true)) {

            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(controller: "adminCompanyService", id: companyInstance.id)
            return
        }

        flash.message = message(code: 'services.transactionDeactivation.ok', args: [message(code: Services.findByUniqueName(serviceUniqueName).name)])

        redirect(controller: "adminCompanyService", id: companyInstance.id)
    }
}

