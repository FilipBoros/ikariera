package cz.ikariera.front

import cz.ikariera.admin.Contact
import cz.ikariera.company.Articles
import cz.ikariera.security.User
import grails.converters.JSON

class ArticlesController {


    def index() {

        params.max = Math.min(params.int('max') ? params.int('max') : 10, 100)
        params.offset = params.int('offset') ? params.int('offset') : 0


        params.sort = null

       // def orderVar = params.order ? params.order : "desc"
       // def sortVar = params.sort ? params.sort : "positionName"

       // def defaultBool=true
       // def array = ['header','bodyText','leadingText']
       // array.each{
       //     if(it==sortVar){
       //         defaultBool=false
       //     }
        //}
       // if(defaultBool==true){
       //     params.sort="header"
       // }

       // if(orderVar!="desc" || orderVar!="asc"){
       //     params.order="desc"
       // }

        List list = Articles.createCriteria().list(params) {
            gt('willExpire', new Date())
            isNotNull('datePublished')

            order('datePublished', 'desc')

        }
        def listSize = list.size()
        def contactInstance = Contact.findByContactIdentify("IAESTE")
        [
                articleInstanceList : list,
                displayedResults    : listSize < params.max + params.offset ? listSize - params.offset : params.max,
                articleInstanceTotal: list.totalCount,
                contactInstance     : contactInstance
        ]

    }

    /**
     * Filter method returns a list of filtered company users
     */
    def getTelephoneNumber() {


        def userId = params.userId
        def articleId = params.advertId

        def user = User.get(userId)

        def adv = Articles.get(articleId)

        def contact = adv?.contact

        if (contact && adv && contact?.user?.id == user?.id) {

            adv.contactCounter += 1
            adv.save(flush: true)

            def result = [data: contact.telephone]
            def converter = result as JSON;
            render converter.toString();
        }
    }

    /**
     * Filter method returns a list of filtered company users
     */
    def getEmailAddress() {


        def userId = params.userId
        def articleId = params.advertId

        def user = User.get(userId)

        def adv = Articles.get(articleId)

        def contact = adv?.contact

        if (contact && adv && contact?.user?.id == user?.id) {

            adv.contactCounter += 1
            adv.save(flush: true)

            def result = [data: contact.publicEmail]
            def converter = result as JSON;
            render converter.toString();
        }
    }


    def detail() {
        def articleInstance = null



        def id = params.id
        if (!id) {
            render(view: "/notFound", model: [:])
        } else if (id.isLong()) {
            articleInstance = Articles.findWhere(id: Long.parseLong(id))
        }

        if (!articleInstance) {
            render(view: "/notFound")

        } else {

            def contactInstance = Contact.findByContactIdentify("IAESTE")
            articleInstance.counter += 1

            articleInstance.save(flush: true)

            render(view: "/articles/detail", model: [articleInstance: articleInstance, contactInstance: contactInstance])
        }

    }
}
