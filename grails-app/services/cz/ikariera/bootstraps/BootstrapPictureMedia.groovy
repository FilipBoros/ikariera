package cz.ikariera.bootstraps

import cz.ikariera.admin.PictureMedia
import grails.web.context.ServletContextHolder

class BootstrapPictureMedia {


    public static void init(String uploadPath

    ) {


        def logoList = [

                "abb.png",
                "bosch.png",
                "ceps.png",
                "cez.png",
                "cpost.png",
                "csob.png",
                "deloitte.png",
                "elektrobit.png",
                "eon.png",
                "ge-aviation.png",
                "porsche.png",
                "procter.png",
                "ricardo.png",
                "rohde.png",
                "skoda.png",
                "sony-dadc.png",
                "tpca.png",
                "tsystems.png"
        ]





        def servletContext = ServletContextHolder.servletContext
        String realPath = servletContext.getRealPath('bootstrap/logos')

        logoList.eachWithIndex { String logo, int i ->

            File sourceFile = new File(realPath + "/" + logo)

            String newFilenameBase = UUID.randomUUID().toString()
            String thumbnailFilename = newFilenameBase + '-thumbnail.png'

            String originalFileExtension = logo.substring(logo.lastIndexOf("."))
            String newFilename = newFilenameBase + originalFileExtension


            File destinationFile = new File(uploadPath + "/" + newFilename)


            File destinationThumbnailFile = new File(uploadPath + "/" + thumbnailFilename)


            sourceFile?.withInputStream {
                destinationFile << it
            }

            sourceFile?.withInputStream {
                destinationThumbnailFile << it
            }

            new PictureMedia(


                    name: "some name",
                    mediaType: "logo",

                    originalFilename: logo,

                    thumbnailFilename: thumbnailFilename,
                    newFilename: newFilename,


                    fileSize: 125,
                    position: i,

                    width: 150,
                    height: 150


            ).save(failOnError: true)
        }


    }


}
