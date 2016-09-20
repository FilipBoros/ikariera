<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-10 columns">

        <h2><g:message code="company.jobOffer.edit"/></h2>

    </div>
    <div class="large-2 columns">
        <g:link controller="companyAccountJobOffers" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>
    <hr>
</div>


<div class="row">

    <g:form method="post" controller="companyAccountJobOffers" action="update">
        <div class="large-12 columns">
            <div class="large-7 columns">

                <g:hiddenField name="id" value="${jobOfferInstance?.id}"/>
                <g:render template="/companyAccountJobOffers/form" model="[jobOfferInstance: jobOfferInstance]"/>

            </div>

            <div class="large-5 columns">
                <div class="panel">
                    <g:render template="formPanel" model="[jobOfferInstance: jobOfferInstance]"/>

                </div>



                <div class="panel">
                    <g:render template="formPanelOptional"
                              model="[jobOfferInstance: jobOfferInstance, contactList: contactList]"/>

                </div>

            </div>

        </div>

        <div class="large-12 columns">

            <button class="button " name="submit" value="">${message(code: 'system.save.button')}</button>

            <button class="button " name="submit" value="publish"><g:message code="publish"/></button>



            <button class="button success" name="submit" value="top"><g:message
                    code="company.jobOffer.publish.as.top"/></button>

        </div>
    </g:form>

</div>


</body>
</html>