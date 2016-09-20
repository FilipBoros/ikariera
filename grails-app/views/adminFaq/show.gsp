<%@ page import="cz.ikariera.admin.Faq" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>${message(code: 'admin.listCompanies.label')}</h2>


        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <div class="admin-btn-group">
            <g:link class="btn btn-large btn-primary" action="list" controller="adminFaq"><g:message
                    code="default.list.label"
                    args="[entityName]"/></g:link>
        </div>


        <div id="list-faq" class="content scaffold-list" role="main">
            <h2><g:message code="default.show.label" args="["FAQ"]"/></h2>

            %{----}%
            %{--<div id="show-faq" class="content scaffold-show" role="main">--}%
            <ol class="property-list faq">

                <g:if test="${faqInstance?.question}">
                    <li class="fieldcontain">
                        <span id="question-label" class="property-label"><g:message code="faq.question.label"
                                                                                    default="Question"/></span>

                        <span class="property-value" aria-labelledby="question-label"><g:fieldValue
                                bean="${faqInstance}"
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

                <g:if test="${faqInstance?.posOrder}">
                    <li class="fieldcontain">
                        <span id="posOrder-label" class="property-label"><g:message code="faq.posOrder.label"
                                                                                    default="Pos Order"/></span>

                        <span class="property-value" aria-labelledby="posOrder-label"><g:fieldValue
                                bean="${faqInstance}"
                                field="posOrder"/></span>

                    </li>
                </g:if>

                <g:if test="${faqInstance?.languageType}">
                    <li class="fieldcontain">
                        <span id="languageType-label" class="property-label"><g:message code="faq.languageType.label"
                                                                                        default="Language Type"/></span>

                        <span class="property-value" aria-labelledby="languageType-label"><g:link
                                controller="languageType"
                                action="show"
                                id="${faqInstance?.languageType?.id}">${faqInstance?.languageType?.name}</g:link></span>

                    </li>
                </g:if>

            </ol>

        </div>
    </div>
</body>
</html>
