<div class="row">

    <div class="large-7 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.telephone.label'),
                name: 'studentAccount.telephone',
                field: 'telephone',

                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.telephone.error')]"/>

    </div>



    <div class="large-5 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.nationality.label'),
                name: 'studentAccount.nationality',
                field: 'nationality',

                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.nationality.error')]"/>

    </div>

</div>


<div class="row">

    <div class="large-7 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.addressStreet.label'),
                name: 'studentAccount.addressStreet',
                field: 'addressStreet',
                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.addressStreet.error')]"/>

    </div>



    <div class="large-5 columns">

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

    <div class="large-4 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.addressZip.label'),
                name: 'studentAccount.addressZip',
                field: 'addressZip',
                maxLength: 150,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.addressZip.error')]"/>

    </div>



    <div class="large-8 columns">



        <g:render template="/layouts/_fields/selectCountry" model="[
                label: message(code: 'student.addressCountry.label'),
                name: 'studentAccount.addressCountry',


                value: userInstance?.studentAccount?.addressCountry,

                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.addressCountry.error')]"/>

    </div>

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'student.personalCharacteristic.label'),
                name: 'studentAccount.personalCharacteristic',
                field: 'personalCharacteristic',
                maxLength: 255,
                inputInstance: userInstance?.studentAccount,
                errorMessage: message(code: 'userPersonal.personalCharacteristic.error')]"/>

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



