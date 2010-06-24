package edu.iu.uis.hr.tk.timesheet.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;

import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.report.dataaccess.BatchMessageDataAccess;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class EmployeeApprovalBatchProgram implements BatchProgram {
    private static final Logger LOG = Logger.getLogger(EmployeeApprovalBatchProgram.class);
    private static final int NUMBER_OF_THREADS = 10;
    private static final int MAX_APPROVAL_ATTEMPTS = 3;
	private static int documentsSuccessfullyProcessed = 0;
	private static int documentsNotProcessed = 0;
    private TimesheetService timesheetService;
    private PayCalendarService payCalendarService;
    private DirectoryService directoryService;
    private EmailService emailService;
    private BatchMessageDataAccess batchMessageDataAccess;
	private List programReport = new ArrayList();
    
	private static final String MAIL_REPORT_TO = "bl-uits-hrms-java-team@exchange.iupui.edu";
	private static final String MAIL_REPORT_FROM = "TIME@indiana.edu";
	private static final String MAIL_SUBJECT = "TIME Employee Approval Batch Program Report";

    public void execute() {
    	int numThreads = NUMBER_OF_THREADS;
        initializeTkOJB();
        List documentHeaders = getTimesheetService().geDocumentHeaders(getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
        
        programReport.add("Number of headers to process: " + documentHeaders.size());
        
        // TODO DEBUGING
        //documentHeaders = documentHeaders.subList(0, 30);
//        documentHeaders.clear();
//        DocumentHeader documentHeader = getTimesheetService().getDocumentHeader("0002018678", getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
//        documentHeaders.add(documentHeader);
        // TODO END DEBUGING
        
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
        
        programReport.add("Documents successfully processed: " + documentsSuccessfullyProcessed);
        programReport.add("Documents not processed: " + documentsNotProcessed);        
        submitReport();
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
     * Works around a bug(?) in OJB 1.1 (HEAD) where it can't handle concurrent initialization of the OJB persistence broker pool.
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
	
    private class DocumentApprover implements Runnable {
    	
    	private final List documentHeaders;
    	private int retryCount = 0;
    	
    	public DocumentApprover(List documentHeaders) {
    		this.documentHeaders = documentHeaders;
    	}
    	
    	public void run() {
    		while (retryCount++<MAX_APPROVAL_ATTEMPTS) {
	            for (Iterator iter = documentHeaders.iterator(); iter.hasNext();) {
	                DocumentHeader documentHeader = (DocumentHeader) iter.next();
	                try {
	                	if (DocumentConstants.SAVED.equals(documentHeader.getDocumentStatus())) {
	                		getTimesheetService().approve(getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId()), getDirectoryService().getNetworkIdByEmployeeId(documentHeader.getUniversityId()));
		                    iter.remove();
		                    documentsSuccessfullyProcessed++;
                		}
	                } catch (Exception e) {
	                	LOG.error("Employee approval error for document " + documentHeader.getDocumentId() + " " + e.getMessage());
	                	if (retryCount==MAX_APPROVAL_ATTEMPTS) {
	                		documentsNotProcessed++;
	                		programReport.add("Error for document " + documentHeader.getDocumentId() + " e: " + e.getMessage());
	                    	BatchMessage batchMessage = new BatchMessage(null,BatchMessageSearchCriteria.EMPLOYEE_APPROVAL_PROCESS_NAME,new Date(),documentHeader.getDocumentId(),documentHeader.getUniversityId(),"Document not approved: " + e.getMessage());
	                    	getBatchMessageDataAccess().store(batchMessage);
	                	}
	                }
	            }
    		}
    	}
    }

	public BatchMessageDataAccess getBatchMessageDataAccess() {
		return batchMessageDataAccess;
	}

	public void setBatchMessageDataAccess(BatchMessageDataAccess batchMessageDataAccess) {
		this.batchMessageDataAccess = batchMessageDataAccess;
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
