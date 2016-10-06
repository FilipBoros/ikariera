package spring

import cz.ikariera.security.AuthExtendedSuccessHandler
import cz.ikariera.security.UserExtendedDetailsService
import grails.plugin.springsecurity.SpringSecurityUtils


// Place your Spring DSL code here


beans = {

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

