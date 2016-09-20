
<div class="form-field">
    <label for="username"><g:message code="jobOffers.username.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: companyUserInstance, field: 'username', 'fail')} required">
        <g:textField name="username" maxlength="255" value="${companyUserInstance?.username}"/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage"--}%
    %{--model="['errorMessage': renderErrors(bean: companyUserInstance, field: 'firstName')]"/>--}%
</div>

<div class="form-field">
    <label for="firstName"><g:message code="jobOffers.firstName.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: companyUserInstance, field: 'firstName', 'fail')} required">
        <g:textField name="firstName" maxlength="255" value="${companyUserInstance?.firstName}"/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage"--}%
              %{--model="['errorMessage': renderErrors(bean: companyUserInstance, field: 'firstName')]"/>--}%
</div>

<div class="form-field">
    <label for="lastName"><g:message code="jobOffers.lastName.label"/><span class="required-star">*</span></label>

    <div class="input ${hasErrors(bean: companyUserInstance, field: 'lastName', 'fail')} required">
        <g:textField name="lastName" maxlength="255" value="${companyUserInstance?.lastName}"/>
    </div>

    %{--<g:render template="/layouts/formLayout/messages/errorMessage"--}%
              %{--model="['errorMessage': renderErrors(bean: companyUserInstance, field: 'lastName')]"/>--}%
</div>

%{--
<div class="form-field">
    <label for="telephone"><g:message code="jobOffers.telephone.label"/></label>

    <div class="input">
        <g:textField name="telephone" maxlength="50" value="${companyAccountInstance?.telephone}"/>
    </div>
</div>


<div class="form-field">
    <label for="positionInCompany"><g:message code="jobOffers.positionInCompany.label"/></label>

    <div class="input">
        <g:textField name="positionInCompany" maxlength="100" value="${companyAccountInstance?.positionInCompany}"/>
    </div>
</div>--}%


%{--
<div class="form-field">
    <label for="passwordREDIRECT"><g:message code="user.password1.label"/>:</label>

    <g:link controller="password" action="editUserPassword" name="passwordREDIRECT"
            params="[returnAddress: request.forwardURI, returnPath: (request.forwardURI - request.contextPath), user: companyUserInstance.id]"
            style="  line-height: 28px;
                margin-left: 280px;
                position: relative;
                width: 300px;"><g:message code="user.changePassword.label"/></g:link>
</div>--}%


