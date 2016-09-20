package cz.ikariera.api

import cz.ikariera.company.JobOffer
import cz.ikariera.front.CompanyProfilesFilterCommand
import grails.converters.JSON
import grails.converters.XML

class ApiJobOffersController {

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

        /*filterCommand.jobCategories = params.list('jobCategories')
        filterCommand.positionLocalities = params.list('positionLocalities')
        filterCommand.jobOfferType = params.list('jobOfferType')*/

        params.offset = params.int('offset') ? params.int('offset') : 0
        params.max = Math.min(params.int('max') ? params.int('max') : 20, 100)
        filterCommand.max = params.max

        filterCommand.sort = null

        def list = JobOffer.jobOfferFilter(filterCommand.filterParams)

        response.status = 201

        def dataList = []
        list.each {
            def driveInfo = [:]
            driveInfo.id = it.id
            driveInfo.positionName = it.positionName
            driveInfo.positionLocality = it.positionLocality
            driveInfo.datePublished = it.datePublished.toString()
            driveInfo.topPos = it.topPos
            driveInfo.companyName = it.company.companyName
            driveInfo.companyID = it.company.companyID

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

    def detail() {

        def jobOffer = null

        def id = params.id
        if (!id) {
            flash.error = message(code: "default.not.found.message", args: ['Job offer', params.id])
            render(view: "/notFound", model: [:])
        } else if (id.isLong()) {
            jobOffer = JobOffer.findWhere(id: Long.parseLong(id))
        }

        if (!jobOffer || jobOffer.datePublished == null) {
            render(view: "/notFound", model: [:])
        } else {
            jobOffer.counter += 1
            jobOffer.save(flush: true)
        }

            def driveInfo = [:]
            driveInfo.id = jobOffer.id
            driveInfo.positionName = jobOffer.positionName
            driveInfo.positionLocality = jobOffer.positionLocality
            driveInfo.datePublished = jobOffer.datePublished.toString()
            driveInfo.topPos = jobOffer.topPos
            driveInfo.companyName = jobOffer.company.companyName
            driveInfo.companyID = jobOffer.company.companyID
            driveInfo.jobOfferDescription = jobOffer.jobOfferDescription
            driveInfo.jobApplicantRequire = jobOffer.jobApplicantRequire
            driveInfo.jobTypeDescription = jobOffer.jobTypeDescription
            driveInfo.jobStartDate = jobOffer.jobStartDate
            driveInfo.jobStartDate = jobOffer.jobStartDate
            driveInfo.dateCreated = jobOffer.dateCreated



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
