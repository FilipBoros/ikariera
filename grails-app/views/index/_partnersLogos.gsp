<g:if test="${partnerInstanceList}">

    <div class="row ">
        <div class="large-12 columns">

            <h4><g:message code="partner.portal.label" /> </h4>

            <hr>

            <ul class="small-block-grid-3 medium-block-grid-4 large-block-grid-5">
                <g:each in="${partnerInstanceList}" var="partnerInstance">
                    <li>
                        <div style="padding: 10px 20px">


                                <a href="${partnerInstance?.link}" title="${partnerInstance?.name}">
                                    <img src="${createLink(absolute: true, controller: 'media', action: 'getMedia', id: partnerInstance?.logo?.newFilename)}"/>
                                </a>
                            </a>
                        </div>
                    </li>
                </g:each>
            </ul>

        </div>

    </div>

</g:if>


