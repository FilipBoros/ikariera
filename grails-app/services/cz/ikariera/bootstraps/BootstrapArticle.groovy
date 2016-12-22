package cz.ikariera.bootstraps

import cz.ikariera.company.Articles
import cz.ikariera.company.Company

class BootstrapArticle {
    public static ArrayList<Articles> init(
            ArrayList<Company> companies

    ) {

        ArrayList<Articles> articles = new ArrayList();

        def article = Articles.findByCompany(companies.first()) ?: new Articles(
                    header: "UK: Šance i pro absolventy technických fakult",
                    bodyText: " Nabídka zaměstnání pro vedoucího vědecké skupiny, postdoktorandy, studenty doktorského studia a techniky\n" +
                            "\n" +
                            " \n" +
                            "\n" +
                            "            Ústav buněčné biologie a patologie 1. lékařské fakulty UK v Praze (ÚBBP) přijme kvalifikovaného, samostatného vědeckého pracovníka (vedoucího vědecké skupiny), postdoktorandy a Ph.D. studenty v  buněčné biologii a příbuzných oborech. Kromě vědeckých pozic hledá ÚBBP pracovníka se vzděláním technického směru pro správu mikroskopických systémů a se zájmem o vývoj nových metodologií a instrumentace.\n" +
                            "\n" +
                            "\n" +
                            "            ÚBBP se především zabývá strukturně-funkční organizací buněčného jádra. Výzkumná práce je zaměřena zejména na organizaci chromatinových domén vyššího řádu, integraci procesů DNA replikace a RNA syntézy a maturace do jaderných substruktur, a organizaci chromatinového vlákna. Pro vědecké experimenty jsou na pracovišti využívány genetické, biochemické, molekulárně biologické, strukturně biologické a buněčně biologické přístupy. Významnou měrou se využívají moderní metody světelné a elektronové mikroskopie včetně \"live cell imaging\", superrezoluční mikroskopie, korelační světelné a elektronové mikroskopie, elektronmikroskopické tomografie a kryo-elektronové mikroskopie spojené s 3D rekonstrukcí izolovaných makromolekul a jejich komplexů. Na pracovišti je rovněž vyvíjen nový typ programovatelného maticového mikroskopu. ÚBBP proniklo v posledních letech  i do oblasti nanotechnologií.\n" +
                            "\n" +
                            "\n" +
                            "            Nabízíme vynikající podmínky pro vědeckou práci na špičkovém pracovišti v oboru, výborně vybavené laboratoře včetně moderních světelných a elektronových mikroskopů a nadstandardní finanční ohodnocení. Zájemce o místo vedoucího vědecké skupiny žádáme, aby zaslali odborný životopis spolu s abstraktem návrhu vědeckého projektu (max. 1 strana) a alespoň dvěma referenčními údaji. Zájemce o doktorandské studium a o místa techniků žádáme o zaslání životopisu a zájemce o postdoktorandská místa o zaslání životopisu spolu se seznamem publikací a alespoň dvěma referenčními údaji. Vyžadované materiály zasílejte laskavě na níže uvedenou emailovou adresu.\n" +
                            "\n" +
                            " \n" +
                            "\n" +
                            "Prof. RNDr. Ivan Raška, DrSc.\n" +
                            "\n" +
                            "Ústav buněčné biologie a patologie\n" +
                            "1. lékařská fakulta UK v Praze\n" +
                            "Albertov 4, 128 00 Praha 2\n" +
                            "\n" +
                            "Tel.: +420-224968001, Fax: +420-224917418\n" +
                            "email: lge@lf1.cuni.cz\n" +
                            "web: http://lge.lf1.cuni.cz ",
                    company: companies.first(),
                    // contact: ,
                    datePublished: new Date(),
                    willExpire: new Date() + 30


            ).save(flush: true, failOnError: true)

        articles.add(article)


        return articles
    }
}

