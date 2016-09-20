<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <div class="large-12">

        <g:form method="post" controller="adminUserAdmin" action="update">

            <h2>${message(code: "admin.registration.h2.label")}</h2>

            <g:hiddenField name="id" value="${adminInstance.id}"/>
            <g:hiddenField name="version" value="${adminInstance.version}"/>
            <g:render template="/adminUserAdmin/formEdit"/>

            <g:if test="${sec.username() == adminInstance.username}">
                <g:link params="[id: adminInstance?.id]"
                        action="changePass">
                    ${message(code: 'user.changePassword.label')}
                </g:link>
            </g:if>
            <br>
            <br>
            <button class="save float">
                ${message(code: 'constantModerator.update.label')}
            </button>

        </g:form>

    </div>
</div>
</body>
</html>
