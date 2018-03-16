package ikariera3.pages

import geb.Page

class StudentSkillPage extends Page {
    static url = "/student-account-skill"

    static at = {$('h2').text() == 'Skills'}

    static content = {}
}
