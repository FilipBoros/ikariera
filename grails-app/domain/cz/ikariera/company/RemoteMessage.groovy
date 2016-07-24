package cz.ikariera.company

class RemoteMessage {


    String name
    String subject


    String text

    static belongsTo = [company: Company]


    Date dateSent = new Date()

    String attachement


    static constraints = {
    }
}
