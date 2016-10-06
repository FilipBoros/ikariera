package cz.ikariera.front

import cz.ikariera.admin.Faq

class FaqController {

    //static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    /*def  FAQ () {
        render (view: "/about/faq")

    } */


    def index() {


        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        //print(session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'])
        //render(view: '/faq/list', model: [faqInstanceList: Faq.list(params), faqInstanceTotal: Faq.count()])
        //LanguageType lang = new LanguageType(name: session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'])
        /* for testing */
        def a = [lang:session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'].toString()]
        //def list = Faq.findAll("from Faq as p where p.languageType.name=:lang order by p.posOrder",[lang:session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'].toString()])
        render(view: '/faq/list', model: [faqInstanceList: Faq.findAll("from Faq as p where p.languageType.name=:lang order by p.posOrder",[lang:session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'].toString()]), faqInstanceTotal: Faq.count()])
        //render(view: '/faq/list', model: [faqInstanceList: Faq.findAll("from Faq as p order by p.posOrder", faqInstanceTotal: Faq.count())])
    }


    //smazat
    def show() {
        def faqInstance = Faq.get(params.id)
        if (!faqInstance) {
            flash.message = message(code: 'faq.not.found.message')
            redirect(action: "list")
            return
        }

        render(view: '/faq/show', model: [faqInstance: faqInstance])

    }
}
