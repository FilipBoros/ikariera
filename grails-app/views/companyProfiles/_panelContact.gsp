<sec:ifAnyGranted roles="ROLE_STUDENT">

    <div class="panel detailInfo">

        <h3><g:message code="contact.label"/></h3>

        <g:if test="${companyInstance?.publicEmail}">





            <p>

                ${companyInstance?.publicEmail}

            </p>


        </g:if>




            <hr>




            <a class="button expand success" data-reveal-id="contactModal" data-reveal>

                <span class="fi-mail"></span>

            <g:message code="message.company.send"/>

        </a>


        <div id="contactModal" class="reveal-modal" data-reveal>

            <div class="row">
                <div class="large-12 columns">
                    <h2><g:message code="do.contact.company"/></h2>

                    <g:render template="messageForm"/>
                </div>
            </div>
        </div>

    </div>

</sec:ifAnyGranted>