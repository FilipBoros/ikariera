<table class="tabled-list-form" id="skills-table-list"
       skillChildCount="${studentInstance?.skills?.size()?: 0}">

    <tr>
        <th  style="width: 206px"><g:message code="reqSkills.skill.label"/></th>
        <th colspan="2" style="width: 214px"><g:message code="reqSkills.level.label"/></th>
        <th></th>
    </tr>



    <g:each var="skill" in="${studentInstance?.skills}" status="i">


        <g:render template='/layouts/_fields/skill/skill'
                  model="['skill': skill, 'i': i, 'hidden': false]"/>


    </g:each>

</table>




