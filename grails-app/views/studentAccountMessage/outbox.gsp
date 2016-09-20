<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="student">
</head>

<body>

<div class="row">

    <br>

    <div class="large-9">

        <h1><g:message code="company.messages.sent"/></h1>

    </div>





    <hr/>
</div>

<div class="row">

    <div class="large-12">

        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="date"
                                  title="Date"/>

                <g:sortableColumn property="company"
                                  title="Company"/>

                <g:sortableColumn property="subject" style="width: 60%"
                                  title="${message(code: 'companyMessage.subject.label', default: 'Subject')}"/>
                <th>
                    <g:message code="action"/>
                </th>

            </tr>
            </thead>
            <tbody>

            <g:if test="${!studentMessageInstanceList}">
                <tr>
                    <td colspan="8">${message(code: 'message.noEntries.label')}</td>
                </tr>

            </g:if>
            <g:else>

                <g:each in="${studentMessageInstanceList}" status="i" var="studentMessageInstance">
                    <tr>

                        <td>
                            <g:if test="${studentMessageInstance.messages.size() > 0}">
                                ${studentMessageInstance?.messages?.last()?.sendDate?.toLocaleString()}

                            </g:if>
                        </td>

                        <td>
                            <g:if test="${studentMessageInstance?.company}">
                                ${studentMessageInstance?.company?.companyName}
                            </g:if>

                        </td>


                        <td>
                            <g:if test="${studentMessageInstance?.messages?.size() > 0}">
                                ${raw(studentMessageInstance?.messages?.last()?.message?.substring(0, studentMessageInstance?.messages?.last()?.message?.length() < 255 ? studentMessageInstance?.messages?.last()?.message?.length() : 100))}
                            </g:if>
                        </td>


                        <td>
                            <g:link action="showOut" id="${studentMessageInstance.id}" class="button  secondary round">
                                <g:message code="companyAccountUser.showJobOffer.label"/>
                            </g:link>
                        </td>
                    </tr>
                </g:each>

            </g:else>

            </tbody>
        </table>

        <g:paginateFoundation total="${studentMessageInstanceListTotal}" params="${params}"
                              maxsteps="4"
                              next="${message(code: "list.paginate.next")}"
                              prev="${message(code: "list.paginate.prev")}"/>

    </div>
</div>
</body>
</html>
