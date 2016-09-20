<g:if test="${heroImageInstance?.thumbnailLink}">

    <li style="width: 120px; height: 100px; overflow: hidden; padding-top: 5px">
        <div style="height: 100px">
            %{--<g:link class="th"
                    url="${createLink(controller: 'adminHeroImage', action: 'getMedia', id: heroImageInstance?.imageLink)}">--}%

                <img src="${createLink(controller: 'media', action: 'getHeroMedia', id: heroImageInstance?.thumbnailLink)}"/>

            %{--</g:link>--}%

        </div>
    </li>

</g:if>