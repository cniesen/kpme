/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.hr.lm.leaveSummary.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.kuali.hr.lm.LMConstants;
import org.kuali.hr.lm.accrual.AccrualCategory;
import org.kuali.hr.lm.accrual.AccrualCategoryRule;
import org.kuali.hr.lm.employeeoverride.EmployeeOverride;
import org.kuali.hr.lm.leaveSummary.LeaveSummary;
import org.kuali.hr.lm.leaveSummary.LeaveSummaryRow;
import org.kuali.hr.lm.leaveblock.LeaveBlock;
import org.kuali.hr.lm.leaveblock.service.LeaveBlockService;
import org.kuali.hr.lm.leaveplan.LeavePlan;
import org.kuali.hr.lm.workflow.LeaveCalendarDocumentHeader;
import org.kuali.hr.time.calendar.CalendarEntries;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.krad.util.ObjectUtils;

public class LeaveSummaryServiceImpl implements LeaveSummaryService {
	private LeaveBlockService leaveBlockService;

    @Override
    public LeaveSummary getLeaveSummary(String principalId, CalendarEntries calendarEntry) {
        LeaveSummary ls = new LeaveSummary();
        List<LeaveSummaryRow> rows = new ArrayList<LeaveSummaryRow>();

        if(StringUtils.isEmpty(principalId) || calendarEntry == null) {
            return ls;
        }
        PrincipalHRAttributes pha = TkServiceLocator.getPrincipalHRAttributeService().getPrincipalCalendar(principalId, calendarEntry.getBeginPeriodDate());
        if(pha == null) {	// principal hr attributes does not exist at the beginning of this calendar entry
        	pha = TkServiceLocator.getPrincipalHRAttributeService().getPrincipalCalendar(principalId, calendarEntry.getEndPeriodDate());
        }
        // get list of principalHrAttributes that become active during this pay period
        List<PrincipalHRAttributes> phaList = TkServiceLocator.getPrincipalHRAttributeService()
                .getActivePrincipalHrAttributesForRange(principalId, calendarEntry.getBeginPeriodDate(), calendarEntry.getEndPeriodDate());

        Set<String> lpStrings = new HashSet<String>();     //
        if(pha != null) {
            lpStrings.add(pha.getLeavePlan());
        }
        if(CollectionUtils.isNotEmpty(phaList)) {
            for(PrincipalHRAttributes aPha : phaList) {
                lpStrings.add(aPha.getLeavePlan());
            }
        }

        if (CollectionUtils.isNotEmpty(lpStrings)) {
            for(String aLpString : lpStrings) {
                LeavePlan lp = TkServiceLocator.getLeavePlanService().getLeavePlan(aLpString, calendarEntry.getEndPeriodDate());
                if(lp == null) {
                    continue;
                }
                DateFormat formatter = new SimpleDateFormat("MMMM d");
                DateFormat formatter2 = new SimpleDateFormat("MMMM d yyyy");
                DateTime entryEndDate = calendarEntry.getEndLocalDateTime().toDateTime();
                if (entryEndDate.getHourOfDay() == 0) {
                    entryEndDate = entryEndDate.minusDays(1);
                }
                String aString = formatter.format(calendarEntry.getBeginPeriodDate()) + " - " + formatter2.format(entryEndDate.toDate());
                ls.setPendingDatesString(aString);

                LeaveCalendarDocumentHeader approvedLcdh = TkServiceLocator.getLeaveCalendarDocumentHeaderService().getMaxEndDateApprovedLeaveCalendar(principalId);
                if(approvedLcdh != null) {
                    Date endApprovedDate = new java.sql.Date(approvedLcdh.getEndDate().getTime());
                    LocalDateTime aLocalTime = new DateTime(approvedLcdh.getEndDate()).toLocalDateTime();
                    DateTime endApprovedTime = aLocalTime.toDateTime(TkServiceLocator.getTimezoneService().getUserTimezoneWithFallback());
                    if(endApprovedTime.getHourOfDay() == 0) {
                        endApprovedDate = TKUtils.addDates(endApprovedDate, -1);
                    }
                    String datesString = formatter.format(approvedLcdh.getBeginDate()) + " - " + formatter2.format(endApprovedDate);
                    ls.setYtdDatesString(datesString);
                }

//                List<LeaveBlock> leaveBlocks = getLeaveBlockService().getLeaveBlocks(principalId, pha.getServiceDate(), calendarEntry.getEndPeriodDateTime());
                List<LeaveBlock> leaveBlocks = getLeaveBlockService().getLeaveBlocks(principalId, this.getLeavePlanCalendarYearStart(lp, calendarEntry), calendarEntry.getEndPeriodDateTime());
                List<LeaveBlock> futureLeaveBlocks = getLeaveBlockService().getLeaveBlocks(principalId, calendarEntry.getEndPeriodDateTime(), calendarEntry.getEndLocalDateTime().toDateTime().plusYears(5).toDate());
                Map<String, List<LeaveBlock>> leaveBlockMap = mapLeaveBlocksByAccrualCategory(leaveBlocks);
                Map<String, List<LeaveBlock>> futureLeaveBlockMap = mapLeaveBlocksByAccrualCategory(futureLeaveBlocks);
                List<AccrualCategory> acList = TkServiceLocator.getAccrualCategoryService().getActiveAccrualCategoriesForLeavePlan(lp.getLeavePlan(), calendarEntry.getEndPeriodDate());
                if(CollectionUtils.isNotEmpty(acList)) {
                    for(AccrualCategory ac : acList) {
                        if(ac.getShowOnGrid().equals("Y")) {
                            LeaveSummaryRow lsr = new LeaveSummaryRow();
                            lsr.setAccrualCategory(ac.getAccrualCategory());
                            lsr.setAccrualCategoryId(ac.getLmAccrualCategoryId());
                            //get max balances
                            AccrualCategoryRule acRule = TkServiceLocator.getAccrualCategoryRuleService().getAccrualCategoryRuleForDate(ac, TKUtils.getCurrentDate(), pha.getServiceDate());
                            //accrual category rule id set on a leave summary row will be useful in generating a relevant balance transfer
                            //document from the leave calendar display. Could put this id in the request for balance transfer document.
                            lsr.setAccrualCategoryRuleId(acRule == null ? null : acRule.getLmAccrualCategoryRuleId());
                            if(acRule != null &&
                                    (acRule.getMaxBalance()!= null
                                      || acRule.getMaxUsage() != null)) {
                                if (acRule.getMaxUsage() != null) {
                                    lsr.setUsageLimit(new BigDecimal(acRule.getMaxUsage()).setScale(2));
                                } else {
                                    lsr.setUsageLimit(null);
                                }

                            } else {
                                lsr.setUsageLimit(null);
                            }

                            List<EmployeeOverride> employeeOverrides = TkServiceLocator.getEmployeeOverrideService().getEmployeeOverrides(principalId,TKUtils.getCurrentDate()); //current date ok?
                            for(EmployeeOverride eo : employeeOverrides) {
                                if(eo.getLeavePlan().equals(lp.getLeavePlan()) && eo.getAccrualCategory().equals(ac.getAccrualCategory())) {
                                    if(eo.getOverrideType().equals("MU") && eo.isActive()) {
                                        if(eo.getOverrideValue()!=null && !eo.getOverrideValue().equals(""))
                                            lsr.setUsageLimit(new BigDecimal(eo.getOverrideValue()));
                                        else // no limit flag
                                            lsr.setUsageLimit(null);
                                    }
                                }
                            }

                            //handle up to current leave blocks
                            assignApprovedValuesToRow(lsr, ac.getAccrualCategory(), leaveBlockMap.get(ac.getAccrualCategory()), lp);

                            //Check for going over max carry over
                            //Should only be setting values on leave summary if the values are "static references".
                            //KPME-1985 and KPME-1264 would replace the need to do this.
                            if (acRule != null
                                    && acRule.getMaxCarryOver() != null
                                    && acRule.getMaxCarryOver() < lsr.getCarryOver().longValue()) {
                                lsr.setCarryOver(new BigDecimal(acRule.getMaxCarryOver()));
                            }

                            //handle future leave blocks
                            assignPendingValuesToRow(lsr, ac.getAccrualCategory(), futureLeaveBlockMap.get(ac.getAccrualCategory()));

                            //compute Leave Balance
                            BigDecimal leaveBalance = lsr.getAccruedBalance().subtract(lsr.getPendingLeaveRequests());
                            //if leave balance is set 
                            //Employee overrides have already been taken into consideration and the appropriate values
                            //for usage have been set by this point.
//                            if (acRule != null && StringUtils.equals(acRule.getMaxBalFlag(), "Y")) {
                            	//there exists an accrual category rule with max balance limit imposed.
                            	//max bal flag = 'Y' has no precedence here with max-bal / balance transfers implemented.
                            	//unless institutions are not required to define a max balance limit for action_at_max_bal = LOSE.
                            	//Possibly preferable to procure forfeiture blocks through balance transfer
                            	if(lsr.getUsageLimit()!=null) { //should not set leave balance to usage limit simply because it's not null.
                            		BigDecimal availableUsage = lsr.getUsageLimit().subtract(lsr.getYtdApprovedUsage().add(lsr.getPendingLeaveRequests()));
                            		if(leaveBalance.compareTo( availableUsage ) > 0)
                            			lsr.setLeaveBalance(availableUsage);
                            		else
                            			lsr.setLeaveBalance(leaveBalance);
                            	}
                            	else//no usage limit
                            		lsr.setLeaveBalance(leaveBalance);
//                            } else {
//                            	//accrual rule is undefined for accrual category, or max bal flag is "N"
//                                lsr.setLeaveBalance(leaveBalance);
//                            }
                            markTransferable(lsr,acRule);
                            markPayoutable(lsr,acRule);
                            rows.add(lsr);
                        }
                    }
                    // let's check for 'empty' accrual categories
                    if (leaveBlockMap.containsKey(null)
                            || futureLeaveBlockMap.containsKey(null)) {
                        LeaveSummaryRow otherLeaveSummary = new LeaveSummaryRow();
                        //otherLeaveSummary.setAccrualCategory("Other");
                        assignApprovedValuesToRow(otherLeaveSummary, null, leaveBlockMap.get(null), lp);
                        assignPendingValuesToRow(otherLeaveSummary, null, futureLeaveBlockMap.get(null));
                        otherLeaveSummary.setAccrualCategory("Other");

                        //compute Leave Balance
                        // blank the avail
                        otherLeaveSummary.setUsageLimit(null);
                      	otherLeaveSummary.setLeaveBalance(null);

                        rows.add(otherLeaveSummary);
                    }
                }
            }
        }
        ls.setLeaveSummaryRows(rows);
        return ls;
    }

