package ikariera.student

import cz.ikariera.security.User

/**
 * StudentController provides several methods how to manage student account that already exists.
 * It uses springSecurityService.
 */

class StudentAccountController {
    def springSecurityService
    /**
     By default if user is logged in it displays edit form to account. If user is not logged is redirected to log form.
     */

    def index() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        render(view: "index")
    }















}
