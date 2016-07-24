package cz.ikariera.student


class Experience {

    String periodStart
    String periodEnd
    String occupation
    String activities
    String employer
    Integer indexPos=0


    static belongsTo = [studentAccount: StudentAccount]

    static constraints = {

        periodStart(nullable: true)
        periodEnd(nullable: true)
        occupation(nullable: true, maxSize: 255)
        activities(nullable: true, maxSize: 2000)
        employer(nullable: true, maxSize: 255)

        indexPos(nullable: true)

    }
}
