<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">
    <div class="large-10 columns">
        <h2><g:message
                args="[userInstance?.lastName, userInstance?.firstName, userInstance?.username]"
                code="company.userChange.label"/></h2>
    </div>

    <div class="large-2 columns">
        <g:link controller="companyAccountUser" action="index"
                class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>


    <hr>
</div>

<div class="row">

    <div class="large-12 columns">

        <g:form method="post" action="update">
            <g:hiddenField name="id" value="${userInstance?.id}"/>
            <g:hiddenField name="version" value="${userInstance?.version}"/>



            <g:render template="/companyAccountUser/form" model="[userInstance: userInstance]"/>


            <g:link params="[id: userInstance?.id]"
                    action="changePass">
                ${message(code: 'user.changePassword.label')}
            </g:link>
            <br/>
            <br/>

        %{--            <g:if test="${sec.username() == companyUserInstance.username}">
                        <div class="form-field">
                            <label for="passwordREDIRECT"><g:message code="user.password1.label"/>:</label>

                            <g:link controller="password" action="editUserPassword" name="passwordREDIRECT"

                                    style="  line-height: 28px;
                                    margin-left: 280px;
                                    position: relative;
                                    width: 300px;">
                            <g:message code="user.changePassword.label"/></g:link>
                        </div>
                    </g:if>--}%

            <button action="updateUser" class="save button">${message(code: 'company.update.label')}</button>

        </g:form>

    </div>

</div>
</body>
</html>