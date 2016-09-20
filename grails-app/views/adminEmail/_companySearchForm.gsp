<fieldset>
    <table class="tabled-list">
        <thead>
        <tr>

            <th><g:message code="admin.companySearch.obor.label"/></th>
            <th><g:message code="admin.companySearch.name.label"/></th>
            <th><g:message code="admin.companySearch.companyID.label"/></th>

            <th><g:message code="admin.companySearch.others.label"/></th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td>

                <g:select name="jobCategories"
                          id="jobCategories"
                          optionKey="id"
                          optionValue="${{message(code: it.i18NameFull)}}"
                          value="${filterParams?.jobCategories}"
                          from="${ikariera.company.JobCategory.list(sort: "posOrder")}"
                          multiple="true"
                          noSelection="['': message(code: 'jobCategory.noSelection')]"/>

            </td><td>


            <g:textField name="companyName" value="${filterParams?.companyName}"/>
        </td><td>

            <g:textField name="companyID" value="${filterParams?.companyID}"/>
        </td><td>

            <g:message code="admin.companySearch.personal.label"/>
            <g:checkBox name="personalAgency" value="${filterParams?.personalAgency}"/>

        </td><td>
            <g:message code="admin.companySearch.onlyActive.label"/>
            <g:checkBox name="active" value="${filterParams?.active}"/>
        </td>

        </tr>
        </tbody>
    </table>

    %{--<g:checkBox name="studentOrganisation" id="studentOrganisation" value="${filterParams?.studentOrganisation}" />--}%

</fieldset>





