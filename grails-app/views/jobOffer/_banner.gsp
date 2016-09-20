<g:if test="${position == 1 && banner}">
    <div id="banner"
         style="border-bottom: 1px solid rgba(0, 0, 0, 0.05); margin: 10px 0px 0px 0px ;height: 100px">
        <a href="${banner?.link}" target="_blank">
            <img style="height: 90px; width: 720px; margin: auto; display: block;"
                 src="${createLink(controller: 'publicBanner', action: "getSomeBanner", id: banner?.id, absolute: true)}"/>
        </a>
    </div>
</g:if>



