package cz.ikariera.admin

class Constants {
    String  cmName
    String  cmComment
    Integer cmValue = null
    String  cmString = null
    Boolean cmBoolean = null

    static mapping = {
        cmComment type: 'text'
        cmString type: 'text'
    }



    static constraints = {
        cmName(unique: true , blank: false, nullable: false, maxSize: 20)
        cmComment(blank: true, nullable: true, maxSize: 400)
        cmBoolean(nullable: true)
        cmValue(nullable: true)
        cmString(nullable: true,blank: true, maxSize: 255)
    }
}
