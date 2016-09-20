<table class="tabled-list-form" id="universities-table-list"
     universityChildCount="${studentInstance?.educations?.size() ?: 0}"
     style="width:100%">

      %{-- <tr>
           <th style="width:125px;"><g:message code="university.endYear.label"/></th>
           <th style="width:62px;"><g:message code="university.title.label"/></th>
           <th style="width:218px;"><g:message code="university.category.label"/></th>
           <th style="width:230px;"><g:message code="university.specialization.label"/></th>
           <th style="width:269px;" colspan="2" ><g:message code="university.university.label"/></th>
       </tr>--}%


        <g:each in="${studentInstance?.educations}" status="i" var="education">

            <g:render template='/layouts/_fields/university/university'
                      model="['education': education, 'hidden': false, 'i': i]"/>

        </g:each>


</table>


