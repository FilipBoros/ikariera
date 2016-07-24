package cz.ikariera.bootstraps

import cz.ikariera.admin.Partner
import grails.web.context.ServletContextHolder

class BootstrapPartners {


    public static void init(
            String uploadPath
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
        String realPath = servletContext.getRealPath('bootstrap/logo')

        logoList.eachWithIndex { logo, i ->



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

            new Partner(

                    name: "Abb",
                    link: "http://abb.cz/",
                    thumbnailLink: thumbnailFilename,
                    imageLink: newFilename,
                    posOrder: i

            ).save(failOnError: true)

        }



    }
}




