package cz.ikariera.bootstraps

import cz.ikariera.student.LanguageLevel

/**
 * Created with IntelliJ IDEA.
 * User: cernydav
 * Date: 8/10/12
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
class BootstrapLanguageLevels {

    public static ArrayList<LanguageLevel> init() {

        ArrayList<LanguageLevel> languages = new ArrayList();

        // language levels
        def languageLevels = new LanguageLevel(name: "A1 - Pokročilý začátečník", i18Name: "a1", posOrder: "1").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = new LanguageLevel(name: "A2 - Mírně pokročilý", i18Name: "a2", posOrder: "2").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = new LanguageLevel(name: "B1 - Středně pokročilý", i18Name: "b1", posOrder: "3").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = new LanguageLevel(name: "B2 - Vyšší", i18Name: "b2", posOrder: "4").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = new LanguageLevel(name: "C1 - Pokročilý", i18Name: "c1", posOrder: "5").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = new LanguageLevel(name: "C2 - Jazykově způsobilý", i18Name: "c2", posOrder: "6").save(flush: true, failOnError: true)
        languages.add(languageLevels)


        return languages
    }

}
