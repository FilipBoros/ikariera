
<div class="form-field">
    <label for="addRemoveCreds"><g:message code="admin.addRemoveCredits.add.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: companyInstance, field: 'addRemoveCreds', 'fail')} required">
        <g:field type="number" name="addRemoveCreds" value="${companyInstance?.addRemoveCreds}"/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage" model="['errorMessage': renderErrors(bean: companyInstance, field: 'addRemoveCreds')]" />--}%
    %{--<g:render template="/layouts/formLayout/messages/infoMessage" model="['infoMessage': message(code: 'admin.addRemoveCredits.add.message')]" />--}%
</div>

<div class="form-field">
    <label for="comment"><g:message code="admin.addRemoveCredits.comment.label" /><span class="required-star">*</span></label>
    <div class="input ${hasErrors(bean: purchasedServiceInstance, field: 'comment', 'fail')} required">
        <g:textField name="comment" maxlength="255" value="${purchasedServiceInstance?.comment}"/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage" model="['errorMessage': renderErrors(bean: purchasedServiceInstance, field: 'comment')]" />--}%
</div>
