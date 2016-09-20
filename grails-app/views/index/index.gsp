<!doctype html>

<html>
<head>

    <meta name="layout" content="main"/>
    <meta name="keywords" content="${message(code: "mainpage.keywords")}"/>
    <title><g:message code="title.mainpage"/></title>
    <meta name="description" content="${message(code: "mainpage.description")}"/>
</head>

<body>

<g:displayMainLogo/>

<g:render template="/index/hero" model="[heroImageArrayList: heroImageArrayList, jobOfferList: jobOfferList]"/>

<div class="panel">

    <g:render template="/index/infoPart" model="[partnerCompanyInstance: partnerCompanyInstance]"/>

</div>

%{-- Categories --}%

<div class="row ">
    <div class="large-4 columns ">
        <g:render template="/index/categories"/>
    </div>

    <div class="large-4 columns ">
        <g:render template="/index/localities"/>
    </div>

    <div class="large-4 columns ">
        <g:render template="/index/jobOfferTypes"/>
    </div>

</div>
<br/>

<g:render template="/index/partnersLogos" model="[partnerInstanceList: partnerInstanceList]"/>

</body>
</html>
