<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2>

            <g:message code="admin.feedbacks"/>

        </h2>

        <hr>

    </div>



</div>


<div class="row">

    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>
                <th><g:message code="company.company.label"/> </th>
                <th><g:message code="about.subject"/> </th>
                <th><g:message code="company.email.dateCreated.label"/> </th>
                <th><g:message code="action"/> </th>
            </tr>
            </thead>
            <tbody>
<g:if test="${!feedBacklist}">
    <tr>
        <td colspan="10">${message(code: 'company.noData.label')}</td>
    </tr>

</g:if>
<g:else>

            <g:each in="${feedBacklist}" status="i" var="message">
                <tr >

                    <td>
                        ${message?.sender}
                    </td>

                    <td>${fieldValue(bean: message, field: "subject")}

                    </td>

                    <td>
                        ${message?.dateCreated}
                    </td>

                    <td>


                        <g:link  action="show"     id="${message.id}" class="button secondary small round right" >
                            <i class="fi-wrench"></i>     <g:message code="admin.catalogue.show.label" />
                        </g:link>


                    </td>

                </tr>
            </g:each>


    </g:else>

    </tbody>
    </table>

        <g:paginateFoundation total="${feedbackcount}"/>

    </div>

</div>

</body>
</html>
