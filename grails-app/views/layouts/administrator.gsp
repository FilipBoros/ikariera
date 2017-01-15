<!doctype html>
<html>
<head>

    <g:set var="entityName" value="iKariera.cz"/>
    <title><g:layoutTitle default="${message(code: 'page.main.title')}"/></title>


    <asset:stylesheet src="application.css"/>
    <asset:javascript src="main.js"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="admin.js"/>
    <g:render template="/layouts/mainLayout/pageHead"/>


    <g:layoutHead/>


    <style>


    .top-bar-section .dropdown li a {

        color: black;

    }

    .top-bar-section li:not(.has-form) a:not(.button) {

        line-height: 1;
    }

    </style>

</head>

<body>




<div class="wrapper">


<g:render template="/layouts/adminLayout/loggedTopMenu"/>


<g:render template="/layouts/adminLayout/mainMenu"/>

<g:render template="/layouts/studentLayout/flashMessages"/>

<g:layoutBody/>












<g:render template="/layouts/mainLayout/dialogs"/>

</div>



%{-- sticky footer --}%


<g:render template="/layouts/sharedLayout/bottomStickyBar"/>

<script>
    $(document).foundation();


</script>

</body>
</html>
