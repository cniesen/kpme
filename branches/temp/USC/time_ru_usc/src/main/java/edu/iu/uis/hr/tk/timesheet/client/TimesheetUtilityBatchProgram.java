package edu.iu.uis.hr.tk.timesheet.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.service.WorkflowDocument;

import edu.iu.hrms.hredoc.infrastructure.HredocServiceLocator;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.entity.logic.IsReadyToApproveLogic;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class TimesheetUtilityBatchProgram implements BatchProgram {
	private static final Logger LOG = Logger.getLogger(TimesheetUtilityBatchProgram.class);
	private static final int NUMBER_OF_THREADS = 10;
	private static int documentsSuccessfullyProcessed = 0;
	private static int documentsNotProcessed = 0;
	private static int docsAtZero = 0;
	private static int docsAtOne = 0;
	private static int docsAtTwo = 0;
	private static int docsAboveTwo = 0;
	private static int docsRequestingCompletion = 0;
	
	private static final String MAIL_REPORT_TO = "nelsonjr@indiana.edu";
	private static final String MAIL_REPORT_FROM = "TIME@indiana.edu";
	private static final String MAIL_SUBJECT = "TIME testing: TimesheetUtility";
	private static final String WORKFLOW_SUPER_USER = "timekeeping-system";

	private TimesheetService timesheetService;
	private PayCalendarService payCalendarService;
	private EmailService emailService;
	private JobService jobService;
    private DirectoryService directoryService;
	
	private List programReport = new ArrayList();

	public void execute() {
		int numThreads = NUMBER_OF_THREADS;
		
		initializeTkOJB(); // this is supposed to fix an issue with batch jobs not terminating

		// all docs - If you want to process all docs, or a subset, use this portion
		//List documentHeaders = getTimesheetService().geDocumentHeaders(getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
		//documentHeaders = documentHeaders.subList(0, 100); // just play with a few of the docs
		
		// if you want to process just one doc, use this portion
		DocumentHeader documentHeader = getTimesheetService().getDocumentHeader("0000072932", getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
		List documentHeaders = new ArrayList();
		documentHeaders.add(documentHeader);
		
		programReport.add("Number of headers to process: " + documentHeaders.size());
		
		List[] dividedDocumentHeaders = divideList(numThreads, documentHeaders);
		Thread[] threads = new Thread[numThreads];
		for (int index = 0; index < threads.length; index++) {
			threads[index] = new Thread(new DocumentDoer(dividedDocumentHeaders[index]));
		}
		for (int index = 0; index < threads.length; index++) {
			threads[index].start();
		}
		for (int index = 0; index < threads.length; index++) {
			try {
				threads[index].join();
			} catch (InterruptedException e) {
				LOG.error("Thread number " + (index + 1) + " was interrupted!");
			}
		}

		programReport.add("docs at zero: " + docsAtZero);
		programReport.add("docs at one: " + docsAtOne);
		programReport.add("docs at two: " + docsAtTwo);
		programReport.add("docs above two: " + docsAboveTwo);
		programReport.add("documentsSuccessfullyProcessed: " +documentsSuccessfullyProcessed);
		programReport.add("documentNotProcessed: " + documentsNotProcessed);
		programReport.add("documentsRequestingCompletion: " + docsRequestingCompletion);
		//submitReport();

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

	private void submitReport() {
		String reportContent = "";
		for (Iterator programReportIterator = programReport.iterator(); programReportIterator.hasNext();) {
			reportContent += (String) programReportIterator.next() + "\n \n";
		}
		emailService.sendMail(MAIL_REPORT_TO, MAIL_REPORT_FROM, MAIL_SUBJECT, reportContent);
		LOG.info(reportContent);
	}

	/**
	 * Works around a bug(?) in OJB 1.1 (HEAD) where it can't handle concurrent
	 * initialization of the OJB persistence broker pool.
	 */
	private void initializeTkOJB() {
		PersistenceBroker broker = PersistenceBrokerFactory.createPersistenceBroker("tkDataSource", null, null);
		broker.close();
	}

	private TimesheetService getTimesheetService() {
		return timesheetService;
	}

	public void setTimesheetService(TimesheetService timesheetService) {
		if (timesheetService != null) {
			this.timesheetService = timesheetService;
		}
	}

	private PayCalendarService getPayCalendarService() {
		return payCalendarService;
	}

	public void setPayCalendarService(PayCalendarService payCalendarService) {
		if (payCalendarService != null) {
			this.payCalendarService = payCalendarService;
		}
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	private class DocumentDoer implements Runnable {

		private final List documentHeaders;

		public DocumentDoer(List documentHeaders) {
			this.documentHeaders = documentHeaders;
		}

		public void run() {
			for (Iterator iter = documentHeaders.iterator(); iter.hasNext();) {
				DocumentHeader documentHeader = (DocumentHeader) iter.next();
				try {

					//testIsReadyToApprove(documentHeader);
					
					//employeeApproval(documentHeader);
					
					payrollExtract(documentHeader);
					
					//oneTimeOnlyStuff(documentHeader);
					
					//openAndSaveTimesheet(documentHeader);

					//countTimesheetsLevels(documentHeader);
					
					//approveAtLevelOne(documentHeader);
					
					//takeCompleteAction(documentHeader);
					
					
				} catch (Exception e) {
					LOG.error("Error " + documentHeader.getDocumentId() + ": " + e.getMessage());
				}
			}
		}
			
		
		private void payrollExtract(DocumentHeader documentHeader) {
    		IsReadyToApproveLogic logic = (IsReadyToApproveLogic)Context.getLogic(IsReadyToApproveLogic.class);
			boolean finalApproved = getTimesheetService().isFinalApproved(WORKFLOW_SUPER_USER, documentHeader.getDocumentId());
			if (!finalApproved && logic.isReadyToApprove(documentHeader.getDocumentId())) {
				try {
					TimesheetDocument td = new TimesheetDocument(documentHeader);
					td.setJobs(getJobService().getJobsWithAssignments(documentHeader.getUniversityId(), documentHeader.getPayEndDate()));
					getTimesheetService().approve(td, WORKFLOW_SUPER_USER);
					finalApproved = getTimesheetService().isFinalApproved(WORKFLOW_SUPER_USER, documentHeader.getDocumentId());
				} catch (Exception e) {
					LOG.error("auto approve error " + e.getMessage());
					programReport.add("auto approve error " + e.getMessage());
				}
			}
		}
		
		/**
		 * Given a document, exercise the ready to appove function and log the result 
		 * 
		 * @param documentHeader
		 */
		private void testIsReadyToApprove(DocumentHeader documentHeader ){
			try {
				IsReadyToApproveLogic logic = (IsReadyToApproveLogic)Context.getLogic(IsReadyToApproveLogic.class);
				if (logic.isReadyToApprove(documentHeader.getDocumentId())) {
					programReport.add(documentHeader.getDocumentId() + " ready to approve.");
				} else {
					programReport.add(documentHeader.getDocumentId() + " NOT ready to approve.");
				}
				
				
			} catch (Exception e) {
				LOG.error("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				programReport.add("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
			}
		}		
		
		
		/**
		 * Open and save a timesheet (to simulate a user doing so). This forces a calc of ovt and shift hours
		 * on batch-loaded timesheet data. 
		 * 
		 * @param documentHeader
		 */
		private void oneTimeOnlyStuff(DocumentHeader documentHeader ){
			try {
				
				WorkflowDocument workflowDocument = new WorkflowDocument(new NetworkIdDTO("timekeeping-system"), new Long(documentHeader.getDocumentId()));
				
				//DocumentHeader docHdr = getTimesheetService().getDocumentHeader("0001332447", getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
				//boolean met = getTimesheetService().expectedHoursMet(getTimesheetService().getTimesheetDocument(docHdr.getDocumentId()));
				
//				List documentHeaders = getTimesheetService().geDocumentHeaders(getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
//				
//				boolean met = false;
//				for (Iterator iter = documentHeaders.iterator(); iter.hasNext();) {
//					DocumentHeader documentHeader = (DocumentHeader)iter.next();
//					met = getTimesheetService().expectedHoursMet(getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId()));
//				}
//				
//				LOG.debug("met = " + met);
				
				
				
			} catch (Exception e) {
				LOG.error("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				programReport.add("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
			}
		}
		
		/**
		 * Employee approve timesheet.
		 * 
		 * @param documentHeader
		 */
		private void employeeApproval(DocumentHeader documentHeader) {
			
			try {
				TimesheetDocument timesheetDocument = getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId());
				String networkId = getDirectoryService().getNetworkIdByEmployeeId(documentHeader.getUniversityId());
                getTimesheetService().approve(timesheetDocument, networkId);
			} catch (Exception e) {
				LOG.error("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				programReport.add("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
			}
		}
		
		
		/**
		 * Open and save a timesheet (to simulate a user doing so).
		 * 
		 * @param documentHeader
		 */
		@SuppressWarnings("unchecked")
		private void openAndSaveTimesheet(DocumentHeader documentHeader ){
			try {
				
				getTimesheetService().lock(documentHeader.getDocumentId());
				documentHeader = getTimesheetService().getDocumentHeader(documentHeader.getUniversityId(), documentHeader.getPayEndDate());
				TimesheetDocument document = getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId());

				getTimesheetService().open(document.getDocumentMetaData());
				Entity entity = (Entity)document;
				
				
		        Iterator operationalLogics = entity.getOperationalLogics(new SaveEvent());
		        while (operationalLogics.hasNext()) {
		            ((OperationalLogic)Context.getLogic((Class)operationalLogics.next())).execute(entity);
		        }
		        
		        if (entity.getEntityErrors().isErroneous()) {
		        	programReport.addAll(entity.getEntityErrors().getErrorMessages());
		        	getTimesheetService().unlockDocument(document);			        	
		        	throw new Exception();
		        } else {
					getTimesheetService().save(document);
					LOG.info("Success for document " + documentHeader.getDocumentId());
					documentsSuccessfullyProcessed++;
		        }
			
			
			} catch (Exception e) {
				LOG.error("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				programReport.add("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				documentsNotProcessed++;
			}
		}
		
		
		
		
		/**
		 * Summarize in global static ints how many docs are at each level. 
		 * 
		 * @param documentHeader
		 */
		private void countTimesheetsLevels(DocumentHeader documentHeader ) {
			try {
				WorkflowDocument workflowDocument = new WorkflowDocument(new NetworkIdDTO("timekeeping-system"), new Long(documentHeader.getDocumentId()));
	
				if (workflowDocument.getRouteHeader().getDocRouteLevel().intValue() == 0 ) {
					docsAtZero++;
				}
				if (workflowDocument.getRouteHeader().getDocRouteLevel().intValue() == 1 ) {
					docsAtOne++;
				}
				if (workflowDocument.getRouteHeader().getDocRouteLevel().intValue() == 2 ) {
					docsAtTwo++;
				}
				if (workflowDocument.getRouteHeader().getDocRouteLevel().intValue() > 2 ) {
					docsAboveTwo++;
				}
					
				LOG.info("Doc at level " + workflowDocument.getRouteHeader().getDocRouteLevel() + " " + documentHeader.getDocumentId());
			} catch (Exception e) {
				LOG.error("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				programReport.add("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
			}
		}
		

		/**
		 * Takes a level 1 approval action on a timesheet. The approval is done
		 * as the first user found in the appropriate action list. 
		 * 
		 * @param documentHeader
		 */
		private void approveAtLevelOne(DocumentHeader documentHeader ){

			try {
				WorkflowDocument workflowDocument = new WorkflowDocument(new NetworkIdDTO("timekeeping-system"), new Long(documentHeader.getDocumentId()));
				if (workflowDocument.getRouteHeader().getDocRouteLevel().intValue() == 1) {
					ActionRequestDTO[] acks = workflowDocument.getActionRequests();
					for (int i = 0; i<acks.length; i++) {
						ActionRequestDTO actionRequest= (ActionRequestDTO)acks[i];
						if (actionRequest.getRouteLevel().intValue() == 1 && actionRequest.getStatus().equals("A")) {
							if (actionRequest.getPrincipalId() != null) {
								String networkId = HredocServiceLocator.getHreUserGroupService().convertPrincipalIDToPrincipalName(actionRequest.getPrincipalId());
								LOG.info("Approving doc " + documentHeader.getDocumentId() + " as " + networkId);
								workflowDocument = new WorkflowDocument(new NetworkIdDTO(networkId), new Long(documentHeader.getDocumentId()));
								workflowDocument.approve("approve via batch");
								documentsSuccessfullyProcessed++;
								break;
							}
						}
					}
				}
				
			} catch (Exception e) {
				LOG.error("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				programReport.add("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				documentsNotProcessed++;
			}
		}		
		
		/**
		 * Takes a Complete action on a document as user nelsonjr. Typically used when a timesheet
		 * has gone to Exception and a Complete request has been genereated. This method will
		 * fulfill that request.
		 * 
		 * @param documentHeader
		 */
		private void takeCompleteAction(DocumentHeader documentHeader ){

			try {
				WorkflowDocument workflowDocument = new WorkflowDocument(new NetworkIdDTO("nelsonjr"), new Long(documentHeader.getDocumentId()));
				if (workflowDocument.isCompletionRequested()) {
					workflowDocument.complete("complete via batch");
					docsRequestingCompletion++;
				}
				
			} catch (Exception e) {
				LOG.error("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				programReport.add("Error for document " + documentHeader.getDocumentId() + ": " + e.getMessage());
				documentsNotProcessed++;
			}
		}
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

    /**
     * @return the directoryService
     */
    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    /**
     * @param the directoryService to set
     */
    public void setDirectoryService(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

}
