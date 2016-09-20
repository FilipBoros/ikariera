package ikariera.student

import cz.ikariera.security.User

class StudentAccountDangerAreaController {


    ////////////////////////////////
    //
    //TODO
    //
    /////////////////////////////

    def springSecurityService

    def index() {


        redirect(controller: "studentAccount")
    }



    def userSelfDeleteConfirm() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        [userInstance: user]

        render(view: "/studentAccountDangerArea/userSelfDeleteConfirm")
    }


    def userSelfDeletePerform() {
        User editedUser = springSecurityService.getCurrentUser()
        if (!editedUser) {
            redirect(controller: "logout")
            return
        }



        if (editedUser.firstName == "")
            editedUser.firstName = editedUser.id
        if (editedUser.lastName == "")
            editedUser.lastName = editedUser.id
        editedUser.username = editedUser.id.toString() + "." + editedUser.username.toString()

        editedUser.deleted = true

        editedUser.save(flush: true)

        flash.message = message(code: 'student.account.delete')
        redirect(controller: "logout",)

    }
}
