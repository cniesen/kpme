package edu.iu.uis.hr.tk.employee.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.client.NonDatabaseBigDecimalPropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseDatePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class LeaveBalance extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
	private static final String LEAVE_BALANCE = "leaveBalance";
	private static final String PLAN_TYPE = "planType";
	private static final String PLAN_DESCRIPTION = "planDescription";

	private BigDecimal leaveBalance;
	private String planType;
	private String planDescription;
	private Date accrualProcessDate;
	
	private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();
    static {
        CUSTOM_PROPERTY_DESCRIPTORS.put(LEAVE_BALANCE, new NonDatabaseBigDecimalPropertyDescriptor(LEAVE_BALANCE,true,2,5));
        CUSTOM_PROPERTY_DESCRIPTORS.put(PLAN_TYPE, new NonDatabaseStringPropertyDescriptor(PLAN_TYPE,2));
        CUSTOM_PROPERTY_DESCRIPTORS.put(PLAN_DESCRIPTION, new NonDatabaseStringPropertyDescriptor(PLAN_DESCRIPTION,30));
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.ACCRUAL_PROCESS_DATE, new NonDatabaseDatePropertyDescriptor(FieldNames.ACCRUAL_PROCESS_DATE));
    }
    
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(LEAVE_BALANCE);     
        LOGIC_EXEMPT_PROPERTY_NAMES.add(PLAN_TYPE);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(PLAN_DESCRIPTION);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.ACCRUAL_PROCESS_DATE);
    }
    
    public LeaveBalance() {
    	setLeaveBalance(new BigDecimal(0));
    	setPlanType("");
    	setPlanDescription("");
    	setAccrualProcessDate(new Date());
    }
    
	public LeaveBalance(LeaveAccrual leaveAccrual) {
    	setLeaveBalance(leaveAccrual.getLeaveBalance());
    	setPlanType(leaveAccrual.getPlanType());
    	setAccrualProcessDate(leaveAccrual.getAccrualProcessDate());
    }
    
	protected Map getPropertyDescriptorMap() {
		return CUSTOM_PROPERTY_DESCRIPTORS;
	}

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public List getAuthorizationExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public BigDecimal getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(BigDecimal leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}
    
    public Date getAccrualProcessDate() {
    	return accrualProcessDate;
	}
    
    public void setAccrualProcessDate(Date accrualProcessDate) {
    	this.accrualProcessDate = accrualProcessDate;
	}
    
    public boolean isDisplayOnly() {
    	return true;
    }
}
