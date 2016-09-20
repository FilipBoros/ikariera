<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: 'remote.api.keys')}</h2>




    </div>

    <div class="large-3 columns">
        <br>
    <g:link controller="adminRemoteApiKey" class="button right" action="create">${message(code: 'remoteApiKey.addServer.label')}</g:link>

    </div>
</div>


<div class="row">

    <div class="large-12 columns">


        <hr>

        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="serviceName"
                                  title="${message(code: 'remoteApiKey.serviceName.label')}"/>

                <g:sortableColumn property="url" title="${message(code: 'remoteApiKey.url.label')}"/>

                <g:sortableColumn property="value"
                                  title="${message(code: 'remoteApiKey.value.label')}"/>

                <g:sortableColumn property="info" title="${message(code: 'remoteApiKey.info.label')}"/>

                <g:sortableColumn property="action" title="${message(code: 'action')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${remoteApiKeyInstanceList}" status="i" var="remoteApiKeyInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>${fieldValue(bean: remoteApiKeyInstance, field: "serviceName")}</td>

                    <td>${fieldValue(bean: remoteApiKeyInstance, field: "url")}</td>

                    <td>${fieldValue(bean: remoteApiKeyInstance, field: "value")}</td>

                    <td>${fieldValue(bean: remoteApiKeyInstance, field: "info")}</td>


                    <td>

                        <g:render template="menu"
                                  model="[remoteApiKeyInstance: remoteApiKeyInstance]"/>

                    </td>

                </tr>
            </g:each>
            </tbody>
        </table>

        <g:paginateFoundation total="${remoteApiKeyInstanceCount}" params="${params}"/>

    </div>

</div>

</body>
</html>
