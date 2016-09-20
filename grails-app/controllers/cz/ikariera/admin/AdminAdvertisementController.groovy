package cz.ikariera.admin

import cz.ikariera.admin.Advertisement
import cz.ikariera.company.Services
import grails.web.context.ServletContextHolder
import org.springframework.web.multipart.MultipartFile

class AdminAdvertisementController {

    def springSecurityService

    def index() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout", action: "index")
            return
        }
        redirect(action: "list")
    }


    def list() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout", action: "index")
            return
        }
        render(view: "/adminAdvertisement/list", model: [advertisementInstanceList: Advertisement.list(), advertisementInstance: Advertisement.count()])
    }

    def edit() {
        def advertisementInstance = Advertisement.get(params.id)
        if (!advertisementInstance) {
            flash.message = "not found"
            redirect(action: "index")
            return
        }
        render(view: "/adminAdvertisement/edit", model: [advertisementInstance: advertisementInstance])
    }


    def getLogo() {

        String uploadDirectory = grailsApplication.config.upload.directory.advertisement

        def advInstance = Advertisement.get(params.id)
        try {

            def picFile = new File(advInstance.directory + "/" + advInstance.file)

            response.addHeader("Cache-Control", "no-store");

            // response.contentType = 'pdf/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }

    def update() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout", action: "index")
            return
        }

        //def advInstance = new Advertisement(params)
        def advInstance = Advertisement.get(params.id)
        advInstance.header = params.header
        advInstance.urlLink = params.urlLink
        MultipartFile f = request.getFile('myFile')
        if (!f.empty) {

            //zjisteni koncovky
            String extension = "";
            String fileName = f.originalFilename.toString()
            int i = fileName.lastIndexOf('.')
            if (i > 0) {
                extension = fileName.substring(i + 1)
            }

            extension = extension.toLowerCase()

            if (!(extension == 'png' || extension == 'jpg' || extension == 'jpeg' || extension == 'bmp')) {

                flash.message = message(code: 'not.supported.image.format')
                render(view: "/adminBanner/create", model: [advInstance: advInstance, returnAddress: params.returnAddress, returnPath: params.returnPath])
                return
            }

            advInstance.file = "advertisement" + java.util.UUID.randomUUID().toString() + "." + extension;

            def currentDate = new Date()
            def formattedDate = g.formatDate(date: currentDate, format: 'yyyy-MMM-dd')

            def servletContext = ServletContextHolder.servletContext
            //String serverPath = servletContext.getRealPath('admin') //udelat novou slozku pro bannery
            String serverPath = grailsApplication.config.upload.directory.advertisement

            def bannerPath = serverPath //+ File.separator + "advertisement"

            advInstance.directory = serverPath

            new File(bannerPath).mkdirs()

            def fullOrigPath = bannerPath + File.separator + advInstance.file
            f.transferTo(new File(fullOrigPath))
        }

        if (!advInstance.save()) {
            render(view: "/adminAdvertisement/edit", model: [advInstance: advInstance, returnAddress: params.returnAddress, returnPath: params.returnPath])
            return
        }

        flash.message = message(code: 'advertisement.created.message', args: [advInstance.header])
        redirect(action: "list")

    }


    def create() {
        render(view: "/adminAdvertisement/create", model: [advertisementInstance: new Advertisement()])
    }


    def save() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout", action: "index")
            return
        }

        def advInstance = new Advertisement(params)


        MultipartFile f = request.getFile('myFile')
        if (f.empty) {
            flash.message = message(code: 'project.file.empty')
            render(view: "/adminAdvertisement/create", model: [advInstance: advInstance, returnAddress: params.returnAddress, returnPath: params.returnPath])
            return
        }

        //zjisteni koncovky
        String extension = "";
        String fileName = f.originalFilename.toString()
        int i = fileName.lastIndexOf('.')
        if (i > 0) {
            extension = fileName.substring(i + 1)
        }

        extension = extension.toLowerCase()

        if (!(extension == 'png' || extension == 'jpg' || extension == 'jpeg' || extension == 'bmp')) {

            flash.message = message(code: 'not.supported.image.format')
            render(view: "/adminBanner/create", model: [advInstance: advInstance, returnAddress: params.returnAddress, returnPath: params.returnPath])
            return
        }

        advInstance.file = java.util.UUID.randomUUID().toString() + "." + extension;

        def currentDate = new Date()
        def formattedDate = g.formatDate(date: currentDate, format: 'yyyy-MMM-dd')

        def servletContext = ServletContextHolder.servletContext
        //String serverPath = servletContext.getRealPath('admin') //udelat novou slozku pro bannery
        String serverPath = grailsApplication.config.upload.directory.advertisement

        def bannerPath = serverPath //+ File.separator + "advertisement"

        advInstance.directory = serverPath

        new File(bannerPath).mkdirs()

        def fullOrigPath = bannerPath + File.separator + advInstance.file
        f.transferTo(new File(fullOrigPath))

        if (!advInstance.save()) {
            render(view: "/adminAdvertisement/create", model: [advInstance: advInstance, returnAddress: params.returnAddress, returnPath: params.returnPath])
            return
        }

        flash.message = message(code: 'advertisement.created.message', args: [advInstance.header])
        redirect(action: "list")
    }


    def publish() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout", action: "index")
            return
        }

        Advertisement.withTransaction { status ->
            def oldAdv = Advertisement.findByDateExpireIsNotNull()
            if (oldAdv) {
                oldAdv.dateExpire = null
                if (!oldAdv.save(flush: true)) {
                    flash.err = message(code: 'system.unspecifiedError.error')
                    status.setRollbackOnly()
                    redirect(action: "list")
                    return
                }
            }

            def newAdv = Advertisement.get(params.id)
            newAdv.dateExpire = new Date() + Services.findByUniqueName("admin-services-jobOffer").defaultExpirationTime

            if (!newAdv.save(flush: true)) {
                flash.err = message(code: 'system.unspecifiedError.error')
                status.setRollbackOnly()
                redirect(action: "list")
                return
            }

            flash.message = message(code: 'advertisement.published.message', args: [newAdv.header])
            redirect(action: "list")
        }
    }


    def depublish() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout", action: "index")
            return
        }

        Advertisement.withTransaction { status ->
            def newAdv = Advertisement.get(params.id)
            newAdv.dateExpire = null

            if (!newAdv.save(flush: true)) {
                flash.err = message(code: 'system.unspecifiedError.error')
                status.setRollbackOnly()
                redirect(action: "list")
                return
            }

            flash.message = message(code: 'advertisement.depublished.message', args: [newAdv.header])
            redirect(action: "list")
        }


    }
}
