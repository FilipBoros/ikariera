<!doctype html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="student.education" /> </h2>

        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <g:form action="save">


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

</body>
</html>



