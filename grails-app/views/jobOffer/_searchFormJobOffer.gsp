<g:form name="filterForm"
        controller="jobOffer"
        action="index"
        method="get">

    <div class="row">
        <div class="large-4 columns">

            <g:select name="jobCategories" class="chosen-select"
                      id="jobCategories"
                      optionKey="id"


                      data-placeholder="${ message(code: 'search.in.which.field.you.want.to.work')}"

                      optionValue="${{ message(code: it.i18NameFull) }}"
                      value="${filterParams?.jobCategories}"
                      from="${cz.ikariera.company?.JobCategory?.list(sort: "posOrder")}"
                      multiple="true"/>

        </div>

        <div class="large-3 columns">

            %{--Hidden multiselects --}%
            <g:select id="positionLocalities" class="chosen-select"
                      name="positionLocalities"

                      data-placeholder="${ message(code: 'search.where.you.want.to.work')}"

                      optionKey="id"
                      optionValue="${{ message(code: it.i18NameFull) }}"
                      value="${filterParams?.positionLocalities}"
                      from="${cz.ikariera.company.Locality.list(sort: "posOrder")}"
                      multiple="true"/>

        </div>

        <div class="large-3 columns">

            <g:select id="jobOfferType" class="chosen-select"
                      name="jobOfferType"

                      data-placeholder= "${ message(code: 'search.work.type')}"

                      optionKey="id"
                      optionValue="${{ message(code: it.i18NameFull) }}"

                      value="${filterParams?.jobOfferType}"
                      from="${cz.ikariera.company?.JobOfferType?.list(sort: "specOrder")}"
                      multiple="true"/>

        </div>

        <div class="large-2 columns">
            <g:submitButton name="${message( code: 'button.search')}" class="button expand  searchButton"/>

        </div>

    </div>



    <g:hiddenField name="max" value="${params.max}"/>


    <g:hiddenField name="menuState" id="menuState" value="${params.menuState}"/>

</g:form>


