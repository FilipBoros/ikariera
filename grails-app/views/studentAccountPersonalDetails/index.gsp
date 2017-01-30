<!doctype html>
<html>
<head>
    <meta name="layout" content="student">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h2><g:message code="personalInfo.label"/></h2>

    </div>

    <hr>

</div>

<div class="row">

<div class="large-7 columns">

    <g:form action="save" id="updateProfileForm">

        <g:hiddenField name="userVersion" value="${userInstance?.version}"/>

        <g:hiddenField name="id" value="${userInstance?.id}"/>


        <g:render template="form" model="[userInstance: userInstance]"/>


        <g:render template="/studentAccountContact/form" model="[userInstance: userInstance]"/>


        <p>
            <g:message code="student.registration.infoEmails.label"/>

        </p>


        <div class="clearfix">

            <button name="create" class="save long ">${message(code: 'default.button.save.label')}</button>
        </div>
    </g:form>

</div>

<div class="large-5 columns">

    <div class="panel">

        <h4><g:message code="profile.photo"/></h4>

        <br>

        <div class="row">
            <div class="large-6 columns">

                <p>
                    <g:if test="${userInstance?.studentAccount?.photo}">
                        <img src="${createLink(controller: 'studentAccountPhoto', action: "getMedia", id: userInstance?.studentAccount?.photo?.newFilename, absolute: true)}"/>
                    </g:if>
                    <g:else>
                        <g:if test="${grailsApplication.config.language.equals('cz')}">
                            <asset:image src="ikariera/no-profile-image2.png"/>
                        </g:if>
                        <g:elseif test="${grailsApplication.config.language.equals('sk')}">
                            <asset:image src="ikariera_sk/no-profile-image2.png"/>
                        </g:elseif>

                        %{--<img src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>--}%
                    </g:else>
                </p>

                <p class="text-center">

                    <g:if test="${userInstance?.studentAccount?.photo}">

                        <g:link controller="studentAccountPhoto" action="delete" class="button alert tiny">
                            <g:message code="delete.photo"/>
                        </g:link>

                    </g:if>
                    <g:else>

                        <g:uploadForm controller="studentAccountPhoto" action="upload" name="photo">

                            <span class="button small success fileinput-button">
                                <i class="glyphicon glyphicon-plus"></i>
                                <span><g:message code="upload.photo"/></span>
                                <input id="fileupload" class="fileupload" type="file" name="file">
                            </span>

                        </g:uploadForm>

                        <script>

                            $(".fileupload").change(function () {
                                $("form[name=photo]").submit();

                            });


                        </script>

                    </g:else>
                </p>
            </div>


            <style>

            .fileinput-button {
                overflow: hidden;
                position: relative;
            }

            .fileinput-button input {
                cursor: pointer;
                direction: ltr;
                font-size: 200px;
                margin: 0;
                opacity: 0;
                position: absolute;
                right: 0;
                top: 0;
            }

            .fileinput-button input:file {
                position: fixed;
                top: -1000
            }

            </style>


            <div class="large-6 columns">

                <p>
                    <g:message code="profile.photo.information"/>

                </p>

                <p>
                    <g:message code="profile.photo.size.information"/>
                </p>
            </div>

        </div>

        <hr>



        <h4><g:message code="student.uploadCv.label"/></h4>


        <p>${message(code: 'student.cv.info.text')}</p>






        <g:if test="${userInstance?.studentAccount?.cv}">

            <div class="row">
                <div class="large-3 columns">

                    <g:link controller="studentAccountCv" action="getStudentCv"
                            params="[id: userInstance?.studentAccount?.cv?.newFilename]" target="_blank">

                        <i class="fi-page-pdf" style="font-size: 100px"></i>
                    </g:link>


                    <br><br>
                    <g:link controller="studentAccountCv" action="delete" id="${userInstance.id}"
                            class="button alert tiny"
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

        </g:if>
        <g:else>

            <g:uploadForm action="upload" controller="studentAccountCv" enctype="multipart/form-data">
                <input id="curriculumVitaePath" type="file" name="myFile"/>
                <div class=" clearfix">
                    <input id="curriculumVitaeUpload" type="submit" class="button small success right"
                           value="${message(code: 'student.cv.upload')}"/>
                </div>
            </g:uploadForm>

        </g:else>

        %{--Michal Dolnak--}%

        <g:form action="index" controller="studentAccountGenerateCv">
            <input type="submit" class="btn btn-danger" value="Generate pdf">
        </g:form>


    %{-- <hr>

     <h4><g:message code="user.account.label"/></h4>

     <p>
         <g:link controller="studentAccountPassword">
             <g:message code="user.changePassword.label"/>
         </g:link>

     </p>




     <hr>

     <g:render template="/studentAccountDangerArea/dangerArea"/>--}%

    </div>

</div>
</div>
</body>
</html>