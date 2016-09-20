<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator"/>
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>${message(code: 'admin.hero.label')}</h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form action="save" enctype="multipart/form-data">

            <g:render template="form"/>

            <g:hiddenField name="id" value="${logosInstance?.id}"/>
            <g:hiddenField name="version" value="${logosInstance?.version}"/>
            <g:hiddenField name="returnAddress" value="${params?.returnAddress}"/>
            <g:hiddenField name="returnPath" value="${params?.returnPath}"/>


            <button name="create" class="btn btn-large btn-success">
                ${message(code: 'default.button.save.label')}
            </button>
            <g:link class="btn btn-large btn-danger" url="${params.returnAddress}">
                <g:message code="constantModerator.return.label"/>
            </g:link>

        </g:form>
    </div>
</div>
</body>
</html>
