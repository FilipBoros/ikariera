package cz.ikariera.company

import cz.ikariera.security.User
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class CompanyAccountLogoController {

    def springSecurityService


    def index() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }
        render(view: "index", model: [userInstance: user])

    }




    def upload() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")

        }

        if (request instanceof MultipartHttpServletRequest) {
            for (filename in request.getFileNames()) {
                MultipartFile file = request.getFile(filename)
                // println "file name : " + file.originalFilename

                if (file.empty) {
                    flash.error = message(code: 'company.logo.image.empty')
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }

                String newFilenameBase = UUID.randomUUID().toString()

                String originalFileExtension = ""
                if(file.originalFilename?.lastIndexOf(".")?.toInteger()>0)
                    originalFileExtension = file.originalFilename.substring(file.originalFilename?.lastIndexOf("."))



                originalFileExtension = originalFileExtension?.toLowerCase()

                byte[] fileBytes = file.bytes
                ByteArrayInputStream bytes = new ByteArrayInputStream(fileBytes)
                BufferedImage image = ImageIO.read(bytes)

                if (!image) {
                    flash.error = message(code: "company.logo.image.isNotImage")
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }

                //////
                if (image.width != 140) {
                    flash.error = message(code: "company.logo.image.width")
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }
                if (image.height != 75) {
                    flash.error = message(code: "company.logo.image.height")
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }


                if (file.size > 1000000) {
                    flash.error = message(code: 'student.cv.bigFile')
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }

                String newFilename = newFilenameBase + originalFileExtension

                String storageDirectory = grailsApplication.config.upload.directory.companyLogo
                File newFile = new File("$storageDirectory/$newFilename")
                file.transferTo(newFile)
                //println "file transferred : " + "$storageDirectory/$newFilename"

                user.company.logo = newFilename

            }
        }

        flash.message = message(code: 'default.created.message')
        redirect(action: "index", controller: "companyAccountProfile")

    }

    def uploadMain() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")

        }

        if (request instanceof MultipartHttpServletRequest) {
            for (filename in request.getFileNames()) {
                MultipartFile file = request.getFile(filename)
                if (file.empty) {
                    flash.error = message(code: 'company.main.image.empty')
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }

                String newFilenameBase = UUID.randomUUID().toString()

                String originalFileExtension = ""
                if(file.originalFilename?.lastIndexOf(".")?.toInteger()>0)
                    originalFileExtension = file.originalFilename.substring(file.originalFilename?.lastIndexOf("."))

                byte[] fileBytes = file.bytes
                ByteArrayInputStream bytes = new ByteArrayInputStream(fileBytes)
                BufferedImage image = ImageIO.read(bytes)

                if (!image) {
                    flash.error = message(code: "company.logo.image.isNotImage")
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }

                ////
                if(!image){

                    return
                }

                if (image.width != 1000) {
                    flash.error = message(code: "company.main.logo.image.width")
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }
                if (image.height != 400) {
                    flash.error = message(code: "company.main.logo.image.height")
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }


                originalFileExtension = originalFileExtension?.toLowerCase()

                //println(originalFileExtension)
                /*log.error(originalFileExtension)*/

                if (file.size > 1000000) {
                    flash.error = message(code: 'company.main.image.toobig')
                    redirect(controller: "companyAccountProfile" ,action: 'index', model: [userInstance: user])
                    return
                }


                String newFilename = newFilenameBase + originalFileExtension

                /*log.error(newFilename)*/

                String storageDirectory = grailsApplication.config.upload.directory.companyMainLogo
                File newFile = new File("$storageDirectory/$newFilename")
                file.transferTo(newFile)
                //println "file transferred : " + "$storageDirectory/$newFilename"

                user.company.mainLogo = newFilename

            }
        }

        flash.message = message(code: 'default.created.message')
        redirect(action: "index", controller: "companyAccountProfile")

    }

    def delete() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        if (!user.company.logo) {
            notFound()
            return
        }

        def logo = user.company.logo

        try {

            File picFile = new File(grailsApplication.config.upload.directory.companyLogo + File.separator + logo)
            picFile.delete()

        } catch (Exception ex) {

            //flash.message = cv.errors
            redirect action: "index", method: "GET"
            return
        }

        user.company.logo = null

        user.save(flush: true)
        flash.message = message(code: 'logo.deleted')
        redirect(action: "index", controller: "companyAccountProfile")
    }

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

            File picFile = new File(grailsApplication.config.upload.directory.companyMainLogo + File.separator + logo)
            picFile.delete()

        } catch (Exception ex) {

            redirect action: "index", method: "GET"
            return
        }

        user.company.mainLogo = null

        user.save(flush: true)
        flash.message = message(code: 'logo.deleted')
        redirect(action: "index", controller: "companyAccountProfile")
    }





    protected void notFound() {
        flash.message = message(code: 'default.not.found.message')
        redirect action: "index", method: "GET"
    }

}