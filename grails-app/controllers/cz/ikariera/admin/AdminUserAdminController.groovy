package ikariera.admin

import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole

class AdminUserAdminController {

    def springSecurityService

    def index() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def offset = params.offset ? params.int('offset') : 0
        def max = Math.min(params.max ? params.int('max') : 10, 100)
        def sort = params.sort ? params.sort : "accountCreated"
        def order = params.order ? params.order : "desc"

        def userInstanceList = User.createCriteria().list(max: max, offset: offset, sort: sort, order: order) {
            eq("adminAccount", true)

            if (params.firstName)
                ilike("firstName", '%' + params.firstName + '%')

            if (params.lastName)
                ilike("lastName", '%' + params.lastName + '%')

            if (params.username)
                ilike("username", '%' + params.username + '%')
        }
        def userInstanceListTotal = userInstanceList.totalCount



        render(view: "index", model: [userInstanceList: userInstanceList, userInstanceListTotal: userInstanceListTotal])
    }


    def create() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        render(view: "register")
    }


    def save() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User adminInstance = new User(params)
        bindData(adminInstance, params, [include: ['username']])

        if (params.password1 == "" || params.password2 == "") {
            adminInstance.errors.rejectValue('password1', 'system.emptyPassword.error')
            render(view: "/adminUserAdmin/register", model: [adminInstance: adminInstance])
            return

        }
        if (params.password1 != params.password2) {
            adminInstance.errors.rejectValue('password1', 'system.wrongControllPassword.error')
            render(view: "/adminUserAdmin/register", model: [adminInstance: adminInstance])
            return
        }

        //generate hashed password
        adminInstance.password = springSecurityService.encodePassword(params.password1, adminInstance.username)

        //saving
        adminInstance.accountExpired = false
        adminInstance.accountLocked = false
        adminInstance.enabled = true
        adminInstance.passwordExpired = false
        adminInstance.adminAccount = true
        if (!adminInstance.validate()) {
            render(view: "/adminUserAdmin/register", model: [adminInstance: adminInstance])
            return
        }

        if (!adminInstance.save(flush: true, failOnError: true)) {
            adminInstance.password = null
            //  adminInstance.password1 = ""
            //  adminInstance.password2 = ""

            flash.error = message(code: 'system.unspecifiedError.error')
            render(view: "register", model: [adminInstance: adminInstance])
            return
        }

        UserRole.create(adminInstance, Role.findByAuthority('ROLE_ADMIN'), true)

        flash.message = message(code: 'system.admin.saved.message')
        redirect(action: 'index')
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
            redirect(action: "index")
            return
        }

        render(view: "edit", model: [adminInstance: editedUser])
    }

    def savePass() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User adminInstance = User.get(params.id)
        if (!adminInstance) {
            flash.error = message(code: 'system.userDoesntExist.error')
            redirect(action: "index")
            return
        }

        def encrOldPass = springSecurityService.encodePassword(params.oldPass, adminInstance.username)

        if (encrOldPass != adminInstance.password) {
            adminInstance.errors.rejectValue('password', 'passwordChange.wrongOldPassword.error')
            render(view: "/adminUserAdmin/changePass", model: [adminInstance: adminInstance])
            return
        }

        if (params.passwordConfirm != params.password) {
            adminInstance.errors.rejectValue('passwordConfirm', 'system.wrongControllPassword.error')
            render(view: "/adminUserAdmin/changePass", model: [adminInstance: adminInstance])
            return
        }

        adminInstance.password = springSecurityService.encodePassword(params.password, adminInstance.username)
        adminInstance.passwordConfirm = adminInstance.password

        if (!adminInstance.save(flush: true)) {
            render(view: "/adminUserAdmin/changePass", model: [adminInstance: adminInstance])
            return
        }

        flash.message = message(code: 'passwordChange.newPassword.message')
        redirect(controller: "adminUserAdmin", action: "index")
    }

    def changePass() {
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

        render(view: "changePass", model: [adminInstance: editedUser])
    }

    def update() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        User adminInstance = User.get(params.id)
        if (!adminInstance) {
            flash.error = message(code: 'system.userDoesntExist.error')
            redirect(action: "index")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (adminInstance.version > version) {
                flash.error = message(code: 'system.version.error')
                redirect(action: "adminsList")
                return
            }
        }

        adminInstance.firstName = params?.firstName
        adminInstance.lastName = params?.lastName
        adminInstance.username = params?.username

        if (!adminInstance.save(flush: true)) {
            render(view: "/adminUserAdmin/edit", model: [adminInstance: adminInstance])
            return
        }

        flash.message = message(code: 'system.administrator.updated')
        redirect(controller: "adminUserAdmin", action: "index")
    }


    def delete(User userInstance) {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }


        if (!userInstance) {
            flash.error = message(code: "system.userDoesntExist.error")
            redirect(controller: "adminUserAdmin", action: "index")
            return
        }
        UserRole.removeAll(userInstance)
        userInstance.delete(flush: true)

        flash.message = message(code: 'student.account.delete')
        redirect(controller: "adminUserAdmin", action: "index")

    }


}
