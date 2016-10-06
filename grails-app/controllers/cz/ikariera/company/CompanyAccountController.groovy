package cz.ikariera.company

import cz.ikariera.security.User

class CompanyAccountController {

    def springSecurityService


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def index() {


        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def companyInstance = user.company
        [companyInstance: companyInstance]

    }


}
