package edu.iu.uis.hr.tk.log.web;

import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.tk.client.AdministrativeAccessAuthorization;


public class TKPerformInfoForm extends AbstractStrutsActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4858804150178570099L;
	
	private String method;
	
	public void prepare() {
		// TODO Auto-generated method stub
		
	}

	public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(AdministrativeAccessAuthorization.class);
        return accessAuthorizations;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}