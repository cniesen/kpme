package edu.iu.uis.hr.tk.timesheet.entity;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.entity.Translate;
import edu.iu.uis.hr.tk.service.TranslateService;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockRequiredFieldsLogic;

public class ClockLog extends AbstractClockLog implements AuditedPersitentDatabaseMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(ClockLog.class);
    private static final String TRANSLATE_FIELD_NAME = "translate";
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.UNIVERSITY_ID);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.EMPLOYEE_RECORD);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.WORK_AREA);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.TASK);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.CLOCK_TIME);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.ACTION);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.IP_ADDRESS);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.USER_UNIVERSITY_ID);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.TIMESTAMP);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.TIME_ZONE);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(TRANSLATE_FIELD_NAME);
    }

    public ClockLog() {
        super();
    }

    public ClockLog(String universityId) {
        this();
        setUniversityId(universityId);
    }

    public ClockLog(String universityId, BigDecimal employeeRecord, BigDecimal workArea, BigDecimal task, Timestamp clockTime, String timeZone, String action) {
        this(universityId);
        setEmployeeRecord(employeeRecord);
        setWorkArea(workArea);
        setTask(task);
        setClockTime(clockTime);
        setAction(action);
        setTimeZone(timeZone);
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public Class getRequiredFieldsLogic() {
        return ClockRequiredFieldsLogic.class;
    }
    
    public String getStatus() {
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getTranslate())) {
            return getTranslate().getDescription() + " since " + new TimedDate(getClockTime()).toString();
        }
        return Utility.getDefaultStringValue();
    }
    
	public Translate getTranslate() {
			if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getClockTime())) {
				TranslateService translateService = (TranslateService)Context.getService(TranslateService.class);
				return translateService.getTranslate("action", this.getAction(), this.getClockTime().getDate());
			} 
			return null;
	}

    public String getAssignment() {
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getEmployeeRecord()) && edu.iu.uis.hr.entity.logic.Utility.hasValue(getWorkArea()) && edu.iu.uis.hr.entity.logic.Utility.hasValue(getTask())) {
            return getUniversityId() + Utility.COMMA + getEmployeeRecord() + Utility.COMMA + getWorkArea() + Utility.COMMA + getTask();
        }
        return Utility.getDefaultStringValue();
    }
    
    public Class getModeAuthorization() {
        return ClockLogModeAuthorization.class;
    }
    
}
