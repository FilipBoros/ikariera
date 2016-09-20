<div class="row">

<div class="large-4 columns">

    <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

</div>

<div class="large-8 columns">

<h2>
    ${message(code: 'publishService.h2')}
</h2>


<p>
    ${message(code: 'publishService.description')}
</p>
<p>

<table class="table">

    <tr>

        <td style="width: 80%">
            <strong>
                ${message(code: 'publishService.activated')}
            </strong>
        </td>
        <td>
            ${companyInstance?.servicesExpire?.publishServiceActivate}
        </td>

    </tr>


    <tr>

        <td>
            <strong>
                ${message(code: 'publishService.periodOfPublishing')}
            </strong>
        </td>
        <td>

            ${companyInstance?.servicesExpire?.periodOfPublishing} ${message(code: 'days')}
        </td>

    </tr>


    <tr>

        <td>
            <strong>
                ${message(code: 'publishService.amount')}
            </strong>
        </td>
        <td>
<g:if test="${heroImageList?.size()==0}">
    ${message(code: 'infinity')}
</g:if>
<g:else>
    ${companyInstance?.servicesExpire?.amountOfJobOffers}
</g:else>
        </td>

    </tr>

    <tr>

        <td>
            <strong>
                ${message(code: 'publishService.amount.this.period')}
            </strong>
        </td>
        <td>

            ${companyInstance?.servicesExpire?.counterJobOfferPublished}
        </td>

    </tr>


</table>





<g:if test="${expirationTime < new Date()}">

    <h4><g:message code="company.service.activation" /> </h4>

    %{--<p><g:message code="company.services.will.be.activated" />:<g:formatDate type="date" style="long"--}%
                                         %{--date="${new Date() + servicesInstance?.defaultExpirationTime}"/>--}%
    </p>

    <table class="table">

        <tr>
            <td style="width: 80%">
                  <g:message code="company.credit.state" />
            </td>

            <td>
                ${companyInstance?.credits}
            </td>

        </tr>

%{--        <tr>
            <td>
                 <g:message code="company.prolong.price" />
            </td>

            <td>
                --}%%{--${servicesInstance?.creditPrice > 0 ? servicesInstance?.creditPrice : message(code: 'forFree')}--}%%{--
            </td>

        </tr>

        <tr>
            <td>
                <strong>
                 <g:message code="company.credit.final.statement" />

                </strong>
            </td>

            <td>

                <strong>
                    --}%%{--${companyInstance?.credits - servicesInstance?.creditPrice}--}%%{--

                </strong>

            </td>

        </tr>--}%

    </table>

%{--
    <g:form action="save">

        --}%%{--<g:hiddenField name="uniqueName" value="${servicesInstance.uniqueName}"/>--}%%{--
        <button class="button "> <g:message code="service.activate.label" /> </button>

    </g:form>--}%


    <br>

</g:if>

%{--<g:if test="${expirationTime >= new Date()}">

    <h4><g:message code="service.is.activated" /> </h4>

    <p> <g:message code="company.service.is.activated.until" /> <g:formatDate date="${expirationTime}" type="date" style="long"/>

    <g:message code="company.service.until.expiration.remains"  />
    <g:daysBetween endDate="${expirationTime}" startDate="${new Date()}"/> <g:message code="company.service.until.expiration.remains.days"  />.

    </p>


    <div class="progress success round">
        <span class="meter"
              style="width: ${inversePercentageBetween(daysBetween: daysBetween(endDate: expirationTime, startDate: new Date()), defaultExpirationTime: servicesInstance?.defaultExpirationTime)}%">
        </span>
    </div>




    <br>

    <h4><g:message code="company.service.prolong" /> </h4>


    <p><g:message code="company.service.will.be.prolonged" />: <g:formatDate type="date" style="long"
                                                                             date="${new Date() + servicesInstance?.defaultExpirationTime}"/>
    </p>


    <table class="table">

        <tr>
            <td style="width: 80%">


                <g:message code="company.credit.state" />
            </td>

            <td>
                ${companyInstance?.credits}
            </td>

        </tr>

        <tr>
            <td>
                <g:message code="company.prolong.price" />

            </td>

            <td>
                ${servicesInstance?.creditPrice > 0 ? servicesInstance?.creditPrice : message(code: 'forFree')}
            </td>

        </tr>

        <tr>
            <td>
                <strong>
                    <g:message code="company.credit.final.statement" />


                </strong>
            </td>

            <td>

                <strong>
                    ${companyInstance?.credits - servicesInstance?.creditPrice}

                </strong>
            </td>

        </tr>

    </table>



    <g:form action="save">

        <g:hiddenField name="uniqueName" value="${servicesInstance.uniqueName}"/>
        <button class="button "> <g:message code="" /><g:message code="company.service.prolong" /> </button>

    </g:form>

</g:if>--}%

</div>

</div>

