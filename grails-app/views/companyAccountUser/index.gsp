<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">
    <br>

    <div class="large-9 columns">
        <h1><g:message code="companyAccountUser.h2.label"/></h1>

    </div>

    <div class="large-3 columns">
        <br>
        <g:link controller="companyAccountUser" action="create" class="button right"><g:message
                code="companyAccountUser.add.label"/></g:link>

    </div>
</div>

<div class="row">

    <div class="large-12 columns">

        <hr>


        <table style="width: 100%">
            <thead>
            <tr>

                <g:sortableColumn property="firstName" title="${message(code: 'user.firstName.label')}"
                                  style="width: 130px"/>
                <g:sortableColumn property="lastName" title="${message(code: 'user.lastName.label')}"
                                  style="width: 130px"/>




                <g:sortableColumn property="username" title="${message(code: 'user.username.label')}"
                                  style="width: 200px"/>

                %{--                <g:sortableColumn property="telephone" title="${message(code: 'companyAccount.telephone.label')}"
                                                  style="width: 150px"/>
                                <g:sortableColumn property="positionInCompany"
                                                  title="${message(code: 'jobOffers.positionInCompany.label')}"/>--}%

                <th>${message(code: 'active')}</th>
                <th style="width: 140px">${message(code: 'last.login')}</th>

                <th style="width: 40px">${message(code: 'company.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${userInstanceList}" status="i" var="userInstance">
                <tr>

                    <td>${userInstance?.firstName}</td>

                    <td>${userInstance?.lastName}</td>






                    <td>${userInstance?.username}</td>

                    %{--        <td>${userInstance?.companyAccount?.telephone}</td>

                            <td>${userInstance?.companyAccount?.positionInCompany}</td>--}%




                    <td>${userInstance?.enabled ? "ano" : "ne"}</td>
                    <td><g:formatDate date="${userInstance?.lastLoginDate}"/></td>

                    <td>

                        <g:render template="dropDownMenu" model="[userInstance: userInstance]"/>

                    </td>
                </tr>
            </g:each>

            </tbody>
        </table>


        <g:paginateFoundation total="${userInstanceListTotal}"/>

    </div>

</div>

</body>
</html>




