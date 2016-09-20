<sec:ifNotLoggedIn>

    <div class="row">

        <div class="large-12 columns">

            <h3><g:message code="user.login.label"/></h3>




            <form action="/security/login_acccess_check" method='POST' id='loginForm' class='cssform' autocomplete='on'>
                <g:if test='${flash.info}'>

                    <div style="color: white;" class='login_message'>${flash.info}</div>

                    <div style="margin: 0 20px;">
                        <a style="color: white;" href="javascript:location.reload()"><g:message
                                code="security.renter.password.label"/></a><br>
                        <g:link style="color: white;" controller="password" action="sendToken"
                                params="[email: params.email]"><g:message
                                code="security.forgottenPassword.label"/></g:link>
                    </div>

                </g:if>
                <g:else>

                    <input type='text' class='text_' name='security_username' id='username' value="${params.security_username}"
                           placeholder="${message(code: 'auth.login.placeholder')}"/>



                    <input type='password' class='text_' name='security_password' id='password'
                           placeholder="${message(code: 'auth.password.placeholder')}"/>


                    <div id="remember_me_holder">
                        <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
                               <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>

                    </div>

                    <input class="button radius tiny" type='submit' id="submit"
                           value='${message(code: "springSecurity.login.button")}'/>
                </g:else>
            </form>


        </div>
    </div>

</sec:ifNotLoggedIn>

<script type='text/javascript'>
    <!--
    (function () {
        document.forms['loginForm'].elements['security_username'].focus();
    })();
    // -->
</script>