
<div class="form-field ">
    <label for="remote">
        <g:message code="remoteRegister.definition.label"/>

    </label>

    <div class="textarea ${hasErrors(bean: remoteRegister, field: 'remote', 'fail')}">
        <g:textArea name="remote" cols="40" rows="5" maxlength="8000"/>
    </div>
</div>



