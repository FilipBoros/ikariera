<%@ page import="cz.ikariera.company.Company; cz.ikariera.company.JobCategory; cz.ikariera.company.Locality; cz.ikariera.admin.Country" %>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'admin.heroImage.name'),
                name: 'name',
                field: 'name',
                placeholder: '',

                inputInstance: heroImageInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">

        <label for="company" class="${hasErrors(bean: heroImageInstance, field: company, 'error')}">

            <g:message code="admin.heroImage.company.id" />

            <g:select optionKey="id"
                      optionValue="companyName"

                      value="${heroImageInstance?.company?.id}"

                      placeholder=""
                      name="company"

                      from="${cz.ikariera.company.Company.list(sort: 'companyName')}"
            />

        </label>

        <small class="hide ${hasErrors(bean: heroImageInstance, field: company, 'error')}"><g:message code="general.mandatory.atribute"/></small>

    </div>
</div>




<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: message(code: 'jobOffer.heroImage.jobCategory'),
                placeholder: '',
                value: heroImageInstance?.jobCategories?.id,
                name: 'jobCategories',

                field: 'jobCategories',
                from: JobCategory.list(sort: 'posOrder')]"/>

    </div>
</div>




<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: message(code: 'jobOffer.heroImage.locality'),
                placeholder: '',
                value: heroImageInstance?.localities?.id,
                name: 'localities',
                field: 'localities',
                from: Locality.list(sort: 'posOrder')]"/>

    </div>
</div>



<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: message(code: 'jobOffer.heroImage.country'),
                placeholder: '',
                value: heroImageInstance?.countries?.id,
                name: 'countries',
                field: 'countries',
                from: Country.list(sort: 'posOrder')]"/>

    </div>
</div>








