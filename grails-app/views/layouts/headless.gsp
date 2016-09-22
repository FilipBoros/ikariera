<!doctype html>
<html prefix="og: http://ogp.me/ns#">
<head>

    <g:set var="entityName" value="iKariera.cz"/>

    <asset:stylesheet src="application.css"/>

    <asset:javascript src="main.js"/>
    <asset:javascript src="application.js"/>

    <g:render template="/layouts/mainLayout/pageHead"/>
    <g:render template="/layouts/mainLayout/googleAnalytics"/>

    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width"/>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta property="og:image" content="${resource(dir: '/images/ikariera', file: 'ikariera-og.png',absolute: true )}" />

    <link rel="shortcut icon" href="${resource(dir: '/images/ikariera', file: 'ikariera.png', absolute: true)}" type="image/x-icon">

    <link href=" http://fonts.googleapis.com/css?family=Open+Sans:300italic,300,400italic,400,600italic,600,700italic,700,800italic,800&subset=latin,latin-ext" rel="stylesheet" type="text/css">


    <g:render template="/layouts/mainLayout/googleAnalytics"/>

    <g:layoutHead/>


</head>

<body>

<div class="wrapper">

<g:render template="/layouts/sharedLayout/maintanceMode" />

<sec:ifAnyGranted roles="ROLE_STUDENT">

    <g:render template="/layouts/studentLayout/loggedTopMenu"/>

</sec:ifAnyGranted>


<sec:ifAnyGranted roles="ROLE_COMPANY">

    <g:render template="/layouts/companyLayout/loggedTopMenu"/>

</sec:ifAnyGranted>



<sec:ifAnyGranted roles="ROLE_ADMIN">

    <g:render template="/layouts/adminLayout/loggedTopMenu"/>
</sec:ifAnyGranted>



<sec:ifNotLoggedIn>

%{--    <g:render template="/layouts/mainLayout/loggedTopMenu"/>--}%

</sec:ifNotLoggedIn>


<div class="menu-part">

    <g:render template="/layouts/mainLayout/mainMenu"/>

</div>



<g:render template="/layouts/mainLayout/flashMessages"/>


<div style="min-height: 400px">

    <g:layoutBody/>

</div>


<div class="footer">

    <g:render template="/layouts/mainLayout/footer"/>

</div>





</div>


%{-- sticky footer --}%
<g:render template="/layouts/sharedLayout/bottomStickyBar"/>







<script>
    $(document).foundation();



</script>



<g:render template="/layouts/mainLayout/dialogs"/>

</body>
</html>