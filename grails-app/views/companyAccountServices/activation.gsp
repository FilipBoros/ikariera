<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">
    <br>

    <div class="large-10 columns">

        <h1>
            <g:message code="company.service.activation"/>
        </h1>
    </div>

    <div class="large-2 columns">
        <g:link controller="companyAccountServices" action="index"
                class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>

    <hr>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="service"
                  model="[expirationTime: expirationTime, servicesInstance: servicesInstance, companyInstance: companyInstance]"/>

    </div>

</div>

<br>

</body>
</html>