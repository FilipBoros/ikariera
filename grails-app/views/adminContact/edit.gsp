<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="contact.h2.label"/></h2>


        <hr>

    </div>

</div>

<div class="row ">

    <div class="large-12 columns">

        <g:form method="post" controller="adminContact" action="save">

            <g:render template="/adminContact/form"/>

            <button class="save">${message(code: 'constantModerator.update.label')}</button>

        </g:form>
    </div>
</div>
</body>
</html>
