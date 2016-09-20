<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="company.gallery"/></h2>

    </div>

    <hr>

</div>

<div class="row">

    <div class="large-12 columns">

        <p><g:message code="company.gallery.description"/></p>

        <g:uploadForm action="upload" enctype="multipart/form-data">
            <input type="file" name="myFile"/>
            <input type="submit" class="button large" value="${message(code: 'student.cv.upload')}"/>
        </g:uploadForm>

    </div>

</div>

<div class="row">
    <div class="large-12 columns">

        <ul class="small-block-grid-4" data-clearing>
            <g:each var="galleryPicture" in="${userInstance?.company?.galleryPictures}">

                <li>

                    <a href="${createLink(absolute: true, controller: 'media', action: 'getCompanyGalleryImage', id: galleryPicture?.id)}"
                       class="th" style="height: 150px; overflow: hidden">
                        <img src="${createLink(absolute: true, controller: 'media', action: 'getCompanyGalleryImage', id: galleryPicture?.id)}"/>
                    </a>

                    <br>
                    <g:link controller="companyGallery" action="delete" id="${galleryPicture?.id}"
                            class="button alert tiny"
                            onclick="return confirm('${message(code: 'student.cv.deleteSure')}');">
                        <g:message code="student.cv.delete"/>
                    </g:link>

                </li>

            </g:each>

        </ul>
    </div>
</div>

</body>
</html>