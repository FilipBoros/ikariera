<a data-dropdown="drop-${contactInstance.id}" class="button dropdown secondary round" href="#">

    <i class="fi-wrench"></i>

</a>



<ul id="drop-${contactInstance.id}" data-dropdown-content class="f-dropdown">

    <li>
        <g:link controller="companyContacts" action="edit" params="[id: contactInstance.id]">
            ${message(code: 'companyEmails.editEmail.label')}
        </g:link>
    </li>

    <li>
        <g:link controller="companyContacts" action="delete" id='${contactInstance.id}'>
            ${message(code: 'companyEmails.deleteEmail.label')}
        </g:link>
    </li>

</ul>
