package cz.ikariera.admin

class Contact {
    String contactIdentify = "IAESTE"
    String contactName
    String contactShortText
    String contactID
    String contactVatID
    String contactStreet
    String contactCity
    String contactCountry
    String contactZip
    String contactTelephone
    String contactFax
    String contactEmail
    String contactWeb
    String contactHelpdesk
    String contactBankNumber=0
    String bankName
    String youtube
    String linkedin

    static constraints = {
        contactID(blank: false, nullable: false, maxSize: 100)
        contactName(blank: false, nullable: false, maxSize: 300)
        contactVatID(blank: false, nullable: false, maxSize: 100)
        contactStreet(blank: false, nullable: false, maxSize: 300)
        contactCity(blank: false, nullable: false, maxSize: 300)
        contactCountry(blank: false, nullable: false, maxSize: 300)
        contactZip(blank: false, nullable: false, maxSize: 50)
        contactEmail (blank: false, nullable: false)
        contactTelephone (blank: true, nullable: false)
        contactFax (blank: true, nullable: false)
        contactWeb (blank: true, nullable: false)
        contactHelpdesk (blank: true, nullable: false)
        contactBankNumber (blank: true, nullable: false)
        bankName (blank: true, nullable: false)
        contactShortText ( blank: true, nullable: false)
        youtube ( blank: true, nullable: true)
        linkedin ( blank: true, nullable: true)

    }
}
