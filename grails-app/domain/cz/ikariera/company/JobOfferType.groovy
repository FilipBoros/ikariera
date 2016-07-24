package cz.ikariera.company

class JobOfferType {

    String name
    Integer specOrder
    String i18Name


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "jobOffer.type." + this.i18Name
    }


    static constraints = {
        i18Name(nullable: true)
    }
}
