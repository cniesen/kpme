/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.kpme.tklm.time.rules.overtime.weekly.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.assignment.Assignment;
import org.kuali.kpme.core.api.calendar.Calendar;
import org.kuali.kpme.core.api.calendar.entry.CalendarEntry;
import org.kuali.kpme.core.api.earncode.EarnCodeContract;
import org.kuali.kpme.core.api.workarea.WorkArea;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.tklm.api.leave.block.LeaveBlock;
import org.kuali.kpme.tklm.api.time.timeblock.TimeBlock;
import org.kuali.kpme.tklm.api.time.timeblock.TimeBlockContract;
import org.kuali.kpme.tklm.api.time.timehourdetail.TimeHourDetail;
import org.kuali.kpme.tklm.api.time.timehourdetail.TimeHourDetailContract;
import org.kuali.kpme.tklm.leave.service.LmServiceLocator;
import org.kuali.kpme.tklm.time.flsa.FlsaDay;
import org.kuali.kpme.tklm.time.flsa.FlsaWeek;
import org.kuali.kpme.tklm.time.rules.overtime.weekly.WeeklyOvertimeRule;
import org.kuali.kpme.tklm.time.rules.overtime.weekly.dao.WeeklyOvertimeRuleDao;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.kpme.tklm.time.timeblock.TimeBlockBo;
import org.kuali.kpme.tklm.time.timehourdetail.TimeHourDetailBo;
import org.kuali.kpme.tklm.time.timesheet.TimesheetDocument;
import org.kuali.kpme.tklm.time.util.TkTimeBlockAggregate;
import org.kuali.kpme.tklm.time.workflow.TimesheetDocumentHeader;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.service.KRADServiceLocator;

import java.math.BigDecimal;
import java.util.*;

public class WeeklyOvertimeRuleServiceImpl implements WeeklyOvertimeRuleService {

    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(WeeklyOvertimeRuleServiceImpl.class);

	private WeeklyOvertimeRuleDao weeklyOvertimeRuleDao;
    private static final ModelObjectUtils.Transformer<TimeBlock, TimeBlock.Builder> toTimeBlockBuilder =
            new ModelObjectUtils.Transformer<TimeBlock, TimeBlock.Builder>() {
                public TimeBlock.Builder transform(TimeBlock input) {
                    return TimeBlock.Builder.create(input);
                };
            };
    private static final ModelObjectUtils.Transformer<TimeBlockBo, TimeBlock> toTimeBlock =
            new ModelObjectUtils.Transformer<TimeBlockBo, TimeBlock>() {
                public TimeBlock transform(TimeBlockBo input) {
                    return TimeBlockBo.to(input);
                };
            };
    private static final ModelObjectUtils.Transformer<TimeBlock, TimeBlockBo> toTimeBlockBo =
            new ModelObjectUtils.Transformer<TimeBlock, TimeBlockBo>() {
                public TimeBlockBo transform(TimeBlock input) {
                    return TimeBlockBo.from(input);
                };
            };

