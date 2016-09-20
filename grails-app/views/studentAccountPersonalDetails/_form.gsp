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
                errorMessage: message(code: 'userPersonal.email.error')]"/>

    </div>

</div>


<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey: 'id',
                label: message(code: 'student.h2.zajemObor'),
                placeholder: '',
                value: userInstance?.studentAccount?.jobCategories?.id,
                name: 'jobCategories',
                field: 'jobCategories',

                from: cz.ikariera.company?.JobCategory?.list(sort: 'posOrder')]"/>


    </div>
</div>




<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/checkbox" model="[
                label: message(code: 'student.infoEmails.label'),
                name: 'studentAccount.infoEmails',
                field: 'studentAccount.infoEmails',
                inputInstance: userInstance]"/>

    </div>
</div>



