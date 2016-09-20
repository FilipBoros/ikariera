<%@ page import="cz.ikariera.admin.RemoteServer" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br>

    <div class="large-12 columns">

        <h1><g:message code="company.jobOffer.select.Contries" /> </h1>

    </div>
    <hr>
</div>

<div class="row">

    <div class="large-12 columns">

        <g:form method="post" controller="adminCompany" action="sendJobOffersTocountries">
            <g:hiddenField name="id" value="${jobOfferInstance?.id}"/>

            <label for="selectedCountries">

                Choose countries

                <g:select optionKey='id'
                          optionValue='${{it.country.name}}'

                          data-placeholder=""

                          class="chosen-select"

                          name="selectedCountries"
                          from="${cz.ikariera.admin?.RemoteServer?.list()}"
                          multiple="true"/>

            </label>
           %{-- <g:render template="/layouts/_fields/multiSelect" model="[
                    optionKey: 'id',
                    optionValue: 'country',
                    label    : 'Zeme',
                    name     : 'selectedCountries',
                    from     : cz.ikariera.admin?.RemoteServer?.list()]"/>
--}%
            <button class="save float">
                ${message(code: 'constantModerator.update.label')}
            </button>
        </g:form>

    </div>

</div>
</body>
</html>