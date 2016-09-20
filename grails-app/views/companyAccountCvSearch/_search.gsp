<div class="row">
    <div class="large-4 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey  : 'id',
                label      : message(code: 'companyEmailSearch.interest.university'),
                placeholder: '',
                value      : filterParams?.educations,
                ,
                field      : 'educations',
                name       : 'educations',
                from       : cz.ikariera.student.University.list(sort: 'posOrder')]"/>

    </div>

    <div class="large-4 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey  : 'id',
                label      : message(code: 'companyEmailSearch.interest.jobCategroy'),
                placeholder: '',
                value      : filterParams?.jobCategories,
                ,
                field      : 'jobCategories',
                name       : 'jobCategories',
                from       : cz.ikariera.company.JobCategory.list(sort: 'posOrder')]"/>

    </div>

    <div class="large-2 columns">

        <g:render template="/layouts/_fields/multiSelect" model="[
                optionKey  : 'id',
                label      : message(code: 'companyEmailSearch.interest.language'),
                placeholder: '',
                value      : filterParams?.languages,
                ,
                field      : 'languages',
                name       : 'languages',
                from       : cz.ikariera.student.LanguageType.list(sort: 'posOrder')]"/>

    </div>

    <div class="large-2 columns">
        <button name="create" class="save long">
            ${message(code: 'default.button.search.label')}
        </button>

    </div>

</div>