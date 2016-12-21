package cz.ikariera.company

import cz.ikariera.admin.PictureMedia
import cz.ikariera.security.User
import grails.gorm.PagedResultList
import org.grails.list.ListDistinct
import org.hibernate.criterion.CriteriaSpecification

import static org.grails.list.ListAssociationSort.associationOrder

class Company {

    String companyID
    String companyVatID

    String notes

    String companyName

    String seoName

    String companyStreet
    String companyCity
    String companyCountry
    String companyZip

    String publicEmail

    String companyWeb
    String companyFileSystemName

    String companyCharacteristic

    BigDecimal credits = 0

    BigDecimal addRemoveCreds

    Date dateCreated = new Date()
    Date startup = new Date()


    Date advancedProfileExpire       //deprecated



    Integer interestQoc = 0
    Date lastInterest = new Date()




    Boolean active = false

    ServicesExpire servicesExpire

    Locality locality

    Boolean personalAgency = false

    Boolean university = false

    Boolean banned = false

    String logo

    String mainLogo

    String APIKEY

    Date getTopProfile() { servicesExpire?.getTopProfile() }

    Integer getJobOfferCount() { jobOffer.size() }


    static transients = ['addRemoveCreds', 'jobOfferCount']

  //  int counterJobOffers

    static mapping = {
        companyCharacteristic type: 'text'

        contactDetails cascade: "all-delete-orphan"
        galleryPictures cascade: "all-delete-orphan"
        jobOffer cascade: "all-delete-orphan"
        companyEmails cascade: "all-delete-orphan"

        purchases cascade: "all-delete-orphan"
        users cascade: "all-delete-orphan"

    }


    static hasMany = [

            galleryPictures  : PictureMedia,
            jobOffer         : JobOffer,
            mainJobCategories: JobCategory,

            contactDetails   : ContactDetails,

            remoteCompanies  : RemoteCompany,

            users            : User,

            companyEmails    : CompanyEmails,
            //  services: PurchasedServices,
            //  serviceHistory: ServicesHistoryLog,



            purchases        : Purchase,


            remoteMessages   : RemoteMessage,

            heroImages       : HeroImage
    ]

    static constraints = {
        startup(blank: true, nullable: true)
        companyID(blank: false, nullable: false, maxSize: 100)
        companyName(blank: false, nullable: false, maxSize: 300)

        mainJobCategories(nullable: true)


        companyVatID(blank: true, nullable: true, maxSize: 100)

        notes(blank: true, nullable: true, maxSize: 1000)

        credits(min: 0.0, scale: 2, bindable: false)
        seoName(unique: true, nullable: true, maxSize: 200)
        companyStreet(blank: false, nullable: false, maxSize: 300)
        companyCity(blank: false, nullable: false, maxSize: 300)
        companyCountry(blank: false, nullable: false, maxSize: 300)
        companyZip(blank: false, nullable: false, maxSize: 50)
        companyFileSystemName(nullable: true, maxSize: 300)

        APIKEY(unique: true, nullable: true)

        servicesExpire(nullable: true)

        companyEmails(nullable: true)

        publicEmail(blank: true, nullable: true, unique: false, email: true)

        companyWeb(blank: false, nullable: true, maxSize: 300)

        companyCharacteristic(blank: true, nullable: true, maxSize: 2000)

        advancedProfileExpire(nullable: true)
        mainLogo(nullable: true)
        jobOffer(nullable: true)
        galleryPictures(nullable: true)
        logo(nullable: true)
        locality(nullable: true, blank: true)
        interestQoc(nullable: true, blank: true)
        lastInterest(nullable: true, blank: true)

    }







    /**
     * Filters JobOffers based on given attributes.
     * @param attrs LinkedHashMap with given attributes
     * 	<ul>
     * 		<li>jobCategories</li>
     * 		<li>positionLocalities</li>
     * 		<li>minSalary</li>
     * 		<li>maxSalary</li>
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
    public static PagedResultList companiesFilter(def attrs = [:]) {
         def list = ListDistinct.list(Company, attrs) {

            // order
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)



            //createAlias 'jobOffer', 'j'

           // createAlias('jobOffer', '_jobOffer', CriteriaSpecification.LEFT_JOIN)
/*
            projections {

                groupProperty 'j'
                count 'j.id'
            }
            order ('j', 'desc')
*/

           /* def c = JobOffer.createCriteria()
            def results = c {
                projections {
                    groupProperty("Company")
                    rowCount()
                }
                order("Company")
            }
*/

            createAlias('mainJobCategories', '_mainJobCategories', CriteriaSpecification.LEFT_JOIN)

