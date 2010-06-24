package edu.iu.uis.hr.tk.web;

import java.util.Collections;
import java.util.List;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;

public class LogoutForm extends AbstractStrutsActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6244153836255222610L;

	public void prepare() {
	}

	@SuppressWarnings("unchecked")
	public List getAccessAuthorizations() {
		return Collections.EMPTY_LIST;
	}

}
