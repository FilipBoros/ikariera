<%@ page import="cz.ikariera.admin.RemoteServer" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>
<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>Remote servers</h2>

    </div>

    <div class="large-3 columns">

        <br/>
        <g:link controller="adminRemoteServer" action="create" class="button right">
            <g:message code="createNew"/>
        </g:link>

    </div>



</div>

<div class="row">

    <div class="large-12 columns">

        <hr>

        <table class="table">
            <thead>
            <tr>

                <th>${message(code: 'servername')}</th>
                <th>${message(code: 'country')}</th>
                <th>${message(code: 'apikey')}</th>
                <th>${message(code: 'url')}</th>
                <th>${message(code: 'user.action.label')}</th>
            </tr>

            </thead>
            <tbody>
            <g:each in="${serverList}" status="i" var="server">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>${fieldValue(bean: server, field: "name")}</td>

                    <td>${fieldValue(bean: server, field: "country.name")}</td>

                    <td>${fieldValue(bean: server, field: "token")}</td>

                    <td>${fieldValue(bean: server, field: "serverURL")}</td>

                    <td><g:render template="menu" model="[server: server]"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>
        <g:paginateFoundation total="${serverList?.size() ?: 0}"/>
    </div>
</div>
</body>
</html>
