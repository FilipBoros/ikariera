package cz.ikariera.front

import cz.ikariera.admin.Contact
import cz.ikariera.company.Company
import cz.ikariera.company.JobCategory
import cz.ikariera.company.JobOffer
import cz.ikariera.company.Locality

class CompanyProfilesController {



    def index(CompanyProfilesFilterCommand filterCommand) {

        // List mainJobCategories = JobCategory.getAll(params.list('mainJobCategories'))
        //  params.mainJobCategories = mainJobCategories

        def list = params.list('mainJobCategories').collect { it as Long }
        def listLocalities = params.list('localities').collect { it as Long }

        filterCommand.max = Math.min(params.int('max') ? params.int('max') : 10, 20)
        filterCommand.offset = params.int('offset') ? params.int('offset') : 0
        //filterCommand.order = params.order ? params.order : "asc"
        filterCommand.sort = params.sort ? params.sort : "companyName"

        filterCommand.mainJobCategories = params.list('mainJobCategories')
        filterCommand.localities = params.list('localities')



        def companyInstanceListOriginal = Company.companiesFilter(filterCommand.filterParams)






        //if(params.order!="desc" || params.order!="asc"){
        //    params.order="desc"
       // }

    //    def preFilteredCompanyInstanceListDefault = Company.companiesFilter(filterCommand.filterParams)

     //   def companyInstanceList1 = preFilteredCompanyInstanceListDefault.sort { it.getTopProfile() }
        //companyInstanceList1.removeAll {it.getTopProfile() < new Date()}
        //companyInstanceList1.removeAll {it.getTopProfile() == null}


        //test this controller
        //def listResult = TopCompanyController.getListOfTopCompanies("id", filterCommand.offset)

/*

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        List purchaseList = Purchase.createCriteria().list(params) {
            order('datePurchased', 'desc')

        }
        def listSize = purchaseList?.totalCount








        def serviceTopProfiles =  ServicesExpire.list(sort: "company.companyName"getTopProfile, w  )

        serviceTopProfiles.totalCount*/


        //CompanyProfilesFilterCommand filterCommand2 = new CompanyProfilesFilterCommand()
       // filterCommand2.max = filterCommand.max - companyInstanceList1.size()
       // filterCommand2.offset = params.int('offset') ? params.int('offset') : 0
       // filterCommand2.offset = companyInstanceList1.totalCount - filterCommand.offset





        /*CompanyProfilesFilterCommand filterCommand1 = new CompanyProfilesFilterCommand()
        filterCommand1.max = Math.min(params.int('max') ? params.int('max') : 10, 20)
        filterCommand1.offset = params.int('offset') ? params.int('offset') : 0*/








     //   def preFilteredCompanyInstanceListDefault2 = Company.companiesFilter(filterCommand2.filterParams)
     //   def companyInstanceList2 = preFilteredCompanyInstanceListDefault2.sort { it.getJobOfferCount() }
     //   companyInstanceList2= companyInstanceList2.reverse(true)
        //companyInstanceList2.removeAll {it.getTopProfile() >= new Date()}

      //  def companyInstanceList = Company.companiesFilter(filterCommand.filterParams)
      //  companyInstanceList.clear()
      //  companyInstanceList = companyInstanceList1.plus(companyInstanceList2)


        /*def companyInstanceList = Company.findAll("\
select count(*) from Company as c\
")*/
        def contactInstance = Contact.findByContactIdentify("IAESTE")
        params.mainJobCategories = list
        params.localities = listLocalities

        render view: 'index',
                model: [
                        companyInstanceListTotal: companyInstanceListOriginal.totalCount,
                        companyInstanceList     : companyInstanceListOriginal,
                        contactInstance     : contactInstance

                ],
                params: params

    }


    def detail() {
        Long companyId
        try {
            companyId = Long.parseLong(params.id)
        }
        catch (Exception e) {
            render(view: "/notFound")
            return
        }
        Company companyInstance = Company.get(companyId)

        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 10, 100)

        params.sort=null



        if (!companyInstance) {
            render(view: "/notFound")
            return
        }

        List jobOfferInstanceList = JobOffer.createCriteria().list(params) {
            eq('company', companyInstance)
            gt('willExpire', new Date())
            isNotNull('datePublished')

            order('datePublished', 'desc')

        }
        def jobOfferInstanceListTotal = jobOfferInstanceList.totalCount
        def contactInstance = Contact.findByContactIdentify("IAESTE")

        render view: 'detail',
                model: [
                        companyInstance: companyInstance,
                        jobOfferInstanceListTotal: jobOfferInstanceListTotal,
                        jobOfferInstanceList: jobOfferInstanceList,
                        contactInstance     : contactInstance


                ],
                params: params


    }
}

/**
 * Command class for binding filter data from controller
 */
class CompanyProfilesFilterCommand {

    List<JobCategory> mainJobCategories = []
    List<Locality> localities = []

    Boolean personalAgency

    String companyName
    String companyID

    Integer max
    Integer offset

    String sort
    String order

    // setter for JobCategories (workaround for dynamicBinding)
    public setMainJobCategories(List<String> mainJobCategories) {
        List<JobCategory> newJobCategories = []
        mainJobCategories.each {
            def jobCategory = JobCategory.get(it)
            if (jobCategory)
                newJobCategories.add(jobCategory)
        }
        this.mainJobCategories = newJobCategories
    }

    // setter for JobCategories (workaround for dynamicBinding)
    public setLocalities(List<String> localities) {
        List<Locality> newLocalities = []
        localities.each {
            def locality = Locality.get(it)
            if (locality)
                newLocalities.add(locality)
        }
        this.localities = newLocalities
    }

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << [
                "mainJobCategories": mainJobCategories,

                "companyName"      : companyName,
                "companyID"        : companyID,
                "max"              : max,
                "personalAgency"   : personalAgency,

                "offset"           : offset,
                "sort"             : sort,
                "order"            : order,
                "localities"       : localities
        ]

        return filterParams

    }

}

