package cz.ikariera

import cz.ikariera.company.JobOffer
import grails.converters.XML
import org.grails.web.converters.marshaller.NameAwareMarshaller
import org.grails.web.converters.marshaller.ObjectMarshaller

import java.text.SimpleDateFormat

class JobOfferMarshaller implements ObjectMarshaller<XML>,NameAwareMarshaller  {

    public boolean supports(Object object) { return object instanceof JobOffer }

    public void marshalObject(Object object, XML converter) {
        JobOffer offer = (JobOffer)object
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy")
        converter.attribute 'id', offer.id.toString()
        converter.build {
            link("cz.ikariera.cz/job-offer/detail/"+offer.id)
            name(offer.positionName)
            region(offer.positionLocality.name)
            salary("")
            company(offer.company.companyName)
            companyinfo("cz.ikariera.cz/company-profiles/detail/"+offer.company.id)
            contacts(offer.company.contactDetails)
        }

        converter.startNode("description")
        converter.chars("<![CDATA[")
        converter.chars(offer.jobOfferDescription )
        converter.chars("]]>")
        converter.end()
        converter.build {
            expire(simpleDateFormat.format(offer.willExpire))
        }
        /*converter.build {
            if (offer.lastUpdated != null)
                updated(simpleDateFormat.format(offer.lastUpdated))
            else
                updated(simpleDateFormat.format(new Date()))
        }*/
    }

    String getElementName(Object o) {
        return 'job'
    }
}
