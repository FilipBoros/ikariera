package ikariera3.pages

import geb.Page

/**
 * Created by michal on 6.7.2016.
 */
class CompanyJobOffersPage extends Page {
    static url = "company-account-job-offers"

    static at = {$('h1').text() == 'Offers'}

    static content = {
        notification {$('div.info-container')}
        jobOffers {$('#jobOfferTableBody')}
        detailOfFirstJobOffer { jobOffers.find('tr').first().find('td').last().find('a').getAt(0) }
        quickShowOption { jobOffers.find('tr').first().find('td').last().find('a').getAt(1)}
    }
}
