package cz.ikariera.company

import cz.ikariera.admin.PublishService
import cz.ikariera.company.Company
import cz.ikariera.security.User

class CompanyPublishServiceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService


    def index() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)
        def sort = params.sort ? params.sort : 'prize'
        def order = params.order ? params.order : 'asc'

        def heroImageList = PublishService.createCriteria().list(max: max, offset: offset, order: order, sort: sort ) {

        }


        //
        Company companyInstance = user?.company


       // render(view: "activation", model: [servicesInstance: servicesInstance, companyInstance: companyInstance])

        //



        def heroImageListTotal = PublishService.count

        render(view: "index", model: [
                heroImageList: heroImageList,
                listInstanceTotal: heroImageListTotal,
                companyInstance: companyInstance
                //  displayedResults: listSize < params.max + params.offset ? listSize - params.offset : params.max
        ])
    }


    def activate() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)




        //prepsat company service expire
        def publishService = PublishService.get(params.id)

        //check state of credits
        if(user.company.credits > publishService.prize){
            //activate
            user.company.servicesExpire.amountOfJobOffers = publishService.numberOfPublishedJobOffers
            user.company.servicesExpire.periodOfPublishing = publishService.numberOfDays
            user.company.servicesExpire.publishServiceActivate = new Date()
            user.company.credits = user.company.credits - publishService.prize
            if (!user.save(failOnError: true, flush: true)) {
                flash.message = "Something went wrong"
                redirect(action: "index")
                return
            }

            flash.message = "Publish service was activated"
        }else{
            flash.message = "Not enough credit"
        }


        redirect(action: "index")





/*
        def heroImageList = PublishService.createCriteria().list(max: max, offset: offset ) {

        }
        //
        Company companyInstance = user?.company

        String uniqueName = params.id as String

        //def expirationTime = getExpirationTime(uniqueName, companyInstance?.servicesExpire)

        def servicesInstance = Services.findByUniqueName(uniqueName)


        def heroImageListTotal = PublishService.count

        render(view: "index", model: [
                heroImageList: heroImageList,
                listInstanceTotal: heroImageListTotal,
                servicesInstance: servicesInstance,
                companyInstance: companyInstance
                //  displayedResults: listSize < params.max + params.offset ? listSize - params.offset : params.max
        ])*/
    }




}
