<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>
<div class="row">

    <br/>


    <div class="large-10 columns">

        <h3><g:message code="about.message"/> : <em>${dialog?.subject}</em></h3>

    </div>
    <div class="large-2 columns">
        <g:link controller="companyAccountMessage" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>
    <hr/>
</div><br/>
<g:each in="${dialog?.messages}" var="msg">

    <g:render template="/studentAccountMessage/messageTemplate" model="[msg:msg, sender:dialog?.sender]"/>

</g:each>


<div class="row">
    <g:form controller="companyAccountMessage" action="replyOut">
        <g:hiddenField name="id" value="${dialog?.id}"/>

        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'company.messages.reply.label'),
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
