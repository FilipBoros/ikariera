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

            <g:message code="company.dialogs.outbox"/>

        </h1>

    </div>

    %{--    <hr>--}%

</div>


<div class="row">

    %{--    <div class="large-3 columns">

            <ul class="side-nav">
                <li><g:link action="index" ><g:message code="company.message.inbox"/></g:link></li>
                <li><g:link action="outbox" class="active"><g:message code="company.message.outbox"/></g:link></li>

            </ul>
        </div>--}%

    <div class="large-12 columns">
    <table class="table">
        <thead>
        <tr>
            <g:sortableColumn property="date"
                              title="Date"/>

            <g:sortableColumn property="recipient"
                              title="${message(code: 'companyMessage.studentName.label', default: 'Student Name')}"/>

            <g:sortableColumn property="subject"
                              title="${message(code: 'companyMessage.subject.label', default: 'Subject')}"/>
            <th>
                <g:message code="action"/>
            </th>

        </tr>
        </thead>
    <tbody>
        <g:if test="${!companyMessageInstanceList}">
            <tr>
                <td colspan="4">${message(code: 'message.noEntries.label')}</td>
            </tr>

            </tbody>
            </table>
        </g:if>
        <g:else>

            <g:each in="${companyMessageInstanceList}" status="i" var="companyMessageInstance">
                <tr>

                    <td>
                        <g:if test="${companyMessageInstance.messages.size() > 0}">
                            ${companyMessageInstance?.messages?.last()?.sendDate.toLocaleString()}
                        </g:if>
                    </td>

                    <td>
                        <g:if test="${companyMessageInstance?.sender?.company != null}">
                            ${fieldValue(bean: companyMessageInstance, field: "recipient.firstName")} ${fieldValue(bean: companyMessageInstance, field: "recipient.lastName")}
                        </g:if>
                        <g:else>
                            ${companyMessageInstance.sender.firstName} ${companyMessageInstance.sender.lastName}
                        </g:else>

                    </td>

                    <td>

                        <g:if test="${companyMessageInstance?.getUnreadCountSender(false) != 0}"><strong>
                            ${fieldValue(bean: companyMessageInstance, field: "subject")}
                            <g:if test="${companyMessageInstance?.getUnreadCountSender(false) != 0}">
                                (${companyMessageInstance?.getUnreadCountSender(false)})
                            </g:if></strong>
                        </g:if>
                        <g:else>
                            ${fieldValue(bean: companyMessageInstance, field: "subject")}
                        </g:else>
                    </td>
                    <td>
                        <g:link action="showOut" id="${companyMessageInstance.id}" class="secondary button round">
                            <g:message code="companyAccountUser.showJobOffer.label"/>
                        </g:link>
                    </td>
                </tr>
            </g:each>

        </g:else>
    </tbody>
    </table>

        <g:paginateFoundation total="${companyMessageInstanceCount ?: 0}"/>

    </div>
</div>
</body>
</html>
