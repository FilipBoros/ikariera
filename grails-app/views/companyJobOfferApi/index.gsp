<%@ page import="cz.ikariera.company.Services" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br>

    <div class="large-12 columns">

        <h1><g:message code='services.api.jobOffer.header'/></h1>

    </div>
</div>


<div class="row">

    <div class="large-12 columns">
        <label for="apikey"><g:message code='services.api.jobOffer.apikey'/></label>
        <g:textField name="apikey" readonly="true" value="${companyInstance?.APIKEY}"/>
        <g:link controller="companyJobOfferApi" action="generateNewApiKey" class="button">
            <g:message code='services.api.jobOffer.generate'/>
        </g:link>
    </div>

</div>


<br/>
<br/>

</body>
</html>




