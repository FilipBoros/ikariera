package cz.ikariera.admin

import org.springframework.dao.DataIntegrityViolationException

class AdminFaqController {

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
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        render(view: '/adminFaq/list', model: [faqInstanceList: Faq.list(params), faqInstanceTotal: Faq.count()])
    }


    def create() {
        render(view: '/adminFaq/create', model: [faqInstance: new Faq(params)])
    }


    def save() {
        def faqInstance = new Faq(params)
        if (!faqInstance.save(flush: true)) {
            render(view: "/adminFaq/create", model: [faqInstance: faqInstance])
            return
        }
        flash.message = message(code: 'faq.created')
        redirect(action: "list", id: faqInstance.id)
    }


    def show() {
        def faqInstance = Faq.get(params.id)
        if (!faqInstance) {
            flash.message = message(code: 'faq.not.found.message')
            redirect(action: "list")
            return
        }

        render(view: '/adminFaq/show', model: [faqInstance: faqInstance])

    }


    def edit() {
        def faqInstance = Faq.get(params.id)
        if (!faqInstance) {
            flash.message = message(code: 'faq.not.found.message')
            redirect(action: "list")
            return
        }

        render(view: '/adminFaq/edit', model: [faqInstance: faqInstance])
    }


    def update() {
        def faqInstance = Faq.get(params.id)
        if (!faqInstance) {
            flash.message = message(code: 'faq.not.found.message')
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (faqInstance.version > version) {
                faqInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'faq.name')] as Object[],
                        "Another user has updated this Faq while you were editing")
                render(view: "/adminFaq/edit", model: [faqInstance: faqInstance])
                return
            }
        }

        faqInstance.properties = params

        if (!faqInstance.save(flush: true)) {
            render(view: "/adminFaq/edit", model: [faqInstance: faqInstance])
            return
        }

        flash.message = message(code: 'faq.updated.message')
        redirect(action: "list", id: faqInstance.id)
    }


    def delete() {
        def faqInstance = Faq.get(params.id)
        if (!faqInstance) {
            flash.message = message(code: 'faq.not.found.message')
            redirect(action: "list")
            return
        }

        try {
            faqInstance.delete(flush: true)
            flash.message = message(code: 'faq.deleted.message')
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'faq.not.deleted.message')
            redirect(action: "show", id: params.id)
        }
    }


}
