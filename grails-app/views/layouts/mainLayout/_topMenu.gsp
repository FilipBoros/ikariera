<div class="clear-fix upper-right-menu ">
    <div class="right">
        <g:link mapping="about">
            <g:message code="upperRightMenu.about.label"/>
        </g:link>
        |
        <g:link mapping="faq">
            <g:message code="upperRightMenu.Faq.label"/>
        </g:link>

        |
        <g:link mapping="termOfUse">
            <g:message code="upperRightMenu.termOfUse.label"/>
        </g:link>
        |
        <g:link mapping="contact">
            <g:message code="upperRightMenu.contact.label"/>
        </g:link>

        |


        <a href="#" data-dropdown="drop1" data-options="is_hover:true" >
            ${message(code: 'layout.main.choseLanguage.label')}
        </a>


        <ul id="drop1" class="f-dropdown" data-dropdown-content>
            <li>
                <g:link controller="index" action="index"
                        params="${[lang: 'cz', targetUri: (request.forwardURI - request.contextPath)]}">
                    <g:message code="language.label.cz"/></g:link>
            </li>
            <li>
                <g:link controller="index" action="index"
                        params="${[lang: 'en', targetUri: (request.forwardURI - request.contextPath)]}">
                    <g:message code="language.label.en"/></g:link>
            </li>
        </ul>


    </div>
</div>






