package cz.ikariera.bootstraps

import cz.ikariera.admin.Country
import cz.ikariera.company.HeroImage
import cz.ikariera.company.JobCategory
import grails.web.context.ServletContextHolder


class BootstrapHeroImage2 {


    public static void init(
            String uploadPath





    ) {


        def heroList = [

                [name: "architecture", value: "arch1.jpg"],
                [name: "civil", value: "civil1.jpg"],
                [name: "electrotechnics", value: "electro1.jpg"],
                [name: "it", value: "it.jpg"],
                [name: "machines", value: "mechanical1.jpg"],
                [name: "cars", value: "mechanical2.jpg"],
                [name: "machines", value: "mechanical3.jpg"],
                [name: "logistics", value: "transport1.jpg"]
        ]


        def servletContext = ServletContextHolder.servletContext
        String realPath = servletContext.getRealPath('bootstrap/hero')

        heroList.eachWithIndex {  hero, int i ->


            File sourceFile = new File(realPath + "/" + hero.value)

            String newFilenameBase = UUID.randomUUID().toString()
            String thumbnailFilename = newFilenameBase + '-thumbnail.png'

            String originalFileExtension = hero?.value?.substring(hero?.value?.lastIndexOf("."))
            String newFilename = newFilenameBase + originalFileExtension



            File destinationFile = new File(uploadPath + "/" + newFilename)

            File destinationThumbnailFile = new File(uploadPath + "/" + thumbnailFilename)


            sourceFile?.withInputStream {
                destinationFile << it
            }

            sourceFile?.withInputStream {
                destinationThumbnailFile << it
            }

            new HeroImage(

                    name: hero.name,
                    publish: true,
                    jobCategory: JobCategory.where{i18Name == hero.name }.find(),// (i % jobCategories.size()),
                    // company:  companies.get(i % companies.size()),
                    // locality: localities.get(i % localities.size()),
                    country: Country.where{i18Name == "CR"}.find(),
                    description: "This is description",
                    thumbnailLink: thumbnailFilename,
                    imageLink: newFilename

            ).save(failOnError: true)

        }


    }
}
