%{--DEPRECATED FILE--}%

<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>
<div class="row">

    <br>
    <div class="large-12">

        <h1>Aktivace účtu</h1>

        <h4 class="subheader"> ${userInstance?.company?.companyName}</h4>

    </div>

    <hr>

</div>

<div class="row">

    <div class="large-12">

        <g:form action="saveCompanyUser">


            <g:hiddenField name="token" value="${userInstance?.token}" />


            <h2 > ${userInstance?.fullName}</h2>

            <div class="row">

                <div class="large-6 columns">

                    <g:render template="/layouts/_fields/inputPassword" model="[
                            label: message(code: 'user.password1.label'),
                            field: 'password',
                            name: 'password',
                            inputInstance: userInstance,
                            errorMessage: message(code: 'userPersonal.password.error')]"/>

                </div>

                <div class="large-6 columns">

                    <g:render template="/layouts/_fields/inputPassword" model="[
                            label: message(code: 'user.password2.label'),
                            field: 'passwordConfirm',
                            name: 'passwordConfirm',
                            inputInstance: userInstance,
                            errorMessage: message(code: 'userPersonal.password2.error')]"/>

                </div>
            </div>




            <button name="create" class="button ">
                <g:message code="activate" />
            </button>

        </g:form>
    </div>
</div>

</body>
</html>