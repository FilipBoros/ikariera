package cz.ikariera.security

import cz.ikariera.security.User
import groovy.time.TimeCategory

class PasswordController {

    def springSecurityService
    def internalEmailService


    def index() {
        render(view: "index")
    }


    def sendToken() {

        if (!params.email ==~ /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/) {

            flash.error = message(code: "password.wrong.user")
            render(view: "index")
            return
        }
        def username = params.email?.toString()?.toLowerCase()

        def user = User.findWhere(username: username)

        if (!user) {
            flash.error = message(code: "password.wrong.user")
            render(view: "index")
            return
        }

        Date now = new Date()
        use(TimeCategory) {
            user.tokenExpiration = now + 2.hour
        }
        user.token = UUID.randomUUID()

        user.save(failOnError: true, flush: true)

        try {
            internalEmailService.sendSimpleEmailMessage(
                    username,


                    message(code: 'mail.password.reset') + "" +  (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.replyAddress,
                    grailsApplication.config.internalEmailService.subjectPrefix + ""  + message(code: 'account.password.reset.subject'),


                    g.render(template: "/email/passwordReset", model: [user: user])
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            return false
        }

        flash.message = message(code: 'requirement.to.reset.password.sent')
        render(view: "sendToken")


    }


    def resetPassword() {

        if (!params.token) {
            flash.error = message(code: 'default.not.found.message')
            render(view: "index")
            return
        }
        def userInstance = User.findWhere(token: params.token)

        if (!userInstance) {
            flash.error = message(code: 'default.not.found.message')
            render(view: "index")
            return
        }

        Date now = new Date();
        if (userInstance.tokenExpiration < now) {
            flash.message = "Požadavek na reset hesla již by odeslán. Zkontrolujte si prosím schránku."
            redirect(action: "sendResetToken")
            return

        }


        render(view: "resetPassword", model: [userInstance: userInstance])

    }

    def save() {


        def token = params.token

        if (!token) {
            flash.error = message(code: 'default.not.found.message')
            render(view: "index")
            return

        }

        def userInstance = User.findWhere(token: token)

        if (!userInstance) {
            flash.error = message(code: 'default.not.found.message')
            render(view: "index")
            return
        }


        Date now = new Date();
        if (userInstance.tokenExpiration < now) {
            flash.error = "Token již expiroval."
            render(view: "index")
            return

        }

        if (params.passwordConfirm != params.password) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.emptyPassword.error')
            render(view: "resetPassword", model: [userInstance: userInstance])
            return
        }

        userInstance.password = springSecurityService.encodePassword(params.passwordConfirm, userInstance.username)

        userInstance.accountExpired = false
        userInstance.passwordExpired = false
        userInstance.enabled = true

        if (!userInstance.validate()) {

            render(view: "resetPassword", model: [userInstance: userInstance])
            return
        }



        userInstance.save(flush: true, failOnError: true)

        flash.message = message(code: 'password.change.ready.to.log')
        redirect(controller: "index")
    }


}
