<g:form name="filter"
        controller="companyProfiles"
        action="index"
        method="get">

    <div class="row">
        <div class="large-4 columns">


            <g:render template="/layouts/_fields/input" model="[
                    label      : '',
                    field      : 'companyName',
                    name       : 'companyName',
                    placeholder: message(code: 'company.companyName.label'),
                    value      : params.companyName,
                    maxLength  : 150,
                    height: 30,
                    padding: 0.4
            ]"/>






        </div>

        <div class="large-3 columns">



                    <g:render template="/layouts/_fields/multiSelect" model="[
                            optionKey: 'id',
                            label: '',
                            placeholder: message(code: 'company.MainCategories.label'),

                            name: 'mainJobCategories',
                            field: 'mainJobCategories',
                            value: params.mainJobCategories,

                            from: cz.ikariera.company?.JobCategory?.list(sort: 'posOrder')]"/>

                    ${
                        println(value)
                    }


        </div>

        <div class="large-3 columns">



            <g:render template="/layouts/_fields/multiSelect" model="[
                    optionKey: 'id',
                    label: '',
                    placeholder: message(code: 'company.MainLocalities.label'),

                    name: 'localities',
                    field: 'localities',
                    value: params.localities,

                    from: cz.ikariera.company?.Locality?.list(sort: 'posOrder')]"/>

            ${
                println(value)
            }


        </div>

        <div class="large-2 columns">
            <g:submitButton name="${message(code: 'button.search')}" class="button expand  searchButton"/>

        </div>

    </div>



    <g:hiddenField name="max" value="${params.max}"/>

    <g:hiddenField name="sort" value="${params.sort}"/>

    <g:hiddenField name="order" value="${params.order}"/>
</g:form>


