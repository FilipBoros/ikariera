<%@ page import="cz.ikariera.admin.Faq" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: 'admin.faq.label')}</h2>

    </div>

    <div class="large-3 columns">
        <br/>
        <g:link class="button right" action="create" controller="adminFaq">
            <g:message
                    code="default.new.label"
                    args="['FAQ']"/></g:link>
    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <hr>

        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="posOrder" width="80px" style="text-align: center"
                                  title="${message(code: 'faq.posOrder.label', default: 'Pos Order')}"/>

                <g:sortableColumn property="question"
                                  title="${message(code: 'faq.question.label', default: 'Question')}"/>





                <th width="80px"><g:message code="faq.languageType.label" default="Language Type"/></th>

                <th width="80px">${message(code: 'faq.action.label')}<th>

            </tr>
            </thead>
            <tbody>

            <g:if test="${!faqInstanceList}">
                <tr>
                    <td colspan="2"><g:message code="empty"/></td>
                </tr>

            </g:if>
            <g:else>
                <g:each in="${faqInstanceList}" status="i" var="faqInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td style="text-align: center">${fieldValue(bean: faqInstance, field: "posOrder")}</td>

                        <td>${fieldValue(bean: faqInstance, field: "question")}

                            <br/>

                            ${fieldValue(bean: faqInstance, field: "answer")}</td>



                        %{--<td>${fieldValue(bean: faqInstance.languageType, field: "name")}</td>--}%
                        <td>${message(code: faqInstance?.languageType?.i18Name)}</td>

                        <td>
                            <g:render template="menu" model="[faqInstance: faqInstance]"/>

                        </td>

                    </tr>
                </g:each>

            </g:else>

            </tbody>
        </table>

        <g:paginateFoundation total="${faqInstanceTotal}"/>

    </div>
</div>
</body>
</html>
