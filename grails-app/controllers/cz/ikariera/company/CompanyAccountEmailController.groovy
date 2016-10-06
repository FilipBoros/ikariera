/*
* Copyright 2012 Senman s.r.o.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package cz.ikariera.company

import cz.ikariera.company.CompanyEmails
import cz.ikariera.security.User
import cz.ikariera.service.CompanyServicesService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

class CompanyAccountEmailController {

    def springSecurityService
    def internalEmailService


    def index() {
        def user = User.cast(springSecurityService.getCurrentUser()) as User

        def company = user.company

        String uniqueName = "mail-service"
        if (!CompanyServicesService.isActivated(uniqueName, company)) {

            redirect(controller: "companyAccountServices", action: "activation", id: uniqueName)
            return
        }

        params.offset = params.offset ? params.int('offset') : 0
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        List companyEmailsInstanceList = CompanyEmails.createCriteria().list(params) {
            order('dateCreated', 'desc')
            eq("company", company)

        }

        def companyEmailsInstanceListTotal = companyEmailsInstanceList?.totalCount

        render(view: "index",
                model: [companyEmailsInstanceList     : companyEmailsInstanceList,
                        companyEmailsInstanceListTotal: companyEmailsInstanceListTotal
                ])
    }


    def create() {

        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }
        render(view: "create", model: [companyEmailsInstance: new CompanyEmails(params)])
    }

    private def checkCompanyPermission(CompanyEmails companyEmailsInstance) {
        User user = springSecurityService.getCurrentUser() as User

        def company = user.company
        if (companyEmailsInstance.company != company)
            return false

        return true
    }

    def edit(CompanyEmails companyEmailsInstance) {

        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }
        if (!checkCompanyPermission(companyEmailsInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        if (!companyEmailsInstance) {
            flash.message = message(code: 'default.not.found2.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index")
            return
        }

        render(view: "edit", model: [companyEmailsInstance: companyEmailsInstance, companyInstance: user.company])

    }


    def display() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }
        def companyEmailsInstance = CompanyEmails.get(params.id)

        if (!checkCompanyPermission(companyEmailsInstance)) {
            render(view: "/notFound", model: [:])
            return
        }
        if (!companyEmailsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index")
            return
        }

        render(view: "display", model: [companyEmailsInstance: companyEmailsInstance, companyInstance: user.company])

    }

    def show() {
        def user = springSecurityService.getCurrentUser()
        if (!user) {
            redirect(controller: "logout")
            return
        }

        def companyEmailsInstance = CompanyEmails.get(params.id)

        if (!checkCompanyPermission(companyEmailsInstance)) {
            render(view: "/notFound", model: [:])
            return
        }
        if (!companyEmailsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index")
            return
        }

        render(view: "raw", model: [companyEmailsInstance: companyEmailsInstance])

    }

    def save(CompanyEmails companyEmailsInstance) {

        User user = springSecurityService.getCurrentUser() as User
        if (!user) {
            redirect(controller: "logout")
            return
        }

        companyEmailsInstance.attachment = null

        companyEmailsInstance.company = user.company

        //save attachedments
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile f = request.getFile('attachmentFile')
            if (f.size > 0) {
                //companyEmailsInstance.contentType = f.contentType
                String serverPath = grailsApplication.config.upload.directory.emailAttachments

                //unique number for saving attachment
                UUID uid = UUID.randomUUID()
                companyEmailsInstance.attachment = uid.toString()
                companyEmailsInstance.originalName = f.originalFilename.toString()

                def storagePathDirectory = new File(serverPath + "/" + companyEmailsInstance.attachment + "/" + companyEmailsInstance.originalName)

                if (!storagePathDirectory.exists()) {
                    storagePathDirectory.mkdirs()
                }

                //println("storagePathDirectory> " + storagePathDirectory)
                f.transferTo(storagePathDirectory)


            } else {
                companyEmailsInstance.attachment = null
            }
        } else {
            companyEmailsInstance.attachment = null
        }

        if (!companyEmailsInstance.validate()) {
            render(view: "create", model: [companyEmailsInstance: companyEmailsInstance])
            return
        }

        if (!companyEmailsInstance.save(failOnError: true, flush: true)) {
            render(view: "create", model: [companyEmailsInstance: companyEmailsInstance])
            return
        }

        flash.message = message(code: 'email.created.label')
        redirect(action: "index")

    }

    def update() {

        CompanyEmails companyEmailsInstance = CompanyEmails.get(params.id)

        if (!checkCompanyPermission(companyEmailsInstance)) {
            render(view: "/notFound", model: [:])
            return
        }

        bindData(companyEmailsInstance, params, [exclude: ['company']])

        //save attachedments
        if (request instanceof MultipartHttpServletRequest) {

            MultipartFile f = request.getFile('attachmentFile')

            if (f.size > 0) {

                String serverPath = grailsApplication.config.upload.directory.emailAttachments

                UUID uid = UUID.randomUUID()
                companyEmailsInstance.attachment = uid.toString()

                companyEmailsInstance.originalName = f.originalFilename.toString()

                def storagePathDirectory = new File(serverPath + "/" + uid.toString() + "/" + companyEmailsInstance.originalName)

                if (!storagePathDirectory.exists()) {
                    storagePathDirectory.mkdirs()
                }
                f.transferTo(storagePathDirectory)
            }

            if (!companyEmailsInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label'), params.id])
                redirect(action: "index")
                return
            }

        }
        companyEmailsInstance.validate()

        if (companyEmailsInstance.hasErrors()) {
            render(view: "edit", model: [companyEmailsInstance: companyEmailsInstance])
            return
        }

        if (!companyEmailsInstance.save(flush: true)) {
            render(view: "edit", model: [companyEmailsInstance: companyEmailsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'companyEmails.label'), companyEmailsInstance.id])
        redirect(action: "index")
    }


    def delete() {
        def companyEmailsInstance = CompanyEmails.get(params.id)

        if (!companyEmailsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index")
            return
        }

        if (!checkCompanyPermission(companyEmailsInstance)) {
            render(view: "/notFound", model: [:])
            return
        }
        try {
            companyEmailsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'companyEmails.label'), params.id])
            redirect(action: "index", id: params.id)
        }
    }

    def uploadImage() {

        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile file = request.getFile('upload')

            if (!file.empty) {


                def serverFile

                try {
                    serverFile = uploadImageGen(file, grailsApplication.config.upload.directory.emailAttachments)
                }
                catch (Exception e) {
                    flash.error = message(code: 'fileupload.error') + e.message
                }

                def funcNum = params.CKEditorFuncNum

                def message = ""

                if (serverFile == null) {
                    message = flash.error
                }


                def link = createLink(controller: 'media', action: 'getAttachedImage', params: [path: serverFile?.getPath(), name: serverFile?.getName()], absolute: true)

                render text: "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + funcNum + ", '$link', '$message');</script>"
            }
        }
    }

    private def uploadImageGen(MultipartFile file, String serverPath) {
        if (file == null) return null

        if (file.size <= 0) return null

        // check file size, check file extension
        def okContentTypes = ['image/png', 'image/jpeg', 'image/jpg', 'image/gif']

        if (!okContentTypes.contains(file.getContentType())) {
            flash.error = message(code: 'system.validation.error.message')
            return null
        }

        // generate new fileName
        UUID uid = UUID.randomUUID()

        def newFileName = uid.toString() + file.originalFilename.substring(file.originalFilename.lastIndexOf("."))

        def storagePathDirectory = new File(serverPath + "/" + uid.toString() + "/" + newFileName)

        if (!storagePathDirectory.exists()) {
            storagePathDirectory.mkdirs()
        }

        file.transferTo(storagePathDirectory)

        return storagePathDirectory
    }

}