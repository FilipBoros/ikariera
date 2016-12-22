package cz.ikariera.bootstraps

import cz.ikariera.student.StudyCategory

class BootstrapStudyCategories {
    public static ArrayList<StudyCategory> init() {

        ArrayList<StudyCategory> categories = new ArrayList();



        def studyCategory = StudyCategory.findByI18Name('admin') ?: new StudyCategory(name: 'Administrativa', i18Name: 'admin', posOrder: '1').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('architecture') ?: new StudyCategory(name: 'Architektura', i18Name: 'architecture', posOrder: '2').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('cars') ?: new StudyCategory(name: 'Automobilový průmysl', i18Name: 'cars', posOrder: '3').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('bank') ?: new StudyCategory(name: 'Bankovnictví', i18Name: 'bank', posOrder: '4').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('travel') ?: new StudyCategory(name: 'Cestovní ruch', i18Name: 'travel', posOrder: '5').save(flush: true, failOnError: true)
        categories.add(studyCategory)

        studyCategory = StudyCategory.findByI18Name('electrotechnics') ?: new StudyCategory(name: 'Elektrotechnika', i18Name: 'electrotechnics', posOrder: '7').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('energetics') ?: new StudyCategory(name: 'Energetický průmysl', i18Name: 'energetics', posOrder: '8').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('importexport') ?: new StudyCategory(name: 'Export-Import', i18Name: 'importexport', posOrder: '9').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('geodezi') ?: new StudyCategory(name: 'Geodézie', i18Name: 'geodezi', posOrder: '10').save(flush: true, failOnError: true)      //
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('chemical') ?: new StudyCategory(name: 'Chemický a farmaceutický průmysl', i18Name: 'chemical', posOrder: '11').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('it') ?: new StudyCategory(name: 'Informační technologie', i18Name: 'it', posOrder: '12').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('hr') ?: new StudyCategory(name: 'Lidské zdroje a personalistika', i18Name: 'hr', posOrder: '13').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('logistics') ?: new StudyCategory(name: 'Logistika a doprava', i18Name: 'logistics', posOrder: '14').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('marketing') ?: new StudyCategory(name: 'Marketing', i18Name: 'marketing', posOrder: '15').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('media') ?: new StudyCategory(name: 'Media', i18Name: 'media', posOrder: '16').save(flush: true, failOnError: true)
        categories.add(studyCategory)

        studyCategory = StudyCategory.findByI18Name('audit') ?: new StudyCategory(name: 'Poradenství', i18Name: 'audit', posOrder: '18').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('food') ?: new StudyCategory(name: 'Potravinářský průmysl', i18Name: 'food', posOrder: '19').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('law') ?: new StudyCategory(name: 'Právo a legislativa', i18Name: 'law', posOrder: '20').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('buysell') ?: new StudyCategory(name: 'Nákup / Prodej', i18Name: 'buysell', posOrder: '17').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('translate') ?: new StudyCategory(name: 'Překladatelství a tlumočnictví', i18Name: 'translate', posOrder: '21').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('services') ?: new StudyCategory(name: 'Služby', i18Name: 'services', posOrder: '22').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('civil') ?: new StudyCategory(name: 'Stavebnictví', i18Name: 'civil', posOrder: '23').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('machines') ?: new StudyCategory(name: 'Strojírenství', i18Name: 'machines', posOrder: '24').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('telecomunication') ?: new StudyCategory(name: 'Telekomunikace', i18Name: 'telecomunication', posOrder: '25').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('textil') ?: new StudyCategory(name: 'Textilní a kožedělný průmysl', i18Name: 'textil', posOrder: '26').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('design') ?: new StudyCategory(name: 'Tvůrčí ­ práce a design', i18Name: 'design', posOrder: '27').save(flush: true, failOnError: true)
        categories.add(studyCategory)

        studyCategory = StudyCategory.findByI18Name('management') ?: new StudyCategory(name: 'Vrcholový management', i18Name: 'management', posOrder: '29').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('work') ?: new StudyCategory(name: 'Výroba', i18Name: 'work', posOrder: '30').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('edu') ?: new StudyCategory(name: 'Výuka a vzdělávání', i18Name: 'edu', posOrder: '31').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('science') ?: new StudyCategory(name: 'Věda a výzkum', i18Name: 'science', posOrder: '28').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('health') ?: new StudyCategory(name: 'Zdravotnictví a farmacie', i18Name: 'health', posOrder: '32').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('agriculture') ?: new StudyCategory(name: 'Zemědělství', i18Name: 'agriculture', posOrder: '33').save(flush: true, failOnError: true)
        categories.add(studyCategory)
        studyCategory = StudyCategory.findByI18Name('economy') ?: new StudyCategory(name: 'Ekonomie', i18Name: 'economy', posOrder: '6').save(flush: true, failOnError: true)
        categories.add(studyCategory)




        return categories
    }
}

