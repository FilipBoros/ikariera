package ikariera3.pages

import geb.Page

/**
 * Created by michal on 5.7.2016.
 */
class StudentAccountPage extends Page {

    static url = "student-account"

    static at = {$('div.right > a').first().text() == 'Personal profile' }

    static content = {
        personalProfile {$('div.right > a').first()}
    }
}
