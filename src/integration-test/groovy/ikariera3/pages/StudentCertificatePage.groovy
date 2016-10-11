package ikariera3.pages

import geb.Page

/**
 * Created by michal on 31.8.2016.
 */
class StudentCertificatePage extends Page {
    static url = "/student-account-certificate"

    static at = {$('h2').text() == 'Certificates and courses'}

    static content = {}
}
