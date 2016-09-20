

<div class="form-field">
    <label for="passwordOld"><g:message code="user.passwordOld.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: userInstance, field: 'password', 'fail')} required">
        <g:passwordField name="passwordOld" maxlength="30" value=""/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage"--}%
              %{--model="['errorMessage': renderErrors(bean: userInstance, field: 'password')]"/>--}%
</div>

<div class="form-field">
    <label for="password1"><g:message code="jobOffers.password1.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: userInstance, field: 'password1', 'fail')} required">
        <g:passwordField name="password1" maxlength="30" value=""/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage"--}%
              %{--model="['errorMessage': renderErrors(bean: userInstance, field: 'password1')]"/>--}%
    %{--<g:render template="/layouts/formLayout/messages/infoMessage" model="['infoMessage': message(code: 'jobOffers.password.message')]"/>--}%
</div>

<div class="form-field">
    <label for="password2"><g:message code="jobOffers.password2.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: userInstance, field: 'password1', 'fail')} required">
        <g:passwordField name="password2" maxlength="30" value=""/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage"--}%
              %{--model="['errorMessage': renderErrors(bean: userInstance, field: 'password1')]"/>--}%
</div>

