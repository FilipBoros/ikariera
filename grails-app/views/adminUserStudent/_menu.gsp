<div class="btn-group">
    <a data-dropdown="drop-${userInstance.id}" class=" dropdown button secondary round" href="#">

        <i class="fi-wrench"></i>
    </a>

    <ul id="drop-${userInstance.id}" data-dropdown-content class="f-dropdown">

        <li>

            <g:link controller="adminUserStudent" action="edit"
                    params="[id: userInstance?.id]">
                ${message(code: 'admin.studentUser.edit')}
            </g:link>

        </li>




        <li>

            <g:link controller="adminUserAll" action="enableUser" class="ajaxCall" tableAffectCol="9"
                    style="display: ${userInstance?.enabled ? 'none;' : 'block;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.enable.label"/>
            </g:link>


            <g:link controller="adminUserAll" action="disableUser" class="ajaxCall" tableAffectCol="9"
                    style="display: ${userInstance?.enabled ? 'block;' : 'none;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.disable.label"/>
            </g:link>

        </li>



        <li>

            <g:link controller="adminUserAll" action="unBanUser" class="ajaxCall" tableAffectCol="6"
                    style="display: ${userInstance?.accountLocked ? 'block;' : 'none;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.confirm.label"/>
            </g:link>


            <g:link controller="adminUserAll" action="BanUser" class="ajaxCall" tableAffectCol="6"
                    style="display: ${userInstance?.accountLocked ? 'none;' : 'block;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.deny.label"/>
            </g:link>

        </li>




        <li>

            <g:link controller="adminUserStudent" action="removeUserFromMailingList" class="ajaxCall" tableAffectCol="12"
                    style="display: ${userInstance?.studentAccount?.infoEmails  ? 'block;' : 'none;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.student.remove.from.maling.list.label"/>
            </g:link>


            <g:link controller="adminUserStudent" action="addUserToMailingList" class="ajaxCall" tableAffectCol="12"
                    style="display: ${userInstance?.studentAccount?.infoEmails ? 'none;' : 'block;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.student.add.to.maling.list.label"/>
            </g:link>

        </li>






        <li>

            <g:link controller="adminUserAll" action="unExpirPass" class="ajaxCall" tableAffectCol="8"
                    style="display: ${userInstance?.accountExpired ? 'block;' : 'none;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.enablePassword.label"/>
            </g:link>


            <g:link controller="adminUserAll" action="expirPass" class="ajaxCall" tableAffectCol="8"
                    style="display: ${userInstance?.accountExpired ? 'none;' : 'block;'}"
                    params="[id: userInstance.id]">
                <g:message code="user.expirePassword.label"/>
            </g:link>

        </li>

        <li>

            <g:link controller="adminUserAll" action="resetUserPassword"
                    params="[id: userInstance?.id, returnPath: (request.forwardURI - request.contextPath)]">
                ${message(code: 'admin.resetPassword.label')}
            </g:link>

        </li>

        <li>
            <sec:ifAllGranted roles="ROLE_SUPER_ADMIN">
                <g:link controller="adminUserStudent" action="delete" class="confirmIt" id="${userInstance.id}"
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">

                    <g:message code="delete" />
                </g:link>
            </sec:ifAllGranted>
        </li>

    </ul>

</div>