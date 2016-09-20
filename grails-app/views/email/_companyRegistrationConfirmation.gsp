<g:render template="/email/style/style"/>

<div style=" width: 800px;">

    <h3>
        <g:message code="emailTemplate.registration.label"/>
    </h3>


    <p>

        na portále

        <a style=" color: #0079C5;" href="http://www.ikariera.cz" title="www.ikariera.cz">
            <g:message code="emailTemplate.registration.link"/>.
        </a>

        Vám býl právě vytvořen firemní účet pro přístup do společnosti

        ${userInstance?.company?.companyName}.

        Pro dokončení aktivace Vašeho účtu prosím klikněte na odkaz níže. Budete přesměrování na formulář pro vytvoření přístupového hesla. Odkaz bude aktivní po dobu 24h.

    </p>


    <br/>
    <table>
        <tr>
            <td width="200" style="color: #0079C5"><g:message code="emailTemplate.registration.username.label"/></td>

            <td>${userInstance?.username}</td>

        </tr>

        <tr>
            <td style="color: #0079C5; "><g:message
                    code="emailTemplate.registration.passwd.label"/></td>
            <td>   <a href="http://www.ikariera.cz/confirm-company-user/${userInstance?.token}">
                www.ikariera.cz/confirm-company-user/${userInstance?.token}</td>

        </tr>
    </table>
    <br/>




    <br/>
    <br/>

    <br/>
    <br/>

    <h2><g:message code="emailTemplate.registration.link"/></h2>
    <g:message code="emailTemplate.registration.info1"/>


    <br/>
    <g:render template="/email/contact"/>

</div>

