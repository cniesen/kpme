package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.Iterator;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class EarnCodePerTimeBlockLogic extends AbstractLogic implements OperationalLogic {

    private static final BigDecimal MAX_HOLIDAY_HOURS = new BigDecimal(8.00);

//    private EarningService earningService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("TimeblockEarncodeLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }

        TimesheetDocument timesheetDocument = (TimesheetDocument)entity;
        Iterator hoursDetailItr = timesheetDocument.getHours().getHoursDetails().iterator();
        while (hoursDetailItr.hasNext()) {
        	HoursDetail hoursDetail = (HoursDetail)hoursDetailItr.next();
            BigDecimal holidayHours = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
            Iterator hourDetailIterator = (hoursDetail).getHourDetails().iterator();
            while (hourDetailIterator.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailIterator.next();
                if (getEarningService().isHolidayEarnCode(hourDetail.getEarnCode(), timesheetDocument.getDocumentHeader().getPayEndDate())) {
                    if (Utility.hasValue(hourDetail.getHours())) {
                        holidayHours = holidayHours.add(hourDetail.getHours());
                    }
                    if (holidayHours.compareTo(MAX_HOLIDAY_HOURS) > 0) {
                        hourDetail.getEntityErrors().add(new String[] { FieldNames.HOURS }, Context.getMessage(MessageKeyConstants.ERROR_HOLIDAY_HOURS_EXCEEDED, new String[] { MAX_HOLIDAY_HOURS.toString() }));
                        hoursDetail.setTabStatus("true");
                    }
                }
                if (getEarningService().isUnallocatedHoursEarnCode(hourDetail.getEarnCode(), timesheetDocument.getDocumentHeader().getPayEndDate())) {
                        hourDetail.getEntityWarnings().add(new String[] { FieldNames.EARN_CODE }, Context.getMessage(MessageKeyConstants.WARNING_UNALLOCATED_HOURS));
                        hoursDetail.setTabStatus("true");
                    }
                if (getEarningService().isAdditionalPaperworkEarnCode(hourDetail.getEarnCode(), timesheetDocument.getDocumentHeader().getPayEndDate())) {
                    hourDetail.getEntityWarnings().add(new String[] { FieldNames.EARN_CODE }, Context.getMessage(MessageKeyConstants.WARNING_EARN_CODE_NEEDS_PAPERWORK, new String[] { hourDetail.getEarnCode() }));
                    hoursDetail.setTabStatus("true");
                }
                if (getEarningService().isHoursDetailCollectByHoursEarnCode(hourDetail.getEarnCode(), timesheetDocument.getDocumentHeader().getPayEndDate())){
                	if (hourDetail.getHours().equals(new BigDecimal(0))){
                		hourDetail.getEntityErrors().add(new String[] { FieldNames.HOURS }, Context.getMessage(MessageKeyConstants.ERROR_INVALID_MINIMUM_TIME_BLOCK_HOURS, new String[] { "0" }));
                		hoursDetail.setTabStatus("true");
                	}
                }
            }
        }
    }

    private EarningService getEarningService() {
        return (EarningService)Context.getService(EarningService.class);
    }

//    public void setEarningService(EarningService earningService) {
//        if (earningService != null) {
//            this.earningService = earningService;
//        }
//    }

}