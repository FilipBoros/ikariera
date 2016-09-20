<div class=" callout" style="margin-bottom: 0 ; background: #333; color: white; font-size: 90%;  ">

    <div class="row">

        <div class="left" style="margin-top: 12px">

            <g:message
                    args="[sec.loggedInUserInfo(field: 'fullName'), loggedUserProfileFinished(id: sec.loggedInUserInfo(field: 'id'))]"
                    code="student.accountName.label"/>

        </div>

        <div class="right">



            <g:link controller="studentAccountPersonalDetails" class="button radius success top-bar-button" style="margin-bottom: 0; margin-top: 0;"><g:message code="student.personal.profile" /> </g:link>

            <g:link controller="logout" action="index" class="button" style="margin-bottom: 0; margin-top: 0;"><g:message code="logout" /></g:link>
        </div>
    </div>

</div>




