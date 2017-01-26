package cz.ikariera.admin

import cz.ikariera.security.User

class AdminController {

    def springSecurityService
    def mailService


    def index() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        render(view: "/admin/index")
    }

    def listBySearch() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)

        //criteria
        List userInstanceList = null;
        def userInstanceTotal = null;
        if (!(params.firstName.equals("") && params.lastName.equals("") && params.username.equals(""))) {
            userInstanceList = User.createCriteria().list(max: max, offset: offset) {
                if (!(params.firstName.equals(""))) like('firstName', "%" + params?.firstName + "%")
                if (!(params.lastName.equals(""))) like('lastName', "%" + params?.lastName + "%")
                if (!(params.username.equals(""))) like('username', "%" + params?.username + "%")
            }
            userInstanceTotal = userInstanceList?.size()
        } else {
            userInstanceTotal = 0
        }

        def displayedResults = userInstanceTotal < max + offset ? userInstanceTotal - offset : max

        render view: '/admin/list',
                model: [
                        userInstanceList : userInstanceList,
                        userInstanceTotal: userInstanceTotal,
                        //  filterParams: filterCommand.filterParams,
                        displayedResults : displayedResults
                ],
                params: params
    }

}
