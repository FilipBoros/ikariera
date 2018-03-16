
<div class="row">

    <div class="large-12 columns">

        <g:form action="saveSkills">

            <g:hiddenField name="userVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="studentVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="userId" value="${userInstance?.id}"/>

            <g:render template="/layouts/_fields/skill/skills" model="['studentInstance': studentInstance]"/>

            <div class="table-row-add-dashed-border">

            </div>

            <a href="#" id="add-new-skill" class="button success ">
                <i class="fi-plus"></i>
                <g:message code="jobSkill.add.label"/>

            </a>


                <button name="create" class="save long ">
                    ${message(code: 'system.saveChanges.button')}
                </button>

        </g:form>

    </div>

</div>



<table style="display: none;" id="skill-row-clone">
    <g:render template='/layouts/_fields/skill/skill' model="['skill': null, 'i': '_clone']"/>
</table>




