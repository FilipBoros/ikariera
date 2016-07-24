package cz.ikariera.company

class JobCategory {
    String name
    String i18Name
    Integer posOrder


    static constraints = {
        name(nullable: false, blank: false, maxSize: 200)
        i18Name(unique: true, maxSize: 200)
    }


    static transients = ['i18NameFull']

    public String getI18NameFull() {

        "job.category." + this.i18Name
    }


}
