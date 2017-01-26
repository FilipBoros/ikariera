package cz.ikariera.admin

import cz.ikariera.security.User
import grails.plugins.rest.client.RestBuilder
import org.springframework.dao.DataIntegrityViolationException

class AdminEmailController {

    def springSecurityService

    def mailService

    def index() {
        def user = User.cast(springSecurityService.getCurrentUser())

        if (!user) {
            redirect(controller: "logout")
            return
        }


        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        List adminEmailsList = AdminEmails.createCriteria().list(params) {
            order('dateCreated', 'desc')

        }


        render(view: "index",
                model: [adminEmailsInstanceList     : adminEmailsList,
                        adminEmailsInstanceListCount: adminEmailsList.size()]
        )
    }

    def createDemoEmail() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        def adminEmail = new AdminEmails(params)
        //"Testing email"
        adminEmail.subject = message(code: 'admin.demo.subject')
        //"This is testing email from www.ikariera.cz. Please do not reply.  "
        adminEmail.message = message(code: 'admin.demo.message')
        adminEmail.dateCreated = new Date()

        render(view: "/adminEmail/createDemoEmail", model: [adminEmailsInstance: adminEmail])
    }

    def displayEmail() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        def adminEmailsInstance = AdminEmails.get(params.id)
        if (!adminEmailsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "list")
            return
        }
        render(view: "/adminEmail/displayEmail", model: [adminEmailsInstance: adminEmailsInstance])
    }

    def showEmail() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def adminEmailsInstance = AdminEmails.get(params.id)
        if (!adminEmailsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "list")
            return
        }

        render(view: "/adminEmail/showEmail", model: [adminEmailsInstance: adminEmailsInstance])
    }



    def saveDemoEmail() {

        def adminEmailInstance = new AdminEmails(

        attachment: params.attachment,
        subject: params.subject,
        message: params.message,
        numberRecipients: 1,
        recipients: params.recipients,
        companyEmail: null,
        originalName: params.originalName,
        )

        if (!adminEmailInstance.save(flush: true)){
            render(view: "createDemoEmail", model: [adminEmailsInstance: adminEmailInstance])

            return
        }

        flash.message = message(code: 'admin.demo.email.created')

        redirect(action: 'index')
    }




    def sendEmail(AdminEmails emailInstance) {

        if (!emailInstance) {
            redirect(action: 'index')
            return
        }

       // def emailInstance = AdminEmails.get(params.id)

        RestBuilder rest = new RestBuilder()

        def pathMail = "/api-rest-mail/"
        def pathMailAttach = "/api-rest-mail-attachment/"

        RemoteApiKey APIKEY = RemoteApiKey.find { serviceName == "Email" }

        def msgStr = g.render(template: '/email/companyEmail', model: [message: emailInstance.message]).toString()
        def resp

       // println emailInstance
       // println emailInstance.subject
      //  println emailInstance.recipients
      //  println APIKEY.url + pathMail + APIKEY.value
        // send email

        try {
            if (grailsApplication.config.mailchimp.server) {
                resp = rest.post(APIKEY.url + pathMail + APIKEY.value) {
                    json {
                        subject = emailInstance.subject
                        message = msgStr
                        recipients = emailInstance.recipients
                        sendOn = emailInstance.dateCreated
                    }
                }


                if (resp.getStatus() == 201) {

                    if (emailInstance.attachment != null) {

                      //  println "multipart yes"

                        def received_id = resp.json.id
                        String serverPath = grailsApplication.config.upload.directory.emailAttachments

                        def ApiPost = APIKEY.url + pathMailAttach + APIKEY.value + "/" + received_id
                        //println("PRINT REST: " + ApiPost)
                        //println("PRINT FILE WHERE: " + serverPath + "/" + Email.attachment + "/" + Email.originalName)

                        resp = rest.post(ApiPost) {
                            contentType "multipart/form-data"
                            file = new File(serverPath + "/" + emailInstance.attachment + "/" + emailInstance.originalName)
                        }

                    }



                    if (emailInstance?.companyEmail) {
                        emailInstance.companyEmail.confirmed = true
                    }
                    emailInstance.dateSent = new Date()
                    emailInstance.save(flush: true, failOnError: true)
                    flash.message = message(code: 'company.email.send.message')

                } else {
                    flash.error = message(code: "ikariera.err.label") + " " + resp.getStatus() + "<br /> " + resp.getText() + "<br /> " + resp.getBody()
                }
            }
        }
        catch (Exception e) {
            flash.error = message(code: "ikariera.err.label") + " "+ e.message
        }

        redirect(action: 'index')
    }

    def deleteEmail(AdminEmails emailsInstance) {
        //def emailsInstance = AdminEmails.get(params.id)
        if (!emailsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index")
            return
        }

        try {
            emailsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index", id: params.id)
        }
    }


}


