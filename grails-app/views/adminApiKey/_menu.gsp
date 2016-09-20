<div class="btn-toolbar">
    <div class="btn-group">

        <a data-dropdown="drop-${apiKeyInstance.id}" class=" dropdown button secondary round right" href="#">

            <i class="fi-wrench"></i>
        </a>

        <ul id="drop-${apiKeyInstance.id}" data-dropdown-content class="f-dropdown">

            <li>
                <g:link controller="adminApiKey" action="edit" params="[id: apiKeyInstance?.id]">
                    ${message(code: 'companyAccountUser.edit.label')}
                </g:link>
            </li>
            <li>
                <g:link controller="adminApiKey" action="delete" id="${apiKeyInstance.id}">
                    <g:message code="delete" />
                </g:link>
            </li>
        </ul>
    </div>

</div>