<!doctype html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2>${message(code: 'student.uploadCv.label')}</h2>

    </div>

    <hr>

</div>

<div class="row">

    <div class="large-6 columns">

        <p>${message(code: 'student.cv.info.text')}</p>

        <g:uploadForm action="upload" enctype="multipart/form-data">
            <input type="file" name="myFile"/>
            <input type="submit" class="button large" value="${message(code: 'student.cv.upload')}"/>
        </g:uploadForm>

    </div>

    <div class="large-6 columns">
        <g:if test="${userInstance?.studentAccount?.cv}">
            <div class="panel">

                <div class="row">
                    <div class="large-3 columns">

                        <g:link controller="studentAccountCv" action="getStudentCv"
                                params="[id: userInstance?.studentAccount?.cv?.newFilename]" target="_blank">

                            <i class="fi-page-pdf" style="font-size: 100px"></i>
                        </g:link>


                        <br><br>
                        <g:link controller="studentAccountCv" action="delete" id="${userInstance.id}" class="button alert tiny"
                                onclick="return confirm('${message(code: 'student.cv.deleteSure')}');">
                            <g:message code="student.cv.delete"/></g:link>

                    </div>

                    <div class="large-9 columns">
                        <ul class="no-bullet">

                            <li>${message(code: 'student.uploadedCv.name.label')}</li>

                            <li><g:link controller="studentAccountCv" action="getStudentCv"
                                        params="[id: userInstance?.studentAccount?.cv?.newFilename]" target="_blank">

                                ${userInstance?.studentAccount?.cv?.name}
                            </g:link></li>

                            <li>${message(code: 'student.uploadedCv.dateInsert.label')}

                                <g:formatDate format="dd.MM.yyyy"
                                              date="${userInstance?.studentAccount?.cv?.date}"/></li>

                        </ul>

                    </div>
                </div>

            </div>
        </g:if>
    </div>

</div>
</body>
</html>