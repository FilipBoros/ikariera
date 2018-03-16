package cz.ikariera.bootstraps

import cz.ikariera.student.LanguageType

/*
 http://en.wikipedia.org/wiki/ISO_639-3

 */

/*
 * Edited by Filip Boroš
 * Added: nameEN, nameSK
 */

class BootstrapLanguages {

    public static ArrayList<LanguageType> init() {

        ArrayList<LanguageType> languages = new ArrayList();

        def language = LanguageType.findByI18Name("en") ?: new LanguageType(nameCZ: "Angličtina", nameEN: "English", nameSK: "Angličtina", i18Name: "en", posOrder: "1").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ar") ?: new LanguageType(nameCZ: "Arabština", nameEN: "Arabic", nameSK: "Arabština", i18Name: "ar", posOrder: "2").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("be") ?: new LanguageType(nameCZ: "Běloruština", nameEN: "Belarusian", nameSK: "Bieloruština", i18Name: "be", posOrder: "3").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("cz") ?: new LanguageType(nameCZ: "Čeština", nameEN: "Czech", nameSK: "Čeština", i18Name: "cz", posOrder: "4").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("cn") ?: new LanguageType(nameCZ: "Čínština", nameEN: "Chinese", nameSK: "Čínština", i18Name: "cn", posOrder: "5").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("da") ?: new LanguageType(nameCZ: "Dánština", nameEN: "Czech", nameSK: "Danish", i18Name: "da", posOrder: "6").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("est") ?: new LanguageType(nameCZ: "Estonština", nameEN: "Estonian", nameSK: "Estónčina", i18Name: "est", posOrder: "7").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("fi") ?: new LanguageType(nameCZ: "Finština", nameEN: "Finnish", nameSK: "Fínčina", i18Name: "fi", posOrder: "8").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("frn") ?: new LanguageType(nameCZ: "Francouzština", nameEN: "French", nameSK: "Francúzština", i18Name: "frn", posOrder: "9").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("hb") ?: new LanguageType(nameCZ: "Hebrejština", nameEN: "Hebrew", nameSK: "Hebrejčina", i18Name: "hb", posOrder: "10").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("hl") ?: new LanguageType(nameCZ: "Holandština", nameEN: "Dutch", nameSK: "Holandčina", i18Name: "hl", posOrder: "11").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("it") ?: new LanguageType(nameCZ: "Italština", nameEN: "Italic", nameSK: "Taliančina",i18Name: "it", posOrder: "12").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ja") ?: new LanguageType(nameCZ: "Japonština", nameEN: "Japanese", nameSK: "Japončina", i18Name: "ja", posOrder: "13").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ko") ?: new LanguageType(nameCZ: "Korejština", nameEN: "Korean", nameSK: "Kórejčina", i18Name: "ko", posOrder: "14").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("la") ?: new LanguageType(nameCZ: "Latina", nameEN: "Latin", nameSK: "Latinčina", i18Name: "la", posOrder: "15").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("li") ?: new LanguageType(nameCZ: "Litevština", nameEN: "Lithuanian", nameSK: "Litovčina", i18Name: "li", posOrder: "16").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("lo") ?: new LanguageType(nameCZ: "Lotyština", nameEN: "Latvian", nameSK: "Lotyština", i18Name: "lo", posOrder: "17").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("hu") ?: new LanguageType(nameCZ: "Maďarština", nameEN: "Hungarian", nameSK: "Maďarčina", i18Name: "hu", posOrder: "18").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("de") ?: new LanguageType(nameCZ: "Němčina", nameEN: "German", nameSK: "Nemčina", i18Name: "de", posOrder: "19").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("no") ?: new LanguageType(nameCZ: "Norština", nameEN: "Norwegian", nameSK: "Nórčina", i18Name: "no", posOrder: "20").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("pl") ?: new LanguageType(nameCZ: "Polština", nameEN: "Polish", nameSK: "Poľština", i18Name: "pl", posOrder: "21").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("po") ?: new LanguageType(nameCZ: "Portugalština", nameEN: "Portuguese", nameSK: "Portugalčina", i18Name: "po", posOrder: "22").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("rm") ?: new LanguageType(nameCZ: "Rumunština", nameEN: "Romanian", nameSK: "Rumunčina", i18Name: "rm", posOrder: "23").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ru") ?: new LanguageType(nameCZ: "Ruština", nameEN: "Russian", nameSK: "Ruština", i18Name: "ru", posOrder: "24").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("ge") ?: new LanguageType(nameCZ: "Řečtina", nameEN: "Greek", nameSK: "Gréčtina", i18Name: "ge", posOrder: "25").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("sl") ?: new LanguageType(nameCZ: "Slovinština", nameEN: "Slovene", nameSK: "Slovinčina", i18Name: "sl", posOrder: "26").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("es") ?: new LanguageType(nameCZ: "Španělština", nameEN: "Spanish", nameSK: "Španielčina", i18Name: "es", posOrder: "27").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("sv") ?: new LanguageType(nameCZ: "Švédština", nameEN: "French", nameSK: "Švédština", i18Name: "sv", posOrder: "28").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("tu") ?: new LanguageType(nameCZ: "Turečtina", nameEN: "Turkish", nameSK: "Turečtina", i18Name: "tu", posOrder: "29").save(flush: true, failOnError: true)
        languages.add(language)
        language = LanguageType.findByI18Name("uk") ?: new LanguageType(nameCZ: "Ukrajinština", nameEN: "Ukrainian", nameSK: "Ukrajinčina", i18Name: "uk", posOrder: "30").save(flush: true, failOnError: true)
        languages.add(language)

        return languages

    }


}
