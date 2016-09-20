<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>${message(code: "admin.registration.h2.label")}</h2>

        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" controller="adminUserAdmin" action="save">

            <g:render template="form"/>

            <button class="save float">${message(code: 'constantModerator.update.label')}</button>

        </g:form>
    </div>

</div>
</body>
</html>
