<div class="row">
    <div class="large-12 columns">

        <label for="${name ? name : field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}
            <g:if test="${required}">
                <g:textField name="${name ? name : field}" disabled="${disabled}" placeholder="${placeholder}" required="required"
                             maxlength="${maxLength ?: 255}" value="${message}"/>
            </g:if>

            <g:else>
            <g:textField name="${name ? name : field}" disabled="${disabled}" placeholder="${placeholder}"
                         maxlength="${maxLength ?: 255}" value="${message}"/>
            </g:else>
        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">
            ${raw(errorMessage)}

        </small>

    </div>
</div>

