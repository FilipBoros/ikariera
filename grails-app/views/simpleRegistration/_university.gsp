
<div class="cloned-table-row row">

    <div class="table-input  large-6 columns">

        <label>

            ${message(code: "university.title.label")}

            <g:textField name="studentAccount.educations[0].titleAwarded"
                         placeholder="Ing., Bc. ..."
                         value="${userInstance?.studentAccount?.educations?.first()?.titleAwarded}"/>

        </label>
    </div>

    <div class="table-select large-6 columns">

        <label>
            ${message(code: "university.startYear.label")}



            <g:select name="studentAccount.educations[0].startYearInteger" from="${2000..2040}"

                      value="${userInstance?.studentAccount?.educations?.first()?.startYearInteger?: formatDate(date:  new Date(), format:  "yyyy" )}"
            />


        </label>

    </div>


    <div class="table-select large-6 columns">

        <label>
            ${message(code: "university.endYear.label")}



            <g:select name="studentAccount.educations[0].endingYearInteger" from="${2000..2040}"

                      value="${userInstance?.studentAccount?.educations?.first()?.endingYearInteger?: formatDate(date:  new Date(), format:  "yyyy" )}"
                      />


        </label>

    </div>


    <div class="table-select large-12 columns ">
        <label>

            <g:message code="university.university.label"/>

            <g:select name="studentAccount.educations[0].university.id"
                      from="${cz.ikariera.student?.University?.list(sort: "posOrder")}"
                      optionKey="id"
                      class="studentUniversitySelect"
                      optionValue="${{ message(code: it.i18NameFull) }}"
                      placeholder="${message(code: "university.endYear.label")}"
                      value="${userInstance?.studentAccount?.educations?.first()?.university?.id}"/>

        </label>
    </div>


    <div class="table-select large-6 columns">
        <label>
            <g:message code="university.category.label"/>
            <g:select name="studentAccount.educations[0].studyCategory.id"
                      from="${cz.ikariera.student?.StudyCategory?.list(sort: "posOrder")}"
                      optionKey="id"


                      optionValue="${{ message(code: it.i18NameFull) }}"
                      value="${userInstance?.studentAccount?.educations?.first()?.studyCategory?.id}"/>
        </label>
    </div>


    <div class="table-input large-6 columns">
        <label>
            <g:message code="university.specialization.label"/>
            <g:textField name="studentAccount.educations[0].specialization" value="${userInstance?.studentAccount?.educations?.first()?.specialization}"/>
        </label>
    </div>


    <div class="table-input studentOtherUniversityInput large-12 columns"
         style="${education?.university?.id == 1 ? 'display: block;' : 'display:none;'} margin-top: 7px; ">
        <g:textField name="studentAccount.educations[0].otherUniversity" value="${userInstance?.studentAccount?.educations?.first()?.otherUniversity}"/>
    </div>




</div>
