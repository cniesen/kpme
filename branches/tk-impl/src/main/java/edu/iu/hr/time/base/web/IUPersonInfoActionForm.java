package edu.iu.hr.time.base.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.hr.time.base.web.PersonInfoActionForm;
import org.kuali.rice.kim.bo.Person;



public class IUPersonInfoActionForm extends PersonInfoActionForm {


	private static final long serialVersionUID = 8998091705091139443L;
	
	private List<Long> payrollProcessorWorkAreas = new ArrayList<Long>();
	private Map<Long,List<Person>> workAreaToPayrollProcessorPerson = new HashMap<Long, List<Person>>();
	

	public Map<Long, List<Person>> getWorkAreaToPayrollProcessorPerson() {
		return workAreaToPayrollProcessorPerson;
	}

	public void setWorkAreaToPayrollProcessorPerson(
			Map<Long, List<Person>> workAreaToPayrollProcessorPerson) {
		this.workAreaToPayrollProcessorPerson = workAreaToPayrollProcessorPerson;
	}

	public List<Long> getPayrollProcessorWorkAreas() {
		return payrollProcessorWorkAreas;
	}

	public void setPayrollProcessorWorkAreas(List<Long> payrollProcessorWorkAreas) {
		this.payrollProcessorWorkAreas = payrollProcessorWorkAreas;
	}
   

}
