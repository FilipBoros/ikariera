<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div class="row">

    <div class="large-12">


        <h2>${message(code: 'company.passwodChange.label')}</h2>

    </div>
          <hr>
</div>
<div class="row">

    <div class="large-12">

        <sec:ifNotLoggedIn>

          <g:render template="form" model="[userInstance: userInstance]" />
        </sec:ifNotLoggedIn>
    </div>

</div>
</body>
</html>