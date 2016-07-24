package cz.ikariera.upload

import org.springframework.web.multipart.MultipartFile

class UploadFileService {

    boolean transactional = true

    //if directory is not existing, method creates one
    static def checkDirectory(def directoryPath) {

        File origFile = new File(directoryPath)
        if (!origFile.exists()) {
            new File(directoryPath).mkdirs()
        }
    }
    /*
    use:        
        String storagePath = new UploadFileService().uploadFile(file, "name_of_file", "name_of_dir")
    */

    def String uploadFile(MultipartFile file, String name, String destinationDirectory) {
        def storagePath = destinationDirectory

        def storagePathDirectory = new File(storagePath)
        if (!storagePathDirectory.exists()) {
            storagePathDirectory.mkdirs()
        }

        if (!file.isEmpty()) {
            file.transferTo(new File("${storagePath}/${name}"))
            return "${storagePath}/${name}"
        } else {
            return null
        }
    }
}
