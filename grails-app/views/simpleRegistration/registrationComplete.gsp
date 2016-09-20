<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>



<div class="panel">

    <div class="row">
        <div class="large-12 columns">

            <h1>

                ${message(code: 'jobOffer.JobOffer.label')}

            </h1>

        </div>

    </div>


    <div class="row">

        <div class="large-12 columns">

            <br>


            <g:render template="/jobOffer/searchFormJobOffer"/>

        </div>

    </div>

</div>



<div class="row">

    <div class="large-8 columns large-centered ">

        <h1 class="text-center"><g:message code="student.create.label"/></h1>

        <div class="alert-message text-center"><g:message code="student.simplereg.label"/></div>



        <br>
        <br>
        <br>

    </div>

    <div class="row">
        <div class="large-6 columns large-centered ">


            <g:link controller="login" id="${token}" class="button  expand large"><g:message
                    code="springSecurity.login.header"/></g:link>


            <h3 class="text-center">
            <g:message code="or" />
        </h3>
            <br>
            <g:link controller="simpleRegistration" id="${token}" class="button success expand medium"><g:message
                    code="simple.registration.another"/></g:link>



        </div>
    </div>

</div>





</body>
</html>
