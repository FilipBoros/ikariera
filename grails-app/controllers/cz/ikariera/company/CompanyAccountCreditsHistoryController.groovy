package ikariera.company

import cz.ikariera.company.Purchase
import cz.ikariera.security.User

class CompanyAccountCreditsHistoryController {

    def springSecurityService

    def index() {
        def user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "login")
            return
        }

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 20, 100)

        def companyInstance = user.company
        List purchaseList = Purchase.createCriteria().list(params) {
            order('datePurchased', 'desc')
            eq('company', user.company)

        }
        def listSize = purchaseList.size()

        render(view: "index",
                model: [
                        companyInstance: companyInstance,
                        purchaseList     : purchaseList,
                        purchaseListCount: listSize,
                        displayedResults : listSize < params.max + params.offset ? listSize - params.offset : params.max
                ],
                params: params)
    }
}
