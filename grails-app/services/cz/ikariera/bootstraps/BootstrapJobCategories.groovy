package cz.ikariera.bootstraps

import cz.ikariera.company.JobCategory

class BootstrapJobCategories {
    public static ArrayList<JobCategory> init() {

        ArrayList<JobCategory> categories = new ArrayList();

        def jobCategory = new JobCategory(name: 'Administrativa', i18Name: 'admin', posOrder: 1).save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Architektura', i18Name: 'architecture', posOrder: '2').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Automobilový průmysl', i18Name: 'cars', posOrder: '3').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Bankovnictví', i18Name: 'bank', posOrder: '4').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Cestovní ruch', i18Name: 'travel', posOrder: '5').save(flush: true, failOnError: true)
        categories.add(jobCategory)

        jobCategory = new JobCategory(name: 'Elektrotechnika', i18Name: 'electrotechnics', posOrder: '7').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Energetický průmysl', i18Name: 'energetics', posOrder: '8').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Export-Import', i18Name: 'importexport', posOrder: '9').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Geodézie', i18Name: 'geodezi', posOrder: '10').save(flush: true, failOnError: true)      //
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Chemický a farmaceutický průmysl', i18Name: 'chemical', posOrder: '11').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Informační technologie', i18Name: 'it', posOrder: '12').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Lidské zdroje a personalistika', i18Name: 'hr', posOrder: '13').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Logistika a doprava', i18Name: 'logistics', posOrder: '14').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Marketing', i18Name: 'marketing', posOrder: '15').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Media', i18Name: 'media', posOrder: '16').save(flush: true, failOnError: true)
        categories.add(jobCategory)

        jobCategory = new JobCategory(name: 'Poradenství', i18Name: 'audit', posOrder: '18').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Potravinářský průmysl', i18Name: 'food', posOrder: '19').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Právo a legislativa', i18Name: 'law', posOrder: '20').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Nákup / Prodej', i18Name: 'buysell', posOrder: '17').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Překladatelství a tlumočnictví', i18Name: 'translate', posOrder: '21').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Služby', i18Name: 'services', posOrder: '22').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Stavebnictví', i18Name: 'civil', posOrder: '23').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Strojírenství', i18Name: 'machines', posOrder: '24').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Telekomunikace', i18Name: 'telecomunication', posOrder: '25').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Textilní a kožedělný průmysl', i18Name: 'textil', posOrder: '26').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Tvůrčí ­ práce a design', i18Name: 'design', posOrder: '27').save(flush: true, failOnError: true)
        categories.add(jobCategory)

        jobCategory = new JobCategory(name: 'Vrcholový management', i18Name: 'management', posOrder: '29').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Výroba', i18Name: 'work', posOrder: '30').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Výuka a vzdělávání', i18Name: 'edu', posOrder: '31').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Věda a výzkum', i18Name: 'science', posOrder: '28').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Zdravotnictví a farmacie', i18Name: 'health', posOrder: '32').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Zemědělství', i18Name: 'agriculture', posOrder: '33').save(flush: true, failOnError: true)
        categories.add(jobCategory)
        jobCategory = new JobCategory(name: 'Ekonomie', i18Name: 'economy', posOrder: '6').save(flush: true, failOnError: true)
        categories.add(jobCategory)

        return categories
    }
}

