
        <!-- Upload CV -->
        <div class="button-create-jobOffer">
            <g:link controller="studentAccount" action="uploadCv">
                ${message(code: 'topSearchBannerStudent.addCV.label')}
            </g:link>
        </div>


        <!-- Watch dog (eye + msg + count) CHANGE IN GRAPHIC-->
        <div class="div-profile-watch-dog">
            <g:link controller="jobOffer" action="favoriteFilter">
                ${message(code: 'topSearchBannerStudent.watchDog.label')}
                <span class="watchDogTotalNumber">${cookie.get(name: "ikarieraWatchDogTotal_" + sec.loggedInUserInfo(field: 'id')) ?: 0}</span>
            </g:link>
        </div>

        <!-- Profile completed (pic1 100%, pic2 x%, white text) -->





        <div class="div-profile-progress-bar">
            <div class="div-profile-progress"
                 style="width: ${loggedUserProfileFinished(id: sec.loggedInUserInfo(field: 'id'))}%;"></div>

            <div class="div-profile-progress-text">
                ${message(code: 'topSearchBannerStudent.progressBarr.label', args: [loggedUserProfileFinished(id: sec.loggedInUserInfo(field: 'id'))])}
            </div>
        </div>

        <!-- Modify profile (link)-->
        <div class="link-div-profile-progress-bar">
            <g:link controller="studentAccount" action="editProfile">
                ${message(code: 'topSearchBannerStudent.editAccount.label')}
            </g:link>
        </div>


