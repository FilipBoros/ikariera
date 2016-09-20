
<!doctype html>
<html>
	<head>
		<meta name="layout" content="administrator">

	</head>


	<body>



    <div class="row">


        <br />
        <div class="large-12">


            <h2><g:message code="remoteRegister.button.create.label" /></h2>
            <hr>

        </div>

    </div>
    <div class="row">



        <div class="large-12">



            <g:form controller="adminRemoteRegister" action="doRemoteRegister" >


					<g:render template="/adminRemoteRegister/form"/>

                    <button name="create" class="save float">${message(code: 'project.create.button')}</button>


			</g:form>

        </div>

    </div>

	</body>
</html>
