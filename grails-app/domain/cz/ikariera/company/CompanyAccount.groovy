package cz.ikariera.company

import cz.ikariera.security.User

class CompanyAccount {

    String titleBefore
    String titleAfter
    String telephone
    String positionInCompany
    Date dateCreated = new Date()
    boolean infoEmails = true




    User user



    static constraints = {
        telephone(blank: true, nullable: true, maxSize: 50)
        titleBefore(blank: true, nullable: true, maxSize: 50)
        titleAfter(blank: true, nullable: true, maxSize: 50)
        positionInCompany(blank: true, nullable: true, maxSize: 100)

    }



}
