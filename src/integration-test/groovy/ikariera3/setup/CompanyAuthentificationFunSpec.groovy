package ikariera3.setup

import geb.spock.GebReportingSpec
import ikariera3.pages.LoginPage

/**
 * Created by michal on 5.7.2016.
 */
abstract class CompanyAuthentificationFunSpec extends GebReportingSpec {
    def setup(){
      clearCookies()

      to LoginPage
      at LoginPage

      loginForm.with {
          security_username = 'company@ikariera.eu'
          security_password = 'company'
      }

      waitFor(30) {loginButton.click()}
    }

    def cleanup(){
        clearCookies()
    }
}


abstract class StudentAuthentificationFunSpec extends GebReportingSpec {
    def setup(){
        clearCookies()

        to LoginPage
        at LoginPage

        loginForm.with {
            security_username = 'student@ikariera.eu'
            security_password = 'student'
        }

        waitFor(30) {loginButton.click()}
    }

    def cleanup(){
        clearCookies()
    }
}