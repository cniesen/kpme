package edu.iu.uis.hr.tk.batch;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.config.ConfigContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.job.runnables.PayrollExtractBatchRunnable;
import edu.iu.uis.hr.tk.io.scp.Scp;

public class TKThreadPoolManager implements InitializingBean, DisposableBean {
	
	private static final Logger LOG = Logger.getLogger(TKThreadPoolManager.class);
	
	private final String COUNT_OF_JOB_ERRORS = "SELECT count(*) FROM tk_batch_job_entries where job_status = 'E' and TK_BATCH_JOB_ID = ?";
	private final String COUNT_OF_MESSAGES_SENT_TODAY = "select count(*) from tk_message_history where message_ref_id = ? and message_date >= ? and message_date < ?";
	private final String INSERT_MESSAGE_HISTORY = "INSERT INTO tk_message_history (message_history_id, message_type, message_date, message_ref_id) VALUES (TK_MSG_HIST_ID_SEQ.NEXTVAL, ?, ?, ?)";
	
	private final String IS_JOB_DONE = "Select total_jobs from TK_BATCH_JOBS a where a.TK_BATCH_JOB_ID = ? " + "and a.TOTAL_JOBS = (select count(data_id) from tk_batch_job_entries "
	+ "where TK_BATCH_JOB_ID = ? and (JOB_STATUS = 'F' or JOB_STATUS = 'E'))";
	private final String MARK_BATCH_JOB_COMPLETE = "update TK_BATCH_JOBS set end_time = ? where tk_batch_job_id = ?";
	private final String IS_BATCH_JOB_MARKED_COMPLETE = "select end_time FROM TK_BATCH_JOBS where tk_batch_job_id = ?";
	
	public static final String UPDATE_JOB_SQL = "update TK_BATCH_JOB_ENTRIES set ip_net = ?, total_time = ?, job_status = ?, job_message = ?, exception = ? where tk_job_id = ?";
	
	private final String JOB_SUMMARY = "select count(TK_BATCH_JOB_ID) STATUS_COUNT, JOB_STATUS from TK_BATCH_JOB_ENTRIES where TK_BATCH_JOB_ID=? group by job_status order by JOB_STATUS";
	private final String EXCEPCION_JOB_MESSAGES = "select DATA_ID, EXCEPTION from TK_BATCH_JOB_ENTRIES where TK_BATCH_JOB_ID= ?  AND JOB_STATUS='E' AND ROWNUM <= 500 order by TK_JOB_ID";
	
	public static final String MESSAGE_QUEUE_CHECKER_IP_PARAM = "tk.message.queue.check.ip";
		
	private ScheduledThreadPoolExecutor threadPoolExecutor;
	private TKBatchPollerThread tkBatchPollerThread;
	private TKMessageQueueChecker tkMessageQueueChecker;

	public void destroy() throws Exception {
		tkBatchPollerThread.join();
		threadPoolExecutor.shutdown();
	}
	
	public void afterPropertiesSet() throws Exception {
		threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
		tkBatchPollerThread = new TKBatchPollerThread(this, 1);
		tkBatchPollerThread.start();
		if (isMessageQueueCheckMachine()) {
			tkMessageQueueChecker = new TKMessageQueueChecker();
			tkMessageQueueChecker.setSecondsSleep(3);
			tkMessageQueueChecker.start();
		}
	}
	
	public boolean isEmpty() {
		return this.threadPoolExecutor.getQueue().isEmpty();
	}
	
	public int getQueueSize() {
		return this.threadPoolExecutor.getQueue().size();
	}
	
