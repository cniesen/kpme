package edu.iu.uis.hr.tk.directory.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.position.entity.Location;
import edu.iu.uis.hr.tk.directory.Utility;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public class UserImpl extends edu.iu.uis.hr.directory.entity.UserImpl implements User {

    private static final String MAINTENANCE_USER_ROLE = Utility.SUPERVISOR_ROLE;
    private static final String ADMINISTRATOR_USER_ROLE = "TK_ADMINISTRATOR";

    public UserImpl() {
        super();
    }

    public UserImpl(String networkId) {
        super(networkId);
    }

    public UserImpl(edu.iu.uis.hr.directory.entity.User user) {
        setNetworkId(user.getNetworkId());
        setRoles(user.getRoles());
        setUniversityId(user.getUniversityId());
        setEmailAddress(user.getEmailAddress());
        setName(user.getName());
    }
    
    protected String getMaintenanceUserRoleName() {
        return MAINTENANCE_USER_ROLE;
    }

    public boolean hasJurisdictionOver(Location location) {
        return isSystemAdministrator(location);
    }

    public boolean hasJurisdictionOver(Department department) {
        return isPayrollManager(department);
    }

    public boolean hasJurisdictionOverDepartments(List departments) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(departments) || !(departments.get(0) instanceof Department)) {
            throw new IllegalArgumentException("UserImpl's hasJurisdictionOverDepartments(List departments) method requires a non-null, non-empty List of Department objects.");
        }
        boolean hasJurisdictionOverDepartment = false;
        Iterator departmentsItr = departments.iterator();
        while (departmentsItr.hasNext()) {
            hasJurisdictionOverDepartment = hasJurisdictionOverDepartment | hasJurisdictionOver((Department) departmentsItr.next());
        }
        return hasJurisdictionOverDepartment;
    }

    public boolean hasJurisdictionOver(WorkArea workArea, Department department) {
        return isReviewer(workArea, department);
    }

    public boolean hasJurisdictionOver(String roleName, TypedPersistentMaintainedEntityList jobs){
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(jobs) || !(jobs.get(0) instanceof Job)) {
            throw new IllegalArgumentException("UserImpl's hasJurisdictionOver(String roleName, TypedPersistentMaintainedEntityList jobList) method requires a non-null, non-empty List of Job objects.");
        }
        
        if (isInterfaceManager()){ //god mode - has all roles for all depts and work areas
        	return true;
        }
        
        Iterator jobItr = jobs.iterator();
        while (jobItr.hasNext()) {
        	Job job = (Job)jobItr.next();
        	
        	if (Utility.SYSTEM_ADMINISTRATOR_ROLE.equals(roleName)) {
        		if (hasRole(Utility.getNestedRoleName(Utility.SYSTEM_ADMINISTRATOR_ROLE, job.getLocation()))) {
        			return true;
        		}
        	} else if (Utility.PAYROLL_MANAGER_ROLE.equals(roleName)) {
        		if (hasRole(Utility.getNestedRoleName(Utility.PAYROLL_MANAGER_ROLE, job.getDepartment()))) {
        			return true;
        		}
        	}
        	else if (Utility.VIEW_ONLY_ROLE.equals(roleName)) {
        		if (hasRole(Utility.getNestedRoleName(Utility.VIEW_ONLY_ROLE, job.getDepartment()))) {
        			return true;
        		}
        	}
        	
        	if (Utility.PAYROLL_PROCESSOR_ROLE.equals(roleName) || Utility.SUPERVISOR_ROLE.equals(roleName) || Utility.REVIEWER_ROLE.equals(roleName)) {
        		if (hasRole(Utility.getNestedRoleName(Utility.SYSTEM_ADMINISTRATOR_ROLE, job.getLocation())) || hasRole(Utility.getNestedRoleName(Utility.PAYROLL_MANAGER_ROLE, job.getDepartment()))) {
        			return true; //has role higher in hierarchy 
        		}
        		Iterator assignmentItr = job.getAssignments().iterator();
            	while (assignmentItr.hasNext()) {
            		String workArea = ((Assignment)assignmentItr.next()).getWorkArea().toString();
                    if (hasJurisdictionOver(workArea)) {
                        return true;
                    }
            	}
        	}

        }
    	return false;
    }

    
    public boolean hasJurisdictionOver(WorkArea workArea, Job job) {
        Department department = new Department();
        department.setDepartment(job.getDepartment());
        department.setLocation(job.getLocation());
        return isReviewer(workArea, department);
    }

    private boolean hasJurisdictionOver(String workArea) {
        if (hasRole(Utility.getNestedRoleName(Utility.PAYROLL_PROCESSOR_ROLE, workArea))) {
            return true;
        }
        if (hasRole(Utility.getNestedRoleName(Utility.SUPERVISOR_ROLE, workArea))) {
            return true;
        }
        if (hasRole(Utility.getNestedRoleName(Utility.REVIEWER_ROLE, workArea))) {
            return true;
        }
        return false;
    }
   
    public boolean hasSystemRole() {
        return isInterfaceManager();
    }

    public boolean hasLocationRole() {
        return isSystemAdministrator();
    }

    public boolean hasDepartmentRole() {
        return isPayrollManager();
    }

    public boolean hasSupervisoryRole() {
        return isSupervisor();
    }

    public boolean hasWorkAreaRole() {
        return isReviewer();
    }

    public boolean hasAdministrativeRole() {
        return hasRole(ADMINISTRATOR_USER_ROLE);
    }

    public List getSystemAdministratorLocations() {
        return getUniqueRoleData(Utility.SYSTEM_ADMINISTRATOR_ROLE);
    }

    public List getPayrollManagerDepartments() {
        return getUniqueRoleData(Utility.PAYROLL_MANAGER_ROLE);
    }
    
    public List getViewOnlyDepartments() {
        return getUniqueRoleData(Utility.VIEW_ONLY_ROLE);
    }
    
    public List getPayrollProcessorWorkAreas() {
        return getUniqueRoleData(Utility.PAYROLL_PROCESSOR_ROLE);
    }

    public List getSupervisorWorkAreas() {
        return getUniqueRoleData(Utility.SUPERVISOR_ROLE);
    }

    public List getReviewerWorkAreas() {
        return getUniqueRoleData(Utility.REVIEWER_ROLE);
    }

    public boolean isInterfaceManager() {
        return hasRole(Utility.INTERFACE_MANAGER_ROLE);
    }

    private boolean isSystemAdministrator(Location location) {
        return isInterfaceManager() || hasRole(Utility.getNestedRoleName(Utility.SYSTEM_ADMINISTRATOR_ROLE, location.getLocation()));
    }

    private boolean isPayrollManager(Department department) {
        return isSystemAdministrator(department.getLocationObject()) || hasRole(Utility.getNestedRoleName(Utility.PAYROLL_MANAGER_ROLE, department.getDepartment()));
    }

    private boolean isPayrollProcessor(WorkArea workArea, Department department) {
        return isPayrollManager(department) || hasRole(Utility.getNestedRoleName(Utility.PAYROLL_PROCESSOR_ROLE, workArea.getWorkArea().toString()));
    }

    private boolean isSupervisor(WorkArea workArea, Department department) {
        return isPayrollProcessor(workArea, department) || hasRole(Utility.getNestedRoleName(Utility.SUPERVISOR_ROLE, workArea.getWorkArea().toString()));
    }

    private boolean isReviewer(WorkArea workArea, Department department) {
        return isSupervisor(workArea, department) || hasRole(Utility.getNestedRoleName(Utility.REVIEWER_ROLE, workArea.getWorkArea().toString()));
    }

    private boolean isSystemAdministrator() {
        return hasRole(Utility.SYSTEM_ADMINISTRATOR_ROLE);
    }

    private boolean isPayrollManager() {
        return hasRole(Utility.PAYROLL_MANAGER_ROLE);
    }

    private boolean isPayrollProcessor() {
        return hasRole(Utility.PAYROLL_PROCESSOR_ROLE);
    }

    private boolean isSupervisor() {
        return hasRole(Utility.SUPERVISOR_ROLE);
    }

    public boolean isReviewer() {
        return hasRole(Utility.REVIEWER_ROLE);
    }

    private List getUniqueRoleData(String parentRoleName) {
        List data = new ArrayList();
        Iterator roles = getRoles().iterator();
        while (roles.hasNext()) {
            String roleName = (String) roles.next();
            if (roleName.startsWith(parentRoleName + edu.iu.uis.hr.Utility.UNDERSCORE)) {
                data.add(roleName.substring(roleName.lastIndexOf(edu.iu.uis.hr.Utility.UNDERSCORE) + 1, roleName.length()));
            }
        }
        return data;
    }
}