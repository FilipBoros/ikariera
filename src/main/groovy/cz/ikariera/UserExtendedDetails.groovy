package cz.ikariera

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

/**
 * Created by michal on 24.7.2016.
 */
class UserExtendedDetails extends GrailsUser {

    final String firstName
    final String lastName
    final String fullName
    final BigDecimal credits
    final String companyName

    UserExtendedDetails(String firstName, String lastName, String username, String password, boolean enabled,
                        boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked,
                        Collection<GrantedAuthority> authorities,
                        long id, String fullName,
                        BigDecimal credits, String companyName) {


        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)

        this.firstName = firstName
        this.lastName = lastName
        this.credits = credits
        this.fullName = fullName
        this.companyName = companyName
    }
}
