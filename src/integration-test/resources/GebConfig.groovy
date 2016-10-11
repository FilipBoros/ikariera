import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile

System.setProperty("webdriver.firefox.bin", "/opt/firefox-46/firefox-bin")

reportsDir = "build/test-results"
baseUrl =  'http://localhost:8089/'

driver = {
    //set the firefox locale to 'en-us' since the tests expect english
    //see http://stackoverflow.com/questions/9822717 for more details
    FirefoxProfile profile = new FirefoxProfile()
    profile.setPreference("intl.accept_languages", "en-us")
    def driverInstance = new FirefoxDriver(profile)
    driverInstance.manage().window().maximize()
    driverInstance
}

baseNavigatorWaiting = true
atCheckWaiting = true