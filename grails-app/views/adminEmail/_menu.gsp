<a data-dropdown="drop-${adminEmailsInstance?.id}" class=" dropdown button secondary round" href="#">

    <i class="fi-wrench"></i>
</a>

<ul id="drop-${adminEmailsInstance?.id}" data-dropdown-content class="f-dropdown">

    <li>
        <g:link controller="adminEmail" action="sendEmail"
                params="[id: adminEmailsInstance.id]" class="btn btn-primary">
            ${message(code: 'companyEmails.sentEmail.label')}
        </g:link>
    </li>

    <li>
        <g:link controller="adminEmail" action="displayEmail"
                params="[id: adminEmailsInstance.id]">
            ${message(code: 'companyEmails.showEmail.label')}
        </g:link>
    </li>

    <li>
        <g:link controller="adminEmail" action="deleteEmail"
                params="[id: adminEmailsInstance.id]">
            ${message(code: 'companyEmails.deleteEmail.label')}
        </g:link>
    </li>


</ul>