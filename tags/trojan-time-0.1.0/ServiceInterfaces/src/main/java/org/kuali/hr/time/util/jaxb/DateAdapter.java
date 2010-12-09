package org.kuali.hr.time.util.jaxb;

import java.sql.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
	public Date unmarshal(String val) throws Exception {
		Date result = null;
		if (val != null) {
			result = Date.valueOf(val);
		}
		return result;

	}

	public String marshal(Date val) throws Exception {
		String result = null;
		if (val != null) {
			result = val.toString();
		}
		return result;

	}
}
