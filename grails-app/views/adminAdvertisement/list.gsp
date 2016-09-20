<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: 'admin.advertisement.labeladv')}</h2>
    </div>
        <div class="large-3 columns">
            <br/>
        <g:link controller="adminAdvertisement" action="create" class="button right">
            ${message(code: 'admin.advertisement.add')}
        </g:link>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <hr>



        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="header" title="${message(code: 'banner.name.label')}"/>
                <g:sortableColumn property="dateCreated" title="${message(code: 'banner.dateCreated.label')}"/>
                <g:sortableColumn property="willExpire" title="${message(code: 'banner.expirationDate.label')}"/>
                <th>${message(code: 'banner.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!advertisementInstanceList}">
                <tr>
                    <td colspan="4">${message(code: 'jobOffer.noData.label', default: 'NO DATA')}</td>
                </tr>
            </g:if>
            <g:else>
                <g:each in="${advertisementInstanceList}" status="i" var="advertisementInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${advertisementInstance.header}</td>
                        <td>${formatDate(format: "dd.MM.yyyy", date: advertisementInstance.dateCreated)}</td>
                        <td>${formatDate(format: "dd.MM.yyyy", date: advertisementInstance.dateExpire)}</td>
                        <td>

                            <g:render template="/adminAdvertisement/menu"
                                      model="[advertisementInstance: advertisementInstance, returnAddress: request.forwardURI,
                                              returnPath: (request.forwardURI - request.contextPath)]"/>


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
