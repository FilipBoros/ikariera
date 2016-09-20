<div class="panel detailInfo">

    <h3><g:message code="contact.label"/></h3>

    <hr>


    <a class="button expand success" data-reveal-id="contactModal" data-reveal>

        <span class="fi-mail"></span>


        Odpovědět na nabídku.

    </a>

</div>


    <div id="contactModal" class="reveal-modal" data-reveal>
        <h2>Kontaktovat společnost</h2>

        <g:form controller="studentAccountMessage" action="sendToCompany">

            <g:hiddenField name="companyId" value="${jobOfferInstance?.company?.id}"/>
            <g:hiddenField name="companyName" value="${jobOfferInstance?.company?.companyName}"/>
            <g:hiddenField name="jobOfferId" value="${jobOfferInstance?.id}"/>
            <g:hiddenField name="id" value="${jobOfferInstance?.id}"/>







            <g:render template="/layouts/_fields/input" model="[
                    label: message(code: 'Subject'),
                    field: 'subject',
                    name: 'subject',
                    placeholder: '',
                    maxLength: 150]"

                    />


            <g:render template="/layouts/_fields/textArea" model="[
                    label: message(code: 'Zpráva'),
                    field: 'text',
                    name: 'text',
                    placeholder: '',
                    maxLength: 4000,
                    height: '400px',
            ]"/>

            <a class="close-reveal-modal">&#215;</a>


            <g:submitButton class="button" name="Odeslat"/>
        </g:form>
    </div>