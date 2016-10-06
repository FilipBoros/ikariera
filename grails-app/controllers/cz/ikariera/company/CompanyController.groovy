package cz.ikariera.company

import cz.ikariera.company.Company
import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import groovy.time.TimeCategory

class CompanyController {


    def springSecurityService
    def mailService
    def internalEmailService
    def recaptchaService

    def index() {}


    def registration() {
        render(view: "/company/registration", model: [userInstance: new User(params), companyInstance: new Company(params)])
    }


    def registrationComplete() {
        render(view: "/company/emailSent")
    }

    def saveRegistration() {

        Company companyInstance = new Company(params)

        User userInstance = new User(params)

        bindData(userInstance, params, [include: ['companyAccount', 'password', 'passwordConfirm', 'username']])
        if (params.startupBox) {//the end date of startup
            use(TimeCategory) {
                companyInstance.startup = new Date()+730.days
            }

        }else{
            Calendar cal = Calendar.getInstance();
            cal.set(2000, Calendar.JANUARY, 1); //Year, month and day of month
            companyInstance.startup = cal.getTime()
        }
        userInstance.password = "dummy password" //dummy password
        userInstance.passwordConfirm = "dummy password" //dummy password


        companyInstance.validate()



        if (companyInstance.hasErrors()) {
            //def errors = companyInstance.errors
            recaptchaService.cleanUp(session)
            userInstance.password = null
            userInstance.passwordConfirm = null
            render(view: "registration", model: [userInstance: userInstance, companyInstance: companyInstance])
            return
        }





        userInstance.username = userInstance.username?.toString()?.toLowerCase()


        userInstance.validate()

        if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
            userInstance.errors.rejectValue('reCaptcha', 'system.captcha.warning')
        }

        if (userInstance.hasErrors()) {
            recaptchaService.cleanUp(session)
            userInstance.password = null
            userInstance.passwordConfirm = null
            render(view: "registration", model: [userInstance: userInstance, companyInstance: companyInstance])
            return
        }



        userInstance.token = java.util.UUID.randomUUID().toString()
        Date now = new Date()
        use(TimeCategory) {
            userInstance.tokenExpiration = now + 36.hours
        }
        userInstance.password = springSecurityService.encodePassword(userInstance.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password

        //companyInstance.users.add(userInstance)
        //userInstance.company=

        companyInstance.addToUsers(userInstance)

        if (!companyInstance.save(failOnError: true, flush: true)) {
            render(view: "/company/registration", model: [companyInstance: companyInstance, userInstance: userInstance])
            return
        }


        UserRole.create(userInstance, Role.findByAuthority('ROLE_COMPANY'), true)


        sendEmail(companyInstance, userInstance)

        sendNotificationEmail(companyInstance)

        redirect(action: "registrationComplete")

    }

    private void sendEmail(Company companyInstance, User userInstance) {


        try {
            internalEmailService.sendSimpleEmailMessage(
                    userInstance.username,

                    message(code: 'mail.from.label.registration') + "" + (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.replyAddress,
                    grailsApplication.config.internalEmailService.subjectPrefix + "" + message(code: "registration.confirmation"),


                    g.render(template: "/email/companyRegistrationConfirmation", model: [companyInstance: companyInstance, userInstance: userInstance])
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            //println("Email error.")

        }


    }

    private void sendNotificationEmail(Company companyInstance) {


        try {
            internalEmailService.sendSimpleEmailMessage(


                    grailsApplication.config.internalEmailService.infoEmails,
                    grailsApplication.config.internalEmailService.infoEmailsSubjectPrefix + "" + (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.infoEmailsReply,
                    grailsApplication.config.internalEmailService.infoEmailsSubjectPrefix + "" + message(code: "company.new.registration"),

                    g.render(template: "/email/newCompanyRegistered", model: [companyInstance: companyInstance]),

            )

            /*
            internalEmailService.sendSimpleEmailMessage(
                    "info@ikariera.cz",
                    "\"" + message(code: 'mail.from.label.registration') +" - iKariera.cz\" <registration@ikariera.cz>",
                    "registration@ikariera.cz",
                    "[ikariera] " + message(code: "company.new.registration"),
                    g.render(template: "/email/newCompanyRegistered", model: [companyInstance: companyInstance])
            )
            */
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            //println("Email error.")

        }


    }


}
