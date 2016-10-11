package ikariera3.pages

import geb.Page

/**
 * Created by michal on 5.8.2016.
 */
class JobOfferDetailPage extends Page {
    static at = {$('#contactLabel').text() == 'Contact'}

    static content = {
        positionName {$('h2')}
        description {$('#jobOfferDescription')}
        requirements {$('#jobOfferRequirements')}
        advatages {$('#jobOfferAdvantages')}
        jobOfferType {$('#jobOfferType')}
        categories {$('#categories')}
        locality {$('#locality')}
        startDate {$('#startDate')}
        requiredLanguages {$('#requiredLanguages')}
    }
}
