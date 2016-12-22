package cz.ikariera.bootstraps

import cz.ikariera.company.JobOfferType

class BootstrapJobOfferType {
    public static ArrayList<JobOfferType> init() {

        ArrayList<JobOfferType> jobOfferTypes = new ArrayList();

        def jobofferType = JobOfferType.findByI18Name('fullTimeJob') ?: new JobOfferType(name: 'fullTimeJob', i18Name: 'fullTimeJob', specOrder: 1).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = JobOfferType.findByI18Name('partTimeJob') ?: new JobOfferType(name: 'partTimeJob', i18Name: 'partTimeJob', specOrder: 2).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = JobOfferType.findByI18Name('diplomaThesis') ?: new JobOfferType(name: 'diplomaThesis', i18Name: 'diplomaThesis', specOrder: 3).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = JobOfferType.findByI18Name('traineeship') ?: new JobOfferType(name: 'traineeship', i18Name: 'traineeship', specOrder: 4).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)

        jobofferType = JobOfferType.findByI18Name('bacholeorThesis') ?: new JobOfferType(name: 'bacholeorThesis', i18Name: 'bacholeorThesis', specOrder: 5).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = JobOfferType.findByI18Name('disertationThesis') ?: new JobOfferType(name: 'disertationThesis', i18Name: 'disertationThesis', specOrder: 6).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        return jobOfferTypes
    }
}

