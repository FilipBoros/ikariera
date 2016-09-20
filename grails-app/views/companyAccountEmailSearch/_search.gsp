<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey  : 'id',
                label      : message(code: 'companyEmailSearch.interest.university'),
                placeholder: message(code: 'company.email.allUniversity.label'),
                value      : filterParams?.educations,
                ,
                field      : 'educations',
                name       : 'educations',
                from       : cz.ikariera.student.University.list(sort: 'posOrder')]"/>

    </div>
</div>

<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey  : 'id',
                label      : message(code: 'companyEmailSearch.interest.jobCategroy'),
                placeholder: message(code: 'company.email.allCategory.label'),
                value      : filterParams?.jobCategories,
                ,
                field      : 'jobCategories',
                name       : 'jobCategories',
                from       : cz.ikariera.company.JobCategory.list(sort: 'posOrder')]"/>

    </div>

</div>

<div class="row">
    <div class="large-12 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey  : 'id',
                label      : message(code: 'companyEmailSearch.interest.language'),
                placeholder: message(code: 'company.email.allLanguage.label'),
                value      : filterParams?.languages,
                ,
                field      : 'languages',
                name       : 'languages',
                from       : cz.ikariera.student.LanguageType.list(sort: 'posOrder')]"/>

    </div>

</div>


