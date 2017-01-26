package cz.ikariera.admin

import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import cz.ikariera.student.StudentAccount
import grails.converters.JSON

class AdminUserStudentController {
    def springSecurityService
    def mailService


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

            isNull("company")
            eq("adminAccount", false)

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
                        userInstanceList     : userInstanceList,
                        userInstanceListTotal: userInstanceListTotal,
                ],
                params: params
    }

    def removeUserFromMailingList() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userInstance = User.get(userId)

        StudentAccount studentInstance = userInstance?.studentAccount
        if (!studentInstance) {
            render([text: "userId: false"]) as JSON
            return
        }


        studentInstance.infoEmails = false;
        studentInstance.save(flush: true)

        def result = [userId: userId, data: "N"]
        def converter = result as JSON;
        render converter.toString();
    }

    def addUserToMailingList() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            render([text: "userId: false"]) as JSON
            return
        }
        def userId = params.id
        if (!userId) {
            render([text: "userId: false"]) as JSON
            return
        }

        def userInstance = User.get(userId)

        StudentAccount studentInstance = userInstance?.studentAccount
        if (!studentInstance) {
            render([text: "userId: false"]) as JSON
            return
        }

        studentInstance.infoEmails = true;
        studentInstance.save(flush: true)


        def result = [userId: userId, data: "E"]
        def converter = result as JSON;
        render converter.toString();
    }


    def save() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def studentUser = User.get(params.id)

        bindData(studentUser, params, [include: ['studentAccount']])
        studentUser.firstName = params?.firstName
        studentUser.lastName = params?.lastName
        studentUser.username = params?.username

        studentUser.studentAccount.jobCategories.clear()
        bindData(studentUser.studentAccount, params)

        if (!studentUser.validate()) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (!studentUser.save(flush: true, failOnError: true)) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])

            return
        }

        flash.message = message(code: 'student.account.update')
        redirect(controller: "adminUserStudent", action: "edit", params: [id: params.id])
    }

    def saveLanguages() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def studentUser = User.get(params.userId)

        StudentAccount studentInstance = studentUser.studentAccount

        studentInstance?.languages.clear()
        bindData(studentInstance, params)

        if (!studentInstance.validate()) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (studentInstance.hasErrors()) {

            //println(studentInstance.errors)
            flash.error = 'Fail to update student, error in language.'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }


        if (!studentInstance.save(flush: true, failOnError: true)) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])

            return
        }

        flash.message = message(code: 'student.account.update')
        redirect(controller: "adminUserStudent", action: "edit", params: [id: params.userId])
    }

    def saveEducation() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def studentUser = User.get(params.userId)

        StudentAccount studentInstance = studentUser.studentAccount
        //println(params)
        studentInstance?.educations?.clear()

        bindData(studentInstance, params)

        if (!studentInstance.validate()) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (studentInstance.hasErrors()) {

            //println(studentInstance.errors)
            flash.error = 'Fail to update student, error in language.'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (!studentInstance.save(flush: true, failOnError: true)) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])

            return
        }

        flash.message = message(code: 'student.account.update')
        redirect(controller: "adminUserStudent", action: "edit", params: [id: params.userId])
    }

    def saveCertificates() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def studentUser = User.get(params.userId)

        StudentAccount studentInstance = studentUser.studentAccount
        //println(params)
        studentInstance?.certificates?.clear()

        bindData(studentInstance, params)

        if (!studentInstance.validate()) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (studentInstance.hasErrors()) {

            //println(studentInstance.errors)
            flash.error = 'Fail to update student, error in language.'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (!studentInstance.save(flush: true, failOnError: true)) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])

            return
        }

        flash.message = message(code: 'student.account.update')
        redirect(controller: "adminUserStudent", action: "edit", params: [id: params.userId])
    }

    def saveExperiences() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def studentUser = User.get(params.userId)

        StudentAccount studentInstance = studentUser.studentAccount

        studentInstance?.experiences?.clear()

        bindData(studentInstance, params)

        if (!studentInstance.validate()) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (studentInstance.hasErrors()) {

            //println(studentInstance.errors)
            flash.error = 'Fail to update student, error in language.'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (!studentInstance.save(flush: true, failOnError: true)) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])

            return
        }

        flash.message = message(code: 'student.account.update')
        redirect(controller: "adminUserStudent", action: "edit", params: [id: params.userId])
    }

    def saveContact() {

        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        def id = params.id
        def studentUser = User.get(id)

        StudentAccount studentInstance = studentUser?.studentAccount

        bindData(studentInstance, params.studentAccount)
        if (!studentInstance.validate()) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (studentInstance.hasErrors()) {

            //println(studentInstance.errors)
            flash.error = 'Fail to update student, error in language.'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])
            return
        }

        if (!studentInstance.save(flush: true, failOnError: true)) {
            flash.error = 'Fail to update student'
            render(view: "/adminUserStudent/editStudent", model: [userInstance: studentUser])

            return
        }

        flash.message = message(code: 'student.account.update')
        redirect(controller: "adminUserStudent", action: "edit", params: [id: id])
    }

    def edit() {

        def user = springSecurityService.getCurrentUser()
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


        render(view: "/adminUserStudent/editStudent", model: [userInstance: editedUser])
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

        flash.message = "Uzivatel smazan"
        redirect(controller: "adminUserStudent", action: "index")

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

        render(view: "changePass", model: [userInstance: editedUser])
    }

    def savePass() {
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
            render(view: "/adminUserStudent/changePass", model: [adminInstance: userInstance])
            return
        }

        userInstance.password = springSecurityService.encodePassword(params.password, userInstance.username)
        userInstance.passwordConfirm = userInstance.password

        if (!userInstance.save(flush: true)) {
            render(view: "/adminUserStudent/changePass", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'passwordChange.newPassword.message')
        redirect(controller: "adminUserStudent", action: "index")
    }

}

/**
 * Command class for binding filter data from controller
 */
class UserAdminFilterCommand {


    String firstName
    String lastName

    //  Role role

    Boolean isStudent
    Boolean isCompany
    Boolean admin

    String username

    Integer max
    Integer offset

    String sort
    String order

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << ["firstName": firstName,
                         "lastName" : lastName,
                         "username" : username,
                         "max"      : max,
                         "isStudent": isStudent,
                         "isCompany": isCompany,
                         "admin"    : admin,

                         //  "role": role,

                         "offset"   : offset,
                         "sort"     : sort,
                         "order"    : order
        ]

        return filterParams
    }


}
