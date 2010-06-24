package edu.iu.uis.hr.tk.timesheet.client;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;

import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.employee.entity.Employee;
import edu.iu.uis.hr.tk.employee.service.EmployeeService;
import edu.iu.uis.hr.tk.report.dataaccess.BatchMessageDataAccess;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.TimesheetInitiateKey;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class InitiateDocumentBatchProgram implements BatchProgram {
    private static final Logger LOG = Logger.getLogger(InitiateDocumentBatchProgram.class);
    private static final int DAYS_TO_PAY_PERIOD_TO_INITIATE = 14;
    private static final int NUMBER_OF_THREADS = 8;
    private static final int MAX_INITIATION_ATTEMPS = 3;
    
    private static int documentCreationCounter = 0;
    private List programReport = new ArrayList();
    private static final String MAIL_REPORT_TO="bl-uits-hrms-java-team@exchange.iupui.edu";
    private static final String MAIL_REPORT_FROM="TIME@indiana.edu";
    private static final String MAIL_SUBJECT="TIME InitiateDocumentBatchProgram Report";

    private TimesheetService timesheetService;
    private EmployeeService employeeService;
    private PayCalendarService payCalendarService;
    private EmailService emailService;    
    private BatchMessageDataAccess batchMessageDataAccess;

    
    public void execute(String universityId) {
    	 List activeEmployees = new ArrayList();
    	 activeEmployees.add(getEmployeeService().getEmployee(universityId));
    	 PayCalendar payCalendar = getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).addDays(DAYS_TO_PAY_PERIOD_TO_INITIATE).getDate());
    	 Thread thread = new Thread(new DocumentInitiator(payCalendar, activeEmployees));
    	 thread.start();
    }
    
    public void execute() {
        int numThreads = NUMBER_OF_THREADS;
        initializeTkOJB();
        PayCalendar payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new Date()).addDays(DAYS_TO_PAY_PERIOD_TO_INITIATE).getDate());
        List activeEmployees = getEmployeeService().getActiveEmployees(payCalendar);
        
        activeEmployees = activeEmployees.subList(0, 10);
        
        programReport.add("Initiate Document Batch Program For Pay Period  " + payCalendar.getPayBeginDate() + " to " + payCalendar.getPayEndDate() );
        programReport.add("Total active employees: " + activeEmployees.size());
        
        List[] dividedActiveEmployees = divideList(numThreads, activeEmployees);
        Thread[] threads = new Thread[numThreads];
        for (int index = 0; index < threads.length; index++) {
            threads[index] = new Thread(new DocumentInitiator(payCalendar, dividedActiveEmployees[index]));
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
        programReport.add("Total timesheets successfully processed: " + documentCreationCounter);
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
    
    /**
     * Works around a bug(?) in OJB 1.1 (HEAD) where it can't handle concurrent initialization of the OJB persistence broker pool.
     */
    private void initializeTkOJB() {
    	PersistenceBroker broker = PersistenceBrokerFactory.createPersistenceBroker("tkDataSource", null, null);
    	broker.close();
    }
    
    private void submitReport(){
        String reportContent = "";
        for (Iterator programReportIterator = programReport.iterator(); programReportIterator.hasNext();) {
            reportContent += (String)programReportIterator.next() + "\n \n";
        }
        emailService.sendMail(MAIL_REPORT_TO, MAIL_REPORT_FROM, MAIL_SUBJECT, reportContent);
    }

    private TimesheetService getTimesheetService() {
        return timesheetService;
    }

    public void setTimesheetService(TimesheetService timesheetService) {
        if (timesheetService != null) {
            this.timesheetService = timesheetService;
        }
    }

    private EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        if (employeeService != null) {
            this.employeeService = employeeService;
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
    
    private EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        if (emailService != null) {
            this.emailService = emailService;
        }
    }
    

    public class DocumentInitiator implements Runnable {

        private final PayCalendar payCalendar;
        private final List activeEmployees;
        int retryCount = 0;

        public DocumentInitiator(PayCalendar payCalendar, List activeEmployees) {
            this.payCalendar = payCalendar;
            this.activeEmployees = activeEmployees;
        }

        public void run() {
        	while (retryCount++<MAX_INITIATION_ATTEMPS) {
	        	for (Iterator iter = activeEmployees.iterator(); iter.hasNext();) {
	                Employee employee = (Employee)iter.next();
	                try {
	                    TimesheetInitiateKey initiateKey = new TimesheetInitiateKey();
	                    initiateKey.setUniversityId(employee.getUniversityId());
	                    initiateKey.setPayEndDate(payCalendar.getPayEndDate());
	                    TimesheetDocument timesheetDocument = (TimesheetDocument)getTimesheetService().initiate(initiateKey);
	                    iter.remove();
	                    documentCreationCounter++;
	                } catch (Exception e) {
	                    LOG.error("Initiate Document error for employee " + employee.getUniversityId() + " " + e.getMessage());
	                    if (retryCount==MAX_INITIATION_ATTEMPS) {
	                    	BatchMessage batchMessage = new BatchMessage(null,BatchMessageSearchCriteria.DOCUMENT_INITIATION_PROCESS_NAME,new Date(),null,employee.getUniversityId(),"No document created: " + e.getMessage());
	                    	getBatchMessageDataAccess().store(batchMessage);
	                    	programReport.add("Initiate Document error for employee " + employee.getUniversityId() + " " + e.getMessage());
	                    }
	                }
	        	}
            }
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