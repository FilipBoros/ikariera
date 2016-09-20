<%@ page import="cz.ikariera.company.JobOfferPositionLevel; cz.ikariera.company.JobOfferPosition; cz.ikariera.company.JobOfferType; cz.ikariera.company.JobOffer" %>





<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffer.name.label') + '*',
                name: 'positionName',
                field: 'positionName',
                placeholder: '',
                inputInstance: jobOfferInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>






<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: message(code: 'jobOffer.jobCategory.constrains.label')+ '*',
                placeholder: '',
                value: jobOfferInstance?.jobCategories,
                name: 'jobCategories',
                field: 'jobCategories',
                inputInstance: jobOfferInstance,
                errorMessage: message(code: 'general.mandatory.atribute'),
                from: cz.ikariera.company?.JobCategory?.list(sort: 'posOrder')]"/>


    </div>

</div>



%{--
    <div class="form-field" id="jobStartDateBlock"
         style="display: ${jobOfferInstance?.advertisementType?.key == 'D' ? 'none' : 'block'}">
        <label for="jobStartDate"><g:message code="jobOffer.jobStartDate.label"/></label>

        <div class="empty-input ${hasErrors(bean: jobOfferInstance, field: 'jobStartDate', 'fail')} required">
            <g:datePicker name="jobStartDate" precision="month" value="${jobOfferInstance?.jobStartDate}"/>
        </div>
--}%%{--        <g:render template="/layouts/formLayout/messages/errorMessage"
                  model="['errorMessage': message(code: 'jobOffer.form.jobStartDate.error')]"/>
        <g:render template="/layouts/formLayout/messages/infoMessage"
                  model="['infoMessage': message(code: 'jobOffer.form.jobStartDate.message')]"/>--}%%{--

    </div>--}%




%{--    <div class="form-field" id="graduatePositionBlock"
         style="display: ${jobOfferInstance?.advertisementType?.key == 'J' ? 'block' : 'none'}">
        <label for="graduatePosition"><g:message code="jobOffer.graduatePosition.label"/></label>

        <div class="checkbox">
            <g:checkBox name="graduatePosition" value="${jobOfferInstance?.graduatePosition}"/>
        </div>

        <div class="checkbox-form-label">&nbsp;
        </div>
    </div>--}%

%{--
<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/checkbox" model="[
                label: message(code: 'jobOffer.graduatePosition.label'),
                field: 'graduatePosition',
                name: 'graduatePosition',
                placeholder: '',
                inputInstance: jobOfferInstance,
                errorMessage: message(code: 'jobOffer.graduatePosition.label')]"/>
    </div>
</div>


<div class="row">

    <div class="large-12 columns">
        <g:render template="/layouts/_fields/checkbox" model="[
                label: message(code: 'jobOffer.topPos.text'),
                field: 'topPos',
                name: 'topPos',
                placeholder: '',
                inputInstance: jobOfferInstance,
                errorMessage: message(code: 'jobOffer.top.label')]"/>
    </div>
</div>--}%


%{-- <div class="form-field">
     <label for="graduatePosition"><g:message code="jobOffer.topPos.label"/></label>

     <div class="checkbox">
         <g:checkBox name="topPos" value="${jobOfferInstance?.topPos}"/>
     </div>

     <div class="checkbox-form-label">${message(code: 'jobOffer.topPos.text', args: [cz.ikariera.company.Services.findByUniqueName('top')?.creditPrice])}
     </div>
 </div>
</div>--}%

%{-- categories --}%

%{--<div class="form-part">--}%
%{--<h2><g:message code="jobOffer.reqLang.label"/></h2>--}%
%{--<g:render template="/layouts/_fields/requiredLanguages/reqLanguages" model="['jobOfferInstance': jobOfferInstance]"/>--}%


%{--</div>--}%




<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'jobOfferDescription.label')+ '*' ,
                name: 'jobOfferDescription',
                field: 'jobOfferDescription',
                height: '200px',

                maxLength: 4000,

                placeholder: '',
                inputInstance: jobOfferInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'jobApplicantRequire.label')+ '*' ,
                name: 'jobApplicantRequire',
                field: 'jobApplicantRequire',
                placeholder: '',
                height: '200px',
                maxLength: 4000,
                inputInstance: jobOfferInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'jobTypeDescription.label')+ '*' ,
                name: 'jobTypeDescription',
                field: 'jobTypeDescription',
                placeholder: '',
                height: '200px',
                maxLength: 4000,
                inputInstance: jobOfferInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>
%{--<div class="form-part">
    <h2><g:message code="jobOfferDescription.label"/></h2>

    <div class="big-textarea  ${hasErrors(bean: jobOfferInstance, field: 'jobOfferDescription', 'fail')} required">
        <g:textArea name="jobOfferDescription" cols="70" rows="6"
                    value="${jobOfferInstance?.jobOfferDescription}"/>
    </div>

    <h2><g:message code="jobApplicantRequire.label"/></h2>

    <div class="big-textarea  ${hasErrors(bean: jobOfferInstance, field: 'jobApplicantRequire', 'fail')} required">
        <g:textArea name="jobApplicantRequire" cols="70" rows="6"
                    value="${jobOfferInstance?.jobApplicantRequire}"/>
    </div>

    <h2><g:message code="jobTypeDescription.label"/></h2>

    <div class="big-textarea  ${hasErrors(bean: jobOfferInstance, field: 'jobTypeDescription', 'fail')} required">
        <g:textArea name="jobTypeDescription" cols="70" rows="6" value="${jobOfferInstance?.jobTypeDescription}"/>
    </div>
</div>--}%

