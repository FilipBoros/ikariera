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
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell

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
            default:
                break
        }

        //generateDocument1(document, studentAccount, studentPhotoDirectory)

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
    private def generateDocument1(Document document, StudentAccount studentAccount, String studentPhotoDirectory, String locale) {
        String unicodeArialFont = ServletContextHolder.servletContext.getRealPath('/')// TODO could couse problems in grails 3
        /*println(unicodeArialFont)*/
        // Parameters
        FontFactory.register(unicodeArialFont+"fonts/arial-unicode-ms.ttf","Arial Unicode MS")
        Font titleFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,20,Font.BOLD,BaseColor.BLUE)
        Font regularFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,12,Font.NORMAL)
        Font boldFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,12,Font.BOLD)
        Font italicFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,12,Font.ITALIC)
        Font blueSubtitlesFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,14,Font.NORMAL,BaseColor.BLUE, )
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
        String addressValue = ""
        if (studentAccount.addressStreet && studentAccount.addressZip && studentAccount.addressCity)
        {
            addressValue = studentAccount.addressStreet+", "+studentAccount.addressZip+" "+studentAccount.addressCity
        } else {
            addressValue = "- - - - "
        }
        Phrase addressPhrase = new Phrase()
        addressPhrase.add(new Chunk(addressDescription,boldFont))
        addressPhrase.add(new Chunk(addressValue,regularFont))
        Paragraph addressParagraph = new Paragraph(addressPhrase)
        document.add(addressParagraph)
        // Telephone
        String telephoneDescription = "Tel.:"+" " // i18n first part
        String telephoneValue = ""
        if (studentAccount.telephone) {
           telephoneValue = studentAccount.telephone
        } else {
            telephoneValue = "- - - - "
        }
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
        String dateOfBirthValue = ""
        if (studentAccount.birthday) {
            dateOfBirthValue = studentAccount.birthday
        }
        dateOfBirthValue = dateOfBirthValue.substring(0,10)
        Phrase dateOfBirthPhrase = new Phrase()
        dateOfBirthPhrase.add(new Chunk(dateOfBirthDescription,boldFont))
        dateOfBirthPhrase.add(new Chunk(dateOfBirthValue,regularFont))
        Paragraph dateOfBirthParagraph = new Paragraph(dateOfBirthPhrase)
        document.add(dateOfBirthParagraph)*/
        String dateOfBirthDescription = "Date of birth:"+" " // i18n first part
        String dateOfBirthValue = ""
        if (studentAccount.birthday) {
            dateOfBirthValue = studentAccount.birthday[0..9]
        } else {
            dateOfBirthValue = "- - - - "
        }
        Phrase dateOfBirthPhrase = new Phrase()
        dateOfBirthPhrase.add(new Chunk(dateOfBirthDescription,boldFont))
        dateOfBirthPhrase.add(new Chunk(dateOfBirthValue,regularFont))
        Paragraph dateOfBirthParagraph = new Paragraph(dateOfBirthPhrase)
        document.add(dateOfBirthParagraph)

        document.add(new Paragraph(" "))
        // Education
        String educationsDescription = "EDUCATION"
        document.add(new Paragraph(educationsDescription,blueSubtitlesFont))
        LineSeparator ls2 = new LineSeparator()
        ls2.setOffset(15)
        ls2.setLineColor(BaseColor.BLUE)
        document.add(new Chunk(ls2))
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
                // TODO add according to Profesia cv
                /*String startYear = education.
            */}
        } else {
            document.add(new Paragraph("No education defined", italicFont))
            document.add(new Paragraph(" "))
            document.add(new Paragraph(" "))
            document.add(new Paragraph(" "))
        }

        // Employment history
        String experiencesDescription = "EMPLOYMENT HISTORY"
        document.add(new Paragraph(experiencesDescription,blueSubtitlesFont))
        LineSeparator ls4 = new LineSeparator()
        ls4.setOffset(15)
        ls4.setLineColor(BaseColor.BLUE)
        document.add(new Chunk(ls4))
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
            document.add(new Paragraph("No employment history defined", italicFont))
            document.add(new Paragraph(" "))
            document.add(new Paragraph(" "))
            document.add(new Paragraph(" "))
        }

        // Language skill
        String languagesDescription = "LANGUAGE KNOWLEDGE"
        document.add(new Paragraph(languagesDescription,blueSubtitlesFont))
        LineSeparator ls5 = new LineSeparator()
        ls5.setOffset(15)
        ls5.setLineColor(BaseColor.BLUE)
        document.add(new Chunk(ls5))
        if(studentAccount.languages.size()){
            for(LanguageCombination languageCombination : studentAccount.languages) {
                String languageName  = languageCombination.languageType.nameCZ + " - "
                String languageLevel = languageCombination.level.nameCZ
                Phrase languagePhrase = new Phrase()
                languagePhrase.add(new Chunk(languageName,boldFont))
                languagePhrase.add(new Chunk(languageLevel,regularFont))
                Paragraph languageParagraph = new Paragraph(languagePhrase)
                document.add(languageParagraph)

                //document.add(new Paragraph(" "))

            }
        } else {
            document.add(new Paragraph("No language knowledge defined", italicFont))
            document.add(new Paragraph(" "))
            document.add(new Paragraph(" "))
            document.add(new Paragraph(" "))
        }

        // Courses and Training
        if(studentAccount.certificates.size()){
            String certificatesDescription = "COURSES AND TRAINING"
            document.add(new Paragraph(certificatesDescription,blueSubtitlesFont))
            LineSeparator ls3 = new LineSeparator()
            ls3.setOffset(15)
            ls3.setLineColor(BaseColor.BLUE)
            document.add(new Chunk(ls3))
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
        Font italicFontSubtitle = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,11,Font.ITALIC, BaseColor.LIGHT_GRAY)
        Font subtitlesFont = FontFactory.getFont("Arial Unicode MS",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,13,Font.UNDERLINE|Font.BOLD,BaseColor.BLACK, )

        //Widths for table columns
        int[] widthsPersonalInfo = [3, 4]
        int[] widthsEducation = [3, 2, 2]
        int[] widthsTitle = [1, 3, 3]

        //Profile photo
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
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
            cell.setFixedHeight(80f);
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
            cell.setFixedHeight(80f);
            cell.setRowspan(2)
            cell.setBorder(0)
            table.addCell(cell)
        }

        //Name
        document.add(new Paragraph(" "))
        String name = studentAccount.user.firstName+" "+studentAccount.user.lastName
        Paragraph title = new Paragraph(name,titleFont)
        title.setAlignment(Paragraph.ALIGN_CENTER)
        PdfPCell cell = new PdfPCell();
        cell.addElement(title)
        cell.setColspan(2)
        cell.setFixedHeight(40f);
        cell.setBorder(2)
        cell.setBorderColor(BaseColor.LIGHT_GRAY)
        table.addCell(cell)
        if (studentAccount.personalCharacteristic) {
            subTitle = studentAccount.personalCharacteristic
        }
        Paragraph parSubTitle = new Paragraph(subTitle,italicFontSubtitle)
        parSubTitle.setAlignment(Paragraph.ALIGN_CENTER)
        cell = new PdfPCell();
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
        PdfPTable tableAddress = new PdfPTable(2);
        tableAddress.setWidthPercentage(100);
        tableAddress.setWidths(widthsPersonalInfo)

        // Address
        String addressValue = " "
        if (studentAccount.addressStreet && studentAccount.addressZip && studentAccount.addressCity)
        {
            addressValue = studentAccount.addressStreet+", "+studentAccount.addressZip+" "+studentAccount.addressCity
        }
        Paragraph parAddressDescription = new Paragraph(addressDescription,importantFont)
        Paragraph parAddressValue = new Paragraph(addressValue,regularFont)
        //parPersonalInfoDescription.setLeading(10)
        //parAddressValue.setLeading(10)
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
        PdfPTable tableEmail = new PdfPTable(2);
        tableEmail.setWidthPercentage(100);
        tableEmail.setWidths(widthsPersonalInfo)
        String emailValue = studentAccount.user.username
        Paragraph parEmailDescription = new Paragraph(emailDescription,importantFont)
        Paragraph parEmailValue = new Paragraph(emailValue,regularFont)
        PdfPCell cellEmailDescription = new PdfPCell();
        cellEmailDescription.addElement(parEmailDescription)
        PdfPCell cellEmailValue = new PdfPCell()
        cellEmailValue.addElement(parEmailValue)
        cellEmailDescription.setBorder(0)
        cellEmailValue.setBorder(0)
        tableEmail.addCell(cellEmailDescription)
        tableEmail.addCell(cellEmailValue)
        document.add(tableEmail)

        // Telephone
        PdfPTable tableContact = new PdfPTable(2);
        tableContact.setWidthPercentage(100);
        tableContact.setWidths(widthsPersonalInfo)
        String contactDescription = ""
        String contactValue = " "
        if (studentAccount.telephone) {
            contactValue = studentAccount.telephone
        }
        Paragraph parContactDescription = new Paragraph(contactDescription,importantFont)
        Paragraph parContactValue = new Paragraph(contactValue,regularFont)
        //parContactDescription.setLeading(10)
        //parContactValue.setLeading(10)
        PdfPCell cellContactDescription = new PdfPCell();
        cellContactDescription.addElement(parContactDescription)
        PdfPCell cellContactValue = new PdfPCell()
        cellContactValue.addElement(parContactValue)
        cellContactDescription.setBorder(0)
        cellContactValue.setBorder(0)
        tableContact.addCell(cellContactDescription)
        tableContact.addCell(cellContactValue)
        document.add(tableContact)

        //Date of birth
        PdfPTable tableBirth = new PdfPTable(2);
        tableBirth.setWidthPercentage(100);
        tableBirth.setWidths(widthsPersonalInfo)
        String dateOfBirthValue = " "
        if (studentAccount.birthday) {
            dateOfBirthValue = studentAccount.birthday[0..9]
        }
        Paragraph parDateOfBirthDescription = new Paragraph(dateOfBirthDescription,importantFont)
        Paragraph parDateOfBirthValue = new Paragraph(dateOfBirthValue,regularFont)
        PdfPCell cellDateOfBirthDescription = new PdfPCell();
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
                PdfPTable tableEducation = new PdfPTable(3);
                tableEducation.setWidthPercentage(100);
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
                PdfPCell cellEducationYears = new PdfPCell();
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
                document.add(experienceDate);
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
            }
        } else {
            Paragraph parNoEmployment = new Paragraph(noEmployment, italicFont)
            parNoEmployment.setLeading(30)
            parNoEmployment.setAlignment(Paragraph.ALIGN_CENTER)
            document.add(parNoEmployment)
        }
        document.add(new Paragraph(" "))

        // Language skill
        Paragraph parLanguagesDescription = new Paragraph(languagesDescription, subtitlesFont)
        parLanguagesDescription.setSpacingAfter(10)
        document.add(parLanguagesDescription)
        if(studentAccount.languages.size()){
            for(LanguageCombination languageCombination : studentAccount.languages) {
                PdfPTable tableLanguage = new PdfPTable(2);
                tableLanguage.setWidthPercentage(100);
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
                PdfPCell cellLanguageName = new PdfPCell();
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
        PdfPTable tableSkill = new PdfPTable(4);
        tableSkill.setWidthPercentage(100);
        Paragraph parSkillsDescription = new Paragraph(skillsDescription, subtitlesFont)
        parSkillsDescription.setSpacingAfter(10)
        document.add(parSkillsDescription)
        if(studentAccount.skills.size()){
            for(SkillCombination skillCombination : studentAccount.skills) {
                //tableSkill.setWidths(widthsPersonalInfo)
                String skillName = skillCombination.skillType.name + ":"
                String skillLevel = skillCombination.level.name
                Paragraph parSkillName = new Paragraph(skillName,boldFont)
                Paragraph parSkillLevel = new Paragraph(skillLevel,regularFont)
                PdfPCell cellSkillName = new PdfPCell();
                cellSkillName.addElement(parSkillName)
                PdfPCell cellSkillLevel = new PdfPCell()
                cellSkillLevel.addElement(parSkillLevel)
                cellSkillName.setBorder(0)
                cellSkillLevel.setBorder(0)
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

        /*Paragraph parSkillsDescription = new Paragraph(skillsDescription, subtitlesFont)
        parSkillsDescription.setSpacingAfter(10)
        document.add(parSkillsDescription)
        if(studentAccount.skills.size()){
            for(SkillCombination skillCombination : studentAccount.skills) {
                PdfPTable tableSkill = new PdfPTable(2);
                tableSkill.setWidthPercentage(100);
                tableSkill.setWidths(widthsPersonalInfo)
                String skillName = skillCombination.skillType.nameCZ
                String skillLevel = skillCombination.level.nameCZ
                Paragraph parSkillName = new Paragraph(skillName,boldFont)
                Paragraph parSkillLevel = new Paragraph(skillLevel,regularFont)
                PdfPCell cellSkillName = new PdfPCell();
                cellSkillName.addElement(parSkillName)
                PdfPCell cellSkillLevel = new PdfPCell()
                cellSkillLevel.addElement(parSkillLevel)
                cellSkillName.setBorder(0)
                cellSkillLevel.setBorder(0)
                tableSkill.addCell(cellSkillName)
                tableSkill.addCell(cellSkillLevel)
                document.add(tableSkill)
            }
        } else {
            Paragraph parNoSkills = new Paragraph(noSkills, italicFont)
            parNoSkills.setLeading(30)
            parNoSkills.setAlignment(Paragraph.ALIGN_CENTER)
            document.add(parNoSkills)
        }
        document.add(new Paragraph(" "))*/

        // Courses and Training
        if(studentAccount.certificates.size()){
            Paragraph parCertificatesDescription = new Paragraph(certificatesDescription, subtitlesFont)
            parCertificatesDescription.setSpacingAfter(10)
            document.add(parCertificatesDescription)
            for(Certificate certificate : studentAccount.certificates) {
                PdfPTable tableCertificates = new PdfPTable(2);
                tableCertificates.setWidthPercentage(100);
                tableCertificates.setWidths(widthsPersonalInfo)
                String certificateName = certificate.name
                String level = " "
                if (certificate.level) {
                    level = certificate.level
                }
                String certificateDescription = " "
                if (certificate.description) {
                    certificateDescription =  "• " + certificate.description
                }
                Paragraph parCertificateName = new Paragraph(certificateName, boldFont)
                Paragraph parLevel = new Paragraph(level,regularFont)
                Paragraph parCertificateDescription = new Paragraph(certificateDescription,italicFont)
                PdfPCell cellCertificateName = new PdfPCell();
                cellCertificateName.addElement(parCertificateName)
                PdfPCell cellLevel = new PdfPCell()
                cellLevel.addElement(parLevel)
                cellCertificateName.setBorder(0)
                cellLevel.setBorder(0)
                tableCertificates.addCell(cellCertificateName)
                tableCertificates.addCell(cellLevel)
                document.add(tableCertificates)
                document.add(parCertificateDescription)
                document.add(new Paragraph(" "))
            }
        }
    }
}
