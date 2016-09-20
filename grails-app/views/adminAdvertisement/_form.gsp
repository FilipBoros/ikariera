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
    </label>
<!-- link to file -->
    <g:link controller="adminAdvertisement" action="getLogo"
            params="[id: advertisementInstance?.id]" target="_blank">

        <i class="fi-page" style="font-size: 100px"></i>

    </g:link>
    <div class="empty-input ${hasErrors(bean: advertisementInstance, field: 'directory', 'fail')}">
        <input type="file" name="myFile" id="myFile"/>
    </div>
</div>

%{--<img src="${resource(dir: advertisementInstance?.directory, file: advertisementInstance?.file,
        absolute: true)}" width="200px" height="262px"/>
<img src="${resource(dir: 'images/ikariera', file: 'ikariera-small.png')}" alt="iKariera"
     style="border-width: 0px; margin-bottom: 10px;"/>
${resource(dir: 'images/ikariera', file: 'ikariera-small.png')}
${resource(dir: advertisementInstance?.directory, file: advertisementInstance?.file,
        absolute: true)}--}%
%{--<img src="${resource(dir: "images/uploads/advertisment", file: companyInstance.mainLogo)}" width="855"
     height="250"/>--}%


<div class="form-field">
    <label for="urlLink" >
        <g:message code="banner.urlLink.label" />

    </label>
    <div class="input ${hasErrors(bean: advertisementInstance, field: 'urlLink', 'fail')}">
       <g:textField name="urlLink" value="${advertisementInstance?.urlLink}"></g:textField>
    </div>
</div>








