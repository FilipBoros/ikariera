<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">


        <h2><g:message code="admin.h2.detailCreated"/></h2>
        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:link class="btn btn-primary btn-lg active" role="button"><g:message code="admin.emailBack.label"/></g:link>









        %{--<iframe width="965" frameborder="0" height="400"
                src="${createLink(controller: "adminEmail", action: "showEmail", id: adminEmailsInstance?.id)}"></iframe>--}%




        <h4><g:message code='company.email.subject.label'/></h4>

        <div class="span12" style="border: 1px solid #d3d3d3; padding: 0.5em; margin: 0.5em; -moz-border-radius: 10px;
        border-radius: 10px;">
            ${adminEmailsInstance?.subject}
        </div>
        <br/>
        <h4><g:message code='company.email.message.label'/></h4>

        <div class="span12" style="border: 1px solid #d3d3d3; padding: 0.5em; margin: 0.5em; -moz-border-radius: 10px;
        border-radius: 10px;">
            ${raw(adminEmailsInstance?.message)}
        </div>
        <br/>

        <g:if test="${adminEmailsInstance?.attachment != null}">
            <h4>
                <g:message code="company.email.attachement.label"/>
            </h4>

            <g:link target="_blank" action="getAttachedMedia" controller="media"
                    params="[path: adminEmailsInstance.attachment, name: adminEmailsInstance.originalName]">
                ${adminEmailsInstance?.originalName}

            </g:link>
            <br/><br/><br/>
        </g:if>




    </div>

</div>

</body>
</html>