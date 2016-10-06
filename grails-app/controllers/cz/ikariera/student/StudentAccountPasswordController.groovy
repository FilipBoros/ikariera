package cz.ikariera.student

import cz.ikariera.security.User

class StudentAccountPasswordController {

    def springSecurityService


    def index() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }


    }

    /*
    def save () {

        def passwordNew = params["passwordNew"]
        def passwordConfirm = params["passwordConfirm"]
        def token = params["token"]

        if (!token){
            redirect(action: "sendToken" )
            return

        }

        def userInstance = User.findWhere(token: token)

        if (!userInstance) {
            // flash.message = message(code: 'default.not.found.message', args: params.id])
            redirect(action: "sendResetToken")
            return
        }

        Date now = new Date();
        if (userInstance.tokenExpiration < now){
            // flash.message = message(code: 'default.not.found.message', args: params.id])
            redirect(action: "sendResetToken")
            return

        }


        if (!(passwordNew && passwordConfirm && passwordNew== passwordConfirm )){
            // flash.message = message(code: 'default.not.found.message', args: params.id])
            redirect(action: "resetPassword", model: [token: token] )
            return

        }



        userInstance.password =  springSecurityService.encodePassword(passwordNew, userInstance.username)


        //userInstance.passwordNew = "DummyPassword"
        //userInstance.passwordConfirm = "DummyPassword"

        userInstance.accountExpired=false
        userInstance.passwordExpired=false
        userInstance.enabled=true



        userInstance.save(flush: true, failOnError: true)

        flash.message = message(code: 'password.change.ready.to.log')
        redirect (controller: "index")
    }
            */


    def save() {

        User userInstance = springSecurityService.getCurrentUser()
        if (!userInstance) {
            redirect(controller: "login")
        }




        if (params.passwordConfirm != params.password) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.emptyPassword.error')
            render(view: "index", model: [userInstance: userInstance])
            return
        }



        if (userInstance.password != springSecurityService.encodePassword(params.passwordOld, userInstance.username)) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.emptyPassword.error')
            render(view: "index", model: [userInstance: userInstance])
            return
        }

        userInstance.password = springSecurityService.encodePassword(params.password, userInstance.username)


        if (!userInstance.validate()) {

            render(view: "index", model: [userInstance: userInstance])
            return
        }


        userInstance.save(flush: true, failOnError: true)

        flash.message = "${message(code: "passwordChange.newPassword.message")}"
        redirect(url: params.returnPath)
    }
}

