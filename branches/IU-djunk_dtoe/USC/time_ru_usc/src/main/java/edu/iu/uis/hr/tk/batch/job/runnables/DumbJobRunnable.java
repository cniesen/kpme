package edu.iu.uis.hr.tk.batch.job.runnables;

import org.apache.log4j.Logger;

public class DumbJobRunnable extends AbstractTKBatchRunnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5518301584200809098L;
	private static final Logger LOG = Logger.getLogger(DumbJobRunnable.class);
	
	public String getDataId() {
		return "0001789719";
	}

	public void run() {
		throw new RuntimeException();
	}

}
