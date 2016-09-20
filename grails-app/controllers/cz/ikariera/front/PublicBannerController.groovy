package ikariera.front

import cz.ikariera.admin.Banner

class PublicBannerController {

    def getActiveBannerWithPosition() {


        def date = new Date()
        def banner = Banner?.find("from cz.ikariera.admin.Banner as b where b.expirationDate !=null and b.expirationDate>:date order by priority desc, rotation asc", [date: date])

        if (!banner) {

            render text: "", contentType: "text/plain"
        }

        render(view: "/layouts/_banner", model: [bannerInstance: banner])


    }
    def getBanner(){
        def date = new Date()

        def banner = Banner?.find("from cz.ikariera.admin.Banner as b where b.expirationDate !=null and b.expirationDate>:date order by priority desc, rotation asc", [date: date])

        if (!banner) {

            render text: "", contentType: "text/plain"
        }

        String uploadDirectory = grailsApplication.config.upload.directory.mediaFile

        try {

            //def picFile = new File(uploadDirectory + "/" + params.id)
            def picFile = new File(uploadDirectory + "/" + banner.newFilename)


            response.addHeader("Cache-Control", "no-store");

            response.contentType = 'image/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }
    def getSomeBanner(Banner banner){

        if (!banner) {

            render text: "", contentType: "text/plain"
        }

        String uploadDirectory = grailsApplication.config.upload.directory.mediaFile

        try {

            def picFile = new File(uploadDirectory + "/" + banner.newFilename)

            response.addHeader("Cache-Control", "no-store");

            response.contentType = 'image/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }

}