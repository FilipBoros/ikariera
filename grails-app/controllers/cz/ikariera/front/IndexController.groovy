package cz.ikariera.front

import cz.ikariera.admin.Contact
import cz.ikariera.admin.Partner
import cz.ikariera.company.Company
import cz.ikariera.company.HeroImage
import cz.ikariera.company.JobOffer

class IndexController {

    def index() {

        //def temp = JobOffer.countJobCategory("Administrativa")
        //  def category = JobCategory.findByName("Administrativa")
        // def count = JobOffer.countByJobCategories(category)

        def generalPartner = null
        def partnerInstanceList = Partner.createCriteria().list() {
            order('posOrder', 'asc')
        }

        int counter = 0
        List<JobOffer> jobOfferList = JobOffer.listOrderByDatePublished(max: 10, order: "desc")
        List<JobOffer> jobOfferListFinal = new ArrayList<JobOffer>()
        //jobOfferList.eachWithIndex { JobOffer joboffer, int i ->
        for (int j = 0; j < jobOfferList.size(); j++) {
            def joboffer = jobOfferList.get(j)
            if (counter >= 5) {
                break;
            }
            if (joboffer.company.active) {
                jobOfferListFinal.add(joboffer)
                counter++
            }
        }
        if (counter < 5) {
            //if jobOfferListFinal doesnt have 5 jobofers then read more
            jobOfferList = JobOffer.listOrderByDatePublished(max: 50, order: "desc")
            //jobOfferList.eachWithIndex { JobOffer joboffer, int i ->
            for (int j = 0; j < jobOfferList.size(); j++) {
                def joboffer = jobOfferList.get(j)
                if (counter >= 5) {
                    break;
                }
                if (joboffer.company.active) {
                    jobOfferListFinal.add(joboffer)
                    counter++
                }
            }
        }
        if (counter < 5) {
            //if jobOfferListFinal still doesnt have 5 jobofers then read more
            jobOfferList = JobOffer.listOrderByDatePublished(max: 150, order: "desc")
            //jobOfferList.eachWithIndex { JobOffer joboffer, int i ->
            for (int j = 0; j < jobOfferList.size(); j++) {
                def joboffer = jobOfferList.get(j)
                if (counter >= 5) {
                    break;
                }
                if (joboffer.company.active) {
                    jobOfferListFinal.add(joboffer)
                    counter++
                }
            }
        }


        ArrayList<HeroImage> heroImageArrayList = new ArrayList<HeroImage>()

        if (jobOfferListFinal) {

            jobOfferListFinal.eachWithIndex { JobOffer joboffer, int i ->

                heroImageArrayList.add(getHeroImage(joboffer))
            }
        }

        def partnerCompanyInstance = Company.createCriteria().get {

            servicesExpire {

                ge('topProfile', new Date())
            }


            maxResults(1)


        }


        def contactInstance = Contact.findByContactIdentify("IAESTE")

        //def targetUri = params.targetUri

        render(view: 'index',
                model: [
                        generalPartner        : generalPartner,
                        partnerCompanyInstance: partnerCompanyInstance,
                        partnerInstanceList   : partnerInstanceList,
                        heroImageArrayList    : heroImageArrayList,
                        jobOfferList          : jobOfferListFinal,
                        contactInstance       : contactInstance
                ])
    }

    def getLastPage() {
        /*def a = params
        def targetUri = params.targetUri ?: "/"
        redirect(uri: targetUri)*/
        redirect(uri: request.getHeader('referer') )
    }

    private def getHeroImage(JobOffer jobOffer) {
        if (jobOffer == null)
            return null
        def img

        img = runImageQuery({ jobOffer.company == null }, {
            and {
                isNotNull("company")
                eq('company', jobOffer.company)
            }
        })

        if (img != null) {
            return img
        }

        img = runImageQuery({ jobOffer.jobCategories.size() == 0 }, {
            and {
                isNotEmpty("jobCategories")
                jobCategories {
                    'in'('id', jobOffer.jobCategories*.id)
                }
            }
        })

        if (img != null) {
            return img
        }
        img = runImageQuery({ jobOffer.positionLocality == null }, {
            and {
                isNotEmpty("localities")
                localities {
                    eq('id', jobOffer.positionLocalityId)
                }
            }
        })
        if (img != null) {
            return img
        }
        img = runImageQuery({ jobOffer.positionCountry == null }, {
            isNotEmpty("countries")
            countries {
                eq('id', jobOffer.positionCountryId)
            }
        })

        if (img != null) {
            return img
        }
        return HeroImage.first()
    }

    private def runImageQuery(def check, def query) {
        if (check()) return null
        def result = HeroImage.createCriteria().listDistinct query
        if (result.isEmpty()) {
            return null
        }
        return result.first()
    }

    def projects() {
        render(view: '/ajax/projectsColumn')
    }

    def fakeAction() {
        def u = params.f as String
        def lang = params.lang as String

        if (u == null || lang == null) {
            render view: '/notFound'
            return
        }
        def a = params
        if (u.contains("/companies") && lang.equals("cs_CZ")) {
            u = u.replace("companies", "firemni-profily")
        } else if (u.contains("/firemni-profily") && lang.equals("en_US")) {
            u = u.replace("firemni-profily", "companies")
        }

        if (u.contains("/job-offers") && lang.equals("cs_CZ")) {
            u = u.replace("job-offers", "nabidky-prace")
        } else if (u.contains("/nabidky-prace") && lang.equals("en_US")) {
            u = u.replace("nabidky-prace", "job-offers")
        }

        if (u.contains("/tips-and-articles") && lang.equals("cs_CZ")) {
            u = u.replace("tips-and-articles", "clanky-a-rady")
        } else if (u.contains("/clanky-a-rady") && lang.equals("en_US")) {
            u = u.replace("clanky-a-rady", "tips-and-articles")
        }

        redirect(uri: u, params: params)//add params...
    }

    private def invertLang(def u, def lang) {
        if (u.contains("tips-and-articles") && lang.equals("cs_CZ")) {
            u = u.replace("tips-and-articles", "clanky-a-rady")
        } else if (u.contains("clanky-a-rady") && lang.equals("en_US")) {
            u = u.replace("clanky-a-rady", "tips-and-articles")
        }
        return u
    }

    def veleterhy() {
        //http://www.veletrhyikariera.cz/
        redirect(url: "http://www.veletrhyikariera.cz/")
    }

}
