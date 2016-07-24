package cz.ikariera.admin

class Advertisement {

    String header

    String urlLink
    String file

    String directory

    Date dateCreated = new Date()
    Date dateExpire = null


    static constraints = {
        dateExpire(nullable: true)
    }
}
