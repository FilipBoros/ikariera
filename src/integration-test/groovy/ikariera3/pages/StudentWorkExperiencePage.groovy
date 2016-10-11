package ikariera3.pages

import geb.Page

/**
 * Created by michal on 31.8.2016.
 */
class StudentWorkExperiencePage extends Page {
    static url = "/student-account-experience"

    static at = {$('h2').text() == 'Work experiences'}

    static content = {}
}
