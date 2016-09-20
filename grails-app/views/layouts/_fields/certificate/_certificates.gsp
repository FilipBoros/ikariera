<table class="tabled-list-form" id="certificates-table-list"
       certificateChildCount="${studentInstance?.certificates?.size()?:0}"
>

    <tr>
    <th  style="width: 206px">
        <g:message code="certificate.name.label"/>
    </th>
    <th>
        <g:message code="certificate.level.label"/>
    </th>
        <th colspan="2" style="width: 214px"><g:message code="certificate.description.label"/></th>
    <th></th>
    </tr>



    <g:each var="certificate" in="${studentInstance?.certificates}" status="i">


        <g:render template='/layouts/_fields/certificate/certificate' model="['certificate': certificate, 'i': i, 'hidden': false]"/>


    </g:each>

</table>




