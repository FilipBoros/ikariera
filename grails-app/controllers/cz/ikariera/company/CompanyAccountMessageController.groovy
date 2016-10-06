package cz.ikariera.company

import cz.ikariera.company.JobOfferReply
import cz.ikariera.security.User
import cz.ikariera.student.Dialog
import cz.ikariera.student.StudentAccount
import grails.transaction.Transactional
import org.springframework.http.HttpStatus

@Transactional
class CompanyAccountMessageController {

    def springSecurityService
    def mailService
    def dialogsService
    def internalEmailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    private def checkCompanyPermission(Dialog dialog) {
        User user = springSecurityService.getCurrentUser() as User
        if (dialog == null) {
            redirect(view: 'notFound')
            return
        }
        def company = user.company
        if (dialog.recipient?.company != company && dialog.sender?.company != company)
            return false

        return true
    }

    private def checkCompanyPermission(JobOfferReply reply) {
        User user = springSecurityService.getCurrentUser() as User
        if (reply == null) {
            redirect(view: 'notFound')
            return
        }
        def company = user.company
        if (reply.jobOffer.company != company)
            return false

        return true
    }


    def detail(JobOfferReply jobOfferReply) {

        def user = User.find { username == jobOfferReply.studentEmail }

        checkCompanyPermission(jobOfferReply)

        jobOfferReply.isRead = true

        jobOfferReply.save(flush: true)

        render view: 'detail', model: [reply: jobOfferReply, studentUser: user]
    }

    def jobofferfeedbacks() {

        User user = springSecurityService.getCurrentUser()

        params.max = Math.min(params.int("max") ?: 10, 100)
        params.offset = params.offset ? params.offset : 0
        params.sort = "dateCreated"
        params.order = "desc"

        def feedBackList = JobOfferReply.where { jobOffer.company == user.company }

        render view: 'jobOfferReplies', model: [feedBackList: feedBackList.list(params), feedBackCount: feedBackList.count()]
    }

    /* inbox */

    def index() {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        //  def dialogList = Dialog.where { ((User)recipient==(User)user && company==user.company) || (company==user.company && recipient==null) } //recipient == user && company == user.company

        def dialogList = Dialog.where { company == user.company } //recipient == user && company == user.company


        params.max = Math.min(params.int("max") ?: 10, 100)
        params.offset = params.offset ? params.offset : 0
        params.sort = "id"
        params.order = "desc"

        render view: 'index', model: [companyMessageInstanceList: dialogList.list(params), companyMessageInstanceCount: dialogList.count()]
//.list(params)
    }

    def outbox() {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        // def dialogList = Dialog.where { sender==user && company==user.company }
        def dialogList = Dialog.where { company == user.company }

        params.max = Math.min(params.int("max") ?: 10, 100)
        params.offset = params.offset ? params.offset : 0
        params.sort = "id"
        params.order = "desc"

        render view: 'outbox', model: [companyMessageInstanceList: dialogList.list(params), companyMessageInstanceCount: dialogList.count()]
//.list(params)
    }

    def jobOfferMessages() {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        def dialogList = Dialog.where { sender.company == user.company || recipient.company == user.company }

        params.max = Math.min(params.int("max") ?: 10, 100)
        params.offset = params.offset ? params.offset : 0
        params.sort = "id"
        params.order = "desc"

        def feedBackList = JobOfferReply.where { jobOffer.company == user.company }

        render view: '/companyAccountMessageJoboffer/index', model: [companyMessageInstanceList: dialogList.list(params), companyMessageInstanceCount: dialogList.count(), feedBackList: feedBackList.list(params), feedBackCount: feedBackList.count()]
//.list(params)
    }


    def totalUnreadCount() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        //in
        def dialogList = Dialog.where {
            (recipient == user && company == user.company) || (company == user.company && recipient == null)
        }.list()
        def totalCountDialogs = 0
        dialogList.each {
            if (it.getUnreadCountRecipient(false) != 0) {
                totalCountDialogs++
            }
        }
        //out
        def dialogListOut = Dialog.where { (sender == user && company == user.company) }.list()
        def totalCountDialogsOut = 0
        dialogListOut.each {
            if (it.getUnreadCountSender(false) != 0) {
                totalCountDialogsOut++
            }

        }

