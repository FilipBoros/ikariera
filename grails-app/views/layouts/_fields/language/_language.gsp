<tr class="cloned-table-row">

    <td>

        <g:hiddenField name='languages[${i}].id' value='${language?.id}' class="exclude-from-clone"/>




            <g:select name="languages[${i}].languageType.id"
                      id="languages[${i}].languageType.id"
                      from="${cz.ikariera.student?.LanguageType?.list(sort: "posOrder")}"
                      optionKey="id"


                      optionValue="${{ message(code: it.i18NameFull) }}"
                      value="${language?.languageType?.id}"
                      style="width: 150px;"/>

</td>
<td>

        <g:select name="languages[${i}].level.id"
                  id="languages[${i}].level.id"
                  from="${cz.ikariera.student?.LanguageLevel?.list(sort: "posOrder")}"
                  optionKey="id"


                  optionValue="${{ message(code: it.i18NameFull) }}"
                  value="${language?.level?.id}"
                  style="width: 200px;"/>


    </td>
    <td>

        <div class="del-cloned-table-row">
            <a href="#" class="button alert tiny"><i class="fi-x"></i></a>
        </div>

    </td>

</tr>

