<a data-dropdown="drop-${companyEmailsInstance.id}" class="button dropdown secondary round" href="#">

    <i class="fi-wrench"></i>

</a>




<ul id="drop-${companyEmailsInstance.id}" data-dropdown-content class="f-dropdown">

    <li>
        <g:link controller="companyAccountEmail" action="edit"
                params="[id: companyEmailsInstance.id]">
            ${message(code: 'companyEmails.editEmail.label')}
        </g:link>
    </li>

    <li>
        <g:link controller="companyAccountEmail" action="display"
                params="[id: companyEmailsInstance.id]">
            ${message(code: 'companyEmails.showEmail.label')}
        </g:link>
    </li>

    <li><g:link controller="companyAccountEmail" action="delete"
                params="[id: companyEmailsInstance.id]">
                ${message(code: 'companyEmails.deleteEmail.label')}
        </g:link>
    </li>

</ul>



