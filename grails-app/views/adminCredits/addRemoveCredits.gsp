<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h1><g:message code="admin.addRemoveCredits.add.label" /> </h1>

        <h3 class="subheader">${companyInstance.companyName}</h3>

    </div>

    <hr>

</div>

<div class="row">

    <div class="large-7 columns">

        <g:form method="post" controller="adminCredits" action="updateCredits">

            <g:hiddenField name="id" value="${companyInstance?.id}"/>
            <g:hiddenField name="version" value="${companyInstance?.version}"/>
            <g:render template="form"/>

            <button class="save float">${message(code: 'constantModerator.update.label')}</button>
        %{--   <g:link class="back-button float" controller="admin" action="companyList"><g:message
                   code="constantModerator.return.label"/></g:link>--}%

        </g:form>
    </div>

    <div class="large-5 columns">
        <div class="panel">

            <h4>${companyInstance.companyName}</h4>


            <p>${message(code: "admin.addRemoveCredits.creditState.label")}: ${companyInstance.credits}</p>

            <p>${message(code: "admin.addRemoveCredits.info.text")}</p>

        </div>
    </div>
</div>
</body>
</html>
