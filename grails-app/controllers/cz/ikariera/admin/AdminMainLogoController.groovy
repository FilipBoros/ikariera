package cz.ikariera.admin

import cz.ikariera.admin.MainLogo
import cz.ikariera.security.User
import cz.ikariera.upload.UploadFileService
import grails.web.context.ServletContextHolder
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile

class AdminMainLogoController {

    def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }


    
    def list() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        def list = MainLogo.list()

        def listSize = list.size()

        def criteria = MainLogo.createCriteria()

        def mainLogoList = criteria.listDistinct {
            maxResults(params.max)
            firstResult(params.offset)
        }

        render(view: "/adminMainLogo/list", model: [
                mainLogoInstanceList: mainLogoList,
                listInstanceTotal   : listSize,
                displayedResults    : listSize < params.max + params.offset ? listSize - params.offset : params.max
        ])
    }

    def create() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        render(view: "/adminMainLogo/create")//,  model: [bannerInstance: bannerInstance, companyInstance: company]
    }

    def save() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        Date endDate = params.endDate
        //println(endDate)
        MultipartFile f = request.getFile('myFile')
        if ((f.empty)) {
            flash.error = message(code: 'project.file.empty')
            render(view: "create")
            return
        }

        //get extension
        String extension = "";
        String fileName = f.originalFilename.toString()
        int i = fileName.lastIndexOf('.')
        if (i > 0) {
            extension = fileName.substring(i + 1)
        }

        extension = extension.toLowerCase()

        if (!(extension == 'png' || extension == 'jpg' || extension == 'bmp' || extension == 'gif')) {

            flash.error = message(code: 'not.supported.image.format')
            render(view: "/adminMainLogo/create")
            return
        }
        def currentDate = new Date()
        def formattedDate = g.formatDate(date: currentDate, format: 'yyyy-MMM-dd')

        def servletContext = ServletContextHolder.servletContext
        String serverPath = grailsApplication.config.upload.directory.adminMainLogo

        def logoPath = serverPath + File.separator
        new File(logoPath).mkdirs()

        def mainLogo = new MainLogo(params)


        if (!mainLogo.save(flush: true)) {
            render(view: "/adminMainLogo/create")
            return
        }

        mainLogo.name = formattedDate + "_" + mainLogo.id

        if (!mainLogo.save(flush: true)) {
            render(view: "/adminMainLogo/create")
            return
        }

        def fullOrigPath = logoPath + File.separator + mainLogo.name

        File origFile = new File(fullOrigPath)
        if (origFile)
            origFile.delete()

        UploadFileService uploadFileService = new UploadFileService()
        String storagePath = uploadFileService.uploadFile(f, mainLogo.name, logoPath)
        if (storagePath == null) {
            flash.error = message(code: 'project.file.empty')
            render(view: "/adminMainLogo/create")
            return
        }

        flash.message = message(code: 'logo.created.label') //change
        redirect(action: "list", params: params)
    }

    def deleteIt() {

        MainLogo logo = MainLogo.get(params.id)
        if (!logo) {
            flash.error = message(code: 'default.not.found.message', args: [message(code: 'banner.label', default: 'Banner'), params.id])
            redirect(action: "list")
            return
        }

        if (logo.publish) {
            flash.error = message(code: 'admin.uploadLogo.wrongDelete', args: [message(code: 'banner.label', default: 'Banner'), params.id])
            redirect(action: "list")
            return
        }

        try {
            String path = grailsApplication.config.upload.directory.adminMainLogo
            File f = new File(path + File.separator + logo?.name)
            f.delete()

            logo.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])
            redirect(action: "list")
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
        def logoList = MainLogo.getAll()
        logoList.each {
            it.publish = false;
            it.save();
        }

        MainLogo logo = MainLogo.get(params.id)

        if (!logo) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "list", params: params)
            return
        }

        logo.publish = true
        if (!logo.save(flush: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "list", params: params)
            return
        }
        flash.message = message(code: 'admin.uploadLogo.publishMessage')
        redirect(controller: "adminMainLogo", action: "list")

    }

    def unpublish() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        MainLogo logo = MainLogo.get(params.id)

        if (!logo) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "list", params: params)
            return
        }

        logo.publish = false
        if (!logo.save(flush: true)) {
            flash.error = message(code: 'system.unspecifiedError.error')
            redirect(action: "list", params: params)
            return
        }
        flash.message = message(code: 'admin.uploadLogo.publishMessage')
        redirect(controller: "adminMainLogo", action: "list")

    }

    def createDefaultLogo() {
        def logoList = MainLogo.getAll()
        logoList.each {
            it.publish = false;
            it.save();
        }
        def defaultLogo = new MainLogo()
        defaultLogo.name = "default.png"
        defaultLogo.publish = true
        defaultLogo.save(flush: true)
    }
}
