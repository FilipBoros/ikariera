<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

%{--
<div class="row">
    <br/>

    <div class="large-12 columns">
        <h2>${message(code: 'companyAccount.create.h2.label')}</h2>
    </div>
    <hr>
</div>

<div class="row">

    <div class="large-7 columns">

        <g:form method="post" controller="companyAccountUser" action="saveUser">


                <g:render template="/companyAccountUser/form" model="[userInstance: userInstance]"/>


                <button action="saveUser" class="save half">${message(code: 'company.createUser.label')}</button>

        </g:form>

    </div>

    <div class="large-5 columns">

    </div>
</div>
--}%




<div class="row">

    <br>
    <div class="large-10 columns">
        <h2>${message(code: 'companyAccount.create.h2.label')}</h2>

    </div>

    <div class="large-2 columns">
        <g:link controller="companyAccountUser" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>

    <hr>

</div>

<div class="row">

    <div class="large-7 columns">

        <g:form method="post" action="save" controller="companyAccountUser">




            <g:render template="/companyAccountUser/form" model="[userInstance: userInstance]"/>



            <button action="updateUser" class="save button ">${message(code: 'registration.create')}</button>

        </g:form>

    </div>


    <div class="large-5 columns">

        <div class="panel">

            <h4>${message(code: 'registration.h4')}</h4>

            <hr>

            <p>
                ${message(code: 'registration.body1')}

            </p>

            <p>
                ${message(code: 'registration.body2')}

            </p>

            <p>
                ${message(code: 'registration.body3')}
            </p>
        </div>

    </div>

</div>
</body>
</html>

