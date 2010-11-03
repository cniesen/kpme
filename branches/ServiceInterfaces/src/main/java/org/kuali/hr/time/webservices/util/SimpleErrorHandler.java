package org.kuali.hr.time.webservices.util;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

    public void error(SAXParseException exception) throws SAXParseException {
        
        System.out.println("error: "+ exception.getMessage());
        throw exception;
    }

    public void fatalError(SAXParseException exception) throws SAXParseException {
        exception.printStackTrace();
        System.out.println("fatalError: "+ exception.getMessage());
         throw exception;
    }

    public void warning(SAXParseException exception) throws SAXParseException {
        exception.printStackTrace();
        System.out.println("warning: "+ exception.getMessage());
         throw exception;
    }
}
