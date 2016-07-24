package cz.ikariera.student

class Photo {


    String name


    String originalFilename
    String thumbnailFilename

    String newFilename
    Integer fileSize


    Integer width
    Integer height


    static belongsTo = [ student:StudentAccount ]


    static constraints = {

        name blank: true


    }


}
