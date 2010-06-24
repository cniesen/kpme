package edu.iu.uis.hr.tk.batch;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.tk.TKServiceLocator;

public class TKMessageQueueChecker extends Thread {

	
	private static final Logger LOG = Logger.getLogger(TKMessageQueueChecker.class);
	
	private static final String EXCEPTIONS_IN_QUEUE_SQL = "select MESSAGE_QUE_ID,MESSAGE_QUE_IP_NBR, VAL_ONE, VAL_TWO from EN_MSG_QUE_T " +
			"where MESSAGE_QUE_STAT_CD = 'E' and MESSAGE_SERVICE_NM like '{TK}timesheetService'";
	
	private int secondsSleep;
	
	@Override
	public void run() {
	
		LOG.info("Message Queue Checker Thread is running");
		
		try {
			//give app time to start up...
			sleep(30000);
		} catch (InterruptedException e) {
			LOG.warn("Caught interrupt exception 1", e);
		} 

		while (true) {
			try {
				sleep(secondsSleep * 1000);
			} catch (InterruptedException e) {
				LOG.warn("Caught interrupt exception", e);
			}

			int count = 0;
			SqlRowSet sqlRowSet = TKServiceLocator.getTkJdbcTemplate().queryForRowSet(EXCEPTIONS_IN_QUEUE_SQL);

			if (!sqlRowSet.isLast()){
				String notification = "Your Attention is Requested.\n\nThe following async timesheets were found in the message queue:\n\n";
				while (sqlRowSet.next()) {
					count++;
					notification  +=  sqlRowSet.getString("VAL_ONE") + " " + sqlRowSet.getString("VAL_TWO") + " (ip:" + sqlRowSet.getString("MESSAGE_QUE_IP_NBR") + ", recordId:" + sqlRowSet.getString("MESSAGE_QUE_ID") + ")\n\n";
				}
				notification += "\n" + Constants.MAIL_SIGNATURE;
				sendMessage(count, notification);
					//wait for 20 minutes if we've sent a positive to reduce spam
					try {
						sleep(20 * 60 * 1000);
					} catch (InterruptedException e) {
						LOG.warn("Caught interrupt exception 2", e);
					}
			}			
			
		}
	}
	
	protected void sendMessage(int count, String notification) {
		TKServiceLocator.getEmailService().sendMail(Constants.MAIL_REPORT_TO, Constants.MAIL_REPORT_FROM, "TIME: " +  count + " Async Timesheets found in Message Queue!!", notification);
	}
	
	public int getSecondsSleep() {
		return secondsSleep;
	}

	public void setSecondsSleep(int secondsSleep) {
		this.secondsSleep = secondsSleep;
	}	
}