<%@ page import="cz.ikariera.company.JobCategory" %>
<%@ page import="cz.ikariera.company.Locality" %>

<%@ page import="cz.ikariera.company.Company" %>
<%@ page import="cz.ikariera.company.JobOfferType" %>
<%@ page import="cz.ikariera.security.User" %>
<%@ page import="cz.ikariera.company.ReqLangCombination" %>
<%@ page import="cz.ikariera.student.LanguageType" %>


<!doctype html>
<html>
<head>

    <title>
        <g:message code="title.joboffer.detail" args="${[jobOfferInstance?.positionName]}"/>
    </title>

    <!-- say hello to open graph and facebook :) -->
    <meta property="og:title"
          content="${message(code: 'title.joboffer.detail', args: [jobOfferInstance?.positionName])}"/>

    <meta property="og:site_name" content="iKariéra.cz"/>

    <meta property="og:url" content="${'http://www.ikariera.cz' + request.forwardURI}"/>

    <meta property="og:description" lang="cz" content="${jobOfferInstance?.jobOfferDescription}"/>

    <meta property="og:image"
          content="${createLink(absolute: true, controller: 'media', action: 'getHeroMedia', id: heroImage?.imageLink)}"/>

    <meta name="layout" content="main">


    <style>
    .headerNavigationRight {

        margin-top: 1em;
        color: #6F6F6F;

    }

    .bgImage {
        background: none repeat scroll 0 0 #000000;
        height: 100%;
        overflow: hidden;
        position: absolute;
        width: 100%;
        z-index: 0;
    }

    .slideshow {
        margin: 0;
        list-style: none outside none;
        padding-left: 0;
        position: relative;
    }

    .slideshow div > img {
        left: 50%;
        margin-left: -800px;
        position: relative;
        display: block;
        height: 700px;
        width: 1600px;
        max-width: none;
        vertical-align: middle;
        border: 0 none;
        z-index: 0;
    }

    </style>
</head>

<body>
<div class="slideshow">
    %{-- test image, show-for-large-up not used because jquery fade in is not woring with this class --}%
%{--    <div class="bgImage " style=" display: none; margin-top: 0.1em ; background: no-repeat center center url('${resource(dir: 'images/ikariera', file: 'test.jpg')}')  ;  height: 400px">
    </div>--}%

    %{-- real image, show-for-large-up --}%
%{--    <g:set var="heroLink"
           value="${createLink(absolute: true, controller: 'media', action: 'getHeroMedia', id: hero?.imageLink)}"/>

    <div class="bgImage " style=" display: none; padding-top: 0.1em ; margin-top: 0.2em ; background: center url('${heroLink}') repeat-x ; height: 400px">
    </div>--}%
</div>


<div class="row">


    <div class="large-12 columns" style="background-color: white">
        <br/>

        <h2>${jobOfferInstance?.positionName}</h2>

        <div class="row">
            <div class="large-10 columns">
                <g:link controller="companyProfiles" action="detail" id="${jobOfferInstance?.company?.id}">
                    <h3 style="font-weight: 300">

                        ${jobOfferInstance?.company?.companyName}

                    </h3>
                </g:link>
            </div>

            <div class="large-2 columns">
                <g:link controller="jobOffer" action="index" class="headerNavigationRight right">${message(code: 'back.label')}</g:link>
            </div>
            <hr style="margin-left: 1em; margin-right: 1em">
        </div>
    </div>
</div>


<div class="row" style="background-color: white">

    <div class="large-8 columns" style="background-color: white">

        <g:render template="mainPart" model="[jobOfferInstance: jobOfferInstance]"/>
    </div>

    <div class="large-4 columns" style="background-color: white">

        <g:render template="panelInfo" model="[jobOfferInstance: jobOfferInstance]"/>


        <g:render template="panelContact" model="[jobOfferInstance: jobOfferInstance]"/>

        <hr>

        <g:render template="panelCompany" model="[jobOfferInstance: jobOfferInstance]"/>

    </div>
</div>


<script>
    bgImage = $(".bgImage");
    rotator_delay_in = 1000;
    bgImage.fadeIn(rotator_delay_in);

</script>


</body>
</html>
