package cz.ikariera.admin

class AdminUserFilterCommand {


    String firstName
    String lastName

    //  Role role

    Boolean isStudent
    Boolean isCompany
    Boolean admin

    String username

    Integer max
    Integer offset

    String sort
    String order

    /**
     * Returns a LinkedHashMap with attributes for filtering
     */
    public LinkedHashMap getFilterParams() {
        LinkedHashMap filterParams = new LinkedHashMap()
        filterParams << [
                "firstName": firstName,
                "lastName": lastName,
                "username": username,
                "max": max,
                "isStudent": isStudent,
                "isCompany": isCompany,
                "admin": admin,


                "offset": offset,
                "sort": sort,
                "order": order
        ]

        return filterParams
    }
}
