package cz.ikariera.company

import cz.ikariera.admin.AdminEmails

class CompanyEmails {

    String subject
    String message
    String attachment
    String contentType
    String originalName

    String recipients

    Date dateCreated = new Date()


    Integer numberRecipients

    BigDecimal creditCost

    Date dateSent

    Boolean confirmed = false

    static belongsTo = [company: Company]

    //AdminEmails adminEmails

    static hasOne = [adminEmails: AdminEmails]

    static transients = ['hotPosition']

    static mapping = {
        message type: 'text'
        recipients type: 'text'
        //adminEmails cascade: "delete"
    }

    static constraints = {


        subject(maxSize: 255, minSize: 2, nullable: false, blank: false)
        message(maxSize: 100000, minSize: 20, nullable: false, blank: false)
        dateSent(nullable: true)
        attachment(maxSize: 3500, nullable: true)
        contentType(maxSize: 255, nullable: true)
        originalName(maxSize: 255, nullable: true)

        recipients(nullable: true)

        numberRecipients(nullable: true)
        creditCost(nullable: true)
        confirmed(nullable: true)
        adminEmails(nullable: true)
    }
}
