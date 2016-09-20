<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator"/>

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">


        <h2><g:message code="admin.heroImage.logo.edit"/> </h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">



        <img src="${createLink(absolute: true, controller: 'media', action: 'getHeroMedia', id: heroImageInstance?.imageLink)}" >


        <g:form method="post" controller="adminHeroImage" action="update">

            <g:hiddenField name="id" value="${heroImageInstance?.id}"/>
            <g:hiddenField name="version" value="${heroImageInstance?.version}"/>




            <g:render template="form"/>







            <button name="create" class="btn btn-large btn-success">
                ${message(code: 'default.button.save.label')}
            </button>


        </g:form>
    </div>
</div>

</body>
</html>
