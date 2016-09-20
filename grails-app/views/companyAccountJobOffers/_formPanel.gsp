<%@ page import="cz.ikariera.company.JobOfferType" %>
<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/select" model="[
                optionKey  : 'id',
                label      : message(code: 'jobOffer.type.label')+ '*',
                placeholder: '',
                value      : jobOfferInstance?.jobOfferType?.id,
                name       : 'jobOfferType',
                field      : 'jobOfferType',
                from       : JobOfferType.list()]"/>

    </div>


    <div class="large-12 columns">

        <g:render template="/layouts/_fields/select" model="[
                optionKey  : 'id',
                label      : message(code: 'jobOffer.positionLocality.label') + '*',
                placeholder: '',
                value      : jobOfferInstance?.positionLocality?.id,
                field      : 'positionLocality',
                name       : 'positionLocality',
                from       : cz.ikariera.company.Locality.list(sort: 'posOrder')]"/>

    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/select" model="[
                optionKey    : 'id',
                label        : message(code: 'jobOffer.positionCountry.label') + '*',
                placeholder  : '',
                inputInstance: jobOfferInstance?.positionCountry?.id,
                field        : 'positionCountry',
                name         : 'positionCountry',
                from         : cz.ikariera.admin.Country.list(sort: 'posOrder')]"/>

    </div>
</div>


<div class="row">

    <div class="large-12 columns">

        %{--class="${hasErrors(bean: jobOfferInstance, field: jobStartDate, 'error')}"--}%
        <label for="jobStartDate">

            ${message(code: 'jobOffer.jobStartDate.label')} *
            <g:datePicker name="jobStartDate" precision="month" default="${new Date() + 31}"
                          value="${jobOfferInstance?.jobStartDate}"/>

        </label>

        %{--
            <small class="hide ${hasErrors(bean: jobOfferInstance, field: jobStartDate, 'error')}">${message(code: 'jobOffer.form.jobStartDate.error')}</small>
        --}%

    </div>
</div>






