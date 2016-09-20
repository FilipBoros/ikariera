package ikariera.admin

import cz.ikariera.company.*
import cz.ikariera.security.User
import cz.ikariera.service.CompanyServicesService
import grails.converters.JSON

class AdminJobOfferController {

    def springSecurityService

    def index(JobOfferAdminFilterCommand filterCommand) {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "logout")
            return
        }

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        filterCommand.max = params.max
        def list = JobOffer.jobOfferAdminFilter(filterCommand.filterParams)

        render view: 'index',
                model: [
                        jobOfferInstanceList : list,
                        jobOfferInstanceTotal: list.totalCount,
                        filterParams         : filterCommand.filterParams,
                        displayedResults     : list.totalCount < params.max + params.offset ? list.totalCount - params.offset : params.max
                ],
                params: params
    }

    /**
     * nowExpire will do jobadvertisement expiration
     *
     * @return
     */

    def jobDelete() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "jobIdId: false"]) as JSON
        }
        def jobOfferId = params.id
        if (!jobOfferId) {
            render([text: "jobId: false"]) as JSON
        }
        def jobOfferInstance = JobOffer.get(jobOfferId)

        if (!jobOfferInstance) {
            render([text: "jobId: false"]) as JSON
        }
        flash.message = "Job offer was deleted."
        jobOfferInstance.delete(flush: true)

        def result = [jobOfferId: jobOfferId, data: "-"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * nowExpire will do jobadvertisement expiration
     *
     * @return
     */

    def jobExpire() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "jobIdId: false"]) as JSON
        }
        def jobOfferId = params.id
        if (!jobOfferId) {
            render([text: "jobId: false"]) as JSON
        }
        def jobOfferInstance = JobOffer.get(jobOfferId)
        def currentDate = new Date()
        jobOfferInstance.willExpire = currentDate

        def formattedDate = g.formatDate(date: currentDate, format: 'dd.MM.yyyy')

        jobOfferInstance.save(flush: true)

        def result = [jobOfferId: jobOfferId, data: formattedDate]
        def converter = result as JSON;
        render converter.toString();
    }

    def edit(JobOffer jobOfferInstance) {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }

        render(view: "edit", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])
    }

    def editCompanyJobOffer() {

        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def selectedAdvertisement = JobOffer.get(params.id)
        def selectedCompany = selectedAdvertisement.company

        if (user.company) {

            user.company = selectedCompany

            user.save(flush: true)

        } else {
            def companyUser = new CompanyAccount(
                    publicEmail: user.username,
                    company: Company.get(params.id),
                    user: user,
                    positionInCompany: "Administrator",
                    telephone: "800200300",
                    belongToAdmin: true
            )

            if (!companyUser.save(flush: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                return
            }
        }
        def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }
        redirect(controller: "companyAccountJobOffers", action: "edit", params: [id: selectedAdvertisement.id])


    }

    def publish() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            flash.error = message(code: 'system.notLogged.error')
            redirect(controller: "logout")
            return
        }

        def selectedAdvertisement = JobOffer.get(params.id)
        def selectedCompany = selectedAdvertisement.company

        if (user.company) {

            user.company = selectedCompany

            user.save(flush: true)

        } else {
            def companyUser = new CompanyAccount(
                    publicEmail: user.username,
                    company: Company.get(params.id),
                    user: user,
                    positionInCompany: "Administrator",
                    telephone: "800200300",
                    belongToAdmin: true
            )

            if (!companyUser.save(flush: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                return
            }
        }

        selectedAdvertisement.properties.datePublished = new Date()
        selectedAdvertisement.properties.willExpire = new Date() + 30


        if (!selectedAdvertisement.save(flush: true, failOnError: true)) {

            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "index", controller: "companyAccountJobOffers")
            return
        }
        flash.message = message(code: 'jobOffer.published.label')
        redirect(action: "index", controller: "adminJobOffer", params: [max: params.max, offset: params.offset])

    }

    def publishTop() {
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            flash.error = message(code: 'system.notLogged.error')
            redirect(controller: "logout")
            return
        }

        def jobOfferInstance = JobOffer.get(params.id)
        def companyInstance = jobOfferInstance.company

        if (user.company) {
            user.company = companyInstance
            user.save(flush: true)

        } else {
            def companyUser = new CompanyAccount(
                    publicEmail: user.username,
                    company: Company.get(params.id),
                    user: user,
                    positionInCompany: "Administrator",
                    telephone: "800200300",
                    belongToAdmin: true
            )

            if (!companyUser.save(flush: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                return
            }
        }


        if (!CompanyServicesService.isActivated("top-service", companyInstance)) {
            flash.error = "Služba není aktivní"
            redirect(controller: "companyAccountServices", action: "activation", id: "top-service")
            return
        }

        def servicesInstance = Services.findByUniqueName("top")

        if (companyInstance.credits < servicesInstance.creditPrice) {
            flash.error = "Nedostatek kreditů"
            redirect(action: "index", controller: "companyAccountJobOffers")

            return
        }

        jobOfferInstance.properties.datePublished = new Date()
        jobOfferInstance.properties.willExpire = new Date() + servicesInstance.defaultExpirationTime
        jobOfferInstance.topPos = true

        Purchase purchase = new Purchase(

                company: companyInstance,

                serviceName: "purchaseServices.serviceBought.label",
                user: user.firstName + " " + user.lastName + " (" + user.username + ")",
                isAdmin: false,
                datePurchased: new Date(),
                serviceNameParams: servicesInstance.name,
                credits: -servicesInstance.creditPrice,
                details: jobOfferInstance?.positionName,
                comment: "",
                detailsParams: ""

        )

        companyInstance.credits -= servicesInstance.creditPrice

        if (!companyInstance.addToPurchases(purchase).save(flush: true, failOnError: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "index")
            return
        }

        if (!jobOfferInstance.save(flush: true, failOnError: true)) {

            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "index", controller: "companyAccountJobOffers")
            return
        }
        redirect(action: "index", controller: "companyAccountJobOffers", params: [max: params.max, offset: params.offset])
    }

    def publicTo() {

        User user = springSecurityService.getCurrentUser() as User

        def jobOfferInstance = JobOffer.get(params.id)
        def companyInstance = jobOfferInstance.company

        if (user.company) {
            user.company = companyInstance
            user.save(flush: true)
        } else {
            def companyUser = new CompanyAccount(
                    publicEmail: user.username,
                    company: Company.get(params.id),
                    user: user,
                    positionInCompany: "Administrator",
                    telephone: "800200300",
                    belongToAdmin: true
            )

            if (!companyUser.save(flush: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                return
            }
        }

        if (!CompanyServicesService.isActivated("remote-post", companyInstance)) {
            flash.error = "Služba není aktivní"
            redirect(controller: "companyAccountServices", action: "activation", id: "remote-post")
            return
        }

        render(view: 'chooseCountries', model: [jobOfferInstance: jobOfferInstance])
    }

}

/**
 * Command class for binding filter data from controller
 */
class JobOfferAdminFilterCommand {

    String companyName
    String companyID

    String fulltextSearch

    Integer max
    Integer offset

    String sort
    String order

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << ["companyName"   : companyName,
                         "companyID"     : companyID,
                         "fulltextSearch": fulltextSearch,
                         "max"           : max,

                         "offset"        : offset,
                         "sort"          : sort,
                         "order"         : order
        ]

        return filterParams
    }
}