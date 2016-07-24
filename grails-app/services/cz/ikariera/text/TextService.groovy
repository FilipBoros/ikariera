package cz.ikariera.text

import cz.ikariera.security.User

/**
 * Simple service for sending emails.
 *
 * Work is planned in the Grails roadmap to implement first-class email
 * support, so there's no point in making this code any more sophisticated
 */
/*
class EmailService {
    boolean transactional = false
    MailSender mailSender
    SimpleMailMessage mailMessage // a "prototype" email instance
    /**
     * Send a org.grails.list of emails
     *
     * @param mails a org.grails.list of maps
     */
/*
    def sendEmails(mails) {
        // Build the mail messages
        def messages = []
        for (mail in mails) {
            // Create a thread safe "sandbox" of the message
            SimpleMailMessage message = new SimpleMailMessage(mailMessage)
            message.to = mail.to
            message.text = mail.text
            message.subject = mail.subject
            messages << message
        }
        // Send them all together
        try {
            println "about to send ${messages.size()} messages to:n${messages.to.join('n')}"
            mailSender.send(messages as SimpleMailMessage[])
        } catch (MailException ex) {
            println "Failed to send emails"
            ex.printStackTrace()
        }
    }
}  */

/**
 * Simple service for redirection after sucessful ligin.
 * Url page where is user is redirected is defined here.
 */
/*Will strip given String text to specific size
additional three dots colud be added to string by setting
dotolean to true
*/

class TextService {

    String loggedUserCompanyCredits(int id) {

        def user = User.get(id)
        int num = (user.company?.credits) ? (user.company.credits) : 0

        return num
    }



    String loggedUserCompanyName(int id) {

        def user = User.get(id)
        String name = (user.company?.companyName)?:""

        return name
    }

    String loggedUserProfileFinished(int id) {

        int num = 25;
        def user = User.get(id)
        num += (user?.studentAccount?.cv) ? 25 : 0
        num += (user?.studentAccount?.educations) ? 25 : 0
        num += (user?.studentAccount?.experiences) ? 25 : 0

        return num.toString()
    }





    String reduceString(String s, int startPos, int endPos, boolean dots) {

        int size = s.size()

        s = s.replaceAll('<', "&lt;")
        s = s.replaceAll('>', "&gt;")

        // s = s.replaceAll('\n', "<br \\>")

        if (s) {
            return s.substring(startPos, size < endPos ? size - 1 : endPos) + (dots ? "..." : "")

        } else {
            return ""
        }


    }


    String stripHtmlSpecificTags (String s) {


            def regex = /<\/?(?i:script|embed|object|frameset|frame|iframe|meta|link|style|a|img)(.|\n)*?>/
            s.replaceAll(regex, '')




    }




    String prepareTextForHtml(String s) {

        s = s.replaceAll('<', "&lt;")
        s = s.replaceAll('>', "&gt;")

        s = s.replaceAll('\n', "<br \\>")

        return s
    }


}

/*
import java.awt.Image as AWTImage
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.imageio.ImageIO as IIO
import java.awt.Graphics2D


static resize = { bytes, out, maxW, maxH ->
AWTImage ai = new ImageIcon(bytes).image
int width = ai.getWidth( null )
int height = ai.getHeight( null )

def limits = 300..2000
assert limits.contains( width ) && limits.contains( height ) : 'Picture is either too small or too big!'

float aspectRatio = width / height
float requiredAspectRatio = maxW / maxH

int dstW = 0
int dstH = 0
if (requiredAspectRatio < aspectRatio) {
dstW = maxW dstH = Math.round( maxW / aspectRatio)
} else {
dstH = maxH dstW = Math.round(maxH * aspectRatio)
}

BufferedImage bi = new BufferedImage(dstW, dstH,   BufferedImage.TYPE_INT_RGB)
Graphics2D g2d = bi.createGraphics()
g2d.drawImage(ai, 0, 0, dstW, dstH, null, null)

IIO.write( bi, 'JPEG', out )
}
*/