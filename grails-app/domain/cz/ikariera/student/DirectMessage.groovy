package cz.ikariera.student

class DirectMessage {

    Date sendDate
    String message
    /*
    Boolean isRead
    Boolean isReply
    */

    Boolean isReadSender = false
    Boolean isReplySender
    Boolean isReadRecipient = false
    Boolean isReplyRecipient

    String sender


    static belongsTo = [ dialog: Dialog ]

    static constraints = {
        message(maxSize: 4000)
    }

}
