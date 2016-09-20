<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
<style>
    div.articleItem {

    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    padding: 20px;
    min-height: 30px;
    }
</style>
</head>

<body>
<div class="panel">
    <div class="row">

        <div class="large-12 columns">

            <h1>${message(code: 'articles.h2.public.list.label')}</h1>

        </div>

    </div>
</div>

<div class="row">
    <div class="large-12 columns">

        <g:if test="${!articleInstanceList}">
            ${message(code: 'articles.noEntries.label')}

        </g:if>
        <g:else>
            <g:each in="${articleInstanceList}" status="i" var="articleInstance">
                <g:render template="table" model="[articleInstance : articleInstance]" />
            </g:each>

            <g:paginateFoundation total="${articleInstanceTotal}" params="${params}"
                                  maxsteps="4"
                                  next="${message(code: "list.paginate.next")}"
                                  prev="${message(code: "list.paginate.prev")}"/>

        </g:else>

    </div>

</div>

</body>
</html>




