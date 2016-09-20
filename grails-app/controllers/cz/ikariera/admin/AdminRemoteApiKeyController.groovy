package ikariera.admin

import cz.ikariera.admin.RemoteApiKey
import grails.transaction.Transactional
import org.springframework.http.HttpStatus

@Transactional(readOnly = true)
class AdminRemoteApiKeyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RemoteApiKey.list(params), model: [remoteApiKeyInstanceCount: RemoteApiKey.count()]
    }

    def show(RemoteApiKey remoteApiKeyInstance) {
        respond remoteApiKeyInstance
    }

    def create() {
        respond new RemoteApiKey(params)
    }

    @Transactional
    def save(RemoteApiKey remoteApiKeyInstance) {
        if (remoteApiKeyInstance == null) {
            notFound()
            return
        }

        if (remoteApiKeyInstance.hasErrors()) {
            respond remoteApiKeyInstance.errors, view: 'create'
            return
        }

        remoteApiKeyInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'remoteApiKeyInstance.label', default: 'RemoteApiKey'), remoteApiKeyInstance.id])
                redirect (action: "index")
            }
            '*' { respond remoteApiKeyInstance, [status: HttpStatus.CREATED] }
        }
    }

    def edit(RemoteApiKey remoteApiKeyInstance) {
        respond remoteApiKeyInstance
    }

    @Transactional
    def update(RemoteApiKey remoteApiKeyInstance) {
        if (remoteApiKeyInstance == null) {
            notFound()
            return
        }

        if (remoteApiKeyInstance.hasErrors()) {
            respond remoteApiKeyInstance.errors, view: 'edit'
            return
        }

        remoteApiKeyInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'RemoteApiKey.label', default: 'RemoteApiKey'), remoteApiKeyInstance.id])
                redirect (action: "index")
            }
            '*' { respond remoteApiKeyInstance, [status: HttpStatus.OK] }
        }
    }

    @Transactional
    def delete(RemoteApiKey remoteApiKeyInstance) {

        if (remoteApiKeyInstance == null) {
            notFound()
            return
        }

        remoteApiKeyInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'RemoteApiKey.label', default: 'RemoteApiKey'), remoteApiKeyInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: HttpStatus.NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'remoteApiKeyInstance.label', default: 'RemoteApiKey'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: HttpStatus.NOT_FOUND }
        }
    }
}
