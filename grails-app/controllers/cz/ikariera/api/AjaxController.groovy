package cz.ikariera.api

import cz.ikariera.admin.MaintenanceMessage
import cz.ikariera.company.*
import org.hibernate.criterion.CriteriaSpecification

class AjaxController {

    def index() {}

    /*   def actualPosition() {
           def listActualPosition = JobOffer.findAll(
                   """from JobOffer  as j where datePublished!=null and company.active=true
                   and company.banned=false  and willExpire>:date order by datePublished desc """, [max: 6, date: new Date()])

           render(view: "/ajax/actualPositionColumn", model: [listActualPosition: listActualPosition])
       }

       def hotPosition() {

           def listHotPosition = JobOffer.findAll(
                   "from JobOffer  as j where datePublished!=null  and company.banned=false and company.active=true and willExpire>:date and topPos=true order by datePublished desc ", [max: 2, date: new Date()])

           render(view: "/ajax/hotPositionColumn", model: [listHotPosition: listHotPosition])
       }
      */

    def list() {

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        List list = Articles.createCriteria().list(params) {
            gt('willExpire', new Date())
            isNotNull('datePublished')

            order('datePublished', 'desc')

        }
        def listSize = list.totalCount


        render(view: "/articles/list", model:
                [
                        articleInstanceList : list,
                        displayedResults    : listSize < params.max + params.offset ? listSize - params.offset : params.max,
                        articleInstanceTotal: listSize])

    }
    //

    def actualPosition() {
        def listActualPosition = cz.ikariera.company.JobOffer.findAll(
                "from JobOffer  as j where datePublished!=null and company.active=true and company.banned=false  and willExpire>:date order by datePublished desc ", [max: 6, date: new Date()])

        render(view: '/ajax/actualPositionColumn', model: [listActualPosition: listActualPosition])
    }

    def hotPosition() {
        def result = cz.ikariera.company.JobOffer.findAll(
                "from JobOffer  as j where datePublished!=null and company.banned=false and company.active=true and willExpire>:date and topPos=true order by datePublished desc ", [max: 3, date: new Date()])
        //def result = ikariera.company.JobOffer.findAll()
        if (!result.isEmpty()) {
            render(view: '/ajax/hotPositionColumn', model: [listHotPosition: result])
        } else {
            def listArticles = Articles.list(max: 3, sort: "id", order: "desc")
            /*def listbodyTexts
            listArticles.each () {
                if(it.bodyText.size()>200){
                    listbodyTexts.add(it.bodyText.substring(0, 200))
                }
                listbodyTexts.add(it.bodyText)
                print " ${it.bodyText}"
            }*/
            render(view: '/ajax/articlesAndTips', model: [listArticles: listArticles]) //, listbodyTexts: listbodyTexts
        }
    }


    def articlesAndTips() {
        def listArticles = Articles.findAll("from Articles where datePublished!=null order by datePublished", [max: 2])
        //def listArticles = Articles.findAll("from Articles as p where p.datePublished!=:null order by p.datePublished",[lang:session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'].toString()], [max: 4])

        render(view: "/ajax/__articlesAndTips", model: [listArticles: listArticles])
    }

    def getMaintanceMessage() {
        def now = new Date()

        def msg = MaintenanceMessage.find("FROM MaintenanceMessage where dateBegin <= :now  and dateEnd > :now  order by dateBegin desc ", [now: now])


        render(view: "/ajax/maintanceMessage", model: [msg: msg])

    }
/*
* Return map
* the categories
* and number of jobOffers in each category
*
*/

    def getCategoriesWithCount() {

        def jobCategoriesList = JobCategory.list(sort: "posOrder", order: "desc")
        def jobOffersCategoriesCountList = []
        jobCategoriesList.each { category ->
            def c = JobOffer.withCriteria() {
                createAlias("jobCategories", "_jobCategories", CriteriaSpecification.LEFT_JOIN)
                eq("_jobCategories.id", category.id)
                //between("l.accessDate", today, today + 1)

                gt('willExpire', new Date())
                isNotNull('datePublished')

                projections {
                    // countDistinct("id")
                    rowCount()
                }
            }
            def jobCountMap = [:]

            jobCountMap.put("i18NameFull", category.i18NameFull)
            jobCountMap.put("i18Name", category.i18Name)
            jobCountMap.put("id", category.id)
            jobCountMap.put("name", category.name)
            jobCountMap.put("count", c.first())

            jobOffersCategoriesCountList.push(jobCountMap)

        }
        render(view: '/ajax/categoriesWithCount', model: [jobOffersCategoriesCountList: jobOffersCategoriesCountList])
    }

    def getLocalitiesWithCount() {

        def localitiesList = Locality.list(sort: "posOrder", order: "desc")
        def localitiesCountList = []
        localitiesList.each { locality ->
            def c = JobOffer.withCriteria() {
                createAlias("positionLocality", "_positionLocality", CriteriaSpecification.LEFT_JOIN)
                eq("_positionLocality.id", locality.id)
                //between("l.accessDate", today, today + 1)

                gt('willExpire', new Date())
                isNotNull('datePublished')

                projections {
                    // countDistinct("id")
                    rowCount()
                }
            }
            def jobCountMap = [:]

            jobCountMap.put("i18NameFull", locality.i18NameFull)
            jobCountMap.put("i18Name", locality.i18Name)
            jobCountMap.put("id", locality.id)
            jobCountMap.put("name", locality.name)
            jobCountMap.put("count", c.first())

            localitiesCountList.push(jobCountMap)

        }
        render(view: '/ajax/localitiesWithCount', model: [localitiesCountList: localitiesCountList])
    }


    def getJobOfferTypesWithCount() {

        def jobCategoriesList = JobOfferType.list(sort: "specOrder", order: "desc")
        def jobOffersTypesCountList = []
        jobCategoriesList.each { jobOfferType ->
            def c = JobOffer.withCriteria() {
                createAlias("jobOfferType", "_jobOfferType", CriteriaSpecification.LEFT_JOIN)
                eq("_jobOfferType.id", jobOfferType.id)
                //between("l.accessDate", today, today + 1)

                gt('willExpire', new Date())
                isNotNull('datePublished')

                projections {
                    // countDistinct("id")
                    rowCount()
                }
            }
            def jobCountMap = [:]

            jobCountMap.put("i18NameFull", jobOfferType.i18NameFull)
            jobCountMap.put("i18Name", jobOfferType.i18Name)
            jobCountMap.put("id", jobOfferType.id)
            jobCountMap.put("name", jobOfferType.name)
            jobCountMap.put("count", c.first())

            jobOffersTypesCountList.push(jobCountMap)

        }
        render(view: '/ajax/jobOfferTypesWithCount', model: [jobOffersTypesCountList: jobOffersTypesCountList])
    }


    def getBanner() {

    }


}
