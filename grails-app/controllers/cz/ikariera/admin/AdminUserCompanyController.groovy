package ikariera.admin

import cz.ikariera.company.CompanyAccount
import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole

class AdminUserCompanyController {

    def springSecurityService


    def index() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }


        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)
        def sort =  params.sort ? params.sort : "accountCreated"
        def order = params.order ? params.order : "desc"

        def userInstanceList = User.createCriteria().list(max: max, offset: offset, sort: sort, order: order) {

            isNotNull("company")

            if (params.firstName)
                ilike("firstName", '%' + params.firstName + '%')

            if (params.lastName)
                ilike("lastName", '%' + params.lastName + '%')

            if (params.username)
                ilike("username", '%' + params.username + '%')

        }


        def userInstanceListTotal = userInstanceList.totalCount


        render view: 'index',
                model: [
                        userInstanceList: userInstanceList,
                        userInstanceListTotal: userInstanceListTotal

                ],
                params: params
    }


    def save() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }


        User.withTransaction { status ->
            def userInstance = User.get(params.id)

            userInstance?.properties = new User(params)
            if(!userInstance?.companyAccount){
                userInstance?.companyAccount = new CompanyAccount(params)
            }else{
                userInstance?.companyAccount?.properties = new CompanyAccount(params)
            }

            if (!userInstance.validate()) {
                status.setRollbackOnly()

                render(view: "/adminUserCompany/editCompanyUser", model: [userInstance: userInstance])
                return
            }

            if (!userInstance.save()) {
                status.setRollbackOnly()
                render(view: "/adminUserCompany/editCompanyUser", model: [userInstance: userInstance])

                return
            }

            UserRole.create(userInstance, Role.findByAuthority('ROLE_ADMIN'), true)

            flash.message = message(code: 'company.account.update')
            redirect(controller: "adminUserCompany", action: "index")


        }


    }

    def edit() {

        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User editedUser = User.get(params.id)
        if (!editedUser) {
            flash.error = message(code: "system.userDoesntExist.error")
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(action: "adminsList")
            return
        }

        render(view: "/adminUserCompany/editCompanyUser", model: [userInstance: editedUser])
    }


    def delete(User userInstance) {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }


        if (!userInstance) {
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(action: "index")
            return
        }
        UserRole.removeAll(userInstance)
        userInstance.delete(flush: true)

        flash.message = message(code: 'student.account.delete')
        redirect(controller: "adminUserCompany", action: "index")

    }


    def changePass(){
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User editedUser = User.get(params.id)
        if (!editedUser) {
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(action: "index")
            return
        }

        render(view: "changePass", model: [userInstance: editedUser])
    }

    def savePass(){
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User userInstance = User.get(params.id)
        if (!userInstance) {
            flash.error = message(code: 'system.userDoesntExist.error')
            redirect(action: "index")
            return
        }

        if (params.passwordConfirm != params.password) {
            userInstance.errors.rejectValue('passwordConfirm', 'system.wrongControllPassword.error')
            render(view: "/adminUserCompany/changePass", model: [adminInstance: userInstance])
            return
        }

        userInstance.password = springSecurityService.encodePassword(params.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password

        if (!userInstance.save(flush: true)) {
            render(view: "/adminUserCompany/changePass", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'passwordChange.newPassword.message')
        redirect(controller: "adminUserCompany", action: "index")
    }


}
