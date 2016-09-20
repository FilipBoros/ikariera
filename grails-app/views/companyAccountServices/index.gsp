<%@ page import="cz.ikariera.company.Services; cz.*" %>

<!doctype html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br>

    <div class="large-12 columns">

        <h1><g:message code="company.portal.services"/></h1>


        <h3 class="subheader"><g:message code="company.services.activation.advantages.information"/></h3>

    </div>

    <hr>

</div>


<div class="row">

<ul class=" medium-block-grid-2 large-block-grid-3">
<li>

    <g:link action="activation" id="articles-service">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

    </g:link>

    <g:link action="activation" id="articles-service">
        <h4><g:message code="company.articles.label"/></h4>
    </g:link>
    <p>
        <g:message code="company.service.articles.information"/>
        <br>

        <g:link action="activation" id="articles-service"><g:message code="more.information"/></g:link>

    </p>



    <g:if test="${companyInstance?.servicesExpire?.article > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.article}" type="date" style="long"/>
        </div>

    </g:if>

</li>
<li>
    <g:link action="activation" id="mail-service">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

    </g:link>

    <g:link action="activation" id="mail-service">
        <h4><g:message code="company.service.mailing"/></h4>
    </g:link>

    <p>
        <g:message code="company.service.mailing.information"/>

        <br/>
        <g:link action="activation" id="mail-service"><g:message code="more.information"/></g:link>

    </p>

    <g:if test="${companyInstance?.servicesExpire?.mail > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.mail}" type="date" style="long"/>
        </div>

    </g:if>

</li>
<li>
    <g:link action="activation" id="cv-service">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

    </g:link>
    <g:link action="activation" id="cv-service">
        <h4><g:message code="admin.services.cvSearch.service"/></h4>
    </g:link>
    <p>
        <g:message code="company.service.cvsearch.information"/>

        <br/>

        <g:link action="activation" id="cv-service"><g:message code="more.information"/></g:link>

    </p>



    <g:if test="${companyInstance?.servicesExpire?.cv > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.cv}" type="date" style="long"/>
        </div>

    </g:if>

</li>
<li>
    <g:link action="activation" id="top-service">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

    </g:link>

    <g:link action="activation" id="top-service">
        <h4><g:message code="service.topOffer"/></h4>
    </g:link>
    <p>
        <g:message code="company.service.topOffer.information"/>
        <br>

        <g:link action="activation" id="top-service"><g:message code="more.information"/></g:link>

    </p>


    <g:if test="${companyInstance?.servicesExpire?.topOffer > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.topOffer}" type="date" style="long"/>
        </div>

    </g:if>

</li>


<li>

    <g:link action="activation" id="message-service">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>
    </g:link>
    <g:link action="activation" id="message-service">
        <h4><g:message code="company.service.messages"/></h4>
    </g:link>
    <p>

        <g:message code="company.service.messages.information"/>
        <br/>
        <g:link action="activation" id="message-service"><g:message code="more.information"/></g:link>
    </p>


    <g:if test="${companyInstance?.servicesExpire?.messages > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.messages}" type="date" style="long"/>
        </div>

    </g:if>

</li>

<li>

    <g:link action="activation" id="remote-post">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>
    </g:link>
    <g:link action="activation" id="remote-post">
        <h4><g:message code="company.service.remoteJobOffers"/></h4>
    </g:link>
    <p>

        <br/>
        <g:link action="activation" id="remote-post"><g:message code="more.information"/></g:link>
    </p>


    <g:if test="${companyInstance?.servicesExpire?.remote > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.remote}" type="date" style="long"/>
        </div>

    </g:if>

</li>
<li>

    <g:link action="activation" id="jobofferapi">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>
    </g:link>
    <g:link action="activation" id="jobofferapi">
        <h4><g:message code="company.jobOffer.api"/></h4>
    </g:link>
    <p>

        <br/>
        <g:link action="activation" id="jobofferapi"><g:message code="more.information"/></g:link>
    </p>


    <g:if test="${companyInstance?.servicesExpire?.apiActiveTill > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.apiActiveTill}" type="date" style="long"/>
        </div>

    </g:if>

</li>




<li>
    <g:link action="activation" id="share-service">
        <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

    </g:link>

    <g:link action="activation" id="share-service">
        <h4><g:message code="company.service.international.sharing"/></h4>
    </g:link>
    <p>
        <g:message code="company.service.international.sharing.information"/>
        <br/>

        <g:link action="activation" id="share-service"><g:message code="more.information"/></g:link>
    </p>


    <g:if test="${companyInstance?.servicesExpire?.shareOffer > new Date()}">
        <div class="panel " style="text-align: center">
            <g:message code="service.actived.until"/>
            <g:formatDate date="${companyInstance?.servicesExpire?.shareOffer}" type="date" style="long"/>
        </div>

    </g:if>

</li>

</ul>

</div>


%{--
    <div class="large-4 columns">
        <g:link action="activation" id="hero-service">
            <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

        </g:link>

        <g:link action="activation" id="hero-service">
            <h4>Vlastní hero obrázek</h4>
        </g:link>
        <p>Chcete aby se vámi vložené nabídky na hlavní stránce párovaly s Vaším profilem? Tato služba umožnuje nahrát.

            <br>

            <g:link action="activation" id="hero-service"><g:message code="more.information" /></g:link>

        </p>



        <g:if test="${companyInstance?.servicesExpire?.hero > new Date()}">
            <div class="panel " style="text-align: center">
               <g:message code="service.actived.until" />
                <g:formatDate date="${companyInstance?.servicesExpire?.hero}" type="date" style="long"/>
            </div>

        </g:if>

    </div>--}%




%{--

<br> <br> <br>

<div class="row">

    <div class="large-4 columns">

        <g:link action="activation" id="rest-service">
            <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>
        </g:link>
        <g:link action="activation" id="rest-service">
            <h4>Vlastní REST Api</h4>
        </g:link>
        <p>Rest service

            <br/>

            <g:link action="activation" id="rest-service"><g:message code="more.information" /></g:link>
        </p>


        <g:if test="${companyInstance?.servicesExpire?.rest > new Date()}">
            <div class="panel " style="text-align: center">
                <g:message code="service.actived.until" />
                <g:formatDate date="${companyInstance?.servicesExpire?.rest}" type="date" style="long"/>
            </div>

        </g:if>

    </div>



</div>



<br> <br> <br>--}%

</body>
</html>




