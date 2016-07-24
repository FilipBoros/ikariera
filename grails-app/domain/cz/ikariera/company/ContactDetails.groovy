package cz.ikariera.company

class ContactDetails {

    String name

    String telephone

    String email

    String detailText

    String positionInCompany

    static belongsTo = [company: Company]

    static constraints = {
        name (nullable: false, blank: true)
        telephone(nullable: true, blank: true)
        positionInCompany(nullable: true, blank: true)
        email(nullable: true, blank: true)
        detailText(nullable: true, blank: true, maxSize: 2000)
    }

    @Override
    String toString() {
        return name + ", tel: " + telephone + ", " + positionInCompany
    }
}
