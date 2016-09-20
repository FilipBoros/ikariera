<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h1>

            <g:message code="feedback.write.us"/>

        </h1>

        <hr>

    </div>

</div>


<div class="row">
    <div class="large-8 columns">
        <g:form controller="companyAccountFeedback" action="sendFeedBack">

            <g:render template="/layouts/_fields/input" model="[
                    label      : message(code: 'company.messages.subject.label'),
                    field      : 'subject',
                    name       : 'subject',
                    placeholder: '',
                    required   : 'true',
                    maxLength  : 150]"/>
            <g:render template="/layouts/_fields/textArea" model="[
                    label      : message(code: 'company.feedback.text'),
                    field      : 'text',
                    name       : 'text',
                    placeholder: '',
                    maxLength  : 4000,
                    height     : '200px',
            ]"/>

            <g:submitButton class="button" name="${message(code: 'send')}"/>
        </g:form>
    </div>


    <div class="large-4 columns">

        <div class="panel">

            <h4><g:message code="company.feedback.info"/></h4>
            <hr>

            <p>

                <g:message code="company.feedback.info.description"/>
            </p>

        </div>

    </div>

</div>

</body>
</html>
