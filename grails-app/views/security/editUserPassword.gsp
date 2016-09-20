<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>


<g:form method="post" action="updateUserPassword" controller="password">
    <g:hiddenField name="version" value="${companyUserInstance?.version}"/>
    <fieldset class="form">
        <g:hiddenField name="user" value="${params?.user}"/>
        <g:hiddenField name="returnAddress" value="${params?.returnAddress}"/>
        <g:hiddenField name="returnPath" value="${params?.returnPath}"/>
        <h2>${message(code: 'company.passwodChange.label')}</h2>
        <g:render template="/security/formUserPasswordEdit"/>
    </fieldset>
    <fieldset class="buttons">
        <button action="updateUserPassword" class="save float">${message(code: 'company.update.label')}</button>
        <g:link class="back-button float" url="${params.returnAddress}">
            <g:message code="constantModerator.return.label"/>
        </g:link>
    </fieldset>
</g:form>

</body>
</html>