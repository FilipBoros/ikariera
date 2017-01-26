package cz.ikariera.admin

import cz.ikariera.admin.ApiKey
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdminSimpleRegistrationController {



  
    def index() {



        def apiKeyInstance = ApiKey.findByServiceNameAndExpireGreaterThanEquals("SimpleRegistration", new Date())

        println(apiKeyInstance)

        if(!apiKeyInstance) {
            flash.message = 'Simple Registration must me activated from Api keys first.'
            redirect(controller: "adminApiKey")
        }

        redirect( controller: "simpleRegistration" , id: apiKeyInstance.value)
    }

    
    
}
