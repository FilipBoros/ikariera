package ikariera3

import ikariera3.pages.*
import ikariera3.setup.CompanyAuthentificationFunSpec

import grails.test.mixin.integration.Integration
import grails.transaction.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class CompanyFunSpec extends CompanyAuthentificationFunSpec {

    // TODO upload suboru http://stackoverflow.com/questions/24908189/image-upload-functional-testing-in-geb
    // TODO upload suboru vracia chybu
    void "Update company profile" () {
        when:
            to CompanyAccountPage
            at CompanyAccountPage

            profileForm.with{
                companyVatID = '2656262'
                companyWeb = 'www.google.com'
                companyStreet = 'Vazovova, 6'
                companyZip = '83106'
                companyCity = 'Bratislava'
                companyCountry = 'SK'
            }

            mainJobCatetogorySelector.click()
            architectureOption.click()

            publicEmail = 'company@company.com'

            mainJobCatetogorySelector.click()
            eletricalEngineeringOption.click()

            companyCharacteristic = 'Zodreme Vas z koze za 5 korun na hodinu.'

            saveChangesButton.click()
        then:
            assert notification.text() == 'Changes successfully saved!'
            assert publicEmail == 'company@company.com'
            assert profileForm.companyVatID == '2656262'
            assert profileForm.companyWeb == 'www.google.com'
            assert profileForm.companyStreet == 'Vazovova, 6'
            assert profileForm.companyZip == '83106'
            assert profileForm.companyCity == 'Bratislava'
            assert profileForm.companyCountry == 'SK'

            assert companyCharacteristic == 'Zodreme Vas z koze za 5 korun na hodinu.'
            assert chosenMainJobCategories.size() == 2
            assert firstChosenMainJobCategory.text() == 'Architecture'
            assert lastChosenMainJobCategory.text() == 'Electrical engineering'
    }

    void "Upload and delete company logo"() {
        when:"The path to a company logo is chosen and upload button is clicked"
            to CompanyAccountPage
            at CompanyAccountPage
            companyLogoPath = "/home/michal/Desktop/ikariera3/src/integration-test/groovy/ikariera3/files/iaeste_140x75.png" // TODO@ odhardkodit tieto cesty
            companyLogoUpload.click()
        then:"The company logo was successfully uploaded"
            assert notification.text() == 'File uploaded'
        when:"The delete button for the company logo is clicked"
            withConfirm(true) { companyLogoDeleteButton.click() }
        then:"The company logo is deleted."
            assert notification.text() == 'Logo deleted'
    }

    void "Upload and delete hero picture"() {
        when:"The path to a hero picture is chosen and upload button is clicked"
        to CompanyAccountPage
        at CompanyAccountPage
        heroPicturePath = "/home/michal/Desktop/ikariera3/src/integration-test/groovy/ikariera3/files/test_hero_1000x400.png" // TODO@ odhardkodit tieto cesty
        heroPictureUpload.click()
        then:"The hero picture was successfully uploaded"
        assert notification.text() == 'File uploaded'
        when:"The delete button for the company logo is clicked"
        withConfirm(true) { heroPicutreDeleteButton.click() }
        then:"The company logo is deleted."
        assert notification.text() == 'Logo deleted'
    }

    void "Create new job offer" () {
        when:"Fill the new job offer and publish it"
            to CompanyAccountPage
            at CompanyAccountPage

            calloutPanelButtons.first().click()
            at NewJobOfferPage

            yearSelector.click()
            nextYearOption.click()

            placeOfWorkSelector.click()
            pardubiceRegionOption.click()

            typeOfOfferSelector.click()
            partTimeJobOption.click()

            newOfferForm.with{
                positionName = 'Drziak poharov'
                jobOfferDescription = 'Jednoducha praca v prijemnom prostredi'
                jobApplicantRequire = 'Drzanie veci v rukach bez toho aby vypadli./n Minimalne vzdelanie tretieho stupna v odbore jadrova fyzika.'
                jobTypeDescription = 'Odkupenie prebytocnych poharov za vyhodnu cenu.'
            }

            jobCategorySelector.click()
            jobCategoryOptions.first().click()

            yearSelector.click()
            nextYearOption.click()

            monthSelector.click()
            marchOption.click()

            requiredLanguageSelector.click()
            czechOption.click()

            requiredLanguageSelector.click()
            englishOption.click()

            publishButton.click()

        then:"New job offer is published"
            at CompanyJobOffersPage
            assert notification.text() == 'job offer published'
        when:"Click on the detail of the first job offer"
            detailOfFirstJobOffer.click()
            withNewWindow( { quickShowOption.click() } ) {
                    at(JobOfferDetailPage)
                    assert positionName.text() == 'Drziak poharov'
                    assert description.text() == 'Jednoducha praca v prijemnom prostredi'
                    assert requirements.text() == 'Drzanie veci v rukach bez toho aby vypadli./n Minimalne vzdelanie tretieho stupna v odbore jadrova fyzika.'
                    assert advatages.text() == 'Odkupenie prebytocnych poharov za vyhodnu cenu.'
                    assert categories.text() == 'Administration'
                    assert locality.text() == 'Pardubice Region'
                    assert startDate.text() == 'March 2017'
                    assert requiredLanguages.text() == 'Czech, English' || 'English, Czech'
                }
        then:"The details of the job offer matches the details of the recently created job offer"
        /*then block is placed in when block, because of http://stackoverflow.com/questions/38802223/withnewwindow-returns-multiplecompilationerrorsexception-in-geb*/

    }
}
