<div class="form-field">
    <label for="posOrder"><g:message code="logo.posOrder.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: logoInstance, field: 'rotation', 'fail')} required">
        <g:field type="number" name="posOrder" value="${logoInstance?.posOrder}"/>
    </div>




    <g:render template="/layouts/formLayout/messages/errorMessage"
              model="['errorMessage': renderErrors(bean: logoInstance, field: 'posOrder')]"/>
    <g:render template="/layouts/formLayout/messages/infoMessage"
              model="['infoMessage': message(code: 'logo.posOrder.message')]"/>
</div>


<div class="form-field">
    <label for="dateCreated"><g:message code="logo.dateCreated.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: logoInstance, field: 'dateCreated', 'fail')} required">
        <g:datePicker name="dateCreated" precision="day" value="${logoInstance?.dateCreated}"/>
    </div>

</div>


<div class="form-field">
    <label for="expirationDate"><g:message code="logo.expirationDate.label"/><span class="required-star">*</span>
    </label>

    <div class="input ${hasErrors(bean: logoInstance, field: 'expirationDate', 'fail')} required">
        <g:datePicker name="expirationDate" precision="day" value="${logoInstance?.expirationDate}"/>
    </div>

</div>


