package cz.ikariera.admin

class MainLogo {
    String name
    boolean publish = false
    Date endDate


    static constraints = {
        name(blank: false, nullable: true, maxSize: 100)
        endDate(blank:true,nullable:true)
    }
}
