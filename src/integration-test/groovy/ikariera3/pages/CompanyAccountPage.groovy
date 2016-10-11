package ikariera3.pages

import geb.Page

/**
 * Created by michal on 5.7.2016.
 */
class CompanyAccountPage extends Page {
    static url = "company-account-profile"

    static at = {$('h1').text() == 'Company profile'}

    static content = {
        profileForm { $('form') }
        publicEmail { $('#publicEmail') }

        mainJobCatetogorySelector { $('#mainJobCategories_chosen') }
        architectureOption { $('ul.chosen-results').find('li').getAt(1) }
        eletricalEngineeringOption { $('ul.chosen-results').find('li').getAt(6) }

        chosenMainJobCategories { $('ul.chosen-choices').find('li.search-choice') }
        firstChosenMainJobCategory { chosenMainJobCategories.first() }
        lastChosenMainJobCategory { chosenMainJobCategories.last() }

        saveChangesButton { $('button.button') }
        notification { $('div.info-container') }
        calloutPanelButtons { $('#calloutPanelButtons > a') }

        companyLogoPath { $('#companyLogoPath') }
        companyLogoUpload { $('#companyLogoUpload') }
        companyLogoDeleteButton { $('a', class: 'button alert tiny right').first() }

        heroPicturePath { $('#heroPicturePath') }
        heroPictureUpload { $('#heroPictureUpload') }
        heroPicutreDeleteButton { $('a', class: 'button alert tiny right').last() }
    }
}
