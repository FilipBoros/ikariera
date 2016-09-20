
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div style="background-color: white">

    <div class="row ">
        <div class="large-12 columns">

            <h1><g:message code="partners.label" /> </h1>

        </div>

    </div>

</div>



<g:render template="partnerList" model="[
        generalPartnerList: generalPartnerList,
        medialPartnerList: medialPartnerList,
        partnerList: partnerList,
        coverPartnerList: coverPartnerList,
        supportPartnerList: supportPartnerList]"/>

</body>
</html>
