<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>
<div class="row">

    <br/>

    <div class="large-12">

        <h1>Add remote server</h1>
        <hr>

    </div>

</div>
<div class="row">

    <div class="large-12">

        <g:form method="post" controller="adminRemoteServer" action="save">

            <g:render template="/adminRemoteServer/form" model="[remoteInstance: remoteServerInstance]"/>

            <button class="save float">
                ${message(code: 'constantModerator.update.label')}
            </button>

        </g:form>

    </div>
</div>
</body>
</html>