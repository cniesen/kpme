package edu.iu.uis.hr.tk.timesheet.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.Event;
import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.Time;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.client.NonDatabaseBigDecimalPropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.job.funding.entity.EarnCode;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRule;
import edu.iu.uis.hr.tk.timesheet.client.ClockOutEvent;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockValidIpAddressLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.HourDetailRequiredFieldsLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.HourDetailRoundingLogic;

/**
 * 
 * <p>Title: HourDetail</p>
 * <p>Description: Displays the earn code, assignment, begin/end times, etc on a TimesheetDocument.</p>
 * 
 */


public class HourDetail extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity, Comparable {

    public static final String HOURS_DETAIL_FIELD_NAME = "hoursDetail";
    public static final String HOUR_DISTRIBUTION_PERCENT_FIELD_NAME = "hourDistributionPercent";
    public static final int NAME_FIELD_LENGTH = 50;

    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(HOURS_DETAIL_FIELD_NAME);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.USER_UNIVERSITY_ID);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.TIMESTAMP);
        //LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.END_TIME);
        //LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.BEGIN_TIME);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.OVERTIME_HOURS);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.OVERTIME_EARN_CODE);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.SHIFT_HOURS);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.SHIFT_EARN_CODE);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.PREMIUM_HOURS);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.PREMIUM_EARN_CODE);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(HOUR_DISTRIBUTION_PERCENT_FIELD_NAME);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.BEGIN_TS_TZ);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.END_TS_TZ);
//        LOGIC_EXEMPT_PROPERTY_NAMES.add("beginTimeOriginal");
//        LOGIC_EXEMPT_PROPERTY_NAMES.add("endTimeOriginal");
    }

    private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabasePropertyDescriptor documentIdPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.DOCUMENT_ID, DocumentHeader.class);
        documentIdPropertyDescriptor.setDisplayOnly(true);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.DOCUMENT_ID, documentIdPropertyDescriptor);
        NonDatabaseStringPropertyDescriptor namePropertyDescriptor = new NonDatabaseStringPropertyDescriptor(FieldNames.NAME, NAME_FIELD_LENGTH);
        namePropertyDescriptor.setDisplayOnly(true);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.NAME, namePropertyDescriptor);

        CUSTOM_PROPERTY_DESCRIPTORS.put(Assignment.ASSIGNMENT, Assignment.ASSIGNMENT_PROPERTY_DESCRIPTOR);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.EARN_CODE, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.EARN_CODE, EarnCode.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.BEGIN_TIME, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.BEGIN_TIME, ShiftDifferentialRule.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.END_TIME, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.END_TIME, ShiftDifferentialRule.class));

//        NonDatabasePropertyDescriptor beginTimeOriginalPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor("beginTimeOriginal", TimeBlock.class);
//        beginTimeOriginalPropertyDescriptor.setDisplayOnly(true);
//        CUSTOM_PROPERTY_DESCRIPTORS.put("beginTimeOriginal", beginTimeOriginalPropertyDescriptor);
//        NonDatabasePropertyDescriptor endTimeOriginalPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor("endTimeOriginal", TimeBlock.class);
//        endTimeOriginalPropertyDescriptor.setDisplayOnly(true);
//        CUSTOM_PROPERTY_DESCRIPTORS.put("endTimeOriginal", endTimeOriginalPropertyDescriptor);
        
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.BEGIN_TS_TZ, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.BEGIN_TS_TZ, TimeBlock.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.END_TS_TZ, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.END_TS_TZ, TimeBlock.class));
        NonDatabasePropertyDescriptor hoursPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.HOURS, TimeBlock.class);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.HOURS, hoursPropertyDescriptor);
        NonDatabasePropertyDescriptor createdByClockPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.CREATED_BY_CLOCK, TimeBlock.class);
        createdByClockPropertyDescriptor.setDisplayOnly(true);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.CREATED_BY_CLOCK, createdByClockPropertyDescriptor);
        NonDatabasePropertyDescriptor userUniversityIdPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.USER_UNIVERSITY_ID, TimeBlock.class);
        userUniversityIdPropertyDescriptor.setDisplayOnly(true);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.USER_UNIVERSITY_ID, userUniversityIdPropertyDescriptor);
        NonDatabasePropertyDescriptor timestampPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.TIMESTAMP, TimeBlock.class);
        timestampPropertyDescriptor.setDisplayOnly(true);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.TIMESTAMP, timestampPropertyDescriptor);
        NonDatabasePropertyDescriptor overtimeHoursPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.OVERTIME_HOURS, TimeBlock.class);
        overtimeHoursPropertyDescriptor.setDisplayOnly(true);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.OVERTIME_HOURS, overtimeHoursPropertyDescriptor);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.OVERTIME_EARN_CODE, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.OVERTIME_EARN_CODE, TimeBlock.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.SHIFT_HOURS, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.SHIFT_HOURS, TimeBlock.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.SHIFT_EARN_CODE, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.SHIFT_EARN_CODE, TimeBlock.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.PREMIUM_HOURS, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.PREMIUM_HOURS, TimeBlock.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.PREMIUM_EARN_CODE, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.PREMIUM_EARN_CODE, TimeBlock.class));
        CUSTOM_PROPERTY_DESCRIPTORS.put(HOUR_DISTRIBUTION_PERCENT_FIELD_NAME, new NonDatabaseBigDecimalPropertyDescriptor(HOUR_DISTRIBUTION_PERCENT_FIELD_NAME, 5, 2));
    }

    public static final String ASSIGNMENT_JAVASCRIPT_EVENT_HANDLER = "initializeEarnCodeSelection(name);populateEarnCodes(name)";
    public static final String EARN_CODE_JAVASCRIPT_EVENT_HANDLER = "setEditableFields(name)";
    private static final Map JAVASCRIPT_EVENT_HANDLERS = new HashMap();
    static {
    	JAVASCRIPT_EVENT_HANDLERS.put(Assignment.ASSIGNMENT, ASSIGNMENT_JAVASCRIPT_EVENT_HANDLER);
    	JAVASCRIPT_EVENT_HANDLERS.put(EarnCode.EARN_CODE, EARN_CODE_JAVASCRIPT_EVENT_HANDLER);
    }
    
    private HoursDetail hoursDetail;
    private String assignment;
    private String earnCode;
    private Time beginTime;
    private Time endTime;
