package edu.iu.uis.hr.tk.timesheet.entity;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.logic.TimeBlockRequiredFieldsLogic;

public class TimeBlock extends AbstractTimeBlock implements PersistentMaintainedEntity, AuditedPersitentDatabaseMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(TimeBlock.class);

    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add("documentHeader");
        LOGIC_EXEMPT_PROPERTY_NAMES.add("overtimeHours");         
        LOGIC_EXEMPT_PROPERTY_NAMES.add("overtimeEarnCode");
        LOGIC_EXEMPT_PROPERTY_NAMES.add("shiftEarnCode");   
        LOGIC_EXEMPT_PROPERTY_NAMES.add("shiftHours");      
        LOGIC_EXEMPT_PROPERTY_NAMES.add("premiumHours");    
        LOGIC_EXEMPT_PROPERTY_NAMES.add("premiumEarnCode");
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.TIMESTAMP);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.BEGIN_TIME);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.END_TIME);        
//        LOGIC_EXEMPT_PROPERTY_NAMES.add("beginTimeOriginal");
//        LOGIC_EXEMPT_PROPERTY_NAMES.add("endTimeOriginal");
    }

    public TimeBlock() {
        super();
        setDocumentId(Utility.getDefaultStringValue());
        setEmployeeRecord(new BigDecimal(0));
        setWorkArea(new BigDecimal(0));
        setTask(new BigDecimal(0));
        setEarnCode(Utility.getDefaultStringValue());
        setBeginTime(new Timestamp());
        setBeginTsTz(Utility.getDefaultStringValue());
        setEndTsTz(Utility.getDefaultStringValue());
        setEndTime(new Timestamp());
        setCreatedByClock(false);
        setHours(new BigDecimal(0));
        setOvertimeEarnCode(Utility.getDefaultStringValue());
        setOvertimeHours(new BigDecimal(0));
        setShiftEarnCode(Utility.getDefaultStringValue());
        setShiftHours(new BigDecimal(0));
        setPremiumEarnCode(Utility.getDefaultStringValue());
        setPremiumHours(new BigDecimal(0));
        setUserUniversityId(Utility.getDefaultStringValue());
        setTimestamp(new Timestamp());
        setTimestampTz(Utility.getDefaultStringValue());
    }

    public TimeBlock(String documentId, BigDecimal employeeRecord, BigDecimal workArea, BigDecimal task, String earnCode, Timestamp beginTime, Timestamp endTime, String beginTsTz, String endTsTz, boolean createdByClock) {
        this();
        setDocumentId(documentId);
        setEmployeeRecord(employeeRecord);
        setWorkArea(workArea);
        setTask(task);
        setEarnCode(earnCode);
        setBeginTime(beginTime);
        setEndTime(endTime);
        setBeginTsTz(beginTsTz);
        setEndTsTz(endTsTz);
        setCreatedByClock(createdByClock);
    }

    
    public String getAssignment(String universityId) {
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(universityId) && edu.iu.uis.hr.entity.logic.Utility.hasValue(getEmployeeRecord()) && edu.iu.uis.hr.entity.logic.Utility.hasValue(getWorkArea()) && edu.iu.uis.hr.entity.logic.Utility.hasValue(getTask())) {
            //get the effective date of the relevant assignment in order to match appropriately in the drop down 
        	//list on the timesheet if there are two assignments differentiated by effdt but no other key fields.
        	java.util.Date hourDate = getBeginTime().getDate(); 
        	AssignmentService assignmentService = (AssignmentService)Context.getService(AssignmentService.class);
        	String timeBlockAssignString = universityId + Utility.COMMA + getEmployeeRecord() + Utility.COMMA + getWorkArea() + Utility.COMMA + getTask();       
        	
        	return assignmentService.appendMatchingAssignEffectiveDate(this, universityId, timeBlockAssignString);          
        }
        return Utility.getDefaultStringValue();
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public List getAuthorizationExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public Class getRequiredFieldsLogic() {
        return TimeBlockRequiredFieldsLogic.class;
    }

    //Method used to perform Set operations. 
    //Add any Timeblock editable field. 
    //Note: Fields added here MUST be included in the documentHeader.timeblock hidden list in the timesheetDocument.jsp file
    //<hr:hiddenMaintainableList nestingPath="timesheetDocument.documentHeader.timeBlock" hiddenFields="
    // documentId,employeeRecord,workArea,task,earnCode,beginTime,endTime,beginTsTz,endTsTz,createdByClock,
    // hours,overtimeEarnCode,shiftEarnCode,shiftHours,premiumEarnCode,premiumHours,userUniversityId,timestamp"/>

    
    private String createTimeBlockKey(TimeBlock timeBlock) {
        StringBuffer key = new StringBuffer();
        TimelessDate timeBlockDate = new TimelessDate(timeBlock.getBeginTime().getDate()); //same date for each timeblock
        key.append(timeBlock.getDocumentId());
        key.append(timeBlock.getEmployeeRecord());
        key.append(timeBlock.getWorkArea());
        key.append(timeBlock.getTask());
        key.append(timeBlock.getEarnCode());
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(timeBlock.getHours())){
            key.append(timeBlock.getHours().setScale(2,BigDecimal.ROUND_HALF_UP));
        }
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(timeBlock.getOvertimeEarnCode())){
          key.append(timeBlock.getOvertimeEarnCode());
        }
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(timeBlock.getPremiumEarnCode())){
            key.append(timeBlock.getPremiumEarnCode());
        }
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(timeBlock.getShiftEarnCode())){
            key.append(timeBlock.getShiftEarnCode());
        }
        
        key.append(timeBlockDate.getYear());
        key.append(timeBlockDate.getMonth());
        key.append(timeBlockDate.getDay());
        key.append(timeBlock.getBeginTime().get24Hour());
        key.append(timeBlock.getBeginTime().getMinute());
        key.append(timeBlock.getEndTime().get24Hour());
        key.append(timeBlock.getEndTime().getMinute());
        key.append(timeBlock.getBeginTsTz());
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(timeBlock.getTimestamp())) {
            key.append(timeBlock.getTimestamp().get24Hour());
            key.append(timeBlock.getTimestamp().getMinute());
        }
        key.append(timeBlock.getTimestampTz());
        key.append(timeBlock.getEndTsTz());
        return key.toString();
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof TimeBlock)) {
            return false;
        } else {
            return createTimeBlockKey(this).equals(createTimeBlockKey((TimeBlock)o));
        }
    }     
    
    public  int hashCode() {
        return  (createTimeBlockKey(this)).hashCode();
    }
}
