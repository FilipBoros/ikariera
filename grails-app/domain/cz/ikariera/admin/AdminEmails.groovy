package cz.ikariera.admin

import cz.ikariera.company.CompanyEmails

class AdminEmails {

    String subject
    String message
    Date dateCreated = new Date()

    Integer numberRecipients
    String recipients

    Date dateSent

    String attachment
    String contentType
    String originalName

    CompanyEmails companyEmail

    static belongsTo = [companyEmail: CompanyEmails]

    static mapping = {
        message type: 'text'
        recipients type: 'text'
    }

    static constraints = {


        subject(maxSize: 255, minSize: 2, nullable: false, blank: false)
        message(maxSize: 100000, minSize: 20, nullable: false, blank: false)
        dateSent(nullable: true)
        attachment(nullable: true)
        contentType(nullable: true)
        originalName(nullable: true)
        numberRecipients(nullable: true)
        recipients (nullable: true)
        companyEmail(nullable: true)
    }
}
