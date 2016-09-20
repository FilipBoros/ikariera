<table class="tabled-list-form" id="experiences-table-list"
       experienceChildCount="${studentInstance?.experiences?.size() ?: 0}">

    %{--<tr>
        <th style="width: 435px"><g:message code="job.position.label"/></th>
        <th   colspan="2" style="width: 476px"><g:message code="job.kindOfWork.label"/></th>
    </tr>--}%



    <g:each var="experience" in="${studentInstance?.experiences}" status="i">

        <g:render template='/layouts/_fields/experience/experience'
                  model="['experience': experience, 'i': i, 'hidden': false]"/>

    </g:each>

</table>




