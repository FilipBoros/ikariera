package cz.ikariera.fulltext

import grails.transaction.Transactional

@Transactional
class FulltextService {

    def sessionFactory

    def getJobOfferByFullText() {
        String query = "SELECT position_name FROM `job_offer` WHERE MATCH (position_name,job_offer_description)" + " AGAINST ('pocitacovych navrh' IN BOOLEAN MODE)"
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }
}
