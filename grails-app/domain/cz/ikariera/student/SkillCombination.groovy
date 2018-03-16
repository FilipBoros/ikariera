package cz.ikariera.student

class SkillCombination {
    SkillType skillType
    SkillLevel level
    Integer indexPos=0


    static belongsTo = [ studentAccount: StudentAccount ]



    static constraints = {

        skillType(nullable: true)

        level(nullable: true)
        indexPos(nullable: true)

    }
}
