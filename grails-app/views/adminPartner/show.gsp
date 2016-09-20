<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'partner.label', default: 'Partner')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div class="row">
    <div class="large-12 columns">




        <h1><g:message code="admin.partners.label" /> </h1>



        <dl>

            <g:if test="${partnerInstance?.name}">
                <dt>
                    <span id="name-label" class="property-label"><g:message code="partner.name.label"
                                                                            default="Name"/></span>

                </dt>
                <dd>

                    <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${partnerInstance}"
                                                                                            field="name"/></span>

                </dd>
            </g:if>

            <g:if test="${partnerInstance?.partnerType}">
                <dt>
                    <span id="partnerType-label" class="property-label"><g:message code="partner.partnerType.label"
                                                                                   default="Partner Type"/></span>

                </dt>
                <dd>

                    <span class="property-value" aria-labelledby="partnerType-label"><g:fieldValue
                            bean="${partnerInstance}" field="partnerType"/></span>

                </dd>
            </g:if>

            <g:if test="${partnerInstance?.logo}">
                <dt>
                    <span id="logo-label" class="property-label"><g:message code="partner.logo.label"
                                                                            default="Logo"/></span>

                </dt>
                <dd>

                    <img
                            src="${createLink(controller: 'adminPictureMedia', action: 'thumbnail', id: partnerInstance?.logo?.id)}"/>

                </dd>
            </g:if>

            <g:if test="${partnerInstance?.link}">
                <dt>
                    <span id="link-label" class="property-label"><g:message code="partner.link.label"
                                                                            default="Link"/></span>

                </dt>
                <dd>

                    <g:link url="${partnerInstance?.link}">${partnerInstance?.link}</g:link>

                </dd>
            </g:if>

        </dl>


        <g:form controller="adminPartner" action="delete" id="${partnerInstance.id}" novalidate="novalidate"
                method="DELETE">

            <g:actionSubmit class="button delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>

        </g:form>


        <g:link class="button edit" action="edit" controller="adminPartner"
                id="${partnerInstance.id}"><g:message code="default.button.edit.label"
                                                      default="Edit"/></g:link>

    </div>
</div>

</body>
</html>
