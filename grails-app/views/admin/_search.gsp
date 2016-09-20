
<g:form method="get" controller="admin" action="listBySearch">

    <fieldset class="form">
        <div class="form-field">
            <label for="firstName">

                <g:message code="user.firstName.label"/>

            </label>

            <div class="input ">
                <g:textField name="firstName" value="${params?.firstName}"/>

            </div>
        </div>


        <div class="form-field">
            <label for="lastName">
                <g:message code="user.lastName.label"/>

            </label>

            <div class="input ">
                <g:textField name="lastName" value="${params?.lastName}"/>
            </div>
        </div>

        <div class="form-field">
            <label for="username">
                <g:message code="user.username.label"/>

            </label>

            <div class="input ">
                <g:textField name="username" value="${params?.username}"/>
            </div>

            <button>${message(code: 'constantModerator.search.label')}</button>
        </div>

    </fieldset>

</g:form>