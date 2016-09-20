<g:form name="searchForm"
        controller="jobOffer"
        action="fulltextSearch"
        method="get">

    <g:textField name="fultextSearch"
                 value="${params.fulltextSearch}"/>

    <g:hiddenField name="max" value="${params.max}"/>

    <g:submitButton name="search" value="${message(code: 'button.search')}"/>

</g:form>
