package cz.ikariera.banner

import cz.ikariera.admin.Banner

/**
 * Created with IntelliJ IDEA.
 * User: cernydav
 * Date: 11/18/12
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
class BannerService {

    Banner getActiveBanner (){
                                                                                 //and b.rotation <= min(:rotation)
        def banner = cz.ikariera.admin.Banner.find("from cz.cz.cz.ikariera.admin.Banner as b where b.expirationDate != null and b.expirationDate > :date order by max(priority), min(rotation) desc",[ date: new Date()])
        if(!banner){
            return null
        }
        banner.counter++
        banner.rotation++
        banner.save()
        return banner
        //return subQuery.find("from " + subQuery +" order by rotation desc",[ date: new Date()])


    }





    Banner getActiveBannerWithPosition(String position) {
        //and b.rotation <= min(:rotation)

        def date = new Date()
        def banner = cz.ikariera.admin?.Banner?.find("from cz.cz.cz.ikariera.admin.Banner as b where b.expirationDate !=null and b.expirationDate>:date order by priority desc, rotation asc", [date: date]) //desc ... asc
        if (!banner) {
            return null
        }


        banner.counter++
        banner.rotation++
        banner.save(flush: true)
            //pridat flash.message

        return banner
        //return subQuery.find("from " + subQuery +" order by rotation desc",[ date: new Date()])

    }

}
