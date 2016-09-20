<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2><g:message code="admin.edit.services" /> </h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" controller="admin" action="updatePurchasedService">

            <g:hiddenField name="id" value="${serviceInstance?.id}"/>
            <g:hiddenField name="version" value="${serviceInstance?.version}"/>
            <g:render template="/adminCompanyService/formService"/>

            <button class="save float">${message(code: 'constantModerator.update.label')}</button>

        </g:form>
    </div>

</div>
</body>
</html>
