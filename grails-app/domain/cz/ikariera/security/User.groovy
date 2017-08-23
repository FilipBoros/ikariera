package cz.ikariera.security

import cz.ikariera.company.Company
import cz.ikariera.company.CompanyAccount
import cz.ikariera.company.CompanyEmails
import cz.ikariera.company.JobOffer
import cz.ikariera.student.StudentAccount
import grails.gorm.PagedResultList
import grails.plugin.springsecurity.userdetails.GrailsUser
import org.grails.list.ListDistinct
import org.springframework.security.core.GrantedAuthority

import static org.grails.list.ListAssociationSort.associationOrder

class User {

    transient springSecurityService

    String firstName
    String lastName
    String username
    String password
    String passwordConfirm
    String passwordOld

    String usertoken // for emailing
    String token
    Date tokenExpiration

    Date accountCreated = new Date()
    Date lastLoginDate


    Boolean termOfUse = false

    Boolean enabled = false
    Boolean accountExpired = false
    Boolean accountLocked = false
    Boolean passwordExpired = false


    String note


    static hasOne = [studentAccount: StudentAccount, companyAccount: CompanyAccount]

    Boolean remote = false

    String reCaptcha

    Boolean adminAccount = false

    //Company company
    static belongsTo = [company: Company]


    static mapping = {
        password column: '`password`'
    }


    static transients = ['springSecurityService', 'fullName', 'reCaptcha', 'passwordConfirm', 'passwordOld']

    static constraints = {


        accountCreated(bindable: false)

        termOfUse(bindable: false)

        enabled(bindable: true) //bindable: false
        accountExpired(bindable: false)
        accountLocked(bindable: false)
        passwordExpired(bindable: false)
        remote(bindable: false)

        adminAccount(bindable: false)


        firstName(blank: false, nullable: false, maxSize: 255)
        lastName(blank: false, nullable: false, maxSize: 255)
        username(blank: false, nullable: false, unique: true, email: true)//, bindable: false

        password(blank: false, nullable: false, minSize: 6, maxSize: 500) //, bindable: false

        passwordConfirm(blank: false, minSize: 6, bindable: false)
        passwordOld(blank: false, bindable: false)

        reCaptcha(blank: false, bindable: false)

        usertoken(unique: true, nullable: true, bindable: false)

        token(nullable: true, bindable: false)
        tokenExpiration(nullable: true, bindable: false)
        lastLoginDate(nullable: true, bindable: false)


        studentAccount(nullable: true, bindable: false)
        companyAccount(nullable: true, bindable: false)

        company(nullable: true, bindable: false)

        note(nullable: true)
    }

    /**
     *  Will get FullName of user
     * @return returns first name lastname of current user
     */
    //
    public String getFullName() {

        this.firstName + " " + this.lastName
    }


    public List<CompanyAccount> companyAccountUserList() {
        CompanyAccount.findAll("from CompanyAccount as c where c.user!=null and c.user.adminAccount=false") as List
    }

    /**
     * returns all others users which belongs to user company
     * @return List of users which belongs to user company
     */
    public List<User> companyUserList() {
        User.findAll("from User as u where u.company.id =:company and  u.adminAccount=false", [company: this.company.id]) as List
    }

    /**
     * List of all job advertisements which belongs to current user and company and aren't deleted
     * @return returns all jog advertisements which belongs to current user and company and aren't deleted
     */
    public List<JobOffer> companyJobOfferList() {
        JobOffer.findAll("from JobOffer as j where j.company.id =:company", [company: this.company.id]) as List
    }

    /**
     *  List of  all emails which returns to current user company
     * @return all emails which belongs to current user and company
     */
    public List<CompanyEmails> companyEmailsList() {
        CompanyEmails.findAll("from CompanyEmails as c where c.company.id =:company", [company: this.company.id]) as List
    }

    /**
     *  List of  all implemented Roles
     * @return all roles
     */
    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }
/*
    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }*/

    protected void encodePassword() {

        password = springSecurityService.encodePassword(password, username)
        //   password = springSecurityService.encodePassword(password)

    }


    public static PagedResultList userAdminFilter(def attrs = [:]) {
        return ListDistinct.list(User, attrs) {

            // order
            org.grails.list.ListAssociationSort.associationOrder(attrs.sort, attrs.order, delegate)

            // cz.ikariera.fulltext search in filters
            if (attrs.firstName) {
                ilike('firstName', '%' + attrs.firstName + '%')
            }
            if (attrs.lastName) {
                ilike('lastName', '%' + attrs.lastName + '%')

            }

            if (attrs.username) {
                ilike('username', '%' + attrs.username + '%')

            }

            if (attrs.isStudent && attrs.isStudent == true) {
                isNotNull("studentAccount")
            }


            if (attrs.adminAccount) {
                eq("adminAccount", attrs.adminAccount)


            }


            if (attrs.isCompany && attrs.isCompany == true) {
                isNotNull("companyAccount")
            }

            // default order
            if (!attrs.sort || attrs.sort?.isEmpty()) {
                order('accountCreated', 'desc')

            }
        }
    }

}


