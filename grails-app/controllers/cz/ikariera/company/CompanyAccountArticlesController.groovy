package ikariera.company

import cz.ikariera.company.Articles
import cz.ikariera.company.Services
import cz.ikariera.security.User
import cz.ikariera.service.CompanyServicesService


class CompanyAccountArticlesController {

    def springSecurityService
    def mailService


    def index() {
        User user = springSecurityService.getCurrentUser() as User


        def company = user.company

        String uniqueName = "articles-service"
        if (!CompanyServicesService.isActivated(uniqueName, company)) {

            redirect(controller: "companyAccountServices", action: "activation", id: uniqueName)
            return
        }

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        List articleInstanceList = Articles.createCriteria().list(params) {
            order('dateCreated', 'desc')
            eq("company", company)

        }

        def articleInstanceListTotal = articleInstanceList?.totalCount

        render(view: "index", model: [articleInstanceListTotal: articleInstanceListTotal, articleInstanceList: articleInstanceList])

    }

    private def checkCompanyPermission(Articles articleInstance) {
        User user = springSecurityService.getCurrentUser() as User

        def company = user.company
        if (articleInstance.company != company)
            return false

        return true
    }


    def show(Articles articleInstance) {

        if (!articleInstance?.datePublished) {
            flash.message = message(code: 'companyAccount.articleUpdated.preview.label')
        }
        redirect(controller: "companyAccountArticles", action: "detailNotPublished", id: articleInstance?.id)

    }

    def detailNotPublished(Articles articleInstance) {

        if (!articleInstance) {
            flash.error = message(code: "default.not.found.message", args: ['Articles', params.id])
            render(view: "/notFound", model: [:])
            return
        }

        render( view: "/articles/detail", model: [articleInstance: articleInstance])
    }




    def edit(Articles articleInstance) {

        if (!checkCompanyPermission(articleInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        render(view: "edit", model: [articleInstance: articleInstance])

    }


    def update() {
        User user = springSecurityService.getCurrentUser() as User


        Articles articleInstance = Articles.get(params.id)

        if (!checkCompanyPermission(articleInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        bindData(articleInstance, params, [exclude: ['company']])

        if (!articleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label'), params.id])
            redirect(action: "list")
            return
        }

        articleInstance.validate()


        if (articleInstance.hasErrors()) {
            render(view: "edit", model: [articleInstance: articleInstance])
            return
        }

        if (!articleInstance.save(flush: true, failOnError: true)) {
            flash.error = "${message(code: "system.articleCompanyNoSave.error")}"
            render(view: "edit", model: [articleInstance: articleInstance])
            return
        }

        flash.message = message(code: "companyAccount.articleUpdated.message")
        redirect(controller: "companyAccountArticles")

    }


    def create() {

        User user = springSecurityService.getCurrentUser() as User

        render(view: "create", model: [articleInstance: new Articles()])
    }


    def save(Articles articleInstance) {

        User user = springSecurityService.getCurrentUser()

        articleInstance.company = user.company

        articleInstance.validate()

        if (articleInstance.hasErrors()) {
            render(view: "create", model: [articleInstance: articleInstance])
            return
        }

        if (!articleInstance.save(flush: true, failOnError: true)) {
            flash.error = "${message(code: "system.articleCompanyNoSave.error")}"
            render(view: "create", model: [articleInstance: articleInstance])
            return
        }

        flash.message = message(code: "companyAccount.articleSaved.message")
        redirect(controller: "companyAccountArticles")

    }


    def delete() {
        User user = springSecurityService.getCurrentUser() as User

        def articleInstance = Articles.get(params.id)

        if (!checkCompanyPermission(articleInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        if (!articleInstance) {
            flash.error = "${message(code: "system.articleDoesntExist.error")}"
            redirect(controller: "companyAccountArticles")
            return
        }


        if (articleInstance.company.id != user.company.id) {
            flash.error = "${message(code: "system.articleCompanyIsWrong.error")}"
            redirect(controller: "companyAccountArticles")
            return
        }

        articleInstance.delete(flush: true)

        flash.message = "${message(code: "system.articleDeleted.messge")}"
        redirect(controller: "companyAccountArticles")
    }


    def publish(Articles articleInstance) {

        User user = springSecurityService.getCurrentUser()

        if (!checkCompanyPermission(articleInstance)) {
            render(view: "/notFound", model: [:])
            return
        }


        if (!articleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label'), params.id])
            redirect(controller: "companyAccountArticles")
            return
        }

        articleInstance.datePublished = new Date()
        articleInstance.willExpire = new Date() + Services.findByUniqueName('admin-services-article-publish').defaultExpirationTime

        if (!articleInstance.save(flush: true, failOnError: true)) {
            flash.error = "${message(code: "system.articleCompanyNoPublished.error")}"
            redirect(controller: "companyAccountArticles")
            return
        }

        flash.message = message(code: "companyAccount.articlePublished.message")
        redirect(controller: "companyAccountArticles")

    }


    def detail() {
        User user = springSecurityService.getCurrentUser()
        //isLogged?
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def articleInstance
        if (params.id) {
            articleInstance = Articles.get(params.id)
        } else if (params.seoName) {
            articleInstance = Articles.findWhere(seoName: params.seoName)
        } else {
            render(view: "/notFound")
            return
        }
        if (!articleInstance) {
            render(view: "/notFound")

        } else {

            articleInstance.counter++
            articleInstance.save(flush: true)

            render(view: "/articles/detail", model: [articleInstance: articleInstance, companyUserList: user.companyAccountUserList()])
        }

    }


    def longerExpire() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }

        //print params
        def articleInstance = Articles.findById(params.id)
        if (!checkCompanyPermission(articleInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        if (params.dateTo) {
            articleInstance.willExpire = params.date(params.dateTo)
        }
        articleInstance.willExpire = new Date() + (Integer) Services.findByUniqueName("admin-services-article-publish").defaultExpirationTime
        articleInstance.save(flush: true, failOnError: true)

        redirect(action: "list", controller: "companyAccountArticles")
    }


    def expire() {
        User user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "login")
        }
        def articleInstance = Articles.findById(params.id)
        if (!checkCompanyPermission(articleInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        articleInstance.willExpire = null
        articleInstance.save(flush: true, failOnError: true)

        redirect(controller: "companyAccountArticles")
    }

}
