<g:hiddenField name="companyName" value="${studentMessageInstance?.companyName}" />

<g:hiddenField name="companyId" value="${studentMessageInstance?.companyId}" />




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


<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'studentMessage.text.label'),
                name: 'text',
                field: 'text',
                maxLength: 2000,
                inputInstance: studentMessageInstance,
                errorMessage: message(code: 'studentMessage.text.error')]"/>

    </div>

</div>




