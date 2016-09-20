<a data-dropdown="drop-${userInstance.id}" class=" dropdown button secondary round" href="#">




    <i class="fi-wrench"></i>




</a>



<ul id="drop-${userInstance.id}" data-dropdown-content class="f-dropdown">

    <li>
        <g:link controller="companyAccountUser" action="edit"
                params="[id: userInstance.id]">
            ${message(code: 'companyAccountUser.edit.label')}
        </g:link>
    </li>
    <g:if test="${sec.username() != userInstance.username}">


        <li>
            <g:link controller="companyAccountUser" action="resendActivation"
                    params="[id: userInstance.id]">
                <g:message code="send.activation.email" />
            </g:link>
        </li>




        <li>
            <g:link controller="companyAccountUser" action="delete"
                    params="[id: userInstance.id]"
                    onclick="return confirm('${message(code: 'system.deleteUser.warning', args: ["${userInstance.username}"])}');">
                ${message(code: 'companyAccountUser.delete.label')}
            </g:link>
        </li>
    </g:if>
</ul>