	public void executeRunnable(final TKBatchRunnable tkBatchRunnable) {
		
		//this is a hack the tkbatchrunnable should have an abstract class
		// that is doing this work, right now a runnable is being stuffed into a runnable
		this.threadPoolExecutor.schedule(new Runnable() {

			public void run() {
				try {
					updateJobData(tkBatchRunnable, Long.valueOf(0), "R", null);
				} catch (Throwable t) {
					LOG.error("Caught Exception updating job data", t);
					return;
				}
				
				Long totalTime = 0L;
				long startTime = System.currentTimeMillis();
				long endTime = 0;
				
				try {
					
					//wrap in transaction 
					//Context.getTransactionTemplate().execute(action)
					
					Context.getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus status) {
						tkBatchRunnable.run();
					    }
					});
					//tkBatchRunnable.run();
					
					endTime = System.currentTimeMillis();
					totalTime = endTime - startTime;
					updateJobData(tkBatchRunnable, totalTime, "F", null);
				} catch (Throwable t) {
					LOG.error("Caught Exception running job ", t);
					endTime = System.currentTimeMillis();
					totalTime = endTime - startTime;
					updateJobData(tkBatchRunnable,totalTime, "E", t);
				}
			}			
		}, 3, TimeUnit.MILLISECONDS);
		
	}
	
	protected void updateJobData(TKBatchRunnable tkBatchRunnable, Long totalTime, String jobStatus, Throwable t) {
		
		//failure case and the bus is retrying our job
		try {
			
			
			String exceptionMessage = "";
			if (t != null) {
				StringWriter stringWriter = new StringWriter();
				t.printStackTrace(new PrintWriter(stringWriter));
				exceptionMessage += stringWriter.toString();
			}
			
			String ipAddress = "";
			try {
				ipAddress = java.net.InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e1) {
				ipAddress = "unknown";
			}
			
			TKServiceLocator.getNontransactionDSJdbcTemplate().update(
					UPDATE_JOB_SQL, new Object[] {ipAddress, totalTime, jobStatus, 
							StringUtils.substring(tkBatchRunnable.getJobMessage(), 0, 3999), 
							StringUtils.substring(exceptionMessage, 0, 3999), tkBatchRunnable.getJobId() });
			
			isJobFinished(tkBatchRunnable);
		} catch (Exception ce) {
			LOG.error("Caught Exception updating batch job row", t);
		}
	}
	
	public Boolean isJobFinished(TKBatchRunnable tkBatchRunnable) {
		Integer cnt = 0;		
		
		//this.checkForErrorsAndSendText(tkBatchRunnable);
		
		SqlRowSet sqlRowSet = TKServiceLocator.getNontransactionDSJdbcTemplate().queryForRowSet(IS_JOB_DONE, new Object[] { tkBatchRunnable.getBatchId(), tkBatchRunnable.getBatchId() });
		while (sqlRowSet.next()) {
			cnt = new Integer(sqlRowSet.getString("TOTAL_JOBS"));
		}
		
		if (cnt > 0) {
			sqlRowSet = TKServiceLocator.getNontransactionDSJdbcTemplate().queryForRowSet(IS_BATCH_JOB_MARKED_COMPLETE, new Object[] { tkBatchRunnable.getBatchId() });
			Timestamp endTime = null;
			if (sqlRowSet.next()){
				endTime = sqlRowSet.getTimestamp("END_TIME");
			}
			
			if (endTime == null) {
				TKServiceLocator.getNontransactionDSJdbcTemplate().update(MARK_BATCH_JOB_COMPLETE, new Object[] { new Timestamp(System.currentTimeMillis()), tkBatchRunnable.getBatchId() });				
			    sendBatchResultsEmail(tkBatchRunnable);
				  
				  //TODO: Make sure runScpToKickoffExtract() is executed only once before enabling it.
				  if (tkBatchRunnable instanceof PayrollExtractBatchRunnable) {
					//runScpToKickoffExtract();
				  }			  
				  LOG.info("\n\n\n\n\n\n\nJob " + tkBatchRunnable.getJobId() + " finished.\n\n\n\n\n\n\n\n\n");
				  return Boolean.TRUE;
			}
		}
		LOG.info("Job " + tkBatchRunnable.getJobId() + " not finished.");
		return Boolean.FALSE;
	}
	
	
	public static void runScpToKickoffExtract() {
		LOG.warn("Preparing to trigger extract via scp...");
		@SuppressWarnings("unused")
		Scp scp = new Scp();
		scp.triggerExtract();
		LOG.warn("Returned from scp.");
	}

	public boolean isMessageQueueCheckMachine() {
		return StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty(MESSAGE_QUEUE_CHECKER_IP_PARAM), TKBatchPollerThread.getIPNumber()); 
	}
	
