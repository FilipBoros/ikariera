<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-10 columns">

        <h1><g:message code="company.edit.message" /> </h1>

    </div>

    <div class="large-2 columns">
        <g:link controller="companyAccountEmail" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>
    <hr>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:form action="update" enctype="multipart/form-data" >

            <g:render template="/companyAccountEmail/form"/>
            <g:if test="${companyEmailsInstance?.attachment != null}">
                Current attachment: ${companyEmailsInstance?.originalName}.

                </br></br>

                <g:message code="company.message.replace.attachement" />

            </g:if>
            </br>
            </br>

            <g:hiddenField name="id" value="${companyEmailsInstance?.id}"/>

            <g:hiddenField name="version" value="${companyEmailsInstance?.version}"/>
                <button name="create"
                        class="save long"><g:message code="save" /> </button>

        </g:form>

    </div>

</div>

</body>
</html>