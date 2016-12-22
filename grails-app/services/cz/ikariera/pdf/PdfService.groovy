package cz.ikariera.pdf

import com.itextpdf.text.BaseColor
import com.itextpdf.text.Chunk
import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Font
import com.itextpdf.text.FontFactory
import com.itextpdf.text.Image
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.BaseFont
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import cz.ikariera.student.Certificate
import cz.ikariera.student.Cv
import cz.ikariera.student.Education
import cz.ikariera.student.Experience
import cz.ikariera.student.LanguageCombination
import cz.ikariera.student.Photo
import cz.ikariera.student.StudentAccount
import grails.transaction.Transactional
import grails.web.context.ServletContextHolder


/**
 * Service to generate student cv using itextpdf dependency
 *
 * @author Michal Dolnak
 * @since 26.08.2016
 */
@Transactional
class PdfService {

    /**
     *  Method is called from controller and handles the specific application logic in generating the cv.
     *  @author Michal Dolnak
     */

    def grailsApplication


    def generateCV(StudentAccount studentAccount) throws DocumentException, IOException {

        String cvDirectory = grailsApplication.config.upload.directory.studentCv
        String studentPhotoDirectory = grailsApplication.config.upload.directory.studentPhoto

        String newFilename = UUID.randomUUID().toString()+".pdf"

        File file = new File(cvDirectory+"/"+newFilename)

        Document document = new Document()

        PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(file))

        document.open()

        generateDocument(document, studentAccount, studentPhotoDirectory)

        document.close()

        Cv cv = new Cv(
                originalFilename: "cv.pdf",
                student: studentAccount,

                newFilename: newFilename,
                name: "cv.pdf",
                fileSize: file.length()
        ).save(flush:true)
    }

    /**
    *  Method creates the content of pdf using itext. Look and feel taken from profesia.sk.
    *  @author Michal Dolnak
    */
    private def generateDocument(Document document, StudentAccount studentAccount, String studentPhotoDirectory) {
        String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')// TODO could couse problems in grails 3
        /*println(unicodeArialFont)*/
        // Parameters
        FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
        Font titleFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,20,Font.BOLD,BaseColor.BLUE)
        Font regularFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,12,Font.NORMAL)
        Font boldFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,12,Font.BOLD)
        Font blueSubtitlesFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,14,Font.NORMAL,BaseColor.BLUE)
        // Profile photo
        if (studentAccount.photo) {
            Photo photo = studentAccount.photo
            Image image = Image.getInstance(studentPhotoDirectory + "/" + photo.newFilename)
            image.setAbsolutePosition(450f, 700f)
            image.scaleAbsolute(60, 80)
            document.add(image)
        }
        // Name
        String name = studentAccount.user.firstName+" "+studentAccount.user.lastName
        document.add(new Paragraph(name,titleFont))

        document.add(new Paragraph(" "))
        // Address
        String addressDescription = "Address:"+" " // i18n first part
        String addressValue = studentAccount.addressStreet+", "+studentAccount.addressZip+" "+studentAccount.addressCity
        Phrase addressPhrase = new Phrase()
        addressPhrase.add(new Chunk(addressDescription,boldFont))
        addressPhrase.add(new Chunk(addressValue,regularFont))
        Paragraph addressParagraph = new Paragraph(addressPhrase)
        document.add(addressParagraph)
        // Telephone
        String telephoneDescription = "Tel."+" " // i18n first part
        String telephoneValue = studentAccount.telephone
        Phrase telephonePhrase = new Phrase()
        telephonePhrase.add(new Chunk(telephoneDescription,boldFont))
        telephonePhrase.add(new Chunk(telephoneValue,regularFont))
        Paragraph telephoneParagraph = new Paragraph(telephonePhrase)
        document.add(telephoneParagraph)
        // E-mail
        String emailDescription = "E-mail:"+" " // i18n first part
        String emailValue = studentAccount.user.username
        Phrase emailPhrase = new Phrase()
        emailPhrase.add(new Chunk(emailDescription,boldFont))
        emailPhrase.add(new Chunk(emailValue,regularFont))
        Paragraph emailParagraph = new Paragraph(emailPhrase)
        document.add(emailParagraph)
        // Date of birth dateOfBirth // TODO doplnit do profilu datum narodenia
       /* String dateOfBirthDescription = "Date of birth:"+" " // i18n first part
        String dateOfBirthValue = studentAccount.birthday
        Phrase dateOfBirthPhrase = new Phrase()
        dateOfBirthPhrase.add(new Chunk(dateOfBirthDescription,boldFont))
        dateOfBirthPhrase.add(new Chunk(dateOfBirthValue,regularFont))
        Paragraph dateOfBirthParagraph = new Paragraph(dateOfBirthPhrase)
        document.add(dateOfBirthParagraph)*/


        document.add(new Paragraph(" "))
        // Education
        if (studentAccount.educations.size()) {
            String educationsDescription = "EDUCATION"
            document.add(new Paragraph(educationsDescription,blueSubtitlesFont))
            LineSeparator ls = new LineSeparator()
            document.add(new Chunk(ls))
            for (Education education : studentAccount.educations) {
                String university = education.university.name // i18n ?
                String endYear = education.endingYear.toString().split("-").first()
                String specialization = education.specialization
                String educationDesciption = "x-"+endYear+", "+specialization // TODO add student education start year
                document.add(new Paragraph(university,boldFont))
                document.add(new Paragraph(educationDesciption,regularFont))

                document.add(new Paragraph(" "))
                // TODO add according to Profesia cv
                /*String startYear = education.
            */}

        }
        // Courses and Training
        if(studentAccount.certificates.size()){
            String certificatesDescription = "COURSES AND TRAINING"
            document.add(new Paragraph(certificatesDescription,blueSubtitlesFont))
            LineSeparator ls = new LineSeparator()
            document.add(new Chunk(ls))
            for(Certificate certificate : studentAccount.certificates) {
                String certificateName  = certificate.name
                String level = " - "+certificate.level
                Phrase certificatePhrase = new Phrase()
                certificatePhrase.add(new Chunk(certificateName,boldFont))
                certificatePhrase.add(new Chunk(level,regularFont))
                Paragraph paragraph = new Paragraph(certificatePhrase)
                document.add(paragraph)
                document.add(new Paragraph(certificate.description))

                document.add(new Paragraph(" "))
            }
        }

        // Employment history
        if(studentAccount.experiences.size()){
            String experiencesDescription = "EMPLOYMENT HISTORY"
            document.add(new Paragraph(experiencesDescription,blueSubtitlesFont))
            LineSeparator ls = new LineSeparator()
            document.add(new Chunk(ls))
            for(Experience experience : studentAccount.experiences) {
                String experienceName  = experience.employer
                document.add(new Paragraph(experienceName,boldFont))
                String experienceDate = experience.periodStart+" - "+experience.periodEnd
                String experienceDetail = experienceDate+", "+experience.occupation
                document.add(new Paragraph(experienceDetail))
                document.add(new Paragraph(experience.activities))

                document.add(new Paragraph(" "))
            }
        }

        // Language skills
        if(studentAccount.languages.size()){
            String languagesDescription = "COURSES AND TRAINING"
            document.add(new Paragraph(languagesDescription,blueSubtitlesFont))
            LineSeparator ls = new LineSeparator()
            document.add(new Chunk(ls))
            for(LanguageCombination languageCombination : studentAccount.languages) {
                String languageName  = languageCombination.languageType.name
                String languageLevel = languageCombination.level.name
                document.add(new Paragraph(languageName+"-"+languageLevel,boldFont))

                document.add(new Paragraph(" "))

            }
        }

    }
}
