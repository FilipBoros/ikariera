<%--
  Created by IntelliJ IDEA.
  User: jagun
  Date: 3/28/2014
  Time: 11:42
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>
<div class="row">

    <div class="large-10 columns">
        <h2><g:message code="company.userChangePass.label"/></h2>
    </div>
    <div class="large-2 columns">
        <g:link controller="companyAccountUser" action="edit" id="${userInstance.id}"
                class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>
    <hr>
</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" controller="companyAccountUser" action="savePass">

            <g:hiddenField name="id" value="${userInstance.id}"/>

            <g:render template="/layouts/_fields/inputPassword" model="[
                    label       : message(code: 'user.password1.label'),
                    field       : 'password',
                    name        : 'password',
                    errorMessage: message(code: 'system.emptyPassword.error')]"/>

            <g:render template="/layouts/_fields/inputPassword" model="[
                    label       : message(code: 'user.password2.label'),
                    field       : 'passwordConfirm',
                    name        : 'passwordConfirm',
                    errorMessage: message(code: 'userPersonal.password2.error')]"/>

            <button class="save float">
                ${message(code: 'constantModerator.update.label')}
            </button>

        </g:form>

    </div>
</div>
</body>
</html>