<!doctype html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="student.working.experience" /> </h2>

        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <g:form action="save">


            <g:render template="/layouts/_fields/experience/experiences" model="['studentInstance': studentInstance]"/>


            <div class="table-row-add-dashed-border">

            </div>

            <a href="#" id="add-new-experience" class="button success ">
                <i class="fi-plus"></i>
                <g:message code="job.addExpierence.label"/>

            </a>

            <button name="create" class="save long ">
                ${message(code: 'system.saveChanges.button')}
            </button>

        </g:form>

    </div>

</div>




<table style="display: none;" id="experience-row-clone">
    <g:render template='/layouts/_fields/experience/experience' model="['experience': null, 'i': '_clone']"/>
</table>

</body>
</html>



