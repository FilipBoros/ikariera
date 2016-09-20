<!doctype html>
<html>
<head>

    <g:set var="entityName" value="iKariera.cz"/>
    <title><g:layoutTitle default="${message(code: 'page.main.title')}"/></title>


    <r:require modules="mainStyle, application"/>



    <g:render template="/layouts/mainLayout/pageHead"/>
    <g:render template="/layouts/mainLayout/googleAnalytics"/>

    <g:layoutHead/>

    <r:layoutResources/>


</head>

<body>
<div class="wrapper">
<g:render template="/layouts/sharedLayout/maintanceMode" />


<g:render template="/layouts/companyLayout/loggedTopMenu" />

<div class="menu-part">

    <g:render template="/layouts/companyLayout/mainMenu"/>

</div>

<g:render template="/layouts/studentLayout/flashMessages"/>





<g:layoutBody/>


%{--

<div class="footer">
    <g:render template="/layouts/mainLayout/footer"/>

</div>--}%


<r:layoutResources/>


<g:render template="/layouts/mainLayout/dialogs"/>

</div>

%{-- sticky footer --}%



<g:render template="/layouts/sharedLayout/bottomStickyBar"/>







<script>
    $(document).foundation();


</script>

</body>
</html>