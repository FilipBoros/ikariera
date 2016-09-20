<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>${message(code: 'admin.listCompanies.label')}</h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" controller="admin" action="updateConstant">

            <g:hiddenField name="id" value="${constantsInstance?.id}"/>
            <g:hiddenField name="version" value="${constantsInstance?.version}"/>
            <g:render template="adminConstant/formConstant"/>

            <button class="save float">${message(code: 'constantModerator.update.label')}</button>
            <g:link class="back-button float" controller="admin" action="listConstant">
                <g:message code="constantModerator.return.label"/>
            </g:link>

        </g:form>
    </div>

</div>
</body>
</html>
