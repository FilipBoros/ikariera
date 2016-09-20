
<div class="form-field">
    <label for="firstName"><g:message code="jobOffers.firstName.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: adminInstance, field: 'firstName', 'fail')} required">
        <g:textField name="firstName" maxlength="255" value="${adminInstance?.firstName}"/>
    </div>

</div>
<div class="form-field">
    <label for="lastName"><g:message code="jobOffers.lastName.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: adminInstance, field: 'lastName', 'fail')} required">
        <g:textField name="lastName" maxlength="255" value="${adminInstance?.lastName}"/>
    </div>

</div>

<div class="form-field">
    <label for="username"><g:message code="jobOffers.username.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: adminInstance, field: 'username', 'fail')} required">
        <g:textField name="username" value="${adminInstance?.username}"/>
    </div>
</div>

<div class="form-field">
    <label for="password1"><g:message code="jobOffers.password1.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: adminInstance, field: 'password1', 'fail')} required">
        <g:passwordField name="password1" maxlength="30" value=""/>
    </div>
</div>
<div class="form-field">
    <label for="password2"><g:message code="jobOffers.password2.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: adminInstance, field: 'password1', 'fail')} required">
        <g:passwordField name="password2" maxlength="30" value=""/>
    </div>
</div>