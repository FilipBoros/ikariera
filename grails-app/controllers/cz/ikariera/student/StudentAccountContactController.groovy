package cz.ikariera.student

import cz.ikariera.security.User

class StudentAccountContactController {

    def springSecurityService

    def index() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        render(view: "/studentAccountContact/contactDetails", model: [userInstance: user])
    }


    def save( ) {

        User userInstance = springSecurityService.getCurrentUser()
        if (!userInstance) {
            redirect(controller: "login")
        }



        bindData(userInstance, params, [include: ['studentAccount']])


        userInstance.validate()

        if (userInstance.hasErrors()) {

            //println("has Errors")

            render(view: "/studentAccountContact/contactDetails", model: [userInstance: userInstance])
            return
        }


        if (!userInstance.save(flush: true)) {

            render(view: "/studentAccountContact/contactDetails", model: [userInstance: userInstance])
            return
        }


        flash.message = message(code: 'student.account.update')
        redirect(controler: "studentAccountContact")


    }

}
