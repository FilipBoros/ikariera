<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h1>Edit ApiKey</h1>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form action="update" id="${remoteApiKeyInstance?.id}" method="PUT">

            <g:hiddenField name="_metnod" value="PUT"/>
            <g:hiddenField name="version" value="${remoteApiKeyInstance?.version}"/>






            <g:render template="form" model="[remoteApiKeyInstance: remoteApiKeyInstance]"/>



            <button name="create" class="save float">
                ${message(code: 'default.button.save.label')}
            </button>

        </g:form>
    </div>
</div>
</body>
</html>
