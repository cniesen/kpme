package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.Iterator;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetailToDistribute;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class HourDistributionValidation {

    public static boolean execute(TimesheetDocument timesheetDocument) {

        for (Iterator hoursDetailToDistributeIter = timesheetDocument.getTimeBlockToDistributeLists().iterator(); hoursDetailToDistributeIter.hasNext();) {

            HoursDetailToDistribute hoursDetailToDistribute = (HoursDetailToDistribute)hoursDetailToDistributeIter.next();
            boolean distributionByHour = false;
            boolean distributionByPercent = false;
            BigDecimal total = new BigDecimal(0);
            
            for (Iterator iter = hoursDetailToDistribute.getDistributedHours().iterator(); iter.hasNext();) {
                HourDetail hourDetail = (HourDetail)iter.next();
                if (!Utility.hasValue(hourDetail.getAssignment())) {
                    hourDetail.getEntityErrors().add(new String[] { "assignment" }, Context.getMessage(edu.iu.uis.hr.entity.logic.MessageKeyConstants.ERROR_REQUIRED_FIELD, new String[] { "Assignment " }));
                    timesheetDocument.setEntityErrors(hourDetail.getEntityErrors());
                    return false;
                } else {
                    if (!Utility.hasValue(hourDetail.getHours())){
                        hourDetail.setHours(new BigDecimal(0));
                    }
                    if (!Utility.hasValue(hourDetail.getHourDistributionPercent())){
                        hourDetail.setHourDistributionPercent(new BigDecimal(0));
                    }
                    if (hourDetail.getHours().compareTo(new BigDecimal(0)) > 0) {
                        distributionByHour = true;
                    }
                    if (hourDetail.getHourDistributionPercent().compareTo(new BigDecimal(0)) > 0) {
                        distributionByPercent = true;
                    }
                    if (distributionByHour) {
                        total =  total.add(hourDetail.getHours());                       
                    } else {
                        if (distributionByPercent) {
                           total = total.add(hourDetail.getHourDistributionPercent());
                        }
                    }
                }
            }

            if (distributionByHour && distributionByPercent) {
                hoursDetailToDistribute.getEntityErrors().add(new String[] { "timesheet" }, Context.getMessage(MessageKeyConstants.ERROR_HOUR_DISTRIBUTION_HOURS_OR_PERCENT));
            }
            if (!distributionByHour && !distributionByPercent) {
                hoursDetailToDistribute.getEntityErrors().add(new String[] { "timesheet" }, Context.getMessage(MessageKeyConstants.ERROR_HOUR_DISTRIBUTION_HOURS_OR_PERCENT));
            }
            
            if (hoursDetailToDistribute.isErroneous()){
                timesheetDocument.setEntityErrors(hoursDetailToDistribute.getEntityErrors());
                return false;
            }

            total = total.setScale(2, BigDecimal.ROUND_HALF_UP);

            if (distributionByHour && total.compareTo(hoursDetailToDistribute.getHours()) != 0) {
                hoursDetailToDistribute.getEntityErrors().add(new String[] { "hours" }, Context.getMessage(MessageKeyConstants.ERROR_HOUR_DISTRIBUTION_MISMATCH_TOTAL_HOURS, new String[] { hoursDetailToDistribute.getHours().toString()  }));
            }
            if (distributionByPercent && total.compareTo(new BigDecimal(100)) != 0) {
                hoursDetailToDistribute.getEntityErrors().add(new String[] { "timesheet" }, Context.getMessage(MessageKeyConstants.ERROR_HOUR_DISTRIBUTION_MISMATCH_TOTAL_PERCENT, new String[] { " 100 " }));
            }

            if (hoursDetailToDistribute.isErroneous()){
                timesheetDocument.setEntityErrors(hoursDetailToDistribute.getEntityErrors());
                return false;
            }
            
        }

        return true;

    }

}
