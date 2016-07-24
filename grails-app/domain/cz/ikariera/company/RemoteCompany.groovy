package cz.ikariera.company

class RemoteCompany {

    String companyName

    String companyStreet
    String companyCity
    String companyCountry
    String companyZip

    String companyWeb

    String companyCharacteristic


    String messageRest



    static belongsTo = [jobOffer: JobOffer]



    static mapping = {

        companyCharacteristic type: 'text'

    }


    static constraints = {



        companyName(blank: false, nullable: false, maxSize: 300)
        companyStreet(blank: false, nullable: false, maxSize: 300)
        companyCity(blank: false, nullable: false, maxSize: 300)
        companyCountry(blank: false, nullable: false, maxSize: 300)
        companyZip(blank: false, nullable: false, maxSize: 50)

        companyCharacteristic(blank: true, nullable: true, maxSize: 2000)

        messageRest(blank: true, nullable: true, maxSize: 2000)



    }
}