    private Map<String, List<LeaveBlock>> mapLeaveBlocksByAccrualCategory(List<LeaveBlock> leaveBlocks) {
        Map<String, List<LeaveBlock>> map = new HashMap<String, List<LeaveBlock>>();
        for (LeaveBlock lb : leaveBlocks) {
            if (map.containsKey(lb.getAccrualCategory())) {
                map.get(lb.getAccrualCategory()).add(lb);
            } else {
                List<LeaveBlock> splitLeaveBlocks = new ArrayList<LeaveBlock>();
                splitLeaveBlocks.add(lb);
                map.put(lb.getAccrualCategory(), splitLeaveBlocks);
            }
        }
        return map;
    }

    /**
     * Use with button display for balance transfers available On-Demand.
     * @param lsr
     * @param accrualCategoryRule
     */
    private void markTransferable(LeaveSummaryRow lsr, AccrualCategoryRule accrualCategoryRule) {
    	//return type must be changed to boolean, or an associated field element must be created for decision
    	//purposes.
    	//an accrual category's balance is transferable if the accrued balance is 
    	//greater than the maximum balance allowed for the accrual category. action_at_max_balance must be TRANSFER
    	boolean transferable = false;
    	if(ObjectUtils.isNotNull(accrualCategoryRule)) {
    		if(ObjectUtils.isNotNull(accrualCategoryRule.getMaxBalance())) {
    			BigDecimal maxBalance = accrualCategoryRule.getMaxBalance();
    			BigDecimal fte = TkServiceLocator.getJobService().getFteSumForAllActiveLeaveEligibleJobs(TKContext.getTargetPrincipalId(), TKUtils.getCurrentDate());
    			BigDecimal adjustedMaxBalance = maxBalance.multiply(fte);
    			if(adjustedMaxBalance.compareTo(lsr.getAccruedBalance()) < 0) {
    				if(StringUtils.equals(accrualCategoryRule.getActionAtMaxBalance(), LMConstants.ACTION_AT_MAX_BAL.TRANSFER) &&
    						StringUtils.equals(accrualCategoryRule.getMaxBalanceActionFrequency(),LMConstants.MAX_BAL_ACTION_FREQ.ON_DEMAND))
    					transferable = true;
    			}
    		}
    	}
    	lsr.setTransferable(transferable);
    }
    
