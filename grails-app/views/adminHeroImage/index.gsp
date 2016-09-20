<!doctype html>
<html>
<head>

    <meta name="layout" content="administrator"/>

</head>

<body>

<div class="row">

    <br/>

    <div class="large-9 columns">

        <h2>${message(code: 'admin.hero.label')}</h2>

    </div>

    <div class="large-3 columns">
        <br>
        <g:link class="button right" controller="adminHeroImage" action="hero"><g:message
                code="ikariera.hero.header"/></g:link>
    </div>
</div>

<div class="row ">

    <div class="large-12 columns">

        <hr>

        <table class="table table-hover table-striped table-condensed table-bordered">
            <thead>
            <tr>
                <g:sortableColumn property="name"
                                  title="${message(code: 'admin.heroImage.name')}"/>
                <th>${message(code: 'default.categories.label')}</th>
                %{--<g:sortableColumn property="jobCategory"
                                  title="${message(code: 'admin.heroImage.jobCategory')}"/>--}%
                <th>${message(code: 'default.localities.label')}</th>
                %{--<g:sortableColumn property="locality"
                                  title="${message(code: 'admin.heroImage.locality')}"/>--}%
                <th>${message(code: 'default.countries.label')}</th>
                %{--<g:sortableColumn property="country"
                                  title="${message(code: 'admin.heroImage.country')}"/>--}%

                <g:sortableColumn property="published"
                                  title="${message(code: 'admin.heroImage.published')}"/>

                <th>${message(code: 'default.thumbnail.label')}</th>

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
                            ${heroImageInstance?.name}
                        </td>





                        <td>
                            <g:each in="${heroImageInstance?.jobCategories}" var="jobCategory">
                                <g:message code="${jobCategory?.i18NameFull}"/> <br/>
                            </g:each>
                        </td>



                        <td>
                            <g:each in="${heroImageInstance?.localities}" var="locality">
                                <g:message code="${locality?.i18NameFull}"/> <br/>
                            </g:each>

                        </td>



                        <td>
                            <g:each in="${heroImageInstance?.countries}" var="country">
                                <g:message code="${country?.i18NameFull}"/> <br/>
                            </g:each>
                        </td>



                        <td>

                            <g:message code="${heroImageInstance.publish}"/>

                        </td>


                        <td>

                            <g:render template="picture" model="[heroImageInstance: heroImageInstance]"/>

                        </td>

                        <td>

                            <a data-dropdown="drop-${heroImageInstance.id}"
                               class=" dropdown button secondary right round" href="#">

                                <i class="fi-wrench"></i>
                            </a>

                            <ul id="drop-${heroImageInstance.id}" data-dropdown-content class="f-dropdown">

                                <li>
                                    <g:if test="${!heroImageInstance.publish}">
                                        <g:link controller="adminHeroImage" action="publish"
                                                params="[id: heroImageInstance?.id]">
                                            ${message(code: 'admin.heroImage.publish')}
                                        </g:link>
                                    </g:if>
                                    <g:else>
                                        <g:link controller="adminHeroImage" action="unPublish"
                                                params="[id: heroImageInstance?.id]">
                                            ${message(code: 'admin.heroImage.unPublish')}
                                        </g:link>
                                    </g:else>

                                </li>




                                <li>
                                    <g:link controller="adminHeroImage" action="edit"
                                            params="[id: heroImageInstance?.id]">
                                        ${message(code: 'edit')}
                                    </g:link>
                                </li>




                                <li>
                                    <g:link controller="adminHeroImage" action="deleteIt"
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
