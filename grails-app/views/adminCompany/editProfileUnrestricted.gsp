<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2><g:message code="admin.catalogue.list.label"/></h2>




    </div>
    <hr>
</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" controller="adminCompany" action="companyProfileUnrestrictedSaveChanges">
            <g:hiddenField name="id" value="${companyInstance?.id}"/>
            <g:hiddenField name="version" value="${companyInstance?.version}"/>

            <h2>${message(code: 'company.profileChange.label')}</h2>
            <g:render template="form"/>
            <g:hiddenField name="returnAddress" value="${params?.returnAddress}"/>
            <g:hiddenField name="returnPath" value="${params?.returnPath}"/>

            <button action="updateProfile" class="save float">${message(code: 'company.update.label')}</button>


        </g:form>

    </div>

</div>

</body>
</html>