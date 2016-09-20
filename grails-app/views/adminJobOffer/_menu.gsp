<a data-dropdown="drop-${jobOfferInstance.id}" class=" dropdown button round secondary" href="#">

    <i class="fi-wrench"></i>
</a>

<ul id="drop-${jobOfferInstance.id}" data-dropdown-content class="f-dropdown">
    <li>
        <g:if test="${jobOfferInstance?.willExpire > new Date()}">
            <g:link controller="adminJobOffer" action="jobExpire" class="ajaxCall"
                    tableAffectCol="6"
                    params="[id: jobOfferInstance.id]">
                <g:message code="jobOffer.expire.label"/>
            </g:link>
        </g:if>
    %{--  <g:else>
          <g:link controller="admin" action="jobPublish" class="ajaxCall"    tableAffectCol="6"
                  params="[id: jobOfferInstance.id]">
                  <g:message code="jobOffer.deactivate.label" />
          </g:link>
      </g:else>--}%

    <li>
        <g:link controller="adminJobOffer" action="editCompanyJobOffer" target="_blank" params="[id: jobOfferInstance.id]">
            <g:message code="jobOffer.edit.label"/>
        </g:link>
    </li>


    <li>
        <g:link controller="adminJobOffer" action="publish" class="confirmIt"   id="${jobOfferInstance.id}"
        >
            <g:message code="company.jobOffer.publish" />

        </g:link>
    </li>


    <li>
        <g:link controller="adminJobOffer" action="publishTop" class="confirmIt"   id="${jobOfferInstance.id}"
        >

            <g:message code="company.jobOffer.publish.as.top" />
        </g:link>
    </li>

    <li>
        <g:link controller="adminJobOffer" action="publicTo" class="confirmIt"   id="${jobOfferInstance.id}"
        >

            <g:message code="company.remote.publish"  />

        </g:link>
    </li>


    <li>
        <g:link controller="adminJobOffer" action="jobDelete" class="ajaxCall" tableAffectCol="all"
                params="[id: jobOfferInstance.id]">
            <g:message code="jobOffer.delete.label"/>
        </g:link>
    </li>

</ul>