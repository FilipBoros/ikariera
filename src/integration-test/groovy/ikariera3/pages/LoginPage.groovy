package ikariera3.pages

import geb.Page

/**
 * Created by michal on 2.7.2016.
 */
class LoginPage extends Page{
    static url = "login/auth"

    static at = {$('#loginLabel').text() == "Login"}

    static content = {
        loginForm { $('#loginForm') }
        loginButton { $('#loginButton')}
    }
}
