package cz.ikariera.company

import cz.ikariera.security.User

class CompanyAccountController {

    def springSecurityService


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def index() {

        log.error('sddddddddddddddddddddddddddd')

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def companyInstance = user.company

        log.error('controller: CompanyAccountController action: index')

        render view: 'index', model: [companyInstance: companyInstance]
    }


}
