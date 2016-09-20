<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>


<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2><g:message code="admin.partners.label" /> </h2>
    </div>

    <div class="large-3 columns">
        <br>
        <g:link class="button right" action="create">
            <g:message code="create" default="Create"/></g:link>
    </div>


</div>

<div class="row">
    <div class="large-12 columns">

        <hr>

    <table  class="selectable-row table" >
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'partner.name.label', default: 'Name')}"/>

            <g:sortableColumn property="partnerType"
                              title="${message(code: 'partner.partnerType.label', default: 'Partner Type')}"/>

            <th><g:message code="partner.logo.label" default="Logo"/></th>

            <g:sortableColumn property="link" title="${message(code: 'partner.link.label', default: 'Link')}"/>
            <th></th>

        </tr>
        </thead>
    <tbody>

        <g:if test="${!partnerInstanceList}">
            <tr>
                <td colspan="2">No data</td>
            </tr>

        </g:if>
        <g:else>

            <g:each in="${partnerInstanceList}" status="i" var="partnerInstance">
                <tr>

                    <td>${fieldValue(bean: partnerInstance, field: "name")}</td>

                    <td>${fieldValue(bean: partnerInstance, field: "partnerType")}</td>

                    <td><img
                            src="${createLink(controller: 'media', action: 'getMedia', id: partnerInstance?.logo?.newFilename)}"/>
                    </td>

                    <td>${partnerInstance?.link}</td>

                    <td>

                        <g:link action="show" id="${partnerInstance.id}"  class="button secondary small round" >
                            <i class="fi-wrench"></i>
                        </g:link>


                    </td>

                </tr>
            </g:each>

        </g:else>


    </tbody>
    </table>
        <ul class="pagination">
            <g:paginateFoundation total="${partnerInstanceCount ?: 0}"/>
        </ul>


    </div>

</div>

</body>
</html>
