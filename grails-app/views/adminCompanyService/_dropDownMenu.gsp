<g:if test="${service && service > new Date()}">

    <g:link controller="adminCompanyService" action="expireCompanyService"  id="${companyInstance.id}" params="[serviceUniqueName:  serviceUniqueName]" class="button secondary round alert">Expire</g:link>


</g:if>
<g:else>

    <g:link controller="adminCompanyService" action="activateCompanyService" id="${companyInstance.id}" params="[serviceUniqueName: serviceUniqueName]" class="button secondary round success">Activate</g:link>



</g:else>