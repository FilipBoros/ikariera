<div class="row">
    <div class="large-12 columns">

        <label for="${name?name:field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}
            <g:if test="${required}">
                <g:textArea required="" style="height: ${height}"  name="${name?name:field}" disabled="${disabled}" placeholder="${placeholder}" maxlength="${maxLength?:2000}"  value="${fieldValue(bean: inputInstance, field: field)}"/>
            </g:if>

            <g:else>
                <g:textArea style="height: ${height}"  name="${name?name:field}" disabled="${disabled}" placeholder="${placeholder}" maxlength="${maxLength?:2000}"  value="${fieldValue(bean: inputInstance, field: field)}"/>
            </g:else>

        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">${errorMessage}</small>

    </div>
</div>

