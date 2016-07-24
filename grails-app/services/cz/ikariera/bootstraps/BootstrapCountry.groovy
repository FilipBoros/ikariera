package cz.ikariera.bootstraps

import cz.ikariera.admin.Country


class BootstrapCountry {
    public static ArrayList<Country> init() {

        ArrayList<Country> countries = new ArrayList();

        def country = new Country(name: 'Czech Republic', i18Name: 'czech', posOrder: 1).save(flush: true, failOnError: true)
        countries.add(country)


        return countries
    }
}

