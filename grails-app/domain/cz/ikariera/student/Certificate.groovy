package cz.ikariera.student


class Certificate  {

    String name
    String description
    String level

    Integer indexPos=0

    static mapping = {
        description type: 'text'
    }



    static belongsTo = [ studentAccount: StudentAccount ]

    static constraints = {

        level(nullable:true , maxSize: 255)
        name(nullable:true , maxSize: 255)
        description(nullable:true,maxSize: 2000 )

        indexPos(nullable: true)


    }
}
