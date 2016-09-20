<div class="row">

    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.firstName.label') + '*',
                field: 'firstName',
                name: 'firstName',
                placeholder: '',
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.firstName.error')]"/>
    </div>


    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.lastName.label') + '*',
                field: 'lastName',
                name: 'lastName',
                placeholder: '',
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.firstName.error')]"/>
    </div>
</div>

<div class="row">

    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.username.label') + '*',
                field: 'username',
                name: 'username',
                placeholder: '',
                inputInstance: userInstance,
                errorMessage: message(code: 'userPersonal.username.error')]"/>
    </div>
</div>


<div class="row">

    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'user.telephone.label') ,
                field: 'telephone',
                name: 'companyAccount.telephone',
                placeholder: '',
                inputInstance: userInstance?.companyAccount,
                errorMessage: message(code: 'userPersonal.firstName.error')]"/>
    </div>

</div>

<div class="row">

    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'jobOffers.positionInCompany.label'),
                field: 'positionInCompany',
                name: 'companyAccount.positionInCompany',
                placeholder: '',
                inputInstance: userInstance?.companyAccount,
                errorMessage: message(code: 'userPersonal.positionInCompany.error')]"/>
    </div>
</div>



