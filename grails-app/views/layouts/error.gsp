<!doctype html>
<html>
<head>

    <g:set var="entityName" value="iKariera.cz"/>
    <title><g:layoutTitle default="${message(code: 'cz.ikariera.title.label')}"/></title>


    <title>iKariera Runtime Exception</title>

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">


    <g:render template="/layouts/mainLayout/pageHead"/>


    <g:layoutHead/>

    <r:layoutResources/>

    <r:require modules="application, applicationStyle"/>

</head>

<body>
<div class="wrapper">

    <g:render template="/layouts/mainLayout/topMenu"/>

    <g:render template="/layouts/mainLayout/banner"/>

    <g:render template="/layouts/mainLayout/mainMenu"/>


    <div class="main-content">

        <div style="text-align: center">

        </div>


        <div class="top-line">
            <div class="breadcrumb" role="navigation">
                <ul>
                    <li class="breadcrumb-divider"></li>

                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>

                    <li class="breadcrumb-divider"></li>

                    <li> <g:message code="about.us"/> </li>

                </ul>
            </div>
            <g:render template="/layouts/sharedLayout/userLoginState"/>
        </div>




        <g:layoutBody/>

    </div>

    <div class="main-content-bottom">

        <div class="float-right">

        </div>

    </div>

    <g:render template="/layouts/mainLayout/footer"/>

    <r:layoutResources/>
</div>




%{-- sticky footer --}%
<g:render template="/layouts/sharedLayout/bottomStickyBar"/>







<script>
    $(document).foundation();
</script>



<g:render template="/layouts/mainLayout/dialogs"/>

</body>
</html>