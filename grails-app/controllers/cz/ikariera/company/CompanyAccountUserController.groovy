package cz.ikariera.company

import cz.ikariera.security.RandomGenerationService
import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import groovy.time.TimeCategory

/**
 * CompanyAccountUserController provides methods to create/view/modify/delete company users, editing company profile and a
 * method to view bought and available services.
 * It uses springSecurityService and mailService
 */

class CompanyAccountUserController {

    def springSecurityService
    def internalEmailService

    /**
     Renders page with user company users list.
     */

    def index() {
        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def company = user.company

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 20, 100)



        List userInstanceList = User.createCriteria().list(params) {
            order('lastName', 'asc')
            eq('company', company)
            eq('adminAccount', false)

        }


        def userInstanceListTotal = userInstanceList?.totalCount



        render(view: "index", model: [
                userInstanceList: userInstanceList,
                userInstanceListTotal: userInstanceListTotal])

    }

    def changePass(){
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

    def savePass(){
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
            render(view: "/companyAccountUser/changePass", model: [adminInstance: userInstance])
            return
        }

        userInstance.password = springSecurityService.encodePassword(params.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password

        if (!userInstance.save(flush: true)) {
            render(view: "/companyAccountUser/changePass", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'passwordChange.newPassword.message')
        redirect(controller: "companyAccountUser", action: "index")
    }


    def edit() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        User userInstance = User.findById(params.id)
        render(view: "edit", model: [userInstance: userInstance])

    }

    /**
     Try to update existing Company User in tables User and CompanyAccount
     */

    def update() {
        def user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "logout")
            return
        }

        User userInstance = User.get(params.id)


        bindData(userInstance, params, [exclude: ['username', 'company']])


        userInstance.validate()


        if (userInstance.hasErrors()) {

            render(view: "edit", model: [userInstance: userInstance])
            return
        }



        if (!userInstance.save(flush: true, failOnError: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')

            render(view: "edit", model: [userInstance: userInstance])
            return
        }


        flash.message = message(code: 'system.updated.message', args: [userInstance.username])
        redirect(controller: "companyAccountUser", action: "index")
    }

    /***
     * Renders page with form to create user (entry values are entered in corresponding fields).
     * @return
     */
    def create() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }


        render(view: "create", model: [userInstance: new User(params)])
    }

    /**
     Try to add new Company User to tables User and CompanyAccount. If successful, email is sent to user
     address with activation token.
     */


    def save() {
        def user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "logout")
            return
        }


        User userInstance = new User(params)

        bindData(userInstance, params, [include: ['companyAccount',  'username']])



        userInstance.username = userInstance.username?.toString()?.toLowerCase()
        String generatedPassword = RandomGenerationService.generateRandomPassword(6)
        userInstance.password = springSecurityService.encodePassword(generatedPassword, userInstance.username)

        userInstance.token = RandomGenerationService.generateRandomToken()
        use(TimeCategory) {
            userInstance.tokenExpiration = new Date() + 7.days //  2.hours
        }



        userInstance.termOfUse = true

        userInstance.companyAccount.infoEmails = true

        userInstance.validate()


        if (userInstance.hasErrors()) {

            render(view: "create", model: [userInstance: userInstance])
            return
        }

        userInstance.company = user.company

        //   println(userInstance.companyAccount)


        if (!userInstance.save(flush: true, failOnError: true)) {
            userInstance.password = null
            render(view: "create", model: [userInstance: userInstance])
            return
        }



        UserRole.create(userInstance, Role.findByAuthority('ROLE_COMPANY'), true)




        sendEmail(userInstance)

        flash.message = message(code: "companyAccount.userSaved.message")
        redirect(controller: "companyAccountUser")
    }


    def resendActivation() {


        def user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "logout")
            return
        }

        User userInstance = User.get(params.id)

        userInstance.token = RandomGenerationService.generateRandomToken()
        use(TimeCategory) {
            userInstance.tokenExpiration = new Date() + 7.days //  2.hours
        }

        if (!userInstance.save(flush: true, failOnError: true)) {
            userInstance.password = null
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        sendEmail(userInstance)
        flash.message = message(code: "companyAccount.userSaved.message")
        redirect(controller: "companyAccountUser")

    }

    private void sendEmail(User userInstance) {


        try {
            internalEmailService.sendSimpleEmailMessage(
                    userInstance.username,


                    message(code: 'mail.from.label.registration') + "" +  (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.replyAddress,
                    grailsApplication.config.internalEmailService.subjectPrefix + "" + message(code: "mail.subject.company"),


                    g.render(template: "/email/companyRegistrationConfirmation", model: [userInstance: userInstance])
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e

        }


    }

/**
 Try to remove existing Company User from tables User and CompanyAccount
 */

    def delete() {
        def user = springSecurityService.getCurrentUser() as User
        //isLogged?
        if (!user) {
            redirect(controller: "logout")
            return
        }


        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(controller: "companyAccountUser", action: "index")
            return
        }

        if (userInstance.id == user.id) {
            flash.error = "${message(code: "system.userLoggedCantBeDeleted.error")}"
            redirect(controller: "companyAccountUser", action: "index")
            return
        }

        //deleted user doesn't belong to same company as logged user
        if (userInstance.company.id != user.company.id) {
            flash.error = "${message(code: "system.userCompanyIsWrong.error")}"
            redirect(controller: "companyAccountUser", action: "index")
            return
        }
/*
        def selfKill=false;
        if(userInstance.username.equals(user.username)){
            selfKill = true;
        }*/

        if (userInstance.delete(flush: true)) {


            flash.error = "${message(code: "system.unspecifiedError.error")}"
            redirect(controller: "companyAccountUser", action: "index")
            return
        }
/*
        if(selfKill){
            flash.message = "${message(code: "system.userDeleted.messge")}"
            redirect(controller: "logout", action: "index")
            return
        }*/

        flash.message = "${message(code: "system.userDeleted.messge")}"
        redirect(controller: "companyAccountUser", action: "index")
    }


}
