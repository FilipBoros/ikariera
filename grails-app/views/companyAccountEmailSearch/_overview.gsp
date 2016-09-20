<ul class="no-bullet">
    <li>

        ${message(code: 'company.email.receiversNumber.label')}

        ${studentInstanceTotal}

    </li>
    <li>
        ${message(code: 'company.email.creditState.label')}

        ${companyCredits}

    </li>
    <li>

        ${message(code: 'company.email.completePrice.label')}

        ${creditPrice}

    </li>
    <li>

        ${message(code: 'company.email.creditRemains.label')}


        <g:if test="${companyCredits - creditPrice >= 0}">
            ${companyCredits - creditPrice}
        </g:if>
        <g:else>
            <span style="color: red">${companyCredits - creditPrice}</span>

            <div style="color: red">${message(code: 'company.email.notEnoughCredits.error')}</div>
        </g:else>

</ul>


