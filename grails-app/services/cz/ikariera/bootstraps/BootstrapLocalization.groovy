package cz.ikariera.bootstraps

import cz.ikariera.admin.Localization


class BootstrapLocalization {
    public static ArrayList<Localization> init() {

        ArrayList<Localization> localizations = new ArrayList();

        def localization = Localization.findByI18Name('cz-CS') ?: new Localization(name: 'Czech', i18Name: 'cz-CS', posOrder: 1).save(flush: true, failOnError: true)
        localizations.add(localization)

        localization = Localization.findByI18Name('en-EN') ?: new Localization(name: 'English', i18Name: 'en-EN', posOrder: 2).save(flush: true, failOnError: true)
        localizations.add(localization)

        /*Added by Michal Dolnak 22.12.2016*/
        localization = Localization.findByI18Name('sk') ?: new Localization(name: 'Slovak', i18Name: 'sk', posOrder: 3).save(flush: true, failOnError: true)
        localizations.add(localization)

        return localizations
    }
}

