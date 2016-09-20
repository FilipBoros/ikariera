<g:link class="button expand alert radius" controller="student"
        action="registration">${message(code: 'topSearchBanner.newRegistration.label')}</g:link>




<g:link controller="companyAccount" class="button expand secondary radius"
        action="addJobOffer">${message(code: 'topSearchBanner.addOffer.label')}</g:link>



<g:render template="/login/auth"/>

