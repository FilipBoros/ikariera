<%@ page import="cz.ikariera.admin.PictureMedia" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>

<div class="row">
    <div class="large-12 columns">

        <h1>Obrázky</h1>




        <nav class="top-bar" data-topbar>
            <ul class="title-area">
                <li class="name"><h1><a href="#"><g:message code="cz.ikariera.list.label" default="List"/></a></h1></li>
            </ul>
            <section class="top-bar-section">
                <!-- Right Nav Section -->
                <ul class="right">
                    <li class="has-form"><g:link class="create button" controller="adminPictureMedia" action="uploadPicture">

                        Nahrát nový obrázek

                    </g:link></li>
                </ul>
            </section>
        </nav>





        <ul class="small-block-grid-8">






                   <g:each in="${pictureMediaInstanceList}" status="i" var="pictureMediaInstance">


                                    <g:render template="picture" model="[pictureMediaInstance : pictureMediaInstance]" />


            </g:each>
        </ul>


        <ul class="pagination">
            <g:paginateFoundation total="${pictureMediaInstanceCount ?: 0}"/>
        </ul>
    </div>

</div>

</body>
</html>
