package ikariera3

import ikariera3.setup.StudentAuthentificationFunSpec
import ikariera3.pages.*

import grails.test.mixin.integration.Integration
import grails.transaction.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class StudentFunSpec extends StudentAuthentificationFunSpec {

    void "Update profile"(){
        when:"Student has logged in and clicks on big green button personal profile"
            at StudentAccountPage
            personalProfile.click()
        then:"Student is at his personal profile"
            waitFor(20) {at StudentPersonalDetailsPage}
        when:'Student fills the form with new data'
            firstName = 'Uku'
            lastName = 'Lele'

            /*userName = 'student@ikariera.eu'*/ // pri zmene emailu treba spravit novu authenfikacnu classu s inym emailom
            countrySelector.click()
            centralAfricaOption.click()

            telephone = '1818181911'
            nationality = 'Caf'
            streetAndAddress = 'Road to Hell 42'
            city = 'Bangui'
            zip = '666'

            jobCatetogorySelector.click()
            architectureOption.click()

            jobCatetogorySelector.click()
            eletricalEngineeringOption.click()

            saveButton.click()

        then:"Profile is updated"
            assert message.text() == 'Account changes saved!' // TODO fix me
            assert firstName == 'Uku'
            assert lastName == 'Lele'
            assert chosenMainJobCategories.size() == 2
            assert firstChosenMainJobCategory.text() == 'Architecture'
            assert lastChosenMainJobCategory.text() == 'Electrical engineering'
            assert selectedCountry.text() ==  'Central African'
            assert telephone == '1818181911'
            assert nationality == 'Caf'
            assert streetAndAddress == 'Road to Hell 42'
            assert city == 'Bangui'
            assert zip  == '666'
    }

    void "Upload and delete curriculum vitae"() {
        when:"The path to a curriculum vitae is chosen and upload button is clicked"
            to StudentPersonalDetailsPage
            at StudentPersonalDetailsPage
            curriculumVitaePath = "/home/michal/Desktop/ikariera3/src/integration-test/groovy/ikariera3/files/cv.pdf" // TODO@ odhardkodit tieto cesty
            curriculumVitaeUpload.click()
        then:"The curriculum vitae was successfully uploaded"
            assert message.text() == 'File uploaded'
        when:"The delete button for the curriculum vitae is clicked"
            withConfirm(true) { curriculumDeleteButton.click() }
        then:"The curriculum vitae is deleted."
            assert message.text() == 'CV deleted'
    }

    void "Upload and delete profile photo"() {
        when:"The path to a profile photo is chosen and javascript action is fired"
            to StudentPersonalDetailsPage
            at StudentPersonalDetailsPage
            fileupload = "/home/michal/Desktop/ikariera3/src/integration-test/groovy/ikariera3/files/nuke_300x300.png" // TODO@ odhardkodit tieto cesty
        then:"The profile photo was successfully uploaded"

        when:"The delete button for the profile photo is clicked"
            profilePhotoDeleteButton.click()
        then:"The profile photo is deleted."
    }

    // TODO finnish these tests
    void "Create update and delete education of a Student"() {
        when:
            to StudentEducationPage
            addUniversityButton.click()
            saveChangesButton.click()
        then:
            to StudentEducationPage
            assert educations.size() == 1
            at StudentEducationPage
        when:'create'
            to StudentEducationPage
            assert educations.size() == 1

            firstEducationDegree = 'Ing'

            startYearSelector.click()
            endingYearSelector.click()
            nextYearOption.click()

            universitySelector.click()
            praguesCVUTOption.click()

            fieldOfStudySelector.click()
            energeticsOption.click()

            specialization = 'Renewable resources'

            saveChangesButton.click()
        then:
            to StudentEducationPage
            assert firstEducationDegree.value().toString() == 'Ing'
            assert selectedYear.text() == '2017'
            assert selectedUniversity.text() == 'Czech technical university in Prague'
            /*waitFor(30) { assert selectedField.text() == 'Energetics' }*/ /*TODO fix me*/
            assert specialization.text() == 'Renewable resources'
       /* when:'update' TODO finnish it
        then:
        when:'delete'
        then:*/
    }

    void "Create update and delete language knowledge of a Student"() {
        when:
            to StudentLanguagePage
        then:
            at StudentLanguagePage
    }

    void "Create update and delete skill of a Student"() {
        when:
            to StudentSkillPage
        then:
            at StudentSkillPage
    }

    void "Create update and delete certificate or course of a Student"() {
        when:
            to StudentCertificatePage
        then:
            at StudentCertificatePage
    }

    void "Create update and delete work experience of a Student"() {
        when:
            to StudentWorkExperiencePage
        then:
            at StudentWorkExperiencePage
    }

}
