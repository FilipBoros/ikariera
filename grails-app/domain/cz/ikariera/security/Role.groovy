package cz.ikariera.security

class Role {

    String authority

    static mapping = {
        /*cache true*/ // TODO remove commentary
    }

    static constraints = {
        authority blank: false, unique: true
    }
}
