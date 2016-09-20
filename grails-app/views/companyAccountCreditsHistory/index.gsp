<!doctype html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">
    <br/>

    <div class="large-9 columns">
        <h2>${message(code: 'company.creditHistory.label')}</h2>

    </div>


    <div class="large-3 columns">
        <br/>
        <g:link controller="companyAccountServices" action="requireCredits" class="button  right">
            <g:message code="company.credit.reqest.label"/>
        </g:link>

    </div>




    <hr>

</div>

<div class="row">

    <div class="large-8 columns">
        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="serviceName" title="${message(code: 'company.serviceName.label')}"/>
                <g:sortableColumn property="datePurchased" title="${message(code: 'company.datePurchased.label')}"/>
                <g:sortableColumn property="credits" title="${message(code: 'company.credits.label')}"/>
                %{--            <g:sortableColumn property="user" title="${message(code: 'company.user.label')}"/>--}%
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
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:message code="${purchase.serviceNameParams}"/></td>
                        <td><g:formatDate format="dd.MM.yyyy" date="${purchase.datePurchased}"/></td>
                        <g:if test="${purchase.credits < 0}">
                            <td class="removing">${fieldValue(bean: purchase, field: "credits")}</td>
                        </g:if>
                        <g:else>
                            <td class="adding">${fieldValue(bean: purchase, field: "credits")}</td>
                        </g:else>
                    %{--                <td>${fieldValue(bean: purchase, field: "user")}</td>--}%
                        <td>
                            <g:if test="${purchase?.details}">
                                <g:message code="${purchase.details}" args="[purchase.detailsParams]"/>
                            </g:if>
                            <g:else>
                                <g:message code="${purchase.comment}"/>
                            </g:else>
                        </td>
                    </tr>
                </g:each>

            </g:else>
            </tbody>
        </table>

        <g:paginateFoundation total="${purchaseListCount}"/>

    </div>


    <div class="large-4 columns">

        <div class="panel">

            <h4><g:message code="company.credit.state"/></h4>
            <hr>
            <h5>

                <g:message code="company.credits.to.date"/> <g:formatDate type="date" style="MEDIUM"
                                                                          date="${new Date()}"/>:

            </h5>

            <h5 style="text-align: right">${companyInstance.credits}        <g:message
                    code="company.credits.state.label"/>
            </h5>
        </div>


        <div class="panel">

            <h4><g:message code="company.credit.request.header"/></h4>
            <hr>

            <p>

                <g:message code="company.credit.request.infotext"/>

            </p>

        </div>
    </div>

</div>

</body>
</html>




