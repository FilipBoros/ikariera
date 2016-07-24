package cz.ikariera.admin

import cz.ikariera.company.Company

class CreditsReqests {

    Date requestDate = new Date()
    static belongsTo = [company : Company]

    static constraints = {
    }
}
