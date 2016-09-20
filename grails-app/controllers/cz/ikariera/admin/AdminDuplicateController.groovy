package cz.ikariera.admin

import cz.ikariera.company.Company

class AdminDuplicateController {

    def springSecurityService
    def mailService


    def index() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        redirect(action: "list", params: params)
    }

    /**
     * Filer method returns a list of filtered JobOffers
     */

    def list(CompanyAdminFilterCommand filterCommand) {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        filterCommand.max = params.max
        def list = Company.companiesAdminFilter(filterCommand.filterParams)


        render view: '/admin/listDupliciteCompanies',
                model: [
                        companyInstanceList : list,
                        companyInstanceTotal: list.totalCount,
                        filterParams        : filterCommand.filterParams,
                        displayedResults    : list.totalCount < params.max + params.offset ? list.totalCount - params.offset : params.max
                ],
                params: params
    }


}
