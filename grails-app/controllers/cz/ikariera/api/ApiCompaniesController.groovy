package cz.ikariera.api

import cz.ikariera.company.Company
import cz.ikariera.front.CompanyProfilesFilterCommand
import grails.converters.JSON
import grails.converters.XML

class ApiCompaniesController {

    static allowedMethods = [
            list: "GET",
            detail: "GET",
    ]

    def mailService
    def internalEmailService
    def springSecurityService


    def index() {
        response.status = 404 //Not found
        render message(code: 'rest.api.notFound', default: "Not found")
        return
    }

    def list(CompanyProfilesFilterCommand filterCommand) {

        def list = params.list('mainJobCategories').collect { it as Long }
        def listLocalities = params.list('localities').collect { it as Long }

        filterCommand.max = Math.min(params.int('max') ? params.int('max') : 10, 20)
        filterCommand.offset = params.int('offset') ? params.int('offset') : 0
        //filterCommand.order = params.order ? params.order : "asc"
        filterCommand.sort = params.sort ? params.sort : "companyName"

        filterCommand.mainJobCategories = params.list('mainJobCategories')
        filterCommand.localities = params.list('localities')

        def companyInstanceListOriginal = Company.companiesFilter(filterCommand.filterParams)

        params.mainJobCategories = list
        params.localities = listLocalities

        response.status = 201

        def dataList = []
        companyInstanceListOriginal.each {
            def driveInfo = [:]
            driveInfo.id = it.id
            driveInfo.companyName = it.companyName
            driveInfo.locality = it.locality
            driveInfo.companyStreet = it.companyStreet
            driveInfo.companyCity = it.companyCity
            driveInfo.companyCountry = it.companyCountry
            driveInfo.companyZip = it.companyZip

            dataList << driveInfo

        }


        withFormat {

            json {
                render(text: dataList as JSON, contentType: "application/json", encoding: "UTF-8")
            }
            xml {
                render(text: dataList as XML, contentType: "application/xml", encoding: "UTF-8")
            }
        }

    }

    def detail(Long id) {
        Long companyId
        try {
            companyId = Long.parseLong(params.id)
        }
        catch (Exception e) {
            render(view: "/notFound")
            return
        }
        Company companyInstance = Company.get(companyId)

        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 10, 100)

        params.sort = null



        if (!companyInstance) {
            render(view: "/notFound")
            return
        }

        def dataList = []

            def driveInfo = [:]
            driveInfo.id = companyInstance.id
            driveInfo.companyName = companyInstance.companyName
            driveInfo.notes = companyInstance.notes
            driveInfo.seoName = companyInstance.seoName
            driveInfo.locality = companyInstance.locality
            driveInfo.companyStreet = companyInstance.companyStreet
            driveInfo.companyCity = companyInstance.companyCity
            driveInfo.companyCountry = companyInstance.companyCountry
            driveInfo.companyZip = companyInstance.companyZip

            driveInfo.companyWeb = companyInstance.companyWeb
            driveInfo.companyCharacteristic = companyInstance.companyCharacteristic
            driveInfo.credits = companyInstance.credits
            driveInfo.dateCreated = companyInstance.dateCreated

        withFormat {

            json {
                render(text: driveInfo as JSON, contentType: "application/json", encoding: "UTF-8")
            }
            xml {
                render(text: driveInfo as XML, contentType: "application/xml", encoding: "UTF-8")
            }
        }
    }

}
