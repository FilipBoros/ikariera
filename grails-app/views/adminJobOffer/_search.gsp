<g:form method="get" controller="adminJobOffer">

    <div class="row">
        <div class="large-4 columns">
            <g:render template="/layouts/_fields/input" model="[

                    name: 'fulltextSearch',
                    field: 'fulltextSearch',
                    placeholder: message(code: 'jobOffer.jobName.label'),
                    value: params?.fulltextSearch]"/>
        </div>


        <div class="large-4 columns">
            <g:render template="/layouts/_fields/input" model="[

                    name: 'companyName',
                    field: 'companyName',
                    placeholder: message(code: 'company.companyName.label'),
                    value: params?.companyName]"/>
        </div>


        <div class="large-4 columns">
            <div class="row collapse">

                <div class="small-8 columns">

                    <g:render template="/layouts/_fields/input" model="[

                            name: 'companyID',
                            field: 'companyID',
                            placeholder: message(code: 'company.companyID.label'),
                            value: params?.companyID]"/>
                </div>


                <div class="small-4 columns ">

                    <button class="button postfix ">${message(code: 'search')}</button>
                </div>
            </div>
        </div>
    </div>



</g:form>
