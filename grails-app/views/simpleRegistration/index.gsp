<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-8 columns large-centered">

        <h1><g:message code="student.registration"/></h1>
        <hr>
    </div>



</div>


<g:form controller="simpleRegistration" action="save">

    <div class="row">

        <div class="large-8 columns large-centered">


            <g:hiddenField name="token" value="${params.id}" />
            <g:render template="form" model="[userInstance: userInstance]"/>

        </div>


    </div>


    <div class="row">
        <div class="large-8 columns large-centered">


            <g:render template='university' />

        </div>
    </div>

    <div class="row">
        <div class="large-8 columns large-centered">

            <button name="create" class="button expand">

                <g:message code="registration"/>
            </button>

            <hr>

            <g:render template="userTermOfUse"/>

            <br>

            <br>
            <br>

        </div>


    </div>

</g:form>

</body>
</html>