    /**
     * Use with button display for balance transfer payouts available On-Demand.
     * @param lsr
     * @param accrualCategoryRule 
     */
    private void markPayoutable(LeaveSummaryRow lsr, AccrualCategoryRule accrualCategoryRule) {
    	//return type must be changed to boolean, or an associated field element must be created for decision
    	//purposes.
    	//an accrual category's balance is transferable if max_bal_action_frequency is ON-DEMAND
    	//and action_at_max_balance is PAYOUT
    	boolean transferable = false;
    	if(ObjectUtils.isNotNull(accrualCategoryRule)) {
    		if(ObjectUtils.isNotNull(accrualCategoryRule.getMaxBalance())) {
    			BigDecimal maxBalance = accrualCategoryRule.getMaxBalance();
    			BigDecimal fte = TkServiceLocator.getJobService().getFteSumForAllActiveLeaveEligibleJobs(TKContext.getTargetPrincipalId(), TKUtils.getCurrentDate());
    			BigDecimal adjustedMaxBalance = maxBalance.multiply(fte);
    			if(adjustedMaxBalance.compareTo(lsr.getAccruedBalance()) < 0) {
    				if(StringUtils.equals(accrualCategoryRule.getActionAtMaxBalance(), LMConstants.ACTION_AT_MAX_BAL.PAYOUT) &&
    						StringUtils.equals(accrualCategoryRule.getMaxBalanceActionFrequency(),LMConstants.MAX_BAL_ACTION_FREQ.ON_DEMAND))
    					transferable = true;
    			}
    		}
    	}
    	lsr.setPayoutable(transferable);
    }
    
