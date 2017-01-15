package ikariera3

import geb.spock.GebReportingSpec
import ikariera3.pages.LoginPage
import ikariera3.pages.StudentAccountPage

import grails.test.mixin.integration.Integration
import grails.transaction.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class LoginFunSpec extends GebReportingSpec {

        def setup() {
            clearCookies()
        }

        void "loadLoginPageTest" () {
            when:
                to LoginPage
            then:
                at LoginPage
        }

        void "loginTest" () {
            when:
                to LoginPage
                at LoginPage
                loginForm.with {
                    security_username = 'student@ikariera.eu'
                    security_password = 'student'
                }
                loginButton.click()
            then:
                at StudentAccountPage

        }
}
