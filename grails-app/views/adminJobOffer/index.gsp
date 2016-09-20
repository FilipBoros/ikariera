<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="jobOffer.list.label"/></h2>

        <hr>

    </div>

</div>


<div class="row">

    <div class="large-12 columns">
        <g:render template="search"/>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="positionName" style="width: 350px"
                                  title="${message(code: 'admin.listJobOffers.positionName')}" params="${params}"/>

                <g:sortableColumn property="company" style="width: 190px"
                                  title="${message(code: 'admin.listJobOffers.company')}"
                                  params="${params}"/>



                <g:sortableColumn property="datePublished"
                                  title="${message(code: 'jobOffer.jobPublished.label')}" params="${params}"/>

                <g:sortableColumn property="willExpire"
                                  title="${message(code: 'admin.listJobOffers.activeTo')}" params="${params}"/>

                <th><g:message code="jobOffer.top.label"/></th>

                <th><g:message code="jobOffer.printed.label"/>

                </th>

                <th><g:message code="jobOffer.action.label"/></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${jobOfferInstanceList}" status="i" var="jobOfferInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td class="first"><g:link controller="jobOffer" action="detail"
                                              id="${jobOfferInstance.id}">${jobOfferInstance?.positionName}</g:link>

                    </td>

                    <td>
                        ${jobOfferInstance?.company?.companyName}
                    </td>

                    <td><g:formatDate format="dd.MM.yyyy" date="${jobOfferInstance.datePublished}"/></td>


                    <td colactive="6"><g:formatDate format="dd.MM.yyyy" date="${jobOfferInstance.willExpire}"/></td>

                    <td style="text-align: center">${jobOfferInstance.topPos ? "T" : "-"}</td>

                    <td style="text-align: center">${jobOfferInstance.counter}
                        /

                        ${jobOfferInstance.contactCounter}</td>

                    <td>

                        <g:render template="/adminJobOffer/menu"
                                  model="[jobOfferInstance: jobOfferInstance, returnAddress: request.forwardURI,
                                          returnPath      : (request.forwardURI - request.contextPath)]"/>

                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <g:paginateFoundation total="${jobOfferInstanceTotal}" params="${params}"/>

    </div>

</div>
</body>
</html>
