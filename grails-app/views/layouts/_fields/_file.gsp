<div class="row">
    <div class="large-12 columns">

        <label for="${name ? name : field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}

            <input type="file" name="${name ? name : field}"
                         value="${inputInstance ? fieldValue(bean: inputInstance, field: field) : value}"/>
        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">
            ${raw(errorMessage)}

        </small>

    </div>
</div>

