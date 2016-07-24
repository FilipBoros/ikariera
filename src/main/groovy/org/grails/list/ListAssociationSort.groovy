package org.grails.list

class ListAssociationSort {
    static assocSort
    static initialized = false

    static init() {
        assocSort = { parts, ord ->
            if (parts.size() == 1) {
                order(parts[0], ord)
            } else {
                "${parts[0]}" {
                    ListAssociationSort.assocSort(parts[1..-1], ord)
                }
            }
        }
        initialized = true
    }

    static def associationOrder(sort, order, deleg) {
        if(!initialized)
            init()

        if (sort) {
            def parts = sort.split("\\.")
            assocSort.delegate = deleg
            assocSort(parts, order ?: 'asc')
        }
    }


}
