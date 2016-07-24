package cz.ikariera.bootstraps


import cz.ikariera.company.Locality

class BootstrapPositionLocalities {
    public static ArrayList<Locality> init() {

        ArrayList<Locality> localities = new ArrayList();

        def locality = new Locality(name: 'Středočeský', i18Name: 'centralbohemian', posOrder: "1").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Jihočeský', i18Name: 'southbohemian', posOrder: "2").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Plzeňský', i18Name: 'pilsen', posOrder: "3").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Karlovarský', i18Name: 'karlovarsky', posOrder: "4").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Ústecký', i18Name: 'ustecky', posOrder: "5").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Liberecký', i18Name: 'liberecky', posOrder: "6").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Královehradecký', i18Name: 'kralovehradecky', posOrder: "7").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Pardubický', i18Name: 'pardubicky', posOrder: "8").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Vysočina', i18Name: 'vysocina', posOrder: "9").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Jihomoravský', i18Name: 'jihomoravsky', posOrder: "10").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Olomoucký', i18Name: 'olomocky', posOrder: "11").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Zlínský', i18Name: 'zlinsky', posOrder: "12").save(flush: true, failOnError: true)
        localities.add(locality)
        locality = new Locality(name: 'Moravskoslezský', i18Name: 'moravskoslezky', posOrder: "13").save(flush: true, failOnError: true)
        localities.add(locality)


        return localities
    }
}

