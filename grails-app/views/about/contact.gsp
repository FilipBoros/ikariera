<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div class="row">

    <div class="large-12 columns">
<h1><g:message code="about.contact"/></h1>

<div>
    <g:render template="displayContact"/>
</div>


<g:form action="sendEmail">
    <fieldset class="form">
        <g:render template="form" model="['userInstance': userInstance]"/>
        <g:render template="/layouts/mainLayout/captchaSegment"/>
        <g:hiddenField name="id" value="${userInstance?.id}"/>
    </fieldset>
    <fieldset class="buttons">
        <button name="create" class="save long">
            ${message(code: 'about.send')}
        </button>
    </fieldset>

</g:form>
    </div>

</div>
</body>
</html>
