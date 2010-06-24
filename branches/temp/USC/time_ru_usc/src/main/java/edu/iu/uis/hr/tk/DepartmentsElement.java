package edu.iu.uis.hr.tk;

import java.util.Iterator;

import org.jdom.Element;

import edu.iu.uis.hr.AbstractWorkflowDocumentElement;

public class DepartmentsElement extends AbstractWorkflowDocumentElement {

    private static final String ELEMENT_NAME = "DEPARTMENTS";
    
    public DepartmentsElement() {
        super();
        setElementName(DepartmentsElement.ELEMENT_NAME);
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
            element.addContent(((DepartmentElement)childElementsIterator.next()).getElement());
        }
        return element;
    }
}
