<div class="prov-contact">
    <H2>${contactInstance?.contactName}</H2>
    <ul>
        <g:if test="${contactInstance?.contactID}">
            <li><g:message code="contactID.label"/>: ${contactInstance?.contactID}</li>
        </g:if>
        <g:if test="${contactInstance?.contactVatID}">
            <li><g:message code="contactVatID.label"/>: ${contactInstance?.contactVatID}</li>
        </g:if>
        <br/>

        <g:if test="${contactInstance?.contactStreet}">
            <li>${contactInstance?.contactStreet}</li>
        </g:if>
        <g:if test="${contactInstance?.contactZip || contactInstance?.contactCity}">
            <li>${contactInstance?.contactZip}, ${contactInstance?.contactCity}</li>
        </g:if>
        <g:if test="${contactInstance?.contactCountry}">
            <li>${contactInstance?.contactCountry}</li>
        </g:if>
        <br/>

        <g:if test="${contactInstance?.contactTelephone}">
            <li><g:message code="contactTelephone.label"/>: ${contactInstance?.contactTelephone}</li>
        </g:if>
        <g:if test="${contactInstance?.contactFax}">
            <li><g:message code="contactFax.label"/>: ${contactInstance?.contactFax}</li>
        </g:if>
        <g:if test="${contactInstance?.contactHelpdesk}">
            <li><g:message code="contactHelpdesk.label"/>: ${contactInstance?.contactHelpdesk}</li>
        </g:if>
        <g:if test="${contactInstance?.contactEmail}">
            <li><g:message code="contactEmail.label"/>: ${contactInstance?.contactEmail}</li>
        </g:if>
        <g:if test="${contactInstance?.contactWeb}">
            <li><g:message code="contactWeb.label"/>:
                <g:link url="${contactInstance?.contactWeb}">
                    ${contactInstance?.contactWeb}
                </g:link>
            </li>
        </g:if>
        <br/>

        <g:if test="${contactInstance?.contactBankNumber}">
            <li><g:message code="contactBankNumber.label"/>: ${contactInstance?.contactBankNumber}</li>
        </g:if>
        <g:if test="${contactInstance?.bankName}">
            <li><g:message code="bankName.label"/>: ${contactInstance?.bankName}</li>
        </g:if>
        <br/>

        <g:if test="${contactInstance?.contactShortText}">
            <li>${contactInstance?.contactShortText}</li>
        </g:if>
    </ul>
</div>
