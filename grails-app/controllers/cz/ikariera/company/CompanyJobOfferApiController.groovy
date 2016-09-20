package ikariera.company

import cz.ikariera.security.User
import cz.ikariera.service.CompanyServicesService

class CompanyJobOfferApiController {

    def springSecurityService

    def index() {
        User user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "login")
            return
        }

        def company = user.company
        def now = new Date()


        String uniqueName = "jobofferapi"
        if (!CompanyServicesService.isActivated(uniqueName, company)) {

            redirect(controller: "companyAccountServices", action: "activation", id: uniqueName)
            return
        }

              /*
        // service epired
        if (company?.servicesExpire?.apiActiveTill?.before(now) ){
            redirect(controller: 'companyAccountServices', action: 'activation', params: [id:'jobofferapi'])
        }   */

        render view : 'index', model : [companyInstance : company]
    }

    def generateNewApiKey(){
        User user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "login")
            return
        }

        def company = user.company

        company?.APIKEY = springSecurityService.encodePassword(company.companyName, user.username + new Date())

        if (!company.save(flush : true, failOnError: true)){
            flash.error = "Error happend"
            render view : 'index', model : [companyInstance : company]
            return
        }

        flash.message = "Api key generated"
        render view : 'index', model : [companyInstance : company]
    }
}
