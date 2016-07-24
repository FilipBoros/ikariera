

/**
 * Created by michal on 23.7.2016.
 */
environments {
    development {
        grails.logging.jul.usebridge = true
        server.port = 8090  // funguje

    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = 'http://www.cz.cz.cz.ikariera.cz'

    }
    test {

        grails.logging.jul.usebridge = false
        grails.serverURL = 'http://localhost:8090'//8090

    }
}

grails.plugin.springsecurity.userLookup.userDomainClassName = 'cz.ikariera.security.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'cz.ikariera.security.UserRole'
grails.plugin.springsecurity.authority.className = 'cz.ikariera.security.Role'
grails.plugin.springsecurity.requestMap.className = 'cz.ikariera.security.Requestmap'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/dbconsole/**',   access: ['permitAll']]
]