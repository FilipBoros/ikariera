package cz.ikariera.student

class Cv {

    String name

    Date date = new Date()

    String originalFilename

    String newFilename
    Integer fileSize



    static belongsTo = [ student:StudentAccount ]


    static constraints = {




    }

}
