package edu.iu.uis.hr.tk.batch;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.config.ConfigContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.lob.OracleLobHandler;

import edu.iu.uis.hr.Constants;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.TKServiceLocator;

public abstract class TKBatchJobPopulator implements Job {
	
	private static final Logger LOG = Logger.getLogger(TKBatchJobPopulator.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			List<TKBatchRunnable> tkBatchRunnables = this.getTKBatchRunnables();
			Long batchRunId = getBatchRunSequence().nextLongValue();
//			sendKickOffEmail(tkBatchRunnables.size());
			insertBatchJobRecord(batchRunId, tkBatchRunnables.size());
			for (TKBatchRunnable batchRunnable : tkBatchRunnables) {
				batchRunnable.setBatchId(batchRunId);
				insertJobData(batchRunnable);
			}
				
		} catch (Exception e) {
			LOG.error("Caught exception creating batch jobs for " + this.getClass().getName(), e);
//			final Exception e1 = e;
//			TKServiceLocator.getNontransactionDSJdbcTemplate().
//				update(ADD_EXCEPTION_TO_HEADER, new Object[]{ 
//						StringUtils.substring(e1.getClass().getName(), 0, 49),
//						getBatchId()});
		}
	}

	private final String INSERT_BATCH_JOB = "insert into TK_BATCH_JOBS values(?, ?, ?, ?, ?, ?)";
	
	protected void insertBatchJobRecord(Long batchRunId, Integer count){
		TKServiceLocator.getNontransactionDSJdbcTemplate().update(INSERT_BATCH_JOB, new Object[]{ 
				batchRunId, 
				this.getName(),
				count, 
				new Timestamp(System.currentTimeMillis()),
				null,
				""});
	}	
	
	
	protected static final String INSERT_JOB_SQL = "INSERT INTO TK_BATCH_JOB_ENTRIES(" +
				"TK_JOB_ID, " +
				"DATA_ID, " +
				"IP_NET, " +
				"TOTAL_TIME, "  +
				"TK_BATCH_JOB_ID, " +
				"JOB_STATUS, " +
				"JOB_TYPE, " +
				"JOB_MESSAGE, " +
				"EXCEPTION, " +
				"SERIALIZED_RUNNABLE) " + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	protected void insertJobData(TKBatchRunnable tkBatchRunnable) {
	
			tkBatchRunnable.setJobId(this.getJobSequence().nextLongValue());
			String ipNumber = this.getRoundRobinIPAddress();
			
			SqlLobValue serializedRunnableParam = new SqlLobValue(SerializationUtils.serialize(tkBatchRunnable), new OracleLobHandler());
			
			TKServiceLocator.getTkJdbcTemplate().update(
					INSERT_JOB_SQL, new Object[] { 
							tkBatchRunnable.getJobId(), 
							tkBatchRunnable.getDataId() == null ? "NONE" : tkBatchRunnable.getDataId(), 
							ipNumber, 
							Long.valueOf(0), 
							tkBatchRunnable.getBatchId(), 
							"S", 
							this.getType(), 
							StringUtils.substring(tkBatchRunnable.getJobMessage(), 0, 3999), 
							"",
							serializedRunnableParam
							},
							new int[] { 
							Types.BIGINT, //TK_JOB_ID
							Types.CHAR, //DATA_ID
							Types.CHAR, //IP_NET
							Types.BIGINT, //TOTAL_TIME
							Types.BIGINT, //TK_BATCH_JOB_ID
							Types.CHAR, //JOB_STATUS
							Types.CHAR, //JOB_TYPE
							Types.CHAR, //JOB_MESSAGE
							Types.CHAR, //EXCEPTION
							Types.BLOB //SERIALIZED_RUNNABLE
							}
					);
	
		}
	
	protected AbstractSequenceMaxValueIncrementer getBatchRunSequence() {
		return new OracleSequenceMaxValueIncrementer(Context.getTKDataSource(), "TK_BATCH_JOB_SEQ");
	}
	
	protected AbstractSequenceMaxValueIncrementer getJobSequence() {
		return new OracleSequenceMaxValueIncrementer(Context.getTKDataSource(), "tk_batch_job_pk_seq");
	}
		
	private int lastPlace = 0;
	
	protected String getRoundRobinIPAddress() {
//		try {
			String clusterIps = ConfigContext.getCurrentContextConfig().getProperty("cluster.ips");
			String[] ips = StringUtils.split(clusterIps, ",");
			String ip = ips[lastPlace++];
			if (lastPlace >= ips.length) {
				lastPlace = 0;
			}
			return ip;
//		} catch (UnknownHostException e) {
//			return "unknown";
//		}
	}

	// these are already in edu.iu.uis.hr.Constants - rpiercy
//	public static final String MAIL_REPORT_TO = "bl-uits-hrms-java-team@exchange.iu.edu";
//	public static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
	private static final String MAIL_MESSAGE_LINE1 = "\n\n This batch job has kicked off with ? processes to complete.";	
	
	protected void sendKickOffEmail(int numberDocs) {
		LOG.info("Sending email stating kickoff of " + numberDocs);
		getEmailService().sendMail(Constants.MAIL_REPORT_TO, Constants.MAIL_REPORT_FROM, getMailSubject(), MAIL_MESSAGE_LINE1.replace("?", numberDocs + ""));
	}

	protected String getMailSubject(){
		return "TIME: " + edu.iu.uis.hr.Utility.splitCamelCappedString(this.getName());
	}
	
	protected EmailService getEmailService() {
		return TKServiceLocator.getEmailService();
	}
	
	public abstract List<TKBatchRunnable> getTKBatchRunnables();
	public abstract String getName();
	public abstract String getType();
	
}
