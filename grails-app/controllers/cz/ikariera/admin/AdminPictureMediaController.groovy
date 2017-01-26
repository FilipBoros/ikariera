package cz.ikariera.admin

import cz.ikariera.admin.PictureMedia
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class AdminPictureMediaController {


    def index() {
        render(view: "/adminPictureMedia/picture", model: [mediaType: "picture"])
    }

    def logo() {
        render(view: "/adminPictureMedia/logo", model: [mediaType: "logo"])
    }

    def uploadLogo() {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile logo = request.getFile('logo')

            String newFilenameBase = UUID.randomUUID().toString()

            def okContentTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif']

            if (!okContentTypes.contains(logo.getContentType())) {
                flash.error = message(code: 'system.validation.error.message')
                render(view: "/adminPictureMedia/logo")
                return
            }

            byte[] fileBytes = logo.bytes
            ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes)
            BufferedImage image = ImageIO.read(bais)

            if (!(image.width <= 150 && image.height <= 150)) {
                flash.error = message(code: "maximal.size.of.logo", args: [150, 150])
                render(view: "/adminPictureMedia/logo")
                return
            }

            String fileExtension = logo.originalFilename.substring(logo.originalFilename.lastIndexOf("."))
            String newFilename = newFilenameBase + "thumb" + fileExtension

            String storageDirectory = grailsApplication.config.upload.directory.mediaFile

            File newFile = new File("$storageDirectory/$newFilename")
            logo.transferTo(newFile)


            PictureMedia pictureMediaInstance = new PictureMedia(
                    originalFilename: logo.originalFilename,
                    thumbnailFilename: newFilename,
                    newFilename: newFilename,
                    name: logo.originalFilename,
                    width: image.width,
                    height: image.height,
                    mediaType: "logo",
                    position: 0,
                    fileSize: logo.size
            )


            if (!pictureMediaInstance.save(failOnError: true, flush: true)) {
                flash.error = message(code: 'system.validation.error.message')
                render(view: "/adminPictureMedia/logo", model: [pictureMediaInstance: pictureMediaInstance])
                return
            }


            flash.message = message(code: 'admin.picture.media.saved')
            redirect(controller: "adminMedia")
        }
    }

}