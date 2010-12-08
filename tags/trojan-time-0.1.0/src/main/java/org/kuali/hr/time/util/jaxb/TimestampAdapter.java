package org.kuali.hr.time.util.jaxb;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(Timestamp v) throws Exception {
		return v == null ? null : String.valueOf(v.getTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public Timestamp unmarshal(String v) throws Exception {
		// if (v == null)
		// return null;
		// else
		// return new Timestamp(Long.parseLong(v));
		return v == null ? null : new Timestamp(Long.parseLong(v));
	}

}
