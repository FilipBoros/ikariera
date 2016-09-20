<!doctype html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br>

    <div class="large-12 columns">

        <h1><g:message code="company.companyProfile.label" /> </h1>

    </div>
    <hr>
</div>

<div class="row">

    <div class="large-7 columns">

        <g:form method="post" action="update">

            <g:hiddenField name="version" value="${companyInstance?.version}"/>
            <g:hiddenField name="id" value="${companyInstance?.id}"/>


            <g:render template="/companyAccountProfile/form"/>



            <button action="update" class="button">${message(code: 'company.update.label')}</button>

        </g:form>

    </div>

    <div class="large-5 columns">
        <div class="panel">
            <h4><g:message code="company.companyProfiles.label" /></h4>
            <hr>

            <p><g:message code="company.companyProfiles.information" /> </p>




            <g:link class="button expand success" target="_blank" mapping="${message(code: 'companies.link')}" id="${companyInstance?.id}">
                <g:message code="company.show.profile"/>

            </g:link>


            <br />


            <br />



            <h5>${message(code: 'company.logo.label')}</h5>

            <hr>


            <div class="row">

                <div class="large-12 columns">

                    <p>${message(code: 'company.logo.size')}</p>



                </div>

                <div class="large-12 columns">
                    <g:if test="${companyInstance?.logo}">

                        <p>
                            <img src="${createLink(absolute: true, controller: 'media', action: 'getCompanyLogo', id: companyInstance?.logo)}" style="  display: block;
                            margin-left: auto;
                            margin-right: auto"/>
                        </p>


                        <div class="clearfix">

                        <g:link controller="companyAccountLogo" action="delete" id="${companyInstance.logo}" class="button alert tiny right"
                                onclick="return confirm('${message(code: 'student.cv.deleteSure')}');">
                            <g:message code="company.image.delete"/></g:link>

                        </div>

                    </g:if>
                    <g:else>


                        <g:uploadForm action="upload" controller="companyAccountLogo" enctype="multipart/form-data">
                            <input id="companyLogoPath" type="file" name="myFile"/>
                            <div class="clearfix">
                                <input id="companyLogoUpload" type="submit" class="button tiny right" value="${message(code: 'company.image.upload')}"/>
                            </div>
                        </g:uploadForm>

                    </g:else>
                </div>

            </div>




           <br />


            <h5>${message(code: 'company.hero.image')}</h5>

            <hr>


            <div class="row">

                <div class="large-12 columns">

                    <p>${message(code: 'company.hero.size')}</p>



                </div>

                <div class="large-12 columns">
                    <g:if test="${companyInstance?.mainLogo}">

                        <p>
                            <img src="${createLink(absolute: true, controller: 'media', action: 'getCompanyLogoMain', id: companyInstance?.mainLogo)}"/>
                        </p>


                        <div class="clearfix">
                        <g:link controller="companyAccountLogo" action="deleteMain" id="${companyInstance.mainLogo}"
                                class="button alert tiny right"
                                onclick="return confirm('${message(code: 'student.cv.deleteSure')}');">
                            <g:message code="company.image.delete"/></g:link>
                        </div>
                    </g:if>
                    <g:else>

                        <g:uploadForm action="uploadMain" controller="companyAccountLogo" enctype="multipart/form-data">
                            <input id="heroPicturePath" type="file" name="myFile"/>
                            <div class="clearfix">
                            <input id="heroPictureUpload" type="submit" class="button tiny right" value="${message(code: 'company.image.upload')}"/>
                            </div>
                        </g:uploadForm>

                    </g:else>
                </div>
            </div>



        </div>
    </div>
</div>
</body>
</html>