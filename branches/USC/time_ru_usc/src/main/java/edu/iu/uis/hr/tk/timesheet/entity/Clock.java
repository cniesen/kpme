package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import edu.iu.uis.hr.Event;
import edu.iu.uis.hr.OpenEvent;
import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.Time;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.client.NonDatabaseBooleanPropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.client.BreakInEvent;
import edu.iu.uis.hr.tk.timesheet.client.BreakOutEvent;
import edu.iu.uis.hr.tk.timesheet.client.ClockInEvent;
import edu.iu.uis.hr.tk.timesheet.client.ClockOutEvent;
import edu.iu.uis.hr.tk.timesheet.client.EditEvent;
import edu.iu.uis.hr.tk.timesheet.client.LunchInEvent;
import edu.iu.uis.hr.tk.timesheet.client.LunchOutEvent;
import edu.iu.uis.hr.tk.timesheet.entity.logic.AdjustForTimeZoneLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.AssignmentAuthorizationClockLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.BreakInActionAllowedLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.BreakOutActionAllowedLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockInActionAllowedLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockOutActionAllowedLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockRequiredFieldsLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockRequiredForLunchLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockTimestampAllowedLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.InvalidClockActionAssignmentLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.LunchInActionAllowedLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.LunchOutActionAllowedLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.RemoveBreakSecondsLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.RoundClockTimeLogic;

