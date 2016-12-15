<style type="text/css">
html, body {
    height: 100%;
    overflow: hidden;
}

.wrapper {
    overflow-y: scroll;
    height: calc(100% - 1.6em);
}

.sticky-footer {
    position: static;
    bottom: 0;
    height: 1.6em;
    background-color: #d9d9d9;
    /*  background: -moz-linear-gradient(top, #ffffff, #808080);*/
}

</style>


<div class="sticky-footer">

    <div class="row">

        <div class="large-12 columns">

            %{-- #717171 --}%



            <span style="font-size: 12px;margin-top: 2px; color:#666; padding-right: 5px" >
                <g:message code="manual.language.selector"/>
            </span>

            <a href="#" data-options="align:top" data-dropdown="dropLang" class="button dropdown tiny secondary" >
                <g:if test="${g.message( code:  org.springframework.web.servlet.support.RequestContextUtils.getLocale(request)).equals("cs_CZ")}">
                    Čestina
                </g:if>
                <g:elseif test="${g.message( code:  org.springframework.web.servlet.support.RequestContextUtils.getLocale(request)).equals("en_US")}">
                    English
                </g:elseif>
                <g:elseif test="${g.message( code:  org.springframework.web.servlet.support.RequestContextUtils.getLocale(request)).equals("sk")}">
                    Slovenčina
                </g:elseif>

            </a>
            <ul id="dropLang" class="tiny f-dropdown" data-dropdown-content>
                <li>
                    <g:link params="[lang: 'cs_CZ', f: request.forwardURI]" controller="index"
                            action="fakeAction"><g:message
                            code="language.czech"/></g:link>
                </li>

                <li>

                    <g:link params="[lang: 'en_US', f: request.forwardURI]" controller="index"
                            action="fakeAction"><g:message
                            code="language.english"/></g:link>
                </li>
                <li>

                    <g:link params="[lang: 'sk', f: request.forwardURI]" controller="index"
                            action="fakeAction"><g:message
                            code="language.slovak"/></g:link>
                </li>
            </ul>




            <p style="float: right; font-size: 12px;margin-top: 2px; color:#666"><g:message code="copyright.label"/>

            2007 - <g:formatDate format="yyyy" date="${new Date()}"/>

            <p>
        </div>
    </div>
</div>