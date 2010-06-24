package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.AbstractAuthorization;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class ClockModeAuthorization extends AbstractAuthorization implements ModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof Clock)) {
            throw new IllegalArgumentException("ClockModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable Clock object.");
        }
        modeApplicable.setMode(getModeAuthorizationFactory().getViewMode(modeApplicable));
        if (Utility.hasValue(((Clock)modeApplicable).getClockLog().getUniversityId()) && ((Clock)modeApplicable).getClockLog().getUniversityId().equals(getUser().getUniversityId()) && getAssignmentService().hasSynchronousAssignment(((Clock)modeApplicable).getClockLog().getUniversityId())) {
            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
        	((Clock)modeApplicable).setHasClockAssignment(true);
        } else if (Utility.hasValue(((Clock)modeApplicable).getClockLog().getUniversityId()) && !((Clock)modeApplicable).getClockLog().getUniversityId().equals(getUser().getUniversityId()) && (getUser().hasSupervisoryRole() || getUser().hasWorkAreaRole())) {
            List synchronousAssignments = getAssignmentService().getSynchronousAssignments(((Clock)modeApplicable).getClockLog().getUniversityId());
            if (Utility.hasValue(synchronousAssignments)) {
            	((Clock)modeApplicable).setHasClockAssignment(true);
                Iterator synchronousAssignmentsItr = synchronousAssignments.iterator();
                while (synchronousAssignmentsItr.hasNext()) {
                    Assignment assignment = (Assignment)synchronousAssignmentsItr.next();
                    Iterator departmentsItr = getPositionService().getDepartments(Arrays.asList(new String[] {assignment.getJob().getDepartment()}), new Date()).iterator();
                    while (departmentsItr.hasNext()) {
                        if (getUser().hasJurisdictionOver(getAssignmentService().getWorkArea(assignment.getWorkArea(), new Date()), (Department)departmentsItr.next())) {
                            modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));
                        }
                    }
                }
            }
        }
    }

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

    private PositionService getPositionService() {
        return (PositionService) Context.getService(PositionService.class);
    }

}
