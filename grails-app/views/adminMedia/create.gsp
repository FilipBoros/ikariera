<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="administrator">

	</head>
	<body>


    <div class="row">
        <div class="large-12 columns" >




            <ul class="breadcrumbs">

                <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><a href="${createLink(uri: '/admin/')}"><g:message code="default.admin.label" default="Admin"/></a></li>
                <li><g:link action="index"><g:message code="cz.ikariera.default.list.label" default="List"  /></g:link></li>

            </ul>


            <h1>Vytvořit</h1>


<g:form controller="adminMedia" action="save" novalidate="novalidate">


					<g:render template="form"/>


					<g:submitButton name="create" class="button save" value="${message(code: 'default.button.create.label', default: 'Create')}" />

            <g:link class="button" action="list"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></g:link>

            </g:form>


        </div>
    </div>

	</body>
</html>
