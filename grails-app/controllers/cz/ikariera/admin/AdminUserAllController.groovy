package cz.ikariera.admin

import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import grails.converters.JSON

class AdminUserAllController {

    def springSecurityService
    def internalEmailService

    /**
     * Filer method returns a list of filtered company users
     */

    def index() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)
        def sort = params.sort ? params.sort : "accountCreated"
        def order = params.order ? params.order : "desc"

        def userInstanceList = User.createCriteria().list(max: max, offset: offset, sort: sort, order: order) {

            if (params.firstName)
                ilike("firstName", '%' + params.firstName + '%')

            if (params.lastName)
                ilike("lastName", '%' + params.lastName + '%')

            if (params.username)
                ilike("username", '%' + params.username + '%')

        }

        def userInstanceListTotal = userInstanceList.totalCount

        render view: 'index',
                model: [
                        userInstanceList     : userInstanceList,
                        userInstanceListTotal: userInstanceListTotal,


                ],
                params: params
    }


    def resetPassword() {

        def user = User.get(params.id)

        /*random uuid string for string */
        user.token = java.util.UUID.randomUUID().toString()

        /* Token expiration */
        Date now = new Date()

        use(groovy.time.TimeCategory) {
            user.tokenExpiration = now + 24.hours
        }

        user.save(flush: true, failOnError: true)

        def emailAddress = user?.username?.toString()?.toLowerCase()

        try {
            internalEmailService.sendSimpleEmailMessage(
                    emailAddress,

                    message(code: 'mail.password.reset') + "" + (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.replyAddress,
                    grailsApplication.config.internalEmailService.subjectPrefix + "" + message(code: 'account.password.reset.subject'),


                    g.render(template: "/email/passwordReset", model: [user: user])
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            return false
        }


        flash.message = message(code: 'user.resetMail.sent', args: [user?.username])

        redirect(url: params.returnPath)
    }

    /**
     * Will enable user
     */

    def enableUser() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userInstance = User.get(userId)

        if (!userInstance) {
            render([text: "userId: false"]) as JSON
            return
        }

        userInstance.enabled = true;
        userInstance.save(flush: true)

        def result = [userId: userId, data: "E"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will enable user
     */

    def disableUser() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userInstance = User.get(userId)

        if (!userInstance) {
            render([text: "userId: false"]) as JSON
            return
        }

        userInstance.enabled = false;
        userInstance.save(flush: true)


        def result = [userId: userId, data: "N"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will ban user
     */

    def banUser() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userInstance = User.get(userId)

        if (!userInstance) {
            render([text: "userId: false"]) as JSON
            return
        }

        userInstance.accountLocked = true;
        userInstance.save(flush: true)


        def result = [userId: userId, data: "Ban"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will ban user
     */

    def unBanUser() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "companyId: false"]) as JSON
            return
        }
        def userInstance = User.get(userId)

        if (!userInstance) {
            render([text: "userId: false"]) as JSON
            return
        }

        userInstance.accountLocked = false;
        userInstance.save(flush: true)

        def result = [userId: userId, data: "-"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will unexpire Password of user
     */

    def unExpirPass() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "companyId: false"]) as JSON
            return
        }
        def userInstance = User.get(userId)

        if (!userInstance) {
            render([text: "userId: false"]) as JSON
            return
        }

        userInstance.accountExpired = false;
        userInstance.save(flush: true)

        def result = [userId: userId, data: "-"]
        def converter = result as JSON;
        render converter.toString();
    }

    /**
     * Will expor user password
     */

    def expirPass() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "companyId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "companyId: false"]) as JSON
            return
        }
        def userInstance = User.get(userId)

        if (!userInstance) {
            render([text: "userId: false"]) as JSON
            return
        }

        userInstance.accountExpired = true;
        userInstance.save(flush: true)

        def result = [userId: userId, data: "EXPIR"]
        def converter = result as JSON;
        render converter.toString();
    }

    def delete(User userInstance) {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }


        if (!userInstance) {
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(controller: "adminUserAll", action: "index")
            return
        }
        UserRole.removeAll(userInstance)
        userInstance.delete(flush: true)

        flash.message = message(code: 'student.account.delete')
        redirect(controller: "adminUserAll", action: "index")

    }


    def save() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def anyUser = User.get(params.id)

        anyUser.firstName = params?.firstName
        anyUser.lastName = params?.lastName
        anyUser.username = params?.username

        if (!anyUser.validate()) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserAll/editUser", model: [userInstance: anyUser])
            return
        }

        if (!anyUser.save(flush: true, failOnError: true)) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserAll/editUser", model: [userInstance: anyUser])

            return
        }

        flash.message = message(code: 'student.account.update')
        redirect(controller: "adminUserAll", action: "edit", params: [id: params.id])
    }


    def edit() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User editedUser = User.get(params.id)
        if (!editedUser) {
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(action: "adminsList")
            return
        }


        render(view: "/adminUserAll/editUser", model: [userInstance: editedUser])
    }

    def changePass() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User editedUser = User.get(params.id)
        if (!editedUser) {
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(action: "index")
            return
        }

        render(view: "changePass", model: [userInstance: editedUser])
    }

    def savePass() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User userInstance = User.get(params.id)
        if (!userInstance) {
            flash.error = message(code: 'system.userDoesntExist.error')
            redirect(action: "index")
            return
        }

        if (params.passwordConfirm != params.password) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.wrongControllPassword.error')
            render(view: "/adminUserAll/changePass", model: [adminInstance: userInstance])
            return
        }

        userInstance.password = springSecurityService.encodePassword(params.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password

        if (!userInstance.save(flush: true)) {
            render(view: "/adminUserAll/changePass", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'passwordChange.newPassword.message')
        redirect(controller: "adminUserStudent", action: "index")
    }

}
