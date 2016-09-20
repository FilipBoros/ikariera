
<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'num.of.days'),
                name: 'numberOfDays',
                field: 'numberOfDays',
                placeholder: '',

                inputInstance: heroImageInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'num.of.published.jobOffers'),
                name: 'numberOfPublishedJobOffers',
                field: 'numberOfPublishedJobOffers',
                placeholder: '',

                inputInstance: heroImageInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <g:render template="/layouts/_fields/input" model="[
                label: message(code: 'prize'),
                name: 'prize',
                field: 'prize',
                placeholder: '',

                inputInstance: heroImageInstance,
                errorMessage: message(code: 'general.mandatory.atribute')]"/>
    </div>
</div>
















