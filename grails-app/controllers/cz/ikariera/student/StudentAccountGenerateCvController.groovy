package ikariera.student

import cz.ikariera.security.User

/**
 * Controller to generate student cv using itextpdf library
 *
 * @author Michal Dolnak
 * @since 26.08.2016
*/
class StudentAccountGenerateCvController {

    def pdfService

    def springSecurityService

    def index() {
        User user = springSecurityService.getCurrentUser()
        pdfService.generateCV(user.studentAccount) // TODO change first parameter
        return redirect(controller: "studentAccountPersonalDetails")
    }
}
