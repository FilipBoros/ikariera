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

            <g:message code="company.message.inbox.jobOfferReplies"/>

        </h1>

    </div>

</div>


<div class="row">




    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>
                %{--            <th>
                                <g:message code="companyMessage.studentName.label"/>
                            </th>
                            <th>
                                <g:message code="detail.jobOffer.Type.JobOffer"/>
                            </th>--}%
                <th>
                    ${message(code: 'student.jobOffer.label')}
                </th>
                <th>
                    <g:message code="action"/>
                </th>
            </tr>
            </thead>

            <tbody>
            <g:each in="${feedBackList}" status="i" var="companyMessageInstance">

                <tr>
                    <g:if test="${!companyMessageInstance.isRead}">
                        <td style="font-weight: bold;">
                        ${companyMessageInstance?.dateCreated.toLocaleString()}
                        <br/>
                        ${companyMessageInstance.studentName + " " + companyMessageInstance.studentLastName}*
                    %{--</td>--}%
                    </g:if>
                    <g:else>
                        <td>

                            ${companyMessageInstance?.dateCreated.toLocaleString()}
                            <br/>
                        ${companyMessageInstance.studentName + " " + companyMessageInstance.studentLastName}
                    %{--</td>--}%
                    </g:else>

                    %{--<td>--}%
                    <br/>
                    <g:link controller="jobOffer" action="detail" params="[id: companyMessageInstance.jobOffer.id]">
                        ${companyMessageInstance.jobOffer.positionName}
                    </g:link>
                </td>
                    <td>
                        <g:link action="detail" id="${companyMessageInstance.id}" class="button">
                            <g:message code="companyAccountUser.showJobOffer.label"/>
                        </g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <g:paginateFoundation total="${feedBackCount ?: 0}"/>

    </div>

</div>
</body>
</html>
