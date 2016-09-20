package ikariera.company

import cz.ikariera.admin.PublishService
import cz.ikariera.admin.RemoteServer
import cz.ikariera.company.*
import cz.ikariera.security.User
import cz.ikariera.service.CompanyServicesService
import grails.plugins.rest.client.RestBuilder

class CompanyAccountJobOffersController {

    def springSecurityService


    def show(JobOffer jobOfferInstance) {

        if (!jobOfferInstance?.datePublished) {
            flash.message = message(code: 'jobOffer.preview.label')
        }
        redirect(controller: "companyAccountJobOffers", action: "detailNotPublished", id: jobOfferInstance?.id)

    }

    def detailNotPublished(JobOffer jobOfferInstance) {

        if (!jobOfferInstance) {
            flash.error = message(code: "default.not.found.message", args: ['Job offer', params.id])
            render(view: "/notFound", model: [:])
            return
        }

        render(view: "detail", model: [jobOfferInstance: jobOfferInstance])
    }

    def expire(JobOffer jobOfferInstance) {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        if (!checkCompanyPermission(jobOfferInstance)) {
            render(view: "/notFound", model: [:])
            return
        }
        jobOfferInstance.datePublished = null
        jobOfferInstance.willExpire = null
        jobOfferInstance.topPos = false


        jobOfferInstance.save(flush: true, failOnError: true)

        redirect(controller: "companyAccountJobOffers")
    }


    def index() {

        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        Company company = user.company

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        List jobOfferInstanceList = JobOffer.createCriteria().list(params) {
            //order('dateCreated', 'desc')
            //order('datePublished', 'desc')
            order('willExpire', 'desc')
            eq("company", company)

        }
        def jobOfferInstanceListTotal = jobOfferInstanceList?.totalCount

        if (!user.company?.active) {

            flash.error = message(code: 'company.notActive.message')
        }


        render(view: "index",
                model: [
                        jobOfferInstanceList     : jobOfferInstanceList,
                        jobOfferInstanceListTotal: jobOfferInstanceListTotal,

                        companyInstance          : user?.company,

                ],
                params: params)


    }


    def edit(JobOffer jobOfferInstance) {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        if (!checkCompanyPermission(jobOfferInstance)) {
            render(view: "/notFound", model: [:])
            return
        }
        def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }

