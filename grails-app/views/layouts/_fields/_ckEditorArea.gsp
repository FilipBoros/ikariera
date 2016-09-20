<div class="row">
    <div class="large-12 columns">

        <label for="${name?name:field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}


            <g:textArea  style="height: ${height}"  cols="10" rows="4" name="${name?name:field}" class="ckeditor" disabled="${disabled}" placeholder="${placeholder}" maxlength="${maxLength?:2000}"  value="${fieldValue(bean: inputInstance, field: field)}"/>
        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">${errorMessage}</small>

    </div>
</div>

