<!doctype html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2>${message(code: 'student.studentContact.label')}</h2>

    </div>

    <hr/>

</div>


<div class="row">

    <div class="large-7 columns">

        <g:form action="save">

            <g:hiddenField name="userVersion" value="${userInstance?.version}"/>

            <g:hiddenField name="id" value="${userInstance?.id}"/>



            <g:render template="form" model="[userInstance: userInstance]"/>



            <button name="create" class="save long">${message(code: 'default.button.save.label')}</button>

        </g:form>

    </div>

    <div class="large-5 columns">
        <div class="panel">

            <p>
                  <g:message code="student.account.contact.details.panel" />

            </p>
        </div>

    </div>
</div>

</body>
</html>