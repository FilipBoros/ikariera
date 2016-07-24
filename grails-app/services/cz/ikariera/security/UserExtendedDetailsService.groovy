package cz.ikariera.security

import cz.ikariera.UserExtendedDetails
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException


class UserExtendedDetailsService implements GrailsUserDetailsService {


    static final List NO_ROLES = [new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)]

    UserDetails loadUserByUsername(String username, boolean loadRoles)
    throws UsernameNotFoundException {
        return loadUserByUsername(username)
    }

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User.withTransaction { status ->

            User user = User.findByUsername(username)
            if (!user) throw new UsernameNotFoundException(
                    'User not found', username)

            def authorities = user.authorities.collect {
                new SimpleGrantedAuthority(it.authority)
            }

            BigDecimal credits = user?.company?.credits

            String companyName = user?.company?.companyName

            return new UserExtendedDetails(
                    user.firstName,
                    user.lastName,
                    user.username,
                    user.password,
                    user.enabled,
                    !user.accountExpired,
                    !user.passwordExpired,
                    !user.accountLocked,
                    authorities ?: NO_ROLES,
                    user.id,
                    user.fullName,
                    credits,
                    companyName
            )
        }
    }
}