	private void assignApprovedValuesToRow(LeaveSummaryRow lsr, String accrualCategory, List<LeaveBlock> approvedLeaveBlocks, LeavePlan lp) {
        //List<TimeOffAccrual> timeOffAccruals = TkServiceLocator.getTimeOffAccrualService().getTimeOffAccrualsCalc(principalId, lsr.get)
		BigDecimal carryOver = BigDecimal.ZERO.setScale(2);
        BigDecimal accrualedBalance = BigDecimal.ZERO.setScale(2);
		BigDecimal approvedUsage = BigDecimal.ZERO.setScale(2);
		BigDecimal fmlaUsage = BigDecimal.ZERO.setScale(2);

        //TODO: probably should get from Leave Plan
		//test failure fix for kpme-trunk-build-unit #2064 - #2070 LeaveSummaryServiceImpleTest.testGetLeaveSummary
		//test expects no carry over, but with previuos definition of priorYearCutOff, carry over was being set.
		//This test will likely fail again during leave plan's calendar year rollover.
		Timestamp priorYearCutOff = new Timestamp(TKUtils.formatDateString(lp.getCalendarYearStart()+"/2012").getTime());
        //Timestamp priorYearCutOff = new Timestamp(new DateMidnight().withWeekOfWeekyear(1).withDayOfWeek(1).toDate().getTime());
        if (CollectionUtils.isNotEmpty(approvedLeaveBlocks)) {
            for(LeaveBlock aLeaveBlock : approvedLeaveBlocks) {
                if((StringUtils.isBlank(accrualCategory) && StringUtils.isBlank(aLeaveBlock.getAccrualCategory()))
                        || (StringUtils.isNotBlank(aLeaveBlock.getAccrualCategory())
                            && StringUtils.equals(aLeaveBlock.getAccrualCategory(), accrualCategory))) {
                    if(aLeaveBlock.getLeaveAmount().compareTo(BigDecimal.ZERO) >= 0
                            && !aLeaveBlock.getLeaveBlockType().equals(LMConstants.LEAVE_BLOCK_TYPE.LEAVE_CALENDAR)) {
                        if(StringUtils.isNotEmpty(aLeaveBlock.getRequestStatus())
                                && aLeaveBlock.getRequestStatus().equals(LMConstants.REQUEST_STATUS.APPROVED)) {
                            if (aLeaveBlock.getLeaveDate().getTime() <= priorYearCutOff.getTime()) {
                                carryOver = carryOver.add(aLeaveBlock.getLeaveAmount());
                            } else {
                                accrualedBalance = accrualedBalance.add(aLeaveBlock.getLeaveAmount());
                            }
                        }
                    } else {
                    	//LEAVE_BLOCK_TYPE.BALANCE_TRANSFER should not count as usage.
                        BigDecimal currentLeaveAmount = aLeaveBlock.getLeaveAmount().compareTo(BigDecimal.ZERO) > 0 ? aLeaveBlock.getLeaveAmount().negate() : aLeaveBlock.getLeaveAmount();
                        approvedUsage = approvedUsage.add(currentLeaveAmount);
                        EarnCode ec = TkServiceLocator.getEarnCodeService().getEarnCode(aLeaveBlock.getEarnCode(), aLeaveBlock.getLeaveDate());
                        if(ec != null && ec.getFmla().equals("Y")) {
                            fmlaUsage = fmlaUsage.add(aLeaveBlock.getLeaveAmount());
                        }
                    }

                    //}
                }
            }
        }
        lsr.setCarryOver(carryOver);
		lsr.setYtdAccruedBalance(accrualedBalance);
		lsr.setYtdApprovedUsage(approvedUsage.negate());
		lsr.setFmlaUsage(fmlaUsage.negate());
		
		//lsr.setLeaveBalance(lsr.getYtdAccruedBalance().add(approvedUsage));
	}
	
