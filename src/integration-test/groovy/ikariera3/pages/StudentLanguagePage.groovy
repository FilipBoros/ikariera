package ikariera3.pages

import geb.Page

/**
 * Created by michal on 31.8.2016.
 */
class StudentLanguagePage extends Page {
    static url = "/student-account-language"

    static at = {$('h2').text() == 'Languages'}

    static content = {}
}
