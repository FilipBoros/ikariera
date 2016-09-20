<!doctype html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h1><g:message code="change.password"/></h1>
    </div>

    <hr>

</div>

<div class="row">

    <div class="large-7 columns">

        <g:form action="save">

            <g:render template="form" model="['userInstance': userInstance]"/>



            <button name="create" class="save long">
                ${message(code: 'default.button.save.label')}
            </button>

            <br/>

        </g:form>

    %{--        <table style="display: none;" id="university-row-clone">
                <g:render template='/layouts/formLayout/elements/university' model="['university': null, 'i': '_clone']"/>
            </table>--}%

    </div>

    <div class="large-5 columns">
        <div class="panel">
            <h3><g:message code="change.password.panel" /> </h3>

            <hr>


            <p>
                    <g:message code="change.password.panel.information" />
            </p>
        </div>

    </div>

</div>

</body>
</html>
