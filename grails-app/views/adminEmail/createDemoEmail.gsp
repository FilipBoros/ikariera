<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="admin.create.demo.email"/></h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <g:form action="saveDemoEmail" enctype="multipart/form-data">

            <div class="row">
                <div class="large-12 columns">
                    <g:render template="/layouts/_fields/input" model="[
                            label        : message(code: 'recipient.label'),
                            name         : 'recipients',
                            field        : 'recipients',
                            placeholder  : 'my-email@example.com',
                            inputInstance: adminEmailsInstance,
                            errorMessage : message(code: 'general.mandatory.atribute')]"/>
                </div>

            </div>

            <div class="row">
                <div class="large-12 columns">
                    <g:render template="/layouts/_fields/input" model="[
                            label        : message(code: 'subject.label'),
                            name         : 'subject',
                            field        : 'subject',
                            placeholder  : 'This is test',
                            inputInstance: adminEmailsInstance,
                            errorMessage : message(code: 'general.mandatory.atribute')]"/>
                </div>
            </div>


            <div class="row">
                <div class="large-12 columns">
                    <g:render template="/layouts/_fields/textArea" model="[
                            label        : message(code: 'message.label'),
                            name         : 'message',
                            field        : 'message',
                            placeholder  : '',
                            inputInstance: adminEmailsInstance,
                            errorMessage : message(code: 'general.mandatory.atribute')]"/>
                </div>
            </div>

            <button name="send"
                    class="save long">${message(code: 'default.button.send.label')}</button>

        </g:form>

    </div>

</div>
</body>
</html>