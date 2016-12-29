<g:link mapping="${message(code: 'joboffers.link')}" >
<h3>
    %{--<g:message code="index.header.actual.positions.label"/>--}%


        <g:message code="index.header.actual.positions.label"/>

</h3>

</g:link>
<hr>



<ul style="font-size: 85%; margin: 0; list-style: none;">
    <g:each in="${listActualPosition}"
            status="i" var="jobOfferInstance">

        <li >
		
            <g:link class="actual-position-header" mapping="${message(code: 'joboffer.link')}"
                    id="${jobOfferInstance.id}">

                <h8>
                ${jobOfferInstance.positionName}
                </h8>
            </g:link>

            <br/>

        <g:link mapping="${message(code: 'company.link')}" id="${jobOfferInstance?.company?.id}">
            <h5 style="font-weight: 300; font-size: 90%">
            ${jobOfferInstance.company.companyName}

                </h5>

            </g:link>


        </li>

    </g:each>

</ul>






