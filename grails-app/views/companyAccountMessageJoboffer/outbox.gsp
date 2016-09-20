<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h1>

            <g:message code="company.message.outbox"/>

        </h1>

    </div>

    <hr>

</div>


<div class="row">

    <div class="large-3 columns">

        <ul class="side-nav">
            <li><g:link action="index" ><g:message code="company.message.inbox"/></g:link></li>
            <li><g:link action="outbox" class="active"><g:message code="company.message.outbox"/></g:link></li>

        </ul>
    </div>

    <div class="large-9 columns">
            <table class="table">
                <thead>
                <tr>
                    <g:sortableColumn property="studentName"
                                      title="${message(code: 'companyMessage.studentName.label', default: 'Student Name')}"/>

                    <g:sortableColumn property="subject"
                                      title="${message(code: 'companyMessage.subject.label', default: 'Subject')}"/>

                </tr>
                </thead>
                <tbody>
                <g:each in="${companyMessageInstanceList}" status="i" var="companyMessageInstance">
                    <tr>

                        <td><g:link action="show" id="${companyMessageInstance.id}">
                            <g:if test="${companyMessageInstance.recipient.studentAccount}">
                                ${fieldValue(bean: companyMessageInstance, field: "recipient.firstName")}
                            </g:if>
                            <g:else>
                                ${fieldValue(bean: companyMessageInstance, field: "sender.firstName")}
                            </g:else>
                        </g:link></td>

                        <td>${fieldValue(bean: companyMessageInstance, field: "subject")}
                        <g:if test="${companyMessageInstance?.getUnreadCount(false) != 0}">
                            (${companyMessageInstance?.getUnreadCount(false)})
                        </g:if>
                        </td>

                    </tr>
                </g:each>
                </tbody>
            </table>

            <g:paginateFoundation total="${companyMessageInstanceCount ?: 0}"/>

    </div>
</div>
</body>
</html>
