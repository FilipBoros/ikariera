<%@ page import="cz.ikariera.admin.Faq" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>


<div class="row">

    <br/>

    <div class="large-12">

        <h1>${message(code: 'faq.list')}</h1>

    </div>

</div>


<div class="row">

    <br/>

    <div class="large-12">

<div>

    <div id="list-faq" class="content scaffold-list" role="main">

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <g:each in="${faqInstanceList}" status="i" var="faqInstance">

            <h2><g:link action="show4users"
                        id="${faqInstance.id}">${fieldValue(bean: faqInstance, field: "question")}</g:link></h2>

            ${fieldValue(bean: faqInstance, field: "answer")}
            <br>

        </g:each>

        <div class="pagination">
            <g:paginate total="${faqInstanceTotal}"/>
        </div>
    </div>
</div>

    </div>

</div>

</body>
</html>
