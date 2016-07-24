package cz.ikariera.service

import cz.ikariera.company.Company
import cz.ikariera.security.User
import cz.ikariera.student.Dialog
import cz.ikariera.student.DirectMessage
import grails.transaction.Transactional

@Transactional
class DialogsService {

    //delete when messaging will be changed
    def startNewDialog(User userSender, User userRecipient, String subject) {

        Dialog dialog = new Dialog(
                sender: userSender,
                company: userSender.company,
                recipient: userRecipient,
                subject: subject,
                messages: new ArrayList()
        )
        dialog.validate()
        if (dialog.hasErrors()){
            //respond dialog.errors, view: 'create'
            return
        }

        dialog.save(flush : true, failOnError: true)

        return dialog
    }

    def startNewDialogCompany(User userSender, Company company, String subject) {

        Dialog dialog = new Dialog(
                sender: userSender,
                company: company,
                //recipient: userRecipient,
                subject: subject,
                messages: new ArrayList()
        )
        dialog.validate()
        if (dialog.hasErrors()){
            //respond dialog.errors, view: 'create'
            return
        }

        dialog.save(flush : true, failOnError: true)

        return dialog
    }


    def replyTo(Dialog dialog, String sender, String messageBody, boolean isReply){

        DirectMessage directMessage = new DirectMessage(
                sendDate: new Date(),
                message: messageBody,
            //    isRead: false,
            //    isReply: isReply,
                sender : sender)

        dialog.addToMessages(directMessage)

        dialog.validate()

        if (dialog.hasErrors()){
            return false
        }
        if (!dialog.save(flush : true, failOnError: true)){
            return false
        }
        return true
    }

    def replyToJobOffer(Dialog dialog, String sender, String messageBody){

        DirectMessage directMessage = new DirectMessage(
                sendDate: new Date(),
                message: messageBody,
                isReadRecipient: false,
                isReplyRecipient: false,
                isReadSender: true,
                isReplySender: true,
                sender : sender)

        dialog.addToMessages(directMessage)

        dialog.validate()

        if (dialog.hasErrors()){
            return false
        }
        if (!dialog.save(flush : true, failOnError: true)){
            return false
        }
        return true
    }

    def replyToSender(Dialog dialog, String sender, String messageBody){

        DirectMessage directMessage = new DirectMessage(
                sendDate: new Date(),
                message: messageBody,
                isReadRecipient: true,
                isReplyRecipient: true,
                isReadSender: false,
                isReplySender: false,
                dialog: dialog,
                sender : sender)


        directMessage.validate()
        directMessage.save(flush : true, failOnError: true)
        /*dialog.add addToMessages(directMessage)

        dialog.validate()

        if (dialog.hasErrors()){
            return false
        }
        if (!dialog.save(flush : true, failOnError: true)){
            return false
        }*/



        return true
    }

    def replyToRecipient(Dialog dialog, String sender, String messageBody){

        DirectMessage directMessage = new DirectMessage(
                sendDate: new Date(),
                message: messageBody,
                isReadRecipient: false,
                isReplyRecipient: false,
                isReadSender: true,
                isReplySender: true,
                sender : sender)

        dialog.addToMessages(directMessage)

        dialog.validate()

        if (dialog.hasErrors()){
            return false
        }
        if (!dialog.save(flush : true, failOnError: true)){
            return false
        }
        return true
    }


    def getUnreadCount(User user){

        def myDialogs = Dialog.findAllByRecipient(user)

        def count = myDialogs.count {
            it.messages.any{
                it.isRead == false && it.isReply == false
            }
        }
    }
}
