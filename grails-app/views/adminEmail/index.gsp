<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>


<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: 'admin.email.list.label')}</h2>

    </div>

    <div class="large-3 columns">
        <br/>
        <g:link class="button right" action="createDemoEmail" controller="adminEmail">
            <g:message code="admin.create.testing.email" /></g:link>
    </div>



</div>


<div class="row">
    <hr>
    <div class="large-12 columns">

        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="subject" title="${message(code: 'adminEmails.subject.label')}"/>

                <g:sortableColumn property="dateCreated" title="${message(code: 'adminEmails.dateCreated.label')}"/>

                <g:sortableColumn property="dateSent" title="${message(code: 'adminEmails.dateSent.label')}"/>

                <th>${message(code: 'admin.attachment.label')}</th>

                <th><g:message code="company.email.receiversNumber.label" /> </th>

                <th>${message(code: 'adminEmails.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!adminEmailsInstanceList}">
                <tr>
                    <td colspan="5">${message(code: 'companyEmails.noData.label')}</td>
                </tr>

            </g:if>
            <g:else>
                <g:each in="${adminEmailsInstanceList}" status="i" var="adminEmailsInstance">

                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:link action="displayEmail"
                                    id="${adminEmailsInstance.id}">${fieldValue(bean: adminEmailsInstance, field: "subject")}</g:link></td>

                        <td><g:formatDate date="${adminEmailsInstance.dateCreated}"/></td>

                        <td><g:formatDate date="${adminEmailsInstance.dateSent}"/></td>

                        <g:if test="${adminEmailsInstance.attachment != null}">
                            <td>${message(code: 'admin.attachment.yes')}</td>
                        </g:if>
                        <g:else>
                            <td>${message(code: 'admin.attachment.no')}</td>
                        </g:else>


                        <td>${adminEmailsInstance.numberRecipients}</td>

                        <td>



                                    <g:render template="/adminEmail/menu"
                                              model="[adminEmailsInstance: adminEmailsInstance, returnAddress: request.forwardURI,
                                                      returnPath: (request.forwardURI - request.contextPath)]"/>




                        </td>
                    </tr>
                </g:each>

            </g:else>

    </tbody>
    </table>

        <g:paginateFoundation total="${adminEmailsInstanceListCount}"/>
    </div>

</div>

</body>
</html>