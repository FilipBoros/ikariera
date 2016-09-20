package cz.ikariera.admin

import cz.ikariera.admin.Banner
import org.springframework.web.multipart.MultipartFile

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class AdminBannerController {

    def springSecurityService

    def index() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        redirect(action: "list", params: params)
    }


    def list() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        render(view: "/adminBanner/list", model: [bannerInstanceList: Banner.list(params), bannerInstanceTotal: Banner.count(), params: params])
    }


    def create() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def bannerInstance = new Banner()

        render(view: "/adminBanner/create", model: [bannerInstance: bannerInstance])
    }

    private def validateImage(Banner bannerInstance, def request) {
        MultipartFile file = request.getFile('file')

        String newFilenameBase = UUID.randomUUID().toString()

        def okContentTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif']

        if (!okContentTypes.contains(file.getContentType())) {
            flash.error = message(code: 'not.supported.image.format')
            return false
        }

        byte[] fileBytes = file.bytes
        ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes)
        BufferedImage image = ImageIO.read(bais)

        if ((image.width != 720 && image.height != 90)) {
            flash.error = message(code: "banner.size.of.media", args: [720, 90])
            return false
        }

        String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
        String newFilename = newFilenameBase + originalFileExtension

        String storageDirectory = grailsApplication.config.upload.directory.mediaFile

        File newFile = new File("$storageDirectory/$newFilename")

        file.transferTo(newFile)
        bannerInstance.newFilename = newFilename
        bannerInstance.originalFilename = file.originalFilename
        return true
    }

    def save() {

        def user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "logout")
            return
        }

        Banner bannerInstance = new Banner(params)

        if (!validateImage(bannerInstance, request)) {
            render(view: "create", model: [bannerInstance: bannerInstance])
            return
        }

        bannerInstance.position = 0

        bannerInstance.rotation = 0

        if (!bannerInstance.validate()) {
            render(view: "/adminBanner/create", model: [bannerInstance: bannerInstance])
            return
        }
        if (!bannerInstance.save(flush: true, failOnError: true)) {
            render(view: "/adminBanner/create", model: [bannerInstance: bannerInstance])
            return
        }

        Banner.executeUpdate("update Banner b set b.rotation=0")

        flash.message = message(code: 'banner.created.label')

        redirect(action: 'list')
    }

    def edit() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        Banner bannerInstance = Banner.get(params.id)
        if (!bannerInstance) {
            render(view: "/adminBanner/list")
            return
        }

        render(view: '/adminBanner/edit', model: [bannerInstance: bannerInstance])
    }


    def update() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def bannerInstance = Banner.get(params.id)

        MultipartFile file = request.getFile('file')

        if (file.size > 0 && !validateImage(bannerInstance, request)) {
            render(view: "/adminBanner/edit", model: [bannerInstance: bannerInstance])
            return
        }

        if (!bannerInstance) {
            render(view: "/adminBanner/list")
            return
        }

        bannerInstance.properties = params

        if (!bannerInstance.save(flush: true)) {
            render(view: "/adminBanner/edit", model: [bannerInstance: bannerInstance])
            return
        }

        flash.message = message(code: 'admin.banner.bannerUpdate', args: [bannerInstance.id])

        redirect(controller: "adminBanner", action: "list")
    }

    def delete(Banner banner) {
        if (banner == null) {
            redirect(controller: 'error', action: 'notFound')
            return
        }
        banner.delete(flush: true)
        redirect(view: 'list')
    }
}
