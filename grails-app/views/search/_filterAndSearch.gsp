

<g:form name="filterForm"
        controller="jobOffer"
        action="filter"
        method="get">

    <g:select name="jobCategories"
              from="${cz.ikariera.company?.JobCategory?.list()}"
              multiple="true"
              optionKey="id"
              optionValue="name"
              value="${filterParams?.jobCategories}"/>

    <g:select name="positionLocalities"
              from="${cz.ikariera.company?.Locality?.list()}"
              multiple="true"
              optionKey="id"
              optionValue="name"
              value="${filterParams?.positionLocalities}"/>

    <g:select name="minSalary"
              from="${cz.ikariera.company?.JobSalary?.list()}"
              optionKey="min"
              optionValue="min"
              value="${filterParams?.min}"
              noSelection="[null: '-']"/>

    <g:select name="maxSalary"
              from="${cz.ikariera.company?.JobSalary?.list()}"
              optionKey="max"
              optionValue="max"
              value="${filterParams?.max}"
              noSelection="[null: '-']"/>

    <g:select name="company.id"
              from="${cz.ikariera.company?.Company?.list()}"
              optionKey="id"
              optionValue="companyName"
              value="${filterParams?.company?.id}"
              noSelection="[null: '-']"/>

    <g:select name="advertisementType"
              from="${cz.ikariera.company?.JobOffer?.AdvertisementType?.list()}"
              optionKey="key"
              value="${filterParams?.advertisementType?.key}"
              noSelection="[null: '-']"/>


    <div>
        <label for="graduatePosition">
            ${message(code: 'graduate.position')}
        </label>
        <g:checkBox name="graduatePosition"
                    value="${filterParams?.graduatePosition}"/>
    </div>

    <div>
        <g:textField name="fultextSearch"
                     value="${params.fultextSearch}"/>
    </div>
    <g:hiddenField name="max" value="${params.max}"/>

    <g:submitButton name="search" value="${message(code: 'button.search')}"/>

</g:form>
