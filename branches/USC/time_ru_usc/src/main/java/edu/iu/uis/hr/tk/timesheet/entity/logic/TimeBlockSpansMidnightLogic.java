package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class TimeBlockSpansMidnightLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(TimeBlockSpansMidnightLogic.class);

//    private WebUserService webUserService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("TimeBlockSpansMidnightLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }
        List hoursDetails = ((TimesheetDocument)entity).getHours().getHoursDetails();

        if (Utility.hasValue(hoursDetails)) {
            Iterator hoursDetailsItr = hoursDetails.iterator();
            while (hoursDetailsItr.hasNext()) {
            	HoursDetail hourDetail = (HoursDetail)hoursDetailsItr.next();
                if(checkForTimesBlocksSpanningMidnight((hourDetail).getHourDetails())){
                 	hourDetail.setTabStatus("true");
                }
                
            }
            
        }
    }

    private boolean checkForTimesBlocksSpanningMidnight(TypedPersistentMaintainedEntityList hourDetails) {
        boolean result = false;
    	if (Utility.hasValue(hourDetails)) {
            Iterator hourDetailsItr = hourDetails.iterator();
            while (hourDetailsItr.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsItr.next();
                if (spansMidnight(hourDetail)) {
                    hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.TIME_BLOCK_SPANS_MIDNIGHT));
                    result = true;
                }
            }
        }
    	return result;
    }

    private boolean spansMidnight(HourDetail hourDetail) {
        if (Utility.hasValue(hourDetail.getBeginTime())) {
            TimedDate hourDetailBegin = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getBeginTime());
            TimedDate hourDetailEnd = new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getEndTime());

            if (hourDetailBegin.after(hourDetailEnd)) {
                return true;
            }
        }
        return false;
    }

    public WebUserService getWebUserService() {
        return (WebUserService) Context.getService(WebUserService.class);
    }

}
