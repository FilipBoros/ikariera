<div class="background_admin ">
    <div class="row">

        <ul class=" admin-menu">
            <li class="logo">
                <a class="home " href="${createLink(uri: '/')}">
                    <asset:image
                         src="ikariera/apple-touch-icon.png" alt="iKariera"
                         style="height: 70px ;border-width: 0px; margin-bottom: 10px;"/>
                </a>
            </li>

            <li>
                <g:link controller="studentAccountPersonalDetails">
                    <g:message code="student.personal.details"/>
                </g:link>

            </li>

            <li>
<g:link controller="studentAccountMessage" action="inBox">

            <g:message code="student.account.messages"/>

    |

    %{--<span class="automaticAjaxLoad" style="display: inline;" TODO uncomment me
          ajaxLoadingUrl="${createLink(controller: 'studentAccountMessage', action: 'dialogUnreadCountAll')}">
    </span>--}%

    <span class="fi-mail"></span>

</g:link>
        </li>

        %{--  <li class="dropdown">

           <span class="fi-mail"></span>  <g:message code="student.account.messages"/>

           <ul>

               <li>

                   <g:link controller="studentAccountMessage" action="inBox">
                       <g:message code="student.dialogs.messages"/>

                    |

                       <span class="automaticAjaxLoad" style="display: inline;"
                             ajaxLoadingUrl="${createLink(controller: 'studentAccountMessage', action: 'dialogUnreadCountIn')}">
                       </span>

                            <span class="fi-mail"></span>

                        </g:link>

                    </li>

                    <li>

                        <g:link controller="studentAccountMessage" action="outbox">
                            <g:message code="student.dialogs.outbox"/>

                       |

                            <span class="automaticAjaxLoad" style="display: inline;"
                                  ajaxLoadingUrl="${createLink(controller: 'studentAccountMessage', action: 'dialogUnreadCountOut')}">
                            </span>

                            <span class="fi-mail"></span>

                        </g:link>

                    </li>


                </ul>
            </li>
        --}%

            <li class="dropdown">

                <g:message code="student.additional.details"/>


                <ul>

                    <li>

                        <g:link controller="studentAccountUniversity">
                            <g:message code="student.education"/>

                        </g:link>
                    </li>
                    <li>

                        <g:link controller="studentAccountLanguage">
                            <g:message code="student.languages"/>
                        </g:link>
                    </li>


                    <li>

                        <g:link controller="studentAccountCertificate">
                            <g:message code="student.certificates"/>
                        </g:link>
                    </li>
                    <li>

                        <g:link controller="studentAccountExperience">
                            <g:message code="student.working.experience"/>
                        </g:link>
                    </li>
                   

                </ul>
            </li>

            <li>

                <g:link controller="StudentAccountRecommended">
                    <g:message code="student.recommended.jobOffer.messages"/>
                </g:link>
            </li>


%{--
   <li>
                <g:link controller="studentAccountContact">

                    <g:message code="student.contact"/>
                </g:link>
            </li>--}%




     



        </ul>

    </div>
</div>