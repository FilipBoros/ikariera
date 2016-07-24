package cz.ikariera.admin


class RemoteRegister {


    String uid

    String firstName
    String lastName
    String username

    String data


    Date date = new Date()


    static constraints = {
        firstName(blank: false, nullable: false, maxSize: 255)
        lastName(blank: false, nullable: false, maxSize: 255)
        username(blank: false, nullable: false, unique: true, email: true)

        uid(blank: true, nullable: true, maxSize: 255)
        data(blank: true, nullable: true)

    }

    static mapping = {
        data type: 'text'

    }

}
