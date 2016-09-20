

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'article.headerText.label') + '*',
                name: 'header',
                field: 'header',
                placeholder: '',
                inputInstance: articleInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>



<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/textArea" model="[
                label:  message(code: 'company.article.leading.text'),
                name: 'leadingText',
                field: 'leadingText',
                height: '70px',

                maxLength: 500,
                placeholder: '',
                inputInstance: articleInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/ckEditorArea" model="[
                label: message(code: 'article.bodyText.label'),
                name: 'bodyText',
                field: 'bodyText',
                height: '200px',

                maxLength: 4000,
                placeholder: '',
                inputInstance: articleInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


