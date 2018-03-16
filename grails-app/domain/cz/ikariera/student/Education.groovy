package cz.ikariera.student


class Education {

    University university

    String otherUniversity

    StudyCategory studyCategory

    Date startYear

    Date endingYear

    Integer endingYearInteger
    Integer startYearInteger

    String specialization
    String titleAwarded

    Integer indexPos=0


    static belongsTo = [ studentAccount: StudentAccount ]


    static constraints = {


        specialization(nullable: true,  maxSize: 255)
        titleAwarded (nullable: true,  maxSize: 50)
        university(nullable: true)
        studyCategory(nullable: true)
        endingYearInteger(nullable: true)
        startYearInteger(nullable: true)

        startYear(nullable: true)
        endingYear(nullable: true)

        otherUniversity(nullable: true)
        indexPos(nullable: true)

    }
}
