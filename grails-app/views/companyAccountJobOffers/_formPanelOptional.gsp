<%@ page import="cz.ikariera.company.JobOfferPosition; cz.ikariera.company.JobOfferPositionLevel" %>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey  : 'id',
                label      : message(code: 'reqLanguageSkills.label'),
                placeholder: '',
                value      : jobOfferInstance?.requieredLanguages?.id,
                name       : 'requieredLanguages',
                field      : 'requieredLanguages',

                from       : cz.ikariera.student?.LanguageType?.list(sort: 'posOrder')]"/>

    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        %{-- class="${hasErrors(bean: jobOfferInstance, field: contactDetails, 'error')}" --}%
        <label for="contactDetails">

            <g:message code="contact.label"/>

            <span data-tooltip aria-haspopup="true" class="has-tip"
                  title="${message(code: 'info.jobOffer.contact')}">?</span>

            <g:select optionKey='id'
                      noSelection="${['null': message(code: 'company.jobOffer.hideContactDetails')]}"
                      value="${jobOfferInstance?.contactDetails?.id}"

                      placeholder=''

                      name='contactDetails'
                      optionValue="name"
                      from="${contactList}"/>

        </label>
        %{--

                    <small class="hide ${hasErrors(bean: jobOfferInstance, field: contactDetails, 'error')}">${errorMessage}</small>

        --}%
    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/selectNoSelection" model="[
                optionKey  : 'id',
                label      : message(code: 'jobOffer.position.label'),
                placeholder: '',
                value      : jobOfferInstance?.jobOfferPosition?.id,
                name       : 'jobOfferPosition',
                labelNoSelection : '',
                field      : 'jobOfferPosition',
                from       : JobOfferPosition.list()]"/>

    </div>
</div>


<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/selectNoSelection" model="[
                optionKey  : 'id',
                labelNoSelection : '',
                label      : message(code: 'jobOffer.positionLevel.label'),
                placeholder: '',
                value      : jobOfferInstance?.jobOfferPositionLevel?.id,
                name       : 'jobOfferPositionLevel',
                field      : 'jobOfferPositionLevel',
                from       : JobOfferPositionLevel.list()]"/>

    </div>
</div>




