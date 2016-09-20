<%@ page import="cz.ikariera.company.Company" %>

<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

    <title>
        <g:message code="title.company.detail" args="${[companyInstance?.companyName ?: ""]}"/>
    </title>

    <meta property="og:title"
          content="${message(code: 'title.company.detail', args: [companyInstance?.companyName ?: ""])}"/>

    <meta property="og:site_name" content="iKariera.cz |  ${companyInstance?.companyName}"/>

    <meta property="og:url" content="${'http://www.ikariera.cz' + request.forwardURI}"/>

    <meta property="og:description" lang="cz" content="${companyInstance?.companyCharacteristic ?: ""}"/>




</head>

<body>
<g:if test="${companyInstance?.mainLogo}">
    <div class="row">

        <!-- hero image -->



        <div class="large-12 columns">
            <p>
                <img  src="${createLink(absolute: true, controller: 'media', action: 'getCompanyLogoMain', id: companyInstance?.mainLogo)}"/>
            </p>
            %{--<img src="${resource(dir: grailsApplication.config.upload.directory.companyLogo, file: companyInstance?.logo, absolute: true)}"/>--}%
        </div>

    </div>

</g:if>

<br/><br/>


<div class="row">
    <g:if test="${companyInstance?.logo}">
        <div class="large-3 columns" >

            <p>
                <img  src="${createLink(absolute: true, controller: 'media', action: 'getCompanyLogo', id: companyInstance?.logo)}"/>
            </p>

        </div>

    </g:if>

    <div class="large-9 columns">
        <h2>

            <!-- company name -->
            ${companyInstance?.companyName}
        </h2>

    </div>
</div>



<hr>


<div class="row">

    <div class="large-8 columns">

        <p>
            <g:stripHtmlTags text="${companyInstance?.companyCharacteristic}"/>
        </p>

    </div>

    <div class="large-4 columns">

        <g:render template="panel" model="[companyInstance: companyInstance]"/>

        <g:render template="panelContact" model="[companyInstance: companyInstance]"/>

    </div>
</div>

<g:if test="${companyInstance.jobOffer}">
    <div class="row">
        <br/>


        <div class="large-12 columns">
            <h2><g:message code="company.companyprofiles.actualJoboffers"/></h2>

            <hr>



            <g:each in="${jobOfferInstanceList}" status="i" var="jobOfferInstance">

                <g:render template="/jobOffer/table" model="[jobOfferInstance: jobOfferInstance]"/>

            </g:each>

        </div>
    </div>

</g:if>

<!-- loop-->

<!-- galerie class="th" -->


<g:if test="${companyInstance.galleryPictures.size() > 0}">

    <div class="row">

        <div class="large-12 columns">

            <br/>

            <h2><g:message code="company.companyprofiles.inOurOffice"/></h2>
            <hr>


            <ul class="small-block-grid-4" data-clearing>
                <g:each var="galleryPicture" in="${companyInstance.galleryPictures}">

                    <li>

                        <a href="${createLink(absolute: true, controller: 'media', action: 'getCompanyGalleryImage', id: galleryPicture?.id)}"
                           class="th" style="height: 150px; overflow: hidden">
                            <img src="${createLink(absolute: true, controller: 'media', action: 'getCompanyGalleryImage', id: galleryPicture?.id)}"/>
                        </a>

                    </li>

                </g:each>

            </ul>

        </div>
    </div>

</g:if>


<div class="row">

    <div class="large-12 columns">

        <br/>

        <h2>${message(code: 'find.our.us')}</h2>
        <hr>
        <g:render template="contact" model="[companyInstance: companyInstance]"/>
    </div>

</div>

<br/>

</body>
</html>
