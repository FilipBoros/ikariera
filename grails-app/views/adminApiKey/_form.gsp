

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'apikey.serviceName.label'),
                name: 'serviceName',
                field: 'serviceName',
                placeholder: '[Email, RemoteRegistration, RemoteStatistics, SimpleRegistration]',

                inputInstance: apiKeyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'apikey.info.label'),
                name: 'info',
                field: 'info',
                placeholder: '',

                inputInstance: apiKeyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>
<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'apikey.url.label'),
                name: 'url',
                field: 'url',
                placeholder: '',

                inputInstance: apiKeyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>
<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'apikey.value.label'),
                name: 'value',
                field: 'value',
                placeholder: 'e.g. daf14daf49a8d1f8ad9f4daf',

                inputInstance: apiKeyInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>





<div class="row">
    <div class="large-12 columns">

        <label for="expire" class="${hasErrors(bean: apiKeyInstance, field: 'expire', 'error')}">

            ${message(code: 'apikey.expire.label')}


                <g:textField name="expire"  placeholder="Y-m-d"
                             value="${formatDate( date: apiKeyInstance.expire,  format: "yyyy-MM-dd" )}" />

        </label>

        <small class="hide ${hasErrors(bean: apiKeyInstance, field: 'expire', 'error')}">
            ${raw(errorMessage)}

        </small>

    </div>
</div>
