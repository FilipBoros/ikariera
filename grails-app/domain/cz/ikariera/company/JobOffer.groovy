package cz.ikariera.company
import cz.ikariera.admin.Country
import cz.ikariera.student.LanguageType
import grails.orm.PagedResultList
import org.grails.list.ListDistinct
import org.hibernate.criterion.CriteriaSpecification

import static org.grails.list.ListAssociationSort.associationOrder

class JobOffer {

    static belongsTo = [company: Company]

    static hasMany = [jobCategories: JobCategory, jobOfferType: JobOfferType, requieredLanguages: LanguageType, replies: JobOfferReply ]

    static mapping = {
        //requieredLanguages cascade: "none"
        jobOfferDescription type: 'text'
        jobApplicantRequire type: 'text'
        jobTypeDescription type: 'text'

    }


    RemoteCompany remoteCompany

    Date lastUpdated

    JobOfferType jobOfferType

    JobOfferPosition jobOfferPosition
    JobOfferPositionLevel jobOfferPositionLevel

    ContactDetails contactDetails

    Locality positionLocality

    Country positionCountry

    Integer counter = 0
    Integer contactCounter = 0




    String positionName
    String jobOfferDescription
    String jobApplicantRequire
    String jobTypeDescription




    Date datePublished
    Date willExpire
    Date dateCreated = new Date() //only for admin use
    Date jobStartDate

    Boolean graduatePosition = false   //graduate position

    Boolean topPos = false
    BigDecimal credits = 0

    static constraints = {
        positionName(blank: false, nullable: false, maxSize: 300)

        //jobCategories(nullable: true)
        requieredLanguages(nullable: true)

        positionLocality(nullable: true, blank: false)
        positionCountry (nullable: true, blank: false)

        datePublished(blank: false, nullable: true)
        willExpire(blank: false, nullable: true)
        jobStartDate(blank: true, nullable: true)

        jobOfferDescription(blank: true, nullable: false, maxSize: 4000)
        jobApplicantRequire(blank: true, nullable: false, maxSize: 4000)
        jobTypeDescription(blank: true, nullable: false, maxSize: 4000)


        contactDetails(blank: true, nullable: true)

        graduatePosition(blank: false, nullable: true)

        remoteCompany(blank: true, nullable: true)
        jobOfferPosition(blank: true, nullable: true)
        jobOfferPositionLevel(blank: true, nullable: true)
    }

    /*
    def static countJobCategory(String name) {
        def category = JobCategory.findByName(name)
        return JobOffer.countByJobCategories(category)
    }*/

    public static PagedResultList jobOfferAdminFilter(def attrs = [:]) {
        return ListDistinct.list(JobOffer, attrs) {

            createAlias('company', '_company', CriteriaSpecification.LEFT_JOIN)

            // fulltext search in filters
            if (attrs.fulltextSearch) {
                or {
                    ilike('positionName', '%' + attrs.fulltextSearch + '%')
                    ilike('jobOfferDescription', '%' + attrs.fulltextSearch + '%')
                    ilike('jobApplicantRequire', '%' + attrs.fulltextSearch + '%')
                    ilike('jobTypeDescription', '%' + attrs.fulltextSearch + '%')
                    ilike('_company.companyName', '%' + attrs.fulltextSearch + '%')
                }
            }

            // order
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)

            // fulltext search in filters
            if (attrs.companyID) {
                ilike('_company.companyID', '%' + attrs.companyID + '%')
            }
            if (attrs.companyName) {
                ilike('_company.companyName', '%' + attrs.companyName + '%')

            }



            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('dateCreated', 'desc')

            }
        }
    }

    /**
     * Filters JobOffers based on given attributes.
     * @param attrs LinkedHashMap with given attributes
     * 	<ul>
     * 		<li>jobCategories</li>
     * 		<li>positionLocalities</li>
     * 		<li>company</li>
     * 		<li>jobOfferType</li>
     * 		<li>graduatePosition</li>
     * 		<li>fulltextSearch</li>
     * 		<li>max: Maximum entries on one page.</li>
     * 		<li>offset: Offset gives the position in the whole org.grails.list and
     * 				together with "max" determine the current page.</li>
     * 		<li>sort: Attribute of the domain class JobOffer for sorting.</li>
     * 		<li>order: ascendent/descendent.</li>
     * 	</ul>
     *
     * @return List of filtered JobOffers
     */
    public static PagedResultList jobOfferFilter(def attrs = [:]) {
        return ListDistinct.list(JobOffer, attrs) {

            // order
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)

            // aliases
            createAlias('jobCategories', '_jobCategories', CriteriaSpecification.LEFT_JOIN)
            createAlias('positionLocality', '_positionLocality', CriteriaSpecification.LEFT_JOIN)
            createAlias('company', '_company', CriteriaSpecification.LEFT_JOIN)
            createAlias('jobOfferType', '_jobOfferType', CriteriaSpecification.LEFT_JOIN)

            // / filter all chosen jobCategories
            or {
                attrs.jobCategories?.each {
                    like('_jobCategories.id', it.id)
                }
            }

            // filter all chosen positionLocalities
            or {
                attrs.positionLocalities?.each {
                    eq('_positionLocality.id', it.id)
                }
            }

            or {
                attrs.jobOfferType?.each {
                    eq('_jobOfferType.id', it.id)
                }
            }



            if (attrs.company) {
                like('company', attrs.company)
            }


            if (attrs.topPos) {
                eq('topPos', true)
            }



            eq('_company.active', true)

            eq('_company.banned', false)




            // if false, both gaduate and non-graduate are listed
            if (attrs.graduatePosition)
                eq('graduatePosition', true)

            // fulltext search in filters
            if (attrs.fulltextSearch) {
                or {
                    ilike('positionName', '%' + attrs.fulltextSearch + '%')
                    ilike('jobOfferDescription', '%' + attrs.fulltextSearch + '%')
                    ilike('jobApplicantRequire', '%' + attrs.fulltextSearch + '%')
                    ilike('jobTypeDescription', '%' + attrs.fulltextSearch + '%')
                    ilike('_company.companyName', '%' + attrs.fulltextSearch + '%')
                }
            }

            isNotNull('datePublished')




            gt('willExpire', new Date())
            eq('_company.active', true)

            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('topPos', 'desc')
                order('datePublished', 'desc')
            }
        }
    }

    /**
     * Fulltext search on JobOffers.
     * @param attrs LinkedHashMap with given attributes:
     * 		<ul>
     * 			<li>fulltextSearch: Search query, case insensitive.</li>
     * 			<li>max: Maximum entries on one page.</li>
     * 			<li>offset: Offset gives the position in the whole org.grails.list and
     * 					together with "max" determine the current page.</li>
     * 			<li>sort: Attribute of the domain class JobOffer for sorting.</li>
     * 			<li>order: ascendent/descendent.</li>
     * 		</ul>
     * @return List of filtered JobOffers
     */
    public static PagedResultList favoriteFilter(def attrs) {
        return ListDistinct.list(JobOffer, attrs) {
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)

            // filter all chosen jobCategories

            createAlias('company', '_company', CriteriaSpecification.LEFT_JOIN)

            or {

                eq('id', Long.parseLong("-1"))        // hack to hide all

                attrs.idList?.each {
                    eq('id', it)
                }

            }

            eq('_company.banned', false)




            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('topPos', 'desc')
                order('datePublished', 'desc')
            }
        }
    }


}


