<!doctype html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br>

    <div class="large-9 columns">

        <h1>
            ${message(code: 'company.email.list.label')}
        </h1>

    </div>

    <div class="large-3 columns">

        <br>
        <g:link controller="companyAccountEmail" action="create" class="button right"><g:message
                code="company.email.createEmail.label"/></g:link>

    </div>



</div>


<div class="row">

    <div class="large-12 columns">

        <hr>





        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="subject" title="${message(code: 'companyEmails.subject.label')}"/>

           %{--     <g:sortableColumn property="dateCreated" title="${message(code: 'companyEmails.dateCreated.label')}"/>--}%

                <g:sortableColumn property="dateSent" title="${message(code: 'companyEmails.dateSent.label')}"/>




                <th><div style="text-align: center">${message(code: 'admin.attachment.label')}</div></th>
                <th>${message(code: 'companyEmails.state.label')}</th>

                <g:sortableColumn property="creditCost" title="${message(code: 'companyEmails.creditCost.label')}"/>

                <th>${message(code: 'companyEmails.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!companyEmailsInstanceList}">
                <tr>
                    <td colspan="5">${message(code: 'companyEmails.noData.label')}</td>
                </tr>

            </g:if>
            <g:else>
                <g:each in="${companyEmailsInstanceList}" status="i" var="companyEmailsInstance">

                    <tr>

                        <td><g:link action="display"
                                    id="${companyEmailsInstance.id}">${fieldValue(bean: companyEmailsInstance, field: "subject")}</g:link></td>

    %{--                    <td><g:formatDate date="${companyEmailsInstance.dateCreated}"/></td>--}%

                        <td>

                    <g:if test="${companyEmailsInstance.dateSent != null}">
                        <g:formatDate  type="date" style="MEDIUM" date="${companyEmailsInstance.dateSent}"/>
                    </g:if>
                    <g:else>
                        <span style="color: gray">X</span>
                    </g:else>

                    </td>





                        <g:if test="${companyEmailsInstance.attachment != null}">
                            <td><div style="text-align: center">${message(code: 'admin.attachment.yes')}</div></td>
                        </g:if>
                        <g:else>
                            <td><div style="text-align: center">${message(code: 'admin.attachment.no')}</div></td>
                        </g:else>

                        <td>
                        <g:if test="${companyEmailsInstance.confirmed}">
                            ${message(code: 'companyEmails.confirmed.label')}
                        </g:if>
                        <g:elseif test="${!companyEmailsInstance.dateSent}">

                            <g:link   mapping="companyAccountEmailSearch"  params="[emailId: companyEmailsInstance.id]" class="button success round">
                                ${message(code: 'companyEmails.sentEmail.label')}

                            </g:link>
                        </g:elseif>
                        <g:else>
                            ${message(code: 'companyEmails.waiting.label')}
                        </g:else>
                        </td>


                        <td>
                            <g:if test="${companyEmailsInstance.creditCost.equals(null)}">
                                <span style="color: gray">X</span>
                            </g:if>
                            <g:else>
                                ${companyEmailsInstance.creditCost}
                            </g:else>
                        </td>

                        <td>

                            <g:render template="dropDownMenu" model="[companyEmailsInstance: companyEmailsInstance]"/>



                        </td>
                    </tr>
                </g:each>



            </g:else>

            </tbody>
        </table>

        <g:paginateFoundation total="${companyEmailsInstanceListTotal}"/>

    </div>
</div>

</body>
</html>