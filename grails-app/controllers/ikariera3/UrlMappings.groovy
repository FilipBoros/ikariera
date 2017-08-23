package ikariera3

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {

            constraints {
                // apply constraints here
            }
        }

        "/katalog"  (
                controller : 'catalog',
                action : 'index'
        )


        name skarticles: "/clanky-a-rady" (
             controller: "articles",
        )

        name skarticle : "/clanky-a-rady/$id" (
             controller: "articles",
             action: 'detail'
        )

        name czarticles: "/clanky-a-rady" (
                controller: "articles",
        )

        name czarticle : "/clanky-a-rady/$id" (
                controller: "articles",
                action: 'detail'
        )

        name enarticles :"/tips-and-articles"(
                controller: "articles",
        )
        name enarticle : "/tips-and-articles/$id"(
                controller: "articles",
                action: 'detail'
        )


        "/veletrhy-ikariera" (
                controller: "index",
                action: "veleterhy"
        )


       name skjobOffers : "/ponuky-prace" (
                controller : 'jobOffer',
                action : 'index'
        )

        "/doporucene-nabidky-prace" ( /*TODO kde je to pouzite? Mozno to nebude fungovat a bude to treba dorobit podla ostatnych*/
                controller : 'jobOffer',
                action : 'recommendedJobOffers'
        )

        name skjobOffer : "/ponuky-prace/$id" (
                controller : 'jobOffer',
                action : 'detail'
        )

        name czjobOffers :"/nabidky-prace" (
                controller : 'jobOffer',
                action : 'index'
        )

        "/doporucene-nabidky-prace" (
                controller : 'jobOffer',
                action : 'recommendedJobOffers'
        )

        name czjobOffer :"/nabidky-prace/$id" (
                controller : 'jobOffer',
                action : 'detail'
        )

        name enjobOffers :"/job-offers"(
                controller : 'jobOffer',
                action : 'index'
        )

        "/recommended-job-offers"(
                controller : 'jobOffer',
                action : 'recommendedJobOffers'
        )

        name enjobOffer :"/job-offers/$id"(
                controller: "jobOffer",
                action: "detail"
        )




        name passwordReset :"/password-reset/$token" (
                controller : "password",
                action : "resetPassword"

        )



        "/confirm-user/$token" (
                controller : "userActivation",
                action: "enableUser"

        )

        "/confirm-company-user/$token" (
                controller: "userActivation",
                action: "enableCompanyUser"
        )

        "/user-logout-email/$token" (
                controller : "signOutInfoEmail",
                action: "logoutEmail"

        )


        "/confiuser/$token" (
                controller : "userActivation",
                action: "enableCompanyUser"

        )

        name companyProfilesSk : "/firemne-profily"(
                controller: "companyProfiles",
        )
        name companyProfileSk :"/firemny-profil/$id"(
                controller: "companyProfiles",
                action: 'detail'
        )
        name companyProfilesCz : "/firemni-profily"(
                controller: "companyProfiles",
        )
        name companyProfileCz :"/firemni-profil/$id"(
                controller: "companyProfiles",
                action: 'detail'
        )
        name companyProfilesEn :"/companies"(
                controller: "companyProfiles",
        )
        name companyProfileEn :"/company/$id"(
                controller: "companyProfiles",
                action: 'detail'
        )

        name fullTextSearchTest:"/test"(
                controller: "jobOffer",
                action: "fullTextFilter"
        )



        "/"(controller: "index")






        "500"(controller: "error", action:"serverError" )
        "404"(controller:"error", action:"notFound")

        // "500"(controller: "error", action: "denied", exception: AccessDeniedException)
        "403"(controller: "error", action: "denied")


        "/student-account-message" (
                controller: "studentAccountMessage",
                action: "sendMessageToCompany"

        )

        "/registrace" (
                controller: "student",
                action: "registration"

        )


        name companyRegistraion : "/registrace-spolecnosti" (
                controller: "company",
                action: "registration"
        )

       name companySaveRegistraion : "/company/save-registration" (
                controller: "company",
                action: "saveRegistration"
       )

        "/api-companies/?(.${format})?"(controller: "apiCompanies") {
            action = [GET: "list"]

        }

        "/api-company/$id?(.${format})?"(controller: "apiCompanies") {
            action = [GET: "detail"]

        }

        "/api-job-offers/?(.${format})?"(controller: "apiJobOffers") {
            action = [GET: "list"]

        }

        "/api-job-offer/$id?(.${format})?"(controller: "apiJobOffers") {
            action = [GET: "detail"]

        }

        /*name remoteRegistration: "/api-companies"(
         controller: "apiCompanies", action: "index"
        )*/
