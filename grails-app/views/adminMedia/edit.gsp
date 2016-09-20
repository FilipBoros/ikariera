<%@ page import="cz.ikariera.admin.PictureMedia" %>
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
                <li><g:link action="index"><g:message code="cz.ikariera.default.list.label" default="List"/></g:link></li>

            </ul>


            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>


<g:form controller="adminMedia" action="update" novalidate="novalidate" method="PUT">


				<g:hiddenField name="version" value="${pictureMediaInstance?.version}" />
                <g:hiddenField name="id" value="${pictureMediaInstance?.id}"/>

					<g:render template="form"/>


					<g:actionSubmit class="button save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />


                <g:link class="button" action="index"><g:message code="default.link.skip.label" default="Storno"/></g:link>

            </g:form>


        </div>
    </div>

	</body>
</html>
