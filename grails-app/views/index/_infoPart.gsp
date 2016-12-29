<div class="row ">

    <div class="large-7 columns ">

        <div class="hot-position-column automaticAjaxLoad"
             ajaxLoadingUrl="${createLink(controller: 'ajax', action: 'hotPosition')}">
        </div>
    </div>

    <div class=" large-5 columns ">

        <div class="actual-position-column automaticAjaxLoad"
             ajaxLoadingUrl="${createLink(controller: 'ajax', action: 'actualPosition')}">
        </div>

    </div>

</div>


<g:if test="${partnerCompanyInstance}">
    <div class="row ">
        <div class="large-12 columns ">

            <hr>

            <h3 style="font-weight: 300"><g:message code="company.top.employer"/></h3>




            <g:link mapping="${message(code: 'company.link')}" id="${partnerCompanyInstance?.id}">
                <h2 style="font-weight: 500" >${partnerCompanyInstance?.companyName}</h2>

            </g:link>
            <p>

                ${partnerCompanyInstance?.companyCharacteristic}

            </p>

        </div>

    </div>

</g:if>