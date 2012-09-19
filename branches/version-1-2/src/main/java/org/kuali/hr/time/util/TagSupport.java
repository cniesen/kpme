package org.kuali.hr.time.util;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TagSupport {

    private String principalId;

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public Map<String, String> getDocumentStatus() {
        return TkConstants.DOCUMENT_STATUS;
    }

    private List<String> ipAddresses = new LinkedList<String>();

    public List<String> getIpAddresses() {
        ipAddresses.add("127.0.0.1");
        ipAddresses.add("129.79.23.203");
        ipAddresses.add("129.79.23.59");
        ipAddresses.add("129.79.23.123");

        return ipAddresses;
    }

    public String getPrincipalFullName(){
        Person person = KimApiServiceLocator.getPersonService().getPerson(principalId);
        if(person != null){
            return person.getName();
        }
        return "";
    }

}