public class Clock extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
    private static final String WORK_STATUS = "workStatus";
    public static final String CLOCK_ASSIGNMENT = "clockAssignment";
    
    public static final String clockedInTimeExceededMessage = "The TIME system found that you were clocked in when closing the current pay period. You were automatically clocked out at 11:59pm on the day of your last clock in. Please contact your supervisor if corrections are needed."; 
    
    //Hour distribution feature
    public static final String HOUR_DISTRIBUTION_ALLOWED = "hourDistributionAllowed";
    public static final String USER_DOING_HOUR_DISTRIBUTION = "userDoingHourDistribution";
    
    
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(WORK_STATUS);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.CLOCK_TIME);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.TIME_ZONE);
    }
    
    public static final Time END_OF_DAY_TIME = new Time("11:59 PM");
    public static final Time BEGIN_OF_DAY_TIME = new Time("12:00 AM");
    public static final int MAX_CLOCK_IN_HOURS = 24;
    
    public static final String CLOCK_IN = "CI";
    public static final String CLOCK_OUT = "CO";
    public static final String LUNCH_IN = "LI";
    public static final String LUNCH_OUT = "LO";
    public static final String BREAK_IN = "BI";
    public static final String BREAK_OUT = "BO";
    public static final List ON_THE_CLOCK_CODES = new ArrayList();
    static {
        ON_THE_CLOCK_CODES.add(CLOCK_IN);
        ON_THE_CLOCK_CODES.add(LUNCH_IN);
        ON_THE_CLOCK_CODES.add(BREAK_IN);
    }
    public static final List OFF_THE_CLOCK_CODES = new ArrayList();
    static {
        OFF_THE_CLOCK_CODES.add(CLOCK_OUT);
        OFF_THE_CLOCK_CODES.add(LUNCH_OUT);
        OFF_THE_CLOCK_CODES.add(BREAK_OUT);
    }
    
    public static final List RESTRICTIVE_CLOCK_CODES = new ArrayList();
    static {
    	RESTRICTIVE_CLOCK_CODES.add(CLOCK_IN);
    	RESTRICTIVE_CLOCK_CODES.add(LUNCH_IN);
    	RESTRICTIVE_CLOCK_CODES.add(LUNCH_OUT);
    	RESTRICTIVE_CLOCK_CODES.add(BREAK_IN);
    	RESTRICTIVE_CLOCK_CODES.add(BREAK_OUT);
    }    
    
    private static final List OUT_TO_LUNCH_CODES = new ArrayList();
    static {
        OUT_TO_LUNCH_CODES.add(LUNCH_OUT);
    }
    private static final List OUT_ON_BREAK_CODES = new ArrayList();
    static {
        OUT_ON_BREAK_CODES.add(BREAK_OUT);
    }

    public static final String ASSIGNMENT_LOCATION_JAVASCRIPT_EVENT_HANDLER = "calculateDisplayTimeOffset(name)";
    private static final Map JAVASCRIPT_EVENT_HANDLERS = new HashMap();
    static {
    	JAVASCRIPT_EVENT_HANDLERS.put(CLOCK_ASSIGNMENT, ASSIGNMENT_LOCATION_JAVASCRIPT_EVENT_HANDLER);
    }
    
    private String workStatus;
    private String assignment;
    private String clockAssignment;    
    private Timestamp clockTime;
    private String timeZone;
    private ClockLog clockLog;
    private boolean clockRequiredForLunch=true;
    private boolean hourDistributionAllowed;
    private boolean userDoingHourDistribution;
    private boolean hasClockAssignment;
    private Map propertyDescriptorMap;
    
    public Clock() {
        super();
        setAssignment(Utility.getDefaultStringValue());
        setWorkStatus(Utility.getDefaultStringValue());
        setClockTime(new TimedDate().getTimestamp());
        setClockLog(new ClockLog());
        setHasClockAssignment(false);
        this.propertyDescriptorMap = createPropertyDescriptorMap();
    }

    public Clock(ClockLog clockLog) {
        this();
        setClockLog(clockLog);
        setAssignment(getClockLog().getAssignment());
        setWorkStatus(getClockLog().getStatus());
    }

    protected Map createPropertyDescriptorMap() {
        Map propertyDescriptorMap = new HashMap();
    	propertyDescriptorMap.put(WORK_STATUS, new NonDatabaseStringPropertyDescriptor(WORK_STATUS, true, 100));
    	propertyDescriptorMap.put(Assignment.ASSIGNMENT, Assignment.ASSIGNMENT_PROPERTY_DESCRIPTOR);
    	propertyDescriptorMap.put(Clock.CLOCK_ASSIGNMENT, new NonDatabaseStringPropertyDescriptor(CLOCK_ASSIGNMENT, 100));
    	propertyDescriptorMap.put(FieldNames.CLOCK_TIME, AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.CLOCK_TIME, ClockLog.class));
        NonDatabaseBooleanPropertyDescriptor userDoingHourDistributionPropertyDescriptor = new NonDatabaseBooleanPropertyDescriptor(USER_DOING_HOUR_DISTRIBUTION);
        userDoingHourDistributionPropertyDescriptor.setDisplayOnly(false);
        propertyDescriptorMap.put(USER_DOING_HOUR_DISTRIBUTION, userDoingHourDistributionPropertyDescriptor);
        return propertyDescriptorMap;
    }
    
    
    public boolean isParentModeInherited() {
        return false;
    }
    
    public Class getRequiredFieldsLogic() {
        return ClockRequiredFieldsLogic.class;
    }
    
    public boolean isOnTheClock() {
        if (ON_THE_CLOCK_CODES.contains(getClockLog().getAction())) {
            return true;
        }
        return false;
    }

    public boolean isOutToLunch() {
        if (OUT_TO_LUNCH_CODES.contains(getClockLog().getAction())) {
            return true;
        }
        return false;
    }

    public boolean isOutOnBreak() {
        if (OUT_ON_BREAK_CODES.contains(getClockLog().getAction())) {
            return true;
        }
        return false;
    }
    
    public boolean isOnRestrictiveClock() {
        if (RESTRICTIVE_CLOCK_CODES.contains(getClockLog().getAction())) {
            return true;
        }
        return false;
    }

    public Map getPropertyDescriptorMap() {
        return this.propertyDescriptorMap;
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public Class getModeAuthorization() {
        return ClockModeAuthorization.class;
    }

    protected List getUncommonOperationalLogics(Event event) {
        List operationalLogics = new ArrayList();
        if (event instanceof ClockInEvent) {
            operationalLogics.add(InvalidClockActionAssignmentLogic.class);     
        	operationalLogics.add(AdjustForTimeZoneLogic.class);
            operationalLogics.add(ClockInActionAllowedLogic.class);
            operationalLogics.add(RoundClockTimeLogic.class);
            operationalLogics.add(ClockRequiredForLunchLogic.class);
            operationalLogics.add(AssignmentAuthorizationClockLogic.class);       
        } else if (event instanceof ClockOutEvent) {  
        	operationalLogics.add(AdjustForTimeZoneLogic.class);        	
            operationalLogics.add(ClockOutActionAllowedLogic.class);
            operationalLogics.add(RoundClockTimeLogic.class);
            operationalLogics.add(AssignmentAuthorizationClockLogic.class);
        } else if (event instanceof LunchInEvent) {
            operationalLogics.add(InvalidClockActionAssignmentLogic.class);     
        	operationalLogics.add(AdjustForTimeZoneLogic.class);       	 
            operationalLogics.add(LunchInActionAllowedLogic.class);
            operationalLogics.add(RoundClockTimeLogic.class);
            operationalLogics.add(AssignmentAuthorizationClockLogic.class);          
        } else if (event instanceof LunchOutEvent) {     
        	operationalLogics.add(AdjustForTimeZoneLogic.class);
            operationalLogics.add(LunchOutActionAllowedLogic.class);
            operationalLogics.add(RoundClockTimeLogic.class);
            operationalLogics.add(AssignmentAuthorizationClockLogic.class);
        } else if (event instanceof BreakInEvent) {
            operationalLogics.add(InvalidClockActionAssignmentLogic.class);     
        	operationalLogics.add(AdjustForTimeZoneLogic.class);
        	operationalLogics.add(BreakInActionAllowedLogic.class);
            operationalLogics.add(ClockRequiredForLunchLogic.class);       
            operationalLogics.add(RemoveBreakSecondsLogic.class);
            operationalLogics.add(AssignmentAuthorizationClockLogic.class);     
        } else if (event instanceof BreakOutEvent) {  
        	operationalLogics.add(AdjustForTimeZoneLogic.class);
            operationalLogics.add(BreakOutActionAllowedLogic.class);
            operationalLogics.add(RemoveBreakSecondsLogic.class);
            operationalLogics.add(AssignmentAuthorizationClockLogic.class);
        }  else if (event instanceof OpenEvent) {
            operationalLogics.add(ClockRequiredForLunchLogic.class);
        } else if (event instanceof SaveEvent) {
            operationalLogics.add(ClockRequiredForLunchLogic.class);
        } else if (event instanceof EditEvent) {
            operationalLogics.add(ClockRequiredForLunchLogic.class);
        } 
        if (!(event instanceof EditEvent) && !(event instanceof OpenEvent) && !(event instanceof SaveEvent)) {
           operationalLogics.add(ClockTimestampAllowedLogic.class);
        }
        return operationalLogics;
    }

    public void update(ClockLog clockLog) {
        if (clockLog != null) {
            this.clockLog = clockLog;
            setWorkStatus(getClockLog().getStatus());
            setAssignment(getClockLog().getAssignment());
        }
    }

    public String getClockAssignment() {
        return clockAssignment;
    }

    public void setClockAssignment(String clockAssignment) {
        if (clockAssignment != null) {
            this.clockAssignment = clockAssignment;
            this.assignment = clockAssignment;
        }
    }
    
    public String getAssignment() {
        return clockAssignment;
    }

    public void setAssignment(String assignment) {
        if (assignment != null) {
            this.clockAssignment = assignment;
            this.assignment = assignment;
        }
    }

    public ClockLog getClockLog() {
        return clockLog;
    }

    public void setClockLog(ClockLog clockLog) {
        if (clockLog != null) {
            this.clockLog = clockLog;
        }
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        if (workStatus != null) {
            this.workStatus = workStatus;
        }
    }

    public Timestamp getClockTime() {
        return clockTime;
    }

    public void setClockTime(Timestamp clockTime) {
        if (clockTime != null) {
            this.clockTime = clockTime;
        }
    }

    public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isClockRequiredForLunch() {
        return clockRequiredForLunch;
    }

    public void setClockRequiredForLunch(boolean clockRequiredForLunch) {
            this.clockRequiredForLunch = clockRequiredForLunch;
    }

    public boolean isHourDistributionAllowed() {
        return hourDistributionAllowed;
    }

    public void setHourDistributionAllowed(boolean hourDistributionAllowed) {
            this.hourDistributionAllowed = hourDistributionAllowed;
    }

    public boolean isUserDoingHourDistribution() {
        return userDoingHourDistribution;
    }

    public void setUserDoingHourDistribution(boolean userDoingHourDistribution) {
            this.userDoingHourDistribution = userDoingHourDistribution;
    }

    public Map getJavascriptEventHandlers() {
    	return JAVASCRIPT_EVENT_HANDLERS;
    }

	public boolean isHasClockAssignment() {
		return hasClockAssignment;
	}

	public void setHasClockAssignment(boolean hasClockAssignment) {
		this.hasClockAssignment = hasClockAssignment;
	}
	
	public static String getClockActionDescription(String action) {
		if (StringUtils.equalsIgnoreCase(action, CLOCK_IN)) {
			return "Clock In";
		}
		if (StringUtils.equalsIgnoreCase(action, CLOCK_OUT)) {
			return "Clock Out";
		}
		if (StringUtils.equalsIgnoreCase(action, LUNCH_IN)) {
			return "Lunch In";
		}
		if (StringUtils.equalsIgnoreCase(action, LUNCH_OUT)) {
			return "Lunch Out";
		}
		if (StringUtils.equalsIgnoreCase(action, BREAK_IN)) {
			return "Break In";
		}
		if (StringUtils.equalsIgnoreCase(action, BREAK_OUT)) {
			return "Break out";
		}
		return "";
	}

}
