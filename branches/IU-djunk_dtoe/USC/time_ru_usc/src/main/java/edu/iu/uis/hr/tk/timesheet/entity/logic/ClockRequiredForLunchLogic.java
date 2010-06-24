package edu.iu.uis.hr.tk.timesheet.entity.logic;

import java.util.Date;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.rule.entity.DepartmentLunchRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ClockRequiredForLunchLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(ClockRequiredForLunchLogic.class);

//    private RuleService ruleService;
//    private TimesheetService timesheetService;
//    private WorkAreaDataAccess workAreaDataAccess;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof Clock)) {
            throw new IllegalArgumentException("ClockRequiredForLunchLogic's execute(Entity entity) method requires a non-null Entity of type Clock");
        }
        DepartmentLunchRule departmentLunchRule = new DepartmentLunchRule();
        try {
            departmentLunchRule = getRuleService().getActiveDepartmentLunchRule(getAssignmentService().getWorkArea(((Clock)entity).getClockLog().getWorkArea(), new Date()).getDepartment(), ((Clock)entity).getClockLog().getWorkArea(), ((Clock)entity).getClockLog().getUniversityId(), ((Clock)entity).getClockLog().getEmployeeRecord(), new Date());
        } catch (Exception e) {
            departmentLunchRule = null;
        }
        if (!Utility.hasValue(departmentLunchRule)) {
            ((Clock)entity).setClockRequiredForLunch(true);
        } else {
            ((Clock)entity).setClockRequiredForLunch(departmentLunchRule.isClockUseRequired());
        }
    }

    public RuleService getRuleService() {
        return (RuleService) Context.getService(RuleService.class);
    }

    public TimesheetService getTimesheetService() {
        return (TimesheetService) Context.getService(TimesheetService.class);
    }

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

}