	@Override
	public void processWeeklyOvertimeRule(TimesheetDocument timesheetDocument, TkTimeBlockAggregate aggregate) {

		LocalDate asOfDate = timesheetDocument.getDocumentHeader().getEndDateTime().toLocalDate();
		String principalId = timesheetDocument.getDocumentHeader().getPrincipalId();
		DateTime beginDate = timesheetDocument.getDocumentHeader().getBeginDateTime();
		DateTime endDate = timesheetDocument.getDocumentHeader().getEndDateTime();
		List<WeeklyOvertimeRule> weeklyOvertimeRules = getWeeklyOvertimeRules(asOfDate);

        List<List<FlsaWeek>> newFlsaWeeks = getFlsaWeeks(principalId, beginDate, endDate, aggregate);

        for (List<FlsaWeek> flsaWeekParts : newFlsaWeeks)
        {
            BigDecimal totalNewOvertimeHours = null;
            BigDecimal totalNewOvertimeMinutes = null;
            BigDecimal hoursTowardsMaxEarnCodes = null;
            BigDecimal minutesTowardsMaxEarnCodes = null;
            for (WeeklyOvertimeRule weeklyOvertimeRule : weeklyOvertimeRules)
            {
                Set<String> maxHoursEarnCodes = HrServiceLocator.getEarnCodeGroupService().getEarnCodeListForEarnCodeGroup(weeklyOvertimeRule.getMaxHoursEarnGroup(), asOfDate);
                Set<String> convertFromEarnCodes = HrServiceLocator.getEarnCodeGroupService().getEarnCodeListForEarnCodeGroup(weeklyOvertimeRule.getConvertFromEarnGroup(), asOfDate);
                Set<String> applyToEarnCodes = HrServiceLocator.getEarnCodeGroupService().getEarnCodeListForEarnCodeGroup(weeklyOvertimeRule.getApplyToEarnGroup(), asOfDate);
/*
                LOG.info(weeklyOvertimeRule);
                LOG.info("maxHoursEarnCodes =");
                LOG.info(maxHoursEarnCodes);

                LOG.info("applyToEarnCodes =");
                LOG.info(convertFromEarnCodes);

                LOG.info("before applying rule");
                LOG.info(newFlsaWeeks);
*/

                /***************/

                if (hoursTowardsMaxEarnCodes == null) {
                    hoursTowardsMaxEarnCodes = getHours(flsaWeekParts, maxHoursEarnCodes);
                }
                if (minutesTowardsMaxEarnCodes == null) {
                    minutesTowardsMaxEarnCodes = getMinutes(flsaWeekParts, maxHoursEarnCodes);
                }
                if (totalNewOvertimeHours == null) {
                    totalNewOvertimeHours = hoursTowardsMaxEarnCodes.subtract(weeklyOvertimeRule.getMaxHours(), HrConstants.MATH_CONTEXT);
                }
                if (totalNewOvertimeMinutes == null) {
                    BigDecimal weeklyOverTimeMaxHours = weeklyOvertimeRule.getMaxHours().multiply(BigDecimal.valueOf(60), HrConstants.MATH_CONTEXT);
                    totalNewOvertimeMinutes = minutesTowardsMaxEarnCodes.subtract(weeklyOverTimeMaxHours, HrConstants.MATH_CONTEXT);
                }

                //flsaWeekParts.get(0).getFlsaDays().get(0).getAppliedTimeBlocks().get(0).
                BigDecimal thisRuleConvertableHours = getHours(flsaWeekParts, convertFromEarnCodes);
                BigDecimal thisRuleConvertableMinutes = getMinutes(flsaWeekParts, convertFromEarnCodes);

/*
                LOG.info("maxEarnCodes");
                LOG.info(maxHoursEarnCodes);
                LOG.info("convertTo");
                LOG.info(weeklyOvertimeRule.getConvertToEarnCode());
                LOG.info("newOverTimeHours");
                LOG.info(totalNewOvertimeHours);
*/

                BigDecimal thisRuleOvertimeHoursToApply = BigDecimal.ZERO;
                BigDecimal thisRuleOvertimeMinutesToApply = BigDecimal.ZERO;

                if (thisRuleConvertableHours.compareTo(BigDecimal.ZERO) > 0) {
                    thisRuleOvertimeHoursToApply = totalNewOvertimeHours;
                    thisRuleOvertimeMinutesToApply = totalNewOvertimeMinutes;
                    if (thisRuleConvertableHours.compareTo(totalNewOvertimeHours) < 0) {
                        thisRuleOvertimeHoursToApply = thisRuleConvertableHours;
                        thisRuleOvertimeMinutesToApply = thisRuleConvertableMinutes;
                    }
                }

                Boolean ruleRan = false;
                if ( ( totalNewOvertimeHours.compareTo(BigDecimal.ZERO) > 0) && (thisRuleOvertimeHoursToApply.compareTo(BigDecimal.ZERO) > 0)) {

                    BigDecimal afterOvertimeRuleRanMinutes = applyOvertimeToFlsaWeeks(flsaWeekParts, weeklyOvertimeRule, asOfDate.plusDays((7 - asOfDate.getDayOfWeek())), applyToEarnCodes, thisRuleOvertimeHoursToApply, thisRuleOvertimeMinutesToApply);
                    //totalNewOvertimeHours = totalNewOvertimeHours.subtract(thisRuleOvertimeHoursToApply.subtract(afterOvertimeRuleRanHours));
                    totalNewOvertimeMinutes = totalNewOvertimeMinutes.subtract(thisRuleOvertimeMinutesToApply.subtract(afterOvertimeRuleRanMinutes));
                }
            }
/*
            LOG.info("after applying rule");
            LOG.info(newFlsaWeeks);
*/
		}

        //convert weeks to list of timeblocks to push back into aggregate
        List<List<TimeBlock>> updatedBlocks = new ArrayList<List<TimeBlock>>();

        DateTime d1 = aggregate.getPayCalendarEntry().getBeginPeriodFullDateTime().minusDays(1);
        DateTime d2 = aggregate.getPayCalendarEntry().getBatchEndPayPeriodFullDateTime();

        Interval calEntryInterval = null;
        if (d2 != null)
        {
            calEntryInterval = new Interval(aggregate.getPayCalendarEntry().getBeginPeriodFullDateTime().minusDays(1), aggregate.getPayCalendarEntry().getEndPeriodFullDateTime().plusDays((7 - aggregate.getPayCalendarEntry().getBatchEndPayPeriodFullDateTime().getDayOfWeek())));
        }
        else
        {
            //calEntryInterval = new Interval(d1, aggregate.getPayCalendarEntry().getEndPeriodFullDateTime().plusDays((7 - d2.getDayOfWeek() ) ));
            calEntryInterval = new Interval(aggregate.getPayCalendarEntry().getBeginPeriodFullDateTime().minusDays(1), aggregate.getPayCalendarEntry().getEndPeriodFullDateTime());
        }


        for (List<FlsaWeek> weekParts : newFlsaWeeks) {
           for (FlsaWeek week : weekParts) {
               for (FlsaDay day : week.getFlsaDays()) {
                   if (calEntryInterval.contains(day.getFlsaDate().toDateTime())) {
                        updatedBlocks.add(day.getAppliedTimeBlocks());
                   }
               }
           }
        }

        //merge
        if (aggregate.getDayTimeBlockList().size() - updatedBlocks.size() == 2) {
            List<TimeBlock> firstDay = aggregate.getDayTimeBlockList().get(0);
            List<TimeBlock> lastDay = aggregate.getDayTimeBlockList().get(aggregate.getDayTimeBlockList().size() - 1);
            updatedBlocks.add(0, firstDay);
            updatedBlocks.add(lastDay);
        }

        aggregate.setDayTimeBlockList(updatedBlocks);
		//savePreviousNextCalendarTimeBlocks(flsaWeeks);
	}
	
