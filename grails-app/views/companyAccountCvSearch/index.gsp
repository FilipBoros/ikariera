<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-12 columns">

        <h2><g:message code="service.cvsearch"/></h2>

    </div>
    <hr>
</div>


<div class="row">

    <div class="large-12 columns">

        <g:form controller="companyAccountCvSearch" action="index" method="GET">

            <g:render template="search" model="[filterParams: filterParams]"/>

        </g:form>

    </div>

</div>


<div class="row">

    <div class="large-12 columns">

        <g:render template="list"
                  model="[studentInstanceList: studentInstanceList, studentInstanceTotal: studentInstanceTotal]"/>
    </div>

</div>

</body>
</html>