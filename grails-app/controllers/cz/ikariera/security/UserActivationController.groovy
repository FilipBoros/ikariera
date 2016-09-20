package ikariera.security

import cz.ikariera.security.User

class UserActivationController {

    def springSecurityService

    def enableCompanyUser() {


        if (!params.token) {
            // flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label', default: 'CompanyEmails'), params.id])
            redirect(controller: "index")
            return

        }


        Date now = new Date()

        def token = params.token
        def userInstance = User.findByToken(token.toString())



        if (!userInstance) {

            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label', default: 'CompanyEmails'), params.id])
            redirect(controller: "index")
            return
        }

        if (userInstance.tokenExpiration < now) {

            render(view: "accountTokenExpired", model: [token: token])
            return

        }


        render(view: "enableCompanyUser", model: [userInstance: userInstance])

    }

    def saveCompanyUser() {


        if (!params.token) {
            // flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label', default: 'CompanyEmails'), params.id])
            redirect(controller: "index")
            return

        }
        Date now = new Date()

        def token = params.token
        def userInstance = User.findByToken(token.toString())


        if (userInstance.tokenExpiration < now) {

            render(view: "accountTokenExpired", model: [token: token])
            return

        }

        userInstance.enabled = true



        if (params.passwordConfirm != params.password) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.emptyPassword.error')
            render(view: "enableCompanyUser", model: [userInstance: userInstance])
            return
        }




        userInstance.password = params.password



        userInstance.validate()

        if (userInstance.hasErrors()) {

            render(view: "enableCompanyUser", model: [userInstance: userInstance])
            return
        }


        userInstance.password = springSecurityService.encodePassword(userInstance.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password


        if (!userInstance.save(failOnError: true, flush: true)) {

            render(view: "enableCompanyUser", model: [userInstance: userInstance])
            return
        }

        redirect(action: "accountActivated")

    }



    def enableUser() {


        if (!params.token) {
            // flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label', default: 'CompanyEmails'), params.id])
            redirect(controller: "index")
            return

        }

        Date now = new Date()


        def token = params.token
        def user = User.findByToken(token.toString())

        if (!user) {

            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label', default: 'CompanyEmails'), params.token])
            redirect(controller: "index")
            return
        }

        if (user.tokenExpiration < now) {

            render(view: "accountTokenExpired", model: [token: token])
            return

        }

        user.token = null

        user.enabled = true


        user.validate()


        if (user.hasErrors()) {

            render(view: "accountActivationError", model: [token: token])
            return
        }




        if (!user.save(flush: true)) {
            render(view: "accountActivationError", params: [token: token])
            return
        }

        redirect(action: "accountActivated")
        //render(view: "/userActivation/accountActivated")
    }

    def accountTokenExpired() {

        flash.message = message(code: 'user.token.expired')

        render(view: "accountTokenExpired")
        // render(view: "/index")

    }

    def accountActivated() {
        //render(view: "/cz.ikariera.security/userActivation/accountActivated")
        //flash.error = message(code: 'user.activated')
        //flash.message = message(code: 'user.activated')
        //render(view: "/index")
        render(view: "accountActivated")
        // return

    }


}
