<!-- cast user -->

<div class="form-part">
    <h2>${message(code: 'upperRightMenu.writeUs.label')}</h2>

    <div class="form-field">
        <label for="firstName"><g:message code="about.firstName" /></label>
        <div class="input ${hasErrors(bean: userInstance, field: 'firstName', 'fail')}">
            <g:textField name="firstName" maxlength="150" value="${userInstance?.firstName}"/>
        </div>
    </div>

    <div class="form-field">
        <label for="lastName"><g:message code="about.lastName" />:</label>
        <div class="input ${hasErrors(bean: userInstance, field: 'lastName', 'fail')}">
            <g:textField name="lastName" maxlength="150" value="${userInstance?.lastName}"/>
        </div>
    </div>

    <div class="form-field">
        <label for="username"><g:message code="about.email" />:<span class="required-star">*</span></label>
        <div class="input ${hasErrors(bean: userInstance, field: 'username', 'fail')}">
            <g:textField name="username" maxlength="150" value="${userInstance?.username}"/>
        </div>
    </div>
</div>

<div class="form-part">
    <div class="form-field">
        <label for="subject"><g:message code="about.subject" />:<span class="required-star">*</span></label>
        <div class="input ${hasErrors(bean: userInstance, field: 'password1', 'fail')}">
            <g:textField  name="subject" maxlength="150" value="${params?.subject}" />
        </div>
    </div>

    <div class="form-field">
        <label for="text"><g:message code="about.text" />:<span class="required-star">*</span></label>
        <div class="textarea ${hasErrors(bean: userInstance, field: 'password2', 'fail')}">
            <g:textArea  name="text" cols="60" rows="10" maxlength="150" >${params?.text}</g:textArea>
        </div>
    </div>
</div>






