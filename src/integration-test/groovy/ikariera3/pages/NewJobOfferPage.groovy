package ikariera3.pages

import geb.Page

/**
 * Created by michal on 5.7.2016.
 */
class NewJobOfferPage extends Page {
    static url = "company-account-job-offers/create/createJobOffer"

    static at = {$('h2').text() == 'New job offer'}

    static content = {
        typeOfOfferSelector {$('#jobOfferType')}
        partTimeJobOption {typeOfOfferSelector.find("option").find {it.value() == "2"}}

        placeOfWorkSelector {$('#positionLocality')}
        pardubiceRegionOption {placeOfWorkSelector.find("option").find {it.value() == "8"}}

        monthSelector {$('#jobStartDate_month')}
        marchOption {monthSelector.find('option').find {it.value() == "3"}}

        yearSelector {$('#jobStartDate_year')}
        nextYearOption {yearSelector.find('option').find {it.value() == "2017"}}

        requiredLanguageSelector {$('#requieredLanguages_chosen')}
        englishOption {requiredLanguageSelector.find('ul.chosen-results').find('li').getAt(0) }
        czechOption {requiredLanguageSelector.find('ul.chosen-results').find('li').getAt(3)}

        /*chosenLanguages {requiredLanguageSelector.find('ul.chosen-choices').find('li.search-choice')}
        firstChosenLanguage {chosenLanguages.first()}
        lastChosenLanguage {chosenLanguages.last()}*/

        newOfferForm {$('form')}

        jobCategorySelector {$('#jobCategories_chosen')}
        jobCategoryOptions {$('ul.chosen-results').find('li')}

        publishButton{$('#publishButton')}
        saveButton{$('#saveButton')}
    }

}
