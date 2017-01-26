package cz.ikariera.admin

import cz.ikariera.admin.CreditsReqests
import cz.ikariera.company.CompanyFeedBack
import cz.ikariera.security.User

class AdminFeedBackController {

    def springSecurityService


    def index() {

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        params.sort= "dateCreated"
        params.order= "desc"
        def feedBackList = CompanyFeedBack.list(params)

        render view: 'index', model: [feedBacklist : feedBackList, feedbackcount : CompanyFeedBack.count()]
    }

    def show (CompanyFeedBack feedBack){

        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }

        render view : 'show', model : [feedback : feedBack]
    }
    def listCreditsRequire(){
        User user = springSecurityService.getCurrentUser()

        if (!user) {
            redirect(controller: "login")
        }
        params.sort= "requestDate"
        params.order= "desc"

        render view : 'list', model : [creditRequests: CreditsReqests.list(params), requestCount: CreditsReqests.count]
    }
}
