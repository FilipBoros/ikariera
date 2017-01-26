package cz.ikariera.admin

class AdminStatisticsController {

    def springSecurityService


    def index() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        render(view: "/adminStatistics/index")

    }

}
