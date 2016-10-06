package cz.ikariera.front

import cz.ikariera.company.JobOffer
import cz.ikariera.company.JobOfferReply
import cz.ikariera.security.User
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

class MessageController {

    def springSecurityService
    def mailService
    def internalEmailService
    def dialogsService


    def send(JobOffer jobOffer) {
        User user = springSecurityService.getCurrentUser() as User

        String firstName
        String lastName
        String email
        String fileName
        String displayFileName

        if (user == null) {

            firstName = params.firstName
            lastName = params.lastName
            email = params.contactMail

            //upload file
            if (request instanceof MultipartHttpServletRequest) {
                MultipartFile file = request.getFile("myFile")
                if (!file.empty) {
                    //if (file.size > 0) {
                        String newFilenameBase = UUID.randomUUID().toString()

                        String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))

                        originalFileExtension = originalFileExtension?.toLowerCase()

                        if (originalFileExtension != '.pdf') {
                            flash.error = message(code: 'student.cv.pdf')
                            return
                        }

                        if (file.size > 5000000) {
                            flash.error = message(code: 'student.cv.bigFile')
                            return
                        }

                        String newFilename = newFilenameBase + originalFileExtension
                        fileName = newFilename
                        String storageDirectory = grailsApplication.config.upload.directory.studentCv
                        displayFileName = file.originalFilename
                        File newFile = new File("$storageDirectory/$newFilename")
                        file.transferTo(newFile)
                    }
               // }
            }
        } else {
            firstName = user.firstName
            lastName = user.lastName
            email = user.username
            if (params?.cv) {
                fileName = user.studentAccount?.cv?.newFilename
                displayFileName = user.studentAccount?.cv?.originalFilename
            }
        }

        def studentReply = new JobOfferReply(
                studentName: firstName,
                studentLastName: lastName,
                studentEmail: email,
                replyText: params.text,
                newFilename: fileName,
                originalFilename: displayFileName
        )

        jobOffer.addToReplies(studentReply)
        if (!jobOffer.validate()) {
            flash.error = "Bad request"
            redirect(controller: "jobOffer", action: "detail", id: jobOffer.id)
            return
        }
        if (!jobOffer.save(flush: true, failOnError: true)) {
            flash.error = "Bad request"
            redirect(controller: "jobOffer", action: "detail", id: jobOffer.id)
            return
        }



        if (jobOffer?.contactDetails?.email) {


            try{
                def sbj = g.message(code : 'student.respond.email.subject', args: [studentReply.studentName])
                internalEmailService.notifyJobOfferReply(studentReply, jobOffer?.contactDetails?.email, sbj)


            }
            catch (Exception e){
                //println("Fail send email to company.")
                log.error("Fail send email to company. Error: ${e.message}", e )
            }
        }

        flash.message = "reply sent"
        redirect(controller: "jobOffer", action: "detail", id: jobOffer.id)
    }




}
