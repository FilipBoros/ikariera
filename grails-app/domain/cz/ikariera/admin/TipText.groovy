package cz.ikariera.admin

class TipText {


    String name
    String text
    Integer posOrder


    static mapping = {
        text type: 'text'

    }

    static constraints = {
        name(maxSize: 255,nullable: false,blank: false)
        text(maxSize: 4000,blank: false, nullable: false)
    }
}
