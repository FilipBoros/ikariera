package cz.ikariera.service

import cz.ikariera.company.Company
import grails.transaction.Transactional

@Transactional
class CompanyServicesService {


    public static boolean isActivated(String uniqueName, Company company) {
        switch (uniqueName) {
            case "cv-service":
                return company?.servicesExpire?.cv > new Date()

                break
            case "articles-service":
                return company?.servicesExpire?.article > new Date()

                break
            case "mail-service":
                return company?.servicesExpire?.mail > new Date()

                break
            case "top-service":
                return company?.servicesExpire?.topOffer > new Date()

                break

            case "message-service":
                return company?.servicesExpire?.messages > new Date()

                break
            case "advanced-profile-service":
                return company?.servicesExpire?.topProfile > new Date()

            case "remote-post":
                return company?.servicesExpire?.remote > new Date()

            case "jobofferapi":
                return company?.servicesExpire?.apiActiveTill > new Date()

            default:
                return false

        }


    }


}
