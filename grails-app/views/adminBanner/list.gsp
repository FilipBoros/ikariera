<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: 'baners.h2.label')}</h2>

    </div>

    <div class="large-3 columns">
        <br/>
    <g:link class="button right" controller="adminBanner" action="create" >
        ${message(code: 'admin.company.uploadBanner')}
    </g:link>

    </div>
</div>

<div class="row">

    <div class="large-12 columns">

        <hr>


            <table class="table">
                <thead>
                <tr>
                    %{--<g:sortableColumn property="company" title="${message(code: 'banner.company.label')}"/>--}%
                    <g:sortableColumn property="dateCreated" title="${message(code: 'banner.dateCreated.label')}"/>
                    <g:sortableColumn property="expirationDate"
                                      title="${message(code: 'banner.expirationDate.label')}"/>
                    <g:sortableColumn property="priority" title="${message(code: 'banner.priority.label')}"/>

                    <g:sortableColumn property="rotation" title="${message(code: 'banner.rotation.label')}"/>

                    <th style="text-align: center">${message(code: 'default.thumbnail.label')}</th>

                    <th></th>
                </tr>
                </thead>
                <tbody>
                <g:if test="${!bannerInstanceList}">
                    <tr>
                        <td colspan="5">${message(code: 'jobOffer.noData.label')}</td>
                    </tr>
                </g:if>
                <g:else>
                    <g:each in="${bannerInstanceList}" status="i" var="bannerInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            %{--<td>${bannerInstance?.company?.companyName}</td>--}%
                            <td>${formatDate(format: "dd.MM.yyyy", date: bannerInstance?.dateCreated)}</td>
                            <td>${formatDate(format: "dd.MM.yyyy", date: bannerInstance?.expirationDate)}</td>
                            <td>${bannerInstance?.priority}</td>
                            <td>${bannerInstance?.rotation}</td>
                            <td>
                                <g:render template="picture" model="[bannerInstance: bannerInstance]"/>
                            </td>

                            <td>
                                <g:render template="/adminBanner/menu"
                                          model="[bannerInstance: bannerInstance]"/>
                            </td>
                        </tr>
                    </g:each>
                </g:else>

                </tbody>
            </table>

            <g:paginateFoundation total="${bannerInstanceTotal}" params="${params}"/>
        </div>

    </div>
</body>
</html>
