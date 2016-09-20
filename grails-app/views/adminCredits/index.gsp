<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2>${message(code: 'company.creditHistory.label')}</h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn style="width:150px" property="company"
                                  title="${message(code: 'company.company.label')}"/>

                <g:sortableColumn style="width:100px" property="datePurchased"
                                  title="${message(code: 'company.datePurchased.label')}"/>

                <g:sortableColumn style="width:80px" property="credits"
                                  title="${message(code: 'company.credits.label')}"/>
                <g:sortableColumn style="width:150px" property="user" title="${message(code: 'company.user.label')}"/>
                <g:sortableColumn style="width:250px" property="serviceName"
                                  title="${message(code: 'company.serviceName.label')}"/>
                <g:sortableColumn property="details" title="${message(code: 'company.details.label')}"/>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!purchaseList}">
                <tr>
                    <td colspan="6">${message(code: 'companyAccount.noCreditEntries.label')}</td>
                </tr>

            </g:if>
            <g:else>
                <g:each in="${purchaseList}" status="i" var="purchase">
                    <tr>

                        <td>${purchase?.company?.companyName}</td>

                        <td><g:formatDate type="date" date="${purchase?.datePurchased}"/></td>



                        <td class="${purchase?.credits < 0 ? 'removing' : 'adding'}">${purchase?.credits}</td>

                        <td>${purchase?.user}</td>
                        <td><g:message code="${purchase?.serviceNameParams}"/></td>
                        <td>
                            <g:if test="${purchase?.details}">
                                <g:message code="${purchase?.details}" args="[purchase?.detailsParams]"/>
                            </g:if>
                            <g:else>
                                <g:message code="${purchase?.comment}"/>
                            </g:else>
                        </td>
                    </tr>
                </g:each>

            </g:else>

            </tbody>
        </table>
        <g:paginateFoundation total="${purchaseListCount}"/>

    </div>
</div>

</body>
</html>
