<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>
<div class="row">

    <br/>

    <div class="large-12">

        <h1>${message(code: 'admin.maintenance.edit')}</h1>

    </div>

</div>
<div class="row">

    <div class="large-12">

        <g:form method="put" controller="adminMaintenanceMessage" action="update">

            <g:hiddenField name="id" value="${maintainMessage.id}"/>

            <g:render template="form" model="[maintainMessage: maintainMessage]"/>

            <button class="save float">
                ${message(code: 'constantModerator.update.label')}
            </button>

        </g:form>

    </div>
</div>
</body>
</html>