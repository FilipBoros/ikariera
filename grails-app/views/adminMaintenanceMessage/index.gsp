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

        <h2>${message(code: 'maintenance.messages')}</h2>

    </div>

    <div class="large-3 columns">
        <br/>
        <g:link controller="adminMaintenanceMessage" action="create" class="button right">
            ${message(code: 'createNew')}
        </g:link>

    </div>
</div>


<div class="row">

    <div class="large-12 columns">

        <hr>
        <table class="table">
            <thead>
            <tr>

                <th>${message(code: 'name')}</th>
                <th>${message(code: 'admin.messageText.label')}</th>
                <th>${message(code: 'From')}</th>
                <th>${message(code: 'To')}</th>
                <th>${message(code: 'user.action.label')}</th>
            </tr>

            </thead>
            <tbody>
            <g:each in="${messages}" status="i" var="msg">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>${fieldValue(bean: msg, field: "name")}</td>

                    <td>${fieldValue(bean: msg, field: "text")}</td>

                    <td>${fieldValue(bean: msg, field: "dateBegin")}</td>

                    <td>${fieldValue(bean: msg, field: "dateEnd")}</td>

                    <td><g:render template="menu" model="[msg: msg]"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>
        <g:paginateFoundation total="${messages?.size() ?: 0}"/>
    </div>
</div>
</body>
</html>
