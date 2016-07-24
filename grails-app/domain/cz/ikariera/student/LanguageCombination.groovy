package cz.ikariera.student


class LanguageCombination {
    LanguageType languageType
    LanguageLevel level
    Integer indexPos=0


    static belongsTo = [ studentAccount: StudentAccount ]



    static constraints = {

        languageType(nullable: true)

        level(nullable: true)
        indexPos(nullable: true)

    }
}
