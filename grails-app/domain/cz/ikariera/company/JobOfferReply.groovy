package cz.ikariera.company

class JobOfferReply {

    String studentName
    String studentLastName = ""
    String studentEmail
    String replyText
    String originalFilename
    String newFilename
    Boolean isRead = false

    Date dateCreated

    static belongsTo = [jobOffer: cz.ikariera.company.JobOffer]

    static constraints = {
        studentEmail (nullable: false, maxSize: 255)
        studentName (nullable: false, maxSize: 255)
        studentLastName (nullable: true, maxSize: 255)
        replyText (nullable: false, maxSize: 4000)
        originalFilename (nullable: true)
        newFilename (nullable: true)
    }
}
