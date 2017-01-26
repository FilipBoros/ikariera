package cz.ikariera.admin

import cz.ikariera.admin.PictureMedia
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdminMediaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static developementDirectory = "/var/uploads"

    def index(Integer max, String sort) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = sort ?: "mediaType"
        render(view: "/adminMedia/index", model: [pictureMediaInstanceList: PictureMedia.list(params), pictureMediaInstanceCount: PictureMedia.count()])
    }

    def hero() {
        Integer max = Math.min(params.max ?: 50, 100)
        Integer offset = params.offset ?: 0
        String logo = "hero"
        String sort = params.name ?: "name"
        String order = params.oder ?: "asc"
        render(view: "/adminMedia/listHero", model: mediaSearch(max, logo, sort, order, offset))

    }

    def gallery() {

        Integer max = Math.min(params.max ?: 50, 100)
        Integer offset = params.offset ?: 0
        String logo = "gallery"
        String sort = params.name ?: "name"
        String order = params.oder ?: "asc"
        render(view: "/adminGallery/listGallery", model: mediaSearch(max, logo, sort, order, offset))
    }

    def picture() {
        Integer max = Math.min(params.max ?: 50, 100)
        Integer offset = params.offset ?: 0
        String logo = "picture"
        String sort = params.name ?: "name"
        String order = params.oder ?: "asc"
        render(view: "/adminMedia/listPicture", model: mediaSearch(max, logo, sort, order, offset))

    }

    def logo() {

        Integer max = Math.min(params.max ?: 50, 100)
        Integer offset = params.offset ?: 0
        String logo = "logo"
        String sort = params.name ?: "name"
        String order = params.oder ?: "asc"

        render(view: "/adminMedia/listLogo", model: mediaSearch(max, logo, sort, order, offset))
    }

    private static mediaSearch(Integer max, String type, String sort, String order, Integer offset) {
        def pictureMediaInstanceList = PictureMedia.findAllWhere(mediaType: type, [max: max, sort: sort, offset: offset, order: order])
        return [pictureMediaInstanceList: pictureMediaInstanceList]
    }

    def show(PictureMedia pictureMediaInstance) {
        respond pictureMediaInstance
    }

}
