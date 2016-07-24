package cz.ikariera.company

class Locality {
    String name
    String i18Name
    Integer posOrder

    static constraints = {
        name(blank: false, nullable: false, maxSize: 200)
        i18Name(unique: true, maxSize: 200)
    }


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "locality." + this.i18Name
    }


}
