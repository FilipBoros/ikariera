<!doctype html>
<html>
<head>

    <g:set var="entityName" value="iKariera.cz"/>
    <title><g:layoutTitle default="${message(code: 'page.main.title')}"/></title>

    <asset:stylesheet src="application.css"/>

    <asset:javascript src="main.js"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="studentEducation.js"/>

    <g:if test="${grailsApplication.config.language.equals('cz')}">
        <asset:stylesheet src="country/cz.css" />
    </g:if>
    <g:elseif test="${grailsApplication.config.language.equals('sk')}">
        <asset:stylesheet src="country/sk.css" />
    </g:elseif>

    <g:render template="/layouts/mainLayout/pageHead"/>
    <g:render template="/layouts/mainLayout/googleAnalytics"/>
    <g:render template="/layouts/mainLayout/pageHead"/>
    <g:render template="/layouts/mainLayout/googleAnalytics"/>

    <g:layoutHead/>



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




<g:render template="/layouts/mainLayout/dialogs"/>

</div>

%{-- sticky footer --}%



<g:render template="/layouts/sharedLayout/bottomStickyBar"/>







<script>
    $(document).foundation();


</script>

</body>
</html>