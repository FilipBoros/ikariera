package ikariera.admin

import cz.ikariera.admin.Partner
import grails.transaction.Transactional
import org.springframework.http.HttpStatus

@Transactional(readOnly = true)
class AdminPartnerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Partner.list(params), model: [partnerInstanceCount: Partner.count()]
    }

    def show(Partner partnerInstance) {
        respond partnerInstance
    }

    def create() {
        respond new Partner(params)
    }

    @Transactional
    def save(Partner partnerInstance) {
        if (partnerInstance == null) {
            notFound()
            return
        }

        if (partnerInstance.hasErrors()) {
            respond partnerInstance.errors, view: 'create'
            return
        }

        partnerInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'partnerInstance.label', default: 'Partner'), partnerInstance.id])
                redirect action: "index", controller: "adminPartner"
            }
            '*' { respond partnerInstance, [status: HttpStatus.CREATED] }
        }
    }

    def edit(Partner partnerInstance) {
        respond partnerInstance
    }

    @Transactional
    def update(Partner partnerInstance) {
        if (partnerInstance == null) {
            notFound()
            return
        }

        if (partnerInstance.hasErrors()) {
            respond partnerInstance.errors, view: 'edit'
            return
        }

        partnerInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Partner.label', default: 'Partner'), partnerInstance.id])
                redirect action: "index", controller: "adminPartner"
            }
            '*' { respond partnerInstance, [status: HttpStatus.OK] }
        }
    }

    @Transactional
    def delete(Partner partnerInstance) {

        if (partnerInstance == null) {
            notFound()
            return
        }

        partnerInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Partner.label', default: 'Partner'), partnerInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: HttpStatus.NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'partnerInstance.label', default: 'Partner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: HttpStatus.NOT_FOUND }
        }
    }
}