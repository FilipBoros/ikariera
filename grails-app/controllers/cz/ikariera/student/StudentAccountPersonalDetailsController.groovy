package cz.ikariera.student

import cz.ikariera.company.JobCategory
import cz.ikariera.security.User

class StudentAccountPersonalDetailsController {

    def springSecurityService




    def index() {


        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }



        render(view: "index", model: [userInstance: user])

    }


    def save() {
        User userInstance = springSecurityService.getCurrentUser()
        if (!userInstance) {
            redirect(controller: "login")
        }

        //studenAccount.jobCategories

        bindData(userInstance, params, [include: ['studentAccount']])
        //this method doesnt change values that are already saved

        userInstance?.firstName = params?.firstName
        userInstance?.lastName = params?.lastName
        userInstance?.username = params?.username

        userInstance?.studentAccount?.jobCategories?.clear()
        List jobCategories = params.list("jobCategories")
        //println(jobCategories)
        for(int i = 0;i<jobCategories.size();i++){
            JobCategory jc = JobCategory.findById(jobCategories[i])
            userInstance.studentAccount.jobCategories.add(jc)
        }

       /* if(params?.infoEmails){
            userInstance?.studentAccount?.infoEmails = params?.infoEmails
        }*/
        userInstance.validate()

        if (userInstance.hasErrors()) {

            //println("has Errors")

            render(view: "index", model: [userInstance: userInstance])
            return
        }


        if (!userInstance.save(flush: true)) {

            render(view: "index", model: [userInstance: userInstance])
            return
        }


        flash.message = message(code: 'student.account.update')
        redirect(action: "index")


    }

}
