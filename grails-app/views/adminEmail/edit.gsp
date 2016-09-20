<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>${message(code: 'admin.adminMailing.label')}</h2>



        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" enctype="multipart/form-data" action="save">

            <g:hiddenField name="id" value="${adminEmailsInstance?.id}"/>
            <g:hiddenField name="version" value="${adminEmailsInstance?.version}"/>

            <fieldset class="form">
                <g:render template="/adminEmail/form"/>
            </fieldset>


            <fieldset class="buttons">

                <button name="create" class="save float">
                    ${message(code: 'default.button.save.label')}
                </button>
                <g:link class="back-button float" url="${params.returnAddress}">
                    <g:message code="constantModerator.return.label"/>
                </g:link>
            </fieldset>

        </g:form>

    </div>

</div>
</body>
</html>

