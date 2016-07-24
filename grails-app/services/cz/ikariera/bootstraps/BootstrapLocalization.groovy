package cz.ikariera.bootstraps

import cz.ikariera.admin.Localization


class BootstrapLocalization {
    public static ArrayList<Localization> init() {

        ArrayList<Localization> localizations = new ArrayList();

        def localization = new Localization(name: 'Czech', i18Name: 'cz-CS', posOrder: 1).save(flush: true, failOnError: true)
        localizations.add(localization)



         localization = new Localization(name: 'English', i18Name: 'en-EN', posOrder: 2).save(flush: true, failOnError: true)
        localizations.add(localization)

        return localizations
    }
}

