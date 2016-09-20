<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="admin.remoteRegister.list.label"/></h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        %{--
                <div class="admin-btn-group">
                    <g:link class="button" action="failSafeRemoteRegister"
                            controller="adminRemoteRegister"><g:message code="remoterRegisterImport"/></g:link>
                </div>
        --}%





        %{--<g:link class="testAjax" controller="remoteRegister" action="save" >test </g:link>--}%

        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="uid" title="${message(code: 'admin.remoteRegister.registratorName')}"
                                  params="${params}"/>

                <g:sortableColumn property="date" title="${message(code: 'admin.remoteRegister.date')}"
                                  params="${params}"/>

                <g:sortableColumn property="firstName" title="${message(code: 'admin.remoteRegister.name')}"
                                  params="${params}"/>

                <g:sortableColumn property="lastName" title="${message(code: 'admin.remoteRegister.lastName')}"
                                  params="${params}"/>

                <g:sortableColumn property="username" title="${message(code: 'admin.remoteRegister.email')}"
                                  params="${params}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${remoteRegisterInstanceList}" status="i" var="remoteRegisterInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td class="first">
                        ${remoteRegisterInstance?.uid}
                    </td>

                    <td>
                        <g:formatDate type="datetime" style="LONG" timeStyle="SHORT"
                                      date="${remoteRegisterInstance?.date}"/>
                    </td>



                    <td>${remoteRegisterInstance?.firstName}</td>

                    <td>${remoteRegisterInstance?.lastName}</td>

                    <td>${remoteRegisterInstance?.username}</td>

                </tr>
            </g:each>
            </tbody>
        </table>



        <g:paginateFoundation total="${listTotalCount}" params="${params}"
                              maxsteps="4"
                              next="${message(code: "list.paginate.next")}"
                              prev="${message(code: "list.paginate.prev")}"/>

    </div>

</div>

<div id="confirm-dialog" title='${message(code: 'system.warning.label')}' class="giveMessage"></div>

</body>
</html>
