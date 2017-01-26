package cz.ikariera.admin

import cz.ikariera.admin.RemoteServer

class AdminRemoteServerController {

    def index() {

        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 10, 100)

        def list = RemoteServer.list(params)

        render(view: 'index', model: [serverList: list])
    }

    def create() {
        render(view: 'create', model: [remoteServerInstance: new RemoteServer()])
    }

    def save(RemoteServer remoteServer) {

        remoteServer.validate()

        if (remoteServer.hasErrors()) {
            render(view: 'create', model: [remoteServerInstance: remoteServer])
            return
        }

        if (!remoteServer.save(flush: true, failOnError: true)) {
            render(view: 'create', model: [remoteServerInstance: remoteServer])
            return
        }
        flash.message = 'Success'

        redirect(action: 'index')
    }

    def edit(RemoteServer remoteServer){
        render(view: 'edit', model: [remoteServerInstance: remoteServer])
    }

    def update(RemoteServer remoteServer){
        remoteServer.validate()

        if (remoteServer.hasErrors()) {
            render(view: 'create', model: [remoteServerInstance: remoteServer])
            return
        }

        if (!remoteServer.save(flush: true, failOnError: true)) {
            render(view: 'create', model: [remoteServerInstance: remoteServer])
            return
        }
        flash.message = 'Success'

        redirect(action: 'index')
    }

    def delete(RemoteServer remoteServer){

        remoteServer.delete(flush : true)

        redirect(action: 'index')
    }
}
