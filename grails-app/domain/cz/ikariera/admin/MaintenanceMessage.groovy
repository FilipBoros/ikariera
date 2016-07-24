package cz.ikariera.admin

class MaintenanceMessage {

    String name
    String text
    Date   dateBegin
    Date   dateEnd

    static constraints = {
        text size: 1..400
    }
}
