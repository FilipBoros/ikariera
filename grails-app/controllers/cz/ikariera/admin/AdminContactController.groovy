package ikariera.admin

import cz.ikariera.admin.Contact

class AdminContactController {

    def springSecurityService


    def index() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        redirect(controler: "AdminContactController", action: "edit")
    }


    def edit() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def contactInstance = Contact.findByContactIdentify("IAESTE")
        render(view: "/adminContact/edit", model: [contactInstance: contactInstance])
    }


    def save() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def contactInstance = Contact.findByContactIdentify("IAESTE") ?: new Contact()
        contactInstance.properties = params

        if (!contactInstance.validate()) {
            render(view: "/adminContact/edit", model: [contactInstance: contactInstance])
            return
        }

        if (!contactInstance.save(flush: true, failOnError: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(controler: "AdminContactController", action: "edit")
            return
        }

        flash.message = message(code: 'system.updated2.message')
        redirect(controler: "AdminContactController", action: "edit")
    }
}
