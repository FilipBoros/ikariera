<tr class="cloned-table-row">
    <td>
        <g:hiddenField name='certificates[${i}].id' value='${certificate?.id}' class="exclude-from-clone"/>
        <g:textField name="certificates[${i}].name" value="${certificate?.name}"/>
    </td>
    <td>
        <g:textField name="certificates[${i}].level" value="${certificate?.level}"/>
    </td>


    <td>

        <g:textArea cols="50" rows="2" style="width: 450px" name="certificates[${i}].description"
                    value="${certificate?.description}"/>
    </td>
    <td>
        <div class="del-cloned-table-row">
            <a href="#" class="alert button tiny"><i class="fi-x"></i></a>
        </div>
    </td>

</tr>

