package cz.ikariera.admin

class RemoteApiKey {

    String serviceName
    String value
    String url
    String info

    static constraints = {

        serviceName unique: true
        url url: true, nullable: true
        value maxSize: 255
        info nullable: true
    }

}
