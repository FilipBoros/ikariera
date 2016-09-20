<div class="form-field">
    <label for="username"><g:message code="jobOffers.username.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: adminInstance, field: 'username', 'fail')} required">
        <g:textField name="username" maxlength="255" value="${adminInstance?.username}"/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage"--}%
    %{--model="['errorMessage': renderErrors(bean: companyUserInstance, field: 'firstName')]"/>--}%
</div>

<div class="form-field">
    <label for="firstName"><g:message code="jobOffers.firstName.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: adminInstance, field: 'firstName', 'fail')} required">
        <g:textField name="firstName" maxlength="255" value="${adminInstance?.firstName}"/>
    </div>

    <g:render template="/layouts/mainLayout/flashMessages" model="['errorMessage': renderErrors(bean: adminInstance, field: 'firstName')]" />
</div>
<div class="form-field">
    <label for="lastName"><g:message code="jobOffers.lastName.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: adminInstance, field: 'lastName', 'fail')} required">
        <g:textField name="lastName" maxlength="255" value="${adminInstance?.lastName}"/>
    </div>

    <g:render template="/layouts/mainLayout/flashMessages" model="['errorMessage': renderErrors(bean: userInstance, field: 'lastName')]" />
</div>