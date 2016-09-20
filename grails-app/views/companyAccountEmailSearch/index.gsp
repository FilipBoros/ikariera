<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-12 columns">

        <h1><g:message code="company.massMail.label" /> </h1>

    </div>
    <hr>
</div>
<g:form mapping="companyAccountEmailSearch"  params="[emailId: companyEmailsInstance.id]"  method="POST">
    <div class="row">

        <div class="large-6 columns">

            <h3><g:message code="companyEmails.criteria"/></h3>

            <g:render template="search" params="[filterParams: filterParams]"/>

            <g:hiddenField name="id" value="${companyEmailsInstance.id}"/>

        </div>


        <div class="large-6 columns">

            <div class="panel">
                <h3><g:message code="company.email.to.send" /> </h3>
                <g:render template="info"/>

            </div>


            <div class="panel">
                <h3>${message(code: 'company.email.price.label')}   </h3>
                <g:render template="overview"/>

            </div>
        </div>
    </div>

    <div class="row">

        <div class="large-12 columns">



            <button name="create" class="save long">
                ${message(code: 'default.button.search.label')}
            </button>



            <g:if test="${companyCredits - creditPrice >= 0 & creditPrice > 0}">


                <button name="send" value="true" class="save long success">
                    ${message(code: 'default.button.send.label')}
                </button>
            </g:if>


        </div>
    </div>

</g:form>

</body>
</html>