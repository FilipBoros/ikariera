

        <a data-dropdown="drop-${msg.id}" class=" dropdown button secondary round" href="#">

            <i class="fi-wrench"></i>
        </a>

        <ul id="drop-${msg.id}" data-dropdown-content class="f-dropdown">

            <li>
                <g:link controller="adminMaintenanceMessage" action="edit" id="${msg.id}">
                    ${message(code: 'edit')}
                </g:link>
            </li>
            <li>
                <g:link controller="adminMaintenanceMessage" action="delete"  id="${msg.id}">
                    ${message(code: 'delete')}
                </g:link>
            </li>
        </ul>
