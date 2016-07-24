package cz.ikariera.admin

class SimpleRegistration {

    String hash
    Date dateExpire

    static constraints = {
        dateExpire(nullable: true)
        hash(nullable: true)
    }

}
