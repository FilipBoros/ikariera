<h2><g:message code="constantModerator.name.h2.label" args="[constantsInstance?.cmName]"/></h2>

<p>
    ${constantsInstance?.cmComment}
</p>
<g:if test="${constantsInstance?.cmValue != null}">
    <div class="form-field">
        <label for="cmValue">
            <g:message code="constantModerator.value.label"/>
        </label>

        <div class="input ${hasErrors(bean: constantsInstance, field: 'cmValue', 'fail')} required">
            <g:field type="number" name="cmValue" value="${constantsInstance?.cmValue}"/>
        </div>
    </div>
</g:if>
<g:if test="${constantsInstance?.cmString != null}">
    <div class="form-field">
        <label for="cmString">
            <g:message code="constantModerator.value.label"/>
        </label>

        <div class="input ${hasErrors(bean: constantsInstance, field: 'cmString', 'fail')} required">
            <g:textField name="cmString" maxlength="255" value="${constantsInstance?.cmString}"/>
        </div>
    </div>
</g:if>
<g:if test="${constantsInstance?.cmBoolean != null}">
    <div class="form-field">
        <label for="cmValue">
            <g:message code="constantModerator.boolaen.label"/>
        </label>

        <div class="checkbox ${hasErrors(bean: constantsInstance, field: 'cmBoolean', 'fail')} required">
            <g:checkBox name="cmBoolean" value="${constantsInstance?.cmBoolean}"/>
        </div>
    </div>
</g:if>


