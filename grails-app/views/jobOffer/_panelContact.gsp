<div class="panel detailInfo">

    <h3><g:message code="contact.label"/></h3>

    %{--<g:if test="${jobOfferInstance?.contactDetails}">--}%

    <hr>

    <div>
        <i class="fi-torso-business"></i>
        <h5>
            <g:message code="contact.label"/>

        </h5>


        <p>

            ${jobOfferInstance?.contactDetails?.name}

            <g:if test="${jobOfferInstance?.contactDetails?.positionInCompany}">
                <br/>
                <em>${jobOfferInstance?.contactDetails?.positionInCompany}</em>

            </g:if>

        </p>
    </div>

%{--</g:if>--}%


    <g:if test="${jobOfferInstance?.contactDetails?.email}">
        <sec:ifLoggedIn>
            <p class="email contact-detail">
                <g:link controller="jobOffer" action="getEmailAddress" id="${jobOfferInstance?.id}"
                        class="getContactDetail ">

                    <g:message code="jobOffer.getEmailAddress"/>

                </g:link>
            </p>

        </sec:ifLoggedIn>

        <sec:ifNotLoggedIn>
        %{--LogIn to see contact detail--}%
        %{--<p class="email contact-detail">
            <g:link controller="jobOffer" action="getEmailAddress" class="getContactDetail "
                    id="${jobOfferInstance?.id}">

                <g:message code="jobOffer.getEmailAddress"/>

            </g:link>
        </p>--}%
        </sec:ifNotLoggedIn>

    </g:if>


    <g:if test="${jobOfferInstance?.contactDetails?.telephone}">

        <sec:ifLoggedIn>
            <p class="email contact-detail">
                <g:link controller="jobOffer" action="getTelephoneNumber" id="${jobOfferInstance?.id}"
                        class="getContactDetail ">

                    <g:message code="jobOffer.getTelephoneNumber"/>

                </g:link>
            </p>
        </sec:ifLoggedIn>

        <sec:ifNotLoggedIn>
        %{--<p class="email contact-detail">
            <g:link class="getContactDetail" controller="jobOffer" action="getTelephoneNumber"
                    id="${jobOfferInstance?.id}">

                <g:message code="jobOffer.getTelephoneNumber"/>

            </g:link>
        </p>--}%
        </sec:ifNotLoggedIn>

    </g:if>
    <sec:ifNotGranted roles="ROLE_COMPANY,ROLE_ADMIN,ROLE_SUPER_ADMIN">
    <a class="button expand success" data-reveal-id="contactModal" data-reveal>

        <span class="fi-mail"></span>

        <g:message code="reply.to.jobOffer"/>

    </a>


        <div id="contactModal" class="reveal-modal" data-reveal>

            <div class="row">
                <div class="large-12 columns">
                    <h2><g:message code="do.contact.company"/></h2>

                    <g:render template="messageForm"/>
                </div>
            </div>
        </div>

    </sec:ifNotGranted>

</div>
