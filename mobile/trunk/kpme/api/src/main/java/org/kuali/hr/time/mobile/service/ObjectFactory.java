
package org.kuali.hr.time.mobile.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.kuali.hr.time.mobile.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddClockAction_QNAME = new QName("http://service.mobile.time.hr.kuali.org/", "addClockAction");
    private final static QName _GetClockEntryInfo_QNAME = new QName("http://service.mobile.time.hr.kuali.org/", "getClockEntryInfo");
    private final static QName _GetClockEntryInfoResponse_QNAME = new QName("http://service.mobile.time.hr.kuali.org/", "getClockEntryInfoResponse");
    private final static QName _AddClockActionResponse_QNAME = new QName("http://service.mobile.time.hr.kuali.org/", "addClockActionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.kuali.hr.time.mobile.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HashMap }
     * 
     */
    public HashMap createHashMap() {
        return new HashMap();
    }

    /**
     * Create an instance of {@link GetClockEntryInfo }
     * 
     */
    public GetClockEntryInfo createGetClockEntryInfo() {
        return new GetClockEntryInfo();
    }

    /**
     * Create an instance of {@link AddClockActionResponse }
     * 
     */
    public AddClockActionResponse createAddClockActionResponse() {
        return new AddClockActionResponse();
    }

    /**
     * Create an instance of {@link GetClockEntryInfoResponse }
     * 
     */
    public GetClockEntryInfoResponse createGetClockEntryInfoResponse() {
        return new GetClockEntryInfoResponse();
    }

    /**
     * Create an instance of {@link AddClockAction }
     * 
     */
    public AddClockAction createAddClockAction() {
        return new AddClockAction();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClockAction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.mobile.time.hr.kuali.org/", name = "addClockAction")
    public JAXBElement<AddClockAction> createAddClockAction(AddClockAction value) {
        return new JAXBElement<AddClockAction>(_AddClockAction_QNAME, AddClockAction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClockEntryInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.mobile.time.hr.kuali.org/", name = "getClockEntryInfo")
    public JAXBElement<GetClockEntryInfo> createGetClockEntryInfo(GetClockEntryInfo value) {
        return new JAXBElement<GetClockEntryInfo>(_GetClockEntryInfo_QNAME, GetClockEntryInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClockEntryInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.mobile.time.hr.kuali.org/", name = "getClockEntryInfoResponse")
    public JAXBElement<GetClockEntryInfoResponse> createGetClockEntryInfoResponse(GetClockEntryInfoResponse value) {
        return new JAXBElement<GetClockEntryInfoResponse>(_GetClockEntryInfoResponse_QNAME, GetClockEntryInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClockActionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.mobile.time.hr.kuali.org/", name = "addClockActionResponse")
    public JAXBElement<AddClockActionResponse> createAddClockActionResponse(AddClockActionResponse value) {
        return new JAXBElement<AddClockActionResponse>(_AddClockActionResponse_QNAME, AddClockActionResponse.class, null, value);
    }

}