             createAlias('locality', '_localities', CriteriaSpecification.LEFT_JOIN)

            if (attrs.abbr) {
                ilike('companyName', attrs.abbr + '%')

            }

            if (attrs.abbrNum) {
                or {
                    ilike('companyName', '0' + '%')
                    ilike('companyName', '1' + '%')
                    ilike('companyName', '2' + '%')
                    ilike('companyName', '3' + '%')
                    ilike('companyName', '4' + '%')
                    ilike('companyName', '5' + '%')
                    ilike('companyName', '6' + '%')
                    ilike('companyName', '7' + '%')
                    ilike('companyName', '8' + '%')
                    ilike('companyName', '9' + '%')

                }
            }

            // filter all chosen jobCategories
            or {
                attrs.mainJobCategories?.each {
                    like('_mainJobCategories.id', it.id)
                }
            }


            eq('active', true)

            // filter all chosen jobCategories
           /* or {
                attrs.jobCategories?.each {
                    like('_jobCategories.id', it.id)
                }
            }
*/

             or {
                 attrs.localities?.each {
                     like('_localities.id', it.id)
                 }
             }

            // fulltext search in filters
            if (attrs.companyID) {
                ilike('companyID', '%' + attrs.companyID + '%')
            }

            if (attrs.companyName) {
                ilike('companyName', '%' + attrs.companyName + '%')

            }

            // fulltext search in filters
            if (attrs.companyCity) {
                ilike('companyCity', '%' + attrs.companyCity + '%')
            }

            // fulltext search in filters
            if (attrs.personalAgency) {
                eq('personalAgency', true)
            }

            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('dateCreated', 'desc')

            }
             /*     Date getTopProfile() { servicesExpire?.getTopProfile() }

    Integer getJobOfferCount() { jobOffer.size() }
     */
        }
/*
        def prefilteredCompanyInstanceListDefault = org.grails.list

        def companyInstanceList1 = prefilteredCompanyInstanceListDefault.sort { it.getTopProfile() }
       // companyInstanceList1.removeAll {it.getTopProfile() < new Date()}
        //companyInstanceList1.removeAll {it.getTopProfile() == null}

        def prefilteredCompanyInstanceListDefault2 = org.grails.list
        def companyInstanceList2 = prefilteredCompanyInstanceListDefault2.sort { it.getJobOfferCount() }
        companyInstanceList2= companyInstanceList2.reverse(true)
        //companyInstanceList2.removeAll {it.getTopProfile() >= new Date()}

        def companyInstanceList = org.grails.list
        //companyInstanceList.clear()
        companyInstanceList = companyInstanceList1.plus(companyInstanceList2)
*/
        return list

    }

    /**
     * Filters JobOffers based on given attributes.
     * @param attrs LinkedHashMap with given attributes
     * 	<ul>
     * 		<li>jobCategories</li>
     * 		<li>positionLocalities</li>
     * 		<li>minSalary</li>
     * 		<li>maxSalary</li>
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
    public static PagedResultList companiesAdminFilter(def attrs = [:]) {
        return ListDistinct.list(Company, attrs) {

            // order
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)

            createAlias('mainJobCategories', '_mainJobCategories', CriteriaSpecification.LEFT_JOIN)

            // fulltext search in filters
            if (attrs.companyID) {
                ilike('companyID', '%' + attrs.companyID + '%')
            }
            if (attrs.companyName) {
                ilike('companyName', '%' + attrs.companyName + '%')

            }

            if (attrs.abbr) {
                ilike('companyName', attrs.abbr + '%')

            }

            if (attrs.abbrNum) {
                or {
                    ilike('companyName', '0' + '%')
                    ilike('companyName', '1' + '%')
                    ilike('companyName', '2' + '%')
                    ilike('companyName', '3' + '%')
                    ilike('companyName', '4' + '%')
                    ilike('companyName', '5' + '%')
                    ilike('companyName', '6' + '%')
                    ilike('companyName', '7' + '%')
                    ilike('companyName', '8' + '%')
                    ilike('companyName', '9' + '%')

                }
            }

            // fulltext search in filters
            if (attrs.companyCity) {
                ilike('companyCity', '%' + attrs.companyCity + '%')
            }

            // fulltext search in filters
            if (attrs.personalAgency) {
                eq('personalAgency', true)
            }

            // filter all chosen jobCategories
            or {
                attrs.mainJobCategories?.each {
                    like('_mainJobCategories.id', it.id)
                }
            }

            // fulltext search in filters
            if (attrs.active) {
                eq('active', true)
            }

            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('dateCreated', 'desc')

            }
        }
    }


}
