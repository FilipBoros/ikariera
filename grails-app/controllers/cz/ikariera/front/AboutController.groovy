package cz.ikariera.front

import cz.ikariera.admin.Contact
import cz.ikariera.security.User
import org.apache.commons.validator.EmailValidator

class AboutController {

    def springSecurityService
    //def mailService
    def internalEmailService
    def recaptchaService


    def index() {
       redirect(action: "about")
    }

    def about () {
        render (view: "/about/about")

    }

    def termOfUse(){

        render (view: "/about/termOfUse")
    }

    def  contact () {
        def contactInstance = Contact.findByContactIdentify("IAESTE")

        render (view: "/about/contact", model: [contactInstance: contactInstance])
    }

    /**
    def  writeUs () {
        User user = springSecurityService.getCurrentUser()

        render (view: "/about/writeUs", model: [userInstance: user])
        //render (view: "/about/writeUs")

    } **/

    def  sendEmail () {
        User user = new User(params)
        def err = false

        if (!params.username){
            user.errors.rejectValue('username', 'about.userName.error')
            err = true
        }else if (params.username){
            EmailValidator emailValidator = EmailValidator.getInstance()
            if (!emailValidator.isValid(params.username.toString())){
                user.errors.rejectValue('username', 'about.userName.wrongAddrss')
                err = true
            }
        }

        if(!params.subject){
            user.errors.rejectValue('password1', 'about.subject.error')
            err = true
        }

        if(!params.text){
            user.errors.rejectValue('password2', 'about.text.error')
            err = true
        }

        /** CAPTCHA **/
        /*try{
            String captcha =  params.response?.toString()
            def control = jcaptchaService.validateResponse("image", session.id, captcha?.toLowerCase())
            if(control){}
            else{
                user.errors.reject('system.captcha.warning')
                err = true
            }

        } catch (Exception e) {
            user.errors.reject('system.captchaLost.warning')
            render(view: "/about/contact",
                    model: [userInstance: user,
                            contactInstance: Contact.findByContactIdentify("IAESTE"),
                            params: params])
            return
        }*/

        /** RECAPTCHA **/
        /*def recaptchaOK = true
        if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
            recaptchaOK = false
        }
        if(recaptchaOK ) {
            recaptchaService.cleanUp(session)
        }
        else {
            err = true
        }

        if(err){
            flash.message = message(code:'system.captchaLost.warning')
            user.errors.reject('system.captchaLost.warning')//not in view part
            render(view: "/about/contact",
                    model: [userInstance: user,
                            contactInstance: Contact.findByContactIdentify("IAESTE"),
                            params: params])
            return
        }
*/



        //send mail
        /*try{
            mailService.sendMail {

                from  params.username
                to    "info@ikariera.cz"
                subject params.subject
                html params.text
            }

        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            flash.error = message(code:'about.notsent')
            render (view: "/about/contact",
                    model: [userInstance: user,
                            contactInstance: Contact.findByContactIdentify("IAESTE"),
                            params: params])
        }*/

        try {
            internalEmailService.sendSimpleEmailMessage(




                    grailsApplication.config.internalEmailService.infoEmails,
                    grailsApplication.config.internalEmailService.infoEmailsSubjectPrefix + "" +  (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.infoEmailsReply,
                    grailsApplication.config.internalEmailService.infoEmailsSubjectPrefix + "" +   params.subject,

                    params.text,
            )
            //
/*
            internalEmailService.sendSimpleEmailMessage(
                    "info@ikariera.cz",
                    "no-reply@ikariera.cz",
                    "no-reply@ikariera2.cz",
                    "no-reply@ikariera3.cz",
                    params.text
            )*/
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            return false
        }


        flash.message = message(code:'about.sent')
        render (view: "/about/contact",
                model: [userInstance: user,
                        contactInstance: Contact.findByContactIdentify("IAESTE"),
                        params: params])

    }


}
