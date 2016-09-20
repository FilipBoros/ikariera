<%@ page import="cz.ikariera.admin.Faq" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>



<div class="row">

    <div class="large-12">

        <h2><g:message code="faq.button.create.label" /></h2>


        <g:form action="save">

            <g:render template="/adminFaq/form"/>

        %{--<g:submitButton name="create" class="save long" value="${message(code: 'default.button.create.label', default: 'Create')}" />--}%
            <button class="save long" name="create">${message(code: 'faq.button.create.label')}</button>

        </g:form>
    </div>
</div>
</body>
</html>
