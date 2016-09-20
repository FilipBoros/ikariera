<style>

.logosBackground {
    background-color: #ffffff;
    -webkit-box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.75);
    -moz-box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.75);
    box-shadow: 2px 2px 2px 0px rgba(0, 0, 0, 0.75);
}

</style>

<div style="background-color: white">

    <g:if test="${generalPartnerList || coverPartnerList}">
        <div class="row  ">

            <div class="medium-5 large-5 columns">

                <g:if test="${generalPartnerList}">

                    <h4><g:message code="partners.general.label" /> </h4>
                    <hr>
                    <ul class="small-block-grid-1 medium-block-grid-1 large-block-grid-1">

                        <g:each in="${generalPartnerList}" var="generalPartner">
                            <li>
                                <div style="padding: 10px 30px">
                                    <a href="${generalPartner?.link}" title="${generalPartner?.name}"><img
                                            src="${createLink(absolute: true, controller: 'media', action: 'picture', id: generalPartner?.logo?.id)}"/>
                                    </a>

                                </div>
                            </li>
                        </g:each>

                    </ul>

                </g:if>

            </div>


            <div class="medium-5 medium-offset-2 large-5 large-offset-2 columns">

                <g:if test="${coverPartnerList}">

                    <h4><g:message code="partners.cover.label" /> </h4>

                    <hr>

                    <ul class="small-block-grid-2 medium-block-grid-1 large-block-grid-1">

                        <g:each in="${coverPartnerList}" var="coverPartner">
                            <li>
                                <div style="padding: 10px 20px">
                                    <a href="${coverPartner?.link}" title="${coverPartner?.name}">
                                        <img
                                                src="${createLink(absolute: true, controller: 'media', action: 'picture', id: coverPartner?.logo?.id)}"/>
                                    </a>
                                </div>
                            </li>
                        </g:each>

                    </ul>

                </g:if>

            </div>

        </div>

    </g:if>



    <g:if test="${medialPartnerList || supportPartnerList}">
        <div class="row ">
            <div class="large-7 columns">

                <g:if test="${medialPartnerList}">

                    <h4><g:message code="partner.medial.label" /> </h4>

                    <hr>

                    <ul class="small-block-grid-3 medium-block-grid-4 large-block-grid-3">
                        <g:each in="${medialPartnerList}" var="medialPartner">
                            <li>
                                <div style="padding: 10px 20px">
                                    <a href="${medialPartner?.link}" title="${medialPartner?.name}"><img
                                            src="${createLink(absolute: true, controller: 'media', action: 'picture', id: medialPartner?.logo?.id)}"/>
                                    </a>
                                </div>
                            </li>
                        </g:each>
                    </ul>

                </g:if>

            </div>


            <div class="large-5 columns">

                <g:if test="${supportPartnerList}">

                    <h4><g:message code="partner.support.label" /> </h4>

                    <hr>

                    <ul class="small-block-grid-3 medium-block-grid-4 large-block-grid-2">
                        <g:each in="${supportPartnerList}" var="supportPartner">
                            <li>
                                <div style="padding: 10px 20px">
                                    <a href="${supportPartner?.link}" title="${supportPartner?.name}"><img
                                            src="${createLink(absolute: true, controller: 'media', action: 'picture', id: supportPartner?.logo?.id)}"/>
                                    </a>
                                </div>
                            </li>
                        </g:each>
                    </ul>

                </g:if>

            </div>

        </div>

    </g:if>
    <g:if test="${partnerList}">

        <div class="row ">
            <div class="large-12 columns">

                <h4> <g:message code="partner.portal.label" />  </h4>

                <hr>

                <ul class="small-block-grid-3 medium-block-grid-4 large-block-grid-5">
                    <g:each in="${partnerList}" var="partner">
                        <li>
                            <div style="padding: 10px 20px">
                                <a href="${partner?.link}" title="${partner?.name}"><img
                                        src="${createLink(absolute: true, controller: 'media', action: 'picture', id: partner?.logo?.id)}"/>
                                </a>
                            </div>
                        </li>
                    </g:each>
                </ul>

            </div>

        </div>

    </g:if>
</div>