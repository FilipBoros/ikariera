package ikariera3.pages

import geb.Page

/**
 * Created by Michal Dolnak on 13.8.2016.
 */
class StudentPersonalDetailsPage extends Page{
    static url = 'student-account-personal-details'

    static at = {$('h2').text() == 'Personal data'}

    static content = {
        firstName {$("#firstName")}
        lastName {$("#lastName")}
        userName {$("#userName")}

        /*Kedze id elementov obsahuje bodku, tak je potrebne pouzit Geb Dsl a nestaci len JQuery*/
        telephone {$('input', id:'studentAccount.telephone')}
        nationality {$('input', id:'studentAccount.nationality')}
        streetAndAddress {$('input', id:'studentAccount.addressStreet')}
        city {$('input', id:'studentAccount.addressCity')}
        zip {$('input', id:'studentAccount.addressZip')}

        countrySelector {$('select', id:'studentAccount.addressCountry')}
        centralAfricaOption {$('select', id:'studentAccount.addressCountry').find('option').find {it.value() == 'caf'}}
        selectedCountry {countrySelector.find('option', selected:'selected')}
        /*selectedCountry TODO add selected option and assert it*/

        jobCatetogorySelector {$('#jobCategories_chosen')}
        architectureOption {$('ul.chosen-results').find('li').getAt(1) }
        eletricalEngineeringOption {$('ul.chosen-results').find('li').getAt(6)}

        chosenMainJobCategories {$('ul.chosen-choices').find('li.search-choice')}
        firstChosenMainJobCategory {chosenMainJobCategories.first()}
        lastChosenMainJobCategory {chosenMainJobCategories.last()}

        curriculumVitaePath {$('#curriculumVitaePath')}
        curriculumVitaeUpload {$('#curriculumVitaeUpload')}
        curriculumDeleteButton { $('a', class: 'button alert tiny').last() }

        fileupload {$('#fileupload')}
        profilePhotoDeleteButton { $('a', class: 'button alert tiny').first() }

        saveButton {$('button', class:'save long ')} // todo fix me nesaveju sa

        message {$('div.info-container')}
    }
}
