package edu.iu.uis.hr.tk.employee.entity;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class LeaveAccrual extends AbstractLeaveAccrual implements PersistentMaintainedEntity {
	private static final Logger LOG = Logger.getLogger(LeaveAccrual.class);
	public static final String LEAVE_BALANCE = "leaveBalance";
	public static final String PLAN_TYPE = "planType";
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(LEAVE_BALANCE);     
        LOGIC_EXEMPT_PROPERTY_NAMES.add(PLAN_TYPE);
    }

	public LeaveAccrual() {
		super();
		setCarryoverHours(new BigDecimal(0));
		setEarnedYearToDateHours(new BigDecimal(0));
		setAdjustYearToDateHours(new BigDecimal(0));
		setBoughtYearToDateHours(new BigDecimal(0));
		setAdjustUnprocessedHours(new BigDecimal(0));
		setBoughtUnprocessedHours(new BigDecimal(0));		
		setTakenYearToDateHours(new BigDecimal(0));
		setSoldYearToDateHours(new BigDecimal(0));
		setTakenUnprocessedHours(new BigDecimal(0));
		setSoldUnprocessedHours(new BigDecimal(0));
	}
    
	public int compareTo(Object o){
		int result = 0;
		if (o instanceof LeaveAccrual) {
			LeaveAccrual compareObj = (LeaveAccrual)o;
			result = this.getUniversityId().compareTo(compareObj.getUniversityId());
			if (result == 0) {
				result = this.getPlanType().compareTo(compareObj.getPlanType());
				if (result == 0) {
					if (this.getAccrualProcessDate() == null) {
						result = 1;
					} else if (compareObj.getAccrualProcessDate() == null) {
						result = -1;
					} else {
						result = this.getAccrualProcessDate().compareTo(compareObj.getAccrualProcessDate());
					}
				}
			}			 
		}
		return result;
	}
	
	public BigDecimal getLeaveBalance() {
		
		BigDecimal result = new BigDecimal(0);
		
		result = result.add(getCarryoverHours());
		result = result.add(getEarnedYearToDateHours());
		result = result.add(getAdjustYearToDateHours());
		result = result.add(getBoughtYearToDateHours());
		result = result.add(getAdjustUnprocessedHours());
		result = result.add(getBoughtUnprocessedHours());
		
		result = result.subtract(getTakenYearToDateHours());
		result = result.subtract(getSoldYearToDateHours());
		result = result.subtract(getTakenUnprocessedHours());
		result = result.subtract(getSoldUnprocessedHours());
		
		return result;

	}

	public void setLeaveBalance() {
		
	}
	
    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public List getAuthorizationExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }
}

