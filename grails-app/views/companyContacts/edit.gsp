<!doctype html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br>

    <div class="large-10 columns">

        <h1><g:message code="company.edit.public.details" /> </h1>

    </div>

    <div class="large-2 columns">
        <g:link controller="companyContacts" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>

    <hr>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:form action="update" controller="companyContacts" method="put">

            <g:hiddenField name="id" value="${contactDetailInstance?.id}" />

            <g:render template="form"/>

            <button class="save long">${message(code: 'system.save.button')}</button>

        </g:form>

    </div>

</div>

</body>
</html>