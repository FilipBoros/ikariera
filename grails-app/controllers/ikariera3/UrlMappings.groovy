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


        "/clanky-a-rady" (
                controller: "articles",
        )
        "/clanky-a-rady/$id" (
                controller: "articles",
                action: 'detail'
        )
        "/tips-and-articles"(
                controller: "articles",
        )
        "/tips-and-articles/$id"(
                controller: "articles",
                action: 'detail'
        )

        "/veletrhy-ikariera" (
                controller: "index",
                action: "veleterhy"
        )

        "/nabidky-prace" (
                controller : 'jobOffer',
                action : 'index'
        )

        "/doporucene-nabidky-prace" (
                controller : 'jobOffer',
                action : 'recommendedJobOffers'
        )

        "/nabidky-prace/$id" (
                controller : 'jobOffer',
                action : 'detail'
        )

        "/job-offers"(
                controller : 'jobOffer',
                action : 'index'
        )

        "/recommended-job-offers"(
                controller : 'jobOffer',
                action : 'recommendedJobOffers'
        )

        "/job-offers/$id"(
                controller: "jobOffer",
                action: "detail"
        )



        "/password-reset/$token" (
                controller : "password",
                action : "resetPassword"

        )



        "/confirm-user/$token" (
                controller : "userActivation",
                action: "enableUser"

        )

        "/user-logout-email/$token" (
                controller : "signOutInfoEmail",
                action: "logoutEmail"

        )


        "/confiuser/$token" (
                controller : "userActivation",
                action: "enableCompanyUser"

        )





        "/firemni-profily"(
                controller: "companyProfiles",
        )
        "/firemni-profily/$id"(
                controller: "companyProfiles",
                action: 'detail'
        )
        "/companies"(
                controller: "companyProfiles",
        )
        "/companies/$id"(
                controller: "companyProfiles",
                action: 'detail'
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


        "/registrace-spolecnosti" (
                controller: "company",
                action: "registration"
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
