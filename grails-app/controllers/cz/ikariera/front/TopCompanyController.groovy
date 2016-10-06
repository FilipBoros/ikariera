package cz.ikariera.front

import cz.ikariera.company.Company
import cz.ikariera.company.ServicesExpire

class TopCompanyController {

    def static getListOfTopCompanies(String sort, int offset) {



        offset = offset ? offset : 0
        sort = sort ? sort : ""
        def listServices = ServicesExpire.createCriteria().list(sort: sort, offset: offset) {
            //order('datePurchased', 'desc')
            isNotNull("topProfile")
        }
        def listServicesSize = listServices?.totalCount

        def listTopCompanies= Company.findAllById(1)

        listTopCompanies.clear()

        listServices.each() {
            listTopCompanies.add(it.company)
            //print " ${it}"
        }

        return listServices

    }
}