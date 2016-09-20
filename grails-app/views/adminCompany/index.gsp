<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2>${message(code: 'admin.listCompanies.label')}</h2>


        <hr>

    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/adminCompany/search"/>
    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="companyID" style="width: 80px"
                                  title="${message(code: 'company.companyIdentificationNumber.label')}"/>

                <g:sortableColumn style="width: 170px" property="companyName"
                                  title="${message(code: 'company.companyName.label')}"/>

                <g:sortableColumn style="width: 150px" property="companyStreet"
                                  title="${message(code: 'company.companyStreet.label')}"/>

                <g:sortableColumn style="width: 80px" property="companyCity"
                                  title="${message(code: 'company.companyCity.label')}"/>

                %{--<g:sortableColumn property="companyCountry" title="${message(code: 'company.companyCountry.label')}"/>--}%

                %{--<g:sortableColumn property="companyZip" title="${message(code: 'company.companyZip.label')}"/>--}%

                <g:sortableColumn property="credits" style="text-align: center;"
                                  title="${message(code: 'company.credits.label')}"/>

                %{--<g:sortableColumn property="companyWebPage" title="${message(code: 'company.companyWebPage.label')}"/>--}%

                <g:sortableColumn property="logo" style="text-align: center;"
                                  title="${message(code: 'company.logoTitle.label')}"/>



                <g:sortableColumn property="personalAgency" style="text-align: center;"
                                  title="${message(code: 'company.personal.label')}"/>

                <g:sortableColumn property="active" style="text-align: center;"
                                  title="${message(code: 'company.active.label')}"/>

                <th style="width: 40px; text-align: center">${message(code: 'startup.label')}</th>

                <th style="width: 40px; text-align: center">${message(code: 'company.is.valid')}</th>

                <th style="width: 80px; text-align: center">${message(code: 'company.action.label')}</th>

            </tr>
            </thead>
            <tbody>
            <g:if test="${!companyInstanceList}">
                <tr>
                    <td colspan="10">${message(code: 'company.noData.label')}</td>
                </tr>

            </g:if>
            <g:else>
                <g:each in="${companyInstanceList}" status="i" var="companyInstance">

                    <tr>

                        <td colactive="0">${companyInstance?.companyID}</td>

                        <td colactive="1">
                            <g:link controller="adminCompany" action="logAsCompany" target="_blank"
                                    params="[id: companyInstance.id]">

                                <span style="${companyInstance?.active ?: 'color:red'}">${companyInstance?.companyName}</span>

                            </g:link>

                        </td>

                        <td colactive="2">${companyInstance?.companyStreet}</td>

                        <td colactive="3">${companyInstance?.companyCity}</td>

                        %{--                        <td>${companyInstance?.companyCountry}</td>

                                                <td>${companyInstance?.companyZip}</td>--}%

                        <td colactive="4" style="text-align: center;">${companyInstance?.credits}</td>

                        %{--<td>${companyInstance?.companyWeb}</td>--}%


                        <td colactive="5" style="text-align: center;">${companyInstance?.logo ? "L" : "-"}</td>



                        <td colactive="7"
                            style="text-align: center;">${companyInstance?.personalAgency ? "P" : "-"}</td>




                        <td colactive="8"
                            style="text-align: center; ${companyInstance?.active ? 'color: black;' : 'color: red;'}">${companyInstance?.active ? "A" : "N"}</td>

                        <td colactive="9"
                            style="text-align: center; color: black;">${companyInstance?.startup > new Date() ? "A" : "N"}</td>

                        <td style="text-align: center;">${companyInstance?.validate() ? "" : "N"}</td>

                        <td>

                            <g:render template="/adminCompany/menu"
                                      model="[companyInstance: companyInstance, returnAddress: request.forwardURI,
                                              returnPath     : (request.forwardURI - request.contextPath)]"/>

                        </td>

                    </tr>

                </g:each>

            </g:else>
            </tbody>
        </table>
        <g:paginateFoundation total="${companyInstanceTotal}" params="${params}"/>
    </div>

</div>

<div id="confirm-dialog" title='${message(code: 'system.warning.label')}' class="giveMessage"></div>

</body>
</html>
