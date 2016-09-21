import cz.ikariera.security.Role



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
        [pattern: '/dbconsole/**',   access: ['permitAll']],
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/shutdown',       access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/fonts/**',    access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/check', 		 access: ['permitAll']],
        [pattern: '/**', 	  		 access: ["ROLE_ADMIN"]]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/**/fonts/**',    filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**',             filters: 'JOINED_FILTERS'],
]

grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*', '/fonts/*']
grails.resources.adhoc.includes = ['/images/**', '/css/**', '/js/**', '/plugins/**', '/fonts/**']
