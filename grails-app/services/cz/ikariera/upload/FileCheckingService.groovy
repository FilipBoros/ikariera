package cz.ikariera.upload

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

public class FileCheckingService {

    static String getFileExtension(fileName) {
        int i = fileName.lastIndexOf('.')
        if (i > 0) {
            return fileName.substring(i + 1).toLowerCase()
        }
        return ""
    }

    static def checkFileExtension(file, extensions) {
        def fileName = file.originalFilename.toString()
        def extension = getFileExtension(fileName)

        if (extensions.contains(extension)) {
            return [result: true, extension: extension]
        } else {
            return [result: false]
        }
    }

    static def checkImageDimensions(imageFile, requiredWidth, requiredHeight) {

        BufferedImage image = ImageIO.read(imageFile.inputStream)
        def imageHeight = image.getHeight()
        def imageWidth = image.getWidth()

        if (imageWidth == requiredWidth && imageHeight == requiredHeight) {
            return [result: true, imageWidth: imageWidth, imageHeight: imageHeight]
        } else {
            return [result: false]
        }
    }

}
