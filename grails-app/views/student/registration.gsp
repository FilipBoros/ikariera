<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h1><g:message code="student.registration"/></h1>
    </div>

    <hr>

</div>

<div class="row">

    <div class="large-7 columns">

        <g:form action="save" >


            <g:render template="form" model="[userInstance: userInstance]"/>


            <hr>

            <g:render template="userTermOfUse"/>

            <button name="create" class="button ">


                <g:message code="registration" />
            </button>

            <br/>

        </g:form>

%{--        <table style="display: none;" id="university-row-clone">
            <g:render template='/layouts/formLayout/elements/university' model="['university': null, 'i': '_clone']"/>
        </table>--}%

    </div>

    <div class="large-5 columns">
        <div class="panel">
            <h3><g:message code="company.registration.label" /></h3>

            <hr>


            <p>
                <g:message code="company.registration.panel.information" />
            </p>
            <g:link mapping="companyRegistraion" class="button expand success" ><g:message code="company.registration.panel.button" /> </g:link>
        </div>

    </div>

</div>

</body>
</html>
