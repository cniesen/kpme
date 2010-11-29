package org.kuali.hr.time.timeblock.web;

import java.util.List;

import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * 
 * @author Jigar
 * 
 */
public class TimeBlockActionForm extends KualiForm {

	List<TimeBlock> timeBlocks;
	private static final long serialVersionUID = 1L;

	public TimeBlockActionForm() {

	}

	public List<TimeBlock> getTimeBlocks() {
		return timeBlocks;
	}

	public void setTimeBlocks(List<TimeBlock> timeBlocks) {
		this.timeBlocks = timeBlocks;
	}

}
