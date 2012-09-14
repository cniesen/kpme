package org.kuali.hr.time.timesheet;

import org.kuali.hr.time.calendar.Calendar;
import org.kuali.hr.time.calendar.CalendarEntries;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

public class TimeSheetInitiate extends PersistableBusinessObjectBase {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String tkTimeSheetInitId;
    private String principalId;
    private String hrCalendarEntriesId;
    private String pyCalendarGroup;
    private String documentId;

    private Person principal;
    private Calendar payCalendarObj;

    private CalendarEntries payCalendarEntriesObj;

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public Person getPrincipal() {
        return principal;
    }

    public void setPrincipal(Person principal) {
        this.principal = principal;
    }


    public String getTkTimeSheetInitId() {
        return tkTimeSheetInitId;
    }

    public void setTkTimeSheetInitId(String tkTimeSheetInitId) {
        this.tkTimeSheetInitId = tkTimeSheetInitId;
    }

    public String getHrCalendarEntriesId() {
        return hrCalendarEntriesId;
    }

    public void setHrCalendarEntriesId(String hrCalendarEntriesId) {
        this.hrCalendarEntriesId = hrCalendarEntriesId;
    }

    public CalendarEntries getPayCalendarEntriesObj() {
        if(hrCalendarEntriesId != null) {
            setPayCalendarEntriesObj(TkServiceLocator.getCalendarEntriesService().getCalendarEntries(hrCalendarEntriesId));
        }
        return payCalendarEntriesObj;
    }

    public void setPayCalendarEntriesObj(CalendarEntries payCalendarEntriesObj) {
        this.payCalendarEntriesObj = payCalendarEntriesObj;
    }

    public String getPyCalendarGroup() {
        return pyCalendarGroup;
    }

    public void setPyCalendarGroup(String pyCalendarGroup) {
        this.pyCalendarGroup = pyCalendarGroup;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Calendar getPayCalendarObj() {
        return payCalendarObj;
    }

    public void setPayCalendarObj(Calendar payCalendarObj) {
        this.payCalendarObj = payCalendarObj;
    }

    public String getBeginAndEndDateTime() {
        if (payCalendarEntriesObj == null && this.getHrCalendarEntriesId() != null) {
            payCalendarEntriesObj = TkServiceLocator.getCalendarEntriesService().getCalendarEntries(this.getHrCalendarEntriesId());
        }
        return (payCalendarEntriesObj != null) ?
                payCalendarEntriesObj.getBeginPeriodDateTime().toString() + " - "+ payCalendarEntriesObj.getEndPeriodDateTime().toString() : "";
    }

}
