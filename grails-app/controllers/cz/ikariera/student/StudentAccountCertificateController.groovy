package cz.ikariera.student

import cz.ikariera.security.User
import cz.ikariera.student.StudentAccount


class StudentAccountCertificateController {


    def springSecurityService

    def index() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        render(view: "/studentAccountCertificate/index", model: [studentInstance: user.studentAccount])
    }


    def save() {

        User userInstance = springSecurityService.getCurrentUser() as User
        if (!userInstance) {
            redirect(controller: "login")
        }

        StudentAccount studentInstance = userInstance.studentAccount

        studentInstance?.certificates?.clear()

        bindData(studentInstance, params)

        studentInstance.validate()

        if (studentInstance.hasErrors()) {

            //println(studentInstance.errors)

            render(view: "/studentAccountCertificate/index", model: [userInstance: userInstance])
            return
        }


        if (!studentInstance.save(failOnError: true, flush: true)) {

            render(view: "/studentAccountCertificate/index", model: [userInstance: userInstance])
            return
        }
        flash.message = message(code: 'student.account.update')
        redirect(action: "index")

    }


}
