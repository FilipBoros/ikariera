<div class="form-part">
    <div class="form-field">
        <label for="subject"><g:message code="company.email.subject.label"/>:<span class="required-star">*</span>
        </label>

        <div class="input ${hasErrors(bean: adminEmailsInstance, field: 'subject', 'fail')} ">
            <g:textField name="subject" maxlength="150" value="${adminEmailsInstance?.subject}"/>
        </div>

        <g:render template="/layouts/formLayout/messages/errorMessage"
                  model="['errorMessage': message(code: 'company.email.subject.error')]"/>

        <g:render template="/layouts/formLayout/messages/infoMessage"
                  model="['infoMessage': message(code: 'company.email.subject.info')]"/>

    </div>

</div>


<div class="form-part">

    <h2><g:message code="company.email.message.label"/><span class="required-star">*</span></h2>

    <div class="big-textarea  ${hasErrors(bean: adminEmailsInstance, field: 'jobOffer', 'fail')} required">
        <g:textArea name="message" id="message" class="mceEditor" cols="70" rows="6"
                    value="${adminEmailsInstance?.message?.decodeHTML()}"/>
    </div>

</div>

<!-- file -->
<div class="form-field">
    <g:if test="${adminEmailsInstance.originalName}">
        <label for="path">
            <g:message code="admin.attachment.label"/>
        </label>

        <p id="path" align="center">${adminEmailsInstance.originalName}</p>
    </g:if>
    <g:if test="${adminEmailsInstance.contentType}">
        <p align="center">${adminEmailsInstance.contentType}</p>
    </g:if>
</div>

<div class="form-field">
    <label for="attachment">
        <g:message code="attachment.file.label"/>
    </label>

    <div class="empty-input ${hasErrors(bean: adminEmailsInstance, field: 'attachement', 'fail')}">
        <input type="file" name="attachment" id="attachment"/>

    </div>

</div>
















