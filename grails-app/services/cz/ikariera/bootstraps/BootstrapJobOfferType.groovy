package cz.ikariera.bootstraps

import cz.ikariera.company.JobOfferType

class BootstrapJobOfferType {
    public static ArrayList<JobOfferType> init() {

        ArrayList<JobOfferType> jobOfferTypes = new ArrayList();

        def jobofferType = new JobOfferType(name: 'fullTimeJob', i18Name: 'fullTimeJob', specOrder: 1).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = new JobOfferType(name: 'partTimeJob', i18Name: 'partTimeJob', specOrder: 2).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = new JobOfferType(name: 'diplomaThesis', i18Name: 'diplomaThesis', specOrder: 3).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = new JobOfferType(name: 'traineeship', i18Name: 'traineeship', specOrder: 4).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)

        jobofferType = new JobOfferType(name: 'bacholeorThesis', i18Name: 'bacholeorThesis', specOrder: 5).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        jobofferType = new JobOfferType(name: 'disertationThesis', i18Name: 'disertationThesis', specOrder: 6).save(flush: true, failOnError: true)
        jobOfferTypes.add(jobofferType)


        return jobOfferTypes
    }
}

