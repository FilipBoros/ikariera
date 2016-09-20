<div class="row">

    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.firstName.label'),
                field: 'firstName',
                name: 'firstName',
                placeholder: '',
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.firstName.error')]"/>
    </div>


    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.lastName.label'),
                field: 'lastName',
                name: 'lastName',
                placeholder: '',
                maxLength: 150,
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.lastName.error')]"/>
    </div>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.username.label'),
                field: 'username',
                name: 'username',
                placeholder: '',
                maxLength: 150,
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.email.error') + ' ' + link( class: 'username-error', action:'index', controller:'password'){'Zapomenuté heslo?'}]"/>




    </div>

</div>


%{--
<div class="row">

    <div class="large-6 columns">

        <g:render template="/layouts/_fields/inputPassword" model="[
                label: message(code: 'user.password1.label'),
                field: 'password',
                name: 'password',
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.password.error')]"/>

    </div>

    <div class="large-6 columns">

        <g:render template="/layouts/_fields/inputPassword" model="[
                label: message(code: 'user.password2.label'),
                field: 'passwordConfirm',
                name: 'passwordConfirm',
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.password2.error')]"/>

    </div>
</div>
--}%


<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/reCaptcha" model="[
                label: message(code: 'rewrite.text.from.the.picture'),
                field: 'reCaptcha',
                name: 'reCaptcha',
                placeholder: '',
                inputInstance: userInstance]"/>

    </div>
</div>

