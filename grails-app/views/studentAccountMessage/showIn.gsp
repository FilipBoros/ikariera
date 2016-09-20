<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>


<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2><g:message code="student.dialogs.inbox.detail" /> </h2>


    </div>


    <div class="large-3 columns">
        <br />


        <g:link controller="studentAccountMessage" action="inBox" class="button right secondary">

            <g:message code="back" />
        </g:link>

    </div>


</div>

<div class="row">
    <div class="large-12 columns">
        <hr>
    </div>
</div>



<g:each in="${dialog?.messages}" var="msg">

    <g:render template="messageTemplate" model="[msg:msg, sender:dialog?.sender]"/>

</g:each>


<div class="row">
    <g:form controller="studentAccountMessage" action="replyInbox">
        <g:hiddenField name="id" value="${dialog?.id}"/>

        <g:render template="/layouts/_fields/textArea" model="[
                label: 'Odpověd',
                field: 'text',
                name: 'text',
                placeholder: '',
                maxLength: 4000,
                height: '200px',
        ]"/>

        <g:submitButton class="button" name="Reply"/>
    </g:form>
</div>
</body>
</html>
