<g:render template="/email/style/style"/>

<div style=" width: 800px;">

    <h2>
        <g:message code="admin.enableCompanyText"/>
    </h2>




    <br/>
    <br/>

    %{--<h2><g:message code="emailTemplate.registration.link"/> </h2>--}%
    <g:message code="emailTemplate.registration.info1"/>


    <g:render template="/email/info"/>



    <br/>
    <g:render template="/email/contact"/>

</div>

