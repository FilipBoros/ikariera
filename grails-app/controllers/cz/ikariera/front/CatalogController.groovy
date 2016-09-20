package ikariera.front

class CatalogController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {

        redirect(url: "http://katalog.ikariera.cz")

       //def catalogInstance = Catalog.first()

       // render(view:"/catalogue/index", model:[catalogInstance: catalogInstance])

    }

    def archive() {
     /*   params.sort="date"
        params.order="desc"
        render(view:"/catalogue/archive", model:[catalogueList: Catalogue.list(params)])
   */ }



}