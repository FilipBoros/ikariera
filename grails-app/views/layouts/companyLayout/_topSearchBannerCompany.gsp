
        <!-- Add job -->
        <div class="button-create-jobOffer">
            <g:link controller="companyAccountJobOffers" action="create">
                ${message(code: 'topSearchBannerCompany.addJob.label')}
            </g:link>
        </div>

        <!-- Watch dog (eye + msg + count) CHANGE IN GRAPHIC-->
        <div class="div-profile-watch-dog">
            <g:link controller="jobOffer" action="favoriteFilter">
                ${message(code: 'topSearchBannerStudent.watchDog.label')}
                <span class="watchDogTotalNumber">${cookie.get(name: "ikarieraWatchDogTotal_" + sec.loggedInUserInfo(field: 'id')) ?: 0}</span>
            </g:link>
        </div>


        <br/>


        <!-- Credits -->
        <div class="div-account-profile-progress-bar">
            <div class="div-profile-progress-company-text">

                ${message(code: 'topSearchBannerCompany.credits.label', args: [loggedUserCompanyCredits(id: sec.loggedInUserInfo(field: 'id'))])}
            </div>
        </div>

        <!-- Edit profile-->
        <div class="button-play-and-win">
            <g:link controller="companyAccountUser" action="index">
                ${message(code: 'topSearchBannerCompany.edit.label')}
            </g:link>
        </div>

