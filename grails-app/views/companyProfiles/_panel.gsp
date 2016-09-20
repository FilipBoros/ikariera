<div class="panel detailInfo">

    <i class="fi-marker"></i>
    <h5>
        ${message(code: 'companyDetail.companyAddress.label')}
    </h5>


    <p>

        ${companyInstance?.companyStreet}
        <br/>
        ${companyInstance?.companyZip}, ${companyInstance?.companyCity}
        <br/>
        ${companyInstance?.companyCountry}

    </p>


    <p>${message(code: 'companyDetail.companyID.label')}


    ${companyInstance?.companyID}
        <br/>
        ${message(code: 'companyDetail.companyVatID.label')}



        ${companyInstance?.companyVatID}
    </p>

    <hr/>

    <g:if test="${companyInstance?.companyWeb}">
    <h5>
        ${message(code: 'companyDetail.companyWeb.label')}
    </h5>

    <p>

            <g:displayWeb web="${companyInstance?.companyWeb}"/>

        %{--        <g:link absolute="true" url="${companyInstance?.companyWeb}" target="_blank" >${companyInstance?.companyWeb}</g:link>--}%

    </p>

    <hr/>
    </g:if>

    <i class="fi-wrench"></i>
    <h5>
        ${message(code: 'companyDetail.companyCategory.label')}
    </h5>


    <p>
        <g:each in="${companyInstance?.mainJobCategories?.sort { it.posOrder }}" status="i"
                var="jobCategoryInstance">

            ${message(code: jobCategoryInstance.i18NameFull)}, <br>

        </g:each>
    </p>%{--${companyInstance?.startup}--}%
    <g:if test="${companyInstance?.startup > new Date()}">
        <hr/>
        <h5>
            ${message(code: 'company.type.label')}
        </h5>

        <p>
            ${message(code: 'startup.label')}
        </p>
    </g:if>
</div>


