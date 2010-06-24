package edu.iu.uis.hr.tk.batch.old;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//package edu.iu.uis.hr.tk.batch;
//
//import java.io.Serializable;
//import java.net.UnknownHostException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.quartz.Job;
//import org.quartz.JobDataMap;
//import org.quartz.JobDetail;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.SimpleTrigger;
//import org.quartz.Trigger;
//import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
//import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallback;
//
//import edu.iu.uis.hr.Context;
//import edu.iu.uis.hr.service.EmailService;
//import edu.iu.uis.hr.tk.TKServiceLocator;
//import edu.iu.uis.hr.tk.employee.service.EmployeeService;
//
//
public abstract class BatchJob implements Job, Serializable{
//	private static final Logger LOG = Logger.getLogger(BatchJob.class);
//	
//	private static final long serialVersionUID = -1425964164769489737L;
//	private final String INSERT_BATCH_JOB = "insert into TK_BATCH_JOBS values(?,?,?,?,?,?)";
//	private final String ADD_EXCEPTION_TO_HEADER = "update TK_BATCH_JOBS set job_exception = ? where tk_batch_job_id = ?";
//	private static final String MAIL_REPORT_TO = "bl-uits-hrms-java-team@exchange.iupui.edu";
//	private static final String MAIL_FROM_ADDRESS = "TIME@indiana.edu";
//	private static final String MAIL_MESSAGE_LINE1 = "\n\n This batch job has kicked off with ? processes to complete.";
//	public static String DOCUMENT_AUTO_APPROVED_FYI_MSG = "Timesheet Document was auto approved by the Timekeeping System";
//	public static String DOCUMENT_AUTO_APPROVED_BY_SUPERVISOR_MSG = " during the Time Approver Batch Job. Please view the document Route Log for full approval details.";
//	public static String DOCUMENT_AUTO_APPROVED_BY_PPROCESSOR_MSG = " during the Payroll Extract Batch Job. Please view the document Route Log for full approval details.";
//	
//	private String name;
//	private Long batchId;
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void kickOffBatchJob(Object o, String type){
//		List jb = new ArrayList();
//		jb.add(o);
//		BatchJobDescription bj = new BatchJobDescription(getName(), 
//										new Timestamp(System.currentTimeMillis()), null, jb);
//	
//		long batchRunId = this.getBatchRunSequence().nextLongValue();
//		setBatchId(batchRunId);
//		insertBatchJobRecord(bj, batchRunId);
//		this.kickOffJobs(bj, 1000);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void kickOffBatchJob(List jobs, String type){
//		BatchJobDescription bj = new BatchJobDescription(getName(), 
//										new Timestamp(System.currentTimeMillis()), null, jobs);
//	
//		long batchRunId = this.getBatchRunSequence().nextLongValue();
//		setBatchId(batchRunId);
//		insertBatchJobRecord(bj, batchRunId);
//		this.kickOffJobs(bj, 1000);
//	}
//	
//	protected AbstractSequenceMaxValueIncrementer getBatchRunSequence() {
//		return new OracleSequenceMaxValueIncrementer(Context.getTKDataSource(), "TK_BATCH_JOB_SEQ");
//	}
//	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
//		try {
//			final BatchJobDescription batchJob = (BatchJobDescription)Context.getTransactionTemplate().execute(new TransactionCallback() {
//				public Object doInTransaction(TransactionStatus status) {
//					 return setupJobs();	
//				}
//			});
//			long batchRunId = this.getBatchRunSequence().nextLongValue();
//			setBatchId(batchRunId);
//			insertBatchJobRecord(batchJob, batchRunId);
//			// assume 1/8 sec to enter each job
//			kickOffJobs(batchJob, batchJob.getJobList().size() * 100);
//				
//			} catch (Exception e) {
//				LOG.error("Caught exception creating batch jobs for " + this.getClass().getName(), e);
//				final Exception e1 = e;
//				TKServiceLocator.getNontransactionDSJdbcTemplate().update(ADD_EXCEPTION_TO_HEADER, new Object[]{ StringUtils.substring(e1.getClass().getName(), 0, 49),getBatchId()});
//			}
		}
//	
//	@SuppressWarnings("unchecked")
//	public void kickOffJobs(BatchJobDescription batchDescr, long delay){
//		List jobs = batchDescr.getJobList();
//		for (Iterator iterator = jobs.iterator(); iterator.hasNext();) {
//			try {
//				Object o = iterator.next();
//				// create a new runnable for each job
//				JobDataMap jobDataMap = new JobDataMap();
//				BatchRunnable batchRunner = getBatchRunnable();
//				batchRunner.setJobDataMap(jobDataMap);
//				batchRunner.setBatchJob(this);
//				batchRunner.setObject(o);
//				batchRunner.insertJobData();
////				this.scheduleBatchRunnable(batchRunner, delay);
//				LOG.info("Put message in quartz.");
//			} catch(Exception e){
//				LOG.error("Caught exception putting job in quartz", e);
//			}
//		}
//		LOG.info("\n\n\n\n\n-----------------------\n\nFinished Loading Job " + this.getName() + "\n\n-----------------------\n\n\n\n\n");
//	}
//	
//	protected void insertBatchJobRecord(final BatchJobDescription batchJob, final long seq){
//		TKServiceLocator.getNontransactionDSJdbcTemplate().update(INSERT_BATCH_JOB, new Object[]{ seq, batchJob.getName(),
//							batchJob.getTotalJobs(), batchJob.getStartTime(), batchJob.getEndTime(),""});
//	}
//	
//	
//	protected void sendKickOffEmail(int numberDocs){
//		LOG.info("Sending email stating kickoff of " + numberDocs);
//		getEmailService().sendMail(MAIL_REPORT_TO, MAIL_FROM_ADDRESS, getMailSubject(), MAIL_MESSAGE_LINE1.replace("?", numberDocs + ""));
//	}
//	
//	protected EmailService getEmailService() {
//		return TKServiceLocator.getEmailService();
//	}
//	
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
//	
////	protected void scheduleBatchRunnable(BatchRunnable batchRunner, long delay) {
////		try {
////		JobDataMap jobDataMap = batchRunner.getJobDataMap();
////		JobDetail jobDetail = new JobDetail("Job " + this.getName() + " Batch Runner " + batchRunner.getDataId(), "TK Batch Runner", batchRunner.getClass());
////		jobDetail.setJobDataMap(jobDataMap);
////		Trigger trigger = new SimpleTrigger("Batch Runner Trigger" + batchRunner.getDataId(), "TK Batch Runner", new Date(System.currentTimeMillis() + delay));
////		trigger.setJobDataMap(jobDataMap);
////		Context.getScheduler().scheduleJob(jobDetail, trigger);
////		} catch (Exception e) {
////			LOG.error("failed to schedule job " + this.getName() + " " + batchRunner.getDataId(), e);
////			batchRunner.updateJobData(Long.valueOf(0), "W", e);
////		}
////	}
//	
//	protected String getMailSubject(){
//		return "TIME: " + edu.iu.uis.hr.Utility.splitCamelCappedString(this.getName());
//	}
//	
//	protected abstract BatchJobDescription setupJobs();
//	protected abstract String getType();
//	protected abstract BatchRunnable getBatchRunnable();
//	
//	public Long getBatchId() {
//		return batchId;
//	}
//
//	public void setBatchId(Long batchId) {
//		this.batchId = batchId;
//	}
//	
//	protected EmployeeService getEmployeeService() {
//		return TKServiceLocator.getEmployeeService();
//	}
//	
}
