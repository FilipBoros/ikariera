package ikariera.admin


class AdminUserRoleFilterCommand {


    String firstName
    String lastName

    String authority

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

                "authority" : authority,

                "offset": offset,
                "sort": sort,
                "order": order
        ]

        return filterParams
    }

}
