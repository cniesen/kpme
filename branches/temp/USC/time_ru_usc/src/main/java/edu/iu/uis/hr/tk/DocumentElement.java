package edu.iu.uis.hr.tk;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jdom.Element;

import edu.iu.uis.hr.AbstractWorkflowDocumentElement;
import edu.iu.uis.hr.WorkflowDocumentElement;

public class DocumentElement extends AbstractWorkflowDocumentElement {

    private static final Logger LOG = Logger.getLogger(DocumentElement.class);

    private static final String ELEMENT_NAME = "TKDocument";
    
    public DocumentElement() {
        super();
        setElementName(DocumentElement.ELEMENT_NAME);
    }
    
    public Element getElement() {
        Element element = new Element(getElementName());
        Iterator attributeKeys = getAttributeMap().keySet().iterator();
        while (attributeKeys.hasNext()) {
            String key = (String) attributeKeys.next();
            element.setAttribute(key, (String)getAttributeMap().get(key));
        }
        Iterator childElementsIterator = getChildElements().iterator();
        while (childElementsIterator.hasNext()) {
            element.addContent(((WorkflowDocumentElement)childElementsIterator.next()).getElement());
        }
        return element;
    }

}
