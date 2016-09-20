<g:form controller="password" action="save" method="POST">


        <g:render template="/layouts/_fields/inputPassword" model="[
                label      : message(code: 'jobOffers.password1.label'),
                field      : 'password',
                name       : 'password',
                inputInstance: userInstance,
                placeholder: '',
                errorMessage: message(code: 'userPersonal.password.error'),
                maxLength  : 150
        ]"/>


        <g:render template="/layouts/_fields/inputPassword" model="[
                label      : message(code: 'jobOffers.password2.label'),
                field      : 'passwordConfirm',
                name       : 'passwordConfirm',
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.password2.error'),
                placeholder: '',
                maxLength  : 150
        ]"/>





    <g:hiddenField name="token" value="${userInstance.token}"/>




        <button action="updateUserPassword"
                class="save float">${message(code: 'company.update.label')}</button>



</g:form>
