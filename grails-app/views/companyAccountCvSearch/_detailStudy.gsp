
<h4 class="subheader">${message(code: 'student.education')}</h4>
<table class="table">
    <thead>
    <tr>
        <th>${message(code: 'university.endYear.label')}</th>
        <th>${message(code: 'university.title.label')}</th>
        <th>${message(code: 'university.category.label')}</th>
        <th>${message(code: 'university.specialization.label')}</th>
        <th>${message(code: 'university.univerity.label')}</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${studentInstance?.educations}" var="university">
        <tr>
            <td><g:formatDate format="yyyy" date="${university?.endingYear}"/></td>
            <td>${university?.titleAwarded}</td>
            <td><g:message code="${university?.studyCategory?.i18NameFull}"/></td>
            <td>${university?.specialization}</td>

            <td>

                <g:if test="${university?.university?.id == 1}">
                    ${university?.otherUniversity}
                </g:if>
                <g:else>
                    <g:message code="${university?.university?.i18NameFull}"/>

                </g:else>

            </td>
        </tr>
    </g:each>
    </tbody>
</table>



<h4 class="subheader">${message(code: 'student.language.label')}
</h4>

<table class="table">

    <thead>
    <tr>
        <th><g:message code="student.language.label" /></th>
        <th><g:message code="student.level.label" /> </th>
    </thead>

    <tbody>

    <g:each in="${studentInstance?.languages}" var="language">

        <tr>
            <td><g:message code="${language?.languageType?.i18NameFull}"/></td>
            <td> <g:message code="${language?.level?.i18NameFull}"/></td>
        </tr>

    </g:each>

    </tbody>
</table>







<g:if test="${studentInstance?.certificates}">

    <h4 class="subheader">${message(code: 'student.h2.knowledge.certificates')}:</h4>
    <table class="table">
        <thead>
        <tr>
            <th>${message(code: 'knowledge.name.label')}</th>
            <th>${message(code: 'knowledge.level.label')}</th>
            <th>${message(code: 'knowledge.description.label')}</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${studentInstance?.certificates}" var="know">
            <tr>
                <td>${know?.name}</td>
                <td>${know?.level}</td>
                <td>${know?.description}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>



<g:if test="${studentInstance?.experiences}">

    <h4 class="subheader">${message(code: 'student.workingExperience.label')}:</h4>

    <g:each in="${studentInstance?.experiences}" var="work">
        <div>

            <h3>${work?.occupation}</h3>
            <h4>${work?.employer}</h4>
            <h5 class="subheader">${work?.periodStart}  -  ${work?.periodEnd}</h5>

            <p>${work?.activities}</p>
        </div>
    </g:each>

</g:if>




<g:if test="${studentInstance?.personalProfile}">

    <h4 class="subheader">${message(code: 'student.personalProfile.label')}:</h4>

    <p>
        <g:stripHtmlTags text="${studentInstance?.personalProfile}"/>
    </p>

</g:if>


<g:render template="buttonsMenu" model="[studentInstance: studentInstance]"/>