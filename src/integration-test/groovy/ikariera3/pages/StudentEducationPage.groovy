package ikariera3.pages

import geb.Page

/**
 * Created by michal on 31.8.2016.
 */
class StudentEducationPage extends Page{
    static url = "/student-account-university"

    static at = {$('h2').text() == 'Education'}

    static content = {
        addUniversityButton {$('#add-new-university')}
        saveChangesButton {$('button',class: 'save long ')}
        educationsTable {$('#universities-table-list')}
        educations {educationsTable.find('tr.cloned-table-row')}

        firstEducationDegree {$('input', id:'educations[0].titleAwarded')}

        startYearSelector {$('select', id:'educations[0].startYear_year')}
        endingYearSelector {$('select', id:'educations[0].endingYear_year')}
        nextYearOption {endingYearSelector.find("option").find {it.value() == "2017"}}
        selectedYear {endingYearSelector.find('option', selected:'selected')}

        universitySelector {$('select', id:'educations[0].university.id')}
        praguesCVUTOption {universitySelector.find("option").find {it.value() == "2"}}
        selectedUniversity {universitySelector.find('option', selected:'selected')}

        fieldOfStudySelector {$('select', id:'educations[0].studyCategory.id')}
        energeticsOption {fieldOfStudySelector.find("option").find {it.value() == "7"}}
        selectedField {fieldOfStudySelector.find('option', selected:'selected')}

        specialization {$('input', id:'educations[0].specialization')}
/*        firstEducationFinalYearSelector
        firstEducationUniversity
        firstEducationFieldSelector

        firstEducationSpecializationSelector*/
    }
}
