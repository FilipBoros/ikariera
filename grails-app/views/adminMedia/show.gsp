<%@ page import="cz.ikariera.admin.PictureMedia" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">
    <div class="large-12 columns">

        <h1><g:message code="adminMedia.show.label"/></h1>





        <dl>

            <g:if test="${pictureMediaInstance?.name}">
                <dt>
                    <span id="name-label" class="property-label"><g:message code="pictureMedia.name.label"
                                                                            default="Name"/></span>

                </dt>
                <dd>

                    <span class="property-value" aria-labelledby="name-label"><g:fieldValue
                            bean="${pictureMediaInstance}" field="name"/></span>

                </dd>
            </g:if>

            <g:if test="${pictureMediaInstance?.mediaType}">
                <dt>
                    <span id="mediaType-label" class="property-label"><g:message code="pictureMedia.mediaType.label"
                                                                                 default="Media Type"/></span>

                </dt>
                <dd>

                    <span class="property-value" aria-labelledby="mediaType-label"><g:fieldValue
                            bean="${pictureMediaInstance}" field="mediaType"/></span>

                </dd>
            </g:if>

            <g:if test="${pictureMediaInstance?.fileSize}">
                <dt>
                    <span id="fileSize-label" class="property-label"><g:message code="pictureMedia.fileSize.label"
                                                                                default="File Size"/></span>

                </dt>
                <dd>

                    <span class="property-value" aria-labelledby="fileSize-label"><g:fieldValue
                            bean="${pictureMediaInstance}" field="fileSize"/></span>

                </dd>
            </g:if>



            <g:if test="${pictureMediaInstance?.newFilename}">
                <dt>
                    <span id="newFilename-label" class="property-label"><g:message code="pictureMedia.newFilename.label"
                                                                                   default="New Filename"/></span>

                </dt>
                <dd>

                    <g:link url="${createLink(controller: 'media', action: 'getMedia', id: pictureMediaInstance?.newFilename)}">Odkaz na obrázek</g:link>

                </dd>
            </g:if>

            <g:if test="${pictureMediaInstance?.newFilename}">
                <dt>
                    <span id="originalFilename-label" class="property-label"><g:message
                            code="pictureMedia.originalFilename.label" default="Original Filename"/></span>

                </dt>
                <dd>


                    <g:render template="mediaTypePicture" model="[mediaType: pictureMediaInstance?.mediaType , filename:pictureMediaInstance?.newFilename ]"   />



                </dd>
            </g:if>

            <g:if test="${pictureMediaInstance?.thumbnailFilename}">
                <dt>
                    <span id="thumbnailFilename-label" class="property-label"><g:message
                            code="pictureMedia.thumbnailFilename.label" default="Thumbnail Filename"/></span>

                </dt>
                <dd>

                    <g:render template="mediaTypePicture" model="[mediaType: pictureMediaInstance?.mediaType , filename:pictureMediaInstance?.newFilename ]"   />



                </dd>
            </g:if>

        </dl>

    </div>
</div>

</body>
</html>
