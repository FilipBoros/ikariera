package cz.ikariera.company

class Services {


    String      uniqueName
    String      name
    BigDecimal  creditPrice = 0
    String      description
    Integer     posOrder
    Integer     defaultExpirationTime = 10

    static mapping = {
        description type: 'text'
    }

    static constraints = {
        name(blank: false, nullable: false, maxSize: 300 )
        uniqueName(unique: true, blank: false, nullable: false,)
        description(blank: false, nullable: true, maxSize: 2000 )
        creditPrice(nullable: false,  min: 0.0, scale: 2)
    }
}
