<%@ page import="cz.ikariera.admin.PictureMedia" %>



<div class="row   ">
<div class="large-12 columns">
	<label for="name" class=" ${hasErrors(bean: pictureMediaInstance, field: 'name', 'error')}">
		<g:message code="pictureMedia.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${pictureMediaInstance?.name}"/>
    <g:hasErrors bean="pictureMediaInstance" field ="name" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>



%{--<div class="row   ">
<div class="large-12 columns">
	<label for="mediaType" class=" ${hasErrors(bean: pictureMediaInstance, field: 'mediaType', 'error')}">
		<g:message code="pictureMedia.mediaType.label" default="Media Type" />
		
	</label>
	<g:select name="mediaType" from="${pictureMediaInstance.constraints.mediaType.inList}" value="${pictureMediaInstance?.mediaType}" valueMessagePrefix="pictureMedia.mediaType" noSelection="['': '']"/>
    <g:hasErrors bean="pictureMediaInstance" field ="mediaType" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>--}%



<div class="row   ">
    <div class="large-12 columns">
        <label for="position" class=" ${hasErrors(bean: pictureMediaInstance, field: 'position', 'error')}">
            <g:message code="pictureMedia.order.label" default="Position" />

        </label>
        <g:textField name="position" value="${pictureMediaInstance?.position}"/>
        <g:hasErrors bean="pictureMediaInstance" field ="position" >
            <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
        </g:hasErrors>
    </div>
</div>




%{--

<div class="row   required">
<div class="large-12 columns">
	<label for="fileSize" class=" ${hasErrors(bean: pictureMediaInstance, field: 'fileSize', 'error')}">
		<g:message code="pictureMedia.fileSize.label" default="File Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="fileSize" type="number" value="${pictureMediaInstance.fileSize}" required=""/>
    <g:hasErrors bean="pictureMediaInstance" field ="fileSize" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>


<div class="row   ">
<div class="large-12 columns">
	<label for="newFilename" class=" ${hasErrors(bean: pictureMediaInstance, field: 'newFilename', 'error')}">
		<g:message code="pictureMedia.newFilename.label" default="New Filename" />
		
	</label>
	<g:textField name="newFilename" value="${pictureMediaInstance?.newFilename}"/>
    <g:hasErrors bean="pictureMediaInstance" field ="newFilename" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>
            --}%

<div class="row   ">
<div class="large-12 columns">
	<label for="originalFilename" class=" ${hasErrors(bean: pictureMediaInstance, field: 'originalFilename', 'error')}">
		<g:message code="pictureMedia.originalFilename.label" default="Original Filename" />
		
	</label>
    <td><img  src="${createLink(controller: 'media', action: 'picture', id: pictureMediaInstance?.id)}" /> </td>
</div>
</div>


<div class="row   ">
<div class="large-12 columns">
	<label for="thumbnailFilename" class=" ${hasErrors(bean: pictureMediaInstance, field: 'thumbnailFilename', 'error')}">
		<g:message code="pictureMedia.thumbnailFilename.label" default="Thumbnail Filename" />
		
	</label>
	<td><img  src="${createLink(controller: 'media', action: 'thumbnail', id: pictureMediaInstance?.id)}" /> </td>
</div>
</div>


