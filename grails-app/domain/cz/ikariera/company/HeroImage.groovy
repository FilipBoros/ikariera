package cz.ikariera.company
import cz.ikariera.admin.Country

class HeroImage {
    String name
    boolean publish = false


    //JobCategory jobCategory

    static belongsTo = [company: Company]
    static hasMany = [jobCategories: JobCategory, localities: Locality, countries: Country]

    //Locality locality
    //Country country

    String description


    String thumbnailLink
    String imageLink


    static constraints = {
        name(blank: true, nullable: true, maxSize: 100)
        jobCategories(nullable: true, blank : true)
        company(nullable: true)
        localities(nullable: true, blank : true)
        countries(nullable: true, blank : true)
        description(nullable: true)

        thumbnailLink(nullable: true)
        imageLink(nullable: true)
    }
}
