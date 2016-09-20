package ikariera.api
import cz.ikariera.admin.ApiKey
import cz.ikariera.admin.RemoteRegister
import cz.ikariera.security.Role
import cz.ikariera.security.User
import cz.ikariera.security.UserRole
import cz.ikariera.student.Cv
import cz.ikariera.student.StudentAccount
import grails.converters.JSON
import groovy.time.TimeCategory
import org.springframework.web.multipart.MultipartHttpServletRequest

class RemoteRegisterController {

    def mailService
    def internalEmailService
    def springSecurityService


    def index ()
    {
        redirect( action: 'save')
    }


    def save() {

        ApiKey APIKEY = ApiKey.find {serviceName == "RemoteRegistration"}

        if (APIKEY.value != params.token){
            response.status = 405
            render message(code: 'rest.api.accessDenied', default: "Access Denied")
            return
        }



        boolean isDirty = params.isDirty?: false

        isDirty = params.password? false : true

        User userInstance = new User(params)
        RemoteRegister remoteRegister = new RemoteRegister()

        remoteRegister.username = userInstance.username
        remoteRegister.firstName = userInstance.firstName
        remoteRegister.firstName = userInstance.lastName

        remoteRegister.uid = userInstance.id


        //remoteRegister.data = params.toQueryString()




        def map = saveUser(userInstance, isDirty)

        remoteRegister.save(failOnError: true, flush: true)

        if (map.get("code")==400){
            response.status = 400 //Bad Request
            render map.get("message")
            return
        }

        if (isDirty) {
            sendEmailDirtyPassword(userInstance, map.get("pass")?.toString())
        } else {
            sendEmail(userInstance)
        }

        response.status = 201

        withFormat {
            json {
                def obj = JSON.parse("{id:"+userInstance.id+"}")
                render ( text: obj , contentType: "application/json", encoding: "UTF-8")
            }
        }
    }


    def saveUser(User userInstance, boolean isDirty){

        def resultMap = [:]

        userInstance.studentAccount = new StudentAccount()
        userInstance.username = userInstance.username?.toString()?.toLowerCase()

        userInstance.termOfUse = true
        userInstance.enabled = true

        userInstance.remote = true

        userInstance.note = userInstance.note?:"remote"

        String dirtyPassword = ""
        if (isDirty) {
            dirtyPassword = org.apache.commons.lang.RandomStringUtils.random(9, true, true)
            userInstance.password = springSecurityService.encodePassword(dirtyPassword, userInstance.username)
            userInstance.passwordConfirm = userInstance.password
            userInstance.token = java.util.UUID.randomUUID().toString()
            Date now = new Date()
            use(TimeCategory) {
                userInstance.tokenExpiration = now + 48.hours
            }
        } else {

            if (params.passwordConfirm != userInstance.password) {
                userInstance.errors.rejectValue('passwordConfirm', 'system.emptyPassword.error')
            }
            userInstance.password = springSecurityService.encodePassword(userInstance.password, userInstance.username)
            userInstance.passwordConfirm = userInstance.password
            userInstance.token = java.util.UUID.randomUUID().toString()
            Date now = new Date()
            use(TimeCategory) {
                userInstance.tokenExpiration = now + 2.hours
            }
        }
        resultMap.put("pass", dirtyPassword)
        userInstance.validate()

        if(userInstance.hasErrors()){
            resultMap.put("code", 400)
            resultMap.put("message", message(code: 'validation.error', args: [userInstance.errors], default: "Validation error {0} "))
            return resultMap

        }

        if (!userInstance.save(failOnError: true, flush: true)) {
            resultMap.put("code", 400)
            resultMap.put("message", message(code: 'save.error', args: [userInstance.errors], default: "Saving Error {0} "))
            return resultMap
        }

        UserRole.create(userInstance, Role.findByAuthority('ROLE_STUDENT'), true)


        resultMap.put("id",userInstance.id)
        resultMap.put("code", 200)
        resultMap.put("message", "User Created")

        return resultMap
    }

