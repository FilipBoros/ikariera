<!doctype html>
<html>
<head>

    <g:set var="entityName" value="iKariera.cz"/>
    <title><g:layoutTitle default="${message(code: 'page.main.title')}"/></title>

    <r:require modules=" jquery, jquery-ui, mainStyle, application, adminAjaxCalls, fileInput , chosenSelector"/>

    <asset:javascript src="vendor/jquery.js"/>
    <asset:javascript src="vendor/jquery-ui.js"/> %{--V ikariere 2 je to v require module, ale neviem kde presne to ma byt--}%
    <asset:javascript src="foundation/foundation.js"/>
    %{--<asset:javascript src="contactDetails.js"/>--}% %{--V ikariere 2 je to v require module, ale neviem kde presne to ma byt--}%
    <asset:javascript src="fileinput/jquery.fileinput.min.js"/>
    <asset:javascript src="ckeditor/ckeditor.js"/>
    <asset:javascript src="chosen_v1.1.0/chosen.jquery.js"/>

    <asset:javascript src="application.js"/>

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
