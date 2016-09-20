package cz.ikariera.admin

import cz.ikariera.admin.Constants

class AdminConstantsController {


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

        render(view: '/adminConstant/listConstant', model: [constantsInstanceList: Constants.list(), constantsInstanceTotal: Constants.count()])
    }

    def editConstant() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def constantsInstance = Constants.get(params.id)
        if (!constantsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'constantsModerator.label'), params.id])
            redirect(action: "list")
            return
        }
        render(view: '/adminConstant/editConstant', model: [constantsInstance: constantsInstance])
    }

    def updateConstant() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def constantsInstance = Constants.get(params.id)
        if (!constantsInstance) {
            flash.message = message(code: 'default.not.found2.message', args: [message(code: 'constantsModerator.label'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (constantsInstance.version > version) {
                render(view: "constant/editConstant", model: [constantsInstance: constantsInstance])
                return
            }
        }

        constantsInstance.properties = params
        if (constantsInstance.cmString == null) constantsInstance.cmString = ""
        if (constantsInstance.cmValue == null) constantsInstance.cmValue = 0

        if (!constantsInstance.save(flush: true)) {
            render(view: "constant/editConstant", model: [constantsInstance: constantsInstance])
            return
        }

        flash.message = message(code: 'default.updated2.message', args: [message(code: 'constantsModerator.label'), constantsInstance.id])
        redirect(controller: "admin", action: "listConstant")
    }


}