	/**
	 * Get the list of all FlsaWeek lists for this period.  An FlsaWeek list is a set of partial FlsaWeeks that overlap a CalendarEntry boundary.  The total of
	 * all days in an FlsaWeek list adds up to one complete week.  This is done so WeeklyOvertimeRule calculations are done for multiple Calendar periods.
	 * 
	 * @param principalId The principal id to apply the rules to
	 * @param beginDate The begin date of the CalendarEntry
	 * @param endDate The end date of the CalendarEntry
	 * @param aggregate The aggregate of the applicable TimeBlocks
	 * 
	 * @return the list of all FlsaWeek lists for this period
	 */
	protected List<List<FlsaWeek>> getFlsaWeeks(String principalId, DateTime beginDate, DateTime endDate, TkTimeBlockAggregate aggregate) {
		List<List<FlsaWeek>> flsaWeeks = new ArrayList<List<FlsaWeek>>();
		
//        DateTimeZone zone = HrServiceLocator.getTimezoneService().getTargetUserTimezoneWithFallback();
        DateTimeZone zone = DateTimeZone.forID(HrServiceLocator.getTimezoneService().getUserTimezone(principalId));
		List<FlsaWeek> currentWeeks = aggregate.getFlsaWeeks(zone, 0, false);
		
		for (ListIterator<FlsaWeek> weekIterator = currentWeeks.listIterator(); weekIterator.hasNext(); ) {
			List<FlsaWeek> flsaWeek = new ArrayList<FlsaWeek>();
			
			int index = weekIterator.nextIndex();
			FlsaWeek currentWeek = weekIterator.next();
			
			if (index == 0 && !currentWeek.isFirstWeekFull()) {
				TimesheetDocumentHeader timesheetDocumentHeader = TkServiceLocator.getTimesheetDocumentHeaderService().getPreviousDocumentHeader(principalId, beginDate);
				if (timesheetDocumentHeader != null) {
                    TimesheetDocument timesheetDocument = TkServiceLocator.getTimesheetService().getTimesheetDocument(timesheetDocumentHeader.getDocumentId());
                    List<String> assignmentKeys = new ArrayList<String>();
                    for(Assignment assignment : timesheetDocument.getAllAssignments()) {
                        assignmentKeys.add(assignment.getAssignmentKey());
                    }

                    List<TimeBlock> timeBlocks = TkServiceLocator.getTimeBlockService().getTimeBlocks(timesheetDocumentHeader.getDocumentId());
                    List<LeaveBlock> leaveBlocks = LmServiceLocator.getLeaveBlockService().getLeaveBlocksForTimeCalendar(principalId, timesheetDocumentHeader.getBeginDateTime().toLocalDate(), timesheetDocumentHeader.getEndDateTime().toLocalDate(), assignmentKeys);
                    if (CollectionUtils.isNotEmpty(timeBlocks)) {
						CalendarEntry calendarEntry =  HrServiceLocator.getCalendarEntryService().getCalendarDatesByPayEndDate(principalId, timesheetDocumentHeader.getEndDateTime(), HrConstants.PAY_CALENDAR_TYPE);
						Calendar calendar = HrServiceLocator.getCalendarService().getCalendar(calendarEntry.getHrCalendarId());
                        TkTimeBlockAggregate previousAggregate = new TkTimeBlockAggregate(timeBlocks, leaveBlocks, calendarEntry, calendar, true);
						List<FlsaWeek> previousWeek = previousAggregate.getFlsaWeeks(zone, 0, false);
						if (CollectionUtils.isNotEmpty(previousWeek)) {
							flsaWeek.add(previousWeek.get(previousWeek.size() - 1));
						}
					}
				 }
			}
			
			flsaWeek.add(currentWeek);
			
			if (index == currentWeeks.size() - 1 && !currentWeek.isLastWeekFull()) {
				TimesheetDocumentHeader timesheetDocumentHeader = TkServiceLocator.getTimesheetDocumentHeaderService().getNextDocumentHeader(principalId, endDate);
				if (timesheetDocumentHeader != null) {
                    TimesheetDocument timesheetDocument = TkServiceLocator.getTimesheetService().getTimesheetDocument(timesheetDocumentHeader.getDocumentId());
                    List<String> assignmentKeys = new ArrayList<String>();
                    for(Assignment assignment : timesheetDocument.getAllAssignments()) {
                        assignmentKeys.add(assignment.getAssignmentKey());
                    }
					List<TimeBlock> timeBlocks = TkServiceLocator.getTimeBlockService().getTimeBlocks(timesheetDocumentHeader.getDocumentId());
                    List<LeaveBlock> leaveBlocks = LmServiceLocator.getLeaveBlockService().getLeaveBlocksForTimeCalendar(principalId, timesheetDocumentHeader.getBeginDateTime().toLocalDate(), timesheetDocumentHeader.getEndDateTime().toLocalDate(), assignmentKeys);

					if (CollectionUtils.isNotEmpty(timeBlocks)) {
						CalendarEntry calendarEntry =  HrServiceLocator.getCalendarEntryService().getCalendarDatesByPayEndDate(principalId, timesheetDocumentHeader.getEndDateTime(), HrConstants.PAY_CALENDAR_TYPE);
                        Calendar calendar = HrServiceLocator.getCalendarService().getCalendar(calendarEntry.getHrCalendarId());
						TkTimeBlockAggregate nextAggregate = new TkTimeBlockAggregate(timeBlocks, leaveBlocks, calendarEntry, calendar, true);
						List<FlsaWeek> nextWeek = nextAggregate.getFlsaWeeks(zone, 0 , false);
						if (CollectionUtils.isNotEmpty(nextWeek)) {
							flsaWeek.add(nextWeek.get(0));
						}
					}
				 }
			}
			
			flsaWeeks.add(flsaWeek);
		}

        List<List<FlsaWeek>> newFlsaWeeks = new ArrayList<List<FlsaWeek>>();

        for (List<FlsaWeek> thisWeek : flsaWeeks)
        {
            if (thisWeek.size() == 2)
            {
                List<FlsaWeek> tmpList = new ArrayList<FlsaWeek>();

                if ( (thisWeek.get(0).getFlsaDays().size() + thisWeek.get(1).getFlsaDays().size()) > 7)
                {
                    FlsaWeek tmpWeek = new FlsaWeek(thisWeek.get(0).getFlsaDayConstant(), thisWeek.get(0).getFlsaTime(), thisWeek.get(0).getPayPeriodBeginTime());
                    for (FlsaDay tmpDay : thisWeek.get(0).getFlsaDays()) {
                        if (tmpDay.getFlsaDate().toDateTime().isBefore(endDate.toDateTime()))
                        {
                            tmpWeek.addFlsaDay(tmpDay);
                        }
                    }
                    tmpList.add(tmpWeek);
                    tmpList.add(thisWeek.get(1));
                    newFlsaWeeks.add(tmpList);
                }
                else
                {
                    newFlsaWeeks.add(thisWeek);
                }
            }
            else
            {
                newFlsaWeeks.add(thisWeek);
            }
        }

		return newFlsaWeeks;
	}
	
