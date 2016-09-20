<a data-dropdown="drop-${jobInstance.id}" class="button dropdown secondary round" href="#">


    <i class="fi-wrench"></i>


</a>



<ul id="drop-${jobInstance.id}" data-dropdown-content class="f-dropdown">
    <g:if test="${!jobInstance.datePublished || jobInstance.willExpire < new Date()}">




            <li>
        <g:link action="show" id="${jobInstance?.id}" target="_blank" >


                    <g:message code="jobOffer.quick.show" />
                </g:link>
            </li>



        <li>
            <g:link controller="companyAccountJobOffers" action="edit" id="${jobInstance.id}">

                <g:message code="jobOffer.edit.label" />
            </g:link>
        </li>


        <li>
            <g:link controller="companyAccountJobOffers" action="publish" id="${jobInstance.id}">
                   <g:message code="company.jobOffer.publish" />

            </g:link>
        </li>


        <li>
            <g:link controller="companyAccountJobOffers" action="publishTop" class="confirmIt"   id="${jobInstance.id}"
            >

                <g:message code="company.jobOffer.publish.as.top" />
            </g:link>
        </li>

        <li>
            <g:link controller="companyAccountJobOffers" action="publicTo" class="confirmIt"   id="${jobInstance.id}"
            >

                <g:message code="company.remote.publish"  />

            </g:link>
        </li>

        <li>
            <g:link controller="companyAccountJobOffers" action="delete" class="confirmIt"  id="${jobInstance.id}"
                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"
            >
                  <g:message code="delete"  />

            </g:link>
        </li>


    </g:if>
   <g:else>


       <li>
           <g:link action="show" id="${jobInstance?.id}" target="_blank" >


               <g:message code="jobOffer.quick.show" />
           </g:link>
       </li>


       <li>
           <g:link controller="companyAccountJobOffers" action="expire" class="confirmIt" id="${jobInstance.id}"

           >
               <g:message code="jobOffer.expire.label" />

           </g:link>
       </li>
   </g:else>
       %{--

        <li>
            <g:link controller="companyAccountJobOffers" action="longerExpire"
                    id="${jobInstance.id}" class="confirmIt">

                ${message(code: 'companyAccountUser.longerExpire.label')}
            </g:link>
        </li>


    <li>
        <g:link controller="companyAccountJobOffers" action="showDetail"
                id="${jobInstance?.id}" target="_blank">
            ${message(code: 'companyAccountUser.showJobOffer.label')}
        </g:link>
    </li>--}%
</ul>
