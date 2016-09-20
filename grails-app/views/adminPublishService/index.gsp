<!doctype html>
<html>
<head>

    <meta name="layout" content="administrator"/>

</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: 'admin.listPublishServices.label')}</h2>




    </div>

    <div class="large-3 columns">
        <br>

        <g:link class="button right" controller="adminPublishService" action="create"><g:message code="create"/></g:link>



    </div>

</div>

<div class="row">

    <div class="large-12 columns">

        <hr>


        <table class="table table-hover table-striped table-condensed table-bordered">
            <thead>
            <tr>
                <th><g:message code="admin.publish.numberOfDays"/></th>
                %{--<g:sortableColumn property="jobCategory"
                                  title="${message(code: 'admin.heroImage.jobCategory')}"/>--}%
                <th><g:message code="admin.publish.numberOfPublishedJobOffers"/></th>
                %{--<g:sortableColumn property="locality"
                                  title="${message(code: 'admin.heroImage.locality')}"/>--}%
                <th><g:message code="admin.publish.prize"/></th>
                %{--<g:sortableColumn property="country"
                                  title="${message(code: 'admin.heroImage.country')}"/>--}%

                <th></th>

            </tr>
            </thead>
            <tbody>
            <g:if test="${!heroImageList}">
                <tr>
                    <td colspan="2">${message(code: 'jobOffer.noData.label')}</td>
                </tr>
            </g:if>
            <g:else>

                <g:each in="${heroImageList}" status="i" var="heroImageInstance">
                    <tr>

                        <td>
                            ${heroImageInstance?.numberOfDays}
                        </td>





                        <td>
                            ${heroImageInstance?.numberOfPublishedJobOffers}
                        </td>



                        <td>
                            ${heroImageInstance?.prize}

                        </td>



                        <td>

                            <a data-dropdown="drop-${heroImageInstance.id}" class=" dropdown button secondary round right" href="#">

                                <i class="fi-wrench"></i>
                            </a>

                            <ul id="drop-${heroImageInstance.id}" data-dropdown-content class="f-dropdown">

                                 <li>
                                    <g:link controller="adminPublishService" action="edit"
                                            params="[id: heroImageInstance?.id]">
                                        ${message(code: 'edit')}
                                    </g:link>
                                </li>




                                <li>
                                    <g:link controller="adminPublishService" action="deleteIt"
                                            params="[id: heroImageInstance?.id]">
                                        ${message(code: 'delete')}
                                    </g:link>
                                </li>

                            </ul>

                        </td>



                    </tr>
                </g:each>

            </g:else>
            </tbody>
        </table>
        <g:paginateFoundation total="${listInstanceTotal}"/>

    </div>
</div>

</body>
</html>
