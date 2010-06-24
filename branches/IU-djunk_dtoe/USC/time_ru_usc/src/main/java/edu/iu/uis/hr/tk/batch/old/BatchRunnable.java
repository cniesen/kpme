package edu.iu.uis.hr.tk.batch.old;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.tk.TKServiceLocator;

public abstract class BatchRunnable implements Job {

	private static final Logger LOG = Logger.getLogger(BatchRunnable.class);

	private final String IS_JOB_DONE = "Select total_jobs from TK_BATCH_JOBS a where a.TK_BATCH_JOB_ID = ? " + "and a.TOTAL_JOBS = (select count(data_id) from tk_batch_job_entries "
			+ "where TK_BATCH_JOB_ID = ? and (JOB_STATUS = 'F' or JOB_STATUS = 'E'))";
	private final String MARK_BATCH_JOB_COMPLETE = "update TK_BATCH_JOBS set end_time = ? where tk_batch_job_id = ?";

//	private Object object;
//	private BatchJob batchJob;
//	private Long jobId;
	private transient JobDataMap jobDataMap;
	
	
	private static final long serialVersionUID = -929671501179849777L;
	
	public BatchRunnable() {
	}
	
//	public BatchRunnable(JobDataMap jobDataMap) {
//		setJobDataMap(jobDataMap);
//	}
	
	public void setJobDataMap(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}
	
	public JobDataMap getJobDataMap() {
		return this.jobDataMap;
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.setJobDataMap(context.getMergedJobDataMap());
		updateJobData(Long.valueOf(0), "R", null);
		Long totalTime = 0L;
		try {
			long startTime = System.currentTimeMillis();
			long endTime = 0;
			try {
				Context.getTransactionTemplate().execute(new TransactionCallbackWithoutResult() {

					@Override
					protected void doInTransactionWithoutResult(TransactionStatus status) {
						try {
							doWork();
						} catch (Exception e) {
							LOG.error("Caught exception processing batch job in " + this.getClass().getName(), e);
							throw new RuntimeException("Caught exception processing batch job in " + this.getClass().getName(), e);
						}
					}
				});

			} finally {
				endTime = System.currentTimeMillis();
			}

			totalTime = endTime - startTime;
			updateJobData(totalTime, "F", null);
		} catch (Exception e) {
			LOG.error("Caught Exception processing job", e);
			updateJobData(totalTime, "E", e);
		} finally {
			Context.getStorageMap().clear();
		}
	}

	private final String OBJECT_KEY = "object";
	public Object getObject() {
		return this.getJobDataMap().get(OBJECT_KEY);
	}

	public void setObject(Object object) {
		this.getJobDataMap().put(OBJECT_KEY, object);
	}

	public Long getBatchId() {
		throw new UnsupportedOperationException();
//		return this.getBatchJob().getBatchId();
	}

	protected static final String ADD_JOB_SQL = "insert into TK_BATCH_JOB_ENTRIES values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

//	protected void insertJobData() {
//
//		//failure case and the bus is retrying our job
//		if (this.getJobId() != null) {
//			return;
//		}
//		
//		this.setJobId(this.getJobSequence().nextLongValue());
//		String ipAddress = "";
//		try {
//			ipAddress = java.net.InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e) {
//			ipAddress = "unknown";
//		}
//		TKServiceLocator.getNontransactionDSJdbcTemplate().update(ADD_JOB_SQL,
//				new Object[] { getJobId(), getDataId() == null ? "NONE" : getDataId(), ipAddress, Long.valueOf(0), Long.valueOf(getBatchId()), "S", getBatchJob().getType(), StringUtils.substring(getJobMessage(), 0, 3999), "" });
//
//	}

	public static final String UPDATE_JOB_SQL = "update TK_BATCH_JOB_ENTRIES set ip_net = ?, total_time = ?, job_status = ?, job_message = ?, exception = ? where tk_job_id = ?";

	protected void updateJobData(final Long totalTime, final String jobStatus, Exception e) {
		
		//failure case and the bus is retrying our job
		try {
			if (this.getJobId() == null) {
//				insertJobData();
			}
			
			
			String exceptionMessage = "";
			if (e != null) {
				StringWriter stringWriter = new StringWriter();
				e.printStackTrace(new PrintWriter(stringWriter));
				exceptionMessage += stringWriter.toString();
			}
			
			String ipAddress = "";
			try {
				ipAddress = java.net.InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e1) {
				ipAddress = "unknown";
			}
			
			TKServiceLocator.getNontransactionDSJdbcTemplate().update(UPDATE_JOB_SQL, new Object[] {ipAddress, totalTime, jobStatus, StringUtils.substring(getJobMessage(), 0, 3999), StringUtils.substring(exceptionMessage, 0, 3999), this.getJobId() });
			isJobFinished();
		} catch (Exception ce) {
			LOG.error("Caught Exception updating batch job row", e);
		}
	}

	public boolean isJobFinished() {
		int cnt = 0;
		SqlRowSet sqlRowSet = TKServiceLocator.getNontransactionDSJdbcTemplate().queryForRowSet(IS_JOB_DONE, new Object[] { getBatchId(), getBatchId() });
		while (sqlRowSet.next()) {
			cnt = new Integer(sqlRowSet.getString("TOTAL_JOBS"));
		}
		if (cnt > 0) {
			TKServiceLocator.getNontransactionDSJdbcTemplate().update(MARK_BATCH_JOB_COMPLETE, new Object[] { new Timestamp(System.currentTimeMillis()), getBatchId() });
			LOG.info("\n\n\n\n\n\n\nJob " + this.getJobId() + " finished.\n\n\n\n\n\n\n\n\n");
			return true;
		}
		LOG.info("Job " + this.getJobId() + " not finished.");
		return false;
	}

	protected abstract void doWork() throws Exception;

	
	private static final String BATCH_JOB_KEY = "batchJob";
	public BatchJob getBatchJob() {
		return (BatchJob)this.getJobDataMap().get(BATCH_JOB_KEY);
	}

	public void setBatchJob(BatchJob batchJob) {
		this.getJobDataMap().put(BATCH_JOB_KEY, batchJob);
	}

	
	private static final String JOB_ID_KEY = "JOBiDKEY";
	public Long getJobId() {
		return (Long)this.getJobDataMap().get(JOB_ID_KEY);
	}

	public void setJobId(Long jobId) {
		this.getJobDataMap().put(JOB_ID_KEY, jobId);
	}

	protected AbstractSequenceMaxValueIncrementer getJobSequence() {
		return new OracleSequenceMaxValueIncrementer(Context.getTKDataSource(), "tk_batch_job_pk_seq");
	}

	protected abstract String getDataId();
	protected String getJobMessage() {
		return "";
	}
}
