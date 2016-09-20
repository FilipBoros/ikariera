<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2>

            <g:message code="creditRequest.caption" />

        </h2>

    </div>

    <hr>

</div>


<div class="row">

    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>
                <th><g:message code="company.company.label"/> </th>
                <th><g:message code="company.datePurchased.label"/> </th>
                <th><g:message code="action"/> </th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${creditRequests}" status="i" var="req">
                <tr >

                    <td>
                        <g:link controller="adminCompany" action="logAsCompany" target="_blank"
                                params="[id: req.companyId]">

                            ${req?.company?.companyName}

                        </g:link>
                    </td>

                    <td>
                        ${formatDate(format: "dd.MM.yyyy", date: req.requestDate)}
                    </td>
                    <td>




                        <g:link  controller="adminCredits" action="addRemoveCredits"      params="[id: req.companyId]" class="button secondary small round right" >
                            <i class="fi-plus"></i>   ${message(code: 'admin.company.addCredit')}
                        </g:link>


                    </td>

                </tr>
            </g:each>
            </tbody>
        </table>

        <g:paginateFoundation total="${requestCount}"/>
    </div>

</div>

</body>
</html>
