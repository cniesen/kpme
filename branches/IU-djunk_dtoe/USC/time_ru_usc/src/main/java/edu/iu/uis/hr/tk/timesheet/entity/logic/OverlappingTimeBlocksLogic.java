package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class OverlappingTimeBlocksLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(OverlappingTimeBlocksLogic.class);

//    private WebUserService webUserService;
//    private EarningService earningService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("OverlappingTimeBlocksLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }
        List hoursDetails = ((TimesheetDocument)entity).getHours().getHoursDetails();

        if (Utility.hasValue(hoursDetails)) {
            Iterator hoursDetailsItr = hoursDetails.iterator();
            while (hoursDetailsItr.hasNext()) {
            	HoursDetail hourDetail = (HoursDetail)hoursDetailsItr.next();
            	boolean result = checkForOverlappingTimesBlocks((hourDetail).getHourDetails());
            	if(result == true)
            		hourDetail.setTabStatus("true");
            }
        }
    }

    private boolean checkForOverlappingTimesBlocks(TypedPersistentMaintainedEntityList hourDetails) {
    	boolean result = false;
        if (Utility.hasValue(hourDetails)) {
            List comparisonList = new ArrayList();
            comparisonList.addAll(hourDetails);
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                if (!getEarningService().isHoursDetailCollectByHoursEarnCode(hourDetail.getEarnCode(), hourDetail.getHoursDetail().getDate()) && !(hourDetail.getBeginTime().compareTo(hourDetail.getEndTime(), hourDetail.getHoursDetail().getDate()) == 0)) {
                	if(checkForOverlappingTimesBlocks(hourDetail, comparisonList)){
                  		result = true;
                	}
                }
            }
        }
        return result;
    }

    private boolean checkForOverlappingTimesBlocks(HourDetail hourDetail, List comparisonList) {
        if (Utility.hasValue(comparisonList)) {
            Iterator comparisonListItr = comparisonList.iterator();
            while (comparisonListItr.hasNext()) {
                HourDetail compareHourDetail = (HourDetail)comparisonListItr.next();
                if (!getEarningService().isHoursDetailCollectByHoursEarnCode(compareHourDetail.getEarnCode(), compareHourDetail.getHoursDetail().getDate()) && !(hourDetail.getBeginTime().compareTo(hourDetail.getEndTime(), hourDetail.getHoursDetail().getDate()) == 0)) {    
                	if (!hourDetail.equals(compareHourDetail) && isOverlapping(hourDetail, compareHourDetail)) {
	                    //putting an error on a time field doesn't make sense in this case so it is set on the assignment
	                    if (hourDetail.isCreatedByClock() || compareHourDetail.isCreatedByClock()) {
	                        hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.OVERLAPPING_TIME_BLOCKS, new String[] { new TimelessDate(hourDetail.getHoursDetail().getDate()).toString() }));
	                    } else {
	                        hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.OVERLAPPING_TIME_BLOCKS));
	                    }
	                    return true;
	                    
	                }
                	
                }
            }
        }
        return false;
    }

    private boolean isOverlapping(HourDetail hourDetail, HourDetail compareHourDetail) {
        if (Utility.hasValue(hourDetail.getBeginTime()) && Utility.hasValue(compareHourDetail.getBeginTime())) {
            TimedDate hourDetailBegin = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getBeginTime());
            TimedDate hourDetailEnd = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getEndTime());
            TimedDate compareHourDetailBegin = new TimedDate(compareHourDetail.getHoursDetail().getDate(), compareHourDetail.getBeginTime());
            TimedDate compareHourDetailEnd = new TimedDate(compareHourDetail.getHoursDetail().getDate(), compareHourDetail.getEndTime());
            
            if (hourDetailBegin.compareTo(hourDetailEnd) == 0 || compareHourDetailBegin.compareTo(compareHourDetailEnd) == 0) {
            	return false;
            }
            if (hourDetailBegin.after(compareHourDetailBegin) && (hourDetailBegin.before(compareHourDetailEnd) || compareHourDetailEnd.after(hourDetailEnd) || hourDetailEnd.equals(compareHourDetailEnd))) {
                return true;
            } else if (compareHourDetailBegin.after(hourDetailBegin) && (compareHourDetailBegin.before(hourDetailEnd) || hourDetailEnd.after(compareHourDetailEnd) || hourDetailEnd.equals(compareHourDetailEnd))) {
                return true;
            } else if (hourDetailBegin.equals(compareHourDetailBegin)) {
                return true;
            }
        }
        return false;
    }

    public WebUserService getWebUserService() {
        return (WebUserService) Context.getService(WebUserService.class);
    }

    private EarningService getEarningService() {
        return (EarningService)Context.getService(EarningService.class);
    }

}