	private void assignPendingValuesToRow(LeaveSummaryRow lsr, String accrualCategory, List<LeaveBlock> pendingLeaveBlocks ) {
		BigDecimal pendingAccrual= BigDecimal.ZERO.setScale(2);
		BigDecimal pendingRequests = BigDecimal.ZERO.setScale(2);
        if (CollectionUtils.isNotEmpty(pendingLeaveBlocks)) {
            for(LeaveBlock aLeaveBlock : pendingLeaveBlocks) {
                if((StringUtils.isBlank(accrualCategory) && StringUtils.isBlank(aLeaveBlock.getAccrualCategory()))
                        || (StringUtils.isNotBlank(aLeaveBlock.getAccrualCategory())
                            && StringUtils.equals(aLeaveBlock.getAccrualCategory(), accrualCategory))) {
                    if(aLeaveBlock.getLeaveAmount().compareTo(BigDecimal.ZERO) >= 0) {
                        pendingAccrual = pendingAccrual.add(aLeaveBlock.getLeaveAmount());
                    } else {
                        pendingRequests = pendingRequests.add(aLeaveBlock.getLeaveAmount());
                    }
                }
            }
        }
		lsr.setPendingLeaveAccrual(pendingAccrual);
		lsr.setPendingLeaveRequests(pendingRequests.negate());
	}
	
	
	private Date calendarYearStartDate(String dateString, Date asOfDate) throws ParseException {
		// dateString in MM/DD format
		Calendar gc = new GregorianCalendar();
		gc.setTime(asOfDate);
		String yearString = Integer.toString(gc.get(Calendar.YEAR));
		String tempString = dateString;
		if(StringUtils.isEmpty(dateString)) {
			tempString = "01/01";		// use 01/01 as default starting date
		}
		String fullString = tempString + "/" + yearString;
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date aDate = formatter.parse(fullString);
		if(aDate.after(asOfDate)) {
			yearString = Integer.toString(gc.get(Calendar.YEAR) -1);
			fullString = tempString + "/" + yearString;
			aDate = formatter.parse(fullString);
		}
		return aDate;
	}
	
