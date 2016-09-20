<%@ page import="cz.ikariera.admin.Faq" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>${message(code: 'admin.listCompanies.label')}</h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <h2><g:message code="default.edit.label" args="['FAQ']"/></h2>



        <g:form method="post" action="update">
            <g:hiddenField name="id" value="${faqInstance?.id}"/>
            <g:hiddenField name="version" value="${faqInstance?.version}"/>
            <fieldset class="form">
                <g:render template="/adminFaq/form" model="[faqInstance : faqInstance]"/>
            </fieldset>
            <button class="save long" name="create">${message(code: 'faq.button.update.label')}</button>
        </g:form>
    </div>

</div>
</body>
</html>
