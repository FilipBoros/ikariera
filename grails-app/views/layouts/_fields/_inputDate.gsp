<div class="row">
    <div class="large-12 columns">

        <label for="${name?name:field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}

            <g:datePicker name="${name?name:field}" value="${inputInstance.birthday}"
                          default="${new Date()}" precision="day" years="${1930..2018}"/>

        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">${errorMessage}</small>

    </div>
</div>