//	protected void checkForErrorsAndSendText(TKBatchRunnable tkBatchRunnable){
//		Integer errorCount = TKServiceLocator.getNontransactionDSJdbcTemplate().queryForInt(COUNT_OF_JOB_ERRORS, new Object[] { tkBatchRunnable.getBatchId() });
//		Date now = new Date(System.currentTimeMillis());
//		Date tomorrow = new TimelessDate(now).addDays(1).getDate();
//		Date today = new TimelessDate(now).getDate();
//		Integer messagesSentToday = TKServiceLocator.getNontransactionDSJdbcTemplate().queryForInt(COUNT_OF_MESSAGES_SENT_TODAY, new Object[] { tkBatchRunnable.getBatchId(), today, tomorrow });
//		if ((Constants.BATCH_RUNNABLES_TO_TRACK_ERRORS.contains(tkBatchRunnable.getClass().toString())) 
//				&& (errorCount > Constants.ERROR_THRESHOLD_FOR_TXTMSG) 
//				&& (messagesSentToday == 0)){
//			try {
//				this.sendTextMessage(tkBatchRunnable);
//				TKServiceLocator.getNontransactionDSJdbcTemplate().update(INSERT_MESSAGE_HISTORY, new Object[] {Constants.TEXT_MESSAGE, today, tkBatchRunnable.getBatchId()});
//			}catch (Exception e){
//				LOG.debug("Error sending text message from TKThreadPoolManager: " + e);
//			}			
//		}
//	}
	
//	protected void sendTextMessage(TKBatchRunnable tkBatchRunnable) throws Exception {
//		LOG.warn("Error threshold("+Constants.ERROR_THRESHOLD_FOR_TXTMSG+") exceeded for Job Id/Batch Id(" + tkBatchRunnable.getJobId() + "/" + tkBatchRunnable.getBatchId() + ")");
//		ArrayList<String> mailTo = new ArrayList<String>();
//		if (StringUtils.equalsIgnoreCase(Context.PRODUCTION_ENVIRONMENT, ConfigContext.getCurrentContextConfig().getProperty("environment"))) {
//			mailTo.addAll(Constants.EMAILS_FOR_TXTMSG);
//		} else {
//			mailTo.addAll(Constants.EMAILS_FOR_TXTMSG);
//		}
//		TKServiceLocator.getEmailService().send(mailTo, Constants.MAIL_REPORT_FROM, getMailSubject(tkBatchRunnable.getClass().getSimpleName())+ ": Job Error Threshold Exceeded",  getJobSummary(tkBatchRunnable.getBatchId()),null,null);
//	}
	
	protected void sendBatchResultsEmail(TKBatchRunnable tkBatchRunnable) {
		//TKServiceLocator.getEmailService().sendMail(TKBatchJobPopulator.MAIL_REPORT_TO, );
		ArrayList<String> mailTo = new ArrayList<String>();
		mailTo.add(Constants.MAIL_REPORT_TO);
		if (StringUtils.equalsIgnoreCase(Context.PRODUCTION_ENVIRONMENT, ConfigContext.getCurrentContextConfig().getProperty("environment"))) {
			mailTo.add("fmscustr@indiana.edu");
		}
		try {
		TKServiceLocator.getEmailService().send(mailTo, Constants.MAIL_REPORT_FROM, getMailSubject(tkBatchRunnable.getClass().getSimpleName())+ ": Job Summary",  getJobSummary(tkBatchRunnable.getBatchId()),null,null);
		}catch (Exception e){
			LOG.debug("Error sending email from TKThreadPoolManager: " + e);
		}
	}	
	
	protected String getMailSubject(String className){
		return "TIME: " + edu.iu.uis.hr.Utility.splitCamelCappedString(className);
	}
	
	protected String getJobSummary(Long batchId){
		String messageSummary = "\n\nTIME Batch Job Id: " + batchId;
		messageSummary += "\n\nStatus:Count\n\n";
		SqlRowSet sqlRowSet = TKServiceLocator.getNontransactionDSJdbcTemplate().queryForRowSet(JOB_SUMMARY, new Object[] { batchId });
		while (sqlRowSet.next()) {
			messageSummary  +=  sqlRowSet.getString("JOB_STATUS")+":"+sqlRowSet.getString("STATUS_COUNT")+"\n";
		}
		
		sqlRowSet = TKServiceLocator.getNontransactionDSJdbcTemplate().queryForRowSet(EXCEPCION_JOB_MESSAGES, new Object[] { batchId });
		if (!sqlRowSet.isLast()){
			messageSummary  +=  "\n\nExceptions found:\n\n";
			while (sqlRowSet.next()) {
				messageSummary  +=  "[" + sqlRowSet.getString("DATA_ID") + "] " + StringUtils.substring(sqlRowSet.getString("EXCEPTION"),0,150) + "\n\n\n";
			}
		}
		messageSummary += "\n" + Constants.MAIL_SIGNATURE;
		return messageSummary;
	}

}