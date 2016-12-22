package cz.ikariera.bootstraps

/**
 * Created with IntelliJ IDEA.
 * User: cernydav
 * Date: 8/10/12
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */

import cz.ikariera.student.University


class BootstrapUniversities {


    public static ArrayList<University> init() {

        ArrayList<University> universities = new ArrayList();

        def university = University.findByI18Name("CZU") ?: new University(name: "Česká zemědělská univerzita v Praze", shortName: "ČZU", i18Name: "CZU", posOrder: "1").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("CTU") ?: new University(name: "České vysoké učení technické v Praze", shortName: "ČVUT", i18Name: "CTU", posOrder: "2").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("JCU") ?: new University(name: "Jihočeská univerzita v Českých Budějovicích", shortName: "JČU", i18Name: "JCU", posOrder: "3").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("MUNI") ?: new University(name: "Masarykova univerzita v Brně", shortName: "MUNI", i18Name: "MUNI", posOrder: "4").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("MZLU") ?: new University(name: "Mendelova zemědělská a lesnická univerzita v Brně", shortName: "MZLU", i18Name: "MZLU", posOrder: "5").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("OU") ?: new University(name: "Ostravská univerzita", shortName: "OU", i18Name: "OU", posOrder: "6").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("SLU") ?: new University(name: "Slezská univerzita v Opavě/Karviné", shortName: "SLU", i18Name: "SLU", posOrder: "7").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("TUL") ?: new University(name: "Technická univerzita v Liberci", shortName: "TUL", i18Name: "TUL", posOrder: "8").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("UHK") ?: new University(name: "Univerzita Hradec Králové", shortName: "UHK", i18Name: "UHK", posOrder: "9").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("UJEP") ?: new University(name: "Univerzita Jana Evangelisty Purkyně v Ústí n.Labem", shortName: "UJEP", i18Name: "UJEP", posOrder: "10").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("UK") ?: new University(name: "Univerzita Karlova v Praze", shortName: "UK", i18Name: "UK", posOrder: "11").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("UPO") ?: new University(name: "Univerzita Palackého v Olomouci", shortName: "UPO", i18Name: "UPO", posOrder: "12").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("UPCE") ?: new University(name: "Univerzita Pardubice", shortName: "UPCE", i18Name: "UPCE", posOrder: "13").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("UTB") ?: new University(name: "Univerzita Tomáše Bati ve Zlíně", shortName: "UTB", i18Name: "UTB", posOrder: "14").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("VFU") ?: new University(name: "Veterinární a farmaceutická univerzita Brno", shortName: "VFU", i18Name: "VFU", posOrder: "15").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("VSB") ?: new University(name: "VŠB - Technická univerzita Ostrava", shortName: "VSB", i18Name: "VSB", posOrder: "16").save(flush: true, failOnError: true)
        universities.add(university)

        university = University.findByI18Name("VSE") ?: new University(name: "Vysoká škola ekonomická v Praze", shortName: "VSE", i18Name: "VSE", posOrder: "17").save(flush: true, failOnError: true)
        universities.add(university)

        university = University.findByI18Name("VSFS") ?: new University(name: "Vysoká škola finanční a správní", shortName: "VŠFS", i18Name: "VSFS", posOrder: "18").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("VSCHT") ?: new University(name: "Vysoká škola chemicko-technologická v Praze", shortName: "VŠCHT", i18Name: "VSCHT", posOrder: "19").save(flush: true, failOnError: true)
        universities.add(university)

        university = University.findByI18Name("VUT") ?: new University(name: "Vysoké učení technické v Brně", shortName: "VUT", i18Name: "VUT", posOrder: "20").save(flush: true, failOnError: true)
        universities.add(university)
        university = University.findByI18Name("ZCU") ?: new University(name: "Západočeská univerzita v Plzni", shortName: "ZČU", i18Name: "ZCU", posOrder: "21").save(flush: true, failOnError: true)
        universities.add(university)







        return universities
    }
}

