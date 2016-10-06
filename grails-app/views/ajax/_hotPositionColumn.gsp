<g:link mapping="${message(code: 'joboffers.link')}" >
<h3>

    <g:message code="index.header.hot.positions.label"/>

</h3>

    </g:link>
<hr>

<g:each in="${listHotPosition}"
        status="i" var="jobOfferInstance">

    <g:link class="actual-position-header" mapping="${message(code: 'joboffers.link')}"
            id="${jobOfferInstance.id}">

        <h4>
            ${jobOfferInstance?.positionName}
        </h4>
    </g:link>




    <g:link mapping="${message(code: 'companies.link')}" id="${jobOfferInstance?.company?.id}">
        <h5 style="font-weight: 300; font-size: 90%">${jobOfferInstance?.company?.companyName}</h5>
    </g:link>
    <p class="large-text-justify" style="font-size: 90%">
        <g:textStripTag
                param1="${jobOfferInstance?.jobOfferDescription}" param2="0" param3="350" param4="true"/>
        <g:link class="actual-position-header" mapping="${message(code: 'joboffers.link')}"
                id="${jobOfferInstance.id}">

        </g:link>
    </p>

</g:each>


