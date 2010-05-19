package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.Translate;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.service.TranslateService;
import edu.iu.uis.hr.tk.employee.entity.LeaveAccrual;
import edu.iu.uis.hr.tk.employee.service.LeaveAccrualService;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class ExceedLeaveBalanceLogic extends AbstractLogic implements OperationalLogic {

	private static final Logger LOG = Logger.getLogger(ExceedLeaveBalanceLogic.class);

//	private LeaveAccrualService leaveAccrualService;
//	private TranslateService translateService;
	private Map leaveMap = new HashMap();

	// TODO these constants are also in TimesheetServiceImpl. Should centralize
	// somewhere...
	private static final List LEAVE_PLAN_TYPES = new ArrayList();
	static {
		LEAVE_PLAN_TYPES.add("5V");
		LEAVE_PLAN_TYPES.add("51");
		LEAVE_PLAN_TYPES.add("50");
		LEAVE_PLAN_TYPES.add("5X");
		LEAVE_PLAN_TYPES.add("5Y");

	}
	private static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal(0);

	public boolean isMatch() {
		return true;
	}

	public void execute(Entity entity) {
		if (!(entity instanceof TimesheetDocument)) {
			throw new IllegalArgumentException("ExceedLeaveBalanceLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
		}

		boolean hasLeaveAppropriateJobs = false;
    	TypedPersistentMaintainedEntityList jobs = ((TimesheetDocument)entity).getJobs();
    	for (Iterator iter = jobs.iterator(); iter.hasNext();) {
    		Job job = (Job)iter.next();
    		if (!"H".equalsIgnoreCase(job.getEmployeeType())) {
    			hasLeaveAppropriateJobs = true;
    		}
    	}
		
		
    	//TODO - this check is supposed to eliminate db calls for getting leave accrual when no accruals should exist
    	if (hasLeaveAppropriateJobs){

			List hoursDetails = ((TimesheetDocument) entity).getHours().getHoursDetails();
	
			// go get the leave balances for all leave plan types for the empl
			// store them in a map of key:erncd value:hours_avail
			initializeLeaveMap(((TimesheetDocument) entity).getDocumentHeader().getUniversityId(), ((TimesheetDocument) entity).getDocumentHeader().getPayEndDate());
	
			// loop through every block on the timesheet.
			// for each block, get the erncd and hours (is it hours or end-start? or
			// do we need to figure that out each time?)
			// determine the leave plan type to which the erncd belongs
			// subtract the erncd hours from the hours on the plan type in the map
			// we created earlier
			if (Utility.hasValue(hoursDetails)) {
				Iterator hoursDetailsItr = hoursDetails.iterator();
				while (hoursDetailsItr.hasNext()) {
					HoursDetail hoursDetail = (HoursDetail) hoursDetailsItr.next();
					TypedPersistentMaintainedEntityList hourDetails = (hoursDetail).getHourDetails();
					Iterator hourDetailsItr = hourDetails.iterator();
					while (hourDetailsItr.hasNext()) {
						HourDetail hourDetail = (HourDetail) hourDetailsItr.next();
						String erncd = hourDetail.getEarnCode();
						ArrayList plans = (ArrayList) getLeaveAccrualService().getEarningsAccrualPlans(erncd, ((TimesheetDocument) entity).getDocumentHeader().getPayEndDate());
						for (Iterator iter = plans.iterator(); iter.hasNext();) {
							String planType = (String) iter.next();
							if (LEAVE_PLAN_TYPES.contains(planType)) {
								// TODO is the hours property always the right place to go?
								BigDecimal hoursAgainstPlan = hourDetail.getHours();
								if (hourDetail.getHours() == null) {
									hoursAgainstPlan = hourDetail.getTimeSpanHours();
								}
								BigDecimal oldBalance = (BigDecimal) leaveMap.get(planType);
								BigDecimal newBalance = oldBalance.subtract(hoursAgainstPlan);
								leaveMap.put(planType, newBalance);
								if (newBalance.compareTo(BIG_DECIMAL_ZERO) < 0) {
									String planDescription = ((Translate) getTranslateService().getTranslate(FieldNames.PLAN_TYPE, planType, ((TimesheetDocument) entity).getDocumentHeader().getPayEndDate())).getLabel();
									hourDetail.getEntityWarnings().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.EXCEED_LEAVE_BALANCE, new String[] { planDescription }));
									hoursDetail.setTabStatus("true");
								}
							}
						}
					}
				}
			}
    	}
	}

	
	private void initializeLeaveMap(String universityId, Date accrualProcessDate) {
		for (Iterator iter = LEAVE_PLAN_TYPES.iterator(); iter.hasNext();) {
			String planType = (String) iter.next();
			LeaveAccrual leaveAccrual = getLeaveAccrualService().getLeaveAccrual(universityId, planType, accrualProcessDate);
			if (leaveAccrual.getPlanType() != null) {
				leaveMap.put(planType, leaveAccrual.getLeaveBalance());
			} else {
				leaveMap.put(planType, BIG_DECIMAL_ZERO);
			}
		}
	}

	public LeaveAccrualService getLeaveAccrualService() {
		return (LeaveAccrualService)Context.getService(LeaveAccrualService.class);
	}

	public TranslateService getTranslateService() {
		return (TranslateService) Context.getService(TranslateService.class);
	}

}
