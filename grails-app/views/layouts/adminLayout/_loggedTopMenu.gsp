<div class=" callout" style="margin-bottom: 0 ; background: #333; color: white; font-size: 90%;  ">

    <div class="row">

        <div class="left" style="margin-top: 12px">

         <g:message code="administrator" />   :  ${sec.loggedInUserInfo(field: 'fullName')}

        </div>


        <div class="right">

            <g:link class="button radius alert top-bar-button" controller="adminCompany"
                    style="margin-bottom: 0; margin-top: 0;"><g:message code="administration" /> </g:link>


            <g:link controller="logout" action="index" class="button"
                    style="margin-bottom: 0; margin-top: 0;"><g:message code="logout" /> </g:link>
        </div>
    </div>

</div>





