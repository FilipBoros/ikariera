<div class="articleItem">

    <g:link mapping="${message(code: 'arcticles.link')}" id="${articleInstance.id}">
        <h3>
            %{--<g:link controller="articles" action="detail"
                    id="${articleInstance.id}">${articleInstance?.header}
            </g:link>--}%

            ${articleInstance?.header}

        </h3>

    </g:link>

    <g:link mapping="${message(code: 'companies.link')}" id="${articleInstance?.company?.id}">
        <h5 style="font-weight: 300">${articleInstance?.company?.companyName}</h5>

    </g:link>
    <p>

        <g:if test="${articleInstance.leadingText}">

        %{--<g:stripHtmlTags text="${articleInstance?.leadingText}"/>--}%
            ${raw(articleInstance?.leadingText)}
        </g:if>
        <g:else>
        %{--<g:textStripTag param1="${articleInstance.bodyText}" param2="0" param3="550"
                        param4="true"/>--}%
            <g:if test="${articleInstance.bodyText.size() < 100}">
                ${raw(articleInstance?.bodyText)}
            </g:if>
            <g:else>
                ${raw(articleInstance?.bodyText.substring(0, 100))}...
            </g:else>
        </g:else>
    </p>


</div>