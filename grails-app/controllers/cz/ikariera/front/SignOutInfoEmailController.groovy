package cz.ikariera.front

import cz.ikariera.security.User

class SignOutInfoEmailController {

    def springSecurityService



    def index() {

        render(view: "/signOutInfoEmail/index")
    }


    def signOut() {



        if (!params.username) {
            //flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label', default: 'CompanyEmails'), params.id])
            render(view: "/signOutInfoEmail/emailErrorNoEmail")
            //redirect(controller: "index")
            return

        }

        /*
             byte[] valueDecoded= Base64.decodeBase64(params.token);
             System.out.println("Decoded value is " + new String(valueDecoded));
             def username = new String(valueDecoded)
             */

        def user = User.findByUsername(params.username)

        if (!user) {

        //    flash.message = "username not found"
            render(view: "/signOutInfoEmail/emailErrorNoUserFound")
            //redirect(controller: "index")
            return
        }


        if (!user.studentAccount) {

            //    flash.message = "username not found"
            render(view: "/signOutInfoEmail/emailErrorNotStudent")
            //redirect(controller: "index")
            return
        }

        user.studentAccount.infoEmails = false


        user.validate()


        if (user.hasErrors()) {

            render(view: "/signOutInfoEmail/emailError")
            return
        }




        if (!user.save(flush: true)) {
            render(view: "/signOutInfoEmail/emailError")
            return
        }

        render(view: "/signOutInfoEmail/emailSuccess")
        //redirect(action: "emailLogout")
    }



}
