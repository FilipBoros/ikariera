package cz.ikariera.front

import cz.ikariera.admin.Banner
import cz.ikariera.admin.Contact
import cz.ikariera.admin.Country
import cz.ikariera.company.*
import cz.ikariera.security.User
import grails.converters.JSON
import grails.converters.XML

class JobOfferController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def botJobs() {
        if (!request.getHeader("User-Agent")?.equals("JoobleBot")) {
            render(view: "/notFound", model: [:])
            return
        }

        if (request.getHeader("If-Modified-Since") != null) {

            def longval = request.getDateHeader("If-Modified-Since")
            Date lastVisit = new Date(longval)

            def count = JobOffer.countByLastUpdatedGreaterThan(lastVisit)

            if (count == 0) {
                response.status = 304
                render text: "Not modified", status: 304
                return
            }
        }

        Date now = new Date()
        def jOInstance = JobOffer.findAllByWillExpireGreaterThan(now)
        render jOInstance as XML
    }

    def getAllJobs() {
        if (!request.getHeader("User-Agent")?.equals("JoobleBot")) {
            render(view: "/notFound", model: [:])
            return
        }

        Date now = new Date()
        def jOInstance = JobOffer.findAllByWillExpireGreaterThan(now)
        render jOInstance as XML
    }


    def detailNotPublished(JobOffer jobOfferInstance) {

        if (!jobOfferInstance) {
            flash.error = message(code: "default.not.found.message", args: ['Job offer', params.id])
            render(view: "/notFound", model: [:])
            return
        }

        render(view: "detail", model: [jobOfferInstance: jobOfferInstance])
    }


    def detail() {

        def jobOffer = null

        def id = params.id
        if (!id) {
            flash.error = message(code: "default.not.found.message", args: ['Job offer', params.id])
            render(view: "/notFound", model: [:])
        } else if (id.isLong()) {
            jobOffer = JobOffer.findWhere(id: Long.parseLong(id))
        }
        /* background image */
        //to delete
        /*int counter = 0
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
*/

        /* final check*/
        if (!jobOffer || jobOffer.datePublished == null) {
            flash.error = message(code: "default.not.found.message", args: ['Job offer', params.id])
            render(view: "/notFound", model: [:])


        } else {

            def contactInstance = Contact.findByContactIdentify("IAESTE")
            def heroImage = getHeroImage(jobOffer)

            jobOffer.counter += 1
/* TODO
            def lastInterest = jobOffer?.company?.lastInterest
            def durationDays = 0
            use(groovy.time.TimeCategory) {
                def duration = new Date()  - lastInterest
                durationDays =  duration.days
            }



            jobOffer.company.interestQoc = jobOffer.company.interestQoc / durationDays==0?1
            jobOffer?.company?.lastInterest = new Date()
*/


            jobOffer.save(flush: true)

            render(view: "/jobOffer/detail", model: [jobOfferInstance: jobOffer, heroImage: heroImage, contactInstance: contactInstance])
        }
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
    /**
     * Filer method returns a list of filtered company users
     */
    def getTelephoneNumber(JobOffer jobOfferInstance) {

        //def userId = params.userId


        if (jobOfferInstance) {
            //def user = User.get(userId)


            def contact = jobOfferInstance?.contactDetails

            if (contact) {


                try {
                    jobOfferInstance?.contactCounter += 1
                    jobOfferInstance.save(flush: true)
                } catch (org.springframework.dao.OptimisticLockingFailureException e) {
                    // deal with exception


                }
                def result = [data: contact?.telephone]
                def converter = result as JSON;
                render converter.toString();
            }
        }
    }

    def jobOfferApi(JobOffer job) {
        def api = params.apikey
        def company = Company.find { APIKEY == api }

        if (company == null) {
            render status: 401, text: ['error_message': "Please provide APIKEY", 'status': 401], contentType: "application/json"
            return
        }
        if (job.id != null) {
            render status: 400, text: ['error_message': "ID was not expected here. probable job already exists in DB, updates are not allowed. yet.", 'status': 400], contentType: "application/json"
            return
        }
        if (params.hasContacts) {
            def kontakt = new ContactDetails(name: params.contact_name,
                    telephone: params.contact_telephone,
                    company: company,
                    email: params.contact_email,
                    detailText: params.contact_detailText,
                    positionInCompany: params.contact_telephone)


            if (!kontakt.save(flush: true)) {
                render status: 400, text: ['error_message': "Bad contacts data", 'status': 400], contentType: "application/json"
                return
            }
            job.contactDetails = kontakt
        }
        if (job.positionLocality != null) {
            job.positionCountry = Country.findByName("Česká republika")
        } else
            job.positionLocality = null

        job.company = company

        job.validate()

        if (job.hasErrors()) {
            def errorMsg = ""
            job.errors.allErrors.each {
                errorMsg += it
                errorMsg += '\n'
            }
            render status: 400, text: ['error_message': 'errors in job offer: ' + errorMsg, 'status': 400], contentType: "application/json"
            return
        }

        if (!job.save(flush: true, failOnError: true)) {
            def errorMsg = ""
            job.errors.allErrors.each {
                errorMsg += it
                errorMsg += '\n'
            }
            render status: 400, text: ['error_message': 'errors in job offer: ' + errorMsg, 'status': 400], contentType: "application/json"
            return
        }

        def respobj = ['id': job.id]

        render text: respobj as JSON, status: 201, contentType: "application/json"
    }

    def publishOfferAPi(JobOffer job) {
        def api = params?.apikey
        if (api == null){
            render(status: 401)
            return
        }
        def company = Company.find { APIKEY == api }

        if (company == null) {
            render(status: 401)
            return
        }

        if (job.id == null) {
            render status: 400, text: ['error_message': 'job offer was not found: ', 'status': 400], contentType: "application/json"
            return
        }

        if (job.datePublished != null) {
            render status: 400, text: ['error_message': 'job offer is already published, see ' + "ikariera.cz/job-offer/detail/" + job.id, 'status': 400], contentType: "application/json"
            return
        }

        job.datePublished = new Date()
        job.willExpire = new Date() + 30


        if (!job.save(flush: true, failOnError: true)) {
            render status: 400, text: ['error_message': 'error saving job offer', 'status': 400], contentType: "application/json"
            return
        }

        render(status: 200, text: ['link': "http://ikariera.cz/job-offer/detail/" + job.id, 'msg': 'job was published! see link'], contentType: "application/json")
    }
    /**
     * Filer method returns a list of filtered company users
     */
    def getEmailAddress(JobOffer jobOfferInstance) {

        // def userId = params.userId
        // def jobOfferId = params.id

        if (jobOfferInstance) {
            //  def user = User.get(userId)
            //   def jobOfferInstance = JobOffer.get(jobOfferId)


            def contact = jobOfferInstance.contactDetails

            if (contact) {

                try {
                    jobOfferInstance?.contactCounter += 1
                    jobOfferInstance.save(flush: true)
                } catch (org.springframework.dao.OptimisticLockingFailureException e) {
                    // deal with exception


                }
                def result = [data: contact?.email]
                //println(contact?.email);
                def converter = result as JSON;
                render converter.toString();
            }
        }

        //new JSON for email.. just return email
        //  println(params.email)
        /*   if (params.email) {

               def result = [data: params?.email]
               def converter = result as JSON;
               render converter.toString();
           }*/
    }


    def topPosList(FilterCommand filterCommand) {
        //  FilterCommand filterCommand = new FilterCommand()
        params.max = Math.min(params.int('max') ? params.int('max') : 10, 100)
        params.offset = params.int('offset') ? params.int('offset') : 0
        filterCommand.max = params.max
        //filterCommand.advertisementType= "D"
        filterCommand.topPos = true
        def list = JobOffer.jobOfferFilter(filterCommand.filterParams)

        render view: 'index',
                model: [
                        jobOfferInstanceList : list,
                        jobOfferInstanceTotal: list.totalCount,
                        filterParams         : filterCommand.filterParams,
                        searchResultType     : "topPos",
                        displayedResults     : list.totalCount < params.max + params.offset ? list.totalCount - params.offset : params.max
                ],
                params: params
    }


    def index(FilterCommand filterCommand) {
        filterCommand.jobCategories = params.list('jobCategories')
        filterCommand.positionLocalities = params.list('positionLocalities')
        filterCommand.jobOfferType = params.list('jobOfferType')

        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 20, 100)
        filterCommand.max = params.max

        filterCommand.sort = null
      //  filterCommand.order = params.order ? params.order : "asc"
      //  filterCommand.sort = params.sort ? params.sort : "positionName"



        //def defaultBool=true
        //def array = ['positionName', 'jobOfferDescription', 'jobApplicantRequire' , 'jobTypeDescription']
        //array.each{
        //    if(it==params.sort){
        //        defaultBool=false
        //    }
        //}

        //if(defaultBool==true){
        //    filterCommand.sort="positionName"
        //}


        //SHIT Yku*venej shit hovno kod
        //if(params.order!="desc" || params.order!="asc"){
        //    filterCommand.order="desc"
        //}




        def list = JobOffer.jobOfferFilter(filterCommand.filterParams)

        def searchResultType = filterCommand.jobOfferType ?: "misc"


        Date date = new Date()

        def contactInstance = Contact.findByContactIdentify("IAESTE")
        def banner = ""
        if (list.size() > 1)
            banner = Banner?.find("from cz.ikariera.admin.Banner as b where b.expirationDate !=null and b.expirationDate>:date order by priority desc, rotation asc", [date: date])

        render view: 'index',
                model: [
                        jobOfferInstanceList : list,
                        banner               : banner,
                        jobOfferInstanceTotal: list.totalCount,
                        filterParams         : filterCommand.filterParams,
                        searchResultType     : searchResultType,
                        displayedResults     : list.totalCount < params.max + params.offset ? list.totalCount - params.offset : params.max,
                        contactInstance     : contactInstance
                ],
                params: params
    }

    def fulltextService

    def fullTextFilter(){
        log.error("Text" + fulltextService.getJobOfferByFullText())
        render view: 'index'
    }

    def cookieService
    /**
     * Favorite filter
     */
    def favoriteFilter(FavoriteFilterCommand filterCommand) {


        User user = springSecurityService.getCurrentUser()



        String watchDogString = "ikarieraWatchDog_" + user?.id;


        String myCookie = cookieService.get(watchDogString).toString()

        myCookie.tokenize("_").each {
            if (it != "null") {
                Long id = Long.parseLong(it)
                filterCommand.idList.add(id)
            }
        }



        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        filterCommand.max = params.max

        def list = JobOffer.favoriteFilter(filterCommand.filterParams)

        render view: 'list',
                model: [
                        jobOfferInstanceList : list,
                        jobOfferInstanceTotal: list.totalCount,
                        filterParams         : filterCommand.filterParams,
                        searchResultType     : "favorite",
                        displayedResults     : list.totalCount < params.max + params.offset ? list.totalCount - params.offset : params.max
                ],
                params: params
    }

    /**
     * Fultext search method returns a list of filtered JobOffers based
     */
    /*def fulltextSearch(SearchCommand searchCommand) {
        def list = JobOffer.cz.ikariera.fulltext(searchCommand.searchParams)

        render view:'list',
            model:[
                jobOfferInstanceList: list,
                jobOfferInstanceTotal: list.totalCount],
            params:params
    }         */
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

/**
 * Command class for binding search data from controller
 */
class FavoriteFilterCommand {
    String sort
    String order

    Integer max
    Integer offset

    List<Long> idList = []

    /**
     * Returns a LinkedHashMap with sorting attributes
     **/
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << [
                "idList": idList,
                "max"   : max,
                "offset": offset,
                "sort"  : sort,
                "order" : order
        ]
        return filterParams
    }
}

