<sec:ifLoggedIn>


%{--    <li >
        <g:link controller="security" action="logout">
            <g:message code="userLoginState.singOut.label" class="user-login-state"
                       userWatchDogId="${sec.loggedInUserInfo(field: 'id')}"/>
        </g:link>

    </li>--}%







</sec:ifLoggedIn>

<sec:ifNotLoggedIn>

    <li>
        <g:link controller="login" action="auth" class="button radius alert top-bar-button">
            <g:message code="user.login.label"/>
        </g:link>
    </li>

%{--    <li class="has-form">

        <g:link mapping="studentRegistration"  class="alert button">
            <g:message code="registration"/>
        </g:link>

    </li>--}%

</sec:ifNotLoggedIn>





