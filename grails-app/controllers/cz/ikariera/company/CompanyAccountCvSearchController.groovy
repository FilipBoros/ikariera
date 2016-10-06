package cz.ikariera.company

import cz.ikariera.company.JobCategory
import cz.ikariera.security.User
import cz.ikariera.service.CompanyServicesService
import cz.ikariera.student.LanguageType
import cz.ikariera.student.StudentAccount
import cz.ikariera.student.University

class CompanyAccountCvSearchController {
    def springSecurityService
    def mailService


    def index(CVSearchFilterCommand filterCommand) {

        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }


        def company = user.company
        String uniqueName = "cv-service"
        if (!CompanyServicesService.isActivated(uniqueName, company)) {

            redirect(controller: "companyAccountServices", action: "activation", id: uniqueName)
            return
        }

        filterCommand.jobCategories = params.list('jobCategories')
        filterCommand.educations = params.list('educations')
        filterCommand.languages = params.list('languages')

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        filterCommand.order = params.order ? params.order : 'desc'
        filterCommand.sort = params.sort ? params.sort : 'dateCreated'


        filterCommand.max = params.max
        def list = StudentAccount.studentCVFilter(filterCommand.filterParams)

        render view: 'index',
                model: [
                        studentInstanceList : list,
                        studentInstanceTotal: list.totalCount,
                        filterParams        : filterCommand.filterParams,
                        companyInstance     : user.company
                ],
                params: params

    }

    def detail(StudentAccount studentInstance) {

        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def company = user.company
        String uniqueName = "cv-service"
        if (!CompanyServicesService.isActivated(uniqueName, company)) {

            redirect(controller: "companyAccountServices", action: "activation", id: uniqueName)
            return
        }

        render(view: "detail",
                model: [studentInstance: studentInstance])

    }


    def getStudentCv(String id) {

        String uploadDirectory = grailsApplication.config.upload.directory.studentCv

        try {

            def picFile = new File(uploadDirectory + "/" + id)

            response.addHeader("Cache-Control", "no-store");

            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }

}

/**
 * Command class for binding filter data from controller
 */
class CVSearchFilterCommand {
    List<JobCategory> jobCategories = []
    List<University> educations = []
    List<LanguageType> languages = []

    String sort
    String order

    Integer allStudents

    Integer max
    Integer offset

    Boolean studentOrganisation

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

    public setEducations(List<String> educations) {
        List<University> newUniversities = []
        educations.each {
            def university = University.get(it)
            if (university)
                newUniversities.add(university)
        }
        this.educations = newUniversities
    }

    // setter for JobCategories (workaround for dynamicBinding)
    public setLanguages(List<String> languages) {
        List<LanguageType> newLanguages = []
        languages.each {
            def language = LanguageType.get(it)
            if (language)
                newLanguages.add(language)
        }
        this.languages = newLanguages
    }

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << [
                "jobCategories"      : jobCategories,
                "educations"         : educations,
                "languages"          : languages,
                "studentOrganisation": studentOrganisation,

                "allStudents"        : allStudents,

                "max"                : max,
                "offset"             : offset,
                "sort"               : sort,
                "order"              : order
        ]

        return filterParams
    }
}
