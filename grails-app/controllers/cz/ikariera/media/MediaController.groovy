package cz.ikariera.media

import cz.ikariera.admin.PictureMedia

class MediaController {


    static allowedMethods = [getMedia: "GET", picture: "GET", thumbnail: "GET", getHeroMedia: "GET"]


    def getHeroMedia() {

        String uploadDirectory = grailsApplication.config.upload.directory.hero

        try {

            def picFile = new File(uploadDirectory + "/" + params.id + "." + params.format)

            // if (!picFile.exists() && picFile.canRead()) {

            //     redirect(controller: "error", action: "notFound")

            // } else {

            response.addHeader("Cache-Control", "no-store");

            response.contentType = 'image/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()
            // }
        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    def getMedia() {

        String uploadDirectory = grailsApplication.config.upload.directory.mediaFile



        try {

            def picFile = new File(uploadDirectory + "/" + params.id + "." + params.format)

            // if (!picFile.exists() && picFile.canRead()) {

            //     redirect(controller: "error", action: "notFound")

            // } else {

            response.addHeader("Cache-Control", "no-store");

            response.contentType = 'image/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()
            // }
        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    def picture() {

        def pic = PictureMedia.get(params.id)
        String uploadDirectory = grailsApplication.config.upload.directory.mediaFile



        try {


            def picFile = new File(uploadDirectory + "/" + pic.newFilename)

            //  if (!picFile.exists() && picFile.canRead()) {

            //     redirect(controller: "error", action: "notFound")

            //} else {


            response.contentType = 'PictureMedia/jpeg'
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()
            //}
        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }

    def thumbnail() {

        def pic = PictureMedia.get(params.id)
        String uploadDirectory = grailsApplication.config.upload.directory.mediaFile


        try {
            def picFile = new File(uploadDirectory + "/" + pic.thumbnailFilename)

            // if (!picFile.exists() && picFile.canRead()) {

            //    redirect(controller: "error", action: "notFound")

            //  } else {
            response.contentType = 'image/png'
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()
        }

        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    def getAttachedImage() {

        String uploadDirectory = grailsApplication.config.upload.directory.emailAttachments

        try {

            def picFile = new File(uploadDirectory + "/" + params.path + "/" + params.name + "." + params.format)
            //println uploadDirectory

            response.addHeader("Cache-Control", "no-store");

            response.contentType = 'image/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    def getAttachedMedia() {
        String uploadDirectory = grailsApplication.config.upload.directory.emailAttachments

        try {

            def picFile = new File(uploadDirectory + "/" + params.path + "/" + params.name)

            response.addHeader("Cache-Control", "no-store");

            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }




    def getCompanyLogo() {

        String uploadDirectory = grailsApplication.config.upload.directory.companyLogo

        try {



            def picFile = new File(uploadDirectory + "/" + params.id + "." + params.format)

            response.addHeader("Cache-Control", "no-store");

            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


    def getCompanyLogoMain() {

        String uploadDirectory = grailsApplication.config.upload.directory.companyMainLogo

        try {

            def picFile = new File(uploadDirectory + "/" + params.id + "." + params.format)

            response.addHeader("Cache-Control", "no-store");

            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }



    def getCompanyGalleryImage() {

        String uploadDirectory = grailsApplication.config.upload.directory.companyGallery

        def pictureMedia = PictureMedia.get(params.id)
        try {

            def picFile = new File(uploadDirectory + "/" + pictureMedia.newFilename)

            response.addHeader("Cache-Control", "no-store");

            // response.contentType = 'pdf/' + params.format
            response.outputStream << new FileInputStream(picFile)
            response.outputStream.flush()

        }
        catch (Exception e) {
            redirect(controller: "error", action: "notFound")
        }
    }


}
