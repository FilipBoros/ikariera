<!doctype html>
<html>
<head>

    <g:set var="entityName" value="iKariera.cz"/>
    <title><g:layoutTitle default="${message(code: 'page.main.title')}"/></title>

    <asset:stylesheet src="application.css"/>

    <asset:javascript src="vendor/jquery.js"/>
    <asset:javascript src="vendor/jquery-ui.js"/> %{--V ikariere 2 je to v require module, ale neviem kde presne to ma byt--}%
    <asset:javascript src="foundation/foundation.js"/>
    %{--<asset:javascript src="contactDetails.js"/>--}% %{--V ikariere 2 je to v require module, ale neviem kde presne to ma byt--}%
    <asset:javascript src="fileinput/jquery.fileinput.min.js"/>
    <asset:javascript src="ckeditor/ckeditor.js"/>
    <asset:javascript src="chosen_v1.1.0/chosen.jquery.js"/>

    <asset:javascript src="application.js"/>
    <asset:javascript src="studentEducation.js"/>

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