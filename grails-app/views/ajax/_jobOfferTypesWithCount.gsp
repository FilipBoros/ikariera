<h4><g:message code=" ${message(code: "jobOfferTypes.header")}" /> </h4>
<hr>


<ul style="font-size: 85%; margin: 0; list-style: none;">
    <g:each in="${jobOffersTypesCountList}"
            status="i" var="jobOfferTypeInstance">

        <g:if test="${jobOfferTypeInstance.count != 0}" >
        <li>

            <g:link controller="jobOffer" action="index" params="[jobOfferType: jobOfferTypeInstance.id]">
                <h8>
                    ${message(code: jobOfferTypeInstance.i18NameFull)}


                 [${jobOfferTypeInstance.count}]
                </h8>
            </g:link>

        </li>

        </g:if>

    </g:each>

</ul>