package edu.iu.uis.hr.tk.directory.entity;

import java.util.List;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.position.entity.Location;
import edu.iu.uis.hr.tk.job.entity.Job;

public interface User extends edu.iu.uis.hr.directory.entity.User {
    public boolean hasJurisdictionOver(Location location);

    public boolean hasJurisdictionOver(Department department);

    public boolean hasJurisdictionOverDepartments(List departments);

    public boolean hasJurisdictionOver(WorkArea workArea, Department department);
    
    public boolean hasJurisdictionOver(String roleName, TypedPersistentMaintainedEntityList jobs);
    
    public boolean hasJurisdictionOver(WorkArea workArea, Job job);

    public boolean hasSystemRole();

    public boolean hasLocationRole();

    public boolean hasDepartmentRole();

    public boolean hasSupervisoryRole();

    public boolean hasWorkAreaRole();

    public boolean hasAdministrativeRole();
    
    public boolean isInterfaceManager();

    public List getSystemAdministratorLocations();

    public List getPayrollManagerDepartments();
    
    public List getViewOnlyDepartments();

    public List getPayrollProcessorWorkAreas();

    public List getSupervisorWorkAreas();

    public List getReviewerWorkAreas();
}