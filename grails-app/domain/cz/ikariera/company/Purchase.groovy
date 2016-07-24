package cz.ikariera.company

class Purchase {


    static belongsTo = [company: Company]



    String details
    String comment
    String detailsParams
    String serviceName
    String serviceNameParams

    Date datePurchased = new Date()

    BigDecimal credits


    String user

    Boolean isAdmin = false




    static constraints = {


        details(nullable: true)
        comment(nullable: true)
        detailsParams(nullable: true)
        serviceName(nullable: true)
        serviceNameParams(nullable: true)
    }
}
