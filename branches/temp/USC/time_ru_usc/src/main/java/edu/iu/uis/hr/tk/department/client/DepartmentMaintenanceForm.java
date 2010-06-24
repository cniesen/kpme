package edu.iu.uis.hr.tk.department.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.Optionable;
import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.Task;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.department.service.DepartmentService;
import edu.iu.uis.hr.tk.directory.service.RolesService;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.service.TranslateService;

public class DepartmentMaintenanceForm extends AbstractMaintenanceActionForm {

    private static final Logger LOG = Logger.getLogger(DepartmentMaintenanceForm.class);
	private static final long serialVersionUID = -1262250495147538958L;
    private static final String DEPARTMENT_WORKAREA_NESTING_PATH="department.workArea";

    private Department department;

    public DepartmentMaintenanceForm() {
        super();
        setDepartment(new Department());
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department != null) {
            this.department = department;
        }
    }

    protected TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        return getDepartment().getWorkAreas();
    }

    public Class getModeAuthorization() {
        return DepartmentMaintenanceFormModeAuthorization.class;
    }

    public void save() {
        LOG.debug("Started save method of DepartmentMaintenanceForm");
        // TODO : what's the plan for unsavedWorkAreas
        Map unsavedWorkAreas = new HashMap();
        Iterator workAreas = getDepartment().getWorkAreas().iterator();
        while (workAreas.hasNext()) {
            WorkArea workArea = (WorkArea) workAreas.next();
            try {
                ((DepartmentService) getService(DepartmentService.class)).saveWorkArea(workArea);
            } catch (Exception e) {
                LOG.info("Could Not Save WorkArea " + workArea.getWorkArea() + ": " + e.getMessage());
                unsavedWorkAreas.put(workArea.getWorkArea(), workArea);
            }
        }
        ((DepartmentService) getService(DepartmentService.class)).saveDepartmentRoleUsers(getDepartment());
        // TODO these have been pushed down to roles service
        //((DepartmentService) getService(DepartmentService.class)).saveDepartmentViewOnlyRoleUsers(getDepartment());
        //((DepartmentService) getService(DepartmentService.class)).saveDepartmentKioskManagers(getDepartment());
        //((DepartmentService) getService(DepartmentService.class)).saveDepartmentKiosks(getDepartment());
        LOG.debug("Finished save method of DepartmentMaintenanceForm");
    }

    public void setLookupResults(List lookupResults) {
        // need to load tasks
        for (Iterator iter = lookupResults.iterator(); iter.hasNext();) {
            WorkArea workArea=(WorkArea)iter.next();
            workArea.setTasks(((AssignmentService) getService(AssignmentService.class)).getCurrentWorkArea(workArea.getWorkArea(),new Date()).getTasks());
            getDepartment().getWorkAreas().add(workArea);
        }
        // TODO : still not sure how to get deptid if resultsets is empty
        if (Utility.hasValue(lookupResults)) {
            setDepartment(((WorkArea) getDepartment().getWorkArea(0)).getDepartment());
        } else {
            setDepartment(getFieldFromLookupForm());
        }

    }

    public void initialize() {
        LOG.debug("Started initialize method of DepartmentMaintenanceForm");
        ((DepartmentService) getService(DepartmentService.class)).setDepartmentRolesUsers(getDepartment());
        ((DepartmentService) getService(DepartmentService.class)).setDepartmentViewOnlyRolesUsers(getDepartment());
        ((DepartmentService) getService(DepartmentService.class)).setDepartmentKioskManagers(getDepartment());
        Iterator i = getDepartment().getWorkAreas().iterator();
        while (i.hasNext()) {
            ((DepartmentService) getService(DepartmentService.class)).setWorkAreaRolesUsers((WorkArea) i.next());
        }
        LOG.debug("Finished initialize method of DepartmentMaintenanceForm");
    }

    public void prepare() {
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.DEPARTMENT_ROLE_NAME_DROPDOWN, ((RolesService) getService(RolesService.class)).getDepartmentRoleNames());
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE_NAME_DROPDOWN, ((RolesService) getService(RolesService.class)).getViewOnlyRoleNames());
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.WORK_AREA_ROLE_NAME_DROPDOWN, ((RolesService) getService(RolesService.class)).getWorkAreaRoleNames());
        setFieldOptions(edu.iu.uis.hr.entity.FieldNames.EMPLOYEE_TYPE, ((TranslateService) getService(TranslateService.class)).getEmployeeTypes());
        setFieldOptions(edu.iu.uis.hr.entity.FieldNames.OVERTIME_PREFERENCE, ((EarningService) getService(EarningService.class)).getDropdownWeeklyOvertimeEarnCodes(new Date()));
        addOption(edu.iu.uis.hr.entity.FieldNames.OVERTIME_PREFERENCE,"BOTH: CPE editable by employee","BOT");
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.KIOSK_ADMIN_ROLE_NAME_DROPDOWN, ((RolesService) getService(RolesService.class)).getKioskAdminRoleNames());
        ((DepartmentService) getService(DepartmentService.class)).setRoleUsersInfo(getDepartment());
        ((DepartmentService) getService(DepartmentService.class)).setWorkAreaDepartments(getDepartment());
        //((DepartmentService) getService(DepartmentService.class)).setKiosks(getDepartment());
    }
    
    private void addOption(String fieldName, String label, String code) {
        List list = (List)getOptionsMap().get(fieldName);
        Optionable newOptionable = new Option();
        newOptionable.setLabel(label);
        newOptionable.setValue(code);
        list.add(newOptionable);
    }    

    public void setDepartment(String department) {
        getDepartment().setDepartment(department);
    }

    public final void addRows(BigDecimal rowsToAdd) {
        setMaintenanceNestingPath(DEPARTMENT_WORKAREA_NESTING_PATH);
        addRows(rowsToAdd.intValue());
    }
    
    public final void delete() {
        // check to make sure the task they are trying to delete does not have active assignments.
        int index = getIndex();
        TypedPersistentMaintainedEntityList tasks = getTypedPersistentMaintainedEntityList();
        if (tasks.get(index) instanceof Task) {
            Task task = (Task)tasks.get(index);
            JobService jobService = (JobService)getService(JobService.class);
            List jobs = jobService.getJobs(new Date(), task.getWorkArea().getWorkArea(), task.getTask());
            if (Utility.hasValue(jobs)) {
                //cannot delete
                task.getEntityErrors().add(new String[] { FieldNames.TASK }, Context.getMessage(edu.iu.uis.hr.tk.department.entity.logic.MessageKeyConstants.ERROR_WORK_AREA_HAS_ACTIVE_ASSIGNMENT));
            } else {
                super.delete();
            }
        } else {
            super.delete();
        }
    }
}
                