	/**
	 * Get the hours worked for the given FlsaWeeks with EarnCodes within maxHoursEarnCodes.
	 * 
	 * @param flsaWeeks The FlsaWeeks to get the hours from
	 * @param maxHoursEarnCodes The EarnCodes used to determine what is applicable as a max hour
	 * 
	 * @return the maximum worked hours for the given FlsaWeeks
	 */
	protected BigDecimal getHours(List<FlsaWeek> flsaWeeks, Set<String> maxHoursEarnCodes) {
		BigDecimal maxHours = BigDecimal.ZERO;
		
		for (FlsaWeek flsaWeek : flsaWeeks) {
			for (FlsaDay flsaDay : flsaWeek.getFlsaDays()) {
				for (TimeBlock timeBlock : flsaDay.getAppliedTimeBlocks()) {
					for (TimeHourDetail timeHourDetail : timeBlock.getTimeHourDetails()) {
						if (maxHoursEarnCodes.contains(timeHourDetail.getEarnCode())) {
							maxHours = maxHours.add(timeHourDetail.getHours(), HrConstants.MATH_CONTEXT);
						}

					}
				}
                for (LeaveBlock leaveBlock : flsaDay.getAppliedLeaveBlocks()) {
                    if (maxHoursEarnCodes.contains(leaveBlock.getEarnCode())) {
                        maxHours = maxHours.add(leaveBlock.getLeaveAmount().negate());
                    }
                }
			}
		}
		
		return maxHours;
	}

