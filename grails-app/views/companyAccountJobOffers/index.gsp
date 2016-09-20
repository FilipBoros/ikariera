<%@ page import="cz.ikariera.company.Services" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-9 columns">

        <h1><g:message code="company.jobOffers.label" /> </h1>

    </div>


    <div class="large-3 columns">
        <br />


        <g:link controller="companyAccountJobOffers" action="create" class="button right">

            <g:message code="company.jobOffer.new" />
        </g:link>

    </div>
    <div class="large-12 columns">
        <hr>

    </div>

</div>





<div class="row">

    <div class="large-12 columns">






        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="positionName" title="${message(code: 'companyAccount.positionName.label')}"
                                  />
 %{--               <g:sortableColumn property="advertisementType" title="${message(code: 'companyAccount.type.label')}"
                                  style="width : 100px; text-align: center;"/>--}%

                %{--          <g:sortableColumn property="dateCreated" title="${message(code: 'companyAccount.dateCreated.label')}"/>--}%

                <g:sortableColumn property="datePublished"   style="width : 150px;"
                                  title="${message(code: 'companyAccount.datePublished.label')}"/>
                <g:sortableColumn   style="width : 120px;" property="willExpire" title="Expiruje za"/>
                %{--<g:sortableColumn property="username" title="${message(code: 'companyAccount.username.label')}" />--}%

                %{--<g:sortableColumn property="credits" title="${message(code: 'companyAccount.credits.label')}"/>--}%


                <th style="text-align: center;  width : 40px">TOP</th>

                <th style="text-align: center;  width : 40px">${message(code: 'jobOffer.printed.label')}</th>
                <th style="width : 120px; text-align: center;">${message(code: 'company.action.label')}</th>
            </tr>
            </thead>
            <tbody id="jobOfferTableBody">
            <g:if test="${!jobOfferInstanceList}">
                <tr>
                    <td colspan="8">${message(code: 'companyAccount.noEntries.label')}</td>
                </tr>

                </tbody>
                </table>
            </g:if>
            <g:else>
                <g:each in="${jobOfferInstanceList}" status="i" var="jobInstance">
                    <tr>

                        %{--   <td><g:link controller="companyAccountJobOffers" action="showDetail"
                                       id="${jobInstance?.id}">${jobInstance?.positionName}</g:link></td>--}%


                        <td>

                                <g:link action="show" id="${jobInstance?.id}" >
                                    <h5>
                                ${jobInstance?.positionName}
                                    </h5>
                                </g:link>



                        <h7 class="subheader">
                        ${message(code: jobInstance?.jobOfferType?.i18NameFull)}
                            </h7>
                        </td>

                %{--        <td style="text-align: center;">
                            ${message(code: jobInstance?.jobOfferType?.i18NameFull)}
                        </td>--}%

                        %{--
                                                <td style="text-align: center;"><g:formatDate format="dd.MM.yyyy"
                                                                                              date="${jobInstance?.dateCreated}"/></td>--}%


                        <td>
                            <g:if test="${jobInstance?.datePublished}">
                                <g:formatDate type="date" style="long"
                                              date="${jobInstance?.datePublished}"/>
                            </g:if>
                            <g:else>
                                Nepublikováno

                            </g:else>

                        </td>

                        <td style="text-align: center;">

                    <g:if test="${jobInstance?.willExpire}">
                        <g:if test="${jobInstance?.willExpire > new Date()}">
                            <g:daysBetween startDate="${new Date()}" endDate="${jobInstance?.willExpire}"  />  dní
                        </g:if>
                        <g:else>
                                Expirováno
                        </g:else>
                    </g:if>
                   %{--         <g:formatDate format="dd.MM.yyyy"
                                          date="${jobInstance?.willExpire}"/>--}%

                        </td>

                        %{--<td>${fieldValue(bean: jobInstance, field: "companyAccountUser.user.username")}</td>--}%
                        <td style="text-align: center;">

                            <g:if test="${jobInstance?.topPos}">
                                <i class="fi-check"></i>
                            </g:if>

                        </td>


                        <td style="text-align: center;">${jobInstance?.counter}/${jobInstance?.contactCounter}</td>
                        <td>

                            <g:render template="dropDownMenu" model="[jobInstance: jobInstance]"/>

                        </td>
                    </tr>
                </g:each>

                </tbody>
                </table>

                <g:paginateFoundation total="${jobOfferInstanceListTotal}" params="${params}"
                                      maxsteps="4"
                                      next="${message(code: "list.paginate.next")}"
                                      prev="${message(code: "list.paginate.prev")}"/>
            </g:else>




        %{--
                <div class="legend">
                    <b><g:message code="jobOffer.legend.title"/></b>
                    <br/>
                    <g:message code="jobOffer.legend.description.Z"/>
                    <br/>
                    <g:message code="jobOffer.legend.description.K"/>
                </div>--}%

    </div>

</div>


<br/>
<br/>

</body>
</html>




