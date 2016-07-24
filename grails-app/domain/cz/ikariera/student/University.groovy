package cz.ikariera.student

class University {
    String name
    String shortName
    String i18Name
    Integer posOrder

    static constraints = {
        name(blank: false, nullable: false, maxSize: 200)
        shortName(blank: false, nullable: false, maxSize: 50)
        i18Name(unique: true, maxSize: 200)


    }


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "universty." + this.i18Name
    }

}
