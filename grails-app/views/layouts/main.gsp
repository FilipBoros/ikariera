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
    <g:render template="/layouts/sharedLayout/maintanceMode"/>

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




    %{--<div class="header-part">
        <div class="row show-for-medium-up">

            <div class="large-offset-7 large-5 columns">

                <g:render template="/layouts/mainLayout/topMenu"/>

            </div>
        </div>


        <div class="row">

            <div class="large-12 columns">

                <g:render template="/layouts/mainLayout/banner"/>

            </div>
        </div>

    </div>


    <div class="menu-part">
        <div class="row">

            <div class="large-12 columns">

                <g:render template="/layouts/mainLayout/mainMenu"/>
            </div>
        </div>

    </div>--}%



    <g:render template="/layouts/mainLayout/flashMessages"/>


    <div style="min-height: 400px">

        <g:layoutBody/>
    </div>



<br/>

    <div class="footer">

        <g:render template="/layouts/mainLayout/footer"/>

    </div>







    <r:layoutResources/>









</div>





<g:render template="/layouts/mainLayout/dialogs"/>

%{-- sticky footer --}%
<g:render template="/layouts/sharedLayout/bottomStickyBar"/>







<script>
    $(document).foundation();
</script>

</body>
</html>