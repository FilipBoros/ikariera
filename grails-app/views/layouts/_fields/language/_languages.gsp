<table class="tabled-list-form" id="languages-table-list"
       languageChildCount="${studentInstance?.languages?.size()?: 0}">

    <tr>
        <th  style="width: 206px"><g:message code="reqLanguages.language.label"/></th>
        <th colspan="2" style="width: 214px"><g:message code="reqLanguages.level.label"/></th>
        <th></th>
    </tr>



    <g:each var="language" in="${studentInstance?.languages}" status="i">


        <g:render template='/layouts/_fields/language/language'
                  model="['language': language, 'i': i, 'hidden': false]"/>


    </g:each>

</table>




