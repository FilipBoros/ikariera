<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'admin.apikey.name'),
                name         : 'serviceName',
                field        : 'serviceName',
                placeholder  : '',

                inputInstance: remoteApiKeyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'admin.apikey.url'),
                name         : 'url',
                field        : 'url',
                placeholder  : '',

                inputInstance: remoteApiKeyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'admin.apikey.value'),
                name         : 'value',
                field        : 'value',
                placeholder  : '',

                inputInstance: remoteApiKeyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label        : message(code: 'admin.apikey.info'),
                name         : 'info',
                field        : 'info',
                placeholder  : '',

                inputInstance: remoteApiKeyInstance,
                errorMessage : message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

