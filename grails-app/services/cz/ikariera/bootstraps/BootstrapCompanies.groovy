package cz.ikariera.bootstraps


import cz.ikariera.company.Company
import cz.ikariera.company.JobCategory

class BootstrapCompanies {


    public static ArrayList<Company> init(
            ArrayList<JobCategory> jobCategories
    ) {


        ArrayList<Company> companies = new ArrayList();




        def company1 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'Ceska republika',
                companyZip: '15600',
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhleda sdg s ůljgl " +
                        "ůjalgůj faůlg jfalůj lůfajh ůljdf lhjfdalůhlůfdajlafdůh lůafdjh lůadjkfh ůlj" +
                        "adadů hk ůfd khůadf ků§adfkh §ůdfak h§ůfdakh ůfdka hůadk" +
                        "adlůfhjdfaljh ldafjh ladfjh ladj hldfja hlafdj hlfjda hljdaf hljfda hlajdf h)" +
                        "f hůljadf hůlafjd hlvac",

                credits: 500,

                companyID: "ID1",
                companyName: "Senman s.r.o.",
                active: true,


        )
        company1.addToMainJobCategories(jobCategories.get(1))

        company1.addToMainJobCategories(jobCategories.get(2))
        company1.save(failOnError: true)





        def company2 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                companyZip: '15600',
                credits: 500,
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",


                companyID: "ID2",
                companyName: "Yakuza s.r.o.",
                active: true
        )


        company2.addToMainJobCategories(jobCategories.get(1))

        company2.addToMainJobCategories(jobCategories.get(2))

        company2.addToMainJobCategories(jobCategories.get(3))
        company2.save(failOnError: true)


        def company3 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                credits: 500,
                companyZip: '15600',
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID3",
                companyName: "Zakoupil a zbořil s.r.o.",
                active: true
        )

        company3.addToMainJobCategories(jobCategories.get(3))
        company3.save(failOnError: true)



        def company4 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                companyZip: '15600',
                credits: 500,
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID4",
                companyName: "Definitive s.r.o.",
                active: true
        )


        company4.addToMainJobCategories(jobCategories.get(3))
        company4.save(failOnError: true)




        def company5 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                credits: 500,
                companyZip: '15600',
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID5",
                companyName: "Kvazar s.r.o.",


                active: true
        )
        company5.addToMainJobCategories(jobCategories.get(3))
        company5.save(failOnError: true)





        def company6 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                companyZip: '15600',
                credits: 500,
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID5456",
                companyName: "Kvazar s.r.o.",


                active: true
        )
        company6.addToMainJobCategories(jobCategories.get(3))
        company6.save(failOnError: true)




        def company7 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                companyZip: '15600',
                credits: 500,
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID5456",
                companyName: "Kvazar s.r.o.",


                active: true
        )
        company7.addToMainJobCategories(jobCategories.get(3))
        company7.save(failOnError: true)




        def company8 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                credits: 500,
                companyZip: '15600',
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID5456",
                companyName: "Kvazar s.r.o.",


                active: true
        )
        company8.addToMainJobCategories(jobCategories.get(3))
        company8.save(failOnError: true)


        def company9 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                companyCountry: 'CR',
                credits: 500,
                companyZip: '15600',
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID5456",
                companyName: "Kvazar s.r.o.",


                active: true
        )
        company9.addToMainJobCategories(jobCategories.get(3))
        company9.save(failOnError: true)




        def company10 = new Company(
                companyStreet: 'Konecna 6',
                companyCity: 'Praha',
                credits: 500,
                companyCountry: 'CR',
                companyZip: '15600',
                companyWeb: 'http://www.seznam.cz',
                companyFileSystemName: 'SEZNAM',
                companyCharacteristic: "vyhledavac",
                companyID: "ID5456",
                companyName: "Kvazar s.r.o.",


                active: true
        )
        company10.addToMainJobCategories(jobCategories.get(3))
        company10.save(failOnError: true)


        companies.add(company1)
        companies.add(company2)
        companies.add(company3)
        companies.add(company4)
        companies.add(company5)

        companies.add(company6)
        companies.add(company7)
        companies.add(company8)
        companies.add(company9)
        companies.add(company10)

        return companies
    }
}

