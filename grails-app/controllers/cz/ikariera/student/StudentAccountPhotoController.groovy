package cz.ikariera.student

import cz.ikariera.security.User
import cz.ikariera.student.Photo
import grails.transaction.Transactional
import org.imgscalr.Scalr
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class StudentAccountPhotoController {


    def springSecurityService




    def index() {


        redirect(controller: "studentAccountPersonalDetails")

    }

    def upload() {


        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }




        def results = []
        if (request instanceof MultipartHttpServletRequest) {


            for (filename in request.getFileNames()) {
                MultipartFile file = request.getFile(filename)
                //println "file name : " + file.originalFilename

                String newFilenameBase = UUID.randomUUID().toString()

                String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
                String newFilename = newFilenameBase + originalFileExtension

                //println(params?.mediaType)


                String storageDirectory = grailsApplication.config.upload.directory.studentPhoto

                File newFile = new File("$storageDirectory/$newFilename")
                file.transferTo(newFile)
                //println "file transferred : " + "$storageDirectory/$newFilename"

                BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 100);
                String thumbnailFilename = newFilenameBase + '-thumbnail.png'
                File thumbnailFile = new File("$storageDirectory/$thumbnailFilename")
                ImageIO.write(thumbnail, 'png', thumbnailFile)



/*
                Photo photo = new Photo(
                        originalFilename: file.originalFilename,
                        thumbnailFilename: thumbnailFilename,
                        newFilename: newFilename,
                        student: user.studentAccount,
                        width: 400,
                        height: 200,
                        name: file.originalFilename,
                        fileSize: file.size
                ).save(failOnError: true, flush: true)
*/
    //println(user.username)
                user.studentAccount.photo = new Photo(
                        originalFilename: file.originalFilename,
                        thumbnailFilename: thumbnailFilename,
                        newFilename: newFilename,
                        width: 400,
                        height: 200,
                        name: file.originalFilename,
                        fileSize: file.size
                )

                /*  results << [
                          name: photo.originalFilename,
                          size: photo.fileSize,
                          url: createLink(controller: 'media', action: 'picture', id: photo.id),
                          thumbnailUrl: createLink(controller: 'media', action: 'thumbnail', id: photo.id),
                          deleteUrl: createLink(controller: 'adminPictureMedia', action: 'delete', id: photo.id),
                          deleteType: "DELETE"
                  ]
                  */
            }
        }

        user.studentAccount.save(failOnError: true, flush: true)

        flash.message = message(code: 'default.created.message')
        redirect(action: "index")

    }





    @Transactional
    def delete() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        Photo photoInstance = user?.studentAccount?.photo


        if (!photoInstance) {
            notFound()
            return
        }




        try {


            File picFile = new File(grailsApplication.config.upload.directory.studentPhoto + "/" + photoInstance?.newFilename)
            picFile.delete()
            File thumbnailFile = new File(grailsApplication.config.upload.directory.studentPhoto + "/" + photoInstance?.thumbnailFilename)
            thumbnailFile.delete()




        } catch (Exception ex) {

            respond user.errors, view: 'index'
            return
        }


        user.studentAccount.photo= null
        photoInstance.delete(flush: true)


        flash.message = message(code: 'default.deleted.message')
        redirect action: "index", method: "GET"

    }


    def getMedia() {

        String uploadDirectory = grailsApplication.config.upload.directory.studentPhoto

        try {

            def picFile = new File(uploadDirectory + "/" + params.id + "." + params.format)
            //println uploadDirectory

            response.addHeader("Cache-Control", "no-store");

            response.contentType = 'image/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    protected void notFound() {

        flash.message = message(code: 'default.not.found.message')
        redirect action: "index", method: "GET"

    }

}
