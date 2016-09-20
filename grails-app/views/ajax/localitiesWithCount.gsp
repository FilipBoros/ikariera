<h4> ${message(code: "localities.header")} </h4>
<hr>


<ul style="font-size: 85%; margin: 0; list-style: none;">
    <g:each in="${localitiesCountList}"
            status="i" var="localityInstance">

        <g:if test="${localityInstance.count != 0}" >
        <li>

            <g:link controller="jobOffer" action="index" params="[positionLocalities: localityInstance.id]">
                <h8>
                    ${message(code: localityInstance.i18NameFull)}

                 [${localityInstance.count}]
                </h8>
            </g:link>

        </li>

        </g:if>

    </g:each>

</ul>