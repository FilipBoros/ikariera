package org.grails.list

import grails.gorm.PagedResultList

/**
 * Used as workaround for Criteria.org.grails.list(...) bug resulting in rows duplicity.
 * @author Michal Knizek
 */
class ListDistinct {

    /**
     * Returns distinct org.grails.list of specified result
     * @param dc domain class to search
     * @param attrs map of search attributes, used attributes: max, offset
     * @param clos query closure in CriteriaBuilder DSL format
     * @return List of specified records
     */

    static PagedResultList list(Class dc, Map attrs, Closure clos) {
        if(attrs.max && attrs.max instanceof String)
            attrs.max = attrs.max.toInteger()
        if(attrs.offset && attrs.offset instanceof String)
            attrs.offset = attrs.offset.toInteger()

		
        def resultIds = dc.createCriteria().listDistinct() {
            if(attrs.max != null)
                maxResults(attrs.max)
            if(attrs.offset != null)
                firstResult(attrs.offset)
            clos.delegate = delegate
            clos()
            order('id', 'asc')
            projections {
                distinct('id')
            }
        }

        def count = dc.createCriteria().get() {
            clos.delegate = delegate
            clos()
            projections {
				countDistinct('id')
            }
        }

        if(attrs.offset && count < attrs.offset) {
            attrs.offset = 0
            return list(dc, attrs, clos)
        }

		def resultObjects
		int totalCount = (int) count
		
        if(resultIds.size() > 0) {
            resultObjects = dc.getAll(resultIds)
        } else {
            resultObjects = new ArrayList()
        }

		// create PagedResultList
		// TODO - create PagedResultList object without querying to DB
		def c = dc.createCriteria()
		def prl = c.list(max:10, offset:0) {}
		prl.clear()
		prl.addAll(resultObjects)
		prl.setTotalCount(totalCount)
		
		return prl
    }
}
