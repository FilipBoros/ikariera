<tr class="cloned-table-row">

    <td>

        <g:hiddenField name='skills[${i}].id' value='${skill?.id}' class="exclude-from-clone"/>




            <g:select name="skills[${i}].skillType.id"
                      id="skills[${i}].skillType.id"
                      from="${cz.ikariera.student?.SkillType?.list(sort: "posOrder")}"
                      optionKey="id"


                      optionValue="${{ message(code: it.i18NameFull) }}"
                      value="${skill?.skillType?.id}"
                      style="width: 150px;"/>

</td>
<td>

        <g:select name="skills[${i}].level.id"
                  id="skills[${i}].level.id"
                  from="${cz.ikariera.student?.SkillLevel?.list(sort: "posOrder")}"
                  optionKey="id"


                  optionValue="${{ message(code: it.i18NameFull) }}"
                  value="${skill?.level?.id}"
                  style="width: 200px;"/>


    </td>
    <td>

        <div class="del-cloned-table-row">
            <a href="#" class="button alert tiny"><i class="fi-x"></i></a>
        </div>

    </td>

</tr>

