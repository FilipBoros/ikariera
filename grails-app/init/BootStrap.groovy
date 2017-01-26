import cz.ikariera.JobOfferListMarshaller
import cz.ikariera.JobOfferMarshaller
import cz.ikariera.security.Requestmap
import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import cz.ikariera.company.Company
import cz.ikariera.company.JobOfferType
import cz.ikariera.company.Locality
import cz.ikariera.company.CompanyAccount
import cz.ikariera.company.JobCategory
import cz.ikariera.student.Education
import cz.ikariera.student.StudentAccount
import cz.ikariera.student.LanguageType
import cz.ikariera.admin.Country
import cz.ikariera.student.StudyCategory
import cz.ikariera.student.University
import grails.converters.XML
import grails.util.Environment
import cz.ikariera.bootstraps.*

class BootStrap {



    def grailsApplication

    def springSecurityService

    def init = { servletContext ->

        XML.registerObjectMarshaller(new JobOfferMarshaller())
        XML.registerObjectMarshaller(new JobOfferListMarshaller())


        Requestmap.findByUrl('/admin-api-key/**') ?:   new Requestmap(url: '/admin-api-key/**', configAttribute: 'ROLE_ADMIN').save(flush: true, failOnError: true)

        Requestmap.findByUrl('/simple-registration/**') ?:   new Requestmap(url: '/simple-registration/**', configAttribute: 'permitAll').save(flush: true, failOnError: true)


        Requestmap.findByUrl('/api-remote-statistics/**') ?:   new Requestmap(url: '/api-remote-statistics/**', configAttribute: 'permitAll').save(flush: true, failOnError: true)

        Requestmap.findByUrl('/student-account-recommended/**') ?:   new Requestmap(url: '/student-account-recommended/**', configAttribute: 'ROLE_STUDENT').save(flush: true, failOnError: true)



        //  new Requestmap(url: '/admin-remote-api-key/**', configAttribute: 'ROLE_ADMIN').save()


/*
* jobOffer.put("id",)
        jobOffer.put("link",)
        jobOffer.put("name",)
        jobOffer.put("region",)
        jobOffer.put("salary",)
        jobOffer.put("company",)//name
        jobOffer.put("company_url",) //company description url
        jobOffer.put("description",)
        jobOffer.put("expire",)
        jobOffer.put("updated",)
* */


        /*if (Environment.current == Environment.DEVELOPMENT)*/ /*All data should be not loaded in production, but for now this is unnecessary.*/
            BootstrapRequestmap.init();

            println("Processing Bootstrap files ...")

            def companyRole = Role.findByAuthority('ROLE_COMPANY') ?: new Role(authority: 'ROLE_COMPANY').save(flush: true, failOnError: true)

            //company
            def company1 = Company.findByCompanyName('Seznam s.r.o.') ?: new Company(
                    companyID: '2030405060',
                    companyName: 'Seznam s.r.o.',
                    companyStreet: 'Konecna 6',
                    companyCity: 'Praha',
                    companyCountry: 'CR',
                    companyZip: '15600',
                    companyWeb: 'http:\\www.seznam.cz',
                    companyFileSystemName: 'SEZNAM',
                    companyCharacteristic: "vyhledavac",
                    credits: 500,
                    active: true
            ).save(failOnError: true)

            def companyUser = User.findByUsername('company@ikariera.eu')
            if(!companyUser) {
                companyUser = new User(
                        username: 'company@ikariera.eu',
                        firstName: 'Jon',
                        lastName: 'Connor',
                        /*company: company1,*/ //  toto nefuguje
                        password: springSecurityService.encodePassword('company', 'company@ikariera.eu'),
                        accountExpired: false,
                        accountLocked: false,
                        enabled: true,
                        passwordExpired: false
                )
                companyUser.company = company1
                companyUser.save(flush: true, failOnError: true)
            }

            if (!companyUser.authorities.contains(companyRole)) {
                UserRole.create(companyUser, companyRole, true)
            }

            def companyAccountUser = CompanyAccount.findByUser(companyUser) ?: new CompanyAccount(
                    titleBefore: "Ing.",
                    telephone: "+420 723 327 901",
                    publicEmail: "karl@seznam.cz",
                    company: company1,
                    user: companyUser,
                    positionInCompany: "ríďa"
            ).save(failOnError: true)

            def studentRole = Role.findByAuthority('ROLE_STUDENT') ?: new Role(authority: 'ROLE_STUDENT').save(flush: true, failOnError: true)

            def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

            def adminUser = User.findByUsername('admin@ikariera.eu') ?: new User(
                    username: 'admin@ikariera.eu',
                    firstName: 'Jon',
                    lastName: 'Connor',
                    accountExpired: false,
                    accountLocked: false,
                    enabled: true,
                    password: springSecurityService.encodePassword('admin', 'admin@ikariera.eu'),
                    passwordExpired: false).save(flush: true, failOnError: true)

            def studentUser = User.findByUsername('student@ikariera.eu') ?: new User(
                    username: 'student@ikariera.eu',
                    firstName: 'Jon',
                    lastName: 'Connor',
                    accountExpired: false,
                    accountLocked: false,
                    enabled: true,
                    password: springSecurityService.encodePassword('student', 'student@ikariera.eu'),
                    passwordExpired: false).save(failOnError: true)

            def student = StudentAccount.findByUser(studentUser) ?: new StudentAccount(
                    telephone: '111111',
                    user: studentUser,
                    nationality: 'CZ').save(failOnError: true)

            studentUser.studentAccount = student
            studentUser.save(failOnError: true)

            if (!adminUser.authorities.contains(adminRole)) {
                UserRole.create(adminUser, adminRole, true)

            }

            if (!studentUser.authorities.contains(studentRole)) {
                UserRole.create(studentUser, studentRole, true)
            }

            BootstrapConstant.init()

            /*TODO change BootsrapRequestMap to use ?: for each permission*/
            println('Bootstrap of request has started')
            BootstrapRequestmap.init();
            println('Bootstrap of request has finished')

            BootstrapServices.init();
            BootstrapLocalization.init();

            ArrayList<JobCategory> jobCategories = BootstrapJobCategories.init();
            ArrayList<Company> companies = BootstrapCompanies.init(jobCategories);

            BootstrapArticle.init(companies)
            BootstrapBanner.init(companies);
            BootstrapStudyCategories.init();
            BootstrapLanguageLevels.init();

            ArrayList<LanguageType> languages = BootstrapLanguages.init();

            BootstrapUniversities.init();

            ArrayList<Locality> localities = BootstrapPositionLocalities.init()
            ArrayList<JobOfferType> jobOfferTypes = BootstrapJobOfferType.init()
            ArrayList<Country> countries = BootstrapCountry.init();

            BootstrapJobOffers.init(companies, jobCategories, localities, countries, languages, jobOfferTypes);

//            String uploadDirectory = grailsApplication.config.upload.directory.mediaFile
//            BootstrapPartners.init(uploadDirectory)
//
//            String uploadHeroDirectory = grailsApplication.config.upload.directory.hero
//
//            BootstrapHeroImage2.init(uploadHeroDirectory);

            def michalUser = User.findByUsername('donkmichal@gmail.com')
            if(!michalUser) {
                michalUser = new User(
                        username: 'donkmichal@gmail.com',
                        firstName: 'Michal',
                        lastName: 'Dolnak',
                        /*company: company1,*/
                        password: springSecurityService.encodePassword('michal', 'donkmichal@gmail.com'),
                        accountExpired: false,
                        accountLocked: false,
                        enabled: true,
                        passwordExpired: false
                )
                michalUser.company = companies.get(2)
                michalUser.save(flush: true, failOnError: true)
            }

            if (!michalUser.authorities.contains(companyRole)) {
                UserRole.create(michalUser, companyRole, true)
            }


            println("... processing of Bootstrap files finished.")
    }

    def destroy = {
    }
}
