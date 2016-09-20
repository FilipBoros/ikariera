<html>
<head>
    <meta name='layout' content='main'/>

</head>

<body>

<div class="row">

    <br/>


    <div class=" medium-7 columns">
        <div class="row">
            <div class="medium-centered medium-8 columns">
                <div class="panel">

                    <h3 id="loginLabel"><g:message code="springSecurity.login.header"/></h3>

                    <hr>

                    <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='on'>

                        <label for='security_username'><g:message code="springSecurity.login.username.label"/>
                            <input type='text' class='text_' name='security_username' id='security_username' value="${params.security_username}"
                                   placeholder="${message(code: 'auth.login.placeholder')}"/>
                        </label>


                        <label for='password'><g:message code="springSecurity.login.password.label"/>
                            <input type='password' class='text_' name='security_password' id='password'
                                   placeholder="${message(code: 'auth.password.placeholder')}"/>

                        </label>

                        %{--              <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
                                             <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                      <label for='remember_me'><g:message
                                              code="springSecurity.login.remember.me.label"/></label>--}%

                        <g:link controller="password" action="index"><g:message code="forgotten.password.label" /> </g:link>
                        <div class="clearfix">
                            <button id="loginButton" name="login" class="save long right">
                                ${message(code: "springSecurity.login.button")}
                            </button>
                        </div>
                    </form>

                </div>

            </div>

        </div>

    </div>

    <div class="large-5 columns">
        <div class="panel">

            <h3><g:message code="not.registered.user" /> </h3>

            <hr>

            <g:link class="button expand success" controller="student" action="registration"><g:message code="do.registration" /> </g:link>

        </div>

    </div>

</div>

<script type='text/javascript'>
    <!--
    (function () {
        document.forms['loginForm'].elements['security_username'].focus();
    })();
    // -->
</script>
</body>
</html>