//    private Time beginTimeOriginal;
//    private Time endTimeOriginal;
    private String beginTsTz;
    private String endTsTz;
    private BigDecimal hours;
    private String overtimeEarnCode;
    private BigDecimal overtimeHours;
    private String shiftEarnCode;
    private BigDecimal shiftHours;
    private String premiumEarnCode;
    private BigDecimal premiumHours;
    private boolean createdByClock;
    private String userUniversityId;
    private Timestamp timestamp;
    private BigDecimal hourDistributionPercent; 

    public HourDetail() {
        super();
        setAssignment(Utility.getDefaultStringValue());
        setEarnCode(Utility.getDefaultStringValue());
        setOvertimeEarnCode(Utility.getDefaultStringValue());
        setShiftEarnCode(Utility.getDefaultStringValue());
        setPremiumEarnCode(Utility.getDefaultStringValue());
        setUserUniversityId(Utility.getDefaultStringValue());
        setBeginTsTz(Utility.getDefaultStringValue());
        setEndTsTz(Utility.getDefaultStringValue());
    }
    
    public HourDetail(HourDetail source) {
        super();
        setAssignment(source.getAssignment());
        setBeginTime(source.getBeginTime());
        setBeginTsTz(source.getBeginTsTz());
        setCreatedByClock(source.isCreatedByClock());
        setEarnCode(source.getEarnCode());
        setEndTime(source.getEndTime());
        setEndTsTz(source.getEndTsTz());
        setHours(source.getHours());
        setTimestamp(new Timestamp());
    }
    
    public HourDetail(TimeBlock timeBlock) {
    	super();
	    this.setBeginTime(new Time(timeBlock.getBeginTime()));
	    this.setEndTime(new Time(timeBlock.getEndTime()));
	    this.setBeginTsTz(timeBlock.getBeginTsTz());
	    this.setEndTsTz(timeBlock.getEndTsTz());
	    this.setCreatedByClock(timeBlock.isCreatedByClock());
	    this.setEarnCode(timeBlock.getEarnCode());
	    this.setHours(timeBlock.getHours());
	    HoursDetail hoursDetail = new HoursDetail();
	    hoursDetail.setDate(timeBlock.getBeginTime().getDate());
	    this.setHoursDetail(hoursDetail);
    }

    public Map getPropertyDescriptorMap() {
        return CUSTOM_PROPERTY_DESCRIPTORS;
    }

    public Class getRequiredFieldsLogic() {
        return HourDetailRequiredFieldsLogic.class;
    }

    public Class getModeAuthorization() {
        return HourDetailModeAuthorization.class;
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public List getAuthorizationExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public HoursDetail getHoursDetail() {
        return hoursDetail;
    }

    public void setHoursDetail(HoursDetail hoursDetail) {
        if (hoursDetail != null) {
            this.hoursDetail = hoursDetail;
        }
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        if (assignment != null) {
            this.assignment = assignment;
        }
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        if (beginTime != null) {
            this.beginTime = beginTime;
        }
       	if(beginTsTz != null && beginTime != null){
    		beginTime.setZone(beginTsTz);
    	}
    }

    public boolean isCreatedByClock() {
        return createdByClock;
    }

    public void setCreatedByClock(boolean createdByClock) {
        this.createdByClock = createdByClock;
    }

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        if (earnCode != null) {
            this.earnCode = earnCode;
        }
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        if (endTime != null) {
            this.endTime = endTime;
        }
		if(endTsTz != null && endTime != null){
    		endTime.setZone(endTsTz);
		}
    }

    public String getBeginTsTz() {
		return beginTsTz;
	}

	public void setBeginTsTz(String beginTsTz) {
		this.beginTsTz = beginTsTz;
    	if(beginTsTz != null && beginTime != null){
    		beginTime.setZone(beginTsTz);
    	}
	}

	public String getEndTsTz() {
		return endTsTz;
	}

	public void setEndTsTz(String endTsTz) {
		this.endTsTz = endTsTz;
		if(endTsTz != null && endTime != null){
    		endTime.setZone(endTsTz);
    	}
	}

	public BigDecimal getHours() {
        return this.hours;
    }

    public void setHours(BigDecimal hours) {
        // TODO why would the hours be null? rpiercy
        if (hours != null) {
            this.hours = hours;
        } else {
            this.hours = new BigDecimal(0);
        }
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        if (timestamp != null) {
            this.timestamp = timestamp;
        }
    }

    public String getUserUniversityId() {
        return userUniversityId;
    }

    public void setUserUniversityId(String userUniversityId) {
        if (userUniversityId != null) {
            this.userUniversityId = userUniversityId;
        }
    }

    public BigDecimal getTimeSpanHours() {
    	Date date;
    	if(this.getHoursDetail()!= null){
    		date = this.getHoursDetail().getDate(); 
    	}else
    		date = null;
        return Time.getTimeDifferenceInHours(getEndTime(), getBeginTime(), date);
    }
    
    public int compareTo(Object o) {
        int result = -1;
        if (o instanceof HourDetail) {
            HourDetail compareObj = (HourDetail)o;
            if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(this.getBeginTime()) || !edu.iu.uis.hr.entity.logic.Utility.hasValue(compareObj.getBeginTime())) {
                return result;
            }
            result = (new Integer(this.getBeginTime().get24Hour()).compareTo(new Integer(compareObj.getBeginTime().get24Hour())));
            if (result == 0) { //both objects have the same hour, compares minutes
                result = (this.getBeginTime().getMinute().compareTo(compareObj.getBeginTime().getMinute()));
            }
        }
        return result;
    }

    public String getOvertimeEarnCode() {
        return overtimeEarnCode;
    }

    public void setOvertimeEarnCode(String overtimeHoursEarnCode) {
        if (overtimeHoursEarnCode != null) {
            this.overtimeEarnCode = overtimeHoursEarnCode;
        }
    }

    public BigDecimal getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(BigDecimal overtimeHours) {
        if (overtimeHours != null) {
            this.overtimeHours = overtimeHours;
        }
    }

    public String getPremiumEarnCode() {
        return premiumEarnCode;
    }

    public void setPremiumEarnCode(String premiumEarnCode) {
        if (premiumEarnCode != null) {
            this.premiumEarnCode = premiumEarnCode;
        }
    }

    public BigDecimal getPremiumHours() {
        return premiumHours;
    }

    public void setPremiumHours(BigDecimal premiumHours) {
        if (premiumHours != null) {
            this.premiumHours = premiumHours;
        }
    }

    public String getShiftEarnCode() {
        return shiftEarnCode;
    }

    public void setShiftEarnCode(String shiftEarnCode) {
        if (shiftEarnCode != null) {
            this.shiftEarnCode = shiftEarnCode;
        }
    }

    public BigDecimal getShiftHours() {
        return shiftHours;
    }

    public void setShiftHours(BigDecimal shiftHours) {
        if (shiftHours != null) {
            this.shiftHours = shiftHours;
        }
    }

    public Map getJavascriptEventHandlers() {
    	return JAVASCRIPT_EVENT_HANDLERS;
    }

    public BigDecimal getHourDistributionPercent() {
        return hourDistributionPercent;
    }

    public void setHourDistributionPercent(BigDecimal hourDistributionPercent) {
        if (hourDistributionPercent != null) {
            this.hourDistributionPercent = hourDistributionPercent;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	protected List getOperationalLogics(SaveEvent event) {
        List operationalLogics = new ArrayList();
        operationalLogics.add(HourDetailRoundingLogic.class);
        return operationalLogics;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getUncommonOperationalLogics(Event event) {
		List operationalLogics = new ArrayList();
		if (event instanceof ClockOutEvent) { 
	        operationalLogics.add(ClockValidIpAddressLogic.class);
		}
		return operationalLogics;
	}

	
    

//	public Time getBeginTimeOriginal() {
//		return beginTimeOriginal;
//	}

//	public void setBeginTimeOriginal(Time beginTimeOriginal) {
//		this.beginTimeOriginal = beginTimeOriginal;
//	}

//	public Time getEndTimeOriginal() {
//		return endTimeOriginal;
//	}

//	public void setEndTimeOriginal(Time endTimeOriginal) {
//		this.endTimeOriginal = endTimeOriginal;
//	}
     
}