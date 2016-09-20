package ikariera.student

import cz.ikariera.company.Company
import cz.ikariera.company.JobOffer
import cz.ikariera.security.User
import cz.ikariera.student.Dialog
import grails.transaction.Transactional

@Transactional
class StudentAccountMessageController {

    def springSecurityService
    def dialogsService
    def internalEmailService

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", sendToCompany: "POST",]

    def showIn(Dialog dialog) {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        if(user == dialog.recipient){
            dialog.readAllMsgRecipient(user == dialog.recipient)
        }else{
            dialog.readAllMsgSender(user == dialog.sender)
        }

        dialog.save(flush: true)

        render view: 'showIn', model: [dialog: dialog]
    }

    def showOut(Dialog dialog) {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        dialog.readAllMsgSender(dialog.sender == user)

        dialog.save(flush: true)

        render view: 'showOut', model: [dialog: dialog]
    }

    def replyInbox(Dialog dialog) {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        checkStudentPermission(dialog, user)

        if (dialog.sender == user) {
            if (!dialogsService.replyToRecipient(dialog, user.fullName + " (" + user.username + ")", params.text)) {
                flash.error = message(code: "error")
                return
            }
        } else {
            if (!dialogsService.replyToSender(dialog, user.fullName + " (" + user.username + ")", params.text)) {
                flash.error = message(code: "error")
                return
            }
        }
        flash.message = message(code: "message.sent")

        redirect(action: "showIn", id: dialog.id)
    }

    def replyOutbox(Dialog dialog) {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        checkStudentPermission(dialog, user)

        //if (!dialogsService.replyTo(dialog, user.firstName + " (Student)", params.text, dialog.sender != user)) {
        if (!dialogsService.replyToRecipient(dialog, user.fullName + " (" + user.username + ")", params.text)) {
            flash.error = message(code: "error")
            return
        }

        flash.message = message(code: "message.sent")

        redirect(action: "showOut", id: dialog.id)
    }

    def inBox() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        params.max = Math.min(params.int("max") ?: 10, 100)
        params.offset = params.offset ? params.offset : 0
        params.sort = "id"
        params.order = "desc"

        List studentMessageInstanceList = Dialog.createCriteria().list(params) {
            order("id", "desc")
            // eq("recipient", user)

        }

        def studentMessageInstanceListTotal = studentMessageInstanceList?.totalCount
        render view: 'index', model: [studentMessageInstanceList: studentMessageInstanceList, studentMessageInstanceListTotal: studentMessageInstanceListTotal]
//.list(params)
    }

    def outbox() {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }



        params.max = Math.min(params.int("max") ?: 10, 100)
        params.offset = params.offset ? params.offset : 0
        params.sort = "id"
        params.order = "desc"


        List studentMessageInstanceList = Dialog.createCriteria().list(params) {

            order("id", "desc")
            //   eq("sender", user)


        }

        def studentMessageInstanceListTotal = studentMessageInstanceList?.totalCount

        render view: 'outbox', model: [studentMessageInstanceList: studentMessageInstanceList, studentMessageInstanceListTotal: studentMessageInstanceListTotal]
//.list(params)
    }

    def sendMessageToCompany() {
        User user = springSecurityService.getCurrentUser() as User


        def company = Company.get(params?.id)

        if (!company) {
            flash.error = message(code: "error")
            return
        }
        Dialog dialog = dialogsService.startNewDialogCompany(user, company, message(code: 'student.send.email.subject', args: [user.username]))
        if (dialog.id == null) {
            flash.error = message(code: "error")
            return
        }

        if (!dialogsService.replyToRecipient(dialog, user.firstName + " (" + user?.username + ")", params.text)) {
            flash.error = message(code: "error")
            return
        }

        //send notification
        try {
            internalEmailService.sendNotificationToCompany(message(code: 'student.send.email.subject', args: [user.username]), params.mail, company.publicEmail, params.text, dialog, user.firstName + " " + dialog, user.lastName, message(code: 'student.send.email.subject', args: [user.username]))
        }
        catch (Exception e) {

            log.error("Fail send email to company ${params.mail}. Error: ${e.message}", e)
        }


        flash.message = message(code: "message.sent")
        redirect(controller: "companyProfiles", action: "detail", id: params.id)
    }

    def jobOfferReply() {//JobOffer jobOffer
        User user = springSecurityService.getCurrentUser() as User
        def a = params

        def jobOffer = JobOffer.get(params?.id)
        def company = Company.get(params?.companyId)

        if (!company) {
            flash.error = message(code: "error")
            return
        }
        if (!jobOffer) {
            flash.error = message(code: "error")
            return
        }
        Dialog dialog
        if (user) {
            dialog = dialogsService.startNewDialogCompany(user, company, message(code: 'student.respond.email.subject', args: [user.username]))

            if (!dialogsService.replyToRecipient(dialog, user.firstName + " (" + user?.username + ")", params.text)) {
                flash.error = message(code: "error")
                return
            }
        } else {
            dialog = dialogsService.startNewDialogCompany(null, company, message(code: 'student.respond.email.subject', args: [params.contactMail]))

            //params.contactMail
            if (!dialogsService.replyToRecipient(dialog, params.contactMail, params.text)) {
                flash.error = message(code: "error")
                return
            }
        }
        dialog.jobOffer = jobOffer
        if (dialog.id == null) {
            flash.error = message(code: "error")
            return
        }

        //send notification
        if (company.publicEmail) {
            try {
                internalEmailService.sendNotificationToCompany(message(code: 'student.send.email.subject', args: [user.username]), params.mail, company.publicEmail, params.text, dialog, user.firstName + " " + dialog, user.lastName, message(code: 'student.send.email.subject', args: [user.username]))
            }
            catch (Exception e) {

                log.error("Fail send email to company ${params.mail}. Error: ${e.message}", e)
            }
        }

        flash.message = message(code: "message.sent")
        redirect(controller: "jobOffer", action: "detail", id: jobOffer.id)
    }

    protected void notFound() {

        flash.message = message(code: 'default.not.found.message', args: [message(code: 'studentMessageInstance.label', default: 'StudentMessage'), params.id])
        redirect action: "index", method: "GET"

    }

    private def checkStudentPermission(Dialog dialog, User user) {

        if (dialog == null) {
            redirect(view: 'notFound')
            return
        }
        if (dialog.recipient != user && dialog.sender != user)
            return false

        return true
    }


    def dialogUnreadCountAll() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        def dialogList = Dialog.where { recipient == user }.list()
        def totalCountDialogs = 0
        dialogList.each {
            if (it.getUnreadCountRecipient(false) != 0) {
                totalCountDialogs++
            }
        }
        def g1 = totalCountDialogs
        def dialogListOut = Dialog.where { sender == user }.list()
        dialogListOut.each {
            if (it.getUnreadCountSender(false) != 0) {
                totalCountDialogs++
            }
        }
        def g2 = totalCountDialogs
        render totalCountDialogs
    }

    def dialogUnreadCountIn() {
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        def dialogList = Dialog.where { recipient.equals(user) || sender.equals(user) }.list()
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
        def dialogList = Dialog.where { sender.equals(user) }.list()
        def totalCountDialogs = 0
        dialogList.each {
            if (it.getUnreadCountSender(false) != 0) {
                totalCountDialogs++
            }
        }

        render totalCountDialogs
    }

}
