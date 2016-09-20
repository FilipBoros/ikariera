<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">



    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" action="save" controller="adminUserCompany">
            <g:hiddenField name="id" value="${userInstance?.id}"/>
            <g:hiddenField name="version" value="${userInstance?.version}"/>
            <g:hiddenField name="version2" value="${userInstance?.companyAccount?.version}"/>

            <h2><g:message args="[userInstance?.lastName, userInstance?.firstName, userInstance?.username]"
                           code="company.userChange.label"/></h2>
            <g:render template="/adminUserCompany/companyUserForm"
                      model="['companyUserInstance': userInstance, 'companyAccountInstance': userInstance?.companyAccount]"/>

            <g:link params="[id: userInstance?.id]"
                    action="changePass">
                ${message(code: 'user.changePassword.label')}
            </g:link>
            <br>
            <br>
            <button action="updateUser" class="save half">${message(code: 'company.update.label')}</button>

        </g:form>
    </div>

</div>
</body>
</html>