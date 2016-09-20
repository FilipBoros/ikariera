<%@ page import="cz.ikariera.admin.PictureMedia" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>
<body>

<div class="row">
    <div class="large-12 columns" >



        <h1>Obrázky a loga: ${filter}</h1>




        <nav class="top-bar" data-topbar>
            <ul class="title-area">
                <li class="name"> <h1><a href="#"><g:message code="cz.ikariera.list.label" default= "List" /></a></h1> </li>
            </ul>
            <section class="top-bar-section">
                <!-- Right Nav Section -->
                <ul class="right">
                    <li class="has-form"> <g:link class="create button" controller="adminPictureMedia"><g:message code="cz.ikariera.create.label" default="Create" /></g:link>   </li>
                </ul>
            </section>
        </nav>







        <table style="width: 100%" class="selectable-row">
            <thead>
            <tr>

                <g:sortableColumn property="name" title="${message(code: 'pictureMedia.name.label')}" />

                <g:sortableColumn property="mediaType" title="${message(code: 'pictureMedia.mediaType.label')}" />


                <g:sortableColumn property="position" title="${message(code: 'pictureMedia.position.label')}" />



                <g:sortableColumn property="fileSize" title="${message(code: 'pictureMedia.fileSize.label')}" />



                <g:sortableColumn property="thumbnailFilename" title="${message(code: 'pictureMedia.thumbnailFilename.label')}" />

            </tr>
            </thead>
            <tbody>
            <g:each in="${pictureMediaInstanceList}" status="i" var="pictureMediaInstance">
                <tr link="${createLink(action: 'show', id: pictureMediaInstance.id)}" >

                    <td>${fieldValue(bean: pictureMediaInstance, field: "name")}</td>

                    <td>${fieldValue(bean: pictureMediaInstance, field: "mediaType")}</td>

                    <td>${fieldValue(bean: pictureMediaInstance, field: "position")}</td>

                    <td>${fieldValue(bean: pictureMediaInstance, field: "fileSize")}</td>


                    <td><img  src="${createLink(controller: 'media', action: 'getMedia', id: pictureMediaInstance?.thumbnailFilename)}" /> </td>

                </tr>
            </g:each>
            </tbody>
        </table>


        <ul class="pagination">
            <g:paginateFoundation total="${pictureMediaInstanceCount ?: 0}" />
        </ul>
    </div>

</div>


</body>
</html>
