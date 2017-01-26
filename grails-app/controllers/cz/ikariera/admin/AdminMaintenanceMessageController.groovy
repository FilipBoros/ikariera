package cz.ikariera.admin

import cz.ikariera.admin.MaintenanceMessage

class AdminMaintenanceMessageController {


    def index() {
        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 10, 100)

        def list = MaintenanceMessage.list(params)

        render(view: 'index', model: [messages: list])
    }

    def create(){
        render (view:'create', model: [ maintainMessage : new MaintenanceMessage()])
    }
    def edit(Long id){
        MaintenanceMessage msg = MaintenanceMessage.get(id)
        if (!msg) {
            redirect(action: 'index')
            return
        }

        render (view:'edit', model: [ maintainMessage : msg])
    }
    def save(MaintenanceMessage msg){
        msg.validate()
        if (msg.hasErrors()){
            flash.error = message(code: 'message.bad')
            render (view:'create', model: [ maintainMessage : msg])
            return
        }
        if (!msg.save(flush: true, failOnError: true)){
            flash.error = "Could not save message"
            render (view:'create', model: [ maintainMessage : msg])
            return
        }
        flash.message = message(code: 'message.saved')

        redirect(action: 'index')
    }
    def update(MaintenanceMessage msg){
        msg.validate()
        if (msg.hasErrors()){
            flash.error = "bad message"
            render (view:'edit', model: [ maintainMessage : msg])
            return
        }
        if (!msg.save(flush: true, failOnError: true)){
            flash.error = "Could not save message"
            render (view:'edit', model: [ maintainMessage : msg])
            return
        }
        flash.message = message(code: 'message.updated')

        redirect(action: 'index')
    }
    def delete(Long id){

        MaintenanceMessage msg = MaintenanceMessage.get(id)
        if (!msg) {
            redirect(action: 'index')
            return
        }

        msg.delete(flush: true)
        flash.message = message(code: 'message.deleted')

        redirect(action: 'index')
    }
}
