package cz.ikariera.security

import grails.gorm.PagedResultList
import org.apache.commons.lang.builder.HashCodeBuilder
import org.grails.list.ListDistinct
import org.hibernate.criterion.CriteriaSpecification

import static org.grails.list.ListAssociationSort.associationOrder

class UserRole implements Serializable {

    private static final long serialVersionUID = 1

    User user
    Role role

    boolean equals(other) {
        if (!(other instanceof UserRole)) {
            return false
        }

        other.user?.id == user?.id &&
                other.role?.id == role?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (role) builder.append(role.id)
        builder.toHashCode()
    }

    static UserRole get(long userId, long roleId) {
        UserRole.where {
            user == User.load(userId) &&
                    role == Role.load(roleId)
        }.get()
    }

    static UserRole create(User user, Role role, boolean flush = false) {
        new UserRole(user: user, role: role).save(flush: flush, insert: true)
    }

    static boolean remove(User u, Role r, boolean flush = false) {

        int rowCount = UserRole.where {
            user == User.load(u.id) &&
                    role == Role.load(r.id)
        }.deleteAll()

        rowCount > 0
    }

    static void removeAll(User u) {
        UserRole.where {
            user == User.load(u.id)
        }.deleteAll()
    }

    static void removeAll(Role r) {
        UserRole.where {
            role == Role.load(r.id)
        }.deleteAll()
    }

    static mapping = {
        id composite: ['role', 'user']
        version false
    }


    public static PagedResultList adminUserRoleFilter(def attrs = [:]) {
        return ListDistinct.list(UserRole, attrs) {

            // order
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)


            createAlias('user', 'userAlias', CriteriaSpecification.LEFT_JOIN)

            createAlias('role', 'roleAlias', CriteriaSpecification.LEFT_JOIN)

            // cz.ikariera.fulltext search in filters
            if (attrs.firstName) {
                ilike('userAlias.firstName', '%' + attrs.firstName + '%')
            }
            if (attrs.lastName) {
                ilike('userAlias.lastName', '%' + attrs.lastName + '%')

            }

            if (attrs.authority) {

                eq('roleAlias.authority', attrs.authority)
            }


            if (attrs.username) {
                ilike('userAlias.username', '%' + attrs.username + '%')

            }
            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('userAlias.accountCreated', 'desc')

            }
        }
    }


}