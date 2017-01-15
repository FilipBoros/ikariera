package spring

import cz.ikariera.security.AuthExtendedSuccessHandler
import cz.ikariera.security.UserExtendedDetailsService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.web.servlet.i18n.SessionLocaleResolver


// Place your Spring DSL code here


beans = {

    /*localeResolver(SessionLocaleResolver) {
        defaultLocale = new Locale(application.config.language)
        Locale.setDefault(defaultLocale)
    }*/

    authenticationSuccessHandler(AuthExtendedSuccessHandler) {
        def conf = SpringSecurityUtils.securityConfig
        requestCache = ref('requestCache')
        defaultTargetUrl = conf.successHandler.defaultTargetUrl
        alwaysUseDefaultTargetUrl = conf.successHandler.alwaysUseDefault
        targetUrlParameter = conf.successHandler.targetUrlParameter
        useReferer = conf.successHandler.useReferer
        redirectStrategy = ref('redirectStrategy')
        adminUrl = "/admin-company"
        studentUrl = "/student-account"
        companyUrl = "/company-account-profile"///company
    }

    userDetailsService(UserExtendedDetailsService)


}

