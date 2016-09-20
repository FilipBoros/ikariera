package ikariera.student

import cz.ikariera.security.User
import cz.ikariera.student.Cv
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

class StudentAccountCvController {

    def springSecurityService

    def index() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        render(view: "index", model: [userInstance: user])

    }


    def getStudentCv() {

        String uploadDirectory = grailsApplication.config.upload.directory.studentCv

        try {

            def picFile = new File(uploadDirectory + "/" + params.id)

            response.addHeader("Cache-Control", "no-store");

            // response.contentType = 'pdf/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    def upload() {


        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")


        }

        // def results = []
        if (request instanceof MultipartHttpServletRequest) {
            for (filename in request.getFileNames()) {
                MultipartFile file = request.getFile(filename)
               // println "file name : " + file.originalFilename

                if (file.empty) {
                    // TODO : normal flash
                    flash.error = message(code: 'project.file.empty')
                    redirect(view: 'index', model: [userInstance: user])
                    return
                }

                String newFilenameBase = UUID.randomUUID().toString()

                String originalFileExtension = ""
                if(file.originalFilename?.lastIndexOf(".")?.toInteger()>0)
                    originalFileExtension = file.originalFilename.substring(file.originalFilename?.lastIndexOf("."))




                originalFileExtension = originalFileExtension?.toLowerCase()

                //println(originalFileExtension)
                if (originalFileExtension != '.pdf') {
                    flash.error = message(code: 'student.cv.pdf')
                    render(view: 'index', model: [userInstance: user])
                    return
                }

               // println("fdfdfd")
                if (file.size > 5000000) {
                    flash.error = message(code: 'student.cv.bigFile')
                    render(view: 'index', model: [userInstance: user])
                    return
                }



                String newFilename = newFilenameBase + originalFileExtension

               // println(params?.mediaType)


                String storageDirectory = grailsApplication.config.upload.directory.studentCv
               // storageDirectory = "C:\\Users\\Senman\\uploads\\ikariera\\cv"
                File newFile = new File("$storageDirectory/$newFilename")
                file.transferTo(newFile)
                //println "file transferred : " + "$storageDirectory/$newFilename"



                log.error("Original File name " + file.originalFilename)
                log.error("New File name" + newFilename)



                Cv cv = new Cv(
                        originalFilename: file.originalFilename,
                        student: user.studentAccount,

                        newFilename: newFilename,
                        name: file.originalFilename,
                        fileSize: file.size
                ).save()

/*                results << [
                        name: cv.originalFilename,
                        size: cv.fileSize,
                        url: createLink(controller: 'media', action: 'picture', id: cv.id),
                        thumbnailUrl: createLink(controller: 'media', action: 'thumbnail', id: cv.id),
                        deleteUrl: createLink(controller: 'adminPictureMedia', action: 'delete', id: cv.id),
                        deleteType: "DELETE"
                ]*/
            }
        }

        flash.message = message(code: 'default.created.message')
        redirect(controller: "studentAccountPersonalDetails", action: "index")

    }


    def delete() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }



        if (!user.studentAccount?.cv) {
            notFound()
            return
        }


        def cv = user.studentAccount?.cv

        try {

            File picFile = new File(grailsApplication.config.upload.directory.studentCv + "/ " + cv.newFilename)
            picFile.delete()

        } catch (Exception ex) {

            flash.message = cv.errors
            redirect action: "index", method: "GET"
            return
        }

        user.studentAccount.cv = null


        user.save(flush: true)
        flash.message = message(code: 'cv.deleted.message' )
        redirect(controller: "studentAccountPersonalDetails", action: "index")
    }




    protected void notFound() {

        flash.message = message(code: 'default.not.found.message')
        redirect action: "index", method: "GET"

    }


}