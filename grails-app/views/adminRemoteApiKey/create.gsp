<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h1>${message(code: 'admin.create.apiKey.label')}</h1>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form action="save"  >


            <g:render template="form" model="[remoteApiKeyInstance: remoteApiKeyInstance]"/>



            <button name="create" class="save button ">
                ${message(code: 'default.button.save.label')}
            </button>

        </g:form>

    </div>

</div>
</body>
</html>
