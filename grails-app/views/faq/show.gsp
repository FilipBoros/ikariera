<%@ page import="cz.ikariera.admin.Faq" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<h1><g:message code="default.show.label" args="[entityName]"/></h1>

<div class="admin-btn-group">
    <g:link class="btn btn-large btn-primary" action="list4users" controller="faq"><g:message code="default.list.label"
                                                                                              args="[entityName]"/></g:link>
</div>


<div id="list-faq" class="content scaffold-list" role="main">

    <ol class="property-list faq">

        <g:if test="${faqInstance?.question}">
            <li class="fieldcontain">
                <span id="question-label" class="property-label"><g:message code="faq.question.label"
                                                                            default="Question"/></span>

                <span class="property-value" aria-labelledby="question-label"><g:fieldValue bean="${faqInstance}"
                                                                                            field="question"/></span>

            </li>
        </g:if>

        <g:if test="${faqInstance?.answer}">
            <li class="fieldcontain">
                <span id="answer-label" class="property-label"><g:message code="faq.answer.label"
                                                                          default="Answer"/></span>

                <span class="property-value" aria-labelledby="answer-label"><g:fieldValue bean="${faqInstance}"
                                                                                          field="answer"/></span>

            </li>
        </g:if>

        <g:if test="${faqInstance?.languageType}">
            <li class="fieldcontain">
                <span id="languageType-label" class="property-label"><g:message code="faq.languageType.label"
                                                                                default="Language Type"/></span>

                <span class="property-value"
                      aria-labelledby="languageType-label">${faqInstance?.languageType?.name}</span>

            </li>
        </g:if>

    </ol>
</div>
</body>
</html>
