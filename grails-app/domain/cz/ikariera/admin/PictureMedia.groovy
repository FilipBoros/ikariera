package cz.ikariera.admin


class PictureMedia {


    String name
    String mediaType

    String originalFilename
    String thumbnailFilename

    String newFilename
    Integer fileSize
    Integer position

    Integer width
    Integer height


    static constraints = {

        name blank: true
        mediaType(inList: ["logo", "picture", "hero", "gallery"])

        position nullable: true

    }
}
