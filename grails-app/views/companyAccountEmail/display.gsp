<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-10 columns">

        <h2><g:message code="companyEmails.show.created"/></h2>

    </div>

    <div class="large-2 columns">
        <g:link controller="companyAccountEmail" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
    </div>
    <hr>
</div>

<div class="row">

    <div class="large-12 columns">
        <h4><g:message code='company.email.subject.label'/></h4>

        <div class="span12" style="border: 1px solid #d3d3d3; padding: 0.5em; margin: 0.5em; -moz-border-radius: 10px;
        border-radius: 10px;">
            ${companyEmailsInstance?.subject}
        </div>
        <br/>
        <h4><g:message code='company.email.message.label'/></h4>

        <div class="span12" style="border: 1px solid #d3d3d3; padding: 0.5em; margin: 0.5em; -moz-border-radius: 10px;
        border-radius: 10px;">
            ${raw(companyEmailsInstance?.message)}
        </div>
        <br/>

        <g:if test="${companyEmailsInstance?.attachment != null}">
            <h4>
                <g:message code="company.email.attachement.label"/>
            </h4>

            <g:link target="_blank" action="getAttachedMedia" controller="media"
                    params="[path: companyEmailsInstance.attachment, name: companyEmailsInstance.originalName]">
                ${companyEmailsInstance?.originalName}

            </g:link>
            <br/><br/><br/>
        </g:if>

    </div>

</div>

<script type="text/javascript">
    window.onload = function WindowLoad(event) {
        CKEDITOR.instances.message.setReadOnly(true);
    }
</script>
</body>
</html>