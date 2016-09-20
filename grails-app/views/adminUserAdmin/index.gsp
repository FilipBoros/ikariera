<%@ page import="cz.ikariera.company.Services" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: "admin.list.h2.label")}</h2>

    </div>

    <div class="large-3 columns">

        <sec:ifAllGranted roles="ROLE_SUPER_ADMIN">

            <g:link controller="adminUserAdmin" action="create" class="button right">
                <g:message code="admin.list.addUser.button"/>
            </g:link>

        </sec:ifAllGranted>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <hr>

        <table class="table">
            <thead>
            <tr>
                <th>${message(code: 'user.firstName.label')}</th>
                <th>${message(code: 'user.lastName.label')}</th>
                <th>${message(code: 'user.username.label')}</th>
                <th>${message(code: 'user.dateCreated.label')}</th>


                <th>${message(code: 'admin.listUsers.lastLogin')}</th>

                <th>${message(code: 'user.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!userInstanceList}">
                <tr>
                    <td colspan="3">${message(code: 'jobOffer.noData.label')}</td>
                </tr>

            </g:if>
            <g:else>

                <g:each in="${userInstanceList}" status="i" var="adminInstance">
                    <tr>
                        <td>${adminInstance?.firstName}</td>
                        <td>${adminInstance?.lastName}</td>
                        <td>${adminInstance?.username}</td>
                        <td><g:formatDate format="dd.MM.yy" date="${adminInstance?.accountCreated}"/></td>

                        <td><g:formatDate date="${adminInstance?.lastLoginDate}" format="dd.MM.yyyy HH:mm:ss"/></td>

                        <td>
                            <g:render template="menu" model="[adminInstance: adminInstance]"/>
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




