<div class="row">
    <div class="large-12 columns">

        <label for="${name?name:field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}

            <g:textField name="${name?name:field}" class="datepicker" disabled="${disabled}" placeholder="${placeholder}" maxlength="${maxLength?:255}"  value="${fieldValue(bean: inputInstance, field: field)}"/>
        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">${errorMessage}</small>

    </div>
</div>

