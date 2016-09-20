

<h2>${message(code: 'companyDetail.companyJobOffers.label')}</h2>

<table class="tabled-list">
    <thead>
    <tr>
        <g:sortableColumn property="positionName"
                          title="${message(code: 'jobOffer.positionName.label')}" params="${params}"/>

        <g:sortableColumn property="positionLocality"
                          title="${message(code: 'jobOffer.positionLocality.label')}"
                          params="${params}"/>

        <th><g:message code="jobOffer.jobCategory.label"/></th>

        <g:sortableColumn property="datePublished"
                          title="${message(code: 'jobOffer.jobPublished.label')}" params="${params}"/>

        <th style="text-align: center"><g:message code="jobOffer.dog.label"/></th>
    </tr>
    </thead>
    <tbody>
    <g:if test="${!companyInstance?.jobOffer}">
        <tr>
            <td colspan="5">${message(code: 'companyAccount.noEntries.label')}</td>
        </tr>
    </g:if>
    <g:else>
        <g:each in="${companyJobOfferList}" status="i" var="jobInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td class="first"><g:link controller="jobOffer" action="detail"
                                          id="${jobInstance.id}">${jobInstance?.positionName}</g:link>
                    <div class="company-name">
                        ${jobInstance?.company?.companyName}
                    </div>
                </td>
                <td>
                    ${message(code: jobInstance?.positionLocality?.i18NameFull)}
                </td>
                <td>
                    <g:each in="${jobInstance?.jobCategories}" status="ii" var="category">
                        ${message(code:  category?.i18NameFull)}
                        <g:if test="${ii < jobInstance?.jobCategories?.size() - 1}">,</g:if>
                        <br/>
                    </g:each>
                </td>
                <td>
                    <g:formatDate format="dd.MM.yyyy" date="${jobInstance?.datePublished}"/>
                </td>
                <td>
                    <span class="favorite silver" style="margin:  0 auto;" AdvertisementID="${jobInstance?.id}"></span>
                </td>
            </tr>
        </g:each>
    </g:else>
    </tbody>
</table>
<div class="pagination">
    <g:message code="default.table.list.pagination.text.part1"/>
    <span class="red">
        ${displayedResults}
    </span>
    <g:message code="default.table.list.pagination.text.part2"/>
    <span class="red">
        ${companyJobOfferListCount}
    </span>
    <g:message code="default.table.list.pagination.text.part3"/>
    <div class="pagination-right-navigation">
        <g:paginate total="${companyJobOfferListCount}" params="${params}"
                    maxsteps="4"
                    next="${message(code: "list.paginate.next")}"
                    prev="${message(code: "list.paginate.prev")}"/>
    </div>
</div>