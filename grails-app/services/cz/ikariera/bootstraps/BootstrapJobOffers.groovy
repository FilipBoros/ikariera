package cz.ikariera.bootstraps

import cz.ikariera.admin.Country
import cz.ikariera.company.*
import cz.ikariera.security.User
import cz.ikariera.student.LanguageType
import cz.ikariera.student.SkillType
import org.apache.commons.logging.LogFactory

class BootstrapJobOffers {
    private static final log = LogFactory.getLog(this)

    public static ArrayList<JobOffer> init(
            ArrayList<Company> companies,
            ArrayList<JobCategory> jobCategories,
            ArrayList<Locality> localities,
            ArrayList<Country> countries,
            ArrayList<LanguageType> languages,
            ArrayList<SkillType> skills,
            ArrayList<JobOfferType> jobOfferTypes

    ) {

        ArrayList<JobOffer> jobOffers = new ArrayList();

        def dateExpired = new Date();
        dateExpired.setYear(2013)

        companies.eachWithIndex { it, i ->


            def companyUser =  User.findByUsername('company@cz.cz.ikariera' + i + '.cz') ?: new User(
                    username: 'company@cz.cz.ikariera' + i + '.cz',
                    firstName: 'Jon' + i,
                    lastName: 'Connor' + i,
                    password: "fdadsgdsgdsgsdgfda",
                    accountExpired: false,
                    accountLocked: false,
                    enabled: true,
                    passwordExpired: false,
            ).save(failOnError: true)


            def companyAccountUser1 = CompanyAccount.findByUser(companyUser) ?: new CompanyAccount(
                    titleBefore: "Ing.",
                    telephone: "+420 723 327 901",
                    publicEmail: "kdsfsdfdsfdsfsdfarl@seznam.cz",
                    user: companyUser,
                    positionInCompany: "říďa",

            ).save(failOnError: true)

            for (int j; j < 10; j++) {
                Random r = new Random(100)
                int randomHour = r.nextInt() % 60
                int randomMinute = r.nextInt() % 60
                int randomDay = r.nextInt() % 31
                Date randomDate = new Date()
                randomDate.setHours(randomHour)
                randomDate.setMinutes(randomMinute)
                randomDate.setDate(randomDay)

                def jobOffer1 = new JobOffer(
                        positionName: "Lektor/ka počítačových školení Microsoft Office" + i + j,
                        positionLocality: localities.get(i % localities.size()),
                        positionCountry: countries.first(),
                        company: it,
                        jobOfferType: jobOfferTypes.get(i % jobOfferTypes.size()),
                        datePublished: randomDate,

                        jobOfferDescription: """- návrh a programování softwaru podle požadavků
                        - ověřování statusu softwarových chyb, testování a modifikování softwaru
                        - komunikace s testery, dokumentátory a pracovníky podpory
                        -    cestování do zahraničí může být nutné pro servis na místě, pomoc centru podpory nebo komunikaci s managementem produktu
- tvorba analýzy na základě požadavků
- tvorba testovacích plánů

Popis společnosti:
- mezinárodní SW společnost

Pracoviště:
- Prostějov

Typ pracovního poměru:
- jedná se o zaměstnanecký pracovní poměr, plný úvazek na dobu určitou (8 měsíců)
- práci nelze vykonávat při studiu
Programmers""" + i + j,

                        jobApplicantRequire: """- zajímavé finanční ohodnocení, řada benefitů (stravenky, jazykové kurzy, široký systém volitelných výhod, firemní půjčky, možnost dalšího školení a vzdělávání..)


Pokud Vás nabídka zaměstnání zaujala, zašlete nám, prosím, jako přílohu e-mailu profesní životopis v českém a anglickém jazyce, případně nás kontaktujte telefonicky. Uveďte také kód pozice, o kterou se zajímáte.
Uchazeče, kteří postoupí do užšího kola, budeme kontaktovat nejpozději do 10 dnů od zaslání životopisu. Ostatní uchazeče zařadíme do databáze a budeme je kontaktovat v případě, že pro ně nalezneme jinou vhodnou nabídku.
Pokud bychom se do 10 dnů neozvali, bohužel jste nepostoupil/a do užšího výběrového kola. Vyrozumění o neúspěchu nerozesíláme, můžete se ale samozřejmě informovat telefonicky.


Moc penez""" + i + j,

                        jobTypeDescription: """dobrá znalost Javy (případně výborná znalost C++), architektury klient/server, objektového programování
- orientace v programovacích technikách
- dobrá znalost MS Windows a MS Office
- komunikativní znalost AJ nebo NJ, dobrá znalost terminologie IT
- pozice je vhodná pro absolventy i zkušené kandidáty s delší praxí
- VŠ nebo SŠ vzdělání
- řidičský průkaz sk. B
- spolehlivost, aktivní přístup, týmový hráč, komunikativnost, schopnost a ochota učit se, vítána schopnost přinášet aktivní podněty




Java""" + i + j,
                        jobStartDate: new Date(),



                        willExpire: dateExpired,
                        topPos: (j % 5 == 0),
                        //  companyAccountUser: companyAccountUser1


                )


                def languageReq = new ReqLangCombination(

                        languageType: languages.get(j % languages.size()),
                        languageSeparator: "A",
                        jobOffer: jobOffer1

                )


                jobOffer1.addToRequieredLanguages(languageReq.languageType)
                jobOffer1.addToJobCategories(jobCategories.get(i % jobCategories.size()))
                jobOffer1.addToJobCategories(jobCategories.get((i + 1) % jobCategories.size()))

                jobOffer1.save(failOnError: true)

                jobOffers.add(jobOffer1)
            }
        }
        return jobOffers
    }
}

