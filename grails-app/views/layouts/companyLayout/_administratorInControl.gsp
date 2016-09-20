<sec:access expression="hasRole('ROLE_ADMIN')">
    <g:render template="/layouts/adminLayout/topLineAdministratorInControl"/>
</sec:access>
<sec:access expression="!hasRole('ROLE_ADMIN')">
    <div class="admin-header"><g:message
            args="[sec.loggedInUserInfo(field: 'companyName'), loggedUserCompanyCredits(id: sec.loggedInUserInfo(field: 'id'))]"
            code="company.accountName.label"/></div>
</sec:access>