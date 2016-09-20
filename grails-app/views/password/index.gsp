<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>
<div class="row">

    <div class="large-12">

        <h1><g:message code="forgottenPassword.label"/></h1>

    </div>

    <hr>

</div>

<div class="row">
    <sec:ifNotLoggedIn>
        <div class="large-7 columns">

            <g:form controller="password" action="sendToken" autocomplete="off">



                <g:render template="/layouts/_fields/input" model="[
                        label      : message(code: 'forgottenPassword.email.label'),
                        field      : 'email',
                        name       : 'email',
                        placeholder: '',
                        maxLength  : 150
                ]"/>

                <button name="submit" class="save long right">
                    <g:message code="send" />
                </button>

            </g:form>

        </div>

        <div class="large-5 columns">
            <div class="panel">

                <h3><g:message code="password.reset" /> </h3>

                <p><g:message code="forgottenPassword.instruction.text"/></p>

            </div>

        </div>
    </sec:ifNotLoggedIn>

</div>
<script type='text/javascript'>
    <!--
    (function () {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>

</body>
</html>