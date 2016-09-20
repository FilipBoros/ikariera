package cz.ikariera.admin

import cz.ikariera.admin.ApiKey


class AdminApiKeyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index() {

        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 10, 100)

        def apiKeyInstanceList = ApiKey.list(params)

        render(view: 'index', model: [apiKeyInstanceList: apiKeyInstanceList])
    }

    def create() {
        render(view: 'create', model: [apiKeyInstance: new ApiKey()])
    }

    def save(ApiKey apiKeyInstance) {

        apiKeyInstance.validate()

        if (apiKeyInstance.hasErrors()) {
            render(view: 'create', model: [apiKeyInstance: apiKeyInstance])
            return
        }

        if (!apiKeyInstance.save(flush: true, failOnError: true)) {
            render(view: 'create', model: [apiKeyInstance: apiKeyInstance])
            return
        }
        flash.message = 'Success'

        redirect(action: 'index')
    }

    def edit(ApiKey apiKeyInstance){
        render(view: 'edit', model: [apiKeyInstance: apiKeyInstance])
    }

    def update(ApiKey apiKeyInstance){
        apiKeyInstance.validate()

        if (apiKeyInstance.hasErrors()) {
            render(view: 'create', model: [apiKeyInstance: apiKeyInstance])
            return
        }

        if (!apiKeyInstance.save(flush: true, failOnError: true)) {
            render(view: 'create', model: [apiKeyInstance: apiKeyInstance])
            return
        }
        flash.message = 'Success'

        redirect(action: 'index')
    }

    def delete(ApiKey apiKeyInstance){

        apiKeyInstance.delete(flush : true)

        redirect(action: 'index')
    }
    
    
}
