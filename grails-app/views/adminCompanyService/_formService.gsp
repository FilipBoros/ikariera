<h2>${serviceInstance?.company?.companyName} - <g:message code="${serviceInstance?.service?.name}"/></h2>

<p>
    <g:message code="service.is.activated"/>
</p>


<div class="form-field">
    <label for="dateActivated">
        <g:message code="service.expire.from"/>
    </label>

    <div class="input ${hasErrors(bean: serviceInstance, field: 'dateActivated', 'fail')} required">
        <g:datePicker precision="day" name="dateActivated" value="${serviceInstance?.dateActivated}"/>
    </div>
</div>


<div class="form-field">
    <label for="dateExpire">
        <g:message code="service.expire.to"/>
    </label>

    <div class="input ${hasErrors(bean: serviceInstance, field: 'dateExpire', 'fail')} required">
        <g:datePicker name="dateExpire" precision="day" value="${serviceInstance?.dateExpire}"/>
    </div>
</div>



