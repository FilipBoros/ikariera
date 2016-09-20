<g:each in="${studentInstanceList}" status="i" var="studentInstance">

    <div class="row">

        <div class="small-4 medium-2 columns">
            <div>
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
            </div>
            <br/>

            <div style="text-align: center">

                <g:link controller="companyAccountCvSearch" action="detail" class="button secondary  radius expand"
                        id="${studentInstance.id}">
                    <g:message code="company.findStudent.showProfile.label"/>
                </g:link>




                <g:render template="buttonsMenu" model="[studentInstance: studentInstance]"/>

            </div>

        </div>


        <div class="small-4 medium-8 columns">

            <div class="row">
                <div class="large-12 columns">

                    <div class="panel">

                        <g:link controller="companyAccountCvSearch" action="detail" id="${studentInstance?.id}">

                            <h3>${studentInstance?.user?.firstName}  ${studentInstance?.user?.lastName}</h3>
                        </g:link>


                    %{--               <g:if test="${studentInstance?.birthday}" >

                                       <g:yearsBetween startDate="${new Date()-8000}" endDate="${new Date()}" />
                                   </g:if>--}%

                    </div>

                </div>

            </div>

            <div class="row">

                <div class="large-12 columns">

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





                    <table class="table">
                        <thead>
                        <tr>
                        <th><g:message code="student.language.table.label"/></th>
                        <th><g:message code="student.level.label"/></th>
                        </thead>

                        <tbody>
                        <g:each in="${studentInstance?.languages}" var="language">

                            <tr>
                                <td><g:message code="${language?.languageType?.i18NameFull}"/></td>
                                <td><g:message code="${language?.level?.i18NameFull}"/></td>
                            </tr>

                        </g:each>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>

        <div class="large-2 columns">

            %{--
                              <g:if test="${studentInstance?.cvPath}">
                          <a href="${createLinkTo( dir: 'student/cv/'+studentInstance?.userId, file: studentInstance?.cvPath, absolute: true)}" target="_blank">
                                  ${studentInstance?.cvPath}
                              </a>

                          </g:if>--}%


            <h5><g:message code="company.jobategory.interrest"/></h5>

            <p style="font-size: 85%">

                <g:each in="${studentInstance?.jobCategories}" var="jobCategory">

                    ${message(code: jobCategory.i18NameFull)}, <br/>

                </g:each>

            </p>

        </div>

    </div>

    <hr>
</g:each>



<g:paginateFoundation total="${studentInstanceTotal}" params="${params}"
                      maxsteps="8"
                      next="${message(code: "list.paginate.next")}"
                      prev="${message(code: "list.paginate.prev")}"/>




