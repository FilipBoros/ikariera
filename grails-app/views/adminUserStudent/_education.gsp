

<div class="row">

    <div class="large-12 columns">

        <g:form action="saveEducation">


            <g:hiddenField name="userVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="studentVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="userId" value="${userInstance?.id}"/>

            <g:render template="/layouts/_fields/university/universities" model="['studentInstance': studentInstance]"/>


            <div class="table-row-add-dashed-border">

            </div>


            <a href="#" id="add-new-university" class="button success">
                <i class="fi-plus"></i>
                <g:message code="university.addUniverity.label"/>
            </a>

            <button name="create" class="save long ">
                ${message(code: 'system.saveChanges.button')}
            </button>
        </g:form>

    </div>

</div>



<table style="display: none;" id="university-row-clone">
    <g:render template='/layouts/_fields/university/university' model="['university': null, 'i': '_clone']"/>
</table>



