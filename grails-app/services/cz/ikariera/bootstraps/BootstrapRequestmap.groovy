package cz.ikariera.bootstraps

import cz.ikariera.security.Requestmap


class BootstrapRequestmap {


    public static void init() {


        new Requestmap(url: '/js/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/css/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/images/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/*', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/j_spring_security_switch_user', configAttribute: 'ROLE_SWITCH_USER,IS_AUTHENTICATED_FULLY').save()
        new Requestmap(url: '/security/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()




        new Requestmap(url: '/user-activation/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/password/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/company-api/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/companies-api/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()


        new Requestmap(url: '/catalogue/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/company-profiles/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/error/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/articles/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/job-offer/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        new Requestmap(url: '/menu-item/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        new Requestmap(url: '/about/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/ajax/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        new Requestmap(url: '/index/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/company/company-event/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/public-banner/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/student/registration', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/company/registration', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        new Requestmap(url: '/student/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()


        new Requestmap(url: '/student-account/**', configAttribute: 'ROLE_STUDENT').save()
        new Requestmap(url: '/student-account-cv/**', configAttribute: 'ROLE_STUDENT').save()
        new Requestmap(url: '/student-account-danger-area/**', configAttribute: 'ROLE_STUDENT').save()
        new Requestmap(url: '/student-account-message/**', configAttribute: 'ROLE_STUDENT').save()
        new Requestmap(url: '/student-account-photo/**', configAttribute: 'ROLE_STUDENT').save()

        new Requestmap(url: '/student-account-personal-details/**', configAttribute: 'ROLE_STUDENT').save()

        new Requestmap(url: '/student-account-messages/**', configAttribute: 'ROLE_STUDENT').save()

        new Requestmap(url: '/student-account-education/**', configAttribute: 'ROLE_STUDENT').save()
        new Requestmap(url: '/student-account-contact/**', configAttribute: 'ROLE_STUDENT').save()
        new Requestmap(url: '/student-account-password/**', configAttribute: 'ROLE_STUDENT').save()

        new Requestmap(url: '/student-account-university/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        new Requestmap(url: '/student-account-language/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        new Requestmap(url: '/student-account-certificate/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        new Requestmap(url: '/student-account-experience/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        new Requestmap(url: '/student-account-generate-cv/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak

        new Requestmap(url: '/company-account/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        new Requestmap(url: '/company/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()



        new Requestmap(url: '/company-articles/**', configAttribute: 'ROLE_COMPANY').save()
        //
        new Requestmap(url: '/company-account-logo/**', configAttribute: 'ROLE_COMPANY').save()
        new Requestmap(url: '/company-gallery/**', configAttribute: 'ROLE_COMPANY').save()




        new Requestmap(url: '/company-account-user/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-job-offers/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-email/mailing-list/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        new Requestmap(url: '/company-account-user/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-job-offers/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        new Requestmap(url: '/company-account-email/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-cv-search/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-advanced-profile/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-articles/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        //
        new Requestmap(url: '/company-account-user/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-job-offers/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-articles/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-cv-search/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-email/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        new Requestmap(url: '/company-account-message/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        new Requestmap(url: '/company-account-profile/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        new Requestmap(url: '/company-account-services/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        new Requestmap(url: '/company-account-credits-history/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        new Requestmap(url: '/company-account-email-search/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        new Requestmap(url: '/company-job-offer-api/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save() // Michal Dolnak

        new Requestmap(url: '/company-contacts*//**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        // Michal Dolnak TODO Zakomentuj ma a naviguj na company contacts => nefunguje error

        new Requestmap(url: '/company-account-feedback/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save() // Michal Dolnak

        new Requestmap(url: '/admin-remote-register/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/password-reset/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        new Requestmap(url: '/admin-credits/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-banner/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-faq/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-main-logo/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/admin-logos/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-catalogue/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-company/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-services/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-statistics/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-advertisement/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/admin-mobile/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/admin-job-offer/**', configAttribute: 'ROLE_ADMIN').save()


        new Requestmap(url: '/admin-contact/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-email/**', configAttribute: 'ROLE_ADMIN').save()



        new Requestmap(url: '/admin-constants/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/admin-user-admin/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/admin-user-all/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/admin-user-company/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-user-student/**', configAttribute: 'ROLE_ADMIN').save()



        new Requestmap(url: '/admin-company-service/**', configAttribute: 'ROLE_ADMIN').save()







        new Requestmap(url: '/admin-article/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-email-footer/**', configAttribute: 'ROLE_ADMIN').save()


        new Requestmap(url: '/admin-duplicate/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/requestmap/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin-remote-server/**', configAttribute: 'ROLE_ADMIN').save()




        new Requestmap(url: '/admin-picture-media/**', configAttribute: 'ROLE_ADMIN').save()

        new Requestmap(url: '/admin-partner/**', configAttribute: 'ROLE_ADMIN').save()


        new Requestmap(url: '/admin-media/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/media/**', configAttribute: 'permitAll').save()


        new Requestmap(url: '/confirm-user/**', configAttribute: 'permitAll').save()
        new Requestmap(url: '/user-activation/**', configAttribute: 'permitAll').save()



    }

}

