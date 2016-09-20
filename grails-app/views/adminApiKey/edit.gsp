<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>
<div class="row">

    <br/>

    <div class="large-12">

        <h1>Edit Api Key</h1>
        <hr>

    </div>

</div>
<div class="row">

    <div class="large-12">

        <g:form method="put" controller="adminApiKey" action="update">

            <g:hiddenField name="id" value="${apiKeyInstance.id}"/>

            <g:render template="/adminApiKey/form" model="[apiKeyInstance: apiKeyInstance]"/>

            <button class="save float">
                ${message(code: 'constantModerator.update.label')}
            </button>

        </g:form>

    </div>
</div>
</body>
</html>