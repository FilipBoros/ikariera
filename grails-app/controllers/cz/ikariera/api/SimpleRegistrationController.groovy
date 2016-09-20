package ikariera.api

import cz.ikariera.admin.ApiKey
import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import groovy.time.TimeCategory

class SimpleRegistrationController {




    def internalEmailService
    def springSecurityService






    def index() {

        if(ApiKey.countByServiceNameAndExpireGreaterThanEqualsAndValue("SimpleRegistration", new Date(), params.id) == 0 ){
            response.status = 405
            render message(code: 'api.access.denied', default: "Access Denied")
            return
        }

            render(view: "/simpleRegistration/index", model: [token: params.id,  userInstance: new User(params)])
    }

    def save() {

        def token = params.token

        if(ApiKey.countByServiceNameAndExpireGreaterThanEqualsAndValue("SimpleRegistration", new Date(), token) == 0 ){
            response.status = 405
            render message(code: 'api.access.denied', default: "Access Denied")
            return
        }


        User userInstance = new User(params)

        bindData(userInstance, params, [include: ['studentAccount', 'password', 'passwordConfirm', 'username']])

        userInstance.username = userInstance.username?.toString()?.toLowerCase()

      //  userInstance.validate()

        if (params.passwordConfirm != userInstance.password) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.emptyPassword.error')
        }





       // def educations = params.educations
        // StudentAccount studentAccountInstance  =  new StudentAccount(params)
     //   bindData(userInstance?.studentAccount, params )

       // userInstance.studentAccount = studentAccountInstance


        //userInstance.token = java.util.UUID.randomUUID().toString()
        userInstance.token = null

        userInstance.enabled = true

        /* Token expiration */
        Date now = new Date()

        use(TimeCategory) {
            userInstance.tokenExpiration = now + 2.hours
        }

        userInstance.password = springSecurityService.encodePassword(userInstance.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password


        if (!userInstance.validate()) {

            render(view: "index", model: [userInstance: userInstance ])
            return
        }



        if (!userInstance.save(failOnError: true, flush: true)) {

            render(view: "index", model: [userInstance: userInstance])
            return
        }

        UserRole.create(userInstance, Role.findByAuthority('ROLE_STUDENT'), true)

        sendEmail(userInstance)



        redirect( controller: "simpleRegistration", action: "registrationComplete", id: params.token)
    }

    def registrationComplete() {

        if(ApiKey.countByServiceNameAndExpireGreaterThanEqualsAndValue("SimpleRegistration", new Date(), params.id) == 0 ){
            response.status = 405
            render message(code: 'api.access.denied', default: "Access Denied")
            return
        }

        render(view: "registrationComplete", model: [token: params.id])
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

