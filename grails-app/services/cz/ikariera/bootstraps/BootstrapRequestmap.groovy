package cz.ikariera.bootstraps

import cz.ikariera.security.Requestmap


class BootstrapRequestmap {


    public static void init() {


        def requestmap  = Requestmap.findByUrl('/js/**') ?: new Requestmap(url: '/js/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/css/**') ?: new Requestmap(url: '/css/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/images/**') ?: new Requestmap(url: '/images/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/login/**') ?: new Requestmap(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/logout/**') ?: new Requestmap(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/*') ?: new Requestmap(url: '/*', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/j_spring_security_switch_user') ?: new Requestmap(url: '/j_spring_security_switch_user', configAttribute: 'ROLE_SWITCH_USER,IS_AUTHENTICATED_FULLY').save()
        requestmap = Requestmap.findByUrl('/security/**') ?: new Requestmap(url: '/security/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()




        requestmap = Requestmap.findByUrl('/user-activation/**') ?: new Requestmap(url: '/user-activation/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/password/**') ?: new Requestmap(url: '/password/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/company-api/**') ?: new Requestmap(url: '/company-api/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/companies-api/**') ?: new Requestmap(url: '/companies-api/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()


        requestmap = Requestmap.findByUrl('/catalogue/**') ?: new Requestmap(url: '/catalogue/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/company-profiles/**') ?: new Requestmap(url: '/company-profiles/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/error/**') ?: new Requestmap(url: '/error/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/articles/**') ?: new Requestmap(url: '/articles/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/ponuky-prace/**') ?: new Requestmap(url: '/ponuky-prace/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/nabidky-prace/**') ?: new Requestmap(url: '/nabidky-prace/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/job-offers/**') ?: new Requestmap(url: '/job-offers/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/job-offer/**') ?: new Requestmap(url: '/job-offer/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/clanky-a-rady/**') ?: new Requestmap(url: '/clanky-a-rady/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save() /*Sk is the same*/
        requestmap = Requestmap.findByUrl('/tips-and-articles/**') ?: new Requestmap(url: '/tips-and-articles/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/menu-item/**') ?: new Requestmap(url: '/menu-item/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/about/**') ?: new Requestmap(url: '/about/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/ajax/**') ?: new Requestmap(url: '/ajax/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/index/**') ?: new Requestmap(url: '/index/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/company/company-event/**') ?: new Requestmap(url: '/company/company-event/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/public-banner/**') ?: new Requestmap(url: '/public-banner/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/student/registration') ?: new Requestmap(url: '/student/registration', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/company/registration') ?: new Requestmap(url: '/company/registration', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/student/**') ?: new Requestmap(url: '/student/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()


        requestmap = Requestmap.findByUrl('/student-account/**') ?: new Requestmap(url: '/student-account/**', configAttribute: 'ROLE_STUDENT').save()
        requestmap = Requestmap.findByUrl('/student-account-cv/**') ?: new Requestmap(url: '/student-account-cv/**', configAttribute: 'ROLE_STUDENT').save()
        requestmap = Requestmap.findByUrl('/student-account-danger-area/**') ?: new Requestmap(url: '/student-account-danger-area/**', configAttribute: 'ROLE_STUDENT').save()
        requestmap = Requestmap.findByUrl('/student-account-message/**') ?: new Requestmap(url: '/student-account-message/**', configAttribute: 'ROLE_STUDENT').save()
        requestmap = Requestmap.findByUrl('/student-account-photo/**') ?: new Requestmap(url: '/student-account-photo/**', configAttribute: 'ROLE_STUDENT').save()

        requestmap = Requestmap.findByUrl('/student-account-personal-details/**') ?: new Requestmap(url: '/student-account-personal-details/**', configAttribute: 'ROLE_STUDENT').save()

        requestmap = Requestmap.findByUrl('/student-account-messages/**') ?: new Requestmap(url: '/student-account-messages/**', configAttribute: 'ROLE_STUDENT').save()

        requestmap = Requestmap.findByUrl('/student-account-education/**') ?: new Requestmap(url: '/student-account-education/**', configAttribute: 'ROLE_STUDENT').save()
        requestmap = Requestmap.findByUrl('/student-account-contact/**') ?: new Requestmap(url: '/student-account-contact/**', configAttribute: 'ROLE_STUDENT').save()
        requestmap = Requestmap.findByUrl('/student-account-password/**') ?: new Requestmap(url: '/student-account-password/**', configAttribute: 'ROLE_STUDENT').save()

        requestmap = Requestmap.findByUrl('/student-account-university/**') ?: new Requestmap(url: '/student-account-university/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        requestmap = Requestmap.findByUrl('/student-account-language/**') ?: new Requestmap(url: '/student-account-language/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        requestmap = Requestmap.findByUrl('/student-account-certificate/**') ?: new Requestmap(url: '/student-account-certificate/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        requestmap = Requestmap.findByUrl('/student-account-experience/**') ?: new Requestmap(url: '/student-account-experience/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak
        requestmap = Requestmap.findByUrl('/student-account-generate-cv/**') ?: new Requestmap(url: '/student-account-generate-cv/**', configAttribute: 'ROLE_STUDENT').save() //Michal Dolnak

        requestmap = Requestmap.findByUrl('/company-account/**') ?: new Requestmap(url: '/company-account/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/company/**') ?: new Requestmap(url: '/company/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/firemni-profil/**') ?: new Requestmap(url: '/firemni-profil/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
        requestmap = Requestmap.findByUrl('/firemny-profil/**') ?: new Requestmap(url: '/firemny-profil/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()


        requestmap = Requestmap.findByUrl('/company-articles/**') ?: new Requestmap(url: '/company-articles/**', configAttribute: 'ROLE_COMPANY').save()
        //
        requestmap = Requestmap.findByUrl('/company-account-logo/**') ?: new Requestmap(url: '/company-account-logo/**', configAttribute: 'ROLE_COMPANY').save()
        requestmap = Requestmap.findByUrl('/company-gallery/**') ?: new Requestmap(url: '/company-gallery/**', configAttribute: 'ROLE_COMPANY').save()




        requestmap = Requestmap.findByUrl('/company-account-user/**') ?: new Requestmap(url: '/company-account-user/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-job-offers/**') ?: new Requestmap(url: '/company-account-job-offers/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-email/mailing-list/**') ?: new Requestmap(url: '/company-account-email/mailing-list/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/company-account-user/**') ?: new Requestmap(url: '/company-account-user/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-job-offers/**') ?: new Requestmap(url: '/company-account-job-offers/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/company-account-email/**') ?: new Requestmap(url: '/company-account-email/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-cv-search/**') ?: new Requestmap(url: '/company-account-cv-search/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-advanced-profile/**') ?: new Requestmap(url: '/company-account-advanced-profile/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-articles/**') ?: new Requestmap(url: '/company-account-articles/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        //
        requestmap = Requestmap.findByUrl('/company-account-user/**') ?: new Requestmap(url: '/company-account-user/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-job-offers/**') ?: new Requestmap(url: '/company-account-job-offers/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-articles/**') ?: new Requestmap(url: '/company-account-articles/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-cv-search/**') ?: new Requestmap(url: '/company-account-cv-search/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-email/**') ?: new Requestmap(url: '/company-account-email/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/company-account-message/**') ?: new Requestmap(url: '/company-account-message/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/company-account-profile/**') ?: new Requestmap(url: '/company-account-profile/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/company-account-services/**') ?: new Requestmap(url: '/company-account-services/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/company-account-credits-history/**') ?: new Requestmap(url: '/company-account-credits-history/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/company-account-email-search/**') ?: new Requestmap(url: '/company-account-email-search/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/company-job-offer-api/**') ?: new Requestmap(url: '/company-job-offer-api/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save() // Michal Dolnak

        requestmap = Requestmap.findByUrl('/company-contacts*//**') ?: new Requestmap(url: '/company-contacts*//**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save() /*TODO checknut toto*/

        requestmap = Requestmap.findByUrl('/company-account-feedback/**') ?: new Requestmap(url: '/company-account-feedback/**', configAttribute: 'ROLE_COMPANY, ROLE_ADMIN').save() // Michal Dolnak

        requestmap = Requestmap.findByUrl('/admin-remote-register/**') ?: new Requestmap(url: '/admin-remote-register/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/password-reset/**') ?: new Requestmap(url: '/password-reset/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()

        requestmap = Requestmap.findByUrl('/admin-credits/**') ?: new Requestmap(url: '/admin-credits/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-banner/**') ?: new Requestmap(url: '/admin-banner/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-faq/**') ?: new Requestmap(url: '/admin-faq/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-main-logo/**') ?: new Requestmap(url: '/admin-main-logo/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-logos/**') ?: new Requestmap(url: '/admin-logos/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-catalogue/**') ?: new Requestmap(url: '/admin-catalogue/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-company/**') ?: new Requestmap(url: '/admin-company/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin/**') ?: new Requestmap(url: '/admin/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-services/**') ?: new Requestmap(url: '/admin-services/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-statistics/**') ?: new Requestmap(url: '/admin-statistics/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-advertisement/**') ?: new Requestmap(url: '/admin-advertisement/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-mobile/**') ?: new Requestmap(url: '/admin-mobile/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-job-offer/**') ?: new Requestmap(url: '/admin-job-offer/**', configAttribute: 'ROLE_ADMIN').save()


        requestmap = Requestmap.findByUrl('/admin-contact/**') ?: new Requestmap(url: '/admin-contact/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-email/**') ?: new Requestmap(url: '/admin-email/**', configAttribute: 'ROLE_ADMIN').save()



        requestmap = Requestmap.findByUrl('/admin-constants/**') ?: new Requestmap(url: '/admin-constants/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-user-admin/**') ?: new Requestmap(url: '/admin-user-admin/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-user-all/**') ?: new Requestmap(url: '/admin-user-all/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-user-company/**') ?: new Requestmap(url: '/admin-user-company/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-user-student/**') ?: new Requestmap(url: '/admin-user-student/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-feed-back/**') ?: new Requestmap(url: '/admin-feed-back/**', configAttribute: 'ROLE_ADMIN').save() // Michal Dolnak
        requestmap = Requestmap.findByUrl('/admin-publish-service/**') ?: new Requestmap(url: '/admin-publish-service/**', configAttribute: 'ROLE_ADMIN').save() // Michal Dolnak

        requestmap = Requestmap.findByUrl('/admin-company-service/**') ?: new Requestmap(url: '/admin-company-service/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-article/**') ?: new Requestmap(url: '/admin-article/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-email-footer/**') ?: new Requestmap(url: '/admin-email-footer/**', configAttribute: 'ROLE_ADMIN').save()


        requestmap = Requestmap.findByUrl('/admin-duplicate/**') ?: new Requestmap(url: '/admin-duplicate/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/requestmap/**') ?: new Requestmap(url: '/requestmap/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/admin-remote-server/**') ?: new Requestmap(url: '/admin-remote-server/**', configAttribute: 'ROLE_ADMIN').save()




        requestmap = Requestmap.findByUrl('/admin-picture-media/**') ?: new Requestmap(url: '/admin-picture-media/**', configAttribute: 'ROLE_ADMIN').save()

        requestmap = Requestmap.findByUrl('/admin-partner/**') ?: new Requestmap(url: '/admin-partner/**', configAttribute: 'ROLE_ADMIN').save()


        requestmap = Requestmap.findByUrl('/admin-media/**') ?: new Requestmap(url: '/admin-media/**', configAttribute: 'ROLE_ADMIN').save()
        requestmap = Requestmap.findByUrl('/media/**') ?: new Requestmap(url: '/media/**', configAttribute: 'permitAll').save()


        requestmap = Requestmap.findByUrl('/confirm-user/**') ?: new Requestmap(url: '/confirm-user/**', configAttribute: 'permitAll').save()
        requestmap = Requestmap.findByUrl('/confirm-company-user/**') ?: new Requestmap(url: '/confirm-company-user/**', configAttribute: 'permitAll').save()
        requestmap = Requestmap.findByUrl('/user-activation/**') ?: new Requestmap(url: '/user-activation/**', configAttribute: 'permitAll').save()



    }

}