    /**
     * Get the hours worked for the given FlsaWeeks with EarnCodes within maxHoursEarnCodes.
     *
     * @param flsaWeeks The FlsaWeeks to get the hours from
     * @param maxHoursEarnCodes The EarnCodes used to determine what is applicable as a max hour
     *
     * @return the maximum worked hours for the given FlsaWeeks
     */
    protected BigDecimal getMinutes(List<FlsaWeek> flsaWeeks, Set<String> maxHoursEarnCodes) {
        BigDecimal maxMinutes = BigDecimal.ZERO;

        for (FlsaWeek flsaWeek : flsaWeeks) {
            for (FlsaDay flsaDay : flsaWeek.getFlsaDays()) {
                for (TimeBlock timeBlock : flsaDay.getAppliedTimeBlocks()) {
                    for (TimeHourDetail timeHourDetail : timeBlock.getTimeHourDetails()) {
                        if (maxHoursEarnCodes.contains(timeHourDetail.getEarnCode())) {
                            maxMinutes = maxMinutes.add(timeHourDetail.getTotalMinutes(), HrConstants.MATH_CONTEXT);
                        }

                    }
                }
                for (LeaveBlock leaveBlock : flsaDay.getAppliedLeaveBlocks()) {
                    if (maxHoursEarnCodes.contains(leaveBlock.getEarnCode())) {
                        maxMinutes = maxMinutes.add(leaveBlock.getLeaveAmount().multiply(BigDecimal.valueOf(60), HrConstants.MATH_CONTEXT).negate());
                    }
                }
            }
        }

        return maxMinutes;
    }
	
	/**
	 * Returns the overtime EarnCode.  Defaults to the EarnCode on the WeeklyOvertimeRule but overrides with the WorkArea default overtime EarnCode and 
	 * the TimeBlock overtime preference EarnCode in that order if they exist.
	 * 
	 * @param weeklyOvertimeRule The WeeklyOvertimeRule to use when calculating the overtime EarnCode
	 * @param timeBlock The TimeBlock to use when calculating the overtime EarnCode
	 * @param asOfDate The effectiveDate of the WorkArea
	 * 
	 * @return the overtime EarnCode
	 */
	protected String getOvertimeEarnCode(WeeklyOvertimeRule weeklyOvertimeRule, TimeBlockContract timeBlock, LocalDate asOfDate) {

        String overtimeEarnCode = weeklyOvertimeRule.getConvertToEarnCode();
        LOG.info("weekly overtime rule code is " + overtimeEarnCode);

        if (weeklyOvertimeRule.isOverrideWorkAreaDefaultOvertime())
        {
            return overtimeEarnCode;
        }

        // KPME-2554 use time block end date instead of passed in asOfDate
        WorkArea workArea = HrServiceLocator.getWorkAreaService().getWorkArea(timeBlock.getWorkArea(), timeBlock.getEndDateTime().toLocalDate());
		if ( StringUtils.isNotBlank(workArea.getDefaultOvertimeEarnCode()) ) {
			overtimeEarnCode = workArea.getDefaultOvertimeEarnCode();
            LOG.info("weekly overtime rule code is from work area -- " + overtimeEarnCode);
		}

        if (StringUtils.isNotEmpty(timeBlock.getOvertimePref())) {
        	overtimeEarnCode = timeBlock.getOvertimePref();
            LOG.info("weekly overtime rule code is from timeblock.getOvertimePref -- " + overtimeEarnCode);
        }
        
        return overtimeEarnCode;
	}
	
	/**
	 * Applies overtime to the given FlsaWeeks.
	 * 
	 * @param flsaWeeks The FlsaWeeks to apply the overtime to
	 * @param weeklyOvertimeRule The WeeklyOvertimeRule in effective
	 * @param asOfDate The effectiveDate of the WorkArea
	 * @param convertFromEarnCodes The EarnCodes to convert to overtime
	 * @param overtimeHours The number of overtime hours to apply
	 */
	protected BigDecimal applyOvertimeToFlsaWeeks(List<FlsaWeek> flsaWeeks, WeeklyOvertimeRule weeklyOvertimeRule, LocalDate asOfDate, Set<String> convertFromEarnCodes, BigDecimal overtimeHours, BigDecimal overtimeMinutes) {
		List<FlsaDay> flsaDays = getFlsaDays(flsaWeeks);


        BigDecimal otMinutesLeft = applyOvertimeToFlsaWeek(flsaDays, weeklyOvertimeRule, asOfDate, convertFromEarnCodes, overtimeHours, overtimeMinutes);

		removeEmptyOvertime(flsaDays, weeklyOvertimeRule, asOfDate);
        return otMinutesLeft;
	}
	
	/**
	 * Gets the list of FlsaDays in the given FlsaWeek.
	 * 
	 * @param flsaWeeks The FlsaWeek to fetch the FlsaDays from
	 * 
	 * @return the list of FlsaDays in the given FlsaWeek
	 */
	protected List<FlsaDay> getFlsaDays(List<FlsaWeek> flsaWeeks) {
		List<FlsaDay> flsaDays = new ArrayList<FlsaDay>();
		
		for (FlsaWeek flsaWeek : flsaWeeks) {
			flsaDays.addAll(flsaWeek.getFlsaDays());
		}
		
		return flsaDays;
	}
	
