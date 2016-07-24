package cz.ikariera.company

class Articles {
    String header
    String bodyText


    String leadingText
    Integer counter = 0
    Integer contactCounter=0
    Date datePublished
    Date willExpire
    Date dateCreated = new Date()

    String seoName



    BigDecimal credits   =0

    ContactDetails contact

    static belongsTo = [company: Company]   //default set to IAESTE CR

    static mapping = {
        bodyText type: 'text'

    }

    static constraints = {
        header(blank: false, nullable: false, minSize: 6, maxSize: 300)
        bodyText(blank: false, nullable: false, minSize: 200)
        seoName(unique: true, nullable: true, maxSize: 200)
        contact(nullable: true)
        datePublished(nullable: true)
        willExpire(nullable: true)

        leadingText(nullable: true, maxSize: 550)
    }
    /*def afterLoad() {
        counter++
        contactCounter++
    }   */







}
