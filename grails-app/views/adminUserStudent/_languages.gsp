
<div class="row">

    <div class="large-12 columns">

        <g:form action="saveLanguages">

            <g:hiddenField name="userVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="studentVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="userId" value="${userInstance?.id}"/>
            %{--<g:hiddenField name="languages" value="${userInstance?.id}"/>--}%

            <g:render template="/layouts/_fields/language/languages" model="['studentInstance': studentInstance]"/>

            <div class="table-row-add-dashed-border">

            </div>

            <a href="#" id="add-new-language" class="button success ">
                <i class="fi-plus"></i>
                <g:message code="jobLanguage.add.label"/>

            </a>


                <button name="create" class="save long ">
                    ${message(code: 'system.saveChanges.button')}
                </button>

        </g:form>

    </div>

</div>



<table style="display: none;" id="language-row-clone">
    <g:render template='/layouts/_fields/language/language' model="['language': null, 'i': '_clone']"/>
</table>




