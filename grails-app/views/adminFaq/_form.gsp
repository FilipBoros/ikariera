<%@ page import="cz.ikariera.admin.Faq" %>
<%@ page import="cz.ikariera.admin.Localization" %>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'faq.question.label'),
                name: 'question',
                field: 'question',
                placeholder: '',

                inputInstance: faqInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'faq.answer.label'),
                name: 'answer',
                field: 'answer',
                placeholder: '',

                inputInstance: faqInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'faq.posOrder.label'),
                name: 'posOrder',
                field: 'posOrder',
                placeholder: '',

                inputInstance: faqInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


%{--
<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: message(code: 'projects.lang.label'),
                placeholder: '',
                value: faqInstance?.languageType?.id,
                name: 'countries',
                field: 'countries',
                from: Localization.list(sort: 'posOrder')]"/>

    </div>
</div>

 --}%

<div class="form-field">
    <label for="languageType.id">
        <g:message code="projects.lang.label"/>
    </label>

    <div class="select ${hasErrors(bean: faqInstance, field: 'languageType', 'fail')}">
        <g:select name="languageType.id" id="languageType.id"
                  value="${faqInstance?.languageType?.id}"
                  optionValue="${{ it.i18Name }}"
                  optionKey="id"
                  from="${Localization.list(sort: "posOrder")}"/>
    </div>
</div>



