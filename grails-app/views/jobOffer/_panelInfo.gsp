<div class="panel detailInfo">

    <g:if test="${jobOfferInstance?.topPos}">

        <div>
            <i class="fi-star" style="font-size: 25px; padding-right: 10px;"></i>

            <h3><g:message code="jobOffer.topPosition.label"/></h3>

            <hr>
        </div>

    </g:if>


    <div>
        <i class="fi-bookmark"></i>
        <h5><g:message code="jobOffer.jobOfferType.label"/></h5>

        <p>${message(code: jobOfferInstance?.jobOfferType?.i18NameFull)}

            <g:if test="${jobOfferInstance?.graduatePosition}">
                <br>
                <g:message code="jobOffer.forStudents.label"/>
            </g:if>

        </p>

        <hr>
    </div>

    <g:if test="${jobOfferInstance?.jobOfferPositionLevel}">
        <div>
            <i class="fi-bookmark"></i>
            <h5><g:message code="jobOffer.positionLevel.label"/></h5>

            <p>${message(code: jobOfferInstance?.jobOfferPositionLevel?.i18NameFull)}</p>

            <hr>
        </div>
    </g:if>

    <g:if test="${jobOfferInstance?.jobOfferPositionLevel}">
        <div>
            <i class="fi-bookmark"></i>
            <h5><g:message code="jobOffer.position.label"/></h5>

            <p>${message(code: jobOfferInstance?.jobOfferPosition?.i18NameFull)}</p>

            <hr>
        </div>
    </g:if>

    <div>
        <i class="fi-calendar"></i>
        <h5>
            <g:message code="jobOffer.datePublished.label"/>

        </h5>

        <p>
            <g:daysToNow date="${jobOfferInstance?.datePublished}"/>
        </p>

        <hr>

    </div>


    <div>
        <i class="fi-wrench"></i>
        <h5>
            <g:message code="nabidkyDiplomovychPraci.label"/>

        </h5>


        <p>
            <g:each in="${jobOfferInstance?.jobCategories?.sort { it.posOrder }}" var="cat">

                <g:message code="${cat?.i18NameFull}"/>
                <br/>

            </g:each>
        </p>

    </div>

    <hr>

    <div>

        <i class="fi-map"></i>
        <h5>
            <g:message code="locality.label"/>

        </h5>


        <p>

            <g:message code="${jobOfferInstance?.positionLocality?.i18NameFull}"/>
        </p>

    </div>


    <hr>


    <g:if test="${jobOfferInstance?.jobStartDate}">
        <div>
            <i class="fi-check"></i>
            <h5>
                <g:message code="article.startDay.label"/>

            </h5>


            <p>
                <g:formatDate date="${jobOfferInstance?.jobStartDate}" format="MMMM yyyy"/>
            </p>

        </div>

        <hr>

    </g:if>

    <g:if test="${jobOfferInstance?.requieredLanguages}">

        <div>
            <i class="fi-sound"></i>
            <h5>
                <g:message code="reqLanguageSkills.label"/>

            </h5>

            <p>

                <g:each in="${jobOfferInstance?.requieredLanguages}" var="reqLanguage" status="i">
                    <g:message code="${reqLanguage?.getI18NameFull()}"/><g:if
                        test="${i < jobOfferInstance.requieredLanguages.size() - 1}">,</g:if>
                </g:each>

            </p>

        </div>

    </g:if>

</div>
