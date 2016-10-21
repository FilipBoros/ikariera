package cz.ikariera.company

import cz.ikariera.company.Company
import cz.ikariera.security.User

class CompanyAccountProfileController {

    def springSecurityService

    def index() {
        def user = springSecurityService.getCurrentUser() as User


        if (!user) {
            redirect(controller: "login")
            return
        }

        def companyInstance = user.company


        render(view: "index" ,model:[companyInstance: companyInstance])
    }


    def update(Company companyInstance) {

        def user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
            return
        }
        if (!checkCompanyPermission(companyInstance)) {
            render(view: "/notFound", model: [:], status: 404)
            return
        }
        companyInstance.validate()

        if (companyInstance.hasErrors()) {

            render(view: "index" ,  model: [companyInstance : companyInstance])
            return
        }


        if (!companyInstance.save(flush: true, failOnError: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            render(view: "index", model: [companyInstance : companyInstance])
            return
        }

        flash.message = message(code: 'system.updated2.message')
        redirect(action: "index")
    }
    private def checkCompanyPermission(Company companyInstance) {
        User user = springSecurityService.getCurrentUser() as User

        def company = user.company
        if (companyInstance != company)
            return false

        return true
    }
}
