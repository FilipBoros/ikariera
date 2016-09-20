<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>


<div class="row">


    <br />
    <div class="large-12">


        <h2>${message(code: 'admin.listCompanies.label')}</h2>


<hr>

</div>

</div>
<div class="row">

    <div class="large-12">

        <g:form action="updateEmail" controller="adminEmail" enctype="multipart/form-data">
            <fieldset class="form">

                <h2><g:message code="company.email.edit.header.label"/></h2>


                <g:render template="/adminEmail/form"/>

            </fieldset>

            <g:hiddenField name="id" value="${adminEmailsInstance?.id}"/>

            <fieldset class="buttons">
                <button name="create"
                        class="save long">${message(code: 'default.button.create.label')}</button>
            </fieldset>

        </g:form>

    </div>

</div>

</body>
</html>