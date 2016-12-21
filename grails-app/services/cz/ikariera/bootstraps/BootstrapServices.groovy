package cz.ikariera.bootstraps

import cz.ikariera.company.Services

class BootstrapServices {


    public static ArrayList<Services> init() {


        ArrayList<Services> services = new ArrayList();


        services.add(Services.findByUniqueName("send-mail-var") ?: new Services(

                uniqueName: "send-mail-var",
                name: "Hromadny mailing cena za odeslani jednoho emailu",
                creditPrice: 0.01,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))


        services.add(Services.findByUniqueName("send-mail-fix") ?: new Services(
                uniqueName: "send-mail-fix",
                name: "Hromadny mailing cena za odeslani jednoho emailu",
                creditPrice: 20,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))


        services.add(Services.findByUniqueName("mail-service") ?: new Services(

                uniqueName: "mail-service",
                name: "Hromadny mailing",
                creditPrice: 20,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))


        services.add(Services.findByUniqueName("banner-service") ?: new Services(

                uniqueName: "banner-service",
                name: "Banery",
                creditPrice: 20,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))

        services.add(Services.findByUniqueName("top") ?: new Services(

                uniqueName: "top",
                name: "Topnabidka",
                creditPrice: 20,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))

        services.add(Services.findByUniqueName("advanced-profile-service") ?: new Services(

                uniqueName: "advanced-profile-service",
                name: "Rozšířený profil",
                creditPrice: 20,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))

        services.add(Services.findByUniqueName("cv-service") ?: new Services(

                uniqueName: "cv-service",
                name: "CV Search",
                creditPrice: 20,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))


        services.add(Services.findByUniqueName("partner-service") ?: new Services(

                uniqueName: "partner-service",
                name: "Logo partner portálu iKariera",
                creditPrice: 200,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))


        services.add(Services.findByUniqueName("general-partner-service") ?: new Services(

                uniqueName: "general-partner-service",
                name: "Generální partner cz.cz.ikariera",
                creditPrice: 200,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))

        services.add(Services.findByUniqueName("articles-service") ?: new Services(

                uniqueName: "articles-service",
                name: "Služba články",
                creditPrice: 20,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))


        services.add(Services.findByUniqueName("credits") ?: new Services(

                uniqueName: "credits",
                name: "Přidání kreditů",
                creditPrice: 0,
                description: "Kecy keci keci keci keci",
                posOrder: 1).save(failOnError: true))


        return services
    }
}

