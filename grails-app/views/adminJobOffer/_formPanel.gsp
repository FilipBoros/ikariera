<div class="row">
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


        <label for="jobStartDate" class="${hasErrors(bean: jobOfferInstance, field: jobStartDate, 'error')}">

${message(code: 'jobOffer.jobStartDate.label')}
<g:datePicker name="jobStartDate" precision="month" default="${new Date() + 31}"
              value="${jobOfferInstance.jobStartDate}"/>

    </label>

    <small class="hide ${hasErrors(bean: jobOfferInstance, field: jobStartDate, 'error')}">${message(code: 'jobOffer.form.jobStartDate.error')}</small>



    </div>
</div>


<div class="row">

        <div class="large-12 columns">

            <g:render template="/layouts/_fields/multiSelect" model="[
                    optionKey  : 'id',
                    label      : message(code: 'reqLanguageSkills.label') ,
                    placeholder: '',
                    value      : jobOfferInstance?.requieredLanguages?.id,
                    name       : 'requieredLanguages',
                    field      : 'requieredLanguages',

                    from       : cz.ikariera.student?.LanguageType?.list(sort: 'posOrder')]"/>

        </div>
    </div>


    <div class="row">
        <div class="large-12 columns">

            <label for="contactDetails" class="${hasErrors(bean: jobOfferInstance, field: contactDetails, 'error')}">


                <g:message code="contact.label" />

                <span data-tooltip aria-haspopup="true" class="has-tip" title="${message(code: 'info.jobOffer.contact')}">?</span>

                <g:select optionKey='id'
                          noSelection="${['null': message(code: 'company.jobOffer.hideContactDetails')]}"
                          value="${jobOfferInstance?.contactDetails?.id}"

                          placeholder=''

                          name='contactDetails'
                          optionValue="name"
                          from="${contactList}"
                />

            </label>

            <small class="hide ${hasErrors(bean: jobOfferInstance, field: contactDetails, 'error')}">${errorMessage}</small>






        </div>




    </div>


