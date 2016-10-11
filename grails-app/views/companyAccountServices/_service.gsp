<div class="row">

<div class="large-4 columns">

    <asset:image class="th" src="/ikariera/no-profile-image2.png"/>

</div>

<div class="large-8 columns">

<h2>
    <g:message code="${servicesInstance?.name}"/>
</h2>


<p>
    <g:message code="${servicesInstance?.description}"/>
</p>
<p>

%{-- begin of rest api doc ... --}%

<g:if test="${servicesInstance?.name == 'company.service.remoteJobOffers'}">

<button class="hide1"><g:message code='services.api.documentation'/></button>

</p>

<div class="doc1">

    <h2><g:message code='services.api.documentation'/></h2>
<hr/>

    <h3>Create new job offer</h3>
<p>
This is not public method, it requires apikey.

POST /api-remote-job-offer

Creates a new unpublished job offer.</p>
    <h3>Parameters</h3>
<p>apikey = unique company key for API</p>

    <p>POST Data |hasContacts |Boolean value indicates whether contacts data were sent and should be associated with this job offer|| |send contact information this way, works only if hasContacts set to true||| |contact_name |Contact person name. |string| |contact_telephone |Contact person telephone number |string| |contact_email |Contact person email |string| |contact_detailText |Some detail description about contact person |string| |contact_position |Contact person position |string|</p>

    <p>|graduatePosition: |Boolean value, indicates whether this position is for graduates |boolean |jobApplicantRequire: |Text. Requirments to applicicant |string [required max 4000] |jobCategories: |Job Category ID. May have multiple values. (jobCategories=3&jobCategories=5) |array of positive integers |jobOfferDescription: |Text. Job offer description |string [required max 4000] |jobOfferType: |Job Offer type ID. Types specified lower. |positive integer |jobStartDate: |Date. Position start day. (format yyyy-mm-dd) |date |jobTypeDescription: |Text. Short job offer discription. |string [required max 4000] |positionCountry: |Country where this position is actual. Required if locality is not set. |positive integer |positionLocality: |Localita. Works only for Czech Republic. Required if country is not set. |positive integer |positionName: |Name of the position. |string [required max 300 symbols] |requieredLanguages: |Desired language knowledge. May have multiple values. (requieredLanguages=5&requieredLanguages=4) |array of positive integers</p>
    <h3>Resource URL</h3>

<p>http://ikariera.cz/api-remote-job-offer?apikey=YOURCOMPANYKEY</p>
    <h3>Result</h3>

<p>status : 201 Returns the ID of created job offer.

Example: { "id" : 2 }</p>
    <h3>Error handling</h3>

<p>If data was bad or for some other reason api will respond with either HTTP status 400, 401 In the following format in JSON { 'error_message' : "some additional information about error that could be useful" 'status' "additionally status code" }</p>

    <hr/>

</div>

</g:if>
%{--end of rest api doc--}%



<table class="table">
    %{--<tr>
        <td>
            <strong>
                <g:message code='services.name.label'/>
            </strong>
        </td>
        <td>
            <g:message code="${servicesInstance?.name}"/>
        </td>
    </tr>--}%
    <tr>

        <td style="width: 80%">
            <strong>
                <g:message code='services.creditPrice.label'/>
            </strong>
        </td>
        <td>
            ${servicesInstance.creditPrice > 0 ? servicesInstance.creditPrice : message(code: 'forFree')}
        </td>

    </tr>


    <tr>

        <td>
            <strong>
                <g:message code='services.defaultExpirationTime.label'/>
            </strong>
        </td>
        <td>

            ${servicesInstance?.defaultExpirationTime > 0 ? servicesInstance?.defaultExpirationTime : "&infin;"}
        </td>

    </tr>

</table>





<g:if test="${expirationTime < new Date()}">

    <h4><g:message code="company.service.activation" /> </h4>

    <p><g:message code="company.services.will.be.activated" />:<g:formatDate type="date" style="long"
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
                ${servicesInstance.creditPrice > 0 ? servicesInstance.creditPrice : message(code: 'forFree')}
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
                    ${companyInstance?.credits - servicesInstance.creditPrice}

                </strong>

            </td>

        </tr>

    </table>


    <g:form action="save">

        <g:hiddenField name="uniqueName" value="${servicesInstance.uniqueName}"/>
        <button class="button "> <g:message code="service.activate.label" /> </button>
    </g:form>


    <br>

</g:if>

<g:if test="${expirationTime >= new Date()}">

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
                ${servicesInstance.creditPrice > 0 ? servicesInstance.creditPrice : message(code: 'forFree')}
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
                    ${companyInstance?.credits - servicesInstance.creditPrice}

                </strong>
            </td>

        </tr>

    </table>



    <g:form action="save">

        <g:hiddenField name="uniqueName" value="${servicesInstance.uniqueName}"/>
        <button class="button "> <g:message code="" /><g:message code="company.service.prolong" /> </button>

        <g:if test="${servicesInstance.uniqueName == "articles-service"}">
            <g:link controller="companyAccountArticles" action="index" class="button radius success"><g:message code="company.service.goto" /></g:link>
        </g:if>
        <g:if test="${servicesInstance.uniqueName == "top-service"}">
            <g:link controller="companyAccountJobOffers" action="index" class="button radius success"><g:message code="company.service.goto" /></g:link>
        </g:if>
        <g:if test="${servicesInstance.uniqueName == "mail-service"}">
            <g:link controller="companyAccountEmail" action="index" class="button radius success"><g:message code="company.service.goto" /></g:link>
        </g:if>
        <g:if test="${servicesInstance.uniqueName == "cv-service"}">
            <g:link controller="companyAccountCvSearch" action="index" class="button radius success"><g:message code="company.service.goto" /></g:link>
        </g:if>
        <g:if test="${servicesInstance.uniqueName == "jobofferapi"}">
            <g:link controller="companyJobOfferApi" action="index" class="button radius success"><g:message code="company.service.goto" /></g:link>
        </g:if>
        <g:if test="${servicesInstance.uniqueName == "message-service"}">
            <g:link controller="companyAccountMessage" action="jobOfferMessages" class="button radius success"><g:message code="company.service.goto" /></g:link>
        </g:if>


    </g:form>

</g:if>


</div>

</div>

