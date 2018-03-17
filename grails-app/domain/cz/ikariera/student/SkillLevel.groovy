package cz.ikariera.student

class SkillLevel {
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

        "skill.level." + this.i18Name
    }

}

