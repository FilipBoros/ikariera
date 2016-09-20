<div class="btn-toolbar">
    <div class="btn-group">

        <a data-dropdown="drop-${server.id}" class=" dropdown button secondary round right" href="#">

            <i class="fi-wrench"></i>
        </a>

        <ul id="drop-${server.id}" data-dropdown-content class="f-dropdown">

            <li>
                <g:link controller="adminRemoteServer" action="edit" params="[id: server?.id]">
                    ${message(code: 'companyAccountUser.edit.label')}
                </g:link>
            </li>
            <li>
                <g:link controller="adminRemoteServer" action="delete" id="${server.id}">
                    <g:message code="delete" />
                </g:link>
            </li>
        </ul>
    </div>

</div>