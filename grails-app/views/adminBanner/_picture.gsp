<g:if test="${bannerInstance?.link}">

    <li style="width: 300px; height: 50px; overflow: hidden; padding-top: 5px;text-align: center ">
        <div style="height: 50px; text-align: center">
            %{--<g:link class="th"
                    url="${createLink(controller: 'adminHeroImage', action: 'getMedia', id: heroImageInstance?.imageLink)}">--}%


            <img style=" text-align: center; height: 50px; width: 300px; display: block;" src="${createLink(controller: 'publicBanner', action: "getSomeBanner", id: bannerInstance?.id, absolute: true)}"/>
            %{--</g:link>--}%

        </div>
    </li>

</g:if>
