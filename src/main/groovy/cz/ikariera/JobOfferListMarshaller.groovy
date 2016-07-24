package cz.ikariera
import cz.ikariera.company.JobOffer
import grails.converters.XML
import org.grails.web.converters.marshaller.NameAwareMarshaller
import org.grails.web.converters.marshaller.ObjectMarshaller

class JobOfferListMarshaller implements  ObjectMarshaller<XML>,NameAwareMarshaller {

    public boolean supports(Object object) { return object instanceof List<JobOffer> }

    public void marshalObject(Object object, XML converter) {
        List<JobOffer> list = (List<JobOffer>) object
        converter.attribute 'hide_contacts', "1"
        list.each {
            converter.startNode 'job'
            converter.convertAnother(it)
            converter.end()
        }
    }

    String getElementName(Object o) {
        return 'jobs'
    }
}
