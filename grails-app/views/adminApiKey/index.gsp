<%@ page import="cz.ikariera.admin.ApiKey" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>


<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2><g:message code="internal.api.keys"/></h2>

    </div>

    <div class="large-3 columns">

        <br/>
        <g:link controller="adminApiKey" action="create" class="button right">
            <g:message code="createNew"/>
        </g:link>

    </div>



</div>


<div class="row">


    <div class="large-12 columns">



        <hr>


    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="info" title="${message(code: 'apiKey.info')}"/>

                <g:sortableColumn property="serviceName"
                                  title="${message(code: 'apiKey.serviceName')}"/>

                <g:sortableColumn property="value" title="${message(code: 'apiKey.value')}"/>



                <g:sortableColumn property="expire" title="${message(code: 'apiKey.expire')}"/>

                <th>${message(code: 'user.action.label')}</th>

            </tr>
            </thead>
            <tbody>

            <g:if test="${!apiKeyInstanceList}">
                <tr>
                    <td colspan="10">${message(code: 'company.noData.label')}</td>
                </tr>

            </g:if>
            <g:else>

                <g:each in="${apiKeyInstanceList}" status="i" var="apiKeyInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td>${fieldValue(bean: apiKeyInstance, field: "info")}</td>

                        <td>${fieldValue(bean: apiKeyInstance, field: "serviceName")}</td>

                        <td>${fieldValue(bean: apiKeyInstance, field: "value")}</td>


                        <td><g:formatDate date="${apiKeyInstance.expire}" format="yyyy-MM-dd" /> </td>

                        <td><g:render template="menu" model="[apiKeyInstance: apiKeyInstance]"/></td>


                    </tr>
                </g:each>

            </g:else>

            </tbody>
        </table>
        <g:paginateFoundation total="${apiKeyInstanceCount ?: 0}"/>

    </div>
</div>
</body>
</html>
