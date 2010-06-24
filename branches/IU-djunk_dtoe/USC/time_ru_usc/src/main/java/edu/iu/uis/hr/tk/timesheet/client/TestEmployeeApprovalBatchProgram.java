package edu.iu.uis.hr.tk.timesheet.client;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.job.funding.service.PayCalendarService;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class TestEmployeeApprovalBatchProgram implements BatchProgram {
    private static final Logger LOG = Logger.getLogger(TestEmployeeApprovalBatchProgram.class);
    private TimesheetService timesheetService;
    private PayCalendarService payCalendarService;
    private DirectoryService directoryService;

    public void execute() {
        
    	String universityId = "";
    	DocumentHeader documentHeader = new DocumentHeader();
        Date payEndDate = new Date(new GregorianCalendar(2006,8,23).getTimeInMillis()); // e.g. 2006,8,23 is September 23, 2006
        try {
        	
            universityId = "0001789756";
            documentHeader = getTimesheetService().getDocumentHeader(universityId, payEndDate);
            getTimesheetService().approve(getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId()), getDirectoryService().getNetworkIdByEmployeeId(documentHeader.getUniversityId()));
            
            universityId = "0001786864";
            documentHeader = getTimesheetService().getDocumentHeader(universityId, payEndDate);
            getTimesheetService().approve(getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId()), getDirectoryService().getNetworkIdByEmployeeId(documentHeader.getUniversityId()));
            
            universityId = "0001786482";
            documentHeader = getTimesheetService().getDocumentHeader(universityId, payEndDate);
            getTimesheetService().approve(getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId()), getDirectoryService().getNetworkIdByEmployeeId(documentHeader.getUniversityId()));
 
            
        } catch (Exception e) {
        	LOG.error("Approval failed : " + e.getMessage());
        }
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
