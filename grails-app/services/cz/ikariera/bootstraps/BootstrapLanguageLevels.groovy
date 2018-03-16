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
        def languageLevels = LanguageLevel.findByI18Name("a1") ?: new LanguageLevel(nameCZ: "A1 - Začátečník", nameEN: "A1 - Beginner", nameSK: "A1 - Začiatočník", i18Name: "a1", posOrder: "1").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = LanguageLevel.findByI18Name("a2") ?: new LanguageLevel(nameCZ: "A2 - Mírně pokročilý", nameEN: "A2 - Elementary", nameSK: "A2 - Mierne pokročilý", i18Name: "a2", posOrder: "2").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = LanguageLevel.findByI18Name("b1") ?: new LanguageLevel(nameCZ: "B1 - Středně pokročilý", nameEN: "B1 - Intermediate", nameSK: "B1 - Stredne pokročilý", i18Name: "b1", posOrder: "3").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = LanguageLevel.findByI18Name("b2") ?: new LanguageLevel(nameCZ: "B2 - Vyšší", i18Name: "b2", nameEN: "B2 - Upper intermediate", nameSK: "B2 - Vyššý", posOrder: "4").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = LanguageLevel.findByI18Name("c1") ?: new LanguageLevel(nameCZ: "C1 - Pokročilý", nameEN: "C1 - Advanced", nameSK: "C1 - Pokročilý", i18Name: "c1", posOrder: "5").save(flush: true, failOnError: true)
        languages.add(languageLevels)
        languageLevels = LanguageLevel.findByI18Name("c2") ?: new LanguageLevel(nameCZ: "C2 - Jazykově způsobilý",  nameEN: "C2 - Mastery", nameSK: "C2 - Jazykovo spôsobilý", i18Name: "c2", posOrder: "6").save(flush: true, failOnError: true)
        languages.add(languageLevels)


        return languages
    }

}