        render totalCountDialogs + totalCountDialogsOut //totalCount
    }


    def dialogUnreadCount() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        def feedBackList = JobOfferReply.where { jobOffer.company == user.company }.list()
        def totalCount = feedBackList.count { !it.isRead }

        render totalCount
    }


    def dialogUnreadCountAll() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        def dialogList = Dialog.where {
            (company == user.company && recipient == null)//(company == user.company) ||
        }.list()
        def totalCountDialogs = 0
        dialogList.each {
            if (it.getUnreadCountRecipient(false) != 0) {
                totalCountDialogs++
            }
        }

        def dialogListOut = Dialog.where { sender == user && company == user.company }.list()
        dialogListOut.each {
            if (it.getUnreadCountSender(false) != 0) {
                totalCountDialogs++
            }
        }

        render totalCountDialogs
    }

    def dialogUnreadCountIn() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        def dialogList = Dialog.where {
            (company == user.company) || (company == user.company && recipient == null)
        }.list()
        def totalCountDialogs = 0
        dialogList.each {
            if (it.getUnreadCountRecipient(false) != 0) {
                totalCountDialogs++
            }
        }

        render totalCountDialogs
    }

    def dialogUnreadCountOut() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        def dialogList = Dialog.where { sender == user && company == user.company }.list()
        def totalCountDialogs = 0
        dialogList.each {
            if (it.getUnreadCountSender(false) != 0) {
                totalCountDialogs++
            }
        }

        render totalCountDialogs
    }


    def showIn(Dialog dialog) {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        checkCompanyPermission(dialog)

        if (user == dialog.sender) {
            dialog.readAllMsgSender(user == dialog.sender)
        } else {
            dialog.readAllMsgRecipient(user == dialog.recipient)
        }
        dialog.save(flush: true)

        render view: 'showIn', model: [dialog: dialog]
    }

    def showOut(Dialog dialog) {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        checkCompanyPermission(dialog)

        dialog.readAllMsgSender(user == dialog.sender)

        dialog.save(flush: true)

        render view: 'showOut', model: [dialog: dialog]
    }

    def sendToStudent() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        User studentUser = StudentAccount.get(params?.id).user

        Dialog dialog = dialogsService.startNewDialog(user, studentUser, params?.subject)

        if (dialog.id == null) {
            flash.error = message(code: "error")
            //redirect( controller: 'companyAccountCvSearch', action: 'index')
            return
        }

        if (!dialogsService.replyToRecipient(dialog, user.fullName + " (" + user?.company?.companyName + ")", params.text)) {
//false
            flash.error = message(code: "error.empty")
            redirect(controller: 'companyAccountCvSearch', action: 'index')
            return
        }

        try {
            internalEmailService.sendNotificationToStudent(dialog, params.text, message(code: 'company.conect.email.subject', args: [user.company.companyName]))
        }
        catch (Exception e) {
            log.error("Fail send email to student.")
        }
        flash.message = message(code: "message.sent")
        redirect action: 'detail', controller: 'companyAccountCvSearch', id: params?.id
    }

    def replyOut(Dialog dialog) {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        checkCompanyPermission(dialog)

        if (!dialogsService.replyToRecipient(dialog, user.fullName + " (" + user?.company?.companyName + ")", params.text)) {
//dialog.sender != user
            flash.error = message(code: "error")
            return
        }

        flash.message = message(code: "message.sent")

        redirect(action: "showOut", id: dialog.id)
    }

    def replyIn(Dialog dialog) {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        checkCompanyPermission(dialog)

        if (dialog.recipient != null && dialog.sender == user) {
            if (!dialogsService.replyToRecipient(dialog, user.fullName + " (" + user?.company?.companyName + ")", params.text)) {
                flash.error = message(code: "error")
                return
            }
        } else {
            if (!dialogsService.replyToSender(dialog, user.fullName + " (" + user?.company?.companyName + ")", params.text)) {
                flash.error = message(code: "error")
                return
            }
        }


        def a = dialog.messages.first().sender

        if (!dialog.sender && !dialog.recipient) {

            try {
                internalEmailService.sendNotification(dialog.subject, dialog.messages.first().sender, user.username, params.text, user.company.companyName, message(code: 'company.respond.email.subject', args: [user.company.companyName]))
            }
            catch (Exception e) {

                log.error("Fail send email to student ${params.mail}. Error: ${e.message}", e)
            }

            flash.message = message(code: "message.sent.none.register.student")
        } else {
            flash.message = message(code: "message.sent")
        }



        redirect(action: "showIn", id: dialog.id)
    }

    def respondToStudent() {
        User user = springSecurityService.getCurrentUser() as User


        def student = User.get(params?.id)

        if (student != null) {
            Dialog dialog = dialogsService.startNewDialog(user, student, params?.subject)
            if (dialog.id == null) {
                flash.error = message(code: "error")
                return
            }

            if (!dialogsService.replyToJobOffer(dialog, user.fullName + " (" + user?.company?.companyName + ")", params.text)) {
                flash.error = message(code: "error")
                return
            }
        }

        //send notification
        try {
            internalEmailService.sendNotification(params.subject, params.mail, user.username, params.text, user.company.companyName, message(code: 'company.respond.email.subject', args: [user.company.companyName]))
        }
        catch (Exception e) {

            log.error("Fail send email to student ${params.mail}. Error: ${e.message}", e)
        }


        flash.message = message(code: "message.sent")
        redirect(action: 'jobofferfeedbacks')
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyMessageInstance.label', default: 'CompanyMessage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: HttpStatus.NOT_FOUND }
        }
    }
}
