package edu.iu.uis.hr.tk.timesheet.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TransientNonDatabaseEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class HoursSummary extends AbstractPersistentNonDatabaseEntity implements TransientNonDatabaseEntity {

    private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();

    public List hoursSummaryRecords = new ArrayList();
    public Map earnings = new TreeMap();
    public List hoursSummaryDisplay = new ArrayList();
    private Map dates = new TreeMap();
    private Map totals = new TreeMap();
    private Map assignments = new TreeMap();
    private TimesheetService timesheetService;
    private EarningService earningService;
    private static final String MAP_PRIORITY_REGULAR = "1";
    private static final String MAP_PRIORITY_GENERAL = "2";
    private static final String MAP_PRIORITY_TOTAL = "3";
    private static final String MAP_PRIORITY_OTHER = "4";
    private static final String MAP_PRIORITY_WORK_HOURS = "0";

    static {
        NonDatabasePropertyDescriptor documentIdPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.DOCUMENT_ID, DocumentHeader.class);
        documentIdPropertyDescriptor.setDisplayOnly(true);
        CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.DOCUMENT_ID, documentIdPropertyDescriptor);
    }
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.UNIVERSITY_ID);
    }
    
    public HoursSummary(TimesheetDocument timesheetDocument) {
        super();
        setTimesheetService((TimesheetService)Context.getService(TimesheetService.class));
        setEarningService((EarningService)Context.getService(EarningService.class));
        populateAssignments(timesheetDocument);
        populateHeaderAndDates(timesheetDocument.getHours());
        populateHoursSummaryRecords(timesheetDocument.getHours().getHoursDetails());
        populateEarningsAndHours(timesheetDocument.getDocumentHeader().getPayEndDate());
        populateHoursSummaryForPayPeriod(timesheetDocument.getDocumentHeader().getPayEndDate());
        
    }
    
    private void populateHoursSummaryRecords(TypedPersistentMaintainedEntityList hoursDetails) {
		Iterator hoursDetailsItr = hoursDetails.iterator();
		while (hoursDetailsItr.hasNext()) {
			Iterator hourDetailsItr = ((HoursDetail)hoursDetailsItr.next()).getHourDetails().iterator();
			while (hourDetailsItr.hasNext()) {
				HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
				if (hourDetail.getHours() != null) {
					getHoursSummaryRecords().add(hourDetail);
					getHoursSummaryRecords().addAll(getOvertimeShiftAndPremiumRecords(hourDetail));
				}
			}
		}
		
	}

	private Collection getOvertimeShiftAndPremiumRecords(HourDetail hourDetail) {
		List results = new ArrayList();
		
		if (edu.iu.uis.hr.entity.logic.Utility.hasValue(hourDetail.getOvertimeEarnCode())) {
			results.add(copyHourDetail(hourDetail, hourDetail.getOvertimeEarnCode(), hourDetail.getOvertimeHours()));
		}
		if (edu.iu.uis.hr.entity.logic.Utility.hasValue(hourDetail.getShiftEarnCode())) {
			results.add(copyHourDetail(hourDetail, hourDetail.getShiftEarnCode(), hourDetail.getShiftHours()));
		}
		if (edu.iu.uis.hr.entity.logic.Utility.hasValue(hourDetail.getPremiumEarnCode())) {
			results.add(copyHourDetail(hourDetail, hourDetail.getPremiumEarnCode(), hourDetail.getPremiumHours()));
		}
		return results;
	}

	private Object copyHourDetail(HourDetail hourDetail, String earnCode, BigDecimal hours) {
		HourDetail hourDetailCopy = new HourDetail();
		hourDetailCopy.setAssignment(hourDetail.getAssignment());
		hourDetailCopy.setBeginTime(hourDetail.getBeginTime());
		hourDetailCopy.setEarnCode(earnCode);
		hourDetailCopy.setEndTime(hourDetail.getEndTime());
		hourDetailCopy.setHours(hours);
		hourDetailCopy.setHoursDetail(hourDetail.getHoursDetail());
		hourDetailCopy.setTimestamp(hourDetail.getTimestamp());
			
		return hourDetailCopy;
	}

	private void populateAssignments(TimesheetDocument timesheetDocument) {
    	Iterator jobsItr = timesheetDocument.getJobs().iterator();
    	while (jobsItr.hasNext()) {
    		Iterator assignmentItr = ((Job)jobsItr.next()).getAssignments().iterator();
    		while (assignmentItr.hasNext()) {
    			Assignment assignment = (Assignment)assignmentItr.next();
    			getAssignments().put(assignment.getAssignmentKeyAsOptionKey(false), assignment.getAssignmentDescription().replace(',', ' '));
    		}
    	}
		
	}

	private void populateEarningsAndHours(Date payEndDate) {
    	HourDetail hoursSummaryRecord;
    	String earnCode;
		Iterator hoursSummaryRecordsItr = getHoursSummaryRecords().iterator();
		while (hoursSummaryRecordsItr.hasNext()){
			hoursSummaryRecord = (HourDetail)hoursSummaryRecordsItr.next();
			earnCode = hoursSummaryRecord.getEarnCode();
			if (!getEarnings().containsKey(earnCode) && edu.iu.uis.hr.entity.logic.Utility.hasValue(earnCode)) {
				getEarnings().put(earnCode, getEarningService().getEarnCode(earnCode, payEndDate).getLabel());
			}
			addHoursToDates(hoursSummaryRecord);
		}
		
	}

	private void addHoursToDates(HourDetail hoursSummaryRecord) {
		TimelessDate recordDate = new TimelessDate(hoursSummaryRecord.getHoursDetail().getDate());
		String earnCode = hoursSummaryRecord.getEarnCode();
		String assignmentKey = hoursSummaryRecord.getAssignment();
		BigDecimal hours;
		
		if (edu.iu.uis.hr.entity.logic.Utility.hasValue(assignmentKey) && edu.iu.uis.hr.entity.logic.Utility.hasValue(earnCode) ){
			Map earnCodeLevel = (Map) getDates().get(recordDate);
			if (!earnCodeLevel.containsKey(earnCode)) {
				earnCodeLevel.put(earnCode, new TreeMap());
			}
			Map assignmentLevel = (Map) earnCodeLevel.get(earnCode);
			if (!assignmentLevel.containsKey(assignmentKey)) {
				assignmentLevel.put(assignmentKey, new BigDecimal(0));
			}
			try {
				hours = ((BigDecimal) assignmentLevel.get(assignmentKey)).add(hoursSummaryRecord.getHours());
			} catch (RuntimeException e) {
				hours = new BigDecimal(0);
			}
			assignmentLevel.put(assignmentKey, hours);
		}		
	}

	private void populateHoursSummaryForPayPeriod(Date payEndDate) {
		String result = "";
		String lastEarnCode = "";
		Map results = new TreeMap();

		Iterator assignmentsItr = getAssignments().keySet().iterator();
		
		while (assignmentsItr.hasNext()) {
			String assignmentKey = (String)assignmentsItr.next();
			Iterator earningsItr = getEarnings().keySet().iterator();
			
			BigDecimal weeklyTotal = new BigDecimal(0);
			BigDecimal payPeriodTotal = new BigDecimal(0);
			while (earningsItr.hasNext()) {
				String earnCode = (String)earningsItr.next();
				String mapPriority;
				if (getEarningService().isRegularAttendanceEarningsEarnCode(earnCode)) {
					mapPriority = MAP_PRIORITY_REGULAR;
				} else if (getEarningService().isGeneralAttendanceEarningsEarnCode(earnCode, payEndDate)) {
					mapPriority = MAP_PRIORITY_GENERAL;
				} else {
					mapPriority = MAP_PRIORITY_OTHER;
				}				
				results.put(mapPriority + "zzz", "DIVIDER,0.00");
				
				Iterator datesItr = getDates().keySet().iterator();
				if (!lastEarnCode.equals(earnCode)) {
					results.put(mapPriority + earnCode, "EARN_CODE," + (String)getEarnings().get(earnCode)); //used to create earn code heading row that spans columns (when new earning found)
				}
				
				int dayCount = 0;
				result = "DATA," + (String)getAssignments().get(assignmentKey) + ",";
				while (datesItr.hasNext()) {
					TimelessDate timelessDate = (TimelessDate)datesItr.next();
					BigDecimal hours;
					try {
						hours = (BigDecimal) ((Map)((Map)getDates().get(timelessDate)).get(earnCode)).get(assignmentKey);
					} catch (RuntimeException e) { hours = new BigDecimal(0.00); }
					if (hours == null) {
						hours = new BigDecimal(0.00);
					}
					
					result += hours.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + ",";
					weeklyTotal = hours.add(weeklyTotal);
					if (++dayCount % 7 == 0) {
						result += weeklyTotal.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + ",";
						payPeriodTotal = weeklyTotal.add(payPeriodTotal);
						weeklyTotal = new BigDecimal(0);
					}
					if (dayCount % 14 == 0) {
						result += payPeriodTotal.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
					}
					
				}
				
				if (new BigDecimal(0).compareTo(payPeriodTotal) < 0) {
					results.put(mapPriority + earnCode + assignmentKey, result);
				}
				payPeriodTotal = new BigDecimal(0);
				lastEarnCode = earnCode;
			}

		}
		
		results.put(MAP_PRIORITY_TOTAL, populateAttendanceHoursTotal(payEndDate));
		results.put(MAP_PRIORITY_TOTAL + "zzz", "DIVIDER,0.00");
		
		results.put(MAP_PRIORITY_WORK_HOURS, "DIVIDER,0.00");
		results.put(MAP_PRIORITY_WORK_HOURS + "aaa", populateWorkedHoursTotal(payEndDate));
		results.put(MAP_PRIORITY_WORK_HOURS + "yyy", "DIVIDER,0.00");
		results.put(MAP_PRIORITY_WORK_HOURS + "zzz", "DIVIDER,0.00");
		
		Iterator resultsItr = results.keySet().iterator();
		while (resultsItr.hasNext()) {
			getHoursSummaryDisplay().add(results.get(resultsItr.next()));
		}
		
	}
	
	private String populateAttendanceHoursTotal(Date payEndDate) {
		String result = "TOTAL,Regular Pay Hours:,";
		BigDecimal weeklyTotal = new BigDecimal(0);
		BigDecimal payPeriodTotal = new BigDecimal(0);
		int dayCount = 0;
		
		Iterator datesItr = getDates().keySet().iterator();
		while (datesItr.hasNext()) {
			TimelessDate timelessDate = (TimelessDate)datesItr.next();
			BigDecimal hours = new BigDecimal(0.00);
			
			Iterator earningsItr = getEarnings().keySet().iterator();
			while (earningsItr.hasNext()) {
				String earnCode = (String)earningsItr.next();
				
				if (getEarningService().isGeneralAttendanceEarningsEarnCode(earnCode, payEndDate)) {
					
					Iterator assignmentsItr = getAssignments().keySet().iterator();
					while (assignmentsItr.hasNext()) {
						String assignmentKey = (String) assignmentsItr.next();

						BigDecimal recordHours = new BigDecimal(0.00);
						try {
							recordHours = (BigDecimal) ((Map) ((Map) getDates().get(timelessDate)).get(earnCode)).get(assignmentKey);
						} catch (RuntimeException e) {
							recordHours = new BigDecimal(0.00);						
						}
						if (recordHours == null) {
							recordHours = new BigDecimal(0.00);
						}

						hours = hours.add(recordHours);
					}
				}				
				
			}
			
			result += hours.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + ",";
			weeklyTotal = weeklyTotal.add(hours);
			if (++dayCount % 7 == 0) {
				result += weeklyTotal.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + ",";
				payPeriodTotal = payPeriodTotal.add(weeklyTotal);
				weeklyTotal = new BigDecimal(0);
			}
			if (dayCount % 14 == 0) {
				result += payPeriodTotal.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}

		}
		
		return result;

	}
	
	private String populateWorkedHoursTotal(Date payEndDate) {
		String result = "WORK_HOURS_TOTAL,Worked Hours:,";
		BigDecimal weeklyTotal = new BigDecimal(0);
		BigDecimal payPeriodTotal = new BigDecimal(0);
		int dayCount = 0;
		
		Iterator datesItr = getDates().keySet().iterator();
		while (datesItr.hasNext()) {
			TimelessDate timelessDate = (TimelessDate)datesItr.next();
			BigDecimal hours = new BigDecimal(0.00);
			
			Iterator earningsItr = getEarnings().keySet().iterator();
			while (earningsItr.hasNext()) {
				String earnCode = (String)earningsItr.next();
				BigDecimal divisor = new BigDecimal(1.00);
				
				if (getEarningService().isActualWorkHoursEarnCode(earnCode, payEndDate)) {
					
					if ("CPE".equals(earnCode)) {
						divisor = new BigDecimal(1.50); //to deflate cpe
					}
					
					Iterator assignmentsItr = getAssignments().keySet().iterator();
					while (assignmentsItr.hasNext()) {
						String assignmentKey = (String) assignmentsItr.next();

						BigDecimal recordHours = new BigDecimal(0.00);
						try {
							recordHours = (BigDecimal) ((Map) ((Map) getDates().get(timelessDate)).get(earnCode)).get(assignmentKey);
						} catch (RuntimeException e) {
							recordHours = new BigDecimal(0.00);						
						}
						if (recordHours == null) {
							recordHours = new BigDecimal(0.00);
						}

						hours = hours.add(recordHours.divide(divisor, 2, BigDecimal.ROUND_HALF_UP));
					}
				}				
				
			}
			
			result += hours.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + ",";
			weeklyTotal = weeklyTotal.add(hours);
			if (++dayCount % 7 == 0) {
				result += weeklyTotal.setScale(2,BigDecimal.ROUND_HALF_UP).toString() + ",";
				payPeriodTotal = payPeriodTotal.add(weeklyTotal);
				weeklyTotal = new BigDecimal(0);
			}
			if (dayCount % 14 == 0) {
				result += payPeriodTotal.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}

		}
		
		return result;

	}

	public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    protected Map getPropertyDescriptorMap() {
        return CUSTOM_PROPERTY_DESCRIPTORS;
    }

	public void populateHeaderAndDates(Hours hours) {
		SimpleDateFormat formattedDate = new SimpleDateFormat("EEE MM/dd");
		String result = "HEADER,0.00,";
		Iterator payCalendarDatesItr = hours.getPayCalendar().getDatesInPayPeriod().iterator();
		while (payCalendarDatesItr.hasNext()) {
			Date currentDate = (Date)payCalendarDatesItr.next();
			getDates().put(new TimelessDate(currentDate), new TreeMap());
			result += formattedDate.format(currentDate) + ",";
			int dayOfWeek = hours.getPayCalendar().getDayInPayPeriod(currentDate);
			if (dayOfWeek % 7 == 0) {
				result +="Weekly Total,";							
			}
		}
		result += "Period Total";
			
		hoursSummaryDisplay.add(result);
	}
	
	public List getHoursSummaryDisplay() {
		return hoursSummaryDisplay;
	}
	
	public void setHoursSummaryDisplay(List hoursSummaryColumnHeaders) {
		this.hoursSummaryDisplay = hoursSummaryColumnHeaders;
	}

	public List getHoursSummaryRecords() {
		return hoursSummaryRecords;
	}

	public void setHoursSummaryRecords(List hoursSummaryRecords) {
		this.hoursSummaryRecords = hoursSummaryRecords;
	}

	public TimesheetService getTimesheetService() {
		return timesheetService;
	}

	public void setTimesheetService(TimesheetService timesheetService) {
		this.timesheetService = timesheetService;
	}

	public Map getDates() {
		return dates;
	}

	public void setDates(Map dates) {
		this.dates = dates;
	}

	public EarningService getEarningService() {
		return earningService;
	}

	public void setEarningService(EarningService earningService) {
		this.earningService = earningService;
	}

	public Map getEarnings() {
		return earnings;
	}

	public void setEarnings(Map regularEarnings) {
		this.earnings = regularEarnings;
	}

	public Map getAssignments() {
		return assignments;
	}

	public void setAssignments(Map assignments) {
		this.assignments = assignments;
	}

	public Map getTotals() {
		return totals;
	}

	public void setTotals(Map totals) {
		this.totals = totals;
	}

}
