package cz.ikariera.security

import org.springframework.http.HttpMethod

class Requestmap {

    String url
    String configAttribute
    HttpMethod httpMethod

    static mapping = {
        /*cache true*/ //TODO odkommentovat
    }

    static constraints = {
        url blank: false, unique: 'httpMethod'
        configAttribute blank: false
        httpMethod nullable: true
    }
}