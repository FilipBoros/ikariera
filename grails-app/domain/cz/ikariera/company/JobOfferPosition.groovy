package cz.ikariera.company

class JobOfferPosition {

    String name
    Integer specOrder
    String i18Name


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "jobOffer.position." + this.i18Name
    }


    static constraints = {
        i18Name(nullable: true)
    }
}
