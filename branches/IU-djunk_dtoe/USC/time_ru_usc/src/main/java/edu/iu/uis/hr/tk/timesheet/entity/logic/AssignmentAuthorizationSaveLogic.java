package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class AssignmentAuthorizationSaveLogic extends AbstractAssignmentAuthorizationLogic {

    private static final Logger LOG = Logger.getLogger(AssignmentAuthorizationSaveLogic.class);

    public void execute(Entity entity) {
        if (!(entity instanceof TimesheetDocument)) {
            throw new IllegalArgumentException("AssignmentAuthorizationSaveLogic's execute(Entity entity) method requires a non-null Entity of type TimesheetDocument");
        }
        User user = (User)getWebUserService().getUser();
        String universityId = ((TimesheetDocument)entity).getDocumentHeader().getUniversityId();
        if (user.getUniversityId().equals(universityId)) {
            // user is editing, no need to check assignment auth logic
            return;
        }
        List savedTimeBlocks = getTimesheetService().getTimeBlocks(((TimesheetDocument)entity).getDocumentId());
        // Build a map of saved to check individual time blocks from the form
        Map savedTimeBlocksMap = new HashMap();
        Iterator savedTimeBlocksIterator = savedTimeBlocks.iterator();
        while (savedTimeBlocksIterator.hasNext()) {
            TimeBlock savedTimeBlock = (TimeBlock) savedTimeBlocksIterator.next();
            String key = createTimeBlockKey(savedTimeBlock);
            savedTimeBlocksMap.put(key, savedTimeBlock);
        }
        // TODO should be added to an object (perhaps timesheet service) since it is also used elsewhere - rpiercy
        Iterator hoursDetailsIterator = ((TimesheetDocument)entity).getHours().getHoursDetails().iterator();
        while (hoursDetailsIterator.hasNext()) {
            HoursDetail hoursDetail = (HoursDetail)hoursDetailsIterator.next();
            Iterator hourDetailsIterator = hoursDetail.getHourDetails().iterator();
            while (hourDetailsIterator.hasNext()) {
                HourDetail hourDetail = (HourDetail)hourDetailsIterator.next();
                TimeBlock timeBlock = new TimeBlock();
                setTimeBlock((TimesheetDocument)entity, hourDetail, timeBlock);
                String key = createTimeBlockKey(timeBlock);
                if (!savedTimeBlocksMap.containsKey(key)) {
                    // new time block
                    if ( Utility.hasValue(timeBlock.getWorkArea()) && !hasAuthorization(user, universityId, hourDetail)) {
                        hourDetail.getEntityErrors().add(new String[] { Assignment.ASSIGNMENT }, getMessage(MessageKeyConstants.ERROR_INVALID_HOUR_DETAIL_ASSIGNMENT));
                    }
                }
            }
        }
    }

    public TimesheetService getTimesheetService() {
        return (TimesheetService) Context.getService(TimesheetService.class);
    }

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

    private String createTimeBlockKey(TimeBlock timeBlock) {
        StringBuffer key = new StringBuffer();
        key.append(timeBlock.getDocumentId());
        key.append(timeBlock.getEmployeeRecord());
        key.append(timeBlock.getWorkArea());
        key.append(timeBlock.getTask());
        key.append(timeBlock.getEarnCode());
        key.append(timeBlock.getBeginTime().getDate().getTime());
        key.append(timeBlock.getEndTime().getDate().getTime());
        return key.toString();
    }
    
    private void setTimeBlock(TimesheetDocument timesheetDocument, HourDetail hourDetail, TimeBlock timeBlock) {
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
        // set only primary key fields since we are simply checking logic
        timeBlock.setDocumentId(timesheetDocument.getDocumentId());
        timeBlock.setEmployeeRecord((BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD));
        timeBlock.setWorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA));
        timeBlock.setTask((BigDecimal)assignmentKeyMap.get(FieldNames.TASK));
        timeBlock.setEarnCode(hourDetail.getEarnCode());
        timeBlock.setBeginTime(new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getBeginTime()).getTimestamp());
        timeBlock.setEndTime(new TimedDate(hourDetail.getHoursDetail().getDate(), hourDetail.getEndTime()).getTimestamp());
    }
    
    private boolean hasAuthorization(User user, String universityId, HourDetail hourDetail) {
        Map assignmentKeyMap = getAssignmentService().getAssignmentKeyMapFromCommaDelimitedList(hourDetail.getAssignment());
        BigDecimal nWorkArea = new BigDecimal((assignmentKeyMap.get(FieldNames.WORK_AREA)).toString());
        WorkArea hrWorkArea = getAssignmentService().getWorkArea(nWorkArea, hourDetail.getHoursDetail().getDate());
        BigDecimal nEmpRec = new BigDecimal((assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD)).toString());
        Job job = getJobService().getJob(universityId, nEmpRec, hourDetail.getHoursDetail().getDate());
        return user.hasJurisdictionOver(hrWorkArea, job);
    }
}