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

            <g:message code="company.message.inbox"/>

        </h1>

    </div>

</div>


<div class="row">

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
                <td colspan="8">${message(code: 'message.noEntries.label')}</td>
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
                            ${companyMessageInstance?.sender?.firstName} ${companyMessageInstance?.sender?.lastName}
                        </g:else>

                    </td>

                    <td>

                        <g:if test="${companyMessageInstance?.getUnreadCountRecipient(false) != 0 &&
                                companyMessageInstance?.company == applicationContext.springSecurityService?.currentUser?.company}">

                            <g:if test="${companyMessageInstance?.messages?.size() > 0}">
                                <strong>
                                    ${fieldValue(bean: companyMessageInstance, field: "subject")}
                                    (${companyMessageInstance?.getUnreadCountRecipient(false)})
                                </strong>
                            </g:if>

                        </g:if>
                        <g:elseif
                                test="${companyMessageInstance?.getUnreadCountSender(false) != 0 &&
                                        companyMessageInstance?.sender?.company == applicationContext.springSecurityService.currentUser?.company}">

                            <g:if test="${companyMessageInstance?.messages?.size() > 0}">
                                <strong>
                                    ${fieldValue(bean: companyMessageInstance, field: "subject")}
                                    (${companyMessageInstance?.getUnreadCountSender(false)})
                                </strong>
                            </g:if>

                        </g:elseif>
                        <g:else>
                            <g:if test="${companyMessageInstance?.messages?.size() > 0}">
                                ${fieldValue(bean: companyMessageInstance, field: "subject")}
                            </g:if>
                        </g:else>

                        <g:if test="${companyMessageInstance.jobOffer}">
                            <br/>

                            <g:link controller="jobOffer" action="detail"
                                    params="[id: companyMessageInstance.jobOffer.id]">
                                ${companyMessageInstance.jobOffer.positionName}
                            </g:link>
                        </g:if>
                    </td>
                    <td>
                        <g:link action="showIn" id="${companyMessageInstance.id}" class="secondary button round">
                            <g:message code="companyAccountUser.showJobOffer.label"/>
                        </g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

            <g:paginateFoundation total="${companyMessageInstanceCount ?: 0}"/>
        </g:else>

    </div>

</div>
</body>
</html>
