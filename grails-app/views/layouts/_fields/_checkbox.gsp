<div class="row">
    <div class="large-12 columns">

        <label for="${name?name:field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

           <g:checkBox name="${name?name:field}"  value="true" checked="${fieldValue(bean: inputInstance, field: field)=="true" ? true : false}" />



            ${label}

        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">${errorMessage}</small>

    </div>
</div>

