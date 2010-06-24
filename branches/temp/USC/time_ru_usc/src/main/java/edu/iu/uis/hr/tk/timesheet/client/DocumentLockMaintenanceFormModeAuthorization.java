package edu.iu.uis.hr.tk.timesheet.client;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;

public class DocumentLockMaintenanceFormModeAuthorization extends MaintenanceActionFormModeAuthorization {

    public void execute(ModeApplicable modeApplicable) {
    	
//    	if  (StringUtils.isBlank(((DocumentLockMaintenanceForm) modeApplicable).getFieldsToReturn())) {
//    		modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
//    	} else {
//    		modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
//    	}

    	String documentId = ((DocumentLockMaintenanceForm) modeApplicable).getDocumentLock(0).getDocumentId();
    	if (documentId == null ) {
    		documentId = ((DocumentLockMaintenanceForm) modeApplicable).getFieldsToReturn();
    	}
    	String userUniversityId = ((DocumentLockMaintenanceForm) modeApplicable).getDocumentLock(0).getUserUniversityId();
    	modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));

        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof DocumentLockMaintenanceForm)) {
            throw new IllegalArgumentException("DocumentLockMaintenanceFormModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable DocumentLockMaintenanceForm object.");
        }
        if (getUser().hasSystemRole()) {
            modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
        }
        // https://onestart.iu.edu/jira/browse/TK-486
        else if(StringUtils.isNotEmpty(documentId)) {
        	try {
        		DocumentHeader documentHeader = TKServiceLocator.getTimesheetService().getDocumentHeader(documentId);
                List synchronousAssignments = getAssignmentService().getAssignments(documentHeader.getUniversityId());
                if (Utility.hasValue(synchronousAssignments)) {
                    Iterator synchronousAssignmentsItr = synchronousAssignments.iterator();
                    while (synchronousAssignmentsItr.hasNext()) {
                        Assignment assignment = (Assignment)synchronousAssignmentsItr.next();
                        Iterator departmentsItr = getPositionService().getDepartments(Arrays.asList(new String[] {assignment.getJob().getDepartment()}), new Date()).iterator();
                        while (departmentsItr.hasNext()) {
                            if (getUser().hasJurisdictionOver(getAssignmentService().getWorkArea(assignment.getWorkArea(), new Date()), (Department)departmentsItr.next())) {
                                modeApplicable.setMode(getModeFactory().getEditMode(modeApplicable));
                                return;
                            }
                        }
                    }
                }
        	} catch (Exception e) {
        		modeApplicable.setMode(getModeFactory().getViewMode(modeApplicable));
			}
        }
    }

    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

	public PositionService getPositionService() {
		return (PositionService) Context.getService(PositionService.class);
	}

}
