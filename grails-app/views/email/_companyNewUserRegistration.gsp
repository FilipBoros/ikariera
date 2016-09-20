<g:render template="/email/style/style"/>

<div style=" width: 800px;">

  <div>
        <g:message code="emailTemplate.registration.label"/>
  </div>


    <div>
        <g:message code="emailTemplate.registration.label2"/>
        <a style=" color: #0079C5;" href="http://www.ikariera.cz" title="www.ikariera.cz">
            <g:message code="emailTemplate.registration.link"/>.
        </a>
        <g:message code="emailTemplate.registration.link3"/>
    </div>


    <br/>
    <table>
        <tr>
            <td width="200" style="color: #0079C5"><g:message code="emailTemplate.registration.username.label"/></td>

            <td>${user.username}</td>

        </tr>

        <tr>
            <td style="color: #0079C5; "><g:message
                    code="emailTemplate.registration.passwd.label"/></td>
            <td><g:message code="emailTemplate.registration.passSecred"/></td>

        </tr>
    </table>
    <br/>


    <div>
        <g:message code="emailTemplate.registration.activate1"/>   <br/>
        <g:link url="http://www.ikariera.cz/confirm-company-user/${user.token}" absolute="true">
            http://www.ikariera.cz/confirm-company-user/${user.token}</a>
        </g:link>
    </div>

    <br/>
    <br/>

    %{--<h2><g:message code="emailTemplate.registration.link"/> </h2>--}%
    <g:message code="emailTemplate.registration.info1"/>

    <br/>

    <g:render template="/email/info"/>


    <br/>
    <g:render template="/email/contact"/>

</div>

