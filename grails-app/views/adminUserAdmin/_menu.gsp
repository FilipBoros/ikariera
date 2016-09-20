<div class="btn-toolbar">
    <div class="btn-group">

        <a data-dropdown="drop-${adminInstance.id}" class=" dropdown button secondary round right" href="#">

            <i class="fi-wrench"></i>
        </a>

        <ul id="drop-${adminInstance.id}" data-dropdown-content class="f-dropdown">

            <li>
                <g:link controller="adminUserAdmin" action="edit" params="[id: adminInstance?.id]">
                    ${message(code: 'companyAccountUser.edit.label')}
                </g:link>
            </li>
            <sec:ifAllGranted roles="ROLE_SUPER_ADMIN">
                <g:if test="${sec.username() != adminInstance?.username}">

                    <li>
                        <g:link controller="adminUserAdmin" action="delete" class="confirmIt" id="${adminInstance.id}"
                                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">

                            <g:message code="delete" />
                        </g:link>
                    </li>

                </g:if>
            </sec:ifAllGranted>
        </ul>
    </div>

</div>