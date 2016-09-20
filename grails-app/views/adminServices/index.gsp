<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">
        <h2>${message(code: 'services.h2.label')}</h2>

        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="name" title="${message(code: 'services.name.label')}"/>
                <g:sortableColumn property="creditPrice" title="${message(code: 'services.creditPrice.label')}"/>
                %{--<th> ${message(code: 'services.description.label')} </th>--}%
                <th>${message(code: 'services.defaultExpirationTime.label')}</th>

                <th>${message(code: 'services.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!servicesInstanceList}">
                <tr>
                    <td colspan="4">${message(code: 'jobOffer.noData.label')}</td>
                </tr>
            </g:if>
            <g:else>
                <g:each in="${servicesInstanceList}" status="i" var="serviceInstance">
                    <tr>
                        <td><g:message code="${serviceInstance?.name}"/></td>
                        <td>${serviceInstance?.creditPrice}</td>
                        %{--<td><g:message code="${serviceInstance?.description}" /></td>--}%

                        <td>${serviceInstance?.defaultExpirationTime}</td>

                        <td>
                            <g:link controller="adminServices" action="edit" params="[id: serviceInstance.id]" class="button secondary small round" >
                                <i class="fi-wrench"></i>
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
