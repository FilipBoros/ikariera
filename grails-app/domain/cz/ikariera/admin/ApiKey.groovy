package cz.ikariera.admin

class ApiKey {

    String serviceName
    String value
    String url
    String info
    Date  expire = new Date()

    static constraints = {

        serviceName unique: true
        url url: true, nullable: true
        value maxSize: 255
        info nullable: true

        expire nullable: true
    }
}