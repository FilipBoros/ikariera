<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>${message(code: 'admin.listCompanies.label')}</h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <h2>${message(code: 'constantModerator.h2.label')}</h2>
        <table class="tabled-list">
            <thead>
            <tr>
                <g:sortableColumn property="cmName" title="${message(code: 'constantModerator.name.label')}"/>
                <g:sortableColumn property="cmComment" title="${message(code: 'constantModerator.comment.label')}"/>
                <g:sortableColumn property="cmValue" title="${message(code: 'constantModerator.value.label')}"/>
                <th>${message(code: 'constantModerator.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!constantsInstanceList}">
                <tr>
                    <td colspan="4">${message(code: 'jobOffer.noData.label', default: 'NO DATA')}</td>
                </tr>
            </g:if>
            <g:else>
                <g:each in="${constantsInstanceList}" status="i" var="constantInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${constantInstance.cmName}</td>

                        <td><g:message code="${constantInstance.cmComment}"/></td>

                        <g:if test="${constantInstance.cmValue}">
                            <td>${fieldValue(bean: constantInstance, field: "cmValue")}</td>
                        </g:if>
                        <g:elseif test="${constantInstance.cmString}">
                            <td>${fieldValue(bean: constantInstance, field: "cmString")}</td>
                        </g:elseif>
                        <g:elseif test="${constantInstance.cmBoolean != null}">
                            <td>${fieldValue(bean: constantInstance, field: "cmBoolean")}</td>
                        </g:elseif>

                        <td>
                            <g:link controller="admin" action="editConstant" params="[id: constantInstance.id]">
                                ${message(code: 'constantModerator.edit.label')}
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </g:else>

            </tbody>
        </table>
    </div>

</div>
</body>
</html>
