package cz.ikariera.student

class SkillType {

    String name
    String i18Name
    Integer posOrder

    static constraints = {
        name(blank: false, nullable: false, maxSize: 200)
        i18Name(unique: true, maxSize: 200)
    }


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "skill.type." + this.i18Name
    }

}
