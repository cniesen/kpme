package edu.iu.uis.hr.tk.timesheet.entity;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class TimeBlockHistory extends AbstractTimeBlock  implements PersistentMaintainedEntity, AuditedPersitentDatabaseMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(TimeBlockHistory.class);

    private BigDecimal recordId;
    private String actionHistory;
    private String modifiedByUniversityId;
    private Timestamp timestampOriginal;
    private String timestampOriginalTz;
    
    public TimeBlockHistory() {
        super();
    }

    public TimeBlockHistory(TimeBlock timeBlock) {
        setBeginTime(timeBlock.getBeginTime());
        setBeginTsTz(timeBlock.getBeginTsTz());
        setCreatedByClock(timeBlock.isCreatedByClock());
        setDocumentId(timeBlock.getDocumentId());
        setEarnCode(timeBlock.getEarnCode());
        setEmployeeRecord(timeBlock.getEmployeeRecord());
        setEndTime(timeBlock.getEndTime());
        setEndTsTz(timeBlock.getEndTsTz());
        setHours(timeBlock.getHours());
        setOvertimeEarnCode(timeBlock.getOvertimeEarnCode());
        setOvertimeHours(timeBlock.getOvertimeHours());
        setPremiumEarnCode(timeBlock.getPremiumEarnCode());
        setPremiumHours(timeBlock.getPremiumHours());
        setShiftEarnCode(timeBlock.getShiftEarnCode());
        setShiftHours(timeBlock.getShiftHours());
        setTask(timeBlock.getTask());
        //setUserUniversityId(timeBlock.getUserUniversityId());
        setWorkArea(timeBlock.getWorkArea());
        setTimestampOriginal(timeBlock.getTimestamp());
        setTimestampOriginalTz(timeBlock.getTimestampTz());
    }
    
    public String getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(String actionHistory) {
        if (actionHistory != null) {
            this.actionHistory = actionHistory;
        }
    }

    public String getModifiedByUniversityId() {
        return modifiedByUniversityId;
    }

    public void setModifiedByUniversityId(String modifiedByUniversityId) {
        if (modifiedByUniversityId != null) {
            this.modifiedByUniversityId = modifiedByUniversityId;
        }
    }
    
    public Class getModeAuthorization() {
        return TimeBlockHistoryModeAuthorization.class;
    }

    public Timestamp getTimestampOriginal() {
        return timestampOriginal;
    }

    public void setTimestampOriginal(Timestamp timestampOriginal) {
        if (timestampOriginal != null) {
            this.timestampOriginal = timestampOriginal;
        }
    }

    public String getTimestampOriginalTz() {
		return timestampOriginalTz;
	}

	public void setTimestampOriginalTz(String timestampOriginalTz) {
		this.timestampOriginalTz = timestampOriginalTz;
	}

	public BigDecimal getRecordId() {
        return recordId;
    }

    public void setRecordId(BigDecimal recordId) {
        if (recordId != null) {
            this.recordId = recordId;
        }
    }

    public boolean isDisplayOnly() {
        return true;
    }
    
}

