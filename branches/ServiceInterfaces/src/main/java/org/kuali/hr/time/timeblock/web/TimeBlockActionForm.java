package org.kuali.hr.time.timeblock.web;

import java.util.List;

import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class TimeBlockActionForm extends KualiForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fileName;
	List<TimeBlock> timeBlocks;
	

	public List<TimeBlock> getTimeBlocks() {
		return timeBlocks;
	}

	public void setTimeBlocks(List<TimeBlock> timeBlocks) {
		this.timeBlocks = timeBlocks;
	}

	public TimeBlockActionForm() {
		super();
	}

	public TimeBlockActionForm(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
