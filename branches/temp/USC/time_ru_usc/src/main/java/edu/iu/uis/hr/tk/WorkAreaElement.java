package edu.iu.uis.hr.tk;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.AbstractWorkflowDocumentElement;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.WorkflowDocumentElement;

public class WorkAreaElement extends AbstractWorkflowDocumentElement {

    private static final Logger LOG = Logger.getLogger(WorkAreaElement.class);

    private static final String ELEMENT_NAME = "WORKAREA";
    
    protected WorkAreaElement() {
        super();
        setElementName(WorkAreaElement.ELEMENT_NAME);
        setChildElements(Utility.getDefaultListValue());
    }

    public WorkAreaElement(String workArea) {
        this();
        getAttributeMap().put(WorkflowDocumentElement.VALUE_ATTRIBUTE, workArea);
    }

//    public Element getElement() {
//        Element element = new Element(getElementName());
//        Iterator attributeKeys = getAttributeMap().keySet().iterator();
//        while (attributeKeys.hasNext()) {
//            String key = (String) attributeKeys.next();
//            element.setAttribute(key, (String)getAttributeMap().get(key));
//        }
//        return element;
//    }

}
