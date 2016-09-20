<%@ page import="cz.ikariera.admin.Advertisement" %>


<div class="form-field">
    <label for="header" >
        <g:message code="banner.name.label" />

    </label>

    <div class="input ${hasErrors(bean: advertisementInstance, field: 'header', 'fail')}">
        <g:textField name="header" maxlength="200" value="${advertisementInstance?.header}"></g:textField>
    </div>
</div>





<!-- file -->
<div class="form-field">
    <label for="myFile">
        <g:message code="banner.file.label"  />
    <div class="empty-input ${hasErrors(bean: advertisementInstance, field: 'directory', 'fail')}">
        <input type="file" name="myFile" id="myFile"/>
    </div>
</div>



<div class="form-field">
    <label for="urlLink" >
        <g:message code="banner.urlLink.label" />

    </label>
    <div class="input ${hasErrors(bean: advertisementInstance, field: 'urlLink', 'fail')}">
       <g:textField name="urlLink" value="${advertisementInstance?.urlLink}"></g:textField>
    </div>
</div>