/*
        name remoteRegistration: "/api-companies/$token"(
         controller: "apiCompanies", action: "detail"
        )*/

        "/api-remote-registration/user/$token"(
                controller: "remoteRegister", action: "save"
        )

        "/api-remote-registration/file/$id/$token"(
                controller: "remoteRegister", action: "saveFile"
        )

        "/api-remote-job-offer/$apikey?/$id?"(
                controller: "jobOffer", action: [POST:"jobOfferApi", PUT:"publishOfferAPi"]
        )

        "/admin-remote-register"(
                controller: "adminRemoteRegister"
        )

        "/admin-credits"(
                controller: "adminCredits"
        )

        "/admin-banner"(
                controller: "adminBanner"
        )

        "/admin-faq"(
                controller: "adminFaq"
        )

        "/admin-catalogue"(
                controller: "adminCatalogue"
        )

        "/admin-company"(
                controller: "adminCompany"
        )

        "/admin-hero"(
                controller: "adminHeroImage"
        )

        "/admin"(
                controller: "admin"
        )

        "/admin-services"(
                controller: "adminServices"
        )


        "/admin-partners"(
                controller: "adminPartner"
        )


        "/admin-media"(
                controller: "adminMedia"
        )

        "/admin-statistics"(
                controller: "statistics"
        )


        "/api-remote-statistics/$token"(
                controller: "statistics", action: "index"
        )



        "/admin-main-logo"(
                controller: "adminMainLogo"
        )

        "/admin-advertisement"(
                controller: "adminAdvertisement"
        )

        "/admin-mobile"(
                controller: "adminMobile"
        )

        "/admin-job-offer"(
                controller: "adminJobOffer"
        )

        "/admin-contact"(
                controller: "adminContact"
        )

        "/admin-email"(
                controller: "adminEmail"
        )
        "/admin-constants"(
                controller: "adminConstants"
        )

        "/admin-user-admin"(
                controller: "adminUserAdmin"
        )

        "/admin-user-company"(
                controller: "adminUserCompany"
        )

        "/admin-user-all"(
                controller: "adminUserAll"
        )

        "/admin-user-student"(
                controller: "adminUserStudent"
        )

        "/admin-api-key"(
                controller: "adminApiKey"
        )

        "/faq"(
                controller: "faq"
        )


        "/about"(
                controller: "about",
                action: "about"
        )


        "/term-of-use"(
                controller: "about",
                action: "termOfUse"
        )


        "/contact"(
                controller: "about",
                action: "contact"
        )


        "/company-account-email-search/$emailId/"(
                controller: "companyAccountEmailSearch",
                action: "index"
        )

        "/media/$action?/$id?(.$format)?"(controller: "media") {

        }



        "/student-account-photo/$action?/$id?(.$format)?"(controller: "studentAccountPhoto") {

        }




        "/simple-registration/$id"(controller: "simpleRegistration") {

        }

        "/simple-registration/save"(controller: "simpleRegistration", action: 'save') {

        }


        "/simple-registration/registration-complete/$id"(controller: "simpleRegistration", action: "registrationComplete") {

        }




        "/user-info-emails-sign-out"(controller: "signOutInfoEmail" , action: "index"){

        }

        "/user-info-emails-sign-out/send"(controller: "signOutInfoEmail" , action: "signOut"){

        }


        "/student-account-university/save"(controller: "studentAccountUniversity", action: 'save') {

        }

        "/company-account-profile"(controller: "companyAccountController", action: "index"){

        }
    }

    /*

        name story: "/news/$id/$headline" {
        controller = "news"
        action = "show"
    }

    That way you could create your urls with the headline in them and the mapping would still work. You of course don't actually have to use the headline parameter that will appear in your controller. The example above uses a named URL mapping so you can then say:

    ${createLink(mapping: "story", params: [id: 102, headline: 'this-is-the-hottest-news-today'])}

    You may also be interested in this plugin for creating canonical urls - http://www.grails.org/plugin

    */
}
