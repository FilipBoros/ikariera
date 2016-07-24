package cz.ikariera.company

class ServicesExpire {


    static belongsTo = [company: Company]

    int counterJobOfferPublished = 0
    Date lastPublished = new Date()
    int amountOfJobOffers = 2 // default
    int periodOfPublishing = 7 // default
    Date publishServiceActivate

    Date topProfile = new Date()
    Date mail = new Date()
    Date topOffer = new Date()
    Date cv = new Date()
    Date article = new Date()

    Date shareOffer = new Date()


    Date messages = new Date()

    Date rest = new Date()

    Date hero = new Date()

    Date remote = new Date()
    Date apiActiveTill = new Date()

    static constraints = {

        topProfile(nullable: true)

        mail(nullable: true)

        topOffer(nullable: true)

        cv(nullable: true)
        article(nullable: true)
        shareOffer(nullable: true)

        messages(nullable: true)
        rest(nullable: true)
        hero(nullable: true)
        remote(nullable: true)
        apiActiveTill(nullable: true)

        counterJobOfferPublished(nullable: true)
        lastPublished(nullable: true)
        amountOfJobOffers(nullable: true)
        periodOfPublishing(nullable: true)
        publishServiceActivate(nullable: true)
    }
}
