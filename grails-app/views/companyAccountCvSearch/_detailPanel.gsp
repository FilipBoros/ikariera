<div class="panel">

    <g:if test="${studentInstance?.photo}">
        <g:link controller="companyAccountCvSearch" action="detail" id="${studentInstance?.id}">
            <img src="${createLink(controller: 'studentAccountPhoto', action: "getMedia", id: studentInstance?.photo?.newFilename, absolute: true)}"/>
        </g:link>
    </g:if>
    <g:else>
        <g:link controller="companyAccountCvSearch" action="detail" id="${studentInstance?.id}">
            <img class="th" src="${resource(dir: '/images/ikariera', file: 'no-profile-image2.png')}"/>

        </g:link>
    </g:else>




    <hr>


    <h5 style="text-align: center">
        ${studentInstance?.user?.username}
    </h5>
    <g:if test="${studentInstance?.telephone}">
        <hr>
        <h5>${message(code: 'student.telephone.label')}</h5>


        <p>${studentInstance?.telephone}
        </p>
    </g:if>


    <g:if test="${studentInstance?.birthday}">
        <hr>
        <h5>${message(code: 'student.yearOfBirth.label')}</h5>


        <p><g:formatDate date="${studentInstance?.birthday}" format="yyyy"/>
        </p>
    </g:if>


    <g:if test="${studentInstance?.birthplace}">
        <hr>
        <h5>${message(code: 'student.placeOfBirth.label')}</h5>


        <p>${studentInstance?.birthplace}
        </p>
    </g:if>


    <g:if test="${studentInstance?.addressStreet}">
        <hr>
        <h5>${message(code: 'student.address.label')}</h5>

        <p>

            <g:if test="${studentInstance?.addressStreet}">${studentInstance?.addressStreet} <br/></g:if>
            <g:if test="${studentInstance?.addressCity}">${studentInstance?.addressCity} <br/></g:if>
            <g:if test="${studentInstance?.addressCountry}">
                <g:message code="${studentInstance?.addressCountry?.i18NameFull}"/>
            </g:if>

        </p>
    </g:if>



    %{--<g:if test="${studentInstance?.cv}">--}%
        %{--<hr>--}%

        %{--<a href="${createLinkTo(dir: 'student/cv/' + studentInstance?.userId, file: studentInstance?.cv, absolute: true)}"--}%
           %{--target="_blank">--}%

            %{--<span class="pdf" style="margin:  0 auto;"></span>--}%
            %{--<g:message code="company.cvSearch.download.CV.label"/>--}%
        %{--</a>--}%
    %{--</g:if>--}%


    <hr>
    <h5>${message(code: 'student.interestedIn.label')}
    </h5>
    <ul class="no-bullet">
        <g:each in="${studentInstance?.jobCategories}" var="jobCategory">
            <li>
                <g:message code="${jobCategory.i18NameFull}"/>
            </li>
        </g:each>
    </ul>

</div>