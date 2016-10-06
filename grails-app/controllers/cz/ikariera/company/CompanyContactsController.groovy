package cz.ikariera.company

import cz.ikariera.company.ContactDetails
import cz.ikariera.company.JobOffer
import cz.ikariera.security.User

class CompanyContactsController {

    def springSecurityService

    def index() {
        redirect( action: "list")
    }

    def list(){
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        def contactsList = ContactDetails.createCriteria().list(params) {
            eq("company", user.company)
        }

        def contactsInstanceListTotal = contactsList?.size()

        render(view: "index",
                model: [
                        contactsList: contactsList,
                        contactsTotal: contactsInstanceListTotal,
                        companyInstance: user?.company,

                ],
                params: params)
    }

    def create(){
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        render(view: 'create', model: [contactDetailInstance: new ContactDetails()])
    }

    def edit(){
        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        ContactDetails contactDetails = ContactDetails.get(params.id)
        render (view : 'edit' , model: [contactDetailInstance: contactDetails])
    }

    def save(ContactDetails contactDetailsInstance){

        User user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "logout")
            return
        }

        contactDetailsInstance.company = user.company


        if (!contactDetailsInstance.validate()){
            render (view : 'create', model:[contactInstance:contactDetailsInstance])
            return
        }
        /*contactDetailsInstance.validate()
        if (contactDetailsInstance.hasErrors()){
            render (view : 'create', model:[contactInstance:contactDetailsInstance])
            return
        }*/
        if (!contactDetailsInstance.save(flush: true)){
            render (view : 'create', model:[contactInstance:contactDetailsInstance])
            return
        }

        flash.message = message(code: 'company.contact.created')

        redirect(action: 'list')
    }

    def update(ContactDetails contactDetailsInstance){
        User user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "logout")
            return
        }

        contactDetailsInstance.company = user.company

        contactDetailsInstance.validate()
        if (contactDetailsInstance.hasErrors()){
            render (view : 'edit', model:[contactInstance:contactDetailsInstance])
            return
        }
        if (!contactDetailsInstance.save(flush: true, failOnError: true)){
            render (view : 'edit', model:[contactInstance:contactDetailsInstance])
            return
        }

        flash.message = message(code: 'company.contact.updated')

        redirect(action: 'list')
    }
    def delete (ContactDetails contactDetailsInstance){
        User user = springSecurityService.getCurrentUser() as User

        if (!user) {
            redirect(controller: "logout")
            return
        }
        def list = JobOffer.createCriteria().list { eq('contactDetails', contactDetailsInstance)}
        list.each {
            it.contactDetails = null
            it.save()
        }

        contactDetailsInstance.delete(flush : true)

        flash.message = message(code: 'company.contact.deleted')
        redirect(action: 'list')
    }
}
