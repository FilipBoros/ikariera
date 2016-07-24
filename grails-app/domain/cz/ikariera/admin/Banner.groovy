package cz.ikariera.admin

class Banner {
    String position
    Integer priority

    Date expirationDate = new Date() + 30
    Date dateCreated = new Date()
    String link
    String originalFilename
    String newFilename


    Integer counter = 0
    Integer rotation = 0

    static constraints = {
        link(blank: false, nullable: true, maxSize: 255, url: true)
        priority(blank: false, nullable: false, min: 0)
        originalFilename(blank: false, nullable: true, maxSize: 255)
        newFilename(blank: false, nullable: true, maxSize: 255)
    }

    def afterLoad() {
        counter++
        rotation++
    }
}
