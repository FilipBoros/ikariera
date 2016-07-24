package cz.ikariera.company

class JobOfferPositionLevel {

    String name
    Integer specOrder
    String i18Name


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "jobOffer.position.level." + this.i18Name
    }


    static constraints = {
        i18Name(nullable: true)
    }
}
