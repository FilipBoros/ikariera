<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h1><g:message code="company.registration.label" /> </h1>
    </div>

    <hr>

</div>

<div class="row">

    <div class="large-7 columns">

        <g:form controller="company" action="saveRegistration">




            <g:render template="form" model="[companyInstance: companyInstance]"/>

            <br/>

            <h3>${message(code: 'companyAccount.create.h2.label')}</h3>

            <g:render template="formUser" model="[userInstance: userInstance]"/>


            <hr/>

            <g:render template="userTermOfUse"/>

            <button action="updateProfile" name="submit"
                    class="button">${message(code: 'company.registration.button')}</button>

        </g:form>
    </div>


    <div class="large-5 columns">

        <div class="panel">

            <h3><g:message code="information" /> </h3>

            <hr>
            <p>
       <g:message code="company.add.jobOffer.registration.information"  />
            </p>


        </div>

    </div>

</div>

</body>
</html>
