package ikariera.company

import cz.ikariera.company.CompanyFeedBack
import cz.ikariera.security.User

class CompanyAccountFeedbackController {

    def springSecurityService
    def internalEmailService
    def index() {
        render view : 'feedback'
    }

    def feedback(){
        render view : 'feedback'
    }

    def sendFeedBack(){

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        CompanyFeedBack feedBack = new CompanyFeedBack(subject: params.subject, text: params.text, sender: user.company.companyName)

        try {
            internalEmailService.sendInfoMail(

                    "Feedback",
                    "Company " + user.company.companyName + " sent feedback " + user.username,

                    grailsApplication.config.info.ikariera.email.adress
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e
            //println("Email error.")
        }

        if (!feedBack.save(flush: true, failOnError: true)){
            flash.error = "Failed to send feedback"
            return
        }

        flash.message = message(code: 'message.sent')

        render view : 'feedback'
    }
}
