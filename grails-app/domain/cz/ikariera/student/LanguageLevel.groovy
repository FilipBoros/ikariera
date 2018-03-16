package cz.ikariera.student

/*
 * Edited by Filip Boroš
 * Added: nameEN, nameSK
 */
class LanguageLevel {
    String nameCZ
    String nameEN
    String nameSK
    String i18Name
    Integer posOrder

    static constraints = {
        nameCZ(blank: false, nullable: false, maxSize: 200)
        nameEN(blank: false, nullable: false, maxSize: 200)
        nameSK(blank: false, nullable: false, maxSize: 200)
        i18Name(unique: true, maxSize: 200)
    }

    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "language.level." + this.i18Name
    }

}

