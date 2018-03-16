package cz.ikariera.student

import cz.ikariera.security.User
import org.springframework.web.servlet.support.RequestContextUtils

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

        def locale = RequestContextUtils.getLocale(request).toString()
        User user = springSecurityService.getCurrentUser()
        pdfService.generateCV(user.studentAccount, 2, locale) // TODO change first parameter
        return redirect(controller: "studentAccountPersonalDetails")
    }
}
