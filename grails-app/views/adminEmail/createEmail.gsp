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

        <g:form action="saveEmail" enctype="multipart/form-data">

            <h2><g:message code="company.email.header.label"/></h2>

            <g:render template="/adminEmail/form"/>


            <button name="create"
                    class="save long">${message(code: 'default.button.create.label')}</button>

        </g:form>

    </div>

</div>
</body>
</html>>