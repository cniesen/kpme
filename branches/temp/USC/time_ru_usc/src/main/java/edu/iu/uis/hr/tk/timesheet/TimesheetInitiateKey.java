package edu.iu.uis.hr.tk.timesheet;

import java.util.Date;

import edu.iu.uis.hr.InitiateKey;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class TimesheetInitiateKey implements InitiateKey {

    private String universityId;
    private Date payEndDate;

    public TimesheetInitiateKey() {
        super();
        setUniversityId(Utility.getDefaultStringValue());
        setPayEndDate(new Date());
    }
    
    public TimesheetInitiateKey(String universityId, Date payEndDate) {
        this();
        setUniversityId(universityId);
        setPayEndDate(payEndDate);
    }

    public String getDocumentType() {
        return TimesheetDocument.TIMESHEET_DOCUMENT_TYPE;
    }
    
    public String getTitle() {
        return TimesheetDocument.TIMESHEET_DOCUMENT_TITLE;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String employeeId) {
        if (employeeId != null) {
            this.universityId = employeeId;
        }
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        if (payEndDate != null) {
            this.payEndDate = payEndDate;
        }
    }

}
