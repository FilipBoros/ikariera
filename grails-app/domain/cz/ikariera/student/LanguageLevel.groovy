package cz.ikariera.student

class LanguageLevel {
    String name
    String i18Name
    Integer posOrder

    static constraints = {
        name(blank: false, nullable: false, maxSize: 200)
        i18Name(unique: true, maxSize: 200)
    }

    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "language.level." + this.i18Name
    }

}

