package cz.ikariera.bootstraps

import cz.ikariera.student.LanguageType

/**
 http://en.wikipedia.org/wiki/ISO_639-3

 */


class BootstrapLanguages {

    public static ArrayList<LanguageType> init() {

        ArrayList<LanguageType> languages = new ArrayList();

        def language = LanguageType.findByI18Name("en") ?: new LanguageType(name: "angličtina", i18Name: "en", posOrder: "1").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ar") ?: new LanguageType(name: "arabština", i18Name: "ar", posOrder: "2").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("be") ?: new LanguageType(name: "běloruština", i18Name: "be", posOrder: "3").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("cz") ?: new LanguageType(name: "čeština", i18Name: "cz", posOrder: "4").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("cn") ?: new LanguageType(name: "čínština", i18Name: "cn", posOrder: "5").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("da") ?: new LanguageType(name: "dánština", i18Name: "da", posOrder: "6").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("est") ?: new LanguageType(name: "estonština", i18Name: "est", posOrder: "7").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("fi") ?: new LanguageType(name: "finština", i18Name: "fi", posOrder: "8").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("frn") ?: new LanguageType(name: "francouzština", i18Name: "frn", posOrder: "9").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("hb") ?: new LanguageType(name: "hebrejština", i18Name: "hb", posOrder: "10").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("hl") ?: new LanguageType(name: "holandština", i18Name: "hl", posOrder: "11").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("it") ?: new LanguageType(name: "italština", i18Name: "it", posOrder: "12").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ja") ?: new LanguageType(name: "japonština", i18Name: "ja", posOrder: "13").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ko") ?: new LanguageType(name: "korejština", i18Name: "ko", posOrder: "14").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("la") ?: new LanguageType(name: "latina", i18Name: "la", posOrder: "15").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("li") ?: new LanguageType(name: "litevština", i18Name: "li", posOrder: "16").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("lo") ?: new LanguageType(name: "lotyština", i18Name: "lo", posOrder: "17").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("hu") ?: new LanguageType(name: "maďarština", i18Name: "hu", posOrder: "18").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("de") ?: new LanguageType(name: "němčina", i18Name: "de", posOrder: "19").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("no") ?: new LanguageType(name: "norština", i18Name: "no", posOrder: "20").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("pl") ?: new LanguageType(name: "polština", i18Name: "pl", posOrder: "21").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("po") ?: new LanguageType(name: "portugalština", i18Name: "po", posOrder: "22").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("rm") ?: new LanguageType(name: "rumunština", i18Name: "rm", posOrder: "23").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ru") ?: new LanguageType(name: "ruština", i18Name: "ru", posOrder: "24").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ge") ?: new LanguageType(name: "řečtina", i18Name: "ge", posOrder: "25").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("sl") ?: new LanguageType(name: "slovinština", i18Name: "sl", posOrder: "26").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("es") ?: new LanguageType(name: "španělština", i18Name: "es", posOrder: "27").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("sv") ?: new LanguageType(name: "švédština", i18Name: "sv", posOrder: "28").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("tu") ?: new LanguageType(name: "turečtina", i18Name: "tu", posOrder: "29").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("uk") ?: new LanguageType(name: "ukrajinština", i18Name: "uk", posOrder: "30").save(flush: true, failOnError: true)
        languages.add(language)

        return languages

    }


}
