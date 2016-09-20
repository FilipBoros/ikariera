package cz.ikariera.admin

import cz.ikariera.company.Company
import cz.ikariera.company.Purchase
import cz.ikariera.security.User

class AdminCreditsController {

    def springSecurityService


    def index() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
            return
        }
        params.sort = params.sort ? params.sort : 'datePurchased'
        params.order = params.order ? params.order : 'desc'
        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        List purchaseList = Purchase.createCriteria().list(params) {
            //order('datePurchased', 'desc')
        }
        def listSize = purchaseList?.totalCount

        render(view: "index",
                model: [
                        purchaseList     : purchaseList,
                        purchaseListCount: listSize,
                        displayedResults : listSize < params.max + params.offset ? listSize - params.offset : params.max
                ],
                params: params)
    }

    def addRemoveCredits() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def companyInstance = Company.get(params.id)

        if (!companyInstance) {

            redirect(action: "index", controller: 'adminCompany')
            return
        }

        render(view: '/adminCredits/addRemoveCredits', model: [companyInstance: companyInstance])
    }


    def updateCredits() {
        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        Company.withTransaction { status ->
            def companyInstance = Company.get(params.id)

            if (!companyInstance) {
                status.setRollbackOnly()
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'constantsModerator.label'), params.id])
                redirect(action: "list")
                return
            }

            String credits = "0"
            if (params.addRemoveCreds != "") {
                credits = params["addRemoveCreds"].toString()
                companyInstance.credits += credits.toBigDecimal()

            }

            Boolean err = false
            if (!companyInstance.validate(["addRemoveCreds"])) {
                err = true
            }
            if (params.addRemoveCreds == "") {
                companyInstance.errors.rejectValue('addRemoveCreds', 'admin.addRemoveCredits.addRemoveCreds.error')
                err = true
            }

            if (companyInstance.credits < 0) {
                companyInstance.errors.rejectValue('credits', 'zadana nizka hodnota')
                err = true
            }

            if (err) {
                if (credits) {
                    companyInstance.credits -= credits.toBigDecimal()
                }
                status.setRollbackOnly()
                render(view: "/adminCredits/addRemoveCredits", model: [companyInstance: companyInstance])
                return
            }

            if (!companyInstance.save(flush: true, failOnError: true)) {
                flash.error = message(code: 'system.unspecifiedError.error')
                status.setRollbackOnly()
                render(view: "/adminCredits/addRemoveCredits", model: [companyInstance: companyInstance])
                return
            }

            flash.message = message(code: 'admin.credits.actualized.message')

            // this IS ugly, but it works get filter params from session and set it to params
            CompanyAdminFilterCommand f = new CompanyAdminFilterCommand()
            bindData(f, session["filterParams"])
            def lst = f.mainJobCategories.collect { it.id }
            def filter = f.getFilterParams()
            filter.mainJobCategories = lst

            redirect(controller: "adminCompany", action: 'index', params: filter)
        }
    }

}
