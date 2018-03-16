<tr class="cloned-table-row">

    <td class="table-input  large-6 columns">

        <g:hiddenField name='educations[${i}].id' value='${education?.id}' class="exclude-from-clone"/>

        <label>

            ${message(code: "university.title.label")}

            <g:textField name="educations[${i}].titleAwarded"
                         placeholder="Ing., Bc. ..."
                         value="${education?.titleAwarded}"/>

        </label>
    </td>


    <td class="table-select large-3 columns">

        <label>
            ${message(code: "university.startYear.label")}

            <g:datePicker name="educations[${i}].startYear" class="exclude-from-clone" precision="year"
                          placeholder="${message(code: "university.startYear.label")}"
                          value="${education?.startYear}"/>

        </label>

    </td>

    <td class="table-select large-3 columns">

        <label>
            ${message(code: "university.endYear.label")}

            <g:datePicker name="educations[${i}].endingYear" class="exclude-from-clone" precision="year"
                          placeholder="${message(code: "university.endYear.label")}"
                          value="${education?.endingYear}"/>

        </label>

    </td>
    


    <td class="table-select large-12 columns ">
        <label>

            <g:message code="university.university.label"/>

            <g:select name="educations[${i}].university.id"
                      from="${cz.ikariera.student?.University?.list(sort: "posOrder")}"
                      optionKey="id"
                      class="studentUniversitySelect"
                      optionValue="${{ message(code: it.i18NameFull) }}"
                      placeholder="${message(code: "university.endYear.label")}"
                      value="${education?.university?.id}"/>

        </label>
    </td>


    <td class="table-select large-6 columns">
        <label>
            <g:message code="university.category.label"/>
            <g:select name="educations[${i}].studyCategory.id"
                      from="${cz.ikariera.student?.StudyCategory?.list(sort: "posOrder")}"
                      optionKey="id"


                      optionValue="${{ message(code: it.i18NameFull) }}"
                      value="${education?.studyCategory?.id}"/>
        </label>
    </td>


    <td class="table-input large-6 columns">
        <label>
            <g:message code="university.specialization.label"/>
            <g:textField name="educations[${i}].specialization" value="${education?.specialization}"/>
        </label>
    </td>


    %{--<td class="table-input studentOtherUniversityInput large-12 columns"
         style="${education?.university?.id == 1 ? 'display: block;' : 'display:none;'} margin-top: 7px; ">
        <g:textField name="educations[${i}].otherUniversity" value="${education?.otherUniversity}"/>
    </td>--}%

    <td>
        <g:if test="${!noButtons}">
            <div class="del-cloned-table-row">
                <a href="#" class="button alert tiny"><i class="fi-x"></i></a>
            </div>
        </g:if>
    </td>
</tr>
