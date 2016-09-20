package ikariera.student

import cz.ikariera.company.*
import cz.ikariera.security.User

class StudentAccountRecommendedController {


    def springSecurityService

    def index(FilterCommand filterCommand) {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }


        def studentsCategories = user.studentAccount.jobCategories.toList()
        //filterCommand.jobCategories = params.list('jobCategories')
        studentsCategories.each {
            filterCommand.jobCategories.add(it)
        }
        filterCommand.positionLocalities = params.list('positionLocalities')
        filterCommand.jobOfferType = params.list('jobOfferType')

        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 20, 100)
        filterCommand.max = params.max

        filterCommand.sort = null

        def list = JobOffer.jobOfferFilter(filterCommand.filterParams)

        def searchResultType = filterCommand.jobOfferType ?: "misc"

        Date date = new Date()



        render view: 'index',
                model: [
                        jobOfferInstanceList : list,

                        jobOfferInstanceTotal: list.totalCount,
                        filterParams         : filterCommand.filterParams,
                        searchResultType     : searchResultType,
                        displayedResults     : list.totalCount < params.max + params.offset ? list.totalCount - params.offset : params.max
                ],
                params: params
    }


}

/**
 * Command class for binding filter data from controller
 */
class FilterCommand {
    List<JobCategory> jobCategories = []
    List<Locality> positionLocalities = []
    List<JobOfferType> jobOfferType = []

    Boolean topPos

    String sort
    String order

    Integer max
    Integer offset

    Company company
    // JobOfferType jobOfferType
    Boolean graduatePosition

    String fulltextSearch

    // setter for JobCategories (workaround for dynamicBinding)
    public setJobCategories(List<String> jobCategories) {
        List<JobCategory> newJobCategories = []
        jobCategories.each {
            def jobCategory = JobCategory.get(it)
            if (jobCategory)
                newJobCategories.add(jobCategory)
        }
        this.jobCategories = newJobCategories
    }

    // setter for PositionLocalities (workaround for dynamicBinding)
    public setPositionLocalities(List<String> positionLocalities) {
        List<Locality> newPositionLocalities = []
        positionLocalities.each {
            def positionLocality = Locality.get(it)
            if (positionLocality)
                newPositionLocalities.add(positionLocality)
        }
        this.positionLocalities = newPositionLocalities
    }

    // setter for PositionLocalities (workaround for dynamicBinding)
    public setJobOfferType(List<String> jobOfferType) {
        List<JobOfferType> newJobOfferType = []
        jobOfferType.each {
            def ajobOfferType = JobOfferType.get(it)
            if (ajobOfferType)
                newJobOfferType.add(ajobOfferType)
        }
        this.jobOfferType = newJobOfferType
    }

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << ["jobCategories"     : jobCategories,
                         "positionLocalities": positionLocalities,
                         "company"           : company,
                         "jobOfferType"      : jobOfferType,
                         "graduatePosition"  : graduatePosition,
                         "fulltextSearch"    : fulltextSearch,
                         "max"               : max,
                         "topPos"            : topPos,
                         "offset"            : offset,
                         "sort"              : sort,
                         "order"             : order
        ]

        return filterParams
    }
}

