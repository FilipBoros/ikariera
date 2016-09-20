package ikariera.admin

import cz.ikariera.admin.RemoteRegister

class AdminRemoteRegisterController {

    def springSecurityService
    //def mailService
    def internalEmailService

    def index() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        redirect(action: "list", params: params)

    }


    def list() {

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.sort = params.sort ? params.sort : "date"
        params.order = params.order ? params.order : "desc"

        def remoteRegisterInstanceList = RemoteRegister.list(params)
        def listTotalCount = remoteRegisterInstanceList.totalCount
        def displayedResults = listTotalCount < params.max + params.offset ? listTotalCount - params.offset : params.max

        render(view: '/adminRemoteRegister/list',
                model: [
                        remoteRegisterInstanceList: remoteRegisterInstanceList,
                        listTotalCount: listTotalCount,
                        displayedResults: displayedResults
                ]
        )
    }


}