    def trySaveFileinternal(def file, User userInstance){
        def map = [:]

        if (file.fileItem.size == 0)
        {
            map.put("code",400)
            map.put("message","File cannot be empty")
            return map
        }
        String newFilenameBase = UUID.randomUUID().toString()
        String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
        originalFileExtension = originalFileExtension?.toLowerCase()

        if (originalFileExtension != '.pdf') {
            map.put("code",400)
            map.put("message",message(code: 'student.cv.pdf', args: [userInstance.errors], default: "CV ne PDF {0} "))
            return map
        }

        if (file.size > 5000000) {
            map.put("code",400)
            map.put("message", map.put("message",message(code: 'student.cv.pdf', args: [userInstance.errors], default: "CV ne PDF {0} ")))
            return map
        }

        String newFilename = newFilenameBase + originalFileExtension

        String storageDirectory = grailsApplication.config.upload.directory.studentCv

        File newFile = new File("$storageDirectory/$newFilename")
        file.transferTo(newFile)

        Cv cvInstance = new Cv(
                originalFilename: file.originalFilename,
                student: userInstance.studentAccount,
                newFilename: newFilename,
                name: file.originalFilename,
                fileSize: file.size
        )
        if (!cvInstance.save(flush: true, failOnError: true)){
            map.put("code",400)
            map.put("message", "File save failed")
            return map
        }
        map.put("code",200)
        map.put("message", "CV file uploaded")
        return map
    }

    def saveUserAndFile(){

        if (APIKEY.value != params.token){
            response.status = 405
            render message(code: 'rest.api.accessDenied', default: "Access Denied")
            return
        }
        //retreeeve info
        boolean isDirty = params.isDirty?: false

        isDirty = params.password? false : true

        User userInstance = new User(params)

        def map = saveUser(userInstance, isDirty)

        if (map.get("code")==400){
            response.status = 400 //Bad Request
            render resultMap.get("message")
            return
        }

        if (isDirty) {
            sendEmailDirtyPassword(userInstance, dirtyPassword)
        } else {
            sendEmail(userInstance)
        }

        def resultMap = [:]

        if (request instanceof MultipartHttpServletRequest) {
            for (filename in request.getFileNames()) {
                resultMap = trySaveFileinternal(request.getFile(filename), userInstance)
                if (resultMap.get("code") == 400){
                    response.status = 400 //Bad Request
                    render resultMap.get("message")
                    return
                }
            }
        }

        response.status = 200 // ok

        render text: "User and file created"
    }

    def saveFile(){
        ApiKey APIKEY = ApiKey.find {serviceName == "RemoteRegistration"}
        if (APIKEY.value != params.token){
            response.status = 405
            render message(code: 'rest.api.accessDenied', default: "Access Denied")
            return
        }

        User userInstance = User.get(params.id)
        if (userInstance == null){
            response.status = 404
            render "Not valid user id"
            return
        }
        def resultMap = [:]
        if (request instanceof MultipartHttpServletRequest) {
            for (filename in request.getFileNames()) {
                resultMap = trySaveFileinternal(request.getFile(filename), userInstance)
                if (resultMap.get("code") == 400){
                        response.status = 400 //Bad Request
                        render resultMap.get("message")
                        return
                    }
                }
        }
        response.status = 200 // ok

        render text: "CV uploaded"
    }

    private void sendEmail(User userInstance) {


        try {
            internalEmailService.sendSimpleEmailMessage(
                    userInstance.username,

                    message(code: 'mail.from.label.registration') + "" +  (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.replyAddress,
                    grailsApplication.config.internalEmailService.subjectPrefix + "" + message(code: "mail.subject.student"),


                    g.render(template: "/email/registrationConfirmation", model: [user: userInstance])
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e

        }


    }


    private void sendEmailDirtyPassword(User userInstance, String dirtyPassword) {


        try {
            internalEmailService.sendSimpleEmailMessage(
                    userInstance.username,

                    message(code: 'mail.from.label.registration') + "" +  (grailsApplication.config.internalEmailService.name),
                    grailsApplication.config.internalEmailService.replyAddress,
                    grailsApplication.config.internalEmailService.subjectPrefix + "" + message(code: "mail.subject.student"),

                    g.render(template: "/email/remoteRegistrationConfirmation", model: [user: userInstance, dirtyPassword: dirtyPassword])
            )
        }
        catch (Exception e) {
            log.error "Error: ${e.message}", e

        }


    }


}
