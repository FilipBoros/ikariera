<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'company.contact.name'),
                name: 'name',
                field: 'name',
                placeholder: '',
                required : 'true',
                inputInstance: contactDetailInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>
<div class="row">
    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'company.contact.telephone'),
                name: 'telephone',
                field: 'telephone',
                placeholder: '',
                required : 'true',
                inputInstance: contactDetailInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>

    <div class="large-6 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'company.contact.email'),
                name: 'email',
                field: 'email',
                placeholder: '',
                required : 'true',
                inputInstance: contactDetailInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'company.contact.positionInCompany'),
                name: 'positionInCompany',
                field: 'positionInCompany',
                placeholder: '',

                inputInstance: contactDetailInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label: message(code: 'company.contact.detailText'),
                name: 'detailText',
                field: 'detailText',
                placeholder: '',

                inputInstance: contactDetailInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


