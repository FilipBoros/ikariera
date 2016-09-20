<g:form method="get" controller="adminCompany">

    <div class="row">
        <div class="large-4 columns">
            <g:render template="/layouts/_fields/input" model="[

                    name: 'companyName',
                    field: 'companyName',
                    placeholder: message(code: 'company.companyName.label'),
                    value: params?.companyName]"/>
        </div>



        <div class="large-4 columns">
        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: '',
                placeholder: message(code: 'job.category'),

                name: 'mainJobCategories',
                field: 'mainJobCategories',
                value:  params?.mainJobCategories,

                from: cz.ikariera.company?.JobCategory?.list(sort: 'posOrder')]"/>




    </div>

        <div class="large-4 columns">

            <div class="row collapse">

                <div class="small-10 columns">
                    <g:render template="/layouts/_fields/input" model="[

                            name: 'companyID',
                            field: 'companyID',
                            placeholder: message(code: 'company.companyID.label'),
                            value: params?.companyID]"/>
                </div>

                <div class="small-2 columns ">

                    <button class="button postfix ">${message(code: 'search')}</button>
                </div>
            </div>

        </div>
    </div>

</g:form>
