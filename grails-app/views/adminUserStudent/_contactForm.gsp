<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.telephone.label'),
                name: 'studentAccount.telephone',
                field: 'telephone',
                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.telephone.error')]"/>

    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.addressStreet.label'),
                name: 'studentAccount.addressStreet',
                field: 'addressStreet',
                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.addressStreet.error')]"/>

    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.addressCity.label'),
                name: 'studentAccount.addressCity',
                field: 'addressCity',
                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.addressCity.error')]"/>

    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.addressZip.label'),
                name: 'studentAccount.addressZip',
                field: 'addressZip',
                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.addressZip.error')]"/>

    </div>

</div>

%{--

<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/select" model="[
                optionKey: 'id',
                label: message(code: 'student.addressCountry.label'),
                placeholder: '',
                inputInstance: userInstance?.studentAccount,
                name: 'studentAccount.addressCountry',
                field: 'addressCountry',
                from: cz.ikariera.admin?.Country?.list(sort: 'posOrder')]"/>

    </div>

</div>
--}%



