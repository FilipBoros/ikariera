<div class="background_admin ">
    <div class="row">

        <div class="">

            <ul class=" admin-menu">
                <li class="logo">
                    <a class="home " href="${createLink(uri: '/')}">
                        <g:if test="${grailsApplication.config.language.equals('cz')}">
                            <asset:image src="ikariera/apple-touch-icon.png" alt="iKariera"
                                         style="border-width: 0px;height: 70px; margin-bottom: 10px;" />
                        </g:if>
                        <g:elseif test="${grailsApplication.config.language.equals('sk')}">
                            <asset:image src="ikariera_sk/apple-touch-icon.png" alt="iKariera"
                                         style="border-width: 0px;height: 70px; margin-bottom: 10px;" />
                        </g:elseif>

                    </a>
                </li>


                <li class="dropdown">

                    <g:message code="company.jobOffers.label"/>


                    <ul>

                        <li>
                            <g:link controller="companyAccountJobOffers">
                                <g:message code="company.jobOffers.label"/>
                            </g:link>
                        </li>


                        <li>

                            <g:link controller="companyAccountJobOffers" action="create"><g:message
                                    code="topSearchBanner.addOffer.label"/></g:link>
                        </li>
                        <li>
                            <g:link controller="companyContacts" action="list">

                                <g:message code="company.contacts"/>
                            </g:link>
                        </li>

                    </ul>
                </li>



                <li class="dropdown">

                    <g:message code="company.advanced.services"/>


                    <ul>

                        <li><g:link controller="companyAccountServices" action="index">

                            <g:message code="company.services.overview"/>
                        </g:link>
                        </li>


                        <li><g:link controller="companyAccountArticles"><g:message
                                code="company.articles.label"/></g:link>
                        </li>


                        <li><g:link controller="companyAccountCvSearch" action="index"><g:message
                                code="company.browseCV.label"/></g:link></li>


                        <li><g:link controller="companyAccountEmail" action="index"><g:message
                                code="company.massMail.label"/></g:link></li>

                        %{--              <li>

                                          <g:link controller="companyAccountMessage" action="jobofferfeedbacks">
                                              <g:message code="company.message.service"/>
                                          </g:link>

                                      </li>--}%



                       %{-- <li><g:link controller="companyJobOfferApi" action="index"> Michal Dolnak
                            <g:message code="company.jobOffer.api"/>
                        </g:link>
                        </li>--}%

                        %{--                   <li><g:link controller="companyAccountLogo" action="index">
                                               ${message(code: 'logo.name.company.label')}
                                           </g:link>

                                            </li>

                                           <li><g:link controller="companyGallery" action="index">
                                               ${message(code: 'companyDetail.gallery.label')}
                                           </g:link>--}%

                       %{-- <li><g:link controller="companyPublishService" action="index"> Michal Dolnak
                            ${message(code: 'publishService.company.label')}
                        </g:link>

                        </li>--}%



                        <li><g:link controller="companyAccountCreditsHistory" action="index"><g:message
                                code="company.creditHistory.label"/></g:link>
                        </li>
             %{--           <li><g:link controller="companyAccountServices" action="askForCredits">
                            <g:message code="topSearchBannerCompany.creditsRequire.label"/>
                        </g:link>
                        </li>--}%

                    </ul>
                </li>

            <li >


            <g:link controller="companyAccountMessage" action="index">
                <g:message code="company.account.messages"/>

                |

                <span class="automaticAjaxLoad" style="display: inline;"
                      ajaxLoadingUrl="${createLink(controller: 'companyAccountMessage', action: 'dialogUnreadCountAll')}">
                </span>

                <span class="fi-mail"></span>

            </g:link>

            </li>

            %{--
                        <li class="dropdown">

                                <g:message code="company.dialogs"/>

                                <ul>

                                    <li>

                                        <g:link controller="companyAccountMessage" action="index">
                                            <g:message code="company.dialogs.inbox"/>

                                            |

                                            <span class="automaticAjaxLoad" style="display: inline;"
                                                  ajaxLoadingUrl="${createLink(controller: 'companyAccountMessage', action: 'dialogUnreadCountIn')}">
                                            </span>

                                            <span class="fi-mail"></span>

                                        </g:link>

                                    </li>

                                <li>

                                    <g:link controller="companyAccountMessage" action="outbox">
                                        <g:message code="company.dialogs.outbox"/>

                                        |

                                        <span class="automaticAjaxLoad" style="display: inline;"
                                              ajaxLoadingUrl="${createLink(controller: 'companyAccountMessage', action: 'dialogUnreadCountOut')}">
                                        </span>

                                        <span class="fi-mail"></span>

                                    </g:link>

                                </li>

                              <li>

                                        <g:link controller="companyAccountMessage" action="jobOfferMessages">
                                            <g:message code="company.message.inbox.jobOfferReplies"/>


                                            |



                                            <span class="automaticAjaxLoad" style="display: inline;"
                                                  ajaxLoadingUrl="${createLink(controller: 'companyAccountMessage', action: 'dialogUnreadCount')}">
                                            </span>

                                            <span class="fi-mail"></span>

                                        </g:link>

                                    </li>

                                </ul>
                            </li>--}%



                <li class="dropdown">
                    <g:message code="company.settings"/>


                    <ul>

                        <li><g:link controller="companyAccountProfile">
                            <g:message code="company.editProfile.label"/></g:link>
                        </li>






                        <li><g:link controller="companyAccountUser">
                            <g:message code="company.editUsers.label"/>
                        </g:link>

                        </li>


                        <li>

                            <g:link controller="companyAccountFeedback" action="feedback">
                                <g:message code="upperRightMenu.writeUs.label"/>
                            </g:link>
                        </li>

                    </ul>
                </li>

            </ul>

        </div>
    </div>
</div>

