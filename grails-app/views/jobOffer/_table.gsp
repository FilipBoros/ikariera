





<div class="jobOfferItem ${jobOfferInstance?.topPos ? 'topPos' : ''}">

    <div class="row ">

        <div class="large-12 columns">

            <g:link mapping="${message(code: 'joboffer.link')}" id="${jobOfferInstance?.id}">
                <h3 style="font-weight: 500">
                    <g:if test="${jobOfferInstance?.topPos}">
                        ${jobOfferInstance?.positionName}
                    </g:if>
                    <g:else>
                        ${jobOfferInstance?.positionName}
                    </g:else>

                </h3>
            </g:link>

        </div>

    </div>

    <div class="row ">

        <div class="large-12 columns">

            <g:link mapping="${message(code: 'company.link')}" id="${jobOfferInstance?.company?.id}">

                <h5 style="font-weight: 300">${jobOfferInstance?.company?.companyName}
                </h5>

            </g:link>
        </div>
    </div>

    <div class="row ">
        <div class="large-5 columns">
            <div style="color: #666; font-size: 80% ; margin-top: 4px">

                ${message(code: jobOfferInstance?.positionLocality?.i18NameFull)}
            </div>

        </div>


        <div class="${jobOfferInstance?.topPos? 'large-7' : 'large-5'} columns">

            <div style=" color: #666; font-size: 80%; margin-top: 4px">

                <g:each in="${jobOfferInstance?.jobCategories?.sort { it.posOrder }}" status="ii"
                        var="category">

                    <g:if test="${ii < 5}">

                        ${message(code: category?.i18NameFull)}<g:if test="${ii < jobOfferInstance?.jobCategories?.size() - 1 && ii < 4}">,</g:if>

                    </g:if>
                </g:each>

            </div>
        </div>

        <g:if test="${!jobOfferInstance?.topPos}">
            <div class="large-2 columns" style="text-align: right; color: #666; font-size: 80%">

                <g:daysToNow date="${jobOfferInstance?.datePublished}"/>

            </div>
        </g:if>

    </div>

</div>