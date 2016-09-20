<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>
<div class="row">

    <br/>


    <div class="large-10 columns">

        <h2><g:message code="companyEmails.showEmail.label"/></h2>



    </div>


        <div class="large-2 columns">
            <g:link controller="companyAccountMessage" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
        </div>

    <hr/>
</div>

<div class="row">
    <div class="large-12 columns">
        <h3>${reply.studentName + " " + reply.studentLastName + " projevil zájem o Vaši nabídku"}</h3>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <p>
            ${reply.replyText}
        </p>

    </div>
    <g:if test="${reply?.newFilename}">

        <g:link controller="companyAccountCvSearch" action="getStudentCv" class="button secondary radius"
                params="[id: reply?.newFilename]" target="_blank">

            <g:message code="company.findStudent.download.label"/>
        </g:link>
    </g:if>
    <a class="button" data-reveal-id="contactModal" data-reveal>

        <span class="fi-mail"></span>

        <g:message code="show.interest" />


    </a>

    <div id="contactModal" class="reveal-modal" data-reveal>
        <h2><g:message code="show.interest" /></h2>

        <g:form controller="companyAccountMessage" action="respondToStudent">

            <g:hiddenField name="id" value="${studentUser?.id}"/>
            <g:hiddenField name="mail" value="${reply?.studentEmail}"/>


            <g:render template="/layouts/_fields/inputWithValue" model="[
                    label: message(code: 'message.subject'),
                    field: 'subject',
                    name: 'subject',
                    message: message(code: 'company.respond.email.subject', args: [cz.ikariera.security?.User?.findByUsername(sec.username())?.company?.companyName]),
                    placeholder: '',
                    maxLength: 150]"

            />


            <g:render template="/layouts/_fields/textArea" model="[
                    label: message(code: 'message.body'),
                    field: 'text',
                    name: 'text',
                    placeholder: '',
                    maxLength: 4000,
                    height: '400px',
            ]"/>

            <a class="close-reveal-modal">&#215;</a>


            <g:submitButton class="button" name="${message(code: 'message.send')}"/>
        </g:form>
    </div>
</div>
</body>
</html>