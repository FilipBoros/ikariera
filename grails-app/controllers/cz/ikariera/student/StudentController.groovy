package ikariera.student

import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import groovy.time.TimeCategory

class StudentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def springSecurityService
    def mailService
    def internalEmailService
    def recaptchaService



    def index() {
        redirect(action: "registration")
    }

    def registrationComplete() {
        render(view: "registrationComplete")
    }

    def registration() {


        render(view: "registration", model: [userInstance: new User(params)])
    }


    def save() {

        User userInstance = new User(params)

        bindData(userInstance, params, [include: ['studentAccount', 'password', 'passwordConfirm', 'username']])


        userInstance.username = userInstance.username?.toString()?.toLowerCase()



        userInstance.validate()


        if (params.passwordConfirm != userInstance.password) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.emptyPassword.error')
        }


        if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
            userInstance.errors.rejectValue('reCaptcha', 'system.captcha.warning')
        }



        if (userInstance.hasErrors()) {
            recaptchaService.cleanUp(session)
            userInstance.password = null
            render(view: "registration", model: [userInstance: userInstance])
            return
        }

        userInstance.token = java.util.UUID.randomUUID().toString()
        /* Token expiration */
        Date now = new Date()

        use(TimeCategory) {
            userInstance.tokenExpiration = now + 2.hours
        }

        userInstance.password = springSecurityService.encodePassword(userInstance.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password



        if (!userInstance.save(failOnError: true, flush: true)) {

            render(view: "registration", model: [userInstance: userInstance])
            return
        }

        UserRole.create(userInstance, Role.findByAuthority('ROLE_STUDENT'), true)

        sendEmail(userInstance)

        redirect(action: "registrationComplete", controller: "student")
    }

    private void sendEmail(User userInstance) {

        try {
            internalEmailService.sendSimpleEmailMessage(
                    userInstance.username,

                    message(code: 'mail.from.label.registration') + "" +  (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.replyAddress,
                    grailsApplication.config.internalEmailService.subjectPrefix + "" + message(code: "mail.subject.student"),

                    g.render(template: "/email/registrationConfirmation", model: [user: userInstance])
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e

        }


    }


}
