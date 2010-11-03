package org.kuali.hr.time.webservices.util;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.thoughtworks.xstream.XStream;

public class WebServiceUtil {
	public static void validateXMLwithXSD(String xmlFile, String xsdFile) throws Exception {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        System.out.println("DocumentBuilderFactory: " + factory.getClass().getName());
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        // Specify our own schema - this overrides the schemaLocation in the xml file
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", xsdFile);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new SimpleErrorHandler());
        Document document = builder.parse(xmlFile);
        Node rootNode = document.getFirstChild();
        System.out.println("Root node: " + rootNode.getNodeName());



    }
	

	public static Object parseXml(String xmlData, Class outerClass,
			Class innerClass) {
		XStream xstream = new XStream();
		//System.out.println("" + outerClass.getSimpleName().toLowerCase());
		//System.out.println("" + innerClass.getSimpleName().toLowerCase());
		xstream.alias("root", outerClass);
		xstream.alias(innerClass.getSimpleName().toLowerCase(), innerClass);
		return xstream.fromXML(xmlData);
	}

	public static Object parseXml(InputStream xmlData, Class outerClass,
			Class innerClass) {
		XStream xstream = new XStream();
		//System.out.println("" + outerClass.getSimpleName().toLowerCase());
		//System.out.println("" + innerClass.getSimpleName().toLowerCase());
		xstream.alias("root", outerClass);
		xstream.alias(innerClass.getSimpleName().toLowerCase(), innerClass);
		return xstream.fromXML(xmlData);
	}

}
