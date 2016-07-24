package cz.ikariera.mail

import cz.ikariera.company.JobOfferReply
import cz.ikariera.student.Dialog
import grails.transaction.Transactional
import org.apache.commons.validator.EmailValidator

@Transactional
class InternalEmailService {

    def mailService
    def groovyPageRenderer
    def grailsApplication
    def messageSource


    public boolean sendSimpleEmailMessage(
            def messageToAddress,
            def messageFromAddressLabel,
            def messageFromAddress,
            def messageSubject,
            def messageBody) {

        //def mailService = new MailService()
        messageToAddress = messageToAddress?.toString()?.toLowerCase()
        EmailValidator emailValidator = EmailValidator.getInstance()
        if (emailValidator.isValid(messageToAddress)) {
           // println("validation successfull")

            mailService.sendMail {
                from "\"" + messageFromAddressLabel + "\" <" + messageFromAddress + ">"
                to messageToAddress
                subject messageSubject
                html messageBody
            }

            return true
        } else {
           // println("error in validation")
            return false

        }

    }

    //send notification about message
    public void sendInfoMail(String sbj, String messageText, def mailAddress){
        def rMsg = groovyPageRenderer.render(template: "/email/infoMessage", model: [messageText : messageText, sbj: sbj])
        // TODO check students' permissions
        mailService.sendMail {
            // async true
            subject sbj
            to mailAddress
            from "info@cz.cz.ikariera.cz"
            html rMsg
        }
    }

    //send notification about message
    public void sendNotificationToStudent(Dialog dialog, String messageText, String sbj){
        def rMsg = groovyPageRenderer.render(template: "/email/fromCompanyToStudentMsg", model: [messageText : messageText, companyEmail: dialog.sender.username , companyName: dialog.sender.company.companyName])

        mailService.sendMail {
           // async true
            subject sbj
            to dialog.recipient.username
            from "info@cz.cz.ikariera.cz"
            html rMsg
        }
    }

    public void sendNotification(String sbj, String studentEmail, String companyEmail, String messageText, String companyName, String mailSbj){

        mailService.sendMail {
           // async true
            subject mailSbj
            to studentEmail
            from "info@cz.cz.ikariera.cz"
            html ( groovyPageRenderer.render(template: "/email/fromCompanyToStudentReply", model: [messageText : messageText, companyEmail: companyEmail, messageSubject: sbj, companyName: companyName ]))
        }
    }


    public void sendNotificationToCompany(String sbj, String studentEmail, String companyEmail, String messageText, String studentName, String mailSbj){

        mailService.sendMail {
            // async true
            subject mailSbj
            to companyEmail
            from "info@cz.cz.ikariera.cz"
            html ( groovyPageRenderer.render(template: "/email/fromStudentToCompanyMsg", model: [messageText : messageText, studentEmail: studentEmail, messageSubject: sbj, studentName: studentName ]))
        }
    }





    //student respond on job offer
    public void notifyJobOfferReply(JobOfferReply studentReply, String companyEmail, String sbj){
        if (studentReply.newFilename == null){
            mailService.sendMail {
             //   async true
                subject "iKariera - Máte nového zájemce - " + studentReply.studentName + " " + studentReply.studentLastName
                to companyEmail
                from "info@cz.cz.ikariera.cz"
                html (   groovyPageRenderer.render(template: "/email/fromStudentToCompany", model: [msg: studentReply]))
            }
        }
        else {

            String storageDirectory = grailsApplication.config.upload.directory.studentCv

            File attachment = new File("$storageDirectory/$studentReply.newFilename")

            mailService.sendMail {
                multipart true
              //  async true
                subject "iKariera - Máte nového zájemce - " + studentReply.studentName + " " + studentReply.studentLastName
                to companyEmail //studentReply.studentEmail
                from "info@cz.cz.ikariera.cz"
                html (  groovyPageRenderer.render(template: "/email/fromStudentToCompany", model: [msg: studentReply]))
                attach attachment
            }
        }
    }

    public boolean sendSimpleEmailMessageAttachment(
            def messageToAddress,
            def messageFromAdressLabel,
            def messageFromAdress,
            def messageSubject,
            def messageBody,
            def messageAttachment) {

        //def mailService = new MailService()
        messageToAddress = messageToAddress?.toString()?.toLowerCase()
        EmailValidator emailValidator = EmailValidator.getInstance()
        if (emailValidator.isValid(messageToAddress)) {
          //  println("validation successfull")

            mailService.sendMail {
                multipart true
                from "\"" + messageFromAdressLabel + "\" <" + messageFromAdress + ">"
                to messageToAddress
                subject messageSubject
                html messageBody
                attachBytes messageAttachment
            }

            return true
        } else {

           // println("error in validation")
            return false

        }
    }

}