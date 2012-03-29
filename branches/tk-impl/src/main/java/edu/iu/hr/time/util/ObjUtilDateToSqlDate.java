package edu.iu.hr.time.util;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;


public class ObjUtilDateToSqlDate implements org.apache.ojb.broker.accesslayer.conversions.FieldConversion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object javaToSql(Object arg0) throws ConversionException {
		if(arg0 instanceof java.util.Date){
			java.util.Date origDate = (java.util.Date)arg0;
			java.sql.Date dt = new java.sql.Date(origDate.getTime());
			return dt;
		}
		return arg0;
	}

	@Override
	public Object sqlToJava(Object arg0) throws ConversionException {
		return arg0;
	}
	

}
