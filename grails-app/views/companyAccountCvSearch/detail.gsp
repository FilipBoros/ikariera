<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="medium-10 columns">

        <h4 class="subheader"><g:message code="student.profile" /> </h4>

        <h2>${studentInstance?.user?.firstName} ${studentInstance?.user?.lastName}</h2>

    </div>
    <div class="medium-2 columns">
    <g:link class="button secondary radius expand " onClick="history.back();return false;" > <g:message code="back" /> </g:link>

    </div>
    <hr>
</div>


<div class="row">

    <div class="large-4 columns">

        <g:render template="detailPanel" model="[studentInstance: studentInstance]"/>

    </div>


    <div class="large-8 columns">

        <g:render template="detailStudy" model="[studentInstance: studentInstance]"/>
    </div>

</div>

</body>
</html>
