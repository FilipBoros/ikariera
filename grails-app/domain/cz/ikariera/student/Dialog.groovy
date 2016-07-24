package cz.ikariera.student

import cz.ikariera.company.Company
import cz.ikariera.company.JobOffer
import cz.ikariera.security.User

class Dialog {

    User sender

    User recipient

    Company company

    JobOffer jobOffer

    List messages
    String subject

    static hasMany = [
            messages: DirectMessage
    ]

    static constraints = {

        subject (nullable : true)
        sender ( nullable: true)
        recipient (nullable: true)
        company (nullable: true)
        jobOffer (nullable: true)
    }
/*
    def getUnreadCount(boolean inbox) {
        def count
        if (inbox) {
            count = messages.count {
                !it.isRead && !it.isReply
            }
        } else {
            count = messages.count {
                it.isReply && !it.isRead
            }
        }

        return count
    }

    def readAllMsg(boolean inbox) {
        if (inbox) {
            messages.each {
                if (!it.isReply && !it.isRead) {
                    it.isRead = true
                }
            }
        } else {
            messages.each {
                if (it.isReply && !it.isRead) {
                    it.isRead = true
                }
            }
        }
    }
*/
    def getUnreadCountSender(boolean inbox) {
        def count
        if (inbox) {
            count = messages.count {
                !it.isReadSender
            }
        } else {
            count = messages.count {
                !it.isReadSender
            }
        }

        return count
    }

    def readAllMsgSender(boolean inbox) {
        if (inbox) {
            messages.each {
                if (!it.isReadSender) {
                    it.isReadSender = true
                }
            }
        } else {
            messages.each {
                if (!it.isReadSender) {
                    it.isReadSender = true
                }
            }
        }
    }

    def getUnreadCountRecipient(boolean inbox) {
        def count
        if (inbox) {
            count = messages.count {
                !it.isReadRecipient
            }
        } else {
            count = messages.count {
                !it.isReadRecipient
            }
        }

        return count
    }

    def readAllMsgRecipient(boolean inbox) {
        if (inbox) {
            messages.each {
                if (!it.isReplyRecipient && !it.isReadRecipient) {
                    it.isReadRecipient = true
                }
            }
        } else {
            messages.each {
                if (!it.isReplyRecipient && !it.isReadRecipient) {
                    it.isReadRecipient = true
                }
            }
        }
    }

}
