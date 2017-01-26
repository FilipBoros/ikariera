<g:render template="/email/style/style"/>

<div style=" width: 800px;">

    <h2>
        <g:message code="emailTemplate.registration.label"/>
    </h2>


    <div>
        <g:message code="emailTemplate.registration.label2"/>
        <a style=" color: #0079C5;" href="http://www.ikariera.${grailsApplication.config.language}" title="www.ikariera.${grailsApplication.config.language}">
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
        <g:link url="http://www.ikariera.${grailsApplication.config.language}/confirm-user/${user.token}" absolute="true">
            http://www.ikariera.${grailsApplication.config.language}/confirm-user/${user.token}</a>
        </g:link>
    </div>

    <br/>              <br/>

    %{--<h2><g:message code="emailTemplate.registration.link"/> </h2>--}%
    <g:message code="emailTemplate.registration.info1"/>


    <g:render template="/email/info"/>



    <br/>
    <g:render template="/email/contact"/>

</div>

