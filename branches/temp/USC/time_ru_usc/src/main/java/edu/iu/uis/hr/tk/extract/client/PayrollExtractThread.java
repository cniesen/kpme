package edu.iu.uis.hr.tk.extract.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.directory.service.WebUserService;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.extract.entity.PayEarnings;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.entity.PayrollInterface;
import edu.iu.uis.hr.tk.extract.service.PayrollExtractService;
import edu.iu.uis.hr.tk.io.scp.Scp;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.report.dataaccess.BatchMessageDataAccess;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.entity.logic.IsReadyToApproveLogic;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class PayrollExtractThread implements Runnable {
    private static final int NUMBER_OF_THREADS = 10;
    private static final int MAX_APPROVAL_ATTEMPTS = 3;
	private static final Logger LOG = Logger.getLogger(PayrollExtractThread.class);
	private PayrollExtractSchedule payrollExtractSchedule;
	private static final String STATUS_COMPLETE = "C";
	private static final String STATUS_IN_PROGRESS = "I";
	private static final String WORKFLOW_SUPER_USER = "timekeeping-system";
	private static final String PROCESS_NAME = BatchMessageSearchCriteria.FINALIZE_TIMESHEETS_PROCESS_NAME;

    private static final String MAIL_REPORT_TO="bl-uits-hrms-java-team@exchange.iupui.edu";
    private static final String MAIL_REPORT_FROM="TIME@indiana.edu";
    private static final String MAIL_SUBJECT="TIME Payroll Extract Report";
	
	private HttpServletRequest request;
	private WebUserService webUserService;
	private EmailService emailService;
	private JobService jobService;
	private TimesheetService timesheetService;
	private PayrollExtractService payrollExtractService;
	private BatchMessageDataAccess batchMessageDataAccess;
	
	private List programReport = new ArrayList();

	public PayrollExtractThread() {
		super();
	}

	public PayrollExtractThread(PayrollExtractSchedule payrollExtractSchedule, HttpServletRequest request) {
		this.payrollExtractSchedule = payrollExtractSchedule;
		this.request = new HttpServletRequestWrapper(request);
	}

	public void run() {
        
		LOG.warn("Started run() Method");

		getTimesheetService().takeSnapshot(payrollExtractSchedule.getPayEndDate());
		
		try {
			
			int numThreads = NUMBER_OF_THREADS;
	        initializeTkOJB();
			
			getWebUserService().setUserSession(request);
			payrollExtractSchedule.setActualBeginTime(new Timestamp());
			setExtractStatus(STATUS_IN_PROGRESS);
			
			List documentHeaders = getTimesheetService().geDocumentHeaders(payrollExtractSchedule.getPayEndDate());
            
			programReport.add("Number of headers to process: " + documentHeaders.size());
			
	        List[] dividedDocumentHeaders = divideList(numThreads, documentHeaders);
	        Thread[] threads = new Thread[numThreads];
	        for (int index = 0; index < threads.length; index++) {
	        	threads[index] = new Thread(new DocumentApprover(dividedDocumentHeaders[index]));
	        }
	        for (int index = 0; index < threads.length; index++) {
	        	threads[index].start();
	        }
	        for (int index = 0; index < threads.length; index++) {
	        	try {
	        		threads[index].join();
	        	} catch (InterruptedException e) {
	        		LOG.error("Thread number " + (index+1) + " was interrupted!");
	        	}
	        }
	        
			setExtractStatus(STATUS_COMPLETE);

			programReport.add("Done");
            
			submitReport();

		} catch (Exception e) {
			// send email for any exception
			LOG.error(e.getMessage(), e);
			// getEmailService().sendMail(e, LOG);
		}
		
		LOG.warn("Preparing to trigger extract via scp...");
		Scp scp = new Scp();
		scp.triggerExtract();
		LOG.warn("Returned from scp.");
		
		LOG.warn("Finished run() Method");
	}

	private void submitReport() {
		String reportContent = "";
		for (Iterator programReportIterator = programReport.iterator(); programReportIterator.hasNext();) {
			reportContent += (String) programReportIterator.next() + "\n \n";
		}
		emailService.sendMail(MAIL_REPORT_TO, MAIL_REPORT_FROM, MAIL_SUBJECT, reportContent);
		LOG.info(reportContent);
	}
	
	
    private class DocumentApprover implements Runnable {
    	
    	private final List documentHeaders;
    	int retryCount = 0;
    	
    	public DocumentApprover(List documentHeaders) {
    		this.documentHeaders = documentHeaders;
    	}
    	
    	public void run() {
    		IsReadyToApproveLogic logic = (IsReadyToApproveLogic)Context.getLogic(IsReadyToApproveLogic.class);
    		while (retryCount++<MAX_APPROVAL_ATTEMPTS) {
    			LOG.error("Start approval attempt " + retryCount + " of " + MAX_APPROVAL_ATTEMPTS + " attempts.");
    			LOG.error("Documents to approve in this attempt : " + documentHeaders.size());
	    		for (Iterator iter = documentHeaders.iterator(); iter.hasNext();) {
    				DocumentHeader documentHeader = (DocumentHeader) iter.next();	    				
	    			try {
	    				boolean finalApproved = getTimesheetService().isFinalApproved(WORKFLOW_SUPER_USER, documentHeader.getDocumentId());
	    				if (!finalApproved && logic.isReadyToApprove(documentHeader.getDocumentId())) {
	    					try {
	    						TimesheetDocument td = new TimesheetDocument(documentHeader);
	    						td.setJobs(getJobService().getJobsWithAssignments(documentHeader.getUniversityId(), documentHeader.getPayEndDate()));
	    						getTimesheetService().approve(td, WORKFLOW_SUPER_USER);
	    						finalApproved = getTimesheetService().isFinalApproved(WORKFLOW_SUPER_USER, documentHeader.getDocumentId());
	    					} catch (Exception e) {
	    						LOG.error("auto approve error " + e.getMessage());
	    						if (retryCount==MAX_APPROVAL_ATTEMPTS) {
	    							programReport.add("auto approve error " + e.getMessage());
	    							BatchMessage batchMessage = new BatchMessage(null,PROCESS_NAME,new Date(),documentHeader.getDocumentId(),documentHeader.getUniversityId(),"auto approve error " + e.getMessage());
	    							getBatchMessageDataAccess().store(batchMessage);
	    						}
	    					}
	    				}

	    				if (finalApproved) {
	    					iter.remove();
	    				}
	    			} catch (Exception e) {
	    				LOG.error("an exception was raised " + e.getMessage());
	    				programReport.add("an exception was raised " + e.getMessage());
						BatchMessage batchMessage = new BatchMessage(null,PROCESS_NAME,new Date(),documentHeader.getDocumentId(),documentHeader.getUniversityId(),"an exception was raised " + e.getMessage());
						getBatchMessageDataAccess().store(batchMessage);
	    			}
	    		}
    		}
    		LOG.info("Finished processExtract() Method");
    	}
    	
    	private void exportData(Collection timeBlocks, Collection empPayEarnings) {
    		TypedPersistentMaintainedEntityList payrollInterfaces = new TypedPersistentMaintainedEntityList(PayrollInterface.class);
    		// calculate hours
    		for (Iterator iter = timeBlocks.iterator(); iter.hasNext();) {
    			TimeBlock timeBlock = (TimeBlock) iter.next();
    			PayEarnings payEarnings = findPayEarnings(timeBlock, empPayEarnings);
    			if (Utility.hasValue(payEarnings)) {
    				Job job = getJobService().getJob(payEarnings.getUniversityId(), payEarnings.getEmployeeRecord(), timeBlock.getBeginTime().getDate());
    				if (Utility.hasValue(job)) {
    					addHoursToInterface(payrollInterfaces, payEarnings, job, getHour(timeBlock.getBeginTime(), timeBlock.getEndTime()), timeBlock);
    				} else {
    					// TODO : generate error message
    				}
    			} else {
    				// TODO : generate error message
    			}
    		}
    		// save to ps_iu_pyfnd_intrfc if there is no error reported ?
    		getPayrollExtractService().savePayrollInterfaces(payrollInterfaces);
    	}

    	private void addHoursToInterface(TypedPersistentMaintainedEntityList payrollInterfaces, PayEarnings payEarnings, Job job, double hours, TimeBlock timeBlock) {
    		double percentDistributed = 0.0;
    		if (!payrollInterfaces.isEmpty()) {
    			for (Iterator iter = payrollInterfaces.iterator(); iter.hasNext();) {
    				PayrollInterface payrollInterface = (PayrollInterface) iter.next();
    				if (payrollInterface.getEarnsBeginDate().equals(payEarnings.getEarnsBeginDate()) && payrollInterface.getEarnsEndDate().equals(payEarnings.getEarnsEndDate()) && payrollInterface.getEarnCode().equals(timeBlock.getEarnCode()) && payrollInterface.getEmployeeRecord().equals(payEarnings.getEmployeeRecord())) {
    					for (Iterator iterator = job.getAssignments().iterator(); iterator.hasNext();) {
    						Assignment assignment = (Assignment) iterator.next();
    						// TODO : check worarea/task/acct now- anything else to
    						// check ?
    						if (assignment.getWorkArea().equals(timeBlock.getWorkArea()) && assignment.getTask().equals(timeBlock.getTask()) && assignment.getAccount().equals(payrollInterface.getAccount())) {
    							percentDistributed += assignment.getPercent().intValue();
    							payrollInterface.setHoursForPeriod(new BigDecimal(payrollInterface.getHoursForPeriod().doubleValue() + hours * (assignment.getPercent()).doubleValue() / 100.00));
    						}
    					}
    				}
    			}
    			if (percentDistributed == 100.00) {
    				return;
    			} else if (percentDistributed > 0) {
    				// TODO : generate error message
    				// partially distributed ?? rollback ?
    			}
    		}

    		// pyfnd interface not created yet
    		for (Iterator iterator = job.getAssignments().iterator(); iterator.hasNext();) {
    			Assignment assignment = (Assignment) iterator.next();
    			// TODO : check worarea/task/acct now- anything else to check ?
    			if (assignment.getWorkArea().equals(timeBlock.getWorkArea()) && assignment.getTask().equals(timeBlock.getTask())) {

    				PayrollInterface payrollInterface = new PayrollInterface(payEarnings.getCompany(), payEarnings.getPaygroup(), payEarnings.getPayEndDate(), payEarnings.isOffCycle(), payEarnings.getUniversityId(), payEarnings.getEmployeeRecord(), payEarnings.getEarnsBeginDate(), payEarnings.getEarnsEndDate(), timeBlock.getEarnCode(), payEarnings.getPageNumber(), payEarnings.getLineNumber(), assignment);
    				payrollInterface.setHoursForPeriod(new BigDecimal(hours * (assignment.getPercent()).doubleValue() / 100.00));
    				payrollInterfaces.add(payrollInterface);
    			}
    		}
    	}

    	private PayEarnings findPayEarnings(TimeBlock timeBlock, Collection empPayEarnings) {
    		for (Iterator iter = empPayEarnings.iterator(); iter.hasNext();) {
    			PayEarnings payEarnings = (PayEarnings) iter.next();
    			// TODO : what is the criteria for earncode match ?
    			if (timeBlock.getEmployeeRecord().equals(payEarnings.getEmployeeRecord()) && !timeBlock.getEndTime().getDate().before(payEarnings.getEarnsBeginDate()) && !timeBlock.getBeginTime().getDate().after(payEarnings.getEarnsEndDate())) {
    				return payEarnings;
    			}
    		}
    		return null;
    	}

    	private double getHour(Timestamp beginTime, Timestamp endTime) {
    		// Ampm
    		return (getAmPmHour(endTime) + endTime.getHour().doubleValue() * 60 + endTime.getMinute().doubleValue() - (getAmPmHour(beginTime) + beginTime.getHour().doubleValue() * 60 + beginTime.getMinute().doubleValue())) / 60;
    	}

    	private double getAmPmHour(Timestamp time) {
    		// if use 12am-11am 12pm-11pm, then this is ok
    		if (time.getHour().equals(new BigDecimal(12))) {
    			if (time.getAmPm().equals("AM")) {
    				return new Double(-720).doubleValue();
    			} else {
    				return new Double(0).doubleValue();
    			}
    		} else {
    			if (time.getAmPm().equals("AM")) {
    				return new Double(0).doubleValue();
    			} else {
    				return new Double(720).doubleValue();
    			}
    		}
    	}
    
    }
	
    private List[] divideList(int numDivisions, List list) {
    	List[] dividedList = new List[numDivisions];
    	for (int index = 0; index < numDivisions; index++) {
    		dividedList[index] = new ArrayList();
    	}
    	int index = 0;
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			dividedList[index++ % numDivisions].add(iterator.next());
		}
    	return dividedList;
    }
    
    /**
     * Works around a bug(?) in OJB 1.1 (HEAD) where it can't handle concurrent initialization of the OJB persistence broker pool.
     */
    private void initializeTkOJB() {
    	PersistenceBroker broker = PersistenceBrokerFactory.createPersistenceBroker("tkDataSource", null, null);
    	broker.close();
    }
    
	private void setExtractStatus(String status) {
		LOG.info("Started setExtractStatus(String status) Method");
		payrollExtractSchedule.setStatus(status);
		TypedPersistentMaintainedEntityList typedPersistentMaintainedEntityList = new TypedPersistentMaintainedEntityList(PayrollExtractSchedule.class);
		typedPersistentMaintainedEntityList.add(payrollExtractSchedule);
		getPayrollExtractService().savePayrollExtractSchedules(typedPersistentMaintainedEntityList);
		LOG.info("Finished setExtractStatus(String status) Method");

	}

	public PayrollExtractSchedule getPayrollExtractSchedule() {
		return payrollExtractSchedule;
	}

	public void setPayrollExtractSchedule(PayrollExtractSchedule payrollExtractSchedule) {
		if (payrollExtractSchedule != null) {
			this.payrollExtractSchedule = payrollExtractSchedule;
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		if (request != null) {
			this.request = request;
		}
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		if (emailService != null) {
			this.emailService = emailService;
		}
	}

	public PayrollExtractService getPayrollExtractService() {
		return payrollExtractService;
	}

	public void setPayrollExtractService(PayrollExtractService payrollExtractService) {
		if (payrollExtractService != null) {
			this.payrollExtractService = payrollExtractService;
		}
	}

	public WebUserService getWebUserService() {
		return webUserService;
	}

	public void setWebUserService(WebUserService webUserService) {
		if (webUserService != null) {
			this.webUserService = webUserService;
		}
	}

	public TimesheetService getTimesheetService() {
		return timesheetService;
	}

	public void setTimesheetService(TimesheetService timesheetService) {
		if (timesheetService != null) {
			this.timesheetService = timesheetService;
		}
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		if (jobService != null) {
			this.jobService = jobService;
		}
	}

	public BatchMessageDataAccess getBatchMessageDataAccess() {
		return batchMessageDataAccess;
	}

	public void setBatchMessageDataAccess(
			BatchMessageDataAccess batchMessageDataAccess) {
		this.batchMessageDataAccess = batchMessageDataAccess;
	}

}
