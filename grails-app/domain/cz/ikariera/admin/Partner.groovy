package cz.ikariera.admin

class Partner {

    String name
    String partnerType
    PictureMedia logo
    String link
    Integer posOrder = 0


    static constraints = {


        name()

        partnerType(inList: ["general", "medial", "normal", "cover", "support"])

        logo()
        link(nullabe: true, blank: true, url: true)

    }
}
