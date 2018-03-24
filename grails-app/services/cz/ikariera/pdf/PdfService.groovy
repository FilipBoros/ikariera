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
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import cz.ikariera.student.Certificate
import cz.ikariera.student.Cv
import cz.ikariera.student.Education
import cz.ikariera.student.Experience
import cz.ikariera.student.LanguageCombination
import cz.ikariera.student.Photo
import cz.ikariera.student.SkillCombination
import cz.ikariera.student.StudentAccount
import grails.transaction.Transactional
import grails.web.context.ServletContextHolder
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfPCell
import org.h2.table.Table

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
     *  updated by Filip Boros
     */

    def grailsApplication

    /*
     * Second parameter changes CV template
     */
    def generateCV(StudentAccount studentAccount, int CVnumber, String locale) throws DocumentException, IOException {

        String cvDirectory = grailsApplication.config.upload.directory.studentCv
        String studentPhotoDirectory = grailsApplication.config.upload.directory.studentPhoto

        String newFilename = UUID.randomUUID().toString()+".pdf"

        File file = new File(cvDirectory+"/"+newFilename)

        Document document = new Document()

        PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(file))

        document.open()

        switch (CVnumber) {
            case 1:  generateDocument1(document, studentAccount, studentPhotoDirectory, locale)
                break
            case 2:  generateDocument2(document, studentAccount, studentPhotoDirectory, locale)
                break
            case 3:  generateDocument3(document, studentAccount, studentPhotoDirectory, locale)
                break
            default: generateDocument1(document, studentAccount, studentPhotoDirectory, locale)
                break
        }

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
    *  updated by Filip Boros
    */
    private def generateDocument1(Document document, StudentAccount studentAccount, String studentPhotoDirectory, String locale) {
        //Text mutations
        String addressDescription = ""
        String telephoneDescription = ""
        String emailDescription = ""
        String dateOfBirthDescription = ""
        String educationsDescription = ""
        String experiencesDescription = ""
        String noEducation = ""
        String noEmployment = ""
        String languagesDescription = ""
        String noLanguages = ""
        String certificatesDescription = ""
        String profileDescription = ""
        String skillsDescription = ""
        String noSkills = ""
        switch (locale) {
            case "cs_CZ":
                addressDescription = "Adresa: "
                telephoneDescription = "Tel.: "
                emailDescription = "E-mail: "
                dateOfBirthDescription = "Datum narození: "
                educationsDescription = "Vzdělání"
                noEducation = "Nedefinované žádné vzdelání"
                experiencesDescription = "Průběh zaměstnání"
                noEmployment = "Nedefinované žádné zaměstnání "
                languagesDescription = "Jazykové znalosti"
                noLanguages = "Nedefinované žádné jazyky"
                certificatesDescription = "Kurzy a školení"
                profileDescription = "Profil"
                skillsDescription = "PC zručnosti"
                noSkills = "Nedefinované žádné zručnosti"
                break
            case "en_US":
                addressDescription = "Address: "
                telephoneDescription = "Tel.: "
                emailDescription = "E-mail: "
                dateOfBirthDescription = "Date of birth: "
                educationsDescription = "Education"
                noEducation = "No education defined"
                experiencesDescription = "Employment history"
                noEmployment = "No employment history defined"
                languagesDescription = "Language knowledge"
                noLanguages = "No language knowledge defined"
                certificatesDescription = "Courses and training"
                profileDescription = "Profile"
                skillsDescription = "Computer skills"
                noSkills = "No computer skills defined"
                break
            case "sk":
                addressDescription = "Adresa: "
                telephoneDescription = "Tel.: "
                emailDescription = "E-mail: "
                dateOfBirthDescription = "Dátum narodenia: "
                educationsDescription = "Vzdelanie"
                noEducation = "Nedefinované žiadné vzdelanie"
                experiencesDescription = "Priebeh zamestnaní"
                noEmployment = "Nedefinované žiadné zamestnanie"
                languagesDescription = "Jazykové znalosti"
                noLanguages = "Nedefinované žiadné jazyky"
                certificatesDescription = "Kurzy a školenia"
                profileDescription = "Profil"
                skillsDescription = "PC zručnosti"
                noSkills = "Nedefinované žiadné zručnosti"
                break
            default:
                addressDescription = "Address: "
                telephoneDescription = "Tel.: "
                emailDescription = "E-mail: "
                dateOfBirthDescription = "Date of birth: "
                educationsDescription = "Education"
                noEducation = "No education defined"
                experiencesDescription = "Employment history"
                noEmployment = "No employment history defined"
                languagesDescription = "Language knowledge"
                noLanguages = "No language knowledge defined"
                certificatesDescription = "Courses and training"
                profileDescription = "Profile"
                skillsDescription = "Computer skills"
                noSkills = "No computer skills defined"
                break
        }

        String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')// TODO could couse problems in grails 3
        //Fonts
        FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
        Font titleFont = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 20 , Font.BOLD, BaseColor.BLUE)
        Font regularFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.NORMAL)
        Font boldFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.BOLD)
        Font italicFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.ITALIC)

        //Column widths for tables
        int[] languagesWidths = [6, 24]

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
        String addressValue = ""
        if (studentAccount.addressStreet && studentAccount.addressZip && studentAccount.addressCity)
        {
            addressValue = studentAccount.addressStreet+", "+studentAccount.addressZip+" "+studentAccount.addressCity
        }
        Phrase addressPhrase = new Phrase()
        addressPhrase.add(new Chunk(addressDescription,boldFont))
        addressPhrase.add(new Chunk(addressValue,regularFont))
        Paragraph addressParagraph = new Paragraph(addressPhrase)
        document.add(addressParagraph)

        // Telephone
        String telephoneValue = ""
        if (studentAccount.telephone) {
           telephoneValue = studentAccount.telephone
        }
        Phrase telephonePhrase = new Phrase()
        telephonePhrase.add(new Chunk(telephoneDescription,boldFont))
        telephonePhrase.add(new Chunk(telephoneValue,regularFont))
        Paragraph telephoneParagraph = new Paragraph(telephonePhrase)
        document.add(telephoneParagraph)

        //E-mail
        String emailValue = studentAccount.user.username
        Phrase emailPhrase = new Phrase()
        emailPhrase.add(new Chunk(emailDescription,boldFont))
        emailPhrase.add(new Chunk(emailValue,regularFont))
        Paragraph emailParagraph = new Paragraph(emailPhrase)
        document.add(emailParagraph)

        //Date of births
        String dateOfBirthValue = ""
        if (studentAccount.birthday) {
            dateOfBirthValue = studentAccount.birthday[0..9]
        }
        Phrase dateOfBirthPhrase = new Phrase()
        dateOfBirthPhrase.add(new Chunk(dateOfBirthDescription,boldFont))
        dateOfBirthPhrase.add(new Chunk(dateOfBirthValue,regularFont))
        Paragraph dateOfBirthParagraph = new Paragraph(dateOfBirthPhrase)
        document.add(dateOfBirthParagraph)
        document.add(new Paragraph(" "))

        //Profile - short info
        if (studentAccount.personalCharacteristic) {
            addSubtitleCV1(document, profileDescription)
            Paragraph profileShortInfo = new Paragraph(studentAccount.personalCharacteristic, regularFont)
            document.add(profileShortInfo)
        }
        document.add(new Paragraph(" "))

        // Education
        addSubtitleCV1(document, educationsDescription)
        if (studentAccount.educations.size()) {
            for (Education education : studentAccount.educations) {
                String university = education.university.name // i18n ?
                String startYear = education.startYear.toString().split("-").first()
                String endYear = education.endingYear.toString().split("-").first()
                String specialization = " "
                if (education.specialization) {
                    specialization = ", "+ education.specialization
                }
                String educationDesciption = startYear + "-"+endYear+specialization // TODO add student education start year
                document.add(new Paragraph(university,boldFont))
                document.add(new Paragraph(educationDesciption,regularFont))
                document.add(new Paragraph(" "))
            }
        } else {
            document.add(new Paragraph(noEducation, italicFont))
            document.add(new Paragraph(" "))
        }

        // Employment history
        addSubtitleCV1(document, experiencesDescription)
        if(studentAccount.experiences.size()){
            for(Experience experience : studentAccount.experiences) {
                String experienceName  = experience.employer
                document.add(new Paragraph(experienceName,boldFont))
                String experienceDate = experience.periodStart+" - "+experience.periodEnd
                String experienceDetail = experienceDate+", "+experience.occupation
                document.add(new Paragraph(experienceDetail))
                document.add(new Paragraph("• " + experience.activities))

                document.add(new Paragraph(" "))
            }
        } else {
            document.add(new Paragraph(noEmployment, italicFont))
            document.add(new Paragraph(" "))
        }

        // Language skill
        addSubtitleCV1(document, languagesDescription)
        if(studentAccount.languages.size()){
            for(LanguageCombination languageCombination : studentAccount.languages) {
                Phrase languagePhrase = new Phrase()
                switch (locale) {
                    case "cs_CZ":
                        languagePhrase.add(new Chunk(languageCombination.languageType.nameCZ,boldFont))
                        languagePhrase.add(new Chunk("     "+languageCombination.level.nameCZ,regularFont))
                        break
                    case "en_US":
                        languagePhrase.add(new Chunk(languageCombination.languageType.nameEN,boldFont))
                        languagePhrase.add(new Chunk("     "+languageCombination.level.nameEN,regularFont))
                        break
                    case "sk":
                        languagePhrase.add(new Chunk(languageCombination.languageType.nameSK,boldFont))
                        languagePhrase.add(new Chunk("     "+languageCombination.level.nameSK,regularFont))
                        break
                    default:
                        languagePhrase.add(new Chunk(languageCombination.languageType.nameEN,boldFont))
                        languagePhrase.add(new Chunk("     "+languageCombination.level.nameEN,regularFont))
                        break
                }
                Paragraph languageParagraph = new Paragraph(languagePhrase)
                document.add(languageParagraph)
            }
            document.add(new Paragraph(" "))
        } else {
            document.add(new Paragraph(noLanguages, italicFont))
            document.add(new Paragraph(" "))
        }

        //Pc skills
        addSubtitleCV1(document, skillsDescription)
        if(studentAccount.skills.size()){
            for(SkillCombination skillCombination : studentAccount.skills) {
                Phrase skillPhrase = new Phrase()
                switch (locale) {
                    case "cs_CZ":
                        skillPhrase.add(new Chunk(skillCombination.skillType.name, boldFont))
                        skillPhrase.add(new Chunk("   -   "+skillCombination.level.nameCZ.substring(3), regularFont))
                        break
                    case "en_US":
                        skillPhrase.add(new Chunk(skillCombination.skillType.name, boldFont))
                        skillPhrase.add(new Chunk("   -   "+skillCombination.level.nameEN.substring(3), regularFont))
                        break
                    case "sk":
                        skillPhrase.add(new Chunk(skillCombination.skillType.name, boldFont))
                        skillPhrase.add(new Chunk("   -   "+skillCombination.level.nameSK.substring(3), regularFont))
                        break
                    default:
                        skillPhrase.add(new Chunk(skillCombination.skillType.name, boldFont))
                        skillPhrase.add(new Chunk("   -   "+skillCombination.level.nameEN.substring(3), regularFont))
                        break
                }
                Paragraph skillParagraph = new Paragraph(skillPhrase)
                document.add(skillParagraph)
            }
            document.add(new Paragraph(" "))
        } else {
            document.add(new Paragraph(noSkills, italicFont))
            document.add(new Paragraph(" "))
        }

        // Courses and Training
        if(studentAccount.certificates.size()){
            addSubtitleCV1(document, certificatesDescription)
            for(Certificate certificate : studentAccount.certificates) {
                String certificateName  = certificate.name
                String level = " "
                if (certificate.level) {
                    level = " - "+certificate.level
                }
                String description = " "
                if (certificate.description) {
                   description =  "• " + certificate.description
                }
                Phrase certificatePhrase = new Phrase()
                certificatePhrase.add(new Chunk(certificateName,boldFont))
                certificatePhrase.add(new Chunk(level,regularFont))
                Paragraph paragraph = new Paragraph(certificatePhrase)
                document.add(paragraph)
                document.add(new Paragraph(description))
                document.add(new Paragraph(" "))
            }
        }

    }

    /*
    * Added by Filip Boroš
    * Second CV template, inspired by profesia.sk
    */
    private def generateDocument2(Document document, StudentAccount studentAccount, String studentPhotoDirectory, String locale) {
        //Text mutations
        String subTitle = ""
        String personalInfoDescription = ""
        String addressDescription = ""
        String emailDescription = ""
        String dateOfBirthDescription = ""
        String noEducation = ""
        String educationsDescription = ""
        String experienceDescription = ""
        String noEmployment = ""
        String languagesDescription = ""
        String noLanguages = ""
        String skillsDescription = ""
        String noSkills = ""
        String certificatesDescription = ""
        switch (locale) {
            case "cs_CZ":
                subTitle = "Student"
                personalInfoDescription = "OSOBNÍ INFO"
                addressDescription = "ADRESA: "
                emailDescription = "KONTAKT: "
                dateOfBirthDescription = "DATUM NAROZENÍ: "
                educationsDescription = "VZDĚLÁNÍ"
                noEducation = "Nedefinované žádné vzdelání"
                experienceDescription = "PRŮBĚH ZAMĚSTNÁNÍ"
                noEmployment = "Nedefinované žádné zaměstnání "
                languagesDescription = "JAZYKOVÉ ZNALOSTI"
                noLanguages = "Nedefinované žádné jazyky"
                skillsDescription = "PC ZRUČNOSTI"
                noSkills = "Nedefinované žádné zručnosti"
                certificatesDescription = "KURZY A ŠKOLENÍ"
                break
            case "en_US":
                subTitle = "Student"
                personalInfoDescription = "PERSONAL INFO"
                addressDescription = "ADDRESS: "
                emailDescription = "CONTACT: "
                dateOfBirthDescription = "DATE OF BIRTH: "
                educationsDescription = "EDUCATION"
                noEducation = "No education defined"
                experienceDescription = "EMPLOYMENT HISTORY"
                noEmployment = "No employment history defined"
                languagesDescription = "LANGUAGE KNOWLEDGE"
                noLanguages = "No language knowledge defined"
                certificatesDescription = "COURSES AND TRAINING"
                skillsDescription = "COMPUTER SKILLS"
                noSkills = "No computer skills defined"
                break
            case "sk":
                subTitle = "Študent"
                personalInfoDescription = "OSOBNÉ ÚDAJE"
                addressDescription = "ADRESA: "
                emailDescription = "KONTAKT: "
                dateOfBirthDescription = "DÁTUM NARODENIA: "
                educationsDescription = "VZDELANIE"
                noEducation = "Nedefinované žiadné vzdelanie"
                experienceDescription = "PRIEBEH ZAMESTNANÍ"
                noEmployment = "Nedefinované žiadné zamestnanie"
                languagesDescription = "JAZYKOVÉ ZNALOSTI"
                noLanguages = "Nedefinované žiadné jazyky"
                certificatesDescription = "KURZY A ŠKOLENIA"
                skillsDescription = "PC ZRUČNOSTI"
                noSkills = "Nedefinované žiadné zručnosti"
                break
            default:
                subTitle = "Student"
                personalInfoDescription = "PERSONAL INFO"
                addressDescription = "ADDRESS: "
                emailDescription = "CONTACT: "
                dateOfBirthDescription = "DATE OF BIRTH: "
                educationsDescription = "EDUCATION"
                noEducation = "No education defined"
                experienceDescription = "EMPLOYMENT HISTORY"
                noEmployment = "No employment history defined"
                languagesDescription = "LANGUAGE KNOWLEDGE"
                noLanguages = "No language knowledge defined"
                certificatesDescription = "COURSES AND TRAINING"
                skillsDescription = "COMPUTER SKILLS"
                noSkills = "No computer skills defined"
                break
        }

        String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')

        //Fonts
        FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
        Font titleFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,20,Font.BOLD,BaseColor.BLACK)
        Font regularFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.NORMAL)
        Font importantFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.NORMAL)
        Font boldFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.BOLD)
        Font italicFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.ITALIC)
        Font italicFontSubtitle = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.ITALIC, new BaseColor(173, 173, 173))
        Font subtitlesFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,13,Font.UNDERLINE|Font.BOLD,BaseColor.BLACK)

        //Widths for table columns
        int[] certificatesWidths = [5, 7, 16]
        int[] widthsPersonalInfo = [3, 4]
        int[] widthsEducation = [3, 2, 2]
        int[] widthsTitle = [1, 3, 3]

        //Profile photo
        PdfPTable table = new PdfPTable(3)
        table.setWidthPercentage(100)
        table.setWidths(widthsTitle)
        if (studentAccount.photo) {
            Photo photo = studentAccount.photo
            Image image = Image.getInstance(studentPhotoDirectory + "/" + photo.newFilename)
            image.setAbsolutePosition(50f, 700f)
            image.scaleAbsolute(65, 80)
            image.setBorder(15)
            image.setBorderColor(BaseColor.LIGHT_GRAY)
            image.setBorderWidth(4f)
            PdfPCell cell = new PdfPCell(image, false)
            cell.setFixedHeight(80f)
            cell.setRowspan(2)
            cell.setBorder(0)
            table.addCell(cell)
        } else {
            Image image = Image.getInstance("grails-app/assets/images/ikariera_sk/no-profile-image2.png")
            image.setAbsolutePosition(50f, 700f)
            image.scaleAbsolute(65, 80)
            image.setBorder(15)
            image.setBorderColor(BaseColor.LIGHT_GRAY)
            image.setBorderWidth(4f)
            PdfPCell cell = new PdfPCell(image, false)
            cell.setFixedHeight(80f)
            cell.setRowspan(2)
            cell.setBorder(0)
            table.addCell(cell)
        }

        //Name
        document.add(new Paragraph(" "))
        String name = studentAccount.user.firstName+" "+studentAccount.user.lastName
        Paragraph title = new Paragraph(name,titleFont)
        title.setAlignment(Paragraph.ALIGN_CENTER)
        PdfPCell cell = new PdfPCell()
        cell.addElement(title)
        cell.setColspan(2)
        cell.setFixedHeight(40f)
        cell.setBorder(2)
        cell.setBorderColor(BaseColor.LIGHT_GRAY)
        table.addCell(cell)
        if (studentAccount.personalCharacteristic) {
            subTitle = studentAccount.personalCharacteristic
        }
        Paragraph parSubTitle = new Paragraph(subTitle,italicFontSubtitle)
        parSubTitle.setAlignment(Paragraph.ALIGN_CENTER)
        cell = new PdfPCell()
        cell.addElement(parSubTitle)
        cell.setColspan(2)
        cell.setBorder(0)
        table.addCell(cell)
        document.add(table)
        document.add(new Paragraph(" "))

        //Personal info
        Paragraph parPersonalInfoDescription = new Paragraph(personalInfoDescription,subtitlesFont)
        parPersonalInfoDescription.setLeading(15,0)
        parPersonalInfoDescription.setSpacingAfter(5)
        document.add(parPersonalInfoDescription)
        PdfPTable tableAddress = new PdfPTable(2)
        tableAddress.setWidthPercentage(100)
        tableAddress.setWidths(widthsPersonalInfo)

        // Address
        String addressValue = " "
        if (studentAccount.addressStreet && studentAccount.addressZip && studentAccount.addressCity)
        {
            addressValue = studentAccount.addressStreet+", "+studentAccount.addressZip+" "+studentAccount.addressCity
        }
        Paragraph parAddressDescription = new Paragraph(addressDescription,importantFont)
        Paragraph parAddressValue = new Paragraph(addressValue,regularFont)
        PdfPCell cellAddressDescription = new PdfPCell()
        cellAddressDescription.addElement(parAddressDescription)
        PdfPCell cellAddressValue = new PdfPCell()
        cellAddressValue.addElement(parAddressValue)
        cellAddressDescription.setBorder(0)
        cellAddressValue.setBorder(0)
        tableAddress.addCell(cellAddressDescription)
        tableAddress.addCell(cellAddressValue)
        document.add(tableAddress)

        // E-mail
        PdfPTable tableEmail = new PdfPTable(2)
        tableEmail.setWidthPercentage(100)
        tableEmail.setWidths(widthsPersonalInfo)
        String emailValue = studentAccount.user.username
        Paragraph parEmailDescription = new Paragraph(emailDescription,importantFont)
        Paragraph parEmailValue = new Paragraph(emailValue,regularFont)
        PdfPCell cellEmailDescription = new PdfPCell()
        cellEmailDescription.addElement(parEmailDescription)
        PdfPCell cellEmailValue = new PdfPCell()
        cellEmailValue.addElement(parEmailValue)
        cellEmailDescription.setBorder(0)
        cellEmailValue.setBorder(0)
        tableEmail.addCell(cellEmailDescription)
        tableEmail.addCell(cellEmailValue)
        document.add(tableEmail)

        // Telephone
        PdfPTable tableContact = new PdfPTable(2)
        tableContact.setWidthPercentage(100)
        tableContact.setWidths(widthsPersonalInfo)
        String contactDescription = ""
        String contactValue = " "
        if (studentAccount.telephone) {
            contactValue = studentAccount.telephone
        }
        Paragraph parContactDescription = new Paragraph(contactDescription,importantFont)
        Paragraph parContactValue = new Paragraph(contactValue,regularFont)
        PdfPCell cellContactDescription = new PdfPCell()
        cellContactDescription.addElement(parContactDescription)
        PdfPCell cellContactValue = new PdfPCell()
        cellContactValue.addElement(parContactValue)
        cellContactDescription.setBorder(0)
        cellContactValue.setBorder(0)
        tableContact.addCell(cellContactDescription)
        tableContact.addCell(cellContactValue)
        document.add(tableContact)

        //Date of birth
        PdfPTable tableBirth = new PdfPTable(2)
        tableBirth.setWidthPercentage(100)
        tableBirth.setWidths(widthsPersonalInfo)
        String dateOfBirthValue = " "
        if (studentAccount.birthday) {
            dateOfBirthValue = studentAccount.birthday[0..9]
        }
        Paragraph parDateOfBirthDescription = new Paragraph(dateOfBirthDescription,importantFont)
        Paragraph parDateOfBirthValue = new Paragraph(dateOfBirthValue,regularFont)
        PdfPCell cellDateOfBirthDescription = new PdfPCell()
        cellDateOfBirthDescription.addElement(parDateOfBirthDescription)
        PdfPCell cellDateOfBirthValue = new PdfPCell()
        cellDateOfBirthValue.addElement(parDateOfBirthValue)
        cellDateOfBirthDescription.setBorder(0)
        cellDateOfBirthValue.setBorder(0)
        tableBirth.addCell(cellDateOfBirthDescription)
        tableBirth.addCell(cellDateOfBirthValue)
        document.add(tableBirth)
        document.add(new Paragraph(" "))

        // Education
        Paragraph parEducationsDescription = new Paragraph(educationsDescription, subtitlesFont)
        parEducationsDescription.setSpacingAfter(10)
        document.add(parEducationsDescription)
        if (studentAccount.educations.size()) {
            for (Education education : studentAccount.educations) {
                PdfPTable tableEducation = new PdfPTable(3)
                tableEducation.setWidthPercentage(100)
                tableEducation.setWidths(widthsEducation)
                String university = education.university.name
                String startYear = education.startYear.toString().split("-").first()
                String endYear = education.endingYear.toString().split("-").first()
                String specialization = " "
                if (education.specialization) {
                    specialization = education.specialization
                }
                Paragraph parEducationYears = new Paragraph(startYear + "-" + endYear,regularFont)
                Paragraph parEducationUniversity = new Paragraph(university,boldFont)
                Paragraph parEducationSpecialization = new Paragraph(specialization,regularFont)
                PdfPCell cellEducationYears = new PdfPCell()
                cellEducationYears.addElement(parEducationYears)
                cellEducationYears.setRowspan(2)
                PdfPCell cellEducationUniversity = new PdfPCell()
                cellEducationUniversity.addElement(parEducationUniversity)
                PdfPCell cellEducationSpecialization = new PdfPCell()
                cellEducationSpecialization.addElement(parEducationSpecialization)
                cellEducationYears.setBorder(0)
                cellEducationUniversity.setBorder(0)
                cellEducationSpecialization.setBorder(0)
                cellEducationUniversity.setColspan(2)
                cellEducationSpecialization.setColspan(2)
                tableEducation.addCell(cellEducationYears)
                tableEducation.addCell(cellEducationUniversity)
                tableEducation.addCell(cellEducationSpecialization)
                document.add(tableEducation)
            }
        } else {
            Paragraph parNoEducation = new Paragraph(noEducation, italicFont)
            parNoEducation.setLeading(30)
            parNoEducation.setAlignment(Paragraph.ALIGN_CENTER)
            document.add(parNoEducation)
        }
        document.add(new Paragraph(" "))

        // Employment history
        Paragraph parExperienceDescription = new Paragraph(experienceDescription, subtitlesFont)
        parExperienceDescription.setSpacingAfter(10)
        document.add(parExperienceDescription)
        if(studentAccount.experiences.size()){
            for(Experience experience : studentAccount.experiences) {
                Paragraph experienceDate = new Paragraph(experience.periodStart+" - "+experience.periodEnd, regularFont)
                document.add(experienceDate)
                Phrase experiencePhrase = new Phrase()
                String experienceEmployer = " "
                if (experience.employer) {
                    experienceEmployer += experience.employer
                }
                experiencePhrase.add(new Chunk(experienceEmployer,boldFont))
                String experienceOccupation = " "
                if (experience.occupation) {
                    experienceOccupation += " / "+experience.occupation
                }
                experiencePhrase.add(new Chunk(experienceOccupation,regularFont))
                Paragraph parExperienceName = new Paragraph(experiencePhrase)
                document.add(parExperienceName)
                document.add(new Paragraph("• " + experience.activities))
                document.add(new Paragraph(" "))
            }
        } else {
            Paragraph parNoEmployment = new Paragraph(noEmployment, italicFont)
            parNoEmployment.setLeading(30)
            parNoEmployment.setAlignment(Paragraph.ALIGN_CENTER)
            document.add(parNoEmployment)
            document.add(new Paragraph(" "))
        }


        // Language skill
        Paragraph parLanguagesDescription = new Paragraph(languagesDescription, subtitlesFont)
        parLanguagesDescription.setSpacingAfter(10)
        document.add(parLanguagesDescription)
        if(studentAccount.languages.size()){
            for(LanguageCombination languageCombination : studentAccount.languages) {
                PdfPTable tableLanguage = new PdfPTable(2)
                tableLanguage.setWidthPercentage(100)
                tableLanguage.setWidths(widthsPersonalInfo)
                String languageName = ""
                String languageLevel = ""
                switch (locale) {
                    case "cs_CZ":
                        languageName = languageCombination.languageType.nameCZ
                        languageLevel = languageCombination.level.nameCZ
                        break
                    case "en_US":
                        languageName = languageCombination.languageType.nameEN
                        languageLevel = languageCombination.level.nameEN
                        break
                    case "sk":
                        languageName = languageCombination.languageType.nameSK
                        languageLevel = languageCombination.level.nameSK
                        break
                    default:
                        languageName = languageCombination.languageType.nameEN
                        languageLevel = languageCombination.level.nameEN
                        break
                }
                Paragraph parLanguageName = new Paragraph(languageName,boldFont)
                Paragraph parLanguageLevel = new Paragraph(languageLevel,regularFont)
                PdfPCell cellLanguageName = new PdfPCell()
                cellLanguageName.addElement(parLanguageName)
                PdfPCell cellLanguageLevel = new PdfPCell()
                cellLanguageLevel.addElement(parLanguageLevel)
                cellLanguageName.setBorder(0)
                cellLanguageLevel.setBorder(0)
                tableLanguage.addCell(cellLanguageName)
                tableLanguage.addCell(cellLanguageLevel)
                document.add(tableLanguage)
            }
        } else {
            Paragraph parNoLanguages = new Paragraph(noLanguages, italicFont)
            parNoLanguages.setLeading(30)
            parNoLanguages.setAlignment(Paragraph.ALIGN_CENTER)
            document.add(parNoLanguages)
        }
        document.add(new Paragraph(" "))

        //PC skills
        PdfPTable tableSkill = new PdfPTable(4)
        tableSkill.setWidthPercentage(100)
        Paragraph parSkillsDescription = new Paragraph(skillsDescription, subtitlesFont)
        parSkillsDescription.setSpacingAfter(10)
        document.add(parSkillsDescription)
        if(studentAccount.skills.size()){
            for(SkillCombination skillCombination : studentAccount.skills) {
                String skillName = skillCombination.skillType.name + ":"
                Paragraph parSkillName = new Paragraph(skillName,boldFont)
                Paragraph parSkillLevel = dottedParagraph(skillCombination.level.posOrder)
                PdfPCell cellSkillName = new PdfPCell()
                cellSkillName.addElement(parSkillName)
                PdfPCell cellSkillLevel = new PdfPCell()
                cellSkillLevel.addElement(parSkillLevel)
                cellSkillName.setBorder(0)
                cellSkillLevel.setBorder(0)
                cellSkillLevel.setPaddingTop(8)
                tableSkill.addCell(cellSkillName)
                tableSkill.addCell(cellSkillLevel)
            }
        } else {
            Paragraph parNoSkills = new Paragraph(noSkills, italicFont)
            parNoSkills.setLeading(30)
            parNoSkills.setAlignment(Paragraph.ALIGN_CENTER)
            document.add(parNoSkills)
        }
        if (studentAccount.skills.size() % 2 == 1) {
            PdfPCell fill = new PdfPCell()
            fill.setBorder(0)
            tableSkill.addCell(fill)
            tableSkill.addCell(fill)
        }
        document.add(tableSkill)
        document.add(new Paragraph(" "))

        // Courses and Training
        if(studentAccount.certificates.size()){
            Paragraph parCertificatesDescription = new Paragraph(certificatesDescription, subtitlesFont)
            parCertificatesDescription.setSpacingAfter(11)
            document.add(parCertificatesDescription)
            for(Certificate certificate : studentAccount.certificates) {
                PdfPTable tableCertificates = new PdfPTable(3)
                tableCertificates.setWidthPercentage(100)
                tableCertificates.setWidths(certificatesWidths)
                String certificateName = certificate.name
                String level = " "
                if (certificate.level) {
                    level = certificate.level
                }
                String certificateDescription = " "
                if (certificate.description) {
                    certificateDescription = certificate.description
                }
                cell = new PdfPCell(new Paragraph(certificateName, boldFont))
                cell.setBorder(0)
                tableCertificates.addCell(cell)
                cell = new PdfPCell(new Paragraph(level, boldFont))
                cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER)
                cell.setBorder(0)
                tableCertificates.addCell(cell)
                cell = new PdfPCell(new Paragraph(certificateDescription, regularFont))
                cell.setBorder(0)
                tableCertificates.addCell(cell)
                document.add(tableCertificates)
                document.add(new Paragraph(" "))
            }
        }
    }

     /*
      * Added by Filip Boroš
      * Third CV template, inspired by kickresume.com
      */
     private def generateDocument3(Document document, StudentAccount studentAccount, String studentPhotoDirectory, String locale) {
         //Text mutations
         String personalInfoDescription = ""
         String firstName = ""
         String lastName = ""
         String dateOfBirth = ""
         String address = ""
         String phone = ""
         String email = ""
         String profile = ""
         String workExperience = ""
         String noWorkExperience = ""
         String educations = ""
         String noEducation = ""
         String skills = ""
         String skillsSoftware = ""
         String skillsLanguages = ""
         String certificates = ""
         switch (locale) {
             case "cs_CZ":
                 personalInfoDescription = "Osobní info"
                 firstName = "Jméno:"
                 lastName = "Příjmení:"
                 dateOfBirth = "Datum narození:"
                 address = "Adresa:"
                 phone = "Telefonní číslo:"
                 email = "Email:"
                 profile = "Profil"
                 workExperience = "Pracovní zkušenosti"
                 noWorkExperience = "Nedefinována žádna pracovní zkušenost"
                 educations = "Vzdělání"
                 noEducation = "Nedefinované žádné vzdelání"
                 skills = "Zručnosti"
                 skillsSoftware = "Software"
                 skillsLanguages = "Jazyky"
                 certificates = "Certifikáty a kurzy"
                 break
             case "en_US":
                 personalInfoDescription = "Personal info"
                 firstName = "First name:"
                 lastName = "Last name:"
                 dateOfBirth = "Date of birth:"
                 address = "Address:"
                 phone = "Phone number:"
                 email = "Email address:"
                 profile = "Profile"
                 workExperience = "Work experience"
                 noWorkExperience = "No work experience defined"
                 educations = "Education"
                 noEducation = "No education defined"
                 skills = "Skills"
                 skillsSoftware = "Software"
                 skillsLanguages = "Languages"
                 certificates = "Certificates and courses"
                 break
             case "sk":
                 personalInfoDescription = "Osobné údaje"
                 firstName = "Meno:"
                 lastName = "Priezvisko:"
                 dateOfBirth = "Dátum narodenia:"
                 address = "Adresa:"
                 phone = "Telefónne číslo:"
                 email = "Email:"
                 profile = "Profil"
                 workExperience = "Pracovné skúsenosti"
                 noWorkExperience = "Nedefinovaná žiadna pracovná skúsenost"
                 educations = "Vzdelanie"
                 noEducation = "Nedefinované žiadné vzdelanie"
                 skills = "Zručnosti"
                 skillsSoftware = "Software"
                 skillsLanguages = "Jazyky"
                 certificates = "Certifikáty a kurzy"
                 break
             default:
                 personalInfoDescription = "Personal info"
                 firstName = "First name:"
                 lastName = "Last name:"
                 dateOfBirth = "Date of birth:"
                 address = "Address:"
                 phone = "Phone number:"
                 email = "Email address:"
                 profile = "Profil"
                 workExperience = "Work experience"
                 noWorkExperience = "No work experience defined"
                 educations = "Education"
                 noEducation = "No education defined"
                 skills = "Skills"
                 skillsSoftware = "Software"
                 skillsLanguages = "Languages"
                 certificates = "Certificates and courses"
                 break
         }

         String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')
         //Fonts
         FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
         Font titleFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,29,Font.BOLD,new BaseColor(86, 86, 86))
         Font regularFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.NORMAL)
         Font boldFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.BOLD)
         Font italicFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.ITALIC)
         //Widths for table columns
         int[] certificatesWidths = [5, 3, 21]
         int[] widthsEducation = [10, 27, 14]
         int[] widthsOthers = [10, 1, 26, 14]
         int[] widthsTitle = [8, 26, 7, 1]

         //Name
         String name = studentAccount.user.firstName + " " + studentAccount.user.lastName
         Paragraph title = new Paragraph(name,titleFont)
         title.setAlignment(Paragraph.ALIGN_CENTER)
         document.add(title)
         document.add(new Paragraph(" "))

         //Personal info
         addSubtitle(document, personalInfoDescription)

         PdfPTable tablePersonalInfo = new PdfPTable(4)
         tablePersonalInfo.setWidthPercentage(100)
         tablePersonalInfo.setWidths(widthsTitle)
         //First name
         PdfPCell cell = new PdfPCell(new Paragraph(firstName, regularFont))
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         if (studentAccount.user.firstName) {
             cell = new PdfPCell(new Paragraph(studentAccount.user.firstName, regularFont))
         } else {
             cell = new PdfPCell(new Paragraph("", regularFont))
         }
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         //Profile photo
         if (studentAccount.photo) {
             Photo photo = studentAccount.photo
             Image image = Image.getInstance(studentPhotoDirectory + "/" + photo.newFilename)
             image.scaleAbsolute(83, 86)
             cell = new PdfPCell(image, false)
             cell.setVerticalAlignment(Image.ALIGN_MIDDLE)
             cell.setHorizontalAlignment(Image.ALIGN_CENTER)
             cell.setBorderColor(new BaseColor(173, 173, 173))
         } else {
             Image image = Image.getInstance("grails-app/assets/images/ikariera_sk/no-profile-image2.png")
             image.scaleAbsolute(83, 86)
             cell = new PdfPCell(image, false)
             cell.setVerticalAlignment(Image.ALIGN_MIDDLE)
             cell.setHorizontalAlignment(Image.ALIGN_CENTER)
             cell.setBorderColor(new BaseColor(173, 173, 173))
         }
         cell.setRowspan(6)
         tablePersonalInfo.addCell(cell)
         //Empty column
         cell = new PdfPCell()
         cell.setRowspan(6)
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         //Last Name
         cell = new PdfPCell(new Paragraph(lastName, regularFont))
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         if (studentAccount.user.lastName) {
             cell = new PdfPCell(new Paragraph(studentAccount.user.lastName, regularFont))
         } else {
             cell = new PdfPCell(new Paragraph("", regularFont))
         }
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         //Date of birth
         cell = new PdfPCell(new Paragraph(dateOfBirth, regularFont))
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         if (studentAccount.birthday) {
             cell = new PdfPCell(new Paragraph(studentAccount.birthday, regularFont))
         } else {
             cell = new PdfPCell(new Paragraph("", regularFont))
         }
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         //Address
         cell = new PdfPCell(new Paragraph(address, regularFont))
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         if (studentAccount.addressStreet && studentAccount.addressZip && studentAccount.addressCity) {
             cell = new PdfPCell(new Paragraph(studentAccount.addressStreet + ", " + studentAccount.addressZip + " " + studentAccount.addressCity, regularFont))
         } else {
             cell = new PdfPCell(new Paragraph("", regularFont))
         }
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         //Phone number
         cell = new PdfPCell(new Paragraph(phone, regularFont))
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         if (studentAccount.telephone) {
             cell = new PdfPCell(new Paragraph(studentAccount.telephone, regularFont))
         } else {
             cell = new PdfPCell(new Paragraph("", regularFont))
         }
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         //Email address
         cell = new PdfPCell(new Paragraph(email, regularFont))
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         if (studentAccount.user.username) {
             cell = new PdfPCell(new Paragraph(studentAccount.user.username, regularFont))
         } else {
             cell = new PdfPCell(new Paragraph("", regularFont))
         }
         cell.setBorder(0)
         tablePersonalInfo.addCell(cell)
         document.add(tablePersonalInfo)
         document.add(new Paragraph(" "))

         //Profile - short info
         if (studentAccount.personalCharacteristic) {
             addSubtitle(document, profile)
             Paragraph profileShortInfo = new Paragraph(studentAccount.personalCharacteristic, regularFont)
             profileShortInfo.setSpacingBefore(10)
             document.add(profileShortInfo)
         }
         document.add(new Paragraph(" "))

         //Work experience
         addSubtitle(document, workExperience)
         if(studentAccount.experiences.size()){
             for(Experience experience : studentAccount.experiences) {
                 PdfPTable tableWorkExperience = new PdfPTable(4)
                 tableWorkExperience.setWidthPercentage(100)
                 tableWorkExperience.setWidths(widthsOthers)
                 if (experience.employer) {
                     cell = new PdfPCell(new Paragraph(experience.employer, boldFont))
                     cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT)
                     cell.setPaddingRight(8)
                 } else {
                     cell = new PdfPCell(new Paragraph("", boldFont))
                 }
                 cell.setBorder(0)
                 cell.setRowspan(2)
                 tableWorkExperience.addCell(cell)
                 if (experience.occupation) {
                     cell = new PdfPCell(new Paragraph(experience.occupation, boldFont))
                     cell.setColspan(2)
                     cell.setPaddingRight(8)
                 } else {
                     cell = new PdfPCell(new Paragraph("", boldFont))
                     cell.setColspan(2)
                 }
                 cell.setBorder(0)
                 tableWorkExperience.addCell(cell)
                 String workExperienceDates = ""
                 if (experience.periodStart) {
                     workExperienceDates += experience.periodStart + " - "
                 } else {
                     workExperienceDates += "X - "
                 }
                 if (experience.periodEnd) {
                     workExperienceDates += experience.periodEnd
                 } else {
                     workExperienceDates += "X"
                 }
                 Paragraph parWorkExperienceDates = new Paragraph(workExperienceDates, boldFont)
                 cell = new PdfPCell(parWorkExperienceDates)
                 cell.setRowspan(2)
                 cell.setBorder(0)
                 cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT)
                 tableWorkExperience.addCell(cell)
                 if(experience.activities) {
                     cell = new PdfPCell(new Paragraph("• ", boldFont))
                     cell.setVerticalAlignment(Paragraph.ALIGN_TOP)
                     cell.setHorizontalAlignment(Paragraph.ALIGN_MIDDLE)
                     cell.setBorder(0)
                     tableWorkExperience.addCell(cell)

                     cell = new PdfPCell(new Paragraph(experience.activities, regularFont))
                     cell.setPaddingRight(8)
                 } else {
                     cell = new PdfPCell(new Paragraph("", regularFont))
                 }
                 cell.setBorder(0)
                 tableWorkExperience.addCell(cell)
                 document.add(tableWorkExperience)
                 document.add(new Paragraph(" "))
             }
         } else {
             Paragraph parNoWorkExperience = new Paragraph(noWorkExperience, italicFont)
             parNoWorkExperience.setLeading(30)
             parNoWorkExperience.setAlignment(Paragraph.ALIGN_CENTER)
             document.add(parNoWorkExperience)
             document.add(new Paragraph(" "))
         }

         // Education
         addSubtitle(document, educations)
         if(studentAccount.educations.size()){
             for(Education education : studentAccount.educations) {
                 PdfPTable tableEducations = new PdfPTable(3)
                 tableEducations.setWidthPercentage(100)
                 tableEducations.setWidths(widthsEducation)
                 if (education.university.name) {
                     cell = new PdfPCell(new Paragraph(education.university.name, boldFont))
                     cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT)
                     cell.setPaddingRight(8)
                 } else {
                     cell = new PdfPCell(new Paragraph("", boldFont))
                 }
                 cell.setBorder(0)
                 cell.setRowspan(2)
                 tableEducations.addCell(cell)
                 if (education.studyCategory.name) {
                     cell = new PdfPCell(new Paragraph(education.studyCategory.name, boldFont))
                     cell.setPaddingRight(8)
                 } else {
                     cell = new PdfPCell(new Paragraph("", boldFont))
                 }
                 cell.setBorder(0)
                 tableEducations.addCell(cell)
                 String educationsDates = ""
                 if (education.startYear) {
                     educationsDates += education.startYear.toString().split("-").first() + " - "
                 } else {
                     educationsDates += "X - "
                 }
                 if (education.endingYear) {
                     educationsDates += education.endingYear.toString().split("-").first()
                 } else {
                     educationsDates += "X"
                 }
                 Paragraph parEducationsDate = new Paragraph(educationsDates, boldFont)
                 cell = new PdfPCell(parEducationsDate)
                 cell.setRowspan(2)
                 cell.setBorder(0)
                 cell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT)
                 tableEducations.addCell(cell)
                 if(education.specialization) {
                     cell = new PdfPCell(new Paragraph(education.specialization, regularFont))
                     cell.setPaddingRight(8)
                 } else {
                     cell = new PdfPCell(new Paragraph("", regularFont))
                 }
                 cell.setBorder(0)
                 tableEducations.addCell(cell)
                 document.add(tableEducations)
                 document.add(new Paragraph(" "))
             }
         } else {
             Paragraph parNoEducations = new Paragraph(noEducation, italicFont)
             parNoEducations.setLeading(30)
             parNoEducations.setAlignment(Paragraph.ALIGN_CENTER)
             document.add(parNoEducations)
             document.add(new Paragraph(" "))
         }

         //Skills
         addSubtitle(document, skills)
         PdfPTable tableSkills = new PdfPTable(2)
         tableSkills.setWidthPercentage(100)
         cell = new PdfPCell(new Paragraph(skillsSoftware, boldFont))
         cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER)
         cell.setBorder(0)
         cell.setPaddingBottom(5)
         tableSkills.addCell(cell)
         cell = new PdfPCell(new Paragraph(skillsLanguages, boldFont))
         cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER)
         cell.setBorder(0)
         cell.setPaddingBottom(5)
         tableSkills.addCell(cell)
         //Software
         PdfPCell cellSoftware = new PdfPCell()
         if (studentAccount.skills.size()) {
             PdfPTable tableSoftware = new PdfPTable(2)
             tableSoftware.setWidthPercentage(100)
             for(SkillCombination skillCombination : studentAccount.skills) {
                 cell = new PdfPCell(new Paragraph(skillCombination.skillType.name, regularFont))
                 cell.setBorder(0)
                 skillCombination.level.posOrder
                 tableSoftware.addCell(cell)
                 Paragraph dots = new Paragraph(dottedParagraph(skillCombination.level.posOrder))
                 cell = new PdfPCell(dots)
                 cell.setPaddingTop(-6)
                 cell.setBorder(0)
                 tableSoftware.addCell(cell)
             }
             cellSoftware.addElement(tableSoftware)
         } else {
             cellSoftware.addElement(new Paragraph(""))
         }
         cellSoftware.setBorder(0)
         tableSkills.addCell(cellSoftware)
         //Languages
         PdfPCell cellLanguages = new PdfPCell()
         if (studentAccount.languages.size()) {
             PdfPTable tableLanguages= new PdfPTable(2)
             tableLanguages.setWidthPercentage(100)
             for(LanguageCombination languageCombination : studentAccount.languages) {
                 String languageName = ""
                 String languageLevel = ""
                 switch (locale) {
                     case "cs_CZ":
                         languageName = languageCombination.languageType.nameCZ
                         languageLevel = languageCombination.level.nameCZ
                         break
                     case "en_US":
                         languageName = languageCombination.languageType.nameEN
                         languageLevel = languageCombination.level.nameEN
                         break
                     case "sk":
                         languageName = languageCombination.languageType.nameSK
                         languageLevel = languageCombination.level.nameSK
                         break
                     default:
                         languageName = languageCombination.languageType.nameEN
                         languageLevel = languageCombination.level.nameEN
                         break
                 }
                 cell = new PdfPCell(new Paragraph(languageName, regularFont))
                 cell.setBorder(0)
                 cell.setPadding(5)
                 tableLanguages.addCell(cell)
                 cell = new PdfPCell(new Paragraph(languageLevel, regularFont))
                 cell.setBorder(0)
                 cell.setPadding(5)
                 cell.setHorizontalAlignment(Paragraph.ALIGN_LEFT)
                 tableLanguages.addCell(cell)
             }
             cellLanguages.addElement(tableLanguages)
         } else {
             cellLanguages.addElement(new Paragraph(""))
         }
         cellLanguages.setBorder(0)
         cellLanguages.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT)
         tableSkills.addCell(cellLanguages)
         document.add(tableSkills)

         // Courses and Training
         if(studentAccount.certificates.size()){
             addSubtitle(document, certificates)
             for(Certificate certificate : studentAccount.certificates) {
                PdfPTable tableCertificates = new PdfPTable(3)
                tableCertificates.setWidthPercentage(100)
                tableCertificates.setWidths(certificatesWidths)
                String certificateName = certificate.name
                String level = " "
                if (certificate.level) {
                    level = certificate.level
                }
                String certificateDescription = " "
                if (certificate.description) {
                    certificateDescription = certificate.description
                }
                 cell = new PdfPCell(new Paragraph(certificateName, boldFont))
                 cell.setBorder(0)
                 tableCertificates.addCell(cell)
                 cell = new PdfPCell(new Paragraph(level, boldFont))
                 cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER)
                 cell.setBorder(0)
                 tableCertificates.addCell(cell)
                 cell = new PdfPCell(new Paragraph(certificateDescription, regularFont))
                 cell.setBorder(0)
                 tableCertificates.addCell(cell)
                 document.add(tableCertificates)
                 document.add(new Paragraph(" "))
             }
         }
     }

    private def addSubtitle(Document document, String subtitleText) {
        String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')
        FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
        Font subtitlesFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,16,Font.BOLD,new BaseColor(86, 86, 86))

        Paragraph parNewSubtitle = new Paragraph(subtitleText,subtitlesFont)
        parNewSubtitle.setLeading(15,0)
        parNewSubtitle.setAlignment(Paragraph.ALIGN_CENTER)
        parNewSubtitle.setSpacingAfter(-9)
        document.add(parNewSubtitle)
        LineSeparator ls = new LineSeparator()
        ls.setOffset(-8)
        ls.setLineColor(new BaseColor(173, 173, 173))
        document.add(new Chunk(ls))
    }

    private def dottedParagraph(int level) {
        String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')
        FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
        Font bigDot = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,25,Font.BOLD)
        Font regularFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,21,Font.BOLD)

        String dotts = ""
        for (int i = 0; i < level; i++) {
            dotts += "•"
        }
        /*String emptyDotts = ""
        for (int i = 0; i < 5-level; i++) {
            emptyDotts += "○ "
        }*/
        Phrase dottPhrase = new Phrase()
        dottPhrase.add(new Chunk(dotts,bigDot))
        //dottPhrase.add(new Chunk(emptyDotts,regularFont))
        Paragraph parDotts = new Paragraph(dottPhrase)
        return parDotts
    }

    private def addSubtitleCV1(Document document, String subtitleText) {
        String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')// TODO could couse problems in grails 3
        FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
        Font blueSubtitlesFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,13,Font.NORMAL,BaseColor.BLUE, )

        document.add(new Paragraph(subtitleText, blueSubtitlesFont))
        LineSeparator ls = new LineSeparator()
        ls.setOffset(15)
        ls.setLineColor(BaseColor.BLUE)
        document.add(new Chunk(ls))
    }
}