	/**
	 * Applies positive overtime to the given FlsaDays.
	 * 
	 * @param flsaDays The FlsaDays to apply the overtime to
	 * @param weeklyOvertimeRule The WeeklyOvertimeRule in effective
	 * @param asOfDate The effectiveDate of the WorkArea
	 * @param convertFromEarnCodes The EarnCodes to convert to overtime
	 * @param overtimeHours The number of overtime hours to apply
	 */
	protected BigDecimal applyOvertimeToFlsaWeek(List<FlsaDay> flsaDays, WeeklyOvertimeRule weeklyOvertimeRule, LocalDate asOfDate, Set<String> convertFromEarnCodes, BigDecimal overtimeHours, BigDecimal overtimeMinutes) {
		for (ListIterator<FlsaDay> dayIterator = flsaDays.listIterator(flsaDays.size()); dayIterator.hasPrevious(); ) {
			FlsaDay flsaDay = dayIterator.previous();
			
			List<TimeBlock> timeBlocks = new ArrayList<TimeBlock>(flsaDay.getAppliedTimeBlocks());
            //timeBlocks.addAll(flsaDay.get)

//            flsaDay.getAppliedLeaveBlocks()
			Collections.sort(timeBlocks, new Comparator<TimeBlock>() {
				public int compare(TimeBlock timeBlock1, TimeBlock timeBlock2) {
					return ObjectUtils.compare(timeBlock1.getBeginDateTime(), timeBlock2.getBeginDateTime());
				}
			});

            List<TimeBlockBo> bos = ModelObjectUtils.transform(timeBlocks, toTimeBlockBo);
			for (ListIterator<TimeBlockBo> timeBlockIterator = bos.listIterator(bos.size()); timeBlockIterator.hasPrevious(); ) {
				TimeBlockBo timeBlock = timeBlockIterator.previous();
				String overtimeEarnCode = getOvertimeEarnCode(weeklyOvertimeRule, timeBlock, asOfDate);
                LOG.info("applying " + overtimeHours + "hours FROM earnCodes : " + convertFromEarnCodes + " to overTime earnCode " + overtimeEarnCode);


				overtimeMinutes = applyOvertimeOnTimeBlock(timeBlock, overtimeEarnCode, convertFromEarnCodes, overtimeHours, overtimeMinutes);
                LOG.info("after this run, OT minutes = " + overtimeMinutes);
			}
			flsaDay.setAppliedTimeBlocks(ModelObjectUtils.transform(bos, toTimeBlock));
			flsaDay.remapTimeHourDetails();
		}
        return overtimeMinutes;
	}


	protected void removeEmptyOvertime(List<FlsaDay> flsaDays, WeeklyOvertimeRule weeklyOvertimeRule, LocalDate asOfDate) {
		for (FlsaDay flsaDay : flsaDays ) {
			List<TimeBlockBo> timeBlocks = ModelObjectUtils.transform(flsaDay.getAppliedTimeBlocks(), toTimeBlockBo);
			for (TimeBlockBo timeBlock : timeBlocks ) {
				String overtimeEarnCode = getOvertimeEarnCode(weeklyOvertimeRule, timeBlock, asOfDate);

				List<TimeHourDetailBo> timeHourDetails = timeBlock.getTimeHourDetails();

                List<TimeHourDetailBo> newTimeHourDetails = new ArrayList<TimeHourDetailBo>();
                for (TimeHourDetailBo detail : timeHourDetails)
                {
                    if ( (detail.getHours().compareTo(BigDecimal.ZERO) != 0) || StringUtils.equals(detail.getEarnCode(), timeBlock.getEarnCode()) )
                    {
                        newTimeHourDetails.add(detail);
                    }
                }

                timeBlock.setTimeHourDetails(newTimeHourDetails);
			}
            flsaDay.setAppliedTimeBlocks(ModelObjectUtils.transform(timeBlocks, toTimeBlock) );
		}
	}

