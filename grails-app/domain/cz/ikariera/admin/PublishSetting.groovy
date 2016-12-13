package cz.ikariera.admin

class PublishSetting {
    /*Originally called PublishService, but had to be changed, because domain class cannot end with service*/
    int numberOfDays
    int numberOfPublishedJobOffers
    int prize

    static constraints = {
    }
}
