<%@ page import="java.text.SimpleDateFormat" %>
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

        <g:link controller="adminMainLogo" action="create" class="btn btn-success"><g:message
                code="admin.uploadLogo.create"/></g:link>
    </div>
</div>

%{----}%

<div class="row-fluid">
    <div class="span12">

    <table class="table table-hover table-striped table-condensed table-bordered">
        <thead>
        <tr>
            <g:sortableColumn property="company.companyName"
                              title="${message(code: 'admin.mainLogo.column.title')}"/>
            <g:sortableColumn property="company.companyName"
                              title="${message(code: 'admin.mainLogo.column.published')}"/>
            <g:sortableColumn property="company.companyName"
                              title="${message(code: 'admin.mainLogo.column.endDate')}"/>
        </tr>
        </thead>
    <tbody>
        <g:if test="${!mainLogoInstanceList}">
            <tr>
                <td colspan="3">${message(code: 'jobOffer.noData.label')}</td>
            </tr>
            </tbody>
        </table>
        </g:if>
        <g:else>

            <g:each in="${mainLogoInstanceList}" status="i" var="mainLogoInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>
                        ${mainLogoInstance?.name}
                    </td>

                    <td>
                        <g:if test="${mainLogoInstance.publish}">
                            true
                        </g:if>
                        <g:else>
                            false
                        </g:else>
                    </td>

                    <td>
                        ${new SimpleDateFormat("dd/M/yyyy").format(mainLogoInstance.endDate)}
                    </td>


                    <td>

                        <div class="btn-group">
                            <a class="btn btn-success dropdown-toggle" data-toggle="dropdown" href="#">
                                ${message(code: 'jobOffer.action.label')}
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">

                                <li>

                                    <g:if test="${mainLogoInstance.publish}">
                                        <g:link controller="adminMainLogo" action="unpublish"
                                                params="[id: mainLogoInstance?.id]">
                                            ${message(code: 'admin.uploadLogo.unpublish')}
                                        </g:link>
                                    </g:if>
                                    <g:else>
                                        <g:link controller="adminMainLogo" action="publish"
                                                params="[id: mainLogoInstance?.id]">
                                            ${message(code: 'admin.uploadLogo.publish')}
                                        </g:link>
                                    </g:else>

                                </li>


                                <li>

                                    <g:link controller="adminMainLogo" action="deleteIt"
                                            params="[id: mainLogoInstance?.id]">
                                        ${message(code: 'admin.uploadLogo.delete')}
                                    </g:link>

                                </li>
                            </ul>
                        </div>

                    </td>

                </tr>
            </g:each>
            </tbody>
            </table>
            <g:paginateFoundatione total="${listInstanceTotal}"/>
        </g:else>

    </div>
</div>

</body>
</html>
