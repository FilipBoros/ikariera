

<div class="row   ">
<div class="large-12 columns">
	<label for="name" class=" ${hasErrors(bean: partnerInstance, field: 'name', 'error')}">
		<g:message code="partner.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${partnerInstance?.name}"/>
    <g:hasErrors bean="partnerInstance" field ="name" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>


<div class="row   ">
<div class="large-12 columns">
	<label for="partnerType" class=" ${hasErrors(bean: partnerInstance, field: 'partnerType', 'error')}">
		<g:message code="partner.partnerType.label" default="Partner Type" />
		
	</label>
	<g:select name="partnerType" from="${partnerInstance.constraints.partnerType.inList}" value="${partnerInstance?.partnerType}" valueMessagePrefix="partner.partnerType" noSelection="['': '']"/>
    <g:hasErrors bean="partnerInstance" field ="partnerType" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>


<div class="row   required">
<div class="large-12 columns">
	<label for="logo" class=" ${hasErrors(bean: partnerInstance, field: 'logo', 'error')}">
		<g:message code="partner.logo.label" default="Logo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="logo" name="logo.id" optionValue="name" from="${cz.ikariera.admin.PictureMedia.list()}" optionKey="id" required="" value="${partnerInstance?.logo?.id}" class="many-to-one"/>


    <g:hasErrors bean="partnerInstance" field ="logo" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>


<div class="row   ">
<div class="large-12 columns">
	<label for="link" class=" ${hasErrors(bean: partnerInstance, field: 'link', 'error')}">
		<g:message code="partner.link.label" default="Link" />
		
	</label>
	<g:field type="url" name="link" value="${partnerInstance?.link}"/>
    <g:hasErrors bean="partnerInstance" field ="link" >
          <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
    </g:hasErrors>
</div>
</div>



<div class="row   ">
    <div class="large-12 columns">
        <label for="posOrder" class=" ${hasErrors(bean: partnerInstance, field: 'posOrder', 'error')}">
            <g:message code=" " default="posOrder" />

        </label>
        <g:textField name="posOrder" value="${partnerInstance?.posOrder}"/>
        <g:hasErrors bean="partnerInstance" field ="posOrder" >
            <small><g:message code="default.form.error.message" default="Invalid Entry" /></small>
        </g:hasErrors>
    </div>
</div>