        render(view: "edit", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])
    }


    def update() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        JobOffer jobOfferInstance = JobOffer.get(params.id)

        if (!checkCompanyPermission(jobOfferInstance)) {
            render(view: "/notFound", model: [:])
            return
        }
        bindData(jobOfferInstance, params, [exclude: ['company']])

        jobOfferInstance.properties.willExpire = null

        jobOfferInstance.validate()

        if (jobOfferInstance.hasErrors()) {
            def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }
            render(view: "edit", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])
            return
        }

        if (!jobOfferInstance.save(failOnError: true, flush: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }
            render(view: "/companyAccountJobOffers/edit", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])

            return
        }



        if (params.submit == "top") {

            publishTop(jobOfferInstance)


        } else if (params.submit == "publish") {

            publish(jobOfferInstance)
        } else {


            redirect(controller: "companyAccountJobOffers")
        }
    }


    def create() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }

        render(view: "create", model: [jobOfferInstance: new JobOffer(), contactList: listOfContacts])
    }

    def save(JobOffer jobOfferInstance) {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        jobOfferInstance.company = user.company

        jobOfferInstance.validate()

        if (jobOfferInstance.jobCategories.equals(null)) {
            flash.error = message(code: 'company.choose.one.category')
            def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }
            render(view: "create", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])
            return
        }
        if (jobOfferInstance.jobCategories.size()>5) {
            flash.error = message(code: 'company.max.workingCategories')
            def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }
            render(view: "create", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])
            return
        }

        if (jobOfferInstance.hasErrors()) {

            def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }
            render(view: "create", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])
            return
        }



        if (!jobOfferInstance.save(flush: true, failOnError: true)) {
            def listOfContacts = ContactDetails.createCriteria().list { eq('company', user.company) }
            render(view: "create", model: [jobOfferInstance: jobOfferInstance, contactList: listOfContacts])
            return
        }
        flash.message = message(code: 'company.jobOffer.saved')

        if (params.submit == "top") {
            publishTop(jobOfferInstance)
        } else if (params.submit == "publish") {
            publish(jobOfferInstance)
        } else {
            redirect(controller: "companyAccountJobOffers")
        }
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
                redirect(action: "index", controller: "companyAccountJobOffers")

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

            companyInstance.credits -= servicesInstance.creditPrice

            if (!companyInstance.addToPurchases(purchase).save(flush: true, failOnError: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                redirect(action: "index")
                return
            }
        }
        flash.message = message(code: 'company.jobOffer.not.sent')
        redirect(action: "index", controller: "companyAccountJobOffers")
    }

    def publicTo(JobOffer jobOfferInstance) {

        User user = springSecurityService.getCurrentUser() as User

        Company company = user.company

        if (!CompanyServicesService.isActivated("remote-post", company)) {
            flash.error = message(code: 'service.isNot.active')
            redirect(controller: "companyAccountServices", action: "activation", id: "remote-post")
            return
        }

        render(view: 'chooseCountries', model: [jobOfferInstance: jobOfferInstance])
    }

    def publishTop(JobOffer jobOfferInstance) {
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            flash.error = message(code: 'system.notLogged.error')
            redirect(controller: "logout")
            return
        }

        Company companyInstance = user.company

        if (!checkCompanyPermission(jobOfferInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        if (!CompanyServicesService.isActivated("top-service", companyInstance)) {
            flash.error = message(code: 'service.isNot.active')
            redirect(controller: "companyAccountServices", action: "activation", id: "top-service")
            return
        }

        def servicesInstance = Services.findByUniqueName("top")

        if (companyInstance.credits < servicesInstance.creditPrice) {
            flash.error = message(code: 'company.notEnoughCredits')
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

    def publish(JobOffer jobOfferInstance) {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            flash.error = message(code: 'system.notLogged.error')
            redirect(controller: "logout")
            return
        }

        if (!checkCompanyPermission(jobOfferInstance)) {
            render(view: "/notFound", model: [:])
            return
        }
        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)
        def publishServiceList = PublishService.createCriteria().list(max: max, offset: offset) {

        }

        def company = Company.get(jobOfferInstance.company.id)

        if(company.servicesExpire == null){
            company.servicesExpire = new ServicesExpire()
            if (!company.save(flush: true, failOnError: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                redirect(action: "index", controller: "companyAccountJobOffers")
                return
            }

        }

        def service = company?.servicesExpire //jobOfferInstance?.company?.servicesExpire

        //first check if any rule is in database

        //if(false){


        if (publishServiceList.size() > 0) {
            //publishServiceActivate lastPublished ==null

            if (service?.lastPublished.equals(null)) {
                service?.lastPublished = new Date()
            }
            //no service activated or it expires
            if (service?.publishServiceActivate.equals(null) || service?.publishServiceActivate + service.periodOfPublishing < new Date()) {
                def theLowestServiceRule = publishServiceList.get(0)

                if (service?.lastPublished + theLowestServiceRule?.numberOfDays >= new Date()) {//+30
                    //else if was there
                    if (service?.counterJobOfferPublished < theLowestServiceRule?.numberOfPublishedJobOffers) {
                        jobOfferInstance.company.servicesExpire.counterJobOfferPublished++
                        flash.message = message(code: 'company.jobOffer.published')
                    } else {
                        flash.message = message(code: 'company.jobOffer.cant.publish.inThisPeriod')
                        redirect(action: "index", controller: "companyAccountJobOffers", params: [max: params.max, offset: params.offset])
                        return
                    }
                } else {
                    //reset counter
                    jobOfferInstance.company.servicesExpire.counterJobOfferPublished = 1
                    jobOfferInstance.company.servicesExpire.lastPublished = new Date()
                    flash.message = message(code: 'company.jobOffer.published')
                }
            //some service activated
            } else {
                def theBoughtServiceRule = null
                def theActualServiceRule = null

                for (int i = 0; i < publishServiceList.size(); i++) {
                    theActualServiceRule = publishServiceList.get(i)
                    //check which service user bought and if it is
                    if(service.periodOfPublishing==theActualServiceRule.numberOfDays
                            && service.amountOfJobOffers == theActualServiceRule.numberOfPublishedJobOffers){
                        theBoughtServiceRule = publishServiceList.get(i)
                        //get rule 1 above if it exists and check if company didnt break rule
                        if(i+1<publishServiceList.size()){
                            theActualServiceRule = publishServiceList.get(i+1)
                        }else{
                            //theActualServiceRule = null
                        }
                        break;
                    }

                }

                if(theActualServiceRule.equals(theBoughtServiceRule)){
                    jobOfferInstance.company.servicesExpire.counterJobOfferPublished++
                    flash.message = message(code: 'company.jobOffer.published')
                }else if(service?.counterJobOfferPublished < theActualServiceRule?.numberOfPublishedJobOffers){
                    if (service?.publishServiceActivate + service.periodOfPublishing >= new Date()) {
                        jobOfferInstance.company.servicesExpire.counterJobOfferPublished++
                        flash.message = message(code: 'company.jobOffer.published')
                    }else{
                        flash.message = message(code: 'jobOffer.noMore.publishing')
                        redirect(action: "index", controller: "companyAccountJobOffers", params: [max: params.max, offset: params.offset])
                        return
                    }
                }else{
                    flash.message = message(code: 'jobOffer.noMore.publishing')
                    redirect(action: "index", controller: "companyAccountJobOffers", params: [max: params.max, offset: params.offset])
                    return
                }

            }

        }else{
            flash.message = message(code: 'company.jobOffer.published')
        }
        jobOfferInstance.properties.datePublished = new Date()
        jobOfferInstance.properties.willExpire = new Date() + 30

        if (!jobOfferInstance.save(flush: true, failOnError: true)) {

            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "index", controller: "companyAccountJobOffers")
            return
        }

        redirect(action: "index", controller: "companyAccountJobOffers", params: [max: params.max, offset: params.offset])

    }

    def delete(JobOffer jobOfferInstance) {

        User user = springSecurityService.getCurrentUser() as User

        if (!jobOfferInstance) {
            flash.error = message(code: "system.articleDoesntExist.error")
            redirect(controller: "companyAccountJobOffers")
            return
        }

        if (!checkCompanyPermission(jobOfferInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        if (jobOfferInstance.company.id != user.company.id) {
            flash.error = "System error"
            redirect(controller: "companyAccountJobOffers")
            return
        }

        jobOfferInstance.delete(flush: true)

        flash.message = message(code: 'jobOffer.deleted.message')
        redirect(controller: "companyAccountJobOffers")
    }

    private def checkCompanyPermission(JobOffer jobOfferInstance) {
        User user = springSecurityService.getCurrentUser() as User

        def company = user.company
        if (jobOfferInstance.company != company)
            return false

        return true
    }

}



