package cz.ikariera.student

class StudyCategory {
    String name
    String i18Name
    Integer posOrder


    static constraints = {
        name(nullable: false, blank: false, maxSize: 200)
        i18Name(unique: true, maxSize: 200)
    }


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "study.category." + this.i18Name
    }
}
