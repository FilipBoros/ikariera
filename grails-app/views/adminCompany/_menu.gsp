<a data-dropdown="drop-${companyInstance?.id}" class=" dropdown button secondary round" href="#">

    <i class="fi-wrench"></i>
</a>

<ul id="drop-${companyInstance?.id}" data-dropdown-content class="f-dropdown">



<g:if test="${companyInstance?.validate()}">

    <li>

        <g:link controller="adminCompany" action="disableCompany" class="ajaxCall" tableAffectCol="8" tableAffectColName="1"
                style="display: ${companyInstance?.active ? 'block;' : 'none;'}"
                params="[id: companyInstance.id]">
            ${message(code: 'admin.company.deny')}
        </g:link>

        <g:link controller="adminCompany" action="enableCompany" class="ajaxCall" tableAffectCol="8" tableAffectColName="1"
                style="display: ${companyInstance?.active ? 'none;' : 'block;'}"
                params="[id: companyInstance.id]">
            ${message(code: 'admin.company.activate')}
        </g:link>

    </li>

    <li>

        <g:link controller="adminCompany" action="companyProfileUnrestrictedEdit"
                params="[id: companyInstance.id, returnAddress: returnAddress, returnPath: returnPath]">
            ${message(code: 'admin.company.editProfile')}
        </g:link>

    </li>

    <li>

        <g:link controller="adminCompany" action="unMarkPersonalCompany" class="ajaxCall" tableAffectCol="7"
                style="display: ${companyInstance?.personalAgency ? 'block;' : 'none;'}"
                params="[id: companyInstance.id]">
            ${message(code: 'admin.company.isNotPersonal')}
        </g:link>


        <g:link controller="adminCompany" action="markPersonalCompany" class="ajaxCall" tableAffectCol="7"
                style="display: ${companyInstance?.personalAgency ? 'none;' : 'block;'}"
                params="[id: companyInstance.id]">
            ${message(code: 'admin.company.isPersonal')}
        </g:link>

    </li>





    <li>

        <g:link controller="adminCredits" action="addRemoveCredits"
                params="[id: companyInstance.id]">
            ${message(code: 'admin.company.addCredit')}
        </g:link>

    </li>



    <li>

        <g:link controller="adminCompany" action="logAsCompany" target="_blank"
                params="[id: companyInstance?.id]">
            ${message(code: 'admin.company.signAs')}
        </g:link>

    </li>



    <li>

        <g:link controller="adminCompanyService" action="index" target="_blank" params="[id: companyInstance.id]" >
            ${message(code: 'admin.company.activateServices')}
        </g:link>

    </li>

    <li>
        <g:link controller="adminCompany" action="setStartup" class="ajaxCall" tableAffectCol="9"
                style="display: ${companyInstance?.startup>new Date() ? 'none;' : 'block;'}"
                params="[id: companyInstance.id]">
            ${message(code: 'setStartup.label')}
        </g:link>
        <g:link controller="adminCompany" action="denyStartup" class="ajaxCall" tableAffectCol="9"
                style="display: ${companyInstance?.startup>new Date() ? 'block;' : 'none;'}"
                params="[id: companyInstance.id]">
            ${message(code: 'denyStartup.label')}
        </g:link>

    </li>
%{--
        <g:link controller="adminCompany" action="companyProfileUnrestrictedEdit"
                params="[id: companyInstance.id, returnAddress: returnAddress, returnPath: returnPath]">
            ${message(code: 'admin.company.editProfile')}
        </g:link>
--}%

    <li>
        <sec:ifAllGranted roles="ROLE_SUPER_ADMIN">
            <g:link controller="adminCompany" action="delete" class="confirmIt" id="${companyInstance.id}"
                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"
            >

                <g:message code="admin.company.delete" />
            </g:link>
        </sec:ifAllGranted>
    </li>
</g:if>
<g:else>

    <li>

        <g:link controller="adminCompany" action="companyProfileUnrestrictedEdit"
                params="[id: companyInstance.id, returnAddress: returnAddress, returnPath: returnPath]">
            ${message(code: 'admin.company.editProfile')}
        </g:link>

    </li>

</g:else>

</ul>