<ul class="no-bullet">
    <li>
        ${message(code: 'company.email.email.label')}:



        <g:link controller="companyAccountEmail" action="display" id="${companyEmailsInstance?.id}"
                target="_blank">${companyEmailsInstance?.subject} (náhled)</g:link>
    </li>
    <li>
        ${message(code: 'company.email.dateCreated.label')}:


        <g:formatDate format="dd.MM.yyyy" date="${companyEmailsInstance?.dateCreated}"/>


</ul>
