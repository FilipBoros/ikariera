<%@ page import="cz.ikariera.admin.PictureMedia" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2><g:message code="admin.media.allImagesAndLogos"/></h2>

    </div>

    <div class="large-3 columns">
        <g:link class="button right" controller="adminPictureMedia" action="logo"><g:message
                code="admin.media.uploadLogo"/></g:link>

    </div>
</div>


<div class="row">
    <div class="large-12 columns">
        <hr>

        <ul class="medium-block-grid-7">

            <g:each in="${pictureMediaInstanceList}" status="i" var="pictureMediaInstance">

                <g:render template="picture" model="[pictureMediaInstance: pictureMediaInstance]"/>

            </g:each>
        </ul>


        <ul class="pagination">
            <g:paginateFoundation total="${pictureMediaInstanceCount ?: 0}"/>
        </ul>
    </div>

</div>

</body>
</html>
