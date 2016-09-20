<g:render template="/email/style/style"/>

<div style=" width: 800px;">

    <div>
        <g:message code="emailTemplate.registration.label"/>
    </div>

<br>
    <div>

        <g:message code="account.password.reset.text"/>
        <br/>
        <br/>

        <g:link mapping="passwordReset" params="${[token: user?.token]}"
                absolute="true">${createLink(mapping: "passwordReset", params: [token: user?.token], absolute: true)}</g:link>

    </div>



    %{--<g:render template="/email/info" />--}%



    <br/>
    <g:render template="/email/contact"/>

</div>

