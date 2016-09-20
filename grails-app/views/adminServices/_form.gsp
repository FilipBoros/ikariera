<h2><g:message code="${serviceInstance?.name}"/></h2>

<p>
    <g:message code="${serviceInstance?.description}"/>
</p>

<div class="form-field">
    <label for="creditPrice">
        <g:message code="services.creditPrice.label"/>
    </label>

    <div class="input ${hasErrors(bean: serviceInstance, field: 'creditPrice', 'fail')} required">
        <g:field type="number" step="0.01" name="creditPrice" value="${serviceInstance?.creditPrice}"/>
    </div>
</div>


<div class="form-field">
    <label for="defaultExpirationTime">
        <g:message code="services.defaultExpirationTime.label"/>
    </label>

    <div class="input ${hasErrors(bean: serviceInstance, field: 'defaultExpirationTime', 'fail')} required">
        <g:textField name="defaultExpirationTime" value="${serviceInstance?.defaultExpirationTime}"/>
    </div>
</div>
