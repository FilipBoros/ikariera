


<g:if test="${studentInstance?.cv}">

    <g:link controller="companyAccountCvSearch" action="getStudentCv" class="button secondary radius"
            params="[id: studentInstance?.cv?.newFilename]" target="_blank">

        <g:message code="company.findStudent.download.label"/>
    </g:link>
</g:if>



<a class="button expand success" data-reveal-id="contactModal-${studentInstance.id}" data-reveal>

    <span class="fi-mail"></span>

    <g:message code="show.interest" />


</a>

<div id="contactModal-${studentInstance.id}" class="reveal-modal" data-reveal>
    <h2><g:message code="show.interest" /> : ${studentInstance?.user?.firstName}  ${studentInstance?.user?.lastName}</h2>

    <g:form controller="companyAccountMessage" action="sendToStudent">

        <g:hiddenField name="id" value="${studentInstance?.id}"/>

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'message.subject'),
                field: 'subject',
                name: 'subject',
                required: 'true',
                placeholder: '',
                maxLength: 150]"

        />


        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'message.body'),
                field: 'text',
                name: 'text',
                placeholder: '',
                required: 'true',
                maxLength: 4000,
                height: '400px',
        ]"/>

        <a class="close-reveal-modal">&#215;</a>


        <g:submitButton class="button" name="${message(code: 'message.send')}"/>
    </g:form>
</div>