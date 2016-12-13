package ikariera.admin

import cz.ikariera.admin.PublishSetting
import cz.ikariera.security.User
import org.springframework.dao.DataIntegrityViolationException

class AdminPublishServiceController {

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


        def heroImageList = PublishSetting.createCriteria().list(max: max, offset: offset) {

        }

        def heroImageListTotal = PublishSetting.count

        render(view: "index", model: [
                heroImageList    : heroImageList,
                listInstanceTotal: heroImageListTotal
        ])
    }

    def create() {
        render(view: '/adminPublishService/create', model: [faqInstance: new PublishSetting(params)])
    }

    def save() {
        def faqInstance = new PublishSetting(params)
        if (!faqInstance.save(flush: true)) {
            render(view: "/adminFaq/create", model: [faqInstance: faqInstance])
            return
        }
        flash.message = "Publish service created"
        redirect(action: "index", id: faqInstance.id)
    }

    def deleteIt() {

        def logo = PublishSetting.get(params.id)
        if (!logo) {
            flash.error = message(code: 'default.not.found.message', args: [message(code: 'banner.label', default: 'Banner'), params.id])
            redirect(action: "index")
            return
        }


        try {
            logo.delete(flush: true)
            flash.message = "Publish service deleted"
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.error = message(code: 'default.not.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def edit() {
        def heroImageInstance = PublishSetting.get(params.id)
        if (!heroImageInstance) {
            flash.message = message(code: 'not.found.message')
            redirect(action: "index")
            return
        }

        render(view: '/adminPublishService/edit', model: [heroImageInstance: heroImageInstance])
    }

    def update() {

        def heroImageInstance = PublishSetting.get(params.id)

        if (!heroImageInstance) {
            flash.message = message(code: 'not.found.message')
            redirect(action: "index")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (heroImageInstance.version > version) {
                heroImageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'heroImage.name')] as Object[],
                        "Another user has updated this Faq while you were editing")
                render(view: "/adminPublishService/edit", model: [heroImageInstance: heroImageInstance])
                return
            }
        }

        bindData(heroImageInstance, params)

        if (!heroImageInstance.save(flush: true)) {
            render(view: "/adminPublishService/edit", model: [heroImageInstance: heroImageInstance])
            return
        }

        flash.message = message(code: 'updated.message')
        redirect(action: "index", id: heroImageInstance.id)
    }
}
