package cz.ikariera.admin

class Faq {

    String question
    String answer
    Integer posOrder
    Localization languageType


    static constraints = {
        question(nullable: false,blank: false)
        answer(nullable: false,blank: false)
        posOrder(nullable: false,blank: false)
        languageType (nullable: true)
    }
}
