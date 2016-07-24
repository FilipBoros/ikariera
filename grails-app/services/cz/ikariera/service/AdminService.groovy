package cz.ikariera.service

import cz.ikariera.company.Company
import cz.ikariera.security.UserRole
import grails.transaction.Transactional

@Transactional
class AdminService {


    def removeAdmins(Company companyInstance) {
boolean bool = false
        //User.withSession { session->
            //def tx = session.beginTransaction()
            companyInstance.users.each {
                def userToRemove = UserRole.findByUser(it)
                if(userToRemove){
                    if(userToRemove.role.id == 3 || userToRemove.role.id == 4){
                        it.company = null
                        it.save(flush: true)
                        //companyInstance.removeFromUsers(it)
                        bool = true
                    }
                }
            }

            //Update status to processing
           // tx.commit()
        //}
        //companyInstance.save(flush: true)
        return bool
    }

}