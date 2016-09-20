

<div class="row">

    <div class="large-12 columns">
        <g:render template="/layouts/_fields/inputPassword" model="[
                label: message(code: 'old.password'),
                field: 'passwordOld',
                name: 'passwordOld',
                placeholder: '',
                inputInstance: userInstance,
                errorMessage: message(code: 'old.password.error')]"/>
    </div>

</div>

<div class="row">

    <div class="large-12 columns">
        <g:render template="/layouts/_fields/inputPassword" model="[
                label: message(code: 'new.password'),
                field: 'passwordNew',
                name: 'passwordNew',
                placeholder: '',
                inputInstance: userInstance,
                errorMessage: message(code: 'new.password.error')]"/>
    </div>

</div>


<div class="row">

    <div class="large-12 columns">
        <g:render template="/layouts/_fields/inputPassword" model="[
                label: message(code: 'confirm.password'),
                field: 'passwordConfirm',
                name: 'passwordConfirm',
                placeholder: '',
                inputInstance: userInstance,
                errorMessage: message(code: 'confirm.password.error')]"/>
    </div>

</div>