<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-10 columns">

        <h1><g:message code="company.create.public.details" /> </h1>

    </div>

    <div class="large-2 columns">
        <g:link controller="companyContacts" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>

    <hr>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:form action="save" controller="companyContacts" method="post">

            <g:render template="form"/>

            <button name="create"
                    class="save long"><g:message code="create" /> </button>
        </g:form>

    </div>

</div>

</body>
</html>