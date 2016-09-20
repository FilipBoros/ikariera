<%@ page import="cz.ikariera.admin.PictureMedia" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="administrator">

    <r:require modules="jqueryFileUpload, jqueryFileUploadStyle"/>

</head>

<body>

<style>

table.table-striped tbody tr:hover {
    background: transparent;
}

</style>

<div class="row">


    <div class="large-12 columns">

        <br />

        <h1><g:message code="cz.ikariera.picture.media.upload.new"/> <g:message code="${mediaType}"/></h1>

        <div class="panel">
            <p>

                Aplikace není schopná zmenšit obrzáky větší než 1000px na 1000px. Obří fotografie prosím nejdříve zmenšete.           <br/>

                Aplikace má z zatim neznámých příčin problém s některými specifickými znaky v názvech. Jedná so hlavně o pomlčky "-".    <br/>

                Minimální výška nebo šířka pro logo je 130pxx130px   <br/>

                Rozměry hero banneru jsou (š x v) 970 x 236 px. Lze používat i užší nebo jinak široké banery. Je jen potřeba dodržet u všech obrázků pro hero baner stejnou velikost.      <br/>

                Aplikace neumí pracovat s obrázky, které mají průhledné pozadí. Nahrávejte obrázky v barvě pozadí.

            </p>
        </div>

    </div>
</div>
    <div class="row">


        <div class="large-12 columns">







        <br>
    <!-- The file upload form used as target for the file upload widget -->


        <g:form name="fileupload" action="upload" controller="adminPictureMedia" absolute="true" method="POST"
                enctype="multipart/form-data">

            <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
            <div class="fileupload-buttonbar">

                <!-- The fileinput-button span is used to style the file input field as button -->
                <div class="button btn-success fileinput-button"
                     style="position: relative; overflow: hidden; margin-right: 5px; float: left; cursor: pointer;">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple style="cursor: pointer">
                </div>
                <button type="submit" class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel upload</span>
                </button>

                <g:hiddenField name="mediaType" value="${mediaType}"/>

            </div>


            <!-- The global progress state -->
            <div class="col-lg-5 fileupload-progress fade">
                <!-- The global progress bar -->
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>

            <!-- The table listing the files available for upload/download -->
            <table role="presentation" class="table table-striped" style="width: 100%"><tbody class="files"></tbody>
            </table>
        </g:form>

        <br>

    </div>


    <script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
    <!-- The Load Image plugin is included for the preview images and image resizing functionality -->
    <script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.min.js"></script>
    <!-- The Canvas to Blob plugin is included for image resizing functionality -->
    <script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>

    <script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>


    <!-- The template to display files available for upload -->
    <script id="template-upload" type="text/x-tmpl">
        {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-upload fade">
            <td>
                <span class="preview"></span>
            </td>
            <td>
                <p class="name">{%=file.name%}</p>
                <strong class="error text-danger"></strong>
            </td>
            <td>
                <p class="size">Processing...</p>

                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100"
                     aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td>
                {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start</span>
                </button>
                {% } %}
                {% if (!i) { %}
                %{--                            <button class="btn btn-warning cancel">
                                                <i class="glyphicon glyphicon-ban-circle"></i>
                                                <span>Cancel</span>
                                            </button>--}%
                {% } %}
            </td>
        </tr>
        {% } %}
    </script>
    <!-- The template to display files available for download -->
    <script id="template-download" type="text/x-tmpl">
        {% for (var i=0, file; file=o.files[i]; i++) { %}
        <tr class="template-download fade">
            <td>
                <span class="preview">
                    {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img
                            src="{%=file.thumbnailUrl%}"></a>
                    {% } %}
                </span>
            </td>
            <td>
                <p class="name">
                    {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}"
                       download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                    {% } else { %}
                    <span>{%=file.name%}</span>
                    {% } %}
                </p>
                {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                {% } %}
            </td>
            <td>
                <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td>
                {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}"
                        data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                <i class="glyphicon glyphicon-trash"></i>
                <span>Delete l</span>
            </button>

                {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
                {% } %}
            </td>
        </tr>
        {% } %}
    </script>




</div>

</body>
</html>
