<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'company.email.subject.label') + '*',
                name: 'subject',
                field: 'subject',
                placeholder: '',
                inputInstance: companyEmailsInstance,
                errorMessage: message(code: 'company.email.subject.error')]"/>
    </div>
</div>




<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/ckEditorArea" model="[
                label: message(code: 'company.email.message.label') + '*',
                name: 'message',
                field: 'message',
                height: '200px',

                maxLength: 4000,
                placeholder: '',
                inputInstance: companyEmailsInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>
<div class="row">
    <div class="large-12 columns">
        </br>
        </br>
        Attachment
        <input type="file"
               name="attachmentFile"/>

    </div>
</div>















