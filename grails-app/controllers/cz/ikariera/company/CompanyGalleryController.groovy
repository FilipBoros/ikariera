package cz.ikariera.company

import cz.ikariera.admin.PictureMedia
import cz.ikariera.security.User
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class CompanyGalleryController {

    def springSecurityService


    def index() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }
        render(view: "index", model: [userInstance: user])

    }


    def getCompanyHeroyImage() {

        String uploadDirectory = grailsApplication.config.upload.directory.companyLogo

        def pictureMedia = PictureMedia.get(params.id)
        try {

            def picFile = new File(uploadDirectory + "/" + pictureMedia.newFilename)

            response.addHeader("Cache-Control", "no-store");

            // response.contentType = 'pdf/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    def getCompanyGalleryImage() {

        String uploadDirectory = grailsApplication.config.upload.directory.companyLogo

        def pictureMedia = PictureMedia.get(params.id)
        try {

            def picFile = new File(uploadDirectory + "/" + pictureMedia.newFilename)

            response.addHeader("Cache-Control", "no-store");

            // response.contentType = 'pdf/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }

    def getCompanyLogoImage() {

        String uploadDirectory = grailsApplication.config.upload.directory.companyLogo

        def pictureMedia = PictureMedia.get(params.id)
        try {

            def picFile = new File(uploadDirectory + "/" + pictureMedia.newFilename)

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
                    flash.error = message(code: 'project.file.empty')
                    render(view: 'index', model: [userInstance: user])
                    return
                }

                String newFilenameBase = UUID.randomUUID().toString()

                String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))

                byte[] fileBytes = file.bytes
                ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes)
                BufferedImage image = ImageIO.read(bais)


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
/*
                def webRootDir = servletContext.getRealPath("/")
                def userDir = new File(webRootDir, "/payload/test")
                userDir.mkdirs()
                file.transferTo( new File( userDir, file.originalFilename))
*/
                String storageDirectory = grailsApplication.config.upload.directory.companyGallery
                // storageDirectory = "C:\\Users\\Senman\\uploads\\ikariera\\cv"
                File newFile = new File("$storageDirectory/$newFilename")

                //File uploadDirectory = ApplicationHolder.getApplication().getParentContext().getResource("/Projects/Testing/uploads/ikariera/companyGallery").getFile()
                //def homeDirectory = System.getProperty("user.home")
                //File newFile = new File(homeDirectory+"/Projects/Testing/uploads/ikariera/companyGallery"+"/$newFilename")
                file.transferTo(newFile)
                //println "file transferred : " + "$storageDirectory/$newFilename"

                PictureMedia pictureMediaInstance = new PictureMedia(
                        originalFilename: file.originalFilename,
                        thumbnailFilename: file.originalFilename,
                        newFilename: newFilename,
                        name: file.originalFilename,
                        width: image.width,
                        height: image.height,
                        mediaType: "gallery",
                        position: 0,
                        fileSize: file.size
                )

                user.company.galleryPictures.add(pictureMediaInstance)

                if (!pictureMediaInstance.save(failOnError: true, flush: true)) {
                    flash.error = message(code: 'system.validation.error.message')
                    render(view: 'index', model: [userInstance: user])
                    return
                }
                user.save(flush: true)

/*
                Cv cv = new Cv(
                        originalFilename: file.originalFilename,
                        student: user.studentAccount,

                        newFilename: newFilename,
                        name: file.originalFilename,
                        fileSize: file.size
                ).save()*/

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
        redirect(action: "index")

    }

    def delete() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        def pictureMedia = PictureMedia.get(params.id)

        if (!pictureMedia) {
            notFound()
            return
        }

        try {

            File picFile = new File(grailsApplication.config.upload.directory.companyGallery + "/" + pictureMedia?.newFilename)
            boolean result = picFile.delete()

        } catch (Exception ex) {

            //flash.message = cv.errors
            redirect action: "index", method: "GET"
            return
        }
        pictureMedia.delete()
        user.company.galleryPictures.remove(pictureMedia)


        user.save(flush: true)
        flash.message = "Logo deleted"
        redirect action: "index", method: "GET"
    }
/*
    def deleteMain() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }



        if (!user.company.mainLogo) {
            notFound()
            return
        }


        def logo = user.company.mainLogo

        try {

            File picFile = new File(grailsApplication.config.upload.directory.companyMainLogo + "/ " + logo)
            picFile.delete()

        } catch (Exception ex) {

            //flash.message = cv.errors
            redirect action: "index", method: "GET"
            return
        }

        user.company.mainLogo = null


        user.save(flush: true)
        flash.message = "Logo deleted"
        redirect action: "index", method: "GET"
    }
*/

    protected void notFound() {

        flash.message = message(code: 'default.not.found.message')
        redirect action: "index", method: "GET"

    }


}