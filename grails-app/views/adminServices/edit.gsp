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

        <g:form method="post" controller="adminServices" action="update">

            <g:hiddenField name="id" value="${serviceInstance?.id}"/>
            <g:hiddenField name="version" value="${serviceInstance?.version}"/>
            <g:render template="form" model="[serviceInstance: serviceInstance]"/>

            <button class="save float">${message(code: 'constantModerator.update.label')}</button>

        </g:form>
    </div>

</div>
</body>
</html>
