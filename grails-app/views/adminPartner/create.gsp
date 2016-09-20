<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">
    <div class="large-12 columns">




        <h1><g:message code="adminPartner.h1.create.label"/></h1>


        <g:form controller="adminPartner" action="save" novalidate="novalidate">

            <g:render template="form"/>

            <g:submitButton name="create" class="button save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>

            <g:link class="button" action="index"><g:message code="default.link.skip.label" default="Storno"/></g:link>

        </g:form>

    </div>
</div>

</body>
</html>
