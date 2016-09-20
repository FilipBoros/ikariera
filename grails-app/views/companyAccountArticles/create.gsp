<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-10 columns">

        <h2>${message(code: 'createJobArticle.h2.create.label')}</h2>

    </div>

    <div class="large-2 columns">
        <g:link controller="companyAccountArticles" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>
    <hr>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:form method="post" controller="companyAccountArticles" action="save">

            <g:render template="form" model="[articleInstance: articleInstance]"/>

            <button class="save long">${message(code: 'system.save.button')}</button>

        </g:form>

    </div>
</div>
</body>
</html>