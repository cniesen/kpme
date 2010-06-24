package edu.iu.uis.hr.tk.web;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public final class ErrorFilter {
	
	public static String getString(Exception e){
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
    	String stringError = stringWriter.toString();
    	IOUtils.closeQuietly(stringWriter);		
    	
		return stringError;
	}

}
