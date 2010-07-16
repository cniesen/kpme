package org.kuali.hr.leave.calendar.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.SerializationUtils;
import org.kuali.hr.leave.calendar.Day;
import org.kuali.hr.leave.calendar.Month;
import org.kuali.hr.leave.util.T2User;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CalendarServiceImpl implements CalendarService {

	public Month getMonth(T2User employee, Date dayInMonth) {
		// TODOcal == beginDateCalendar for ledgerItems and the beginning of the iterator
		Calendar cal = new GregorianCalendar(dayInMonth.getYear()+1900, dayInMonth.getMonth(), 1);
		Calendar endDateCalendar = (Calendar) SerializationUtils.clone(cal);
		Integer daysOfThisMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		endDateCalendar.set(Calendar.DAY_OF_MONTH, endDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		Month month = new Month();
		month.setMonth(cal.get(Calendar.MONTH));
		month.setYear(cal.get(Calendar.YEAR));
		month.setDaysBeforeFirst(cal.get(Calendar.DAY_OF_WEEK) - 1);
// TODO		List<Ledger> ledgerItems = EptoServiceLocator.getLedgerService().getLedgerList(employee.getEmplid(), new Date(cal.getTimeInMillis()), new Date(endDateCalendar.getTimeInMillis()));

		for (int i = 1; i <= daysOfThisMonth; i++) {

			Day dayToAdd = new Day();
			dayToAdd.setDayInMonth(i);
			dayToAdd.setDayInWeek(cal.get(Calendar.DAY_OF_WEEK));
			dayToAdd.setMonth(month);
// TODO			for (Iterator<Ledger> iter = ledgerItems.iterator(); iter.hasNext();){
// TODO				Ledger ledgerItem = (Ledger) iter.next();
// TODO
// TODO				if ((ledgerItem.getStatus() == 1) &&
// TODO						(StringUtils.isNotEmpty(ledgerItem.getPtoCode())) &&
// TODO						(i == ledgerItem.getPtoDate().getDate())){
// TODO						dayToAdd.getLedgerItems().add(ledgerItem);
// TODO				}
// TODO			}
			month.getDays().add(dayToAdd);

			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		cal.add(Calendar.DAY_OF_MONTH, -1);
		month.setDaysAfterLast(7 - cal.get(Calendar.DAY_OF_WEEK));

		// TODOSet the "next" and "previous" months and years
		if (month.getMonth() == 0) {
			month.setPreviousMonth(11);
			month.setPreviousYear(month.getYear() - 1);
		} else {
			month.setPreviousMonth(month.getMonth() - 1);
			month.setPreviousYear(month.getYear());
		}

		if (month.getMonth() == 11) {
			month.setNextMonth(0);
			month.setNextYear(month.getYear() + 1);
		} else {
			month.setNextMonth(month.getMonth() + 1);
			month.setNextYear(month.getYear());
		}

		return month;
	}

 	public void insertDocumentHeaderOnMonth(Month month, String supervisorId, String employeeId){
// TODO 	EptoDocumentHeader docHeader = EptoServiceLocator.getEptoCalendarWorkflowService().getDocumentHeaderForEmployee(supervisorId, employeeId, month);
// TODO 	if(docHeader!=null){
// TODO 		month.setDocumentId(docHeader.getDocumentNumber());
// TODO			month.setDocumentStatus(StringUtils.equals(docHeader.getDocumentNumber(), "-1") ? "F" : docHeader.getDocumentStatusCode());
// TODO		}
// TODO		else{
// TODO			month.setApprovalRequested(false);
// TODO		}
 	}
// TODO
// TODO	public List<Ledger> createDateRange(Month month, Date fromDate, Date toDate, BigDecimal hours, Boolean applyToYTDUsed, LeaveCode leaveCode, Boolean isWeekendIncluded, EptoUser employee, String description,
// TODO		boolean accrualGenerated, Long scheduledPerstId) {
// TODO
// TODO		int dayDifference = new TimelessDate().dayDifference(fromDate, toDate);
// TODO		List<Ledger> ledgerList = new ArrayList<Ledger>();
// TODO
// TODO		Calendar cal = Calendar.getInstance();
// TODO		cal.setTime(fromDate);
// TODO
// TODO		for (int i = 0; i <= dayDifference; i++) {
// TODO
// TODO			Ledger ledger = new Ledger();
// TODO			ledger.setPtoDate(TimelessDate.getSqlDate(cal.getTime()));
// TODO			ledger.setEmplId(employee.getEmplid());
// TODO			ledger.setLeaveCodePsuedoKey(leaveCode.getPersistableId());
// TODO			ledger.setPtoCode(leaveCode.getLeaveCode());
// TODO			ledger.setAccrualCategoryPseudoKey(leaveCode.getAccrualCategoryPseudoKey());
// TODO			ledger.setStatus(1);
// TODO			ledger.setHours(hours);
// TODO			ledger.setApplyToYTDUsed(applyToYTDUsed);
// TODO			ledger.setEmplidActivated(EptoContext.getUser().getEmplid());
// TODO			ledger.setEmplidInactivated(null);
// TODO			ledger.setTimestampActivated(new Date(System.currentTimeMillis()));
// TODO			ledger.setTimestamInactivated(null);
// TODO			ledger.setAccuralGenerated(accrualGenerated);
// TODO			ledger.setLockingNumber(0);
// TODO			ledger.setDescription(description);
// TODO			if(scheduledPerstId!=null){
// TODO				ledger.setScheduledTimeOffPsuedoKey(scheduledPerstId);
// TODO			}
// TODO			ledger.setLeaveCodeDescription(leaveCode.getDisplayName());
// TODO			ledger.setDontAllowRemoval(Boolean.valueOf(false));
// TODO
// TODO
// TODO			if (isWeekendIncluded ||
// TODO					(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7)) {
// TODO
// TODO				ledgerList.add(ledger);
// TODO			}
// TODO			cal.add(Calendar.DAY_OF_MONTH, 1);
// TODO		}
// TODO
// TODO		// TODOSet calendar to the last day that PTO was entered on
// TODO		cal.add(Calendar.DAY_OF_MONTH, -1);
// TODO		// TODOallows this code to do double duty between Calendar action and TimeoffdetailAction
// TODO		if (month != null) {
// TODO			month.setMonth(cal.get(Calendar.MONTH));
// TODO			month.setYear(cal.get(Calendar.YEAR));
// TODO		}
// TODO
// TODO		return ledgerList;
// TODO	}
// TODO
// TODO	// create a month object with ledger information and also save the
// TODO	public void addRange(Month month, Date fromDate, Date toDate, BigDecimal hours, Boolean applyToYTDUsed, String leaveCodeKey, Boolean isWeekendIncluded, EptoUser employee, String description, boolean accrualGenerated, boolean validateLeaveCode) {
// TODO
// TODO		Long leaveCodeId = EptoServiceLocator.getLeaveCodeService().getLeaveCodeId(leaveCodeKey);
// TODO
// TODO		// write method to fetch leave code by psuedokey and effect date
// TODO		// we want the most effective and active record
// TODO		LeaveCode leaveCode = EptoServiceLocator.getLeaveCodePersistenceService().getLeaveCodeByKey(leaveCodeId, new Date(System.currentTimeMillis()));
// TODO
// TODO		// mngantun added: basic version will need to set hours in ledger to the leave code default hours
// TODO		RateRangeAggregate rateRangeAggregate = EptoServiceLocator.getAccrualService().getRateRangeAggregate(employee.getEmplid(),month.getYear());
// TODO		double accrualRateModifier = rateRangeAggregate.getRate(fromDate).getAccrualRatePercentageModifier();
// TODO		if (hours == null) {
// TODO			hours = new BigDecimal(accrualRateModifier *  leaveCode.getDefaultHours()).setScale(1, BigDecimal.ROUND_HALF_UP);
// TODO		}
// TODO
// TODO		Long scheduledCodeId = EptoServiceLocator.getLeaveCodeService().getScheduledTimeOffId(leaveCodeKey);
// TODO		List<Ledger> ledgerList = this.createDateRange(month, fromDate, toDate, hours, applyToYTDUsed, leaveCode, isWeekendIncluded, employee, description, accrualGenerated, scheduledCodeId);
// TODO		// run validation for Schedule time off and be sure the hours used are the hours alloted for that holiday
// TODO		// mngantung commented out; we have this in HolidayLeaveCodeValidator
// TODO		boolean isValid = true;
// TODO
// TODO
// TODO		// run validation and save if we pass return w/o saving if we don't
// TODO		if (validateLeaveCode) {
// TODO			// negate all hours value
// TODO			// hours = (hours.abs()).multiply(new BigDecimal(-1));
// TODO			isValid = isValid && LeaveCodeWebValidation.isValid(leaveCode, ledgerList, hours, employee, fromDate, toDate);
// TODO			isValid = isValid && LedgerEntryValidator.validateEntryNotExceededMaxHours(ledgerList, employee, hours);
// TODO		} else { // correction mode validation
// TODO			isValid = isValid && CorrectionModeWebValidation.isValid(leaveCode, applyToYTDUsed, ledgerList, hours, employee, fromDate, toDate);
// TODO		}
// TODO
// TODO		isValid = isValid && new CalendarRule().validateAddDateRangeToApprovedMonth(ledgerList);
// TODO
// TODO		if (isValid) {
// TODO
// TODO			String altSchedTimeOffs = "";
// TODO			// if FMLA holiday, INJ charged to HOL
// TODO			if (EptoConstants.TIMEOFFS_AFFECT_HOLIDAY_PTO.contains(leaveCode.getLeaveCode())) {
// TODO				// get the list of empl sched time off starts with the one that will expire soon
// TODO				List<EmplSchedTimeOff> emplSchedTimeOffs = EptoServiceLocator.getEmplSchedTimeOffPersistenceService().getEmplSchedTimeOffSoonExpired(employee.getEmplid(), toDate);
// TODO
// TODO				// sum up the ledger hours
// TODO				BigDecimal ledgerHoursTotal = EptoServiceLocator.getLedgerService().getTotalLedgerHours(ledgerList);
// TODO				// loop through each sched time offs
// TODO				for(EmplSchedTimeOff emplSchedTimeOff: emplSchedTimeOffs) {
// TODO					BigDecimal holHours = new BigDecimal(accrualRateModifier *  emplSchedTimeOff.getHours().intValue());
// TODO					// if one full holiday will be used
// TODO					if (ledgerHoursTotal.intValue() == 0) {
// TODO						break;
// TODO					} else if ((ledgerHoursTotal.subtract(holHours.abs().multiply(new BigDecimal(-1)))).compareTo(new BigDecimal(0)) <= 0) {
// TODO						// add to alt sched time off
// TODO						altSchedTimeOffs = buildCSV(altSchedTimeOffs, emplSchedTimeOff.getSchedTimeOffPseudoKey().toString());
// TODO						// remove the empl sched time off
// TODO						EptoServiceLocator.getEmplSchedTimeOffPersistenceService().deleteEmplScheduledTimeOff(emplSchedTimeOff.getSchedTimeOffPseudoKey(), employee.getEmplid());
// TODO					} else { // only partial holiday hours will be taken, the rest will be transferred to PTO hours
// TODO						// remaining holiday hours transfer to PTO
// TODO						ledgerList.add(EptoServiceLocator.getHolidayService().populateLedgerForHolidayPTOTransfer(new Ledger(), leaveCode, toDate, employee, (holHours.abs().multiply(new BigDecimal(-1))).subtract(ledgerHoursTotal), "Holiday PTO Transfer", emplSchedTimeOff, false));
// TODO						ledgerList.add(EptoServiceLocator.getHolidayService().populateLedgerForHolidayPTOTransfer(new Ledger(), leaveCode, toDate, employee, ledgerHoursTotal.subtract(holHours.abs().multiply(new BigDecimal(-1))), "Holiday PTO Transfer", emplSchedTimeOff, true));
// TODO						// remove the empl sched time off
// TODO						EptoServiceLocator.getEmplSchedTimeOffPersistenceService().deleteEmplScheduledTimeOff(emplSchedTimeOff.getSchedTimeOffPseudoKey(), employee.getEmplid());
// TODO						// add to alt sched time off
// TODO						altSchedTimeOffs = buildCSV(altSchedTimeOffs, emplSchedTimeOff.getSchedTimeOffPseudoKey().toString());
// TODO						break;
// TODO					}
// TODO					ledgerHoursTotal = ledgerHoursTotal.subtract(holHours.abs().multiply(new BigDecimal(-1)));
// TODO				}
// TODO			}
// TODO			EptoServiceLocator.getLedgerService().saveToLedger(ledgerList, altSchedTimeOffs);
// TODO			if(ledgerList != null && ledgerList.size() > 0){
// TODO				Ledger firstLedger = ledgerList.get(0);
// TODO				if (leaveCode.getAffectsHolidayAccrual() || ! leaveCode.getAccrualEarned() && ! firstLedger.isAccuralGenerated()) {
// TODO					EptoServiceLocator.getAccrualService().runAccrual(firstLedger.getEmplId(), fromDate, toDate);
// TODO				}
// TODO			}
// TODO		}
// TODO	}
// TODO
// TODO	public static String buildCSV(String container, String value) {
// TODO		if (StringUtils.isEmpty(container)) {
// TODO			container = value;
// TODO		} else {
// TODO			container = container + "," + value;
// TODO		}
// TODO
// TODO		return container;
// TODO	}

}
