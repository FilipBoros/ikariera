<!doctype html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>
<br/>

<br/>

<div class="row">
    <div class="large-12 columns">
        <h1><sec:loggedInUserInfo field="fullName"/></h1>

    </div>
    <hr>

</div>


<div class="row">
    <div class="large-7 columns">

        <h4><g:message code="student.account.finished.from" /> </h4>

        <div class="progress success round"><span class="meter"
                                                  style="width: ${loggedUserProfileFinished(id: sec.loggedInUserInfo(field: 'id'))}%"></span>
        </div>

        <div style="text-align: center">${loggedUserProfileFinished(id: sec.loggedInUserInfo(field: 'id'))}%</div>

    </div>

    <div class="large-5 columns">
        <div class="panel">
                    <h4><g:message code="student.user.profile" /> </h4>
            <p>
                <g:message code="student.user.profile.information" />
            </p>
        </div>


        <div class="panel">
            <h4> <g:message code="student.why.to.fill.profile" /> </h4>
            <p>
                <g:message code="student.why.to.fill.profile.information" />
            </p>
        </div>

    </div>

</div>

</body>
</html>
<!doctype html>
<html>
<head>
    %{--<meta name="layout" content="student">--}%

</head>

<body>
<br/>

<br/>

<div class="row">
    <div class="large-12 columns">
        <h1><sec:loggedInUserInfo field="fullName"/></h1>

    </div>
    <hr>

</div>


<div class="row">
    <div class="large-7 columns">

        <h4><g:message code="student.account.finished.from" /> </h4>

        <div class="progress success round"><span class="meter"
                                                  style="width: ${loggedUserProfileFinished(id: sec.loggedInUserInfo(field: 'id'))}%"></span>
        </div>

        <div style="text-align: center">${loggedUserProfileFinished(id: sec.loggedInUserInfo(field: 'id'))}%</div>

    </div>

    <div class="large-5 columns">
        <div class="panel">
                    <h4><g:message code="student.user.profile" /> </h4>
            <p>
                <g:message code="student.user.profile.information" />
            </p>
        </div>


        <div class="panel">
            <h4> <g:message code="student.why.to.fill.profile" /> </h4>
            <p>
                <g:message code="student.why.to.fill.profile.information" />
            </p>
        </div>

    </div>

</div>

</body>
</html>