	/**
	 * Applies overtime additions to the indicated TimeBlock.
	 *
	 * @param timeBlock The time block to apply the overtime on.
	 * @param overtimeEarnCode The overtime earn code to apply overtime to.
	 * @param applyToEarnCodes The earn codes on the time block that can potentially be converted to overtime
	 * @param overtimeHours The overtime hours to apply.
	 *
	 * @return the amount of overtime hours remaining to be applied.
	 */
    protected BigDecimal applyOvertimeOnTimeBlock(TimeBlockBo timeBlock, String overtimeEarnCode, Set<String> applyToEarnCodes, BigDecimal overtimeHours, BigDecimal overtimeMinutes) {
        List<TimeHourDetailBo> timeHourDetails = timeBlock.getTimeHourDetails();

        EarnCodeContract earnCodeObj = HrServiceLocator.getEarnCodeService().getEarnCode(overtimeEarnCode, timeBlock.getEndDateTime().toLocalDate());

        BigDecimal newOtActual = BigDecimal.ZERO;
        BigDecimal newOtActualMinutes = BigDecimal.ZERO;

        BigDecimal maxOtToConvert = BigDecimal.ZERO;
        BigDecimal existingOtActualHours = BigDecimal.ZERO;
        BigDecimal existingOtActualMinutes = BigDecimal.ZERO;
        BigDecimal newOt = BigDecimal.ZERO;
        BigDecimal newOtMinutes = BigDecimal.ZERO;

        TimeHourDetailBo overtimeTimeHourDetail = null;

        List<TimeHourDetailBo> toConvert = new ArrayList<TimeHourDetailBo>();

        BigDecimal regularHours = BigDecimal.ZERO;
        BigDecimal regularMinutes = BigDecimal.ZERO;

        TimeHourDetailBo regularHoursTimeHourDetail = null;
        for (TimeHourDetailBo timeHourDetail : timeHourDetails) {
            if ( ( timeHourDetail.getEarnCode().equals(timeBlock.getEarnCode()) )
                && (applyToEarnCodes.contains(timeHourDetail.getEarnCode()) ) )
            {
                regularHours = timeHourDetail.getHours();
                regularMinutes = timeHourDetail.getTotalMinutes();
                regularHoursTimeHourDetail = timeHourDetail;
                toConvert.add(timeHourDetail);
            }
            else if (timeHourDetail.getEarnCode().equals(overtimeEarnCode))
            {
                overtimeTimeHourDetail = timeHourDetail;
            }
        }

        if (regularHoursTimeHourDetail == null)
        {
            //throw new RuntimeException("No TimeHourDetailBo exists for TimeBlock's BO");
            return overtimeMinutes;
        }

        //this is the actual hours of overtime worked before any multiplying factor.
        existingOtActualHours = timeBlock.getHours().subtract(regularHours);
        existingOtActualMinutes = timeBlock.getTotalMinutes().subtract(regularMinutes);

        if (overtimeMinutes.compareTo(BigDecimal.ZERO) <= 0)                  //new overtime is less than or equal to zero
        {
            if (existingOtActualMinutes.compareTo(BigDecimal.ZERO) != 0) {
                //handle this case Friday morning
                regularHoursTimeHourDetail.setHours(regularHoursTimeHourDetail.getHours().add(existingOtActualHours));
                regularHoursTimeHourDetail.setTotalMinutes(regularHoursTimeHourDetail.getTotalMinutes().add(existingOtActualMinutes));
            }
            newOtActual = BigDecimal.ZERO;       //could this be a problem??
            newOtActualMinutes = BigDecimal.ZERO;
            newOt = BigDecimal.ZERO;
            newOtMinutes = BigDecimal.ZERO;
        }
        else if (overtimeMinutes.compareTo(existingOtActualMinutes) <= 0)       //new overtime is less than old overtime
        {
            //subtract from existing OT until existing OT is zero, if necessary add to toConvert List until the difference is ZERO
            BigDecimal newOtToRemove = existingOtActualHours.subtract(overtimeHours);
            BigDecimal newOtMinToRemove = existingOtActualMinutes.subtract(overtimeMinutes);

            newOtActual = overtimeHours;
            newOtActualMinutes = overtimeMinutes;
            newOt = earnCodeObj.getInflateFactor().multiply(overtimeHours, HrConstants.MATH_CONTEXT).setScale(HrConstants.BIG_DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
            newOtMinutes = earnCodeObj.getInflateFactor().multiply(overtimeMinutes, HrConstants.MATH_CONTEXT);
            regularHoursTimeHourDetail.setHours(regularHoursTimeHourDetail.getHours().add(newOtToRemove));
            regularHoursTimeHourDetail.setTotalMinutes(regularHoursTimeHourDetail.getTotalMinutes().add(newOtMinToRemove));
        }
        else if (overtimeMinutes.compareTo(existingOtActualMinutes) > 0)        //new overtime is greater than old overtime
        {
            //add difference to OT, up to maxOtToConvert.  remove from toConvert as necessary
            BigDecimal newOtToAdd = overtimeHours.subtract(existingOtActualHours);
            BigDecimal newOtMinToAdd = overtimeMinutes.subtract(existingOtActualMinutes);
            if (newOtToAdd.compareTo(overtimeHours) >= 0) {
                newOtToAdd = overtimeHours;
                newOtMinToAdd = overtimeMinutes;
            }

            if (newOtToAdd.compareTo(timeBlock.getHours()) > 0)
            {
                newOtToAdd = timeBlock.getHours();
                newOtMinToAdd = timeBlock.getTotalMinutes();
            }


            BigDecimal toReduce = newOtToAdd;
            BigDecimal toReduceMin = newOtMinToAdd;

            BigDecimal existingHours = regularHoursTimeHourDetail.getHours();
            BigDecimal existingMinutes = regularHoursTimeHourDetail.getTotalMinutes();
            if (toReduceMin.compareTo(existingMinutes) >= 0)
            {
                regularHoursTimeHourDetail.setHours(BigDecimal.ZERO);
                regularHoursTimeHourDetail.setTotalMinutes(BigDecimal.ZERO);
            }
            else if (toReduceMin.compareTo(BigDecimal.ZERO) > 0)
            {
                regularHoursTimeHourDetail.setHours(regularHoursTimeHourDetail.getHours().subtract(toReduce));
                regularHoursTimeHourDetail.setTotalMinutes(regularHoursTimeHourDetail.getTotalMinutes().subtract(toReduceMin));
            }

            newOtActual = existingOtActualHours.add(newOtToAdd);
            newOtActualMinutes = existingOtActualMinutes.add(newOtMinToAdd);
            newOt = earnCodeObj.getInflateFactor().multiply(newOtActual, HrConstants.MATH_CONTEXT).setScale(HrConstants.BIG_DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
            newOtMinutes = earnCodeObj.getInflateFactor().multiply(newOtActualMinutes, HrConstants.MATH_CONTEXT);
        }


        if (overtimeTimeHourDetail != null) {
            overtimeTimeHourDetail.setHours(newOt);
            overtimeTimeHourDetail.setTotalMinutes(newOtMinutes);
        } else {
            TimeHourDetailBo newTimeHourDetail = new TimeHourDetailBo();
            newTimeHourDetail.setTkTimeBlockId(timeBlock.getTkTimeBlockId());
            newTimeHourDetail.setEarnCode(overtimeEarnCode);
            newTimeHourDetail.setHours(newOt);
            newTimeHourDetail.setTotalMinutes(newOtMinutes);
            timeBlock.addTimeHourDetail(newTimeHourDetail);
        }

        return overtimeMinutes.subtract(newOtActualMinutes);
    }

	protected <T extends TimeHourDetailContract> T getTimeHourDetailByEarnCode(List<T> timeHourDetails, Collection<String> earnCodes) {
		T result = null;
		
		for (T timeHourDetail : timeHourDetails) {
			if (earnCodes.contains(timeHourDetail.getEarnCode())) {
				result = timeHourDetail;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Saves the TimeBlocks from both the previous and next CalendarEntry, since these are not saved automatically by calling methods.
	 * 
	 * @param flsaWeeks The list of FlsaWeek lists
	 */
	protected void savePreviousNextCalendarTimeBlocks(List<List<FlsaWeek>> flsaWeeks) {
		for (ListIterator<List<FlsaWeek>> weeksIterator = flsaWeeks.listIterator(); weeksIterator.hasNext(); ) {
			int index = weeksIterator.nextIndex();
			List<FlsaWeek> currentWeekParts = weeksIterator.next();
			
			if (index == 0 && currentWeekParts.size() > 1) {
				FlsaWeek previousFlsaWeek = currentWeekParts.get(0);
				for (FlsaDay flsaDay : previousFlsaWeek.getFlsaDays()) {
					KRADServiceLocator.getBusinessObjectService().save(ModelObjectUtils.transform(flsaDay.getAppliedTimeBlocks(), toTimeBlockBo));
                    //TkServiceLocator.getTimeBlockService().saveOrUpdateTimeBlocks(flsaDay.getAppliedTimeBlocks());
				}
			}
				
			if (index == flsaWeeks.size() - 1 && currentWeekParts.size() > 1) {
				FlsaWeek nextFlsaWeek = currentWeekParts.get(currentWeekParts.size() - 1);
				for (FlsaDay flsaDay : nextFlsaWeek.getFlsaDays()) {
					KRADServiceLocator.getBusinessObjectService().save(ModelObjectUtils.transform(flsaDay.getAppliedTimeBlocks(), toTimeBlockBo));
                    //TkServiceLocator.getTimeBlockService().saveOrUpdateTimeBlocks(flsaDay.getAppliedTimeBlocks());
				}
			}
		}
	}

	@Override
	public List<WeeklyOvertimeRule> getWeeklyOvertimeRules(LocalDate asOfDate) {
		return weeklyOvertimeRuleDao.findWeeklyOvertimeRules(asOfDate);
	}

	@Override
	public void saveOrUpdate(WeeklyOvertimeRule weeklyOvertimeRule) {
		weeklyOvertimeRuleDao.saveOrUpdate(weeklyOvertimeRule);
	}

	@Override
	public void saveOrUpdate(List<WeeklyOvertimeRule> weeklyOvertimeRules) {
		weeklyOvertimeRuleDao.saveOrUpdate(weeklyOvertimeRules);
	}

	public void setWeeklyOvertimeRuleDao(WeeklyOvertimeRuleDao weeklyOvertimeRuleDao) {
		this.weeklyOvertimeRuleDao = weeklyOvertimeRuleDao;
	}

	@Override
	public WeeklyOvertimeRule getWeeklyOvertimeRule(String tkWeeklyOvertimeRuleId) {
		return weeklyOvertimeRuleDao.getWeeklyOvertimeRule(tkWeeklyOvertimeRuleId);
	}

}
