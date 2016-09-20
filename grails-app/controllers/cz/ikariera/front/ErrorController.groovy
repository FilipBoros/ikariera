package ikariera.front

import grails.util.Environment


class ErrorController {


    def internalEmailService
    def mailService

    def index = {

        redirect(controller: "error", action: "serverError")
    }

    def denied() {
        render(view: '/login/denied')
    }

    def serverError = {


        Environment.executeForCurrentEnvironment {
            development {

                def exception = request.exception
                render(view: '/error')


            }
            production {


                render(view: '/serverError')

            }
        }
    }

    def notFound = {

        response.status = 404
        render(view: '/notFound')

    }
}