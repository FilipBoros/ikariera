package ikariera.admin

import cz.ikariera.company.HeroImage
import cz.ikariera.security.User
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class AdminHeroImageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService


    def index() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)

        def heroImageList = HeroImage.createCriteria().list(max: max, offset: offset) {

        }

        def heroImageListTotal = HeroImage.count

        render(view: "index", model: [
                heroImageList    : heroImageList,
                listInstanceTotal: heroImageListTotal
        ])
    }

    def hero() {
        render(view: "hero", model: [mediaType: "hero"])
    }

    def uploadHero() {
        if (request instanceof MultipartHttpServletRequest) {

            MultipartFile file = request.getFile('file')

            String newFilenameBase = UUID.randomUUID().toString()

            def okContentTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif']

            if (!okContentTypes.contains(file.getContentType())) {
                flash.error = message(code: 'system.validation.error.message')
                render(view: "hero")
                return
            }

            byte[] fileBytes = file.bytes
            ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes)
            BufferedImage image = ImageIO.read(bais)

            //println(image.width + " " + image.height)

            if (!(image.width >= 1600 && image.height >= 700)) {
                flash.error = message(code: "maximal.size.of.media", args: [1600, 700])
                render(view: "hero")
                return
            }

            String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
            String newFilename = newFilenameBase + originalFileExtension

            String storageDirectory = grailsApplication.config.upload.directory.hero

            File newFile = new File("$storageDirectory/$newFilename")
            file.transferTo(newFile)

            HeroImage heroImageInstance = new HeroImage(
                    name: file.originalFilename,
                    publish: false,

                    thumbnailLink: newFilename,
                    imageLink: newFilename
            )

            if (!heroImageInstance.save(failOnError: true, flush: true)) {
                flash.error = message(code: 'system.validation.error.message')
                render(view: "hero", model: [heroImageInstance: heroImageInstance])
                return
            }

            flash.message = message(code: 'admin.picture.media.saved')
            redirect(controller: "adminHeroImage")

        }

    }

    def deleteIt() {

        def logo = HeroImage.get(params.id)
        if (!logo) {
            flash.error = message(code: 'default.not.found.message', args: [message(code: 'banner.label', default: 'Banner'), params.id])
            redirect(action: "index")
            return
        }

        if (logo.name == "default.png") {
            flash.error = message(code: 'admin.uploadLogo.defaultLogo.deleteLogo')
            redirect(action: "index")
            return
        }

        try {
            String path = servletContext.getRealPath('images')
            File f = new File(path + File.separator + "heroImageIkariera" + File.separator + logo?.name)
            f.delete()
            logo.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.error = message(code: 'default.not.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def publish() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def heroImage = HeroImage.get(params.id)
        if (!heroImage) {
            render(view: "index")
            return
        }

        heroImage.publish = true
        if (!heroImage.save(flush: true)) {

            flash.error = message(code: 'system.unspecifiedError.error')
            render(view: "index")
            return
        }

        flash.message = message(code: 'admin.uploadLogo.publishMessage')
        redirect(controller: "adminHeroImage", action: "index")
    }

    def edit() {
        def heroImageInstance = HeroImage.get(params.id)
        if (!heroImageInstance) {
            flash.message = message(code: 'not.found.message')
            redirect(action: "index")
            return
        }

        render(view: '/adminHeroImage/edit', model: [heroImageInstance: heroImageInstance])
    }

    def getMedia() {

        String uploadDirectory = grailsApplication.config.upload.directory.hero



        try {

            def picFile = new File(uploadDirectory + "/" + params.id)

            // if (!picFile.exists() && picFile.canRead()) {

            //     redirect(controller: "error", action: "notFound")

            // } else {

            response.addHeader("Cache-Control", "no-store");

            response.contentType = 'image/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()
            // }
        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }

    def update() {

        def heroImageInstance = HeroImage.get(params.id)

        if (!heroImageInstance) {
            flash.message = message(code: 'not.found.message')
            redirect(action: "index")
            return
        }

        heroImageInstance.countries.clear()
        heroImageInstance.jobCategories.clear()
        heroImageInstance.localities.clear()

        if (params.version) {
            def version = params.version.toLong()
            if (heroImageInstance.version > version) {
                heroImageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'heroImage.name')] as Object[],
                        "Another user has updated this Faq while you were editing")
                render(view: "/adminHeroImage/edit", model: [heroImageInstance: heroImageInstance])
                return
            }
        }

        bindData(heroImageInstance, params)

        if (!heroImageInstance.save(flush: true)) {
            render(view: "/adminHeroImage/edit", model: [heroImageInstance: heroImageInstance])
            return
        }

        flash.message = message(code: 'updated.message')
        redirect(action: "index", id: heroImageInstance.id)
    }


    def unPublish() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def heroImageInstance = HeroImage.get(params.id)
        if (!heroImageInstance) {
            render(view: "index")
            return
        }

        heroImageInstance.publish = false
        if (!heroImageInstance.save(flush: true)) {

            flash.error = message(code: 'system.unspecifiedError.error')
            render(view: "index")
            return
        }

        flash.message = message(code: 'admin.hero.unpublishMessage')
        redirect(controller: "adminHeroImage", action: "index")

    }

}
