package cz.ikariera.company

class CompanyFeedBack {

    String sender
    String subject
    String text
    Date dateCreated = new Date()

    static constraints = {
        subject(nullable: false, maxSize: 150)
        text(nullable: false, maxSize: 4000)
        dateCreated(nullable: true, blank: true)
    }
}
