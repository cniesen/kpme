package edu.iu.uis.hr.tk.entity;

import java.util.Date;

import org.apache.log4j.Logger;

public class Preference extends AbstractPreference {
	private static final Logger LOG = Logger.getLogger(Preference.class);

	public Preference() {
		super();
	}
	
	public Preference(String preferenceCode, Date effectiveDate) {
		this();
		setEffectiveDate(effectiveDate);
		setPreferenceCode(preferenceCode);
		setRole(edu.iu.uis.hr.tk.directory.Utility.EMPLOYEE_ROLE);
	}
	
	public int compareTo(Object arg0, Object arg1) {
		Preference p0 = (Preference)arg0;
		Preference p1 = (Preference)arg1;
		return p0.getDescription().compareTo(p1.getDescription());
	}
	
}

