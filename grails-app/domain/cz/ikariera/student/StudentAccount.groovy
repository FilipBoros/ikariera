package cz.ikariera.student

import cz.ikariera.company.JobCategory
import cz.ikariera.security.User
import grails.gorm.PagedResultList
import org.grails.list.ListDistinct
import org.hibernate.criterion.CriteriaSpecification

import static org.grails.list.ListAssociationSort.associationOrder

class StudentAccount {


    static hasMany = [
            educations        : Education,

            jobCategories     : JobCategory,

            languages         : LanguageCombination,
            skills            : SkillCombination,
            experiences       : Experience,
            certificates      : Certificate
    ]


    Collection educations

    Collection experiences

    Collection certificates

    Collection languages

    Collection skills


    static mapping = {

        educations cascade: "all-delete-orphan"

        languages cascade: "all-delete-orphan"
        skills cascade: "all-delete-orphan"
        experiences cascade: "all-delete-orphan"
        certificates cascade: "all-delete-orphan"

        personalProfile type: 'text'

    }


    Photo photo


    Cv cv


    boolean infoEmails = true


    String addressStreet
    String addressZip
    String addressCountry
    String addressCity

    String personalCharacteristic

    String telephone


    String nationality
    String birthday
    String birthplace


    String personalProfile
    Date dateCreated = new Date()

    // static belongsTo = [user: User]
    User user




    static constraints = {


        cv(unique: true, nullable: true)
        photo(unique: true, nullable: true)



        addressStreet(blank: true, nullable: true)
        addressCountry(blank: true, nullable: true)
        addressCity(blank: true, nullable: true)
        addressZip(blank: true, nullable: true)

        personalCharacteristic(blank: true, nullable: true, maxSize: 255)

        telephone(blank: true, nullable: true)


        birthday(blank: true, nullable: true, maxSize: 255)
        birthplace(blank: true, nullable: true, maxSize: 255)

        nationality(blank: true, nullable: true)
        personalProfile(blank: true, nullable: true, maxSize: 2000)




        jobCategories(nullable: true)
        languages(nullable: true)
        skills(nullable: true)



    }

    /**
     * Filters Students based on given attributes.
     * @param attrs LinkedHashMap with given attributes
     * 	<ul>
     * 		<li>jobCategories</li>
     * 		<li>universities</li>
     * 		<li>languages</li>

     * 		<li>cvPath</li>
     * 		<li>max: Maximum entries on one page.</li>
     * 		<li>offset: Offset gives the position in the whole org.grails.list and
     * 				together with "max" determine the current page.</li>
     * 		<li>sort: Attribute of the domain class JobOffer for sorting.</li>
     * 		<li>order: ascendent/descendent.</li>
     * 	</ul>
     *
     * @return List of filtered JobOffers
     */
    public static PagedResultList studentFilter(def attrs = [:]) {
        return ListDistinct.list(StudentAccount, attrs) {

            // order
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)

            // aliases
            createAlias('jobCategories', '_jobCategories', CriteriaSpecification.LEFT_JOIN)
            createAlias('educations', '_educations', CriteriaSpecification.LEFT_JOIN)
            //  createAlias('studyCategories', '_studyCategories', CriteriaSpecification.LEFT_JOIN)
            createAlias('languages', '_languages', CriteriaSpecification.LEFT_JOIN)
            createAlias('skills', '_skills', CriteriaSpecification.LEFT_JOIN)
            createAlias('user', '_user', CriteriaSpecification.LEFT_JOIN)

            // filter all chosen jobCategories
            or {
                attrs.jobCategories?.each {
                    like('_jobCategories.id', it.id)
                }
            }

            // filter all chosen educations
            or {
                attrs.educations?.each {
                    like('_educations.university.id', it.id)
                }
            }

            // filter all chosen languages
            or {
                attrs.languages?.each {
                    like('_languages.languageType.id', it.id)
                }
            }

            // filter all chosen skills
            or {
                attrs.skills?.each {
                    like('_skills.skillType.id', it.id)
                }
            }



            eq('infoEmails', true)

            eq('_user.enabled', true)

            // eq('_user.isStudent', true)

            // eq('_user.accountExpired', false)

            //  eq('_user.accountLocked', false)


            if (attrs.allStudents == 1) {
                or {
                    gt('_educations.endingYear', new Date())
                }
            } else if (attrs.allStudents == 2) {
                or {
                    le('_educations.endingYear', new Date())
                }
            } else if (attrs.allStudents == 3) {
                and {
                    le('_educations.endingYear', new Date())
                }
            }

            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('dateCreated', 'desc')
            }

        }
    }

    /**
     * Filters Students based on given attributes.
     * @param attrs LinkedHashMap with given attributes
     * 	<ul>
     * 		<li>jobCategories</li>
     * 		<li>educations</li>
     * 		<li>languages</li>

     * 		<li>cvPath</li>
     * 		<li>max: Maximum entries on one page.</li>
     * 		<li>offset: Offset gives the position in the whole org.grails.list and
     * 				together with "max" determine the current page.</li>
     * 		<li>sort: Attribute of the domain class JobOffer for sorting.</li>
     * 		<li>order: ascendent/descendent.</li>
     * 	</ul>
     *
     * @return List of filtered JobOffers
     */
    public static PagedResultList studentCVFilter(def attrs = [:]) {
        return ListDistinct.list(StudentAccount, attrs) {

            // order
            //
            if (attrs.sort != "firstName" && attrs.sort != "lastName") {
                org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)
            }

            // aliases
            createAlias('jobCategories', '_jobCategories', CriteriaSpecification.LEFT_JOIN)
            createAlias('educations', '_educations', CriteriaSpecification.LEFT_JOIN)
            //  createAlias('studyCategories', '_studyCategories', CriteriaSpecification.LEFT_JOIN)
            createAlias('languages', '_languages', CriteriaSpecification.LEFT_JOIN)
            createAlias('skills', '_skills', CriteriaSpecification.LEFT_JOIN)

            createAlias('user', '_user', CriteriaSpecification.LEFT_JOIN)


            if (attrs.sort == "firstName") {
                order('_user.firstName', attrs.order)
            } else if (attrs.sort == "lastName") {
                order('_user.lastName', attrs.order)
            }

            // filter all chosen jobCategories
            or {
                attrs.jobCategories?.each {
                    like('_jobCategories.id', it.id)
                }
            }

            // filter all chosen educations
            or {
                attrs.educations?.each {
                    like('_educations.university.id', it.id)
                }
            }

            // filter all chosen languages
            or {
                attrs.languages?.each {
                    like('_languages.languageType.id', it.id)
                }
            }

            // filter all chosen skill
            or {
                attrs.skills?.each {
                    like('_skills.skillType.id', it.id)
                }
            }






            eq('_user.enabled', true)







            if (attrs.allStudents == 1) {
                or {
                    gt('_educations.endingYear', new Date())
                }
            } else if (attrs.allStudents == 2) {
                le('_educations.endingYear', new Date())

            } else if (attrs.allStudents == 3) {

                lt('_educations.endingYear', new Date())

            }

            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('cvUploaded', 'desc')
            }

        }
    }


}
