<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>

<div class="row">

    <div class="large-12 columns">

        <h2><g:message code="info.emails.title"/></h2>
        <hr>
    </div>

</div>


<div class="row">
    <div class="large-7 columns">

        <g:form controller="signOutInfoEmail" action="signOut" autocomplete="off">

            <g:render template="/layouts/_fields/input" model="[
                    label      : message(code: 'user.username.label'),
                    field      : 'username',
                    name       : 'username',
                    placeholder: '',
                    maxLength  : 150
            ]"/>

            <button name="submit" class="save long right">
                <g:message code="send"/>
            </button>

        </g:form>

    </div>

    <div class="large-5 columns">
        <div class="panel">

            <h3><g:message code="information"/></h3>

            <p>
                <g:message code="info.emails.sign.out.tip"/>

            </p>

        </div>

    </div>

</div>

</body>
</html>