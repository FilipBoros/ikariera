<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-10 columns">

        <h1><g:message code="company.create.message" /> </h1>

    </div>

    <div class="large-2 columns">
        <g:link controller="companyAccountEmail" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>
    <hr>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:form action="save" enctype="multipart/form-data" method="post">

            <g:render template="form"/>



            <button name="create"
                    class="save long">${message(code: 'default.button.create.label')}</button>

        </g:form>

    </div>

</div>

</body>
</html>