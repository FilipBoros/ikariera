<div class="background_admin ">
    <div class="row">

        <div class="">

            <ul class=" admin-menu">
                <li class="logo">
                    <a class="home" href="${createLink(uri: '/')}">
                        <img style="height: 70px"
                             src="${resource(dir: 'images/ikariera', file: 'apple-touch-icon.png')}" alt="iKariera"
                             style="border-width: 0px; margin-bottom: 10px;"/>
                    </a>
                </li>
                <li class="dropdown">

                    <g:message code="admin.companies.label"/>
                    <ul>
                        <li><g:link mapping="adminCompany"><g:message
                                code="admin.companies.label"/></g:link></li>

                        <li><g:link mapping="adminJobOffer"><g:message
                                code="admin.advertisement.label"/></g:link></li>








                    <li>
                        <g:link controller="adminEmail">
                            <g:message
                                    code="admin.mass.emails"/> ${cz.ikariera.admin.AdminEmails.countByDateSentIsNull()}
                        </g:link>
                    </li>







                        <li>
                            <g:link controller="adminFeedBack" action="listCreditsRequire">
                                <g:message code="creditRequest.caption"/>
                            </g:link>
                        </li>

                    <li><g:link controller="adminFeedBack"><g:message code="admin.feedbacks" /> </g:link></li>

                    </ul>
                </li>

                <li class="dropdown"><g:message code="admin.services.label"/>
                    <ul>

                    <li><g:link mapping="adminServices"><g:message
                            code="admin.services.label"/></g:link></li>

                    <li><g:link controller="adminPublishService" action="index"><g:message code="publishService.h2" /> </g:link></li>

                    <li><g:link mapping="adminCredits"><g:message
                            code="admin.creditsHistory.label"/></g:link></li>

                </ul>
                </li>



                <li class="dropdown"><g:message code="admin.allUsers.label"/>
                    <ul>
                        <li><g:link mapping="adminUserAll">
                            <g:message code="admin.allUsers.label"/>

                        </g:link></li>


                        <li><g:link mapping="adminUserCompany"><g:message
                                code="admin.companyUsers.label"/></g:link></li>

                        <li><g:link mapping="adminUserStudent"><g:message
                                code="admin.studentUsers.label"/></g:link></li>

                        <li><g:link mapping="adminUserAdmin"><g:message
                                code="admin.adminUsers.label"/></g:link></li>



                    <li><g:link mapping="adminRemoteRegister" action="list"><g:message
                            code="admin.remoteRegister.label"/></g:link></li>



                        <li>
                       <g:link controller="adminSimpleRegistration"  >
                                <g:message code="simple.student.registration"/>
                            </g:link>
                        </li>


                    </ul>
                </li>

                <li class="dropdown"><g:message code="admin.web.label"/>
                    <ul>
                        %{--                         <li><g:link mapping="adminCompany"><g:message
                                                         code="admin.companies.label"/></g:link></li>

                                                 <li><g:link mapping="adminJobOffer"><g:message
                                                         code="admin.advertisement.label"/></g:link></li>


                                                 <li><g:link mapping="adminServices"><g:message
                                                         code="admin.services.label"/></g:link></li>

                                                 <li><g:link mapping="adminCredits"><g:message
                                                         code="admin.creditsHistory.label"/></g:link></li>

                                                 <li><g:link mapping="adminBanner"><g:message code="admin.banners.label"/></g:link></li>


                     --}%


                    <li><g:link controller="adminAdvertisement" action="list"><g:message
                            code="admin.advertisement.labeladv"/></g:link></li>






                    <li><g:link mapping="adminPartners"><g:message code="admin.partners.label"/></g:link></li>

                    <li><g:link mapping="adminMedia"><g:message code="study.category.media"/></g:link></li>





                    <li><g:link mapping="adminBanner"><g:message code="admin.banners.label"/></g:link></li>





                        <li><g:link mapping="adminFaq"><g:message code="admin.faq.label"/></g:link></li>

%{--                        <li><g:link mapping="adminStatistics"><g:message
                                code="admin.statistics.label"/></g:link></li>--}%











                    </ul>
                </li>


            <li class="dropdown"><g:message code="admin.settings.label"/>
                <ul>
                    %{--                          <li><g:link mapping="adminContact"><g:message
                                                      code="admin.contactEdit.label"/></g:link></li>

                                              <li><g:link mapping="adminFaq"><g:message code="admin.faq.label"/></g:link></li>

                                              <li><g:link mapping="adminStatistics"><g:message
                                                      code="admin.statistics.label"/></g:link></li>

                                              --}%%{--<li><g:link controller="adminMainLogo" action="index">MAIN LOGO1</g:link></li>--}%%{--
                                              <li><g:link mapping="adminMainLogo"><g:message code="admin.mainLogo.label"/></g:link></li>

                  --}%


                    %{--<li><g:link mapping="adminEmail"><g:message code="admin.mailing.label"/></g:link></li>--}%


                <li><g:link mapping="adminHero"><g:message code="admin.hero.label"/></g:link></li>

                <li><g:link mapping="adminContact"><g:message
                        code="admin.contactEdit.label"/></g:link></li>

                    <li><g:link controller="adminMaintenanceMessage"><g:message
                            code="administration.maintenance.messages"/></g:link></li>


                %{--    <li><g:link controller="adminEmail" action="createDemoEmail"><g:message code="admin.test.mailchimp" /> </g:link></li>
--}%

                    %{--       <li><g:link mapping="adminCatalogue"><g:message
                                   code="admin.catalogue.label"/></g:link></li>--}%
                </ul>
            </li>


                <li class="dropdown"><g:message code="admin.apis.label"/>
                    <ul>

                    <li><g:link mapping="adminApiKey"><g:message code="internal.api.keys"/></g:link></li>

                    <li><g:link controller="adminRemoteApiKey"><g:message
                            code="remote.api.keys"/></g:link></li>

                    <li><g:link controller="adminRemoteServer"><g:message
                            code="administration.remote.servers"/></g:link></li>






                </ul>
                </li>


            </ul>
        </div> <!-- #wrapper -->
    </div>
</div>





