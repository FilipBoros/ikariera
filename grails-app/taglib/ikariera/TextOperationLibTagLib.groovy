package ikariera

import cz.ikariera.admin.Banner
import cz.ikariera.banner.BannerService
import cz.ikariera.text.TextService
import grails.web.context.ServletContextHolder

class TextOperationLibTagLib {

    TextService textService = new TextService()  // This will be auto-wired

    def textStripTag = { attribs ->
        def textString = attribs['param1'].toString()
        def startInt = attribs['param2'].toString()
        def endInt = attribs['param3'].toString()
        def dots = attribs['param4'].toString()

        // Do something with the params

        out << textService.reduceString(textString, Integer.parseInt(startInt), Integer.parseInt(endInt), Boolean.parseBoolean(dots))

        //  "I just used method of MyService class"
    }







    def daysToNow = { attribs ->


        def startDate = new Date()
        def endDate = attribs['date']

        Integer hours = 0
        Integer days = 0
        Integer minutes =0


        def output = ""

        if(!endDate){

            out << ""
            return
        }

        use(groovy.time.TimeCategory) {
            def duration =  startDate - endDate
            days = duration.days
            hours = duration.hours
            minutes = duration.minutes
        }





        if (days == 0 && hours == 0 && minutes < 2) {

            output = "před " + 1 + " minutou"

        }

        else if (days == 0 && hours == 0) {

            output = "před " + minutes + " minutami"

        } else if (days == 0 && hours == 1) {


            output = "před " + 1 + " hodinou"



        } else if (days == 0 && hours > 0) {


            output = "před " + hours + " hodinami"

        } else if (days == 1) {

            output = "před " + 1 + " dnem"
        } else if (days < 10) {
            output = "před " + days + " dny"
        }      else {

            output =   formatDate(date: endDate, type: "date", style : "long")
        }





        out << output

    }




    def daysBetween = { attribs ->

        def startDate = attribs['startDate']
        def endDate = attribs['endDate']

        use(groovy.time.TimeCategory) {
            def duration = endDate - startDate
            out << duration.days
        }

    }

    def yearsBetween = { attribs ->

        def startDate = attribs['startDate']
        def endDate = attribs['endDate']



        use(groovy.time.TimeCategory) {
            def duration = endDate.year - startDate.year



            out << duration
        }

    }


    def inversePercentageBetween = { attribs ->

        Integer daysBetween = attribs['daysBetween'] as Integer

        Integer defaultExpirationTime = attribs['defaultExpirationTime'] as Integer

        out << (1 - daysBetween / defaultExpirationTime) * 100


    }



    def stripHtmlTags = { attribs ->
        def text = attribs['text']

        if (!text)
            text = ""

        text = text.toString()


        out << textService.prepareTextForHtml(text)

    }




    def loggedUserCompanyName = { attribs ->
        def id = attribs['id'].toString()

        out << textService.loggedUserCompanyName(Integer.parseInt(id))
    }


    def loggedUserCompanyCredits = { attribs ->
        def id = attribs['id'].toString()

        out << textService.loggedUserCompanyCredits(Integer.parseInt(id))
    }

    def loggedUserProfileFinished = { attribs ->
        def id = attribs['id'].toString()

        out << textService.loggedUserProfileFinished(Integer.parseInt(id))
    }


    def logoPartnerImage = { attribs ->
        def path = attribs['path'].toString()

        def servletContext = ServletContextHolder.servletContext

        out << '<div class="partner-logo" >  <asset:image   class="displayed"   src ="' + companies/path + '" />  </div>' /*TODO finnish this*/

    }

    BannerService bannerService = new BannerService()  // This will be auto-wired

    def bannerAdvertisement = { attribs ->
        def style = attribs['style']
        def _class = attribs['class']
        def position = attribs['position'].toString()

        Banner bannerInstance = bannerService.getActiveBannerWithPosition(position)

        if (bannerInstance) {
            out << '<a href ="' + bannerInstance.urlLink + '" style="' + style + '" class="' + _class + '"><asset:image src="' + bannerInstance.directory/bannerInstance.file + '" /> </a>'
        }

    }


}