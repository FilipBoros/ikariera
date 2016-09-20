<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'jobOffers.companyName.label'),
                name         : 'companyName',
                field        : 'companyName',
                placeholder  : '',
                disabled     : 'disabled',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'jobOffers.companyID.label'),
                name         : 'companyID',
                field        : 'companyID',
                placeholder  : '',
                disabled     : '',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>


    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'user.companyVatID.label'),
                name         : 'companyVatID',
                field        : 'companyVatID',
                placeholder  : '',
                inputInstance: companyInstance
        ]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'jobOffers.web.label'),
                name         : 'companyWeb',
                field        : 'companyWeb',
                placeholder  : '',
                inputInstance: companyInstance,
        ]"/>
    </div>
</div>

<div class="row">
    <div class="large-4 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'jobOffers.street.label') + '*',
                name         : 'companyStreet',
                field        : 'companyStreet',
                placeholder  : '',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>


    <div class="large-4 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'jobOffers.zip.label') + '*',
                name         : 'companyZip',
                field        : 'companyZip',
                placeholder  : '',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>


    <div class="large-4 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'jobOffers.city.label') + '*',
                name         : 'companyCity',
                field        : 'companyCity',
                placeholder  : '',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'jobOffers.country.label') + '*',
                name         : 'companyCountry',
                field        : 'companyCountry',
                placeholder  : '',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey    : 'id',
                label        : message(code: 'work.category'),
                placeholder  : '',
                inputInstance: companyInstance,
                name         : 'mainJobCategories',
                field        : 'mainJobCategories',
                value        : companyInstance?.mainJobCategories,
                from         : cz.ikariera.company?.JobCategory?.list(sort: 'posOrder')]"/>
    </div>
</div>

<g:if test="${companyInstance?.startup>new Date()}">
<div class="row">
    <div class="large-12 columns">
        <label>${message(code: 'company.type.label')}</label>
        ${message(code: 'startup.label')}
    </div>
</div>
<br/>
</g:if>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'company.publicEmail.label'),
                name         : 'publicEmail',
                field        : 'publicEmail',
                placeholder  : '',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>



<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label        : message(code: 'company.companyCharacteristic.label') + '*',
                name         : 'companyCharacteristic',
                field        : 'companyCharacteristic',
                placeholder  : '',
                height       : '200px',
                inputInstance: companyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>




