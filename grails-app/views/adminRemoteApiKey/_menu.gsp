<a data-dropdown="drop-${remoteApiKeyInstance?.id}" class=" dropdown button secondary round" href="#">

    <i class="fi-wrench"></i>
</a>

<ul id="drop-${remoteApiKeyInstance?.id}" data-dropdown-content class="f-dropdown">

        <li>

            <g:link controller="adminRemoteApiKey" action="edit"

                    params="[id: remoteApiKeyInstance.id]">
                ${message(code: 'edit')}
            </g:link>

        </li>




        <li>
            <sec:ifAllGranted roles="ROLE_SUPER_ADMIN">
                <g:link controller="adminRemoteApiKey" action="delete" class="confirmIt" id="${remoteApiKeyInstance.id}"
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">

                    <g:message code="delete" />
                </g:link>
            </sec:ifAllGranted>
        </li>

</ul>