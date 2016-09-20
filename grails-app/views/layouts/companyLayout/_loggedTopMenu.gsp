<div class=" callout" style="margin-bottom: 0 ; background: #333; color: white; font-size: 90%;  ">

    <div class="row">

        <div class="left" style="margin-top: 12px">

            <sec:access expression="hasRole('ROLE_ADMIN')">

                <g:link controller="admin">

                    <g:message code="administration"/>
                </g:link>
                ${message(code: "admin.takeControlInfo.label", args: [sec.username(), cz.ikariera.security?.User?.findByUsername(sec.username())?.company?.companyName, loggedUserCompanyCredits(id: sec.loggedInUserInfo(field: 'id'))])}

            </sec:access>
            <sec:access expression="!hasRole('ROLE_ADMIN')">

                <g:message
                        args="[sec.loggedInUserInfo(field: 'companyName'), loggedUserCompanyCredits(id: sec.loggedInUserInfo(field: 'id'))]"
                        code="company.accountName.label"/>

            </sec:access>

        </div>


        <div id="calloutPanelButtons" class="right">

            <g:link id="createJobOffer" controller="companyAccountJobOffers" action="create" class="button radius success"
                    style="margin-bottom: 0; margin-top: 0;"><g:message code="topSearchBanner.addOffer.label"/></g:link>





            <g:link controller="companyAccount" class="button radius  top-bar-button" data-dropdown="drop-company"
                    style="margin-bottom: 0; margin-top: 0;">

                <g:message code="user.account.label"/>


                |



                <span class="automaticAjaxLoad" style="display: inline;"
                     ajaxLoadingUrl="${createLink(controller: 'companyAccountMessage', action: 'totalUnreadCount')}">
                </span>

                <span class="fi-mail"></span>


            </g:link>
        %{--jobofferfeedbacks--}%

        </div>
    </div>

</div>



<ul id="drop-company" data-dropdown-content class="f-dropdown">
    %{--  <li><g:link controller="companyAccount"> Info</g:link></li>--}%
    <li><g:link controller="companyAccountProfile"><g:message code="company.profile.label"/></g:link></li>
    <li><g:link controller="companyAccountUser"><g:message code="company.users.label"/></g:link></li>
    <li><g:link controller="companyAccountJobOffers"><g:message code="company.jobOffers.label"/></g:link></li>
    %{--    <li><g:link controller="companyAccountJobOffers" action="create" > Přidat nabídku</g:link></li>--}%
    <li>
    <g:link controller="companyAccountMessage" action="index">
        <g:message code="company.message.inbox"/>:
        <span class="automaticAjaxLoad" style="display: inline;"
              ajaxLoadingUrl="${createLink(controller: 'companyAccountMessage', action: 'dialogUnreadCountIn')}">
        </span>


    </g:link>
</li>


    <li><g:link controller="logout" action="index" class="  " style="margin-bottom: 0; margin-top: 0;">
        <g:message code="logout"/></g:link>
    </li>

</ul>
