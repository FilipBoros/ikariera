

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.companyName.label'),
                name: 'companyName',
                field: 'companyName',
                placeholder: '',

                inputInstance: companyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>




<div class="row">
    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.companyID.label'),
                name: 'companyID',
                field: 'companyID',
                placeholder: '',

                inputInstance: companyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>



    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.companyVatID.label'),
                name: 'companyVatID',
                field: 'companyVatID',
                placeholder: '',
                inputInstance: companyInstance
        ]"/>
    </div>
</div>



<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.web.label'),
                name: 'companyWeb',
                field: 'companyWeb',
                placeholder: '',
                inputInstance: companyInstance,
        ]"/>
    </div>
</div>

<div class="row">
    <div class="large-4 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.street.label') + '*',
                name: 'companyStreet',
                field: 'companyStreet',
                placeholder: '',
                inputInstance: companyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>



    <div class="large-4 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.zip.label') + '*',
                name: 'companyZip',
                field: 'companyZip',
                placeholder: '',
                inputInstance: companyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>



    <div class="large-4 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.city.label') + '*',
                name: 'companyCity',
                field: 'companyCity',
                placeholder: '',
                inputInstance: companyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.country.label') + '*',
                name: 'companyCountry',
                field: 'companyCountry',
                placeholder: '',
                inputInstance: companyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: message(code: 'company.MainCategories.label'),
                placeholder: '',
                value: companyInstance?.mainJobCategories,
                name: 'mainJobCategories',
                field: 'mainJobCategories',
                from: cz.ikariera.company?.JobCategory?.list(sort: 'posOrder')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">

%{--        <g:render template="/layouts/_fields/checkbox" model="[
                label: message(code: 'student.infoEmails.label'),
                name: 'startupBox',
                field: 'startupBox',
                inputInstance: companyInstance]"/>--}%
        <div class="row">
            <div class="large-12 columns">

                <label for="startupBox">

                    <g:checkBox name="startupBox" />

                    ${message(code: 'startup.label')}

                </label>

            </div>
        </div>
    </div>
</div>
