<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="admin.students.title" /></h2>

        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <g:form method="get" controller="adminUserStudent">

            <g:render template="search"/>

        </g:form>

        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="firstName" title="${message(code: 'admin.listUsers.firstName')}"
                                  style="width: 120px;"/>

                <g:sortableColumn property="lastName" title="${message(code: 'admin.listUsers.lastName')}"
                                  style="width: 120px;"/>

                <g:sortableColumn property="username" title="${message(code: 'admin.listUsers.username')}"
                                  style="width: 120px;"/>


                %{--<th>${message(code: 'user.role.label')}</th>--}%
                %{--<g:sortableColumn property="authorities.authority" title="Role"/>--}%


                %{--<g:sortableColumn property="companyAccountUser.company.companyName" title="${message(code: 'admin.listUsers.company')}" style="width: 150px;"/>--}%


                <g:sortableColumn property="accountCreated" title="${message(code: 'admin.listUsers.dateCreated')}"
                                  style="width: 90px;"/>

                <g:sortableColumn property="lastLoginDate" title="${message(code: 'admin.listUsers.lastLogin')}"
                                  style="width: 90px;"/>

                <g:sortableColumn property="lastLoginDate" title="${message(code: 'admin.user.enabled')}"
                                  style="text-align: center;"/>

                <g:sortableColumn property="accountLocked" title="${message(code: 'admin.listUsers.ban')}"
                                  style="text-align: center;"/>

                <g:sortableColumn property="passwordExpired" title="${message(code: 'admin.listUsers.heslo')}"
                                  style="text-align: center;"/>

                <th style="width: 40px; text-align: center">${message(code: 'company.is.valid')}</th>

                <th style="width: 40px; text-align: center">${message(code: 'admin.user.remote.registration')}</th>

                <th style="width: 40px; text-align: center">${message(code: 'admin.user.note')}</th>

                <th style="width: 40px; text-align: center">${message(code: 'admin.info.emails')}</th>

                <th style="text-align: center; width: 70px;">${message(code: 'company.action.label')}</th>

            </tr>
            </thead>
            <tbody>
            <g:if test="${!userInstanceList}">
                <tr>
                    <td colspan="5">${message(code: 'company.noData.label')}</td>
                </tr>

            </g:if>
            <g:else>
                <g:each in="${userInstanceList}" status="i" var="userInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td colactive="0">${userInstance?.firstName}</td>

                        <td colactive="1">${userInstance?.lastName}</td>

                    %{--neaktivni uzivale budou mit cervenzy username--}%
                        <g:if test="${userInstance?.enabled}">
                            <td colactive="2">${userInstance?.username}</td>
                        </g:if>
                        <g:else>
                            <td colactive="2"><span style="color:red">${userInstance?.username}</span></td>
                        </g:else>




                    %{--<td colactive="3">${userInstance?.authorities?.authority} </td>--}%

                    %{--<td colactive="4"> ${userInstance?.companyAccountUser?.company?.companyName }   </td>--}%



                        <td colactive="5"><g:formatDate date="${userInstance?.accountCreated}"
                                                        format="dd.MM.yyyy"/></td>

                        <td colactive="7"><g:formatDate date="${userInstance?.lastLoginDate}" format="dd.MM.yyyy"/></td>

                        <td colactive="9"
                            style="text-align: center;">${userInstance?.enabled ? "E" : "N"}</td>

                        <td colactive="6" style="text-align: center;">${userInstance?.accountLocked ? "BAN" : "-"}</td>

                        <td colactive="8"
                            style="text-align: center;">${userInstance?.accountExpired ? "EXPIR" : "-"}</td>


                        <td style="text-align: center;">${userInstance?.validate() ? "" : "N"}</td>

                        <td style="text-align: center;">${userInstance?.note }</td>
                        <td style="text-align: center;">${userInstance?.remote }</td>

                        <td  colactive="12" style="text-align: center;">${userInstance?.studentAccount?.infoEmails ? "E" : "N" }</td>
                        <td>
                            <g:render template="menu" model="[userInstance: userInstance]"/>

                        </td>

                    </tr>
                </g:each>

            </g:else>
            </tbody>
    </table>


        <g:paginateFoundation total="${userInstanceListTotal}"/>


    </div>
</div>

</body>
</html>