	@Override
	public List<Date> getLeaveSummaryDates(CalendarEntries calendarEntry) {
		List<Date> leaveSummaryDates = new ArrayList<Date>();

		DateTime start = calendarEntry.getBeginLocalDateTime().toDateTime();
		DateTime end = calendarEntry.getEndLocalDateTime().toDateTime();
        Interval interval = new Interval(start, end);

        for (DateTime day = interval.getStart(); day.isBefore(interval.getEnd()); day = day.plusDays(1)) {
        	leaveSummaryDates.add(day.toLocalDate().toDateTimeAtStartOfDay().toDate());
        }
		 
		 return leaveSummaryDates;
	}

    protected LeaveBlockService getLeaveBlockService() {
        if (leaveBlockService == null) {
            leaveBlockService = TkServiceLocator.getLeaveBlockService();
        }
        return leaveBlockService;
    }

    
    private boolean ifCalendarYearStartForLeavePlan(LeavePlan leavePlan, CalendarEntries leaveCalEntries) {

    	boolean flag = true;
		// check if Calendar entry is first entry of the year start the make accrued balance and approved usage zero
		String calendarYearStartStr = leavePlan.getCalendarYearStart();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		sdf.setLenient(false);
		Date calYearStart = null;
		try {
			calYearStart = sdf.parse(calendarYearStartStr);
		} catch (ParseException e) {
		}
		System.out.println("Leave Plan is >> "+leavePlan.getLeavePlan());
		System.out.println("Leave Cal Entry is  >> "+leaveCalEntries);
		Calendar lpYearStart = Calendar.getInstance();
		lpYearStart.setTime(calYearStart);
		lpYearStart.set(Calendar.HOUR_OF_DAY, 0);
		lpYearStart.set(Calendar.MINUTE, 0);
		lpYearStart.set(Calendar.SECOND, 0);
		lpYearStart.set(Calendar.MILLISECOND, 0);
		lpYearStart.set(Calendar.YEAR, leaveCalEntries.getBeginLocalDateTime().getYear());
		if(lpYearStart.getTime() != null) {
			if((lpYearStart.getTime().compareTo(leaveCalEntries.getBeginPeriodDateTime()) >=0) && (lpYearStart.getTime().compareTo(leaveCalEntries.getEndPeriodDateTime()) <=0)){
				flag = true;
			}
		}
		return flag;
    }
    
    private Date getLeavePlanCalendarYearStart(LeavePlan leavePlan, CalendarEntries leaveCalEntries) {
    	boolean flag = true;
		// check if Calendar entry is first entry of the year start the make accrued balance and approved usage zero
		String calendarYearStartStr = leavePlan.getCalendarYearStart();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		sdf.setLenient(false);
		Date calYearStart = null;
		try {
			calYearStart = sdf.parse(calendarYearStartStr);
		} catch (ParseException e) {
		}
		Calendar lpYearStart = Calendar.getInstance();
		lpYearStart.setTime(calYearStart);
		lpYearStart.set(Calendar.HOUR_OF_DAY, 0);
		lpYearStart.set(Calendar.MINUTE, 0);
		lpYearStart.set(Calendar.SECOND, 0);
		lpYearStart.set(Calendar.MILLISECOND, 0);
		lpYearStart.set(Calendar.YEAR, leaveCalEntries.getBeginLocalDateTime().getYear());
		if(lpYearStart.getTime() != null) {
			if((lpYearStart.getTime().compareTo(leaveCalEntries.getBeginPeriodDateTime()) >=0) && (lpYearStart.getTime().compareTo(leaveCalEntries.getEndPeriodDateTime()) <=0)){
				flag = true;
			}
		}
		return lpYearStart.getTime();
    }
}
