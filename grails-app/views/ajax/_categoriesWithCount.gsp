<h4>${message(code: "categories.header")}</h4>
<hr>


<ul style="font-size: 85%; margin: 0; list-style: none;">
    <g:each in="${jobOffersCategoriesCountList}"
            status="i" var="jobCategoryInstance">

        <g:if test="${jobCategoryInstance.count != 0}">
            <li>

                <g:link controller="jobOffer" action="index" params="[jobCategories: jobCategoryInstance.id]">
                    <h8>
                        ${message(code: jobCategoryInstance.i18NameFull)}
                        [${jobCategoryInstance.count}]
                    </h8>
                </g:link>
            </li>

        </g:if>

    </g:each>

</ul>