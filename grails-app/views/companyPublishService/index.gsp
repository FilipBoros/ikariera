<!doctype html>
<html>
<head>

    <meta name="layout" content="company"/>

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h1>            <g:message code="company.service.activation"/> </h1>


        <hr>

    </div>

</div>

%{----}%

<div class="row">

    <div class="large-12 columns">

        <g:render template="service" model="[companyInstance: companyInstance ]"/>

    </div>

</div>

%{----}%

<div class="row">

    <div class="large-12">

         <table class="table table-hover table-striped table-condensed table-bordered">
            <thead>
            <tr>
                <th>${message(code: 'num.of.days')}</th>
                %{--<g:sortableColumn property="jobCategory"
                                  title="${message(code: 'admin.heroImage.jobCategory')}"/>--}%
                <th>${message(code: 'num.of.published.jobOffers')}</th>
                %{--<g:sortableColumn property="locality"
                                  title="${message(code: 'admin.heroImage.locality')}"/>--}%
                <th>${message(code: 'prize')}</th>
                %{--<g:sortableColumn property="country"
                                  title="${message(code: 'admin.heroImage.country')}"/>--}%

                <th></th>

            </tr>
            </thead>
            <tbody>
            <g:if test="${!heroImageList}">
                <tr>
                    <td colspan="2">${message(code: 'jobOffer.noData.label')}</td>
                </tr>

            </g:if>
            <g:else>



                <g:each in="${heroImageList}" status="i" var="heroImageInstance">

                    <g:if test="${companyInstance?.servicesExpire?.periodOfPublishing == heroImageInstance?.numberOfDays &&
                            companyInstance?.servicesExpire?.amountOfJobOffers == heroImageInstance?.numberOfPublishedJobOffers}">
                    <tr style="background-color: #D7D7D7">
                    </g:if>
                    <g:else>
                        <tr>
                    </g:else>


                        <td>
                            ${heroImageInstance?.numberOfDays}

                        </td>


                        <td>
                            ${heroImageInstance?.numberOfPublishedJobOffers}
                        </td>



                        <td>
                            ${heroImageInstance?.prize}

                        </td>



                        <td>

                              <g:link class="button" controller="companyPublishService" action="activate" id="${heroImageInstance?.id}">${message(code: 'activate')}</g:link>

                        </td>



                    </tr>
                </g:each>


            </g:else>

            </tbody>
         </table>

        <g:paginateFoundation total="${listInstanceTotal}"/>
    </div>
</div>

</body>
</html>
