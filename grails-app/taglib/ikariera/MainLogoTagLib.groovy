package ikariera

import cz.ikariera.admin.MainLogo

class MainLogoTagLib {
    def displayMainLogo = {
        attrs ->

        def logoTmp=MainLogo.find{ publish == true }
        def today = new Date()
        if (logoTmp!=null &&logoTmp.endDate.after(today)){
            out << "<a class=\"home\" href=\""<<createLink(uri: '/')<<"\">"<<"<img width=\"970px\" src=\""+'images/mainLogoIkariera/'+logoTmp.name+ "\" alt=\"iKariera\"/></a>"
        }

    }
}
