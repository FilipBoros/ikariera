<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>

            ${companyInstance?.companyName}

        </h2>


    </div>
    <hr>
</div>

<div class="row">

    <div class="large-8 columns">

        <table class="table">
            <thead>
            <tr><th><g:message code="service.name.label" /> </th>
                <th><g:message code="service.actived.until" /> </th>
                <th style="width: 150px"><g:message code="action" /> </th></tr>

            </thead>

            <tbody>
            <tr><td>Top společnost</td>
                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.topProfile]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.topProfile,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'advanced-profile-service']"/></td>
            </tr>

            <tr><td>Mail service</td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.mail]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.mail,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'mail-service']"/></td>
            </tr>
            <tr><td><g:message code="service.topOffer"/></td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.topOffer]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.topOffer,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'top-service']"/></td>
            </tr>
            <tr><td> <g:message code="service.cvsearch" /> </td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.cv]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.cv,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'cv-service']"/></td>
            </tr>
            <tr><td><g:message code="service.Articles" /></td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.article]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.article,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'articles-service']"/></td>


            </tr>


            <tr><td><g:message code="service.international.sharing" /></td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.shareOffer]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.shareOffer,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'share-service']"/></td>
            </tr>


            <tr><td><g:message code="service.messages" /> </td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.messages]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.messages,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'message-service']"/></td>
            </tr>



            <tr><td><g:message code="service.restApi" /></td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.rest]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.rest,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'rest-service']"/></td>
            </tr>



            <tr><td><g:message code="service.hero" /> </td>

                <td><g:render template="serviceState"
                              model="[service: companyInstance?.servicesExpire?.hero]"/></td>
                <td><g:render template="dropDownMenu"
                              model="[service: companyInstance?.servicesExpire?.hero,
                                      companyInstance: companyInstance,
                                      serviceUniqueName: 'hero-service']"/></td>
            </tr>




            </tbody>
        </table>
    </div>

    <div class="large-4 columns" >
        <div class="panel">

            <h4><g:message code="notice" /> </h4>

            <hr>

            <p><g:message code="admin.service.activation.notice" /> </p>


        </div>


    </div>

</div>

</body>
</html>




