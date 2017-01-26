package cz.ikariera.admin

import cz.ikariera.company.Services

class AdminServicesController {

    def springSecurityService


    def index() {
        render(view: 'index', model: [servicesInstanceList: Services.list(params), servicesInstanceTotal: Services.count()])
    }


    def edit(Services serviceInstance) {

        if (!serviceInstance) {
            flash.message = message(code: 'default.not.found2.message', args: [message(code: 'constantsModerator.label'), params.id])
            redirect(action: "index")
            return
        }
        render(view: 'edit', model: [serviceInstance: serviceInstance])
    }


    def update(Services serviceInstance) {

        if (!serviceInstance) {
            flash.message = message(code: 'default.not.found2.message', args: [message(code: 'constantsModerator.label'), params.id])
            redirect(action: "index")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (serviceInstance.version > version) {
                render(view: "edit", model: [serviceInstance: serviceInstance])
                return
            }
        }

        //workaround for decimal
        params.creditPrice = params.creditPrice.replace(".", ",")
        serviceInstance.properties = params

        if (!serviceInstance.save(flush: true)) {
            render(view: "edit", model: [serviceInstance: serviceInstance])
            return
        }

        flash.message = message(code: 'default.updated2.message', args: [message(code: 'constantsModerator.label'), serviceInstance.id])
        redirect(action: "index")
    }


